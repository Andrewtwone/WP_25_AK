package OrderWithStateDemo;

class NewState implements OrderState{

    public String name() {
        return "New";
    }

    public void pay(Order order) {
        order.setState(new PaidState());
        System.out.println(Ansi.GREEN + "Zapłacono za zamówienie." + Ansi.RESET);
    }

    public void ship(Order order) {
        System.out.println(Ansi.RED + "Najpierw zapłać." + Ansi.RESET);
    }

    public void cancel(Order order) {
        order.setState(new CancelledState());
        System.out.println(Ansi.GREEN + "Zamówienie anulowane." + Ansi.RESET);
    }
}

class PaidState implements OrderState {

    public String name() { return "PAID"; }

    public void pay(Order order) {
        System.out.println(Ansi.YELLOW + "Już opłacone." + Ansi.RESET);
    }

    public void ship(Order order) {
        order.setState(new ShippedState());
        System.out.println(Ansi.GREEN + "Zamówienie wysłane." + Ansi.RESET);
    }

    public void cancel(Order order) {
        order.setState(new CancelledState());
        System.out.println(Ansi.GREEN + "Zamówienie anulowane." + Ansi.RESET);
    }
}

class ShippedState implements OrderState {

    public String name() { return "SHIPPED"; }

    public void pay(Order order) {
        System.out.println(Ansi.RED + "Za późno na płatność." + Ansi.RESET);
    }

    public void ship(Order order) {
        System.out.println(Ansi.YELLOW + "Już wysłane." + Ansi.RESET);
    }

    public void cancel(Order order) {
        System.out.println(Ansi.RED + "Nie można anulować po wysyłce." + Ansi.RESET);
    }
}

class CancelledState implements OrderState {

    public String name() { return "CANCELLED"; }

    public void pay(Order order) {
        System.out.println(Ansi.RED + "Błąd. Zamówienie jest anulowane." + Ansi.RESET);
    }

    public void ship(Order order) {
        System.out.println(Ansi.RED + "Błąd. Zamówienie jest anulowane." + Ansi.RESET);
    }

    public void cancel(Order order) {
        System.out.println(Ansi.YELLOW + "Już anulowane." + Ansi.RESET);
    }
}

