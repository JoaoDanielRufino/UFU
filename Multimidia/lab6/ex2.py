# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 06 - Compressão - Parte 1>

# 2. Construa um programa que receba uma string de dados e realize a codificação Run
# Length. Em seguida, use uma imagem binária e análise novamente esse algoritmo Run
# Length sobre a imagem.

import cv2
import collections

from collections import OrderedDict


def runLengthEncoding(input):
    dict = OrderedDict.fromkeys(input, 0)
    for ch in input:
        dict[ch] += 1
    output = ''
    for key, value in dict.items():
        output = output + key + str(value)
    return output


def runLengthEncodingImage(image):
    w, h, _ = image.shape

    compressed = []

    for i in range(w):
        prev = image[i][0]
        count = 0
        if "aux" in locals():
            compressed.append(aux)
        aux = []
        for j in range(h):
            if (image[i][j] == prev).all():
                count += 1
            else:
                aux.append(count)
                count = 1
                prev = image[i][j]
            if j == h-1:
                aux.append(count)

    return compressed


string = "string test"

print('Compressed string:', runLengthEncoding(string))

img = cv2.imread('img/boat.tiff')
compressed = runLengthEncodingImage(img)
print('Compressed image:', compressed)
