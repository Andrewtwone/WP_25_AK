public class FahrenheitToCelsiusAdapter implements CelsiusSensor {
    private final FahrenheitSensor sensor;

    public FahrenheitToCelsiusAdapter(FahrenheitSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double readCelsius() {
        double f = sensor.readFahrenheit();
        return (f - 32.0) * 5.0 / 9.0;
    }
}
