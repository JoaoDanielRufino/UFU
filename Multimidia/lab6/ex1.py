# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 06 - Compressão - Parte 1>

# 1. Baseado nos arquivos (imagem, áudio e vídeo) dos conjuntos de dados já utilizados
# em nosso curso escreva um programa que Calcule a entropia de primeira ordem de
# uma imagem e um arquivo de texto.

import numpy as np
import cv2
import math
from collections import Counter


def eta(data):
    if len(data) <= 1:
        return 0

    counts = Counter()

    for d in data:
        counts[d] += 1

    ent = 0

    probs = [float(c) / len(data) for c in counts.values()]
    for p in probs:
        if p > 0.:
            ent -= p * math.log(p, math.exp(1))

    return ent


img = cv2.imread('img/relogio300.tif', 0)

marg = np.histogramdd(np.ravel(img), bins=256)[0]/img.size
marg = list(filter(lambda p: p > 0, np.ravel(marg)))
entropy = -np.sum(np.multiply(marg, np.log2(marg)))

print('Entropia da imagem:', entropy)
with open('text/a.txt', 'r') as file:
    x = file.read()
    print('Entropia do texto:', eta(x))
