package OrderWithStateDemo;

public interface OrderState {
    String name();
    void pay(Order order);
    void ship(Order order);
    void cancel(Order order);
}
