import pandas as pd
from DistanceFinder import DistanceFinder


class SimplifiedSilhouette:
    def __init__(self, df, kmeans):
        self.kmeans = kmeans
        self.df = df
        self.clusters = self.kmeans.centroids
        self.distanceFinder = DistanceFinder()
        self.__distanceMatrix = self.__getDistanceMatrix()

    def __getDistanceMatrix(self):
        distanceMatrix = []
        for a in self.df.iloc:
            aux = []
            for cluster in self.clusters.values():
                aux.append(self.distanceFinder.euclidean(a.values, cluster))
            distanceMatrix.append(aux)
        return distanceMatrix

    def __get_a(self, row):
        cluster = self.kmeans.predict(self.df.iloc[row])
        return self.__distanceMatrix[row][cluster]

    def __get_b(self, row):
        cluster_id = self.kmeans.predict(self.df.iloc[row])
        minDist = 9999999999

        for cluster in range(len(self.__distanceMatrix[row])):
            if cluster != cluster_id:
                minDist = min(minDist, self.__distanceMatrix[row][cluster])

        return minDist

    def calculate(self):
        silhouette = []
        ss = 0

        for i in range(len(self.__distanceMatrix)):
            a = self.__get_a(i)
            b = self.__get_b(i)
            s_i = (b - a) / max(a, b)
            silhouette.append([a, b, s_i])
            ss += s_i

        return ss/len(silhouette)
