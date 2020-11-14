# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 05 - Vídeo>
# 2. Construa um programa que faça a leitura de um outro arquivo .avi e mostre a estrutura
# do primeiro frame e salve a informação da imagem desse frame em HSI

import matplotlib.pyplot as plt
import cv2
import numpy as np
import math


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


def RGB_TO_HSI(img):

    with np.errstate(divide='ignore', invalid='ignore'):

        bgr = np.float32(img)/255

        blue = bgr[:, :, 0]
        green = bgr[:, :, 1]
        red = bgr[:, :, 2]

        def calc_intensity(red, blue, green):
            return np.divide(blue + green + red, 3)

        def calc_saturation(red, blue, green):
            minimum = np.minimum(np.minimum(red, green), blue)
            saturation = 1 - (3 / (red + green + blue + 0.001) * minimum)

            return saturation

        def calc_hue(red, blue, green):
            hue = np.copy(red)

            for i in range(0, blue.shape[0]):
                for j in range(0, blue.shape[1]):
                    hue[i][j] = 0.5 * ((red[i][j] - green[i][j]) + (red[i][j] - blue[i][j])) / \
                        math.sqrt((red[i][j] - green[i][j])**2 +
                                  ((red[i][j] - blue[i][j]) * (green[i][j] - blue[i][j])))
                    hue[i][j] = math.acos(hue[i][j])

                    if blue[i][j] <= green[i][j]:
                        hue[i][j] = hue[i][j]
                    else:
                        hue[i][j] = ((360 * math.pi) / 180.0) - hue[i][j]

            return hue

        hsi = cv2.merge((calc_hue(red, blue, green), calc_saturation(
            red, blue, green), calc_intensity(red, blue, green)))
        return hsi


def histogramHSI(img):
    lu1 = img[..., 0].flatten()
    plt.subplot(1, 3, 1)
    plt.hist(lu1*360, bins=360, range=(0.0, 360.0),
             histtype='stepfilled', color='r', label='Hue')
    plt.title("Hue")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()

    lu2 = img[..., 1].flatten()
    plt.subplot(1, 3, 2)
    plt.hist(lu2, bins=100, range=(0.0, 1.0),
             histtype='stepfilled', color='g', label='Saturation')
    plt.title("Saturation")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()

    lu3 = img[..., 2].flatten()
    plt.subplot(1, 3, 3)
    plt.hist(lu3*255, bins=256, range=(0.0, 255.0),
             histtype='stepfilled', color='b', label='Intesity')
    plt.title("Intensity")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()
    plt.show()


cap = cv2.VideoCapture('videos/bird.avi')
ret, frame = cap.read()
histogramRGB(frame)
hsi = RGB_TO_HSI(frame)
histogramHSI(hsi)

with open('videos/hsi.txt', 'w') as file:
    file.write(str(hsi))
