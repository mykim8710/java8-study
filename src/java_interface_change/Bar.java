package java_interface_change;

public interface Bar {
    default void printNameUpperCase() {
        System.out.println("BAR");
    }
}
