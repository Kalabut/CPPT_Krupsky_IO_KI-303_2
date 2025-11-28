import math
import struct
import os
import sys


def calculate(x: float) -> float:
    try:
        if math.cos(8 * x) == 0:
            raise ZeroDivisionError("cos(8x) = 0 → ділення на нуль")
        return math.sin(x) * math.sin(8 * x) / math.cos(8 * x)
    except Exception as e:
        print(f"Помилка: {e}")
        return float('nan')


def write_text(filename, result):
    try:
        with open(filename, 'w', encoding='utf-8') as f:
            f.write(str(result))
        print(f"Результат записано у текстовий файл: {filename}")
    except Exception as e:
        print(f"Помилка запису у текстовий файл: {e}")


def read_text(filename):
    try:
        if os.path.exists(filename):
            with open(filename, 'r', encoding='utf-8') as f:
                return f.read()
        else:
            raise FileNotFoundError(f"Файл {filename} не знайдено")
    except FileNotFoundError as e:
        print(e)
        return ""


def write_binary(filename, result):
    try:
        with open(filename, 'wb') as f:
            f.write(struct.pack('d', result))  # 8 байт (double)
        print(f"Результат записано у бінарний файл: {filename}")
    except Exception as e:
        print(f"Помилка запису у бінарний файл: {e}")


def read_binary(filename):
    try:
        if os.path.exists(filename):
            with open(filename, 'rb') as f:
                return struct.unpack('d', f.read())[0]
        else:
            raise FileNotFoundError(f"Файл {filename} не знайдено")
    except FileNotFoundError as e:
        print(e)
        return float('nan')


if __name__ == "__main__":
    try:
        x = float(input("Введіть x: "))
    except ValueError:
        print("Помилка: введено не число")
        sys.exit(1)

    y = calculate(x)
    print(f"Результат: y = {y}")

    # Запис у файли
    write_text("result.txt", y)
    write_binary("result.bin", y)

    # Читання назад
    print("З текстового файлу:", read_text("result.txt"))
    print("З бінарного файлу:", read_binary("result.bin"))
