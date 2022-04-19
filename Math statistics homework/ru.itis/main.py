import math

import numpy as np
from collections import Counter
import matplotlib.pyplot as plt
from scipy.stats import chi2
from scipy.stats import t


def check(arr):
    if len(arr) == 0:
        return [False, 'Пустой массив']
    else:
        return [True]


# def get_moda(arr):
#     def_mess = 'Нет моды'
#     ch = check(arr)
#     if not ch[0]:
#         return ch[1]
#
#     #Дает двумерный массив: значение - сколько раз встречается, в порядке убывания
#     all_data = Counter(arr).most_common()
#
#     if len(all_data) == 1:
#         return all_data[0][0]
#     elif all_data[0][1] == all_data[1][1]:
#         return def_mess
#     else:
#         return all_data[0][0]


def get_moda(arr):

    ch = check(arr)
    if not ch[0]:
        return ch[1]

    #Дает двумерный массив: значение - сколько раз встречается, в порядке убывания
    all_data = Counter(arr).most_common()

    if len(all_data) == 1:
        return all_data[0][0]

    result = 0
    count = 0
    for i in range(len(all_data)):
        if all_data[0][1] == all_data[i][1]:
            result += all_data[i][0]
            count += 1
        else:
            break

    return round(result/count, 3)


def get_median(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    length = len(arr)
    if length % 2 == 0:
        return (arr[length//2] + arr[length//2 - 1])/2
    else:
        return arr[length//2]


def get_hist(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    number = math.ceil(1 + 3.322*math.log10(len(arr)))
    #bins - кол-во интервалов
    #density=True - делит количество встреч значения на n
    plt.hist(data, bins=number, density=True)
    plt.show()


def get_average(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    res = 0
    for x in arr:
        res += x
    return round(res/len(arr), 3)


def get_variance(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    math_exp = get_average(arr)
    res = 0
    for x in arr:
        res += (x - math_exp)**2
    return round(res/len(arr), 3)


def get_stand_dev(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    return round(math.sqrt(get_variance(arr)), 3)


def get_asymmetry(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    math_exp = get_average(arr)
    stand_dev = get_stand_dev(arr)
    res = 0
    for x in arr:
        res += (x - math_exp)**3
    return round(res / ((stand_dev**3) * len(arr)), 3)


def get_excess(arr):
    ch = check(arr)
    if not ch[0]:
        return ch[1]
    math_exp = get_average(arr)
    stand_dev = get_stand_dev(arr)
    res = 0
    for x in arr:
        res += (x - math_exp) ** 4
    return round(res / ((stand_dev ** 4) * len(arr)) - 3, 3)


def get_int_average(arr, g):
    math_exp = get_average(arr)
    stand_dev = get_stand_dev(arr)
    k = (t.ppf(g, len(arr)) * stand_dev)/math.sqrt(len(arr))
    return math_exp - k, math_exp + k


def get_int_var(arr, g):
    var = get_variance(arr)
    a1 = (1-g)/2
    a2 = (1+g)/2
    x1 = chi2.isf(df=len(arr)-1, q=a1)
    x2 = chi2.isf(df=len(arr)-1, q=a2)
    #числитель
    numerator = (len(arr)-1)*var
    return numerator/x1, numerator/x2


# data = [10, 10, 11, 12, 12, 14, 15, 15, 16, 16,
#         18, 18, 18, 19, 19, 20, 21, 21, 21, 22,
#         22, 22, 23, 23, 23, 25, 25, 28, 28, 28]

print('Введите путь до файла:')
input()

print('Введите номер столбца для исследования:')
col_num = int(input())

print('Введите коэффициент уверенности:')
g = float(input())

data = np.genfromtxt('dataset/iris.data', delimiter=",", usecols=col_num)
# data.sort()

print('Среднее', get_average(data))
print('Дисперсия', get_variance(data))
print('Среднеквадратичное отклонение', get_stand_dev(data))
print('Мода', get_moda(data))
print('Медиана', get_median(data))
interval = get_int_average(data, g)
print('Доверительный интервал для среднего', interval[0], '< x <', interval[1])
interval = get_int_var(data, g)
print('Доверительный интервал для дисперсии', interval[0], '< σ <', interval[1])
print('Ассиметрия', get_asymmetry(data))
print('Эксцесс', get_excess(data))
get_hist(data)
