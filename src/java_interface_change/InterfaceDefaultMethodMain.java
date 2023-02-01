package java_interface_change;

public class InterfaceDefaultMethodMain {
    public static void main(String[] args) {
        Foo foo = new FooImpl("mykim");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();  // interface static method
    }
}
