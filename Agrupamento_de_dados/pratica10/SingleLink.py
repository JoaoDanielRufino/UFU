from DistanceFinder import DistanceFinder
import math
import pandas as pd


class SingleLink:
    def __init__(self, df):
        self.distanceFinder = DistanceFinder()
        distances = self.distanceFinder.euclideanAll(df)
        self.distanceMatrix = self.__convertToDict(distances)
        # A simple mock test from pratica-09

        # self.distanceMatrix = {
        #     0: [0, 2, 6, 10, 9],
        #     1: [2, 0, 5, 9, 8],
        #     2: [6, 5, 0, 4, 5],
        #     3: [10, 9, 4, 0, 3],
        #     4: [9, 8, 5, 3, 0]
        # }

        self.dfDistance = pd.DataFrame(self.distanceMatrix)
        open("result.txt", "w").close()  # Clearing the result file

    def __combine(self, i, j):
        return str(i) + ', ' + str(j)

    def SingleLink(self):
        while len(self.dfDistance) > 2:
            # print(self.dfDistance, end="\n\n")
            self.__printDf()
            i, j = self.__posMinValue()  # Find cluster (i, j) to join
            newDistancesMatrix = pd.DataFrame()
            newDistancesMatrix[self.__combine(i, j)] = [  # Create a new distances matrix with new joined cluster
                0]*(len(self.dfDistance)-1)

            for column in self.dfDistance.columns:
                if column not in (i, j):
                    newDistancesMatrix[column] = [0]*(len(self.dfDistance)-1)

            # Rename the rows with neu joined clusters
            newDistancesMatrix = self.__renameRowsDf(newDistancesMatrix)

            for k in newDistancesMatrix.columns:
                for k2 in newDistancesMatrix.columns:
                    if k == k2:
                        # If clusters are the same, add 0
                        newDistancesMatrix[k][k2] = 0
                    else:
                        if type(k) is str:
                            # Case where we need to find minDistance beetwen merged clusters, example: (i, j) = (1, 2), k = (1, 2) and k2 = 3 then minValue = min((1, 3), (2, 3))
                            if (i in eval(k)) or (j in eval(k)):
                                minV = min(
                                    self.dfDistance[i][k2], self.dfDistance[j][k2])
                                newDistancesMatrix[k][k2] = minV
                            # If current cluster is not in a joined cluster, just add the previous value
                            elif k in self.dfDistance.columns and k2 in self.dfDistance.columns:
                                newDistancesMatrix[k][k2] = self.dfDistance[k][k2]
                        if type(k2) is str:
                            if (i in eval(k2)) or (j in eval(k2)):
                                minV = min(
                                    self.dfDistance[i][k], self.dfDistance[j][k])
                                newDistancesMatrix[k][k2] = minV
                            # If current cluster is not in a joined cluster, just add the previous value
                            elif k in self.dfDistance.columns and k2 in self.dfDistance.columns:
                                newDistancesMatrix[k][k2] = self.dfDistance[k][k2]
                        elif type(k) is int and type(k2) is int:
                            # If current cluster is not in a joined cluster, just add the previous value
                            newDistancesMatrix[k][k2] = self.dfDistance[k][k2]

            # print(newDistancesMatrix)
            self.dfDistance = newDistancesMatrix

        self.__printDf()
        return self.dfDistance

    def __renameRowsDf(self, newDistancesMatrix):
        index = 0
        aux = {}
        for column in newDistancesMatrix.columns:
            aux[index] = column
            index += 1

        return newDistancesMatrix.rename(index=aux)

    def __posMinValue(self):
        minV = math.inf
        for i in self.dfDistance:
            for j in self.dfDistance[i].index:
                if i != j and self.dfDistance[i][j] < minV:
                    minV = self.dfDistance[i][j]
                    res = (i, j)

        return res

    def __convertToDict(self, matrix):
        return {idx: val for idx, val in enumerate(matrix)}

    def __printDf(self):
        tmp = ""
        for cluster in self.dfDistance.columns:
            tmp += "{ " + str(cluster) + " }, "
        print("Hierarchy: ", tmp, end="\n\n")
        with open("result.txt", "a") as file:
            file.write(tmp + "\n")
