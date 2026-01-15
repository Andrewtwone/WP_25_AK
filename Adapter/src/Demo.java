public class Demo {
    public static void main(String[] args) {
        Device device = new Device();

        Power5V powerFrom230 = new Adapter230VTo5V(new Socket230V());
        device.powerOn(powerFrom230);

        Power5V powerFrom110 = new Adapter110VTo5V(new Socket110V());
        device.powerOn(powerFrom110);
    }
}
