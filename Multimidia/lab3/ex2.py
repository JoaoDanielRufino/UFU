# Laboratório -  3
# Alunos:
#     Pedro Henrique Faria Teixeira - 11621BCC025
#     João Daniel de Aquino Rufino - 11621BCC033

# 2-a) Elabore um programa que possa ler três imagens em tons de cinza e calcule a
# matização (Dithering) ordenado de cada uma dessas imagens. Salve as imagens e
# mostre o resultado dessa operação.

import cv2
import numpy as np
from math import floor
from PIL import Image


def set_pixel(im, x, y, new):
    im[x, y] = new


def quantize(im, width, height, w1, w2, w3, w4):
    for y in range(0, height-1):
        for x in range(1, width-1):
            old_pixel = im[x, y]
            if old_pixel < 127:
                new_pixel = 0
            else:
                new_pixel = 255
            set_pixel(im, x, y, new_pixel)
            quant_err = old_pixel-new_pixel
            set_pixel(im, x+1, y, im[x+1, y]+quant_err*w1)
            set_pixel(im, x-1, y+1, im[x-1, y+1] + quant_err*w2)
            set_pixel(im, x, y+1, im[x, y+1] + quant_err * w3)
            set_pixel(im, x+1, y+1, im[x+1, y+1] + quant_err * w4)

    return im


def dithering_gray(img_path, output):
    img = cv2.imread(img_path)
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    img2 = img.copy()
    width, height, z = img.shape
    w1 = 7/16.0
    w2 = 3/16.0
    w3 = 5/16.0
    w4 = 1/16.0
    blue = img[:, :, 0]
    blue = quantize(blue, width, height, w1, w2, w3, w4)
    green = img[:, :, 1]
    green = quantize(green, width, height, w1, w2, w3, w4)
    red = img[:, :, 2]
    red = quantize(red, width, height, w1, w2, w3, w4)
    gray1 = quantize(gray, width, height, w1, w2, w3, w4)

    cv2.imwrite(output, gray1)


# Dithering images to gray
print("Dithering images gray...")
dithering_gray("img/h1.jpg", "img/dithered-h1.jpg")

dithering_gray("img/e16.jpg", "img/dithered-e16.jpg")

dithering_gray("img/h2.jpg", "img/dithered-h2.jpg")
print("Dithering completed")

# 2 - b)Construa um código para elaboração da matriz de matização ordenada sobre
# imagens coloridas em RGB e salve o resultados da operação.


def dithering_rgb(img_path, output):
    img = cv2.imread(img_path)
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    img2 = img.copy()
    width, height, z = img.shape
    w1 = 7/16.0
    w2 = 3/16.0
    w3 = 5/16.0
    w4 = 1/16.0
    blue = img[:, :, 0]
    blue = quantize(blue, width, height, w1, w2, w3, w4)
    green = img[:, :, 1]
    green = quantize(green, width, height, w1, w2, w3, w4)
    red = img[:, :, 2]
    red = quantize(red, width, height, w1, w2, w3, w4)
    gray1 = quantize(gray, width, height, w1, w2, w3, w4)

    image = cv2.merge((blue, green, red))
    cv2.imwrite(output, image)


# Dithering images to rgb
print("Dithering images rgb...")
dithering_rgb("img/h1.jpg", "img/dithered-rgb-h1.jpg")

dithering_rgb("img/e16.jpg", "img/dithered-rgb-e16.jpg")

dithering_rgb("img/h2.jpg", "img/dithered-rgb-h2.jpg")
print("Dithering completed")


# 2- c) Implemente o algoritmo Floyd–Steinberg
# (https://en.wikipedia.org/wiki/Floyd%E2%80%93Steinberg_dithering) para imagens
# coloridas e salve o resultado após a operação da matização de Floyd–Steinberg.

def apply_threshold(value):
    return 255 * floor(value/128)


def floyd_steinberg_dither(image_file):
    new_img = Image.open(image_file)

    new_img = new_img.convert('RGB')
    pixel = new_img.load()

    x_lim, y_lim = new_img.size

    for y in range(1, y_lim):
        for x in range(1, x_lim):
            red_oldpixel, green_oldpixel, blue_oldpixel = pixel[x, y]

            red_newpixel = apply_threshold(red_oldpixel)
            green_newpixel = apply_threshold(green_oldpixel)
            blue_newpixel = apply_threshold(blue_oldpixel)

            pixel[x, y] = red_newpixel, green_newpixel, blue_newpixel

            red_error = red_oldpixel - red_newpixel
            blue_error = blue_oldpixel - blue_newpixel
            green_error = green_oldpixel - green_newpixel

            if x < x_lim - 1:
                red = pixel[x+1, y][0] + round(red_error * 7/16)
                green = pixel[x+1, y][1] + round(green_error * 7/16)
                blue = pixel[x+1, y][2] + round(blue_error * 7/16)

                pixel[x+1, y] = (red, green, blue)

            if x > 1 and y < y_lim - 1:
                red = pixel[x-1, y+1][0] + round(red_error * 3/16)
                green = pixel[x-1, y+1][1] + round(green_error * 3/16)
                blue = pixel[x-1, y+1][2] + round(blue_error * 3/16)

                pixel[x-1, y+1] = (red, green, blue)

            if y < y_lim - 1:
                red = pixel[x, y+1][0] + round(red_error * 5/16)
                green = pixel[x, y+1][1] + round(green_error * 5/16)
                blue = pixel[x, y+1][2] + round(blue_error * 5/16)

                pixel[x, y+1] = (red, green, blue)

            if x < x_lim - 1 and y < y_lim - 1:
                red = pixel[x+1, y+1][0] + round(red_error * 1/16)
                green = pixel[x+1, y+1][1] + round(green_error * 1/16)
                blue = pixel[x+1, y+1][2] + round(blue_error * 1/16)

                pixel[x+1, y+1] = (red, green, blue)

    return new_img


print("Floyd dithering....")
img = floyd_steinberg_dither("img/e16.jpg")
img.save("img/floyd-dither.jpg")
print("Finished floyd dithering")
