package ch1_functional_interface_lamda;

import java.util.function.*;

public class JavaDefaultFunctionalInterfaceMain {
    public static void main(String[] args) {
        /** Function<T, R>
         *  T : 입력, R : 출력
         */
        Plus10 plus10 = new Plus10();
        System.out.println("plus10.apply(1) = " + plus10.apply(1));

        Function<Integer, Integer> plus10Impl = (i) -> i +10;
        System.out.println("plus10Impl.apply(1) = " + plus10Impl.apply(1));

        Function<Integer, Integer> multiply2Impl = (i) -> i * 2;
        System.out.println("multiply2Impl.apply(1) = " + multiply2Impl.apply(1));

        /** Function<T, R> - 함수조합
         * a.compose(b).apply(input) : input에 b함수를 먼저 적용하고 그 결과 값에 a함수 적용
         * a.andThen(b).apply(input) : input에 a함수를 먼저 적용하고 그 결과 값에 b함수 적용
         */
        Function<Integer, Integer> multiply2AndPlus10Impl = plus10Impl.compose(multiply2Impl);
        System.out.println("multiply2AndPlus10Impl.apply(1) = " +multiply2AndPlus10Impl.apply(1)); // 1 * 2 +10

        Function<Integer, Integer> Plus10Andmultiply2Impl = plus10Impl.andThen(multiply2Impl);
        System.out.println("Plus10Andmultiply2Impl.apply(1) = " +Plus10Andmultiply2Impl.apply(1)); // (10 + 1) *2

        /**
         * Consumer<T>
         * T : 입력, void
         */
        Consumer<String> printName = (name) -> System.out.println("name = " + name);
        printName.accept("mykim");

        /**
         * Supplier<T>
         * T : 출력(반환)
         */
        Supplier<Integer> get10 = () -> 10;
        System.out.println("get10.get() = " + get10.get());

        /**
         * Predicate<T>
         * T : 입력, 출력 : boolean
         */
        Predicate<Integer> isTenGeneration = (age) -> age >= 10 && age < 20;
        System.out.println("isTenGeneration.test(10) = " + isTenGeneration.test(10));

        /**
         * UnaryOperator<T>, 입력값 하나를 받아서 동일한 타입을 리턴
         * T = 입력값 타입 = 출력값 타입
         */
        UnaryOperator<String> plusA = (str) -> str +"A";
        System.out.println("plusA.apply(\"triple\") = " + plusA.apply("triple"));
    }
}
