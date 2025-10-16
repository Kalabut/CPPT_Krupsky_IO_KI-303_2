package KI303.Krupskyi.Lab2;

/**
 * Клас-драйвер {@code Main} демонструє роботу класу {@link Phone}.
 * Перевіряє усі основні методи класу, включно з протоколюванням у файл
 * та коректним закриттям ресурсу.
 * 
 * @author Крупський Ілля
 * @version 1.0
 * @since 2025-10-16
 */
public class Main {
    public static void main(String[] args) {
        Phone phone = null;
        try {
            // створюємо телефон з параметрами
            phone = new Phone("Samsung Galaxy S21", 6.4, "2400x1080",
                    "Exynos 2100", 8, 4000, 85);

            System.out.println("=== Initial state ===");
            System.out.println(phone);
            System.out.println();

            // демонстрація методів
            phone.powerOn(); // вмикаємо телефон
            System.out.println("Phone is powered on: " + phone.isPoweredOn());

            phone.makeCall("+380981234567"); // імітація дзвінка
            System.out.println("After call, battery charge: " + phone.getChargeLevel() + "%");

            phone.chargePhone(); // заряджаємо телефон
            System.out.println("After charging, battery charge: " + phone.getChargeLevel() + "%");

            System.out.println("Processor model: " + phone.getProcessorModel());

            phone.powerOff(); // вимикаємо телефон
            System.out.println("Phone is powered on: " + phone.isPoweredOn());

            // фінальний стан
            System.out.println("\n=== Final state ===");
            System.out.println(phone);

        } catch (Exception e) {
        	 System.out.println("Error: " + e.getMessage());
        } finally {
            // Коректне завершення роботи з файлом (без finalize)
            if (phone != null)
                phone.closeLog();
        }
    }
}
