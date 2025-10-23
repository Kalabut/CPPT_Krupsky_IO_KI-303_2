package KI303.Krupskyi.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Абстрактний клас {@code Phone} моделює базову предметну область "Телефон".
 * Містить складові частини — екран, процесор і батарею, 
 * а також базові методи для імітації роботи пристрою.
 * Усі події логуються у файл {@code phone_log.txt}.
 *
 * @author Крупський Ілля
 * @version 1.0
 * @since 2025-10-23
 */
public abstract class Phone {

    /** Вкладений клас, що описує екран телефону */
    class Display {
        double size;
        String resolution;

        Display(double size, String resolution) {
            this.size = size;
            this.resolution = resolution;
        }

        @Override
        public String toString() {
            return "Display: " + size + "\" " + resolution;
        }
    }

    /** Вкладений клас, що описує процесор телефону */
    class Processor {
        String model;
        int cores;

        Processor(String model, int cores) {
            this.model = model;
            this.cores = cores;
        }

        @Override
        public String toString() {
            return "Processor: " + model + " (" + cores + " cores)";
        }
    }

    /** Вкладений клас, що описує батарею телефону */
    class Battery {
        int capacity;
        int charge;

        Battery(int capacity, int charge) {
            this.capacity = capacity;
            this.charge = charge;
        }

        void chargeBattery(int amount) {
            charge = Math.min(100, charge + amount);
        }

        void discharge(int amount) {
            charge = Math.max(0, charge - amount);
        }

        @Override
        public String toString() {
            return "Battery: " + capacity + " mAh, charge = " + charge + "%";
        }
    }

    // --- Основні поля ---
    protected String brand;
    protected Display display;
    protected Processor processor;
    protected Battery battery;
    protected boolean poweredOn;
    protected PrintWriter log;

    // --- Конструктор ---
    /**
     * Конструктор із параметрами.
     * @throws IOException якщо не вдається створити лог-файл
     */
    public Phone(String brand, double screenSize, String resolution,
                 String cpu, int cores, int batteryCap, int charge) throws IOException {
        this.brand = brand;
        this.display = new Display(screenSize, resolution);
        this.processor = new Processor(cpu, cores);
        this.battery = new Battery(batteryCap, charge);
        this.poweredOn = false;
        this.log = new PrintWriter(new FileWriter("phone_log.txt", true));
        log.println("Created phone: " + brand);
        log.flush();
    }

    /** Увімкнути телефон */
    public void powerOn() {
        if (!poweredOn) {
            poweredOn = true;
            log.println(brand + " powered ON.");
            log.flush();
        }
    }

    /** Вимкнути телефон */
    public void powerOff() {
        if (poweredOn) {
            poweredOn = false;
            log.println(brand + " powered OFF.");
            log.flush();
        }
    }

    /** Зарядити телефон */
    public void chargePhone() {
        battery.chargeBattery(10);
        log.println(brand + " charging... ");
        log.flush();
    }

    /** Показати рівень заряду */
    public int getChargeLevel() {
        return battery.charge;
    }

    /** Отримати модель процесора */
    public String getProcessorModel() {
        return processor.model;
    }

    /** Перевірити, чи увімкнений телефон */
    public boolean isPoweredOn() {
        return poweredOn;
    }

    /** Закрити лог-файл */
    public void closeLog() {
        if (log != null) {
            log.println("Closing log for " + brand);
            log.close();
        }
    }

    /** Абстрактний метод, що реалізується у підкласах */
    public abstract void makeCall(String number);

    @Override
    public String toString() {
        return "Phone: " + brand + "\n" +
                display + "\n" +
                processor + "\n" +
                battery + "\n" +
                "State: " + (poweredOn ? "On" : "Off");
    }
}
