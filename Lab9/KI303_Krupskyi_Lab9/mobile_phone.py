from KI303_Krupskyi_Lab9.phone import Phone

# Клас "Мобільний телефон", що успадковує клас Phone
class MobilePhone(Phone):
    # Конструктор похідного класу
    def __init__(self, brand, screen_size, battery_capacity, operator_name):
        # Виклик конструктора батьківського класу (Phone)
        super().__init__(brand, screen_size, battery_capacity)
        
        # Додаткова властивість мобільного телефону
        self.operator_name = operator_name 
        self.wifi_connected = False  # Стан підключення до Wi-Fi
        
        print(f"Mobile features initialized. Operator: {self.operator_name}")

    # Метод підключення до Wi-Fi
    def connect_wifi(self, network_name):
        self.wifi_connected = True
        print(f"Connected to Wi-Fi network: {network_name}")

    # Метод відключення від Wi-Fi
    def disconnect_wifi(self):
        self.wifi_connected = False
        print("Disconnected from Wi-Fi.")
    
    # Перевизначення методу дзвінка (поліморфізм)
    def make_call(self, number):
        # Додаємо специфічну логіку для мобільного (використання оператора)
        print(f"Dialing {number} via {self.operator_name}...")
        
        # Викликаємо базовий метод з класу Phone для виконання основної логіки
        super().make_call(number)