import matplotlib.pyplot as plt
import statistics as st

f = open("supermercado.txt", "r")

data = []
for x in f.read().split():
  data.append(int(x))

print("Media com outlier: ", st.mean(data))
print("Mediana com outlier: ", st.median(data))
print("Variancia com outlier: ", st.variance(data))

# Removendo o outlier
data.remove(728)

print("\nMedia sem outlier: ", st.mean(data))
print("Mediana sem outlier: ", st.median(data))
print("Variancia sem outlier: ", st.variance(data))

plt.boxplot(data)

plt.show()