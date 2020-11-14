# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 07 - Compressão - Parte 2>

# 2. Esse exercício explora a transformada discreta de cosseno:
# a) Implemente um programa que permita ler imagens e recorte parte dessas imagens com
# tamanho 8 x 8 e calcule a transformada discreta de cosseno (Discrete Cosine Transform -
# DCT).

import numpy as np
import cv2
from scipy.fftpack import dct, idct
import matplotlib.pyplot as plt


def dct2(a):
    return dct(dct(a.T, norm='ortho').T, norm='ortho')


def idct2(a):
    return idct(idct(a.T, norm='ortho').T, norm='ortho')


def cut_img(path, size_r, size_c):
    test_image = cv2.imread(path, 0)

    windowsize_r = size_r
    windowsize_c = size_c

    for r in range(0, test_image.shape[0] - windowsize_r, windowsize_r):
        for c in range(0, test_image.shape[1] - windowsize_c, windowsize_c):
            window = test_image[r:r+windowsize_r, c:c+windowsize_c]

    return window


window = cut_img('img/1.jpeg', 8, 8)
dct_8 = dct2(window)
cv2.imshow('DCT', dct_8)
cv2.waitKey(0)


# b) Implemente a transformada inversa de cosseno (Inverse Discrete Cosine Transform - IDCT)
# sobre os resultados do item a) e mostre o resultado do processo.

idct_8 = idct2(dct_8)
cv2.imshow('IDCT', idct_8)
cv2.waitKey(0)


cv2.destroyAllWindows()

# c) Empregue a métrica mean square error (MSE) para avaliar as degradações (erro) ou não
# na imagem com uso da transformada entre o processo da transformada e operação
# inversa.


def mse(imageA, imageB):
    return np.nanmean(np.square(np.subtract(imageA, imageB)))


print("MSE: ", mse(dct_8, idct_8))

# d) Faça a implementação da DCT e IDCT em imagens de tamanhos diferentes (8 x 8, 16 x
# 16, etc) e analise as degradações em relação às modificações.

window_8 = cut_img('img/1.jpeg', 8, 8)
dct_boat = dct2(window_8)
cv2.imshow('DCT-8x8', dct_boat)
cv2.waitKey(0)

idct_boat = idct2(dct_boat)
cv2.imshow('IDCT-8x8', idct_boat)
cv2.waitKey(0)

window_16 = cut_img('img/1.jpeg', 16, 16)
dct_16 = dct2(window_16)
cv2.imshow('DCT-16x16', dct_16)
cv2.waitKey(0)

idct_16 = idct2(dct_16)
cv2.imshow('IDCT-16x16', idct_16)
cv2.waitKey(0)

window_32 = cut_img('img/1.jpeg', 32, 32)
dct_32 = dct2(window_32)
cv2.imshow('DCT-32x32', dct_32)
cv2.waitKey(0)

idct_32 = idct2(dct_32)
cv2.imshow('IDCT-32x32', idct_32)
cv2.waitKey(0)
