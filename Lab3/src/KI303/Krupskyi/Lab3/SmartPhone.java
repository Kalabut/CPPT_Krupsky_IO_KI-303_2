package KI303.Krupskyi.Lab3;

import java.io.IOException;

/**
 * Клас {@code SmartPhone} розширює абстрактний клас {@link Phone}
 * і реалізує інтерфейс {@link Connectable}.
 * Додає функціональність підключення до мережі та встановлення застосунків.
 */
public class SmartPhone extends Phone implements Connectable {
    private String connectedNetwork;

    /**
     * Конструктор класу SmartPhone.
     */
    public SmartPhone(String brand, double screenSize, String resolution,
                      String cpu, int cores, int batteryCap, int charge) throws IOException {
        super(brand, screenSize, resolution, cpu, cores, batteryCap, charge);
    }

    /** Реалізація абстрактного методу для здійснення дзвінка. */
    @Override
    public void makeCall(String number) {
        if (isPoweredOn()) {
            log.println(brand + " is calling " + number + "...");
            battery.discharge(5);
            log.flush();
        } else {
            log.println("Cannot call — phone is off.");
        }
    }

    /** Реалізація інтерфейсу Connectable */
    @Override
    public void connect(String networkName) {
        connectedNetwork = networkName;
        log.println("Connected to network: " + networkName);
        log.flush();
    }

    @Override
    public void disconnect() {
        log.println("Disconnected from network: " + connectedNetwork);
        connectedNetwork = null;
        log.flush();
    }

    /** Встановлення застосунку */
    public void installApp(String appName) {
        if (isPoweredOn()) {
            log.println("Installing app: " + appName);
            log.flush();
        } else {
            log.println("Cannot install app — phone is off.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nNetwork: " +
                (connectedNetwork != null ? connectedNetwork : "not connected");
    }
}
