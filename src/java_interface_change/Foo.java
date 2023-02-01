package java_interface_change;

public interface Foo {
    void printName();

    // 기본 메서드 : 이 인터페이스를 구현하는 클래스에서 이 메서드를 구현하지않고 바로 사용할 수 있다
    // ㄴ 문제가 된다면 구현클래스에서 재정의할 수 있다.
    /**
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println("upperCase name= " +getName().toUpperCase());
    }

    // static 메서드
    static void printAnything() {
        System.out.println("Anything");
    }

    String getName();
}
