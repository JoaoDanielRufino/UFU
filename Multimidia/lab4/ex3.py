# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 04 - Imagem – Análise>

# 3 - Implemente um programa para calcular o histograma de cores para um conjunto de três
# imagens RGB. Esse código deve ser implementado sem usar as funções já disponíveis em
# bibliotecas da linguagem.
import cv2
import matplotlib.pyplot as plt
import numpy as np


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
    plt.subplot(1, 3, 1)
    plt.plot(histr, color='b')
    plt.xlim([0, 256])

    histr = calculateHistogram(img[:, :, 1])
    plt.subplot(1, 3, 2)
    plt.plot(histr, color='g')
    plt.xlim([0, 256])

    histr = calculateHistogram(img[:, :, 2])
    plt.subplot(1, 3, 3)
    plt.plot(histr, color='r')
    plt.xlim([0, 256])

    plt.show()


img = cv2.imread('threshold/1.jpeg')
img2 = cv2.imread('threshold/boat.tiff')
img3 = cv2.imread('textura/texture3.tiff')

histogramRGB(img)
histogramRGB(img2)
histogramRGB(img3)
