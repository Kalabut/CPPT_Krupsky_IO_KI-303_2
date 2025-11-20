import sys

# Введення розміру матриці
n = int(input("Введіть розмір квадратної матриці: "))

# Введення символу
filler = input("Введіть символ-заповнювач: ")

# Перевірки
if len(filler) == 0:
    print("Не введено символ-заповнювач")
    sys.exit(1)
elif len(filler) > 1:
    print("Забагато символів-заповнювачів")
    sys.exit(1)

lst = []   # зубчатий список

print("\nЗаштрихована область (варіант 13):")

for i in range(n):
    row = []        # тільки заштриховані комірки
    for j in range(n):

        if j % 2 == 1:     # непарні колонки – заштриховані
            print(filler, end=" ")
            row.append(filler)

        else:              # парні – порожні
            print(" ", end=" ")

    lst.append(row)
    print()   # новий рядок
