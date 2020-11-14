# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 05 - Vídeo>
# 4. Construa um programa que realize as seguintes operações em um arquivo de vídeo da
# sua escolha (comente os resultados obtidos):
# a) Retire alguns frames do vídeo e execute o vídeo;

import cv2
import numpy as np
import os

cap = cv2.VideoCapture('videos/video-sample.avi')
frame_width = int(cap.get(3))
frame_height = int(cap.get(4))
size = (frame_width, frame_height)

out = cv2.VideoWriter('videos/video-sample-skipped-frames.avi',
                      cv2.VideoWriter_fourcc(*'HFYU'), 30, size)
out_compress = cv2.VideoWriter('videos/video-sample-skipped-frames-compressed.avi',
                               cv2.VideoWriter_fourcc(*'LMP2'),
                               60, size)

start_frame_number = 50
cap.set(cv2.CAP_PROP_POS_FRAMES, start_frame_number)

# Lendo frame a patir do frame 50
while(cap.isOpened()):
    ret, frame = cap.read()

    if ret == True:
        cv2.imshow('Starting at frame 50', frame)

        out.write(frame)
        out_compress.write(frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break

cap.release()
out.release()
out_compress.release()

# b) Aplique um filtro de ajuste de cor em alguns frames e faça a inserção e reprodução;
cap2 = cv2.VideoCapture('videos/video-sample.avi')
frame_width = int(cap2.get(3))
frame_height = int(cap2.get(4))

size = (frame_width, frame_height)

out = cv2.VideoWriter('videos/video-sample-color-filter.avi',
                      cv2.VideoWriter_fourcc(*'HFYU'), 30, size)
out_compress = cv2.VideoWriter('videos/video-sample-color-filter-compressed.avi',
                               cv2.VideoWriter_fourcc(*'LMP2'),
                               60, size)
frame_number = 0
while(cap2.isOpened()):
    frame_number += 1
    ret, frame = cap2.read()

    if ret == True:
        if frame_number % 90:  # sempre que for multiplo de 90 muda o frame para somente cinza
            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        cv2.imshow('frame', frame)
        out.write(frame)
        out_compress.write(frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break

cap2.release()
out.release()
out_compress.release()
cv2.destroyAllWindows()


# c) Salve esses arquivos (vídeos) modificados de duas formas: sem compressão e com
# compressão (MPEG2). O tamanho dos arquivos são diferentes?
a = os.path.getsize("videos/video-sample-skipped-frames-compressed.avi")
b = os.path.getsize("videos/video-sample-skipped-frames.avi")
print('Compressed file(skipped frames): ', a)
print('Lossless file(skipped frames): ', b)

a = os.path.getsize("videos/video-sample-color-filter-compressed.avi")
b = os.path.getsize("videos/video-sample-color-filter.avi")
print('\nCompressed file(color filtered): ', a)
print('Lossless file(color filtered): ', b)

# Sim, o tamanho dos arquivos são diferentes, o comprimido é menor como já se esperava.
