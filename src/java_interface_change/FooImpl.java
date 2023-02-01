package java_interface_change;

public class FooImpl implements Foo {
    private String name;

    public FooImpl(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("name = " +this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // 언터페이스 기본 메서드
    // ㄴ 문제가 된다면 구현클래스에서 재정의할 수 있다.
    @Override
    public void printNameUpperCase() {
        Foo.super.printNameUpperCase();
    }
}
