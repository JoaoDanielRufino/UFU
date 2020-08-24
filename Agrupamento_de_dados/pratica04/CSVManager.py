import pandas as pd
import numpy as np


class CSVManager:
    def read(self, filePath):
        return pd.read_csv(filePath)

    def deleteObjectColumns(self, df):
        return df.drop(columns=['class'])

    def convertMatrixToCSV(self, mat):
        csvMatrix = np.asarray(mat)
        return csvMatrix

    def writeCSV(self, csv, path):
        np.savetxt(path, csv, delimiter=",", fmt="%10.5f")
