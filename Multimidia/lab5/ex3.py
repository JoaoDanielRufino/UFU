# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 05 - Vídeo>
# 3. Construa um programa que leia, reproduza o vídeo e, posteriormente, execute apenas
# os frames 1–10. Mostre o tempo necessário para execução desses frames e a taxa de
# quadros por segundo. Faça uma modificação para que a exibição seja com uma taxa de
# quadros por segundo diferente do padrão definido neste vídeo.
import os
import cv2
import time

start = time.time()

cap = cv2.VideoCapture('videos/cbw3.avi')

while(cap.isOpened()):
    ret, frame = cap.read()

    if ret == True:
        cv2.imshow('full video', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break

end = time.time()
print('Execution time full video: ', end-start)
print('Frame rate: ', cap.get(cv2.CAP_PROP_FPS))

start = time.time()
cap = cv2.VideoCapture('videos/cbw3.avi')
count = 0
while(cap.isOpened()):
    ret, frame = cap.read()
    if ret == True:
        cv2.imshow('10 frame video', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break
    if count >= 10:
        break
    count += 1

end = time.time()
print("\nExecution time 10 frame video: ", end-start)
print("Frame rate: ", cap.get(cv2.CAP_PROP_FPS))


print("\nChanging frame rate to 10")
cap = cv2.VideoCapture('videos/cbw3.avi')
cap.set(cv2.CAP_PROP_FPS, int(60))
while(cap.isOpened()):
    ret, frame = cap.read()

    if ret == True:
        cv2.imshow('full video', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break
