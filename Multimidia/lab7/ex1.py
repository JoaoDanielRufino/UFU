# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 07 - Compressão - Parte 2>

# 1. Construa um programa que implemente a codificação aritmética para compressão de
# dados.
# a) Use pelo menos 3 fontes de dados (texto ou imagem) de entrada para testar sua
# implementação nestes algoritmos.


import sys
import random
import string

import decimal
from decimal import Decimal

decimal.getcontext().prec = 100


def encode(encode_str, N):
    # tabela de probabilidade
    count = dict.fromkeys(string.ascii_lowercase, 1)
    cdf_range = dict.fromkeys(string.ascii_lowercase, 0)
    pdf = dict.fromkeys(string.ascii_lowercase, 0)

    low = 0
    high = Decimal(1)/Decimal(26)

    for key, value in sorted(cdf_range.items()):
        cdf_range[key] = [low, high]
        low = high
        high += Decimal(1)/Decimal(26)

    for key, value in sorted(pdf.items()):
        pdf[key] = Decimal(1)/Decimal(26)

    i = 26

    lower_bound = 0
    upper_bound = 1

    u = 0

    # percorre todos os simbolos na string
    for sym in encode_str:
        i += 1
        u += 1
        count[sym] += 1

        # current range
        curr_range = upper_bound - lower_bound
        upper_bound = lower_bound + \
            (curr_range * cdf_range[sym][1])
        lower_bound = lower_bound + \
            (curr_range * cdf_range[sym][0])

        # atualiza cdf_range dps que os N sibolos foram lidos
        if (u == N):
            u = 0

            for key, value in sorted(pdf.items()):
                pdf[key] = Decimal(count[key])/Decimal(i)

            low = 0
            for key, value in sorted(cdf_range.items()):
                high = pdf[key] + low
                cdf_range[key] = [low, high]
                low = high

    return lower_bound


def decode(encoded, strlen, every):
    decoded_str = ""

    # tabela de probabilidade
    count = dict.fromkeys(string.ascii_lowercase, 1)
    cdf_range = dict.fromkeys(string.ascii_lowercase, 0)
    pdf = dict.fromkeys(string.ascii_lowercase, 0)

    low = 0
    high = Decimal(1)/Decimal(26)

    for key, value in sorted(cdf_range.items()):
        cdf_range[key] = [low, high]
        low = high
        high += Decimal(1)/Decimal(26)

    for key, value in sorted(pdf.items()):
        pdf[key] = Decimal(1)/Decimal(26)

    lower_bound = 0
    upper_bound = 1

    k = 0

    while (strlen != len(decoded_str)):
        for key, value in sorted(pdf.items()):

            # current range
            curr_range = upper_bound - lower_bound
            upper_cand = lower_bound + \
                (curr_range * cdf_range[key][1])
            lower_cand = lower_bound + \
                (curr_range * cdf_range[key][0])

            if (lower_cand <= encoded < upper_cand):
                k += 1
                decoded_str += key

                if (strlen == len(decoded_str)):
                    break

                upper_bound = upper_cand
                lower_bound = lower_cand

                count[key] += 1

                if (k == every):
                    k = 0
                    for key, value in sorted(pdf.items()):
                        pdf[key] = Decimal(count[key]) / \
                            Decimal(26+len(decoded_str))

                    low = 0
                    for key, value in sorted(cdf_range.items()):
                        high = pdf[key] + low
                        cdf_range[key] = [low, high]
                        low = high

    return decoded_str


def main():
    with open('text/a.txt', 'r') as file:
        st = file.read()
        encode_str = st.replace(" ", "")
        strlen = len(encode_str)
        every = 3
        encoded = encode(encode_str, every)
        print('Encoded: ', encoded)
        decoded = decode(encoded, strlen, every)
        print('Decoded:', decoded)

    with open('text/b.txt', 'r') as file:
        st = file.read()
        encode_str = st.replace(" ", "")
        strlen = len(encode_str)
        every = 3
        encoded = encode(encode_str, every)
        print('Encoded: ', encoded)
        decoded = decode(encoded, strlen, every)
        print('Decoded:', decoded)

    with open('text/c.txt', 'r') as file:
        st = file.read()
        encode_str = st.replace(" ", "")
        strlen = len(encode_str)
        every = 3
        encoded = encode(encode_str, every)
        print('Encoded: ', encoded)
        decoded = decode(encoded, strlen, every)
        print('Decoded:', decoded)


if __name__ == '__main__':
    main()


# b) Compare e comente o desempenho em termos de compressão para a técnica de
# compressão aritmética em relação a abordagem de Huffman (Laboratório 06) para tipo de
# dados de entrada.
# R: A taxa de compressão da codificação aritmética é melhor do que a codificação de Huffman,
# enquanto o desempenho da codificação de Huffman é superior ao da codificação aritmética. Dentro
# Além disso, a implementação da codificação Huffman é muito mais fácil do que a codificação aritmética.
