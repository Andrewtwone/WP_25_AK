public class KelvinToCelsiusAdapter implements CelsiusSensor {
    private final KelvinSensor sensor;

    public KelvinToCelsiusAdapter(KelvinSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double readCelsius() {
        double k = sensor.readKelvin();
        return k - 273.15;
    }
}
