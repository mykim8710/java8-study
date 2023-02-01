package java_interface_change;

public class FooBarImpl implements Foo, Bar{
    @Override
    public void printName() {

    }

    @Override
    public String getName() {
        return null;
    }

    // Foo interface, Bar interface에 모두 동일한 이름의 default method 가 있는 경우,
    // compile error 발생, 구현해주어야함
    public void printNameUpperCase() {
        Foo.super.printNameUpperCase();
    }
}
