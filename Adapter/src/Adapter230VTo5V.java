public class Adapter230VTo5V implements Power5V {
    private final Socket230V socket;

    public Adapter230VTo5V(Socket230V socket) {
        this.socket = socket;
    }

    @Override
    public int output5V() {
        int v = socket.output230V();
        return convertTo5V(v);
    }

    private int convertTo5V(int input) {
        return 5;
    }
}
