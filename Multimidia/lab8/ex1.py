# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 08 - Compressão JPEG>

import cv2
from PIL import Image
from scipy.fftpack import dct, idct
import numpy as np
import heapq
import os

# 1. Implemente um programa que aplique a transformada, quantização e a codificação para
# compressão e descompressão do padrão de compressão hibrída JPEG. O código deve
# apresentar os resultados de cada uma das etapas da técnica JPEG (modificação de
# canais de cores, subamostragem, transformada de cosseno, etc).


class HuffmanCoding:
    def __init__(self, path):
        self.path = path
        self.heap = []
        self.codes = {}
        self.reverse_mapping = {}

    class HeapNode:
        def __init__(self, char, freq):
            self.char = char
            self.freq = freq
            self.left = None
            self.right = None

        def __lt__(self, other):
            return self.freq < other.freq

        def __eq__(self, other):
            if(other == None):
                return False
            if(not isinstance(other, HeapNode)):
                return False
            return self.freq == other.freq

    def make_frequency_dict(self, text):
        frequency = {}
        for character in text:
            if not character in frequency:
                frequency[character] = 0
            frequency[character] += 1
        return frequency

    def make_heap(self, frequency):
        for key in frequency:
            node = self.HeapNode(key, frequency[key])
            heapq.heappush(self.heap, node)

    def merge_nodes(self):
        while(len(self.heap) > 1):
            node1 = heapq.heappop(self.heap)
            node2 = heapq.heappop(self.heap)

            merged = self.HeapNode(None, node1.freq + node2.freq)
            merged.left = node1
            merged.right = node2

            heapq.heappush(self.heap, merged)

    def make_codes_helper(self, root, current_code):
        if(root == None):
            return

        if(root.char != None):
            self.codes[root.char] = current_code
            self.reverse_mapping[current_code] = root.char
            return

        self.make_codes_helper(root.left, current_code + "0")
        self.make_codes_helper(root.right, current_code + "1")

    def make_codes(self):
        root = heapq.heappop(self.heap)
        current_code = ""
        self.make_codes_helper(root, current_code)

    def get_encoded_text(self, text):
        encoded_text = ""
        for character in text:
            encoded_text += self.codes[character]
        return encoded_text

    def pad_encoded_text(self, encoded_text):
        extra_padding = 8 - len(encoded_text) % 8
        for i in range(extra_padding):
            encoded_text += "0"

        padded_info = "{0:08b}".format(extra_padding)
        encoded_text = padded_info + encoded_text
        return encoded_text

    def get_byte_array(self, padded_encoded_text):
        if(len(padded_encoded_text) % 8 != 0):
            print("Texto codificado não preenchido corretamente")
            exit(0)

        b = bytearray()
        for i in range(0, len(padded_encoded_text), 8):
            byte = padded_encoded_text[i:i+8]
            b.append(int(byte, 2))
        return b

    def compress(self):
        filename, file_extension = os.path.splitext(self.path)
        output_path = filename + ".bin"

        with open(self.path, 'r+') as file, open(output_path, 'wb') as output:
            text = file.read()
            text = text.rstrip()

            frequency = self.make_frequency_dict(text)
            self.make_heap(frequency)
            self.merge_nodes()
            self.make_codes()

            encoded_text = self.get_encoded_text(text)
            padded_encoded_text = self.pad_encoded_text(encoded_text)

            b = self.get_byte_array(padded_encoded_text)
            output.write(bytes(b))

        print("Comprimido com sucesso!")
        return output_path


def dct_2(img):
    # Get 2D Cosine Transform of Image
    return dct(dct(np.asarray(img).T, norm='ortho').T, norm='ortho')


def quantize(img):
    pil_image = Image.fromarray(np.uint8(img_dct), mode=None)
    return pil_image.quantize(128)


img = Image.open("img/boat.tiff")
img_dct = dct_2(img)
cv2.imshow('DCT', img_dct)
cv2.waitKey(0)

quantized_img = quantize(img_dct)
quantized_img.show()

HuffmanCoding(quantized_img).compress()

# a) Selecione um conjunto com 3 imagens com baixas variações de intensidades e com
# regiões uniforme para investigação do desempenho. Aplique as técnicas e empregue as
# métricas “peak signal-to-noise ratio” (PSNR) e “mean square error” (MSE) para avaliar as
# modificações nestas imagens.


def mse(imageA, imageB):
    return np.nanmean(np.square(np.subtract(imageA, imageB)))


def PSNR(original, compressed):
    mse = np.mean((original - compressed) ** 2)
    if(mse == 0):
        return 100
    max_pixel = 255.0
    psnr = 20 * log10(max_pixel / sqrt(mse))
    return psnr


img1 = Image.open("img/boat.tiff")
img_dct1 = dct_2(img1)
cv2.imshow('DCT', img_dct1)
cv2.waitKey(0)

quantized_img1 = quantize(img_dct1)
quantized_img1.show()
HuffmanCoding(quantized_img1).compress()

img2 = Image.open("img/boat.tiff")
img_dct2 = dct_2(img2)
cv2.imshow('DCT', img_dct2)
cv2.waitKey(0)

quantized_img2 = quantize(img_dct2)
quantized_img2.show()
HuffmanCoding(quantized_img2).compress()

img3 = Image.open("img/boat.tiff")
img_dct3 = dct_2(img3)
cv2.imshow('DCT', img_dct3)
cv2.waitKey(0)

quantized_img3 = quantize(img_dct3)
quantized_img3.show()
HuffmanCoding(quantized_img3).compress()

print(mse(quantized_img1, quantized_img2))
print(mse(quantized_img1, quantized_img3))
print(mse(quantized_img2, quantized_img3))

print(PSNR(img1, quantized_img1))
print(PSNR(img2, quantized_img2))
print(PSNR(img3, quantized_img3))

# b) Empregue o processo sobre um conjunto de 3 imagens coloridas e faça uma análise em
# relação ao erro aplicando as métricas propostas PSNR E MSE. Comente sobre os
# resultados em cada uma das etapas do JPEG.

img1 = Image.open("img/boat.tiff")
img_dct1 = dct_2(img1)
cv2.imshow('DCT', img_dct1)
cv2.waitKey(0)

quantized_img1 = quantize(img_dct1)
quantized_img1.show()

img2 = Image.open("img/boat.tiff")
img_dct2 = dct_2(img2)
cv2.imshow('DCT', img_dct2)
cv2.waitKey(0)

quantized_img2 = quantize(img_dct2)
quantized_img2.show()

img3 = Image.open("img/boat.tiff")
img_dct3 = dct_2(img3)
cv2.imshow('DCT', img_dct3)
cv2.waitKey(0)

quantized_img3 = quantize(img_dct3)
quantized_img3.show()

print(mse(quantized_img1, quantized_img2))
print(mse(quantized_img1, quantized_img3))
print(mse(quantized_img2, quantized_img3))

print(PSNR(img1, quantized_img1))
print(PSNR(img2, quantized_img2))
print(PSNR(img3, quantized_img3))

# A Transformada Discreta de Cosseno converte uma matriz de valores altamente correlacionados,
# e com uma distribuição de probabilidade uniforme, em um conjunto de valores menos correlacionados
# e com uma distribuição de probabilidade não uniforme.
# Somente a aplicação da DCT resulta em pouca ou quase nenhuma compactação,
# pois a matriz resultante terá o mesmo tamanho que a original, e poucos coeficientes terão valor zero.
# Neste processo de compressão os elementos menos importantes da matriz de coeficientes DCT serão descartados.
# A matriz de coeficientes DCT é dividida por outra matriz , que transforma todos os elementos em uma nova matriz .
# Este processo irá acentuar as características da matriz de Coeficientes de DCT forçando todos os elementos a aproximarem-se do valor zero.
