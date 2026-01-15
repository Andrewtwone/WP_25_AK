public class  Main {
    public static void main(String[] args) {
        WeatherApp app = new WeatherApp();

        CelsiusSensor s1 = new KelvinToCelsiusAdapter(new KelvinSensor());
        CelsiusSensor s2 = new FahrenheitToCelsiusAdapter(new FahrenheitSensor());

        app.showTemperature(s1);
        app.showTemperature(s2);
    }
}
