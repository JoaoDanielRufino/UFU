import sys
import matplotlib.pyplot as plt
import random
import numpy as np
import argparse
from CSVManager import CSVManager
from KMeans import KMeans
import pandas as pd
import os
from SimplifiedSilhoutte import SimplifiedSilhouette


def main():
    path = sys.argv[1]

    csvManager = CSVManager()
    df = csvManager.read(path)

    df = csvManager.replaceNan(df)

    formattedCSV = csvManager.deleteObjectColumns(df)

    formattedCSV = csvManager.deleteObjectColumns(df)
    matrix = csvManager.convertCSVToMatrix(formattedCSV)

    try:
        with open('result/result.txt', 'w') as file:
            res = ''
            for k in range(2, 5):
                kmeans = KMeans(k)
                kmeans.fit(matrix)

                simplifiedSilhouette = SimplifiedSilhouette(
                    formattedCSV, kmeans)
                sswc = simplifiedSilhouette.calculate()
                res += 'K = ' + str(k) + '; ' + 'SSWC = ' + str(sswc) + '\n'
            file.write(res)

    except Exception:
        print("An empty cluster was found, please run the program again. This program does not handle empty clusters")


if __name__ == "__main__":
    main()
