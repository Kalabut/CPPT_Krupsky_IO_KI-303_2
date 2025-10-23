package KI303.Krupskyi.Lab3;

/**
 * Клас-драйвер {@code Main} демонструє роботу спадкування, абстракції та інтерфейсів.
 * Тестує класи {@link SmartPhone} і {@link Connectable}.
 */
public class Main {
    public static void main(String[] args) {
        SmartPhone phone = null;
        try {
            phone = new SmartPhone("Samsung Galaxy S21", 6.4, "2400x1080",
                    "Exynos 2100", 8, 4000, 85);

            System.out.println("=== Initial state ===");
            System.out.println(phone);
            System.out.println();

            phone.powerOn();
            phone.connect("Wi-Fi Home");
            phone.makeCall("+380981234567");
            phone.installApp("Telegram");
            phone.disconnect();
            phone.chargePhone();
            phone.powerOff();

            System.out.println("\n=== Final state ===");
            System.out.println(phone);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (phone != null)
                phone.closeLog();
        }
    }
}
