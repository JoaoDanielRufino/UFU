import sys
import matplotlib.pyplot as plt
import random
import numpy as np
import argparse
from CSVManager import CSVManager
from KMeans import KMeans
import pandas as pd
import os


def randomColor():
    return ["#"+''.join([random.choice('0123456789ABCDEF') for j in range(6)])]


def saveData(confusionMatrix, purity, datasetPath, k):
    df = pd.read_csv(datasetPath)
    rs = df['class'].unique()
    rs = np.append(rs, ['Purity'])
    indexNames = ['k' + str(i) for i in range(k)] + ['Total']
    new_df = pd.DataFrame(columns=rs, index=indexNames)

    for i in range(len(confusionMatrix)):
        for j in range(len(confusionMatrix[i])):
            new_df.loc[indexNames[i], rs[j]] = confusionMatrix[i][j]

    for i in range(len(purity)):
        new_df.loc[indexNames[i], 'Purity'] = purity[i]

    for i in range(len(rs)-1):
        new_df.loc['Total', rs[i]] = new_df[rs[i]].sum()

    new_df.fillna(0, inplace=True)

    totalPurity = 0
    for i in range(len(confusionMatrix)):
        totalPurity += (sum(confusionMatrix[i]) / len(df)) * purity[i]

    new_df.loc['Total', 'Purity'] = totalPurity
    print(new_df, end="\n\n")
    new_df.to_csv(os.path.join('results', 'result_k') + str(k) + '.csv')


def main():
    path = sys.argv[1]

    csvManager = CSVManager()
    df = csvManager.read(path)

    df = csvManager.replaceNan(df)

    formattedCSV = csvManager.deleteObjectColumns(df)
    matrix = csvManager.convertCSVToMatrix(formattedCSV)

    try:
        for k in range(2, 5):
            kmeans = KMeans(k)
            kmeans.fit(matrix)

            for centroid in kmeans.centroids:
                plt.scatter(kmeans.centroids[centroid][0], kmeans.centroids[centroid][1],
                            marker="o", color="k", s=150, linewidths=5)

            for classification in kmeans.classifications:
                color = randomColor()
                for featureset in kmeans.classifications[classification]:
                    plt.scatter(featureset[0], featureset[1],
                                marker="x", color=color, s=60, linewidths=2)

            plt.show()

            confusionMatrix, purity = kmeans.purity()
            saveData(confusionMatrix, purity, path, k)
    except Exception:
        print("An empty cluster was found, please run the program again. This program does not handle empty clusters")


if __name__ == "__main__":
    main()
