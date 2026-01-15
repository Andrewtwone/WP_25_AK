public class WeatherApp {
    public void showTemperature(CelsiusSensor sensor) {
        double c = sensor.readCelsius();
        System.out.printf("Temperatura: %.2f °C%n", c);
    }
}