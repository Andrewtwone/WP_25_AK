package OrderWithoutStateDemo;

enum Status {
    NEW, PAID, SHIPPED, DELIVERED, CANCELLED
}

public class Order {
    private Status status = Status.NEW;

    private void log(String action) {
        System.out.println(
                Ansi.CYAN + "[STATE = " + status + "] " +
                        Ansi.RESET + "wywołanie: " +
                        Ansi.BLUE + action +
                        Ansi.RESET
        );
    }

    public void pay() {
        log("pay()");
        switch (status) {
            case NEW -> {
                status = Status.PAID;
                System.out.println(Ansi.GREEN + "Zapłacono za zamówienie." + Ansi.RESET);
            }
            case PAID -> System.out.println(Ansi.YELLOW + "Już opłacone." + Ansi.RESET);
            default -> System.out.println(Ansi.RED + "Nie można opłacić w stanie: " + status + Ansi.RESET);
        }
    }

    public void ship() {
        log("ship()");
        switch (status) {
            case PAID -> {
                status = Status.SHIPPED;
                System.out.println(Ansi.GREEN + "Zamówienie wysłane." + Ansi.RESET);
            }
            case NEW -> System.out.println(Ansi.RED + "Najpierw zapłać." + Ansi.RESET);
            default -> System.out.println(Ansi.RED + "Nie można wysłać w stanie: " + status + Ansi.RESET);
        }
    }

    public void cancel() {
        log("cancel()");
        switch (status) {
            case NEW, PAID -> {
                status = Status.CANCELLED;
                System.out.println(Ansi.GREEN + "Zamówienie anulowane." + Ansi.RESET);
            }
            default -> System.out.println(Ansi.RED + "Nie można anulować w stanie: " + status + Ansi.RESET);
        }
    }

    public Status getStatus() {
        return status;
    }

    public static void main(String[] args) {
        Order order = new Order();

        order.ship();
        order.pay();
        order.pay();
        order.cancel();
        order.ship();

        System.out.println(
                Ansi.CYAN + "FINAL STATE: " + order.getStatus() + Ansi.RESET
        );

}


}

