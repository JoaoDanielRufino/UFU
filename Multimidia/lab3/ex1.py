# Laboratório -  3
# Alunos:
#     Pedro Henrique Faria Teixeira - 11621BCC025
#     João Daniel de Aquino Rufino - 11621BCC033


# 1 - Desenvolva um programa que permita ler uma imagem (em níveis de cinza) e mostrar o
# bitplane para os 8 bits dos níveis de cinza. Lembre-se que a imagem a ser analisada deve
# estar em níveis de cinza com apenas um canal de cor.
import cv2
import numpy as np

img = cv2.imread('img/e16.jpg', 0)

lst = []
for i in range(img.shape[0]):
    for j in range(img.shape[1]):
        lst.append(np.binary_repr(img[i][j], width=8))

eight_bit_img = (np.array([int(i[0]) for i in lst],
                          dtype=np.uint8) * 128).reshape(img.shape[0], img.shape[1])
seven_bit_img = (np.array([int(i[1]) for i in lst],
                          dtype=np.uint8) * 64).reshape(img.shape[0], img.shape[1])
six_bit_img = (np.array([int(i[2]) for i in lst], dtype=np.uint8)
               * 32).reshape(img.shape[0], img.shape[1])
five_bit_img = (np.array([int(i[3]) for i in lst],
                         dtype=np.uint8) * 16).reshape(img.shape[0], img.shape[1])
four_bit_img = (np.array([int(i[4]) for i in lst],
                         dtype=np.uint8) * 8).reshape(img.shape[0], img.shape[1])
three_bit_img = (np.array([int(i[5]) for i in lst],
                          dtype=np.uint8) * 4).reshape(img.shape[0], img.shape[1])
two_bit_img = (np.array([int(i[6]) for i in lst],
                        dtype=np.uint8) * 2).reshape(img.shape[0], img.shape[1])
one_bit_img = (np.array([int(i[7]) for i in lst],
                        dtype=np.uint8) * 1).reshape(img.shape[0], img.shape[1])

finalr = cv2.hconcat([eight_bit_img, seven_bit_img, six_bit_img, five_bit_img])
finalv = cv2.hconcat([four_bit_img, three_bit_img, two_bit_img, one_bit_img])

final = cv2.vconcat([finalr, finalv])

cv2.imshow('bit plane', final)
cv2.waitKey(0)
