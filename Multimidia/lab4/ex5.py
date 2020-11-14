# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 04 - Imagem – Análise>

# 5. Desenvolva um programa que leia quatro imagens e calcule a matriz de coocorrência
# numa representação de 256 níveis de brilho.
# a) Calcule a matriz nas orientações de 0, 45, 90 e 135 graus com d =1, d = 2, d = 3 e d = 4.
# Calcule a média e desvio padrão das matrizes e faça uma análise comparativa do
# comportamento dos dados com as variações do parâmetro d.
import cv2
import numpy as np
from skimage.feature import greycomatrix, greycoprops

img = cv2.imread('threshold/1.jpeg', 0)

glcm1 = greycomatrix(img, distances=[1], angles=[0, 45, 90, 135], levels=256,
                     symmetric=True, normed=True)
mean1 = np.mean(glcm1)
std1 = np.std(glcm1)

glcm2 = greycomatrix(img, distances=[2], angles=[0, 45, 90, 135], levels=256,
                     symmetric=True, normed=True)
mean2 = np.mean(glcm2)
std2 = np.std(glcm2)

glcm3 = greycomatrix(img, distances=[3], angles=[0, 45, 90, 135], levels=256,
                     symmetric=True, normed=True)
mean3 = np.mean(glcm3)
std3 = np.std(glcm3)

glcm4 = greycomatrix(img, distances=[4], angles=[0, 45, 90, 135], levels=256,
                     symmetric=True, normed=True)
mean4 = np.mean(glcm4)
std4 = np.std(glcm4)

print("0 graus")
print('Media distancia 1: ', mean1)
print('Desvio padrao distancia 1: ', std1)
print('Media distancia 2: ', mean2)
print('Desvio padrao distancia 2: ', std2)
print('Media distancia 3: ', mean3)
print('Desvio padrao distancia 3: ', std3)
print('Media distancia 4: ', mean4)
print('Desvio padra distancia 4: ', std4)

# Em relação ao parâmetro D, não houve mudança na média e sim no desvio padrão, quando se é 0 graus

# b) Utilize as matrizes de coocorrência e calcule os 14 descritores propostos por Haralick

glcm = greycomatrix(img, distances=[1, 2, 3, 4], angles=[0, 45, 90, 135], levels=256,
                    symmetric=True, normed=True)

homogeneity = greycoprops(glcm, "homogeneity")
contrast = greycoprops(glcm, "contrast")
dissimilarity = greycoprops(glcm, "dissimilarity")
asm = greycoprops(glcm, "ASM")
energy = greycoprops(glcm, "energy")
correlation = greycoprops(glcm, "correlation")

print("homogeneity: ", homogeneity)
print("contrast: ", contrast)
print("dissimilarity: ", dissimilarity)
print("asm: ", asm)
print("energy: ", energy)
print("correlation: ", correlation)
