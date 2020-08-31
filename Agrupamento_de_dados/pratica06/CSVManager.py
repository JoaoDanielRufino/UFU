import pandas as pd
import numpy as np


class CSVManager:
    def read(self, filePath):
        return pd.read_csv(filePath)

    def deleteObjectColumns(self, df):
        return df.select_dtypes(include=['float64'], exclude=['object'])

    def convertMatrixToCSV(self, mat):
        csvMatrix = np.asarray(mat)
        return csvMatrix

    def convertCSVToMatrix(self, df):
        return df.to_numpy()

    def writeCSV(self, csv, path):
        np.savetxt(path, csv, delimiter=",", fmt="%10.5f")

    def replaceNan(self, df):
        cols_with_missing = [
            col for col in df.columns if df[col].isnull().any()]

        for i in cols_with_missing:
            if type(df[i][0]) != str:
                df[i].fillna(df[i].mean(), inplace=True)
            else:
                df[i].fillna('No-Value', inplace=True)

        return df
