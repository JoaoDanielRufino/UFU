# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 04 - Imagem – Análise>
# 4. Implemente um algoritmo para leitura de um grupo de 3 imagens e apresente (calcule,
# sem usar funções prontas):

# a) Histograma normalizado

import matplotlib.pyplot as plt
import numpy as np
import cv2

import numpy as np
import matplotlib.pyplot as plt


def normalize(x):
    arr = []
    for i in x.flatten():
        i = i/255.0
        arr.append(i)

    return np.float32(arr)


def calculateHistogram(img):
    h, w = img.shape
    hist = np.zeros(256)

    for i in range(h):
        for j in range(w):
            pos = img[i][j]
            hist[pos] += 1

    return hist


def histogramRGB(img):
    histr = calculateHistogram(img[:, :, 0])
    histr = normalize(histr)
    plt.subplot(1, 3, 1)
    plt.plot(histr, color='b')
    plt.xlim([0, 256])

    histr = calculateHistogram(img[:, :, 1])
    histr = normalize(histr)
    plt.subplot(1, 3, 2)
    plt.plot(histr, color='g')
    plt.xlim([0, 256])

    histr = calculateHistogram(img[:, :, 2])
    histr = normalize(histr)
    plt.subplot(1, 3, 3)
    plt.plot(histr, color='r')
    plt.xlim([0, 256])

    plt.show()


img = cv2.imread('threshold/1.jpeg')
img2 = cv2.imread('threshold/boat.tiff')
img3 = cv2.imread('textura/texture3.tiff')

print("Histogramas normalizados")
histogramRGB(img)
histogramRGB(img2)
histogramRGB(img3)


# b) Histograma acumulado

def cumulativeHistogram(img):
    mu = 200
    sigma = 25
    n_bins = 50

    fig, ax = plt.subplots(figsize=(8, 4))

    n, bins, patches = ax.hist(img, n_bins, density=True, histtype='step',
                               cumulative=True, label='Empirical')

    y = ((1 / (np.sqrt(2 * np.pi) * sigma)) *
         np.exp(-0.5 * (1 / sigma * (bins - mu))**2))
    y = y.cumsum()
    y /= y[-1]

    ax.plot(bins, y, 'k--', linewidth=1.5, label='Theoretical')

    ax.hist(img, bins=bins, density=True, histtype='step', cumulative=-1,
            label='Reversed emp.')

    ax.grid(True)
    ax.legend(loc='right')
    ax.set_title('Cumulative step histograms')

    plt.show()


img = cv2.imread('threshold/1.jpeg', 0)
img2 = cv2.imread('threshold/boat.tiff', 0)
img3 = cv2.imread('textura/texture3.tiff', 0)

print("Histogramas acumulados")
cumulativeHistogram(img)
cumulativeHistogram(img2)
cumulativeHistogram(img3)
