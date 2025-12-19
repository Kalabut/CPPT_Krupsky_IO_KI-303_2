import os

# Базовий клас "Телефон", що описує загальні характеристики
class Phone:
    # Конструктор класу. Ініціалізує властивості телефону
    def __init__(self, brand, screen_size, battery_capacity):
        self.brand = brand                  # Марка телефону
        self.screen_size = screen_size      # Розмір екрану
        self.battery_capacity = battery_capacity # Ємність батареї
        self.charge = 100                   # Початковий рівень заряду (у відсотках)
        
        # Виводимо повідомлення про створення об'єкту
        print(f"Phone created: {self.brand}, Screen: {self.screen_size}, Battery: {self.battery_capacity}")

    # Метод для здійснення дзвінка
    def make_call(self, number):
        # Перевіряємо, чи достатньо заряду для дзвінка
        if self.charge > 5:
            print(f"Calling {number} from {self.brand}...")
            self.charge -= 5  # Зменшуємо заряд на 5%
        else:
            print(f"Not enough battery to call from {self.brand}.")

    # Метод для зарядки телефону
    def charge_battery(self, amount):
        self.charge += amount
        # Обмеження заряду максимумом у 100%
        if self.charge > 100:
            self.charge = 100
        print(f"Battery charged. Current level: {self.charge}%")

    # Метод для отримання поточного рівня заряду (гетер)
    def get_charge_level(self):
        return self.charge