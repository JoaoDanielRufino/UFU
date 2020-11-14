# Nome do aluno: Pedro Henrique Faria Teixeira
# RA: 11621BCC025
# Nome do aluno: João Daniel de Aquino Rufino
# RA: 11621BCC033

# Laboratório: <Laboratório 06 - Compressão - Parte 1>


# 4. Implemente o algoritmo de Shannon-Fano e faça testes com as mesmas fontes do
# exercício 3. Comente as diferenças entre os algoritmos de Shannon-Fano e Huffman
# em relação ao desempenho sobre as fontes de entrada.

import math
import matplotlib.pyplot as plt
import numpy as np
from collections import Counter
import operator
import os


class ShanonFanno(object):

    def __init__(self, path):
        self.path = path
        self.sum_logs_with_pis = 0
        self.size_after_compress = 0
        self.sorted_s = ""
        self.char_dict = dict()

    def devide_chars(self, x, itr, code):
        if len(x) == 2:
            return [[x[0], itr + 1, code + "0"], [x[1], itr + 1, code + "1"]]
        if len(x) == 1:
            return [[x, itr, code]]
        index = self.break_the_node(x)
        return [self.devide_chars(x[:index+1], itr + 1, code + "0"), self.devide_chars(x[index+1:], itr + 1, code + "1")]

    def make_count(self):
        self.char_dict = dict(Counter(self.sentence))
        char_ls = sorted(self.char_dict.items(),
                         key=operator.itemgetter(1), reverse=True)
        sorted_s = ""
        for i in char_ls:
            sorted_s += i[0]
        return self.char_dict, sorted_s

    def flatten_the_tree(self, tree):
        leaf = False
        flat_list = []
        if len(tree) == 1:
            tree = tree[0]
        while(leaf == False):

            if type(tree[-1]) is not list:
                if len(tree) != 3:
                    break
                leaf = True
                pi = self.sentence.count(tree[0])/self.total
                log_pi = math.log(1/pi, 2)
                x = tree.copy()
                x.append(pi)
                self.sum_logs_with_pis += pi * log_pi
                return [x]
            else:

                flat_right = []
                flat_left = []
                flat_right.extend(self.flatten_the_tree(tree[0]))
                flat_left.extend(self.flatten_the_tree(tree[1]))
                flat_list.extend(flat_right)
                flat_list.extend(flat_left)
                return flat_list

    def break_the_node(self, node):
        total = 0
        for i in node:
            total += self.char_dict[i]
        length = len(node)
        count = 0
        last_char_index = 0
        for i in range(length//2):
            count += self.char_dict[self.sorted_s[i]]
            if (count - (total/2) >= 0):
                last_char_index = i + 1
                break
        return last_char_index

    def plot_pie(self):
        x_labels = list(map(lambda x: x[0], self.final_flat))
        y = list(map(lambda x: x[1], self.final_flat))
        plt.pie(y, labels=x_labels, autopct='%1.1f%%',  startangle=90)
        plt.show()

    def save(self):
        filename, file_extension = os.path.splitext(self.path)
        output_path = filename + "_shannon.bin"

        np_final_flat = np.array(sh.final_flat)
        keys = np_final_flat[:, 0]
        values = np_final_flat[:, 2]
        chars_encoded_dict = dict(zip(keys, values))

        arr = []
        for ch in self.sentence:
            arr.append(chars_encoded_dict[ch])

        with open(output_path, 'w') as output:
            output.write(' '.join(arr))

        print('Arquivo comprimido com sucesso!')

    def do_the_work(self):
        s = ''
        with open(self.path, 'r') as file:
            s = file.read()
            s = s.rstrip()
        self.sentence = s
        self.total = len(s)
        self.char_dict, self.sorted_s = self.make_count()
        last_char_index = self.break_the_node(self.sorted_s)
        left = self.sorted_s[:last_char_index]
        right = self.sorted_s[last_char_index:]
        left_tree = self.devide_chars(left, 1, "0")
        right_tree = self.devide_chars(right, 1, "1")

        left_flat = self.flatten_the_tree(left_tree)
        right_flat = self.flatten_the_tree(right_tree)
        self.final_flat = []
        self.final_flat.extend(left_flat)
        self.final_flat.extend(right_flat)
        self.write_final_logs(self.final_flat)

    def write_final_logs(self, final_flat):
        b1 = 0
        for i in final_flat:
            count = self.sentence.count(i[0])
            b1 += count*i[1]
            print("Symbol: {0} => [Pi: {1}], [Code: {2}], [Freq.: {3}], [No. of Bits: {4}]".format(
                i[0], i[-1], i[2], count, i[1]*count))
        b0 = len(self.sentence)*8
        print("B0: {0}".format(b0))
        print("B1: {0}".format(b1))
        print("Compression Ratio: {0}".format(b0/b1))
        print("Ideal Entropy: {0}".format(self.sum_logs_with_pis))
        print("Algo Entropy: {0}".format(b1/self.total))


sh = ShanonFanno('text/a.txt')
sh.do_the_work()
sh.save()

sh = ShanonFanno('text/b.txt')
sh.do_the_work()
sh.save()

sh = ShanonFanno('text/c.txt')
sh.do_the_work()
sh.save()


# A diferença entre o algoritmo de ShannonFano e o de Huffman está basicamente na maneira em que a árvore binária é construída,
# a árvore de decisão de Huffman não é gerada baseando-se em divisões de dois grupos
# de símbolos que é utilizada pelo algoritmo de Shannon-Fano, no qual a soma das
# probabilidades tem que ser igual ou semelhante, mais sim na soma das menores
# freqüências de símbolos encontradas nos arquivos de entrada.
