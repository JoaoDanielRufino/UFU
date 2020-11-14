# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 04 - Imagem – Análise>

# 1 - Desenvolva um programa que calcule a limiarização global manual de imagens em
# níveis de cinza. Defina três imagens e avalie o emprego de um limiar manual. Mostre os
# resultados após aplicação dessa limiarização. Faça a mesma operação em relação ao
# valor do limiar, mas aplique isso com uma limiarização local. Análise os resultados e
# discuta os pontos positivos e negativos desse método. Esse código deve ser implementado
# sem usar as funções já disponíveis em bibliotecas da linguagem.

from PIL import Image
import cv2
from scipy import ndimage
import numpy as np


def global_threshold(img, thresholdValue, maxVal):
    for i in range(len(img)):
        for j in range(len(img[i])):
            if img[i][j] > thresholdValue:
                img[i][j] = maxVal
            else:
                img[i][j] = 0
    return img


def local_threshold(img, maxVal, constant=5):
    average_neighborhood = ndimage.generic_filter(
        img, np.nanmean, size=3, mode='constant', cval=np.NaN)  # faz as médias dos vizinhos na matriz

    for i in range(len(img)):
        for j in range(len(img[i])):
            if img[i][j] > average_neighborhood[i][j] - constant:
                img[i][j] = maxVal
            else:
                img[i][j] = 0
    return img


print("Global treshold of boat.tiff")
img_1 = cv2.imread("threshold/boat.tiff", 0)
global_threshold_img = global_threshold(img_1, 127, 255)

print("Local treshold of boat.tiff")
img_1 = cv2.imread("threshold/boat.tiff", 0)
local_threshold_img = local_threshold(img_1, 255)

im_h_1 = cv2.hconcat([global_threshold_img, local_threshold_img])

print("Global treshold of relogio300.tif")
img_1 = cv2.imread("threshold/relogio300.tif", 0)
global_threshold_img = global_threshold(img_1, 127, 255)

print("Local treshold of relogio300.tif")
img_1 = cv2.imread("threshold/relogio300.tif", 0)
local_threshold_img = local_threshold(img_1, 255)

im_h_2 = cv2.hconcat([global_threshold_img, local_threshold_img])

print("Global treshold of lena.tiff")
img_1 = cv2.imread("threshold/lena.tiff", 0)
global_threshold_img = global_threshold(img_1, 127, 255)

print("Local treshold of lena.tiff")
img_1 = cv2.imread("threshold/lena.tiff", 0)
local_threshold_img = local_threshold(img_1, 255)

im_h_3 = cv2.hconcat([global_threshold_img, local_threshold_img])


cv2.imshow("treshold img", im_h_1)
cv2.waitKey(0)

cv2.imshow("treshold img", im_h_2)
cv2.waitKey(0)

cv2.imshow("treshold img", im_h_3)
cv2.waitKey(0)

# Discussão: Ao contrário da técnica de limiarização global, o limiar adaptativo local
# escolhe diferentes valores de limiar para cada pixel da imagem com base em uma análise de
# seus pixels vizinhos. Isso permite imagens com níveis de contraste variados,
# onde uma técnica de limiar global não funcionará satisfatoriamente.
