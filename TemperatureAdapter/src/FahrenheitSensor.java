public class FahrenheitSensor {

    private double temperature = 68.0;

    public FahrenheitSensor() {}

    public FahrenheitSensor(double temperature) {
        this.temperature = temperature;
    }

    public double readFahrenheit() {
        return temperature;
    }
}
