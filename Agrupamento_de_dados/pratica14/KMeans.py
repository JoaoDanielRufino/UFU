import numpy as np
import random
from DistanceFinder import DistanceFinder
from sklearn import metrics


class KMeans:
    def __init__(self, k=3, iterations=100, tol=0.001):
        self.k = k
        self.iterations = iterations
        self.tol = 0.001
        self.centroids = {}
        self.classifications = {}
        self.distanceFinder = DistanceFinder()

    def __initializeCentroids(self, data):
        for i in range(self.k):
            self.centroids[i] = random.choice(data)

    def __initializeClassification(self):
        self.classifications = {}
        for i in range(self.k):
            self.classifications[i] = []

    def __updateCentroids(self):
        for i in self.classifications:
            self.centroids[i] = np.average(self.classifications[i], axis=0)

    def __isOptimized(self, prevCentroids):
        for key, currCentroid in self.centroids.items():
            prevCentroid = prevCentroids[key]
            tmp = np.sum((currCentroid -
                          prevCentroid) / prevCentroid*100.0)
            if tmp > self.tol:
                return False
        return True

    def __createConfusionMatrix(self):
        confusionMatrix = []

        for classification in self.classifications:
            aux = [0]*len(self.classifications)

            for featureset in self.classifications[classification]:
                pos = self.predict(featureset)
                aux[pos] += 1

            confusionMatrix.append(aux)

        return confusionMatrix

    def purity(self):
        confusionMatrix = self.__createConfusionMatrix()

        purity = []
        for i in range(len(confusionMatrix)):
            p = max(confusionMatrix[i])/sum(confusionMatrix[i])
            purity.append(p)

        return confusionMatrix, purity

    def fit(self, data):
        self.__initializeCentroids(data)

        for _ in range(self.iterations):
            self.__initializeClassification()

            for feature in data:
                distances = []
                for centroid in self.centroids.values():
                    distances.append(np.linalg.norm(
                        self.distanceFinder.euclidean(feature, centroid)))
                minIndex = distances.index(min(distances))
                self.classifications[minIndex].append(feature)

            prevCentroids = dict(self.centroids)

            self.__updateCentroids()

            if self.__isOptimized(prevCentroids):
                break

    def predict(self, feature):
        distances = []
        for centroid in self.centroids.values():
            distances.append(self.distanceFinder.euclidean(feature, centroid))

        return distances.index(min(distances))
