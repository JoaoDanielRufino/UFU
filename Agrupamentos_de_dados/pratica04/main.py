import sys

from CSVManager import CSVManager
from DistanceFinder import DistanceFinder


def main():
    method = sys.argv[1]
    filePath = sys.argv[2]

    csvManager = CSVManager()
    df = csvManager.read(filePath)
    formattedCSV = csvManager.deleteObjectColumns(df)

    distanceFinder = DistanceFinder()

    if method == "euclidean":
        print("Computing euclidean distance...")

        euclideanMatrix = distanceFinder.euclideanAll(formattedCSV)
        csvMatrix = csvManager.convertMatrixToCSV(euclideanMatrix)
        csvManager.writeCSV(csvMatrix, "./dataset/euclidean.csv")

        print("Eclidean distance created with success!")
    elif method == "manhattan":
        print("Computing manhattan distance...")

        manhattanMatrix = distanceFinder.manhattanAll(formattedCSV)
        csvMatrix = csvManager.convertMatrixToCSV(manhattanMatrix)
        csvManager.writeCSV(csvMatrix, "./dataset/manhattan.csv")

        print("Manhattan distance created with success!")
    else:
        print("Please enter with a valid input, 'euclidean' or 'manhattan'")


if __name__ == "__main__":
    main()
