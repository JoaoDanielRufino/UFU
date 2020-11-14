# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 04 - Imagem – Análise>

# 2 -  Implemente um programa para segmentação de imagens baseado em uma limiarização
# adaptativa local com o cálculo da média para obtenção do valor do limiar para separação
# dos objetos. Selecione novamente as imagens empregadas na questão 1 e avalie os
# resultados em relação ao método anterior. Faça uma discussão sobre as característica
# desse método. Esse código deve ser implementado sem usar as funções já disponíveis em
# bibliotecas da linguagem.

from skimage.color import rgb2gray
import matplotlib.pyplot as plt
import cv2
import numpy as np


def segmentation(img):
    gray_r = img.reshape(img.shape[0]*img.shape[1])

    for i in range(gray_r.shape[0]):
        if gray_r[i] > gray_r.mean():
            gray_r[i] = 3
        elif gray_r[i] > 0.5:
            gray_r[i] = 2
        elif gray_r[i] > 0.25:
            gray_r[i] = 1
        else:
            gray_r[i] = 0

    return gray_r.reshape(img.shape[0], img.shape[1])


pic = plt.imread('threshold/1.jpeg')
gray = rgb2gray(pic)
segmented_img = segmentation(gray)

plt.imshow(segmented_img, cmap='gray')
plt.show()

# Observa-se que a imagem foi tonallizada com 4 tons de cinzas.
# Agrupando os grupos de cores em grupos de cinzas, por exemplo o verde foi serparado em
# um tom de cinza médio, em quanto o marron foi separado em um tom de cinza claro.
