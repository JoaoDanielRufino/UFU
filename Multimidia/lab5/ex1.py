# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 05 - Vídeo>
# 1. Crie um programa que faça a leitura de um arquivo do padrão .avi e mostre se o arquivo
# tem compressão e o número de frames deste arquivo. O arquivo .avi deve ser gerado pelos
# estudantes.
# a) Em seguida, carregue um outro arquivo de vídeo e faça a verificação dessas
# informações.

# Para instalar e rodar o comando é necessário acessar esse link.
# https://hachoir.readthedocs.io/en/latest/install.html

import cv2
import os

cap = cv2.VideoCapture('videos/cbw3.avi')

while(cap.isOpened()):
    ret, frame = cap.read()

    if ret == True:
        cv2.imshow('frame', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break


cap.release()
cv2.destroyAllWindows()

print("bird.avi: ")
os.system("hachoir-metadata videos/bird.avi")
# bird.avi:
# Common:
# - Duration: 11 sec 833 ms
# - Image width: 256 pixels
# - Image height: 240 pixels
# - Frame rate: 30.0 fps
# - Bit rate: 1.0 Mbit/sec
# - Comment: Has audio/video index (5688 bytes)
# - MIME type: video/x-msvideo
# - Endianness: Little endian
# Video stream:
# - Duration: 11 sec 833 ms
# - Image width: 256 pixels
# - Image height: 240 pixels
# - Bits/pixel: 24
# - Compression: Radius Cinepak (fourcc:"cvid")
# - Frame rate: 30.0 fps

print("\ncbw3.avi: ")
os.system("hachoir-metadata videos/cbw3.avi")
# cbw3.avi:
# Common:
# - Duration: 6 sec 250 ms
# - Image width: 256 pixels
# - Image height: 240 pixels
# - Frame rate: 40.0 fps
# - Bit rate: 1.1 Mbit/sec
# - Comment: Has audio/video index (4008 bytes)
# - MIME type: video/x-msvideo
# - Endianness: Little endian
# Video stream:
# - Duration: 6 sec 250 ms
# - Image width: 256 pixels
# - Image height: 240 pixels
# - Bits/pixel: 24
# - Compression: Radius Cinepak (fourcc:"cvid")
# - Frame rate: 40.0 fps


# b) Há diferença em relação aos dados desses arquivos? Justifique o que ocorre neste
# processo (informações dos arquivos).
# R: No bird, o frame rate é de 30fps e o bit date é de 1.0 Mbit/sec, com duração de 11 sec 833 ms,
# com tamanho de (5688 bytes)
# No cbw3, o frame rate é de 40fps e o bit rate é de 1.1 Mbit/sec, com duração de 6 sec 250 ms,
# com tamanho de (4008 bytes)


# c) Qual é o tamanho da estrutura dos arquivos de vídeo?
# R: Bird = 5688 bytes
#    Cbw3 = 4008 bytes
