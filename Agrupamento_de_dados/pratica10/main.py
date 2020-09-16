from CSVManager import CSVManager
from SingleLink import SingleLink
from DistanceFinder import DistanceFinder
import pandas as pd


def main():
    csvManager = CSVManager()
    df = csvManager.read("dataset/iris.data")
    df = csvManager.replaceNan(df)
    formattedCSV = csvManager.deleteObjectColumns(df)
    singleLink = SingleLink(formattedCSV)

    print(singleLink.SingleLink())


if __name__ == "__main__":
    main()
