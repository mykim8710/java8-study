package ch1_functional_interface_lamda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class JavaLambdaVariableCapture {
    public static void main(String[] args) {
        JavaLambdaVariableCapture foo = new JavaLambdaVariableCapture();
        foo.fun();
    }

    private void fun() {
        // 로컬변수 참조
        int baseNumber = 10;
        /** java8부터 final 생략가능 = 사실상 final
         * - baseNumber가 사실상 변할 수 없을때
         *
         */

        // 로컬 클래스 : 쉐도윙 O
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 20; // 이 변수가 fun()의 baseNumber를 가림 : 쉐도윙
                System.out.println("LocalClass : baseNumber = " + baseNumber); // 20
            }
        }
        new LocalClass().printBaseNumber(); // 20

        // 익명 클래스 : 쉐도윙 O
        Consumer<Integer> anonymous = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) { // 이 변수가 fun()의 baseNumber를 가림 : 쉐도윙
                System.out.println("anonymous baseNumber = " + baseNumber); // 입력 값
            }
        };
        anonymous.accept(30);   //30

        // 람다식 : 쉐도윙 X
        IntConsumer printInt = (i) -> {
            // int baseNumber = 30; // 컴파일오류, void fun()와 같은 scope이다, 같은 변수명을 정할 수 없다.
            System.out.println("Lambda : " +baseNumber); // 10
        };
        printInt.accept(11); // 10
    }


}
