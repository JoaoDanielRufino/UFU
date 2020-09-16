from CSVManager import CSVManager
from SingleLink import SingleLink
from DistanceFinder import DistanceFinder
import sys


def main():
    path = sys.argv[1]

    csvManager = CSVManager()
    df = csvManager.read(path)
    df = csvManager.replaceNan(df)
    formattedCSV = csvManager.deleteObjectColumns(df)
    singleLink = SingleLink(formattedCSV)


if __name__ == "__main__":
    main()
