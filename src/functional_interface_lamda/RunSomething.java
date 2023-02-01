package functional_interface_lamda;

@java.lang.FunctionalInterface
public interface RunSomething {
    /** 추상 메서드가 1개 => 함수형 인터페이스
     * static void printName() {}
     * default void printAge() {}
     * 위 두개는 상관없음, 단지 추상메스드의 개수만 따진다.
     *
     * @FunctionalInterface
     * 추상메서드가 2개면 컴파일 오류 발생
     */
    void doIt();

    static void printName() {
        System.out.println("My name is mykim");
    }

    default void printAge() {
        System.out.println("My age is 35");
    }
}
