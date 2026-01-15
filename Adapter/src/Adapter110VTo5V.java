public class Adapter110VTo5V implements Power5V {
    private final Socket110V socket;

    public Adapter110VTo5V(Socket110V socket) {
        this.socket = socket;
    }

    @Override
    public int output5V() {
        int v = socket.output110V();
        return convertTo5V(v);
    }

    private int convertTo5V(int input) {
        return 5;
    }
}
