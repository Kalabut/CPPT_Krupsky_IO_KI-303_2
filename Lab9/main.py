from KI303_Krupskyi_Lab9.mobile_phone import MobilePhone

# Перевірка, чи запущено файл як основну програму
if __name__ == "__main__":
    print("--- Start Program ---")
    
    # Створення об'єкту класу MobilePhone (Мобільний телефон)
    # Передаємо параметри: Марка, Екран, Батарея, Оператор
    my_phone = MobilePhone("Samsung Galaxy S21", 6.4, 4000, "Kyivstar")
    
    # Перевірка початкового стану заряду
    print(f"Initial charge: {my_phone.get_charge_level()}%")
    
    # Демонстрація роботи нових методів (Wi-Fi)
    my_phone.connect_wifi("Home_Network")
    
    # Демонстрація поліморфізму (виклик переписаного методу make_call)
    my_phone.make_call("+380981234567")
    
    # Тестування зарядки та відключення
    my_phone.charge_battery(10)
    my_phone.disconnect_wifi()
    
    print("--- Success! ---")