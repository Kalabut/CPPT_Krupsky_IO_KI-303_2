package KI303.Krupskyi.Lab3;

/**
 * Інтерфейс {@code Connectable} описує можливість підключення пристрою до мережі.
 * Його реалізує клас {@link SmartPhone}.
 */
public interface Connectable {
    /**
     * Підключення до мережі.
     * @param networkName назва мережі
     */
    void connect(String networkName);

    /** Відключення від мережі */
    void disconnect();
}
