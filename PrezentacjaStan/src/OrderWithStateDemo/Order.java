package OrderWithStateDemo;

public class Order {
    private OrderState state = new NewState();

    void setState(OrderState newState) {
        this.state = newState;
    }

    private void log(String action) {
        System.out.println(
                Ansi.CYAN + "[STATE = " + state.name() + "] " +
                        Ansi.RESET + "wywołanie: " +
                        Ansi.BLUE + action +
                        Ansi.RESET
        );
    }

    public void pay() {
        log("pay()");
        state.pay(this);
    }

    public void ship() {
        log("ship()");
        state.ship(this);
    }

    public void cancel() {
        log("cancel()");
        state.cancel(this);
    }

    public String getStateName() {
        return state.name();
    }

    public static void main(String[] args) {
        Order order = new Order();

        order.ship();
        order.pay();
        order.pay();
        order.cancel();
        order.ship();

        System.out.println(
                Ansi.CYAN + "FINAL STATE: " + order.getStateName() + Ansi.RESET
        );
        System.out.println();

        Order order1 = new Order();

        order1.pay();
        order1.ship();
    }
}

