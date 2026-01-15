public class KelvinSensor {

    private double temperature = 295.15;

    public KelvinSensor() {}

    public KelvinSensor(double temperature) {
        this.temperature = temperature;
    }
    public double readKelvin() {
        return temperature;
    }
}

