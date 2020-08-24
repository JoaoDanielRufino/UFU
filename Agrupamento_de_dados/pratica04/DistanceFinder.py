import math


class DistanceFinder:
    def euclidean(self, x, y):
        acc = 0
        for i, j in zip(x, y):
            acc += (i - j) ** 2
        return round(math.sqrt(acc), 2)

    def manhattan(self, x, y):
        acc = 0
        for i, j in zip(x, y):
            acc += abs((i - j))
        return acc

    def euclideanAll(self, df):
        m = []
        for a in df.iloc:
            tmp = []
            for b in df.iloc:
                tmp.append(self.euclidean(a.values, b.values))
            m.append(tmp)
        return m

    def manhattanAll(self, df):
        m = []
        for a in df.iloc:
            tmp = []
            for b in df.iloc:
                tmp.append(self.manhattan(a.values, b.values))
            m.append(tmp)
        return m
