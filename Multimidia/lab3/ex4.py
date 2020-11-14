# Laboratório -  3
# Alunos:
#     Pedro Henrique Faria Teixeira - 11621BCC025
#     João Daniel de Aquino Rufino - 11621BCC033


# 4. Faça um programa que leia 3 imagens coloridas e realize a separação dos cores dos
# canais RGB. Então, faça a conversa para o modelo de cores HSI e construa os gráficos
# dos histogramas de cores após a conversão para esse modelo de cores. No caso das
# imagens do canal i aplique uma equalização de histograma e retorne os canais para
# representação da imagem em HSI e calcule novamente os histogramas das imagens
# modificadas. Finalmente, faça a reconstrução para o modelo de cores RGB e aplique
# medidas para analisar as modificações sobre as imagens originais e após todos os
# processos (neste caso, implemente a métrica: mean square error (MSE) -
# https://homepages.inf.ed.ac.uk/rbf/CVonline/LOCAL_COPIES/VELDHUIZEN/node18.html.


import cv2
import numpy as np
import math
from PIL import Image
from matplotlib import pyplot as plt
import matplotlib.image as mpimg


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
    lu1 = imgHSI[..., 0].flatten()
    plt.subplot(1, 3, 1)
    plt.hist(lu1*360, bins=360, range=(0.0, 360.0),
             histtype='stepfilled', color='r', label='Hue')
    plt.title("Hue")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()

    lu2 = imgHSI[..., 1].flatten()
    plt.subplot(1, 3, 2)
    plt.hist(lu2, bins=100, range=(0.0, 1.0),
             histtype='stepfilled', color='g', label='Saturation')
    plt.title("Saturation")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()

    lu3 = imgHSI[..., 2].flatten()
    plt.subplot(1, 3, 3)
    plt.hist(lu3*255, bins=256, range=(0.0, 255.0),
             histtype='stepfilled', color='b', label='Intesity')
    plt.title("Intensity")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()
    plt.show()


def equalizeIntensity(imgHSI):
    imgHSI[:, :, 2], _ = histeq(imgHSI[:, :, 2])

    lu3 = imgHSI[..., 2].flatten()

    plt.hist(lu3*255, bins=256, range=(0.0, 255.0),
             histtype='stepfilled', color='b', label='Intesity')
    plt.title("Intensity")
    plt.xlabel("Value")
    plt.ylabel("Frequency")
    plt.legend()
    plt.show()

    return imgHSI


def histeq(im, nbr_bins=256):
    imhist, bins = np.histogram(im.flatten(), nbr_bins)
    cdf = imhist.cumsum()
    cdf = 1 * cdf / cdf[-1]

    im2 = np.interp(im.flatten(), bins[:-1], cdf)

    return im2.reshape(im.shape), cdf


def mse(imageA, imageB):
    return np.nanmean(np.square(np.subtract(imageA, imageB)))


def compare_images(imageA, imageB, title):
    m = mse(imageA, imageB)

    fig = plt.figure(title)
    plt.suptitle("MSE: %.2f" % (m))

    ax = fig.add_subplot(1, 2, 1)
    plt.imshow(imageA)
    plt.axis("off")

    ax = fig.add_subplot(1, 2, 2)
    plt.imshow(imageB)
    plt.axis("off")

    plt.show()


img = cv2.imread("img/h1.jpg")
imgHSI = RGB_TO_HSI(img)
histogramHSI(imgHSI)

imgHSI = equalizeIntensity(imgHSI)

img = mpimg.imread("img/h1.jpg")
imgRGB = cv2.cvtColor(imgHSI, cv2.COLOR_HSV2RGB)
compare_images(img, imgRGB, "comparation")


img = cv2.imread("img/e16.jpg")
imgHSI = RGB_TO_HSI(img)
histogramHSI(imgHSI)

imgHSI = equalizeIntensity(imgHSI)

img = mpimg.imread("img/e16.jpg")
imgRGB = cv2.cvtColor(imgHSI, cv2.COLOR_HSV2RGB)
compare_images(img, imgRGB, "comparation")

img = cv2.imread("img/d1.jpg")
imgHSI = RGB_TO_HSI(img)
histogramHSI(imgHSI)

imgHSI = equalizeIntensity(imgHSI)

img = mpimg.imread("img/d1.jpg")
imgRGB = cv2.cvtColor(imgHSI, cv2.COLOR_HSV2RGB)
compare_images(img, imgRGB, "comparation")
