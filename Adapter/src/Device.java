public class Device {
    public void powerOn(Power5V power) {
        int v = power.output5V();
        if (v != 5) {
            throw new IllegalStateException("Wrong voltage: " + v + "V (expected 5V)");
        }
        System.out.println("Device ON (5V).");
    }
}
