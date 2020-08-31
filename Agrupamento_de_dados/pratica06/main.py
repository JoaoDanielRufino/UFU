import sys
import matplotlib.pyplot as plt
import random
import numpy as np
import argparse
from CSVManager import CSVManager
from KMeans import KMeans


def randomColor():
    return ["#"+''.join([random.choice('0123456789ABCDEF') for j in range(6)])]


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('path', type=str, help="path to dataset")
    parser.add_argument('--k', type=int, default=3,
                        help="quantity of clusters (default 3)")
    parser.add_argument('--it', type=int, default=100,
                        help="max iterations (default 100)")
    parser.add_argument('--tol', type=float, default=0.001,
                        help="tolerance (default 0.001)")
    args = parser.parse_args()

    csvManager = CSVManager()
    df = csvManager.read(args.path)

    df = csvManager.replaceNan(df)

    formattedCSV = csvManager.deleteObjectColumns(df)
    matrix = csvManager.convertCSVToMatrix(formattedCSV)

    kmeans = KMeans(args.k, args.it, args.tol)

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


if __name__ == "__main__":
    main()
