# Laboratório -  3
# Alunos:
#     Pedro Henrique Faria Teixeira - 11621BCC025
#     João Daniel de Aquino Rufino - 11621BCC033


# 3  - Utilizando a imagem ctskull-256.tif, refaça a quantização de seus pixels utilizando de 7
#     a 1 bit(s) por pixel

import cv2
from PIL import Image
import numpy as np

img = Image.open("img/ctskull-256.tif")
img128 = np.array(img.quantize(128))
img64 = np.array(img.quantize(64))
img32 = np.array(img.quantize(32))
img16 = np.array(img.quantize(16))
img8 = np.array(img.quantize(8))
img4 = np.array(img.quantize(4))
img2 = np.array(img.quantize(2))

finalr = cv2.hconcat([img128, img64, img32])
finalv = cv2.hconcat([img16, img4, img2])

final = cv2.vconcat([finalr, finalv])

cv2.imshow('quantize', final)
cv2.waitKey(0)
