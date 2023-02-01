package functional_interface_lamda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class JavaLambdaMethodReference {
    public static void main(String[] args) {

        // 스태틱 메소드 참조, 타입::스태틱 메소드
        UnaryOperator<String> hiFunctionalUnaryOperator = (name) -> "Hi " +name;
        System.out.println(hiFunctionalUnaryOperator.apply("mykim"));

        UnaryOperator<String> hiUnaryOperator = Greeting::hi;
        System.out.println(hiUnaryOperator.apply("mykim"));


        // 특정 객체의 인스턴스 메소드 참조, 객체 레퍼런스::인스턴스 메소드
        UnaryOperator<String> helloFunctionalUnaryOperator = (name) -> "Hello " +name;
        System.out.println(helloFunctionalUnaryOperator.apply("mykim"));

        UnaryOperator<String> helloUnaryOperator = new Greeting()::hello;
        System.out.println(helloUnaryOperator.apply("mykim"));


        // 생성자 참조 : 기본생성자, 타입::new
        Supplier<Greeting> greetingSupplier = Greeting::new;
        Greeting greeting = greetingSupplier.get();
        System.out.println("greeting.getName() = " + greeting.getName());
        
        // 생성자 참조 : 생성자, 타입::new
        Function<String, Greeting> stringGreetingFunction = Greeting::new;
        Greeting mykimGreeting = stringGreetingFunction.apply("mykim");
        System.out.println("mykimGreeting.getName() = " + mykimGreeting.getName());


        // 임의 객체의 인스턴스 메소드 참조, 타입::인스턴스 메소드
        String[] names = {"mykim", "khjang", "jskim"};
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println("Arrays.toString(names) = " + Arrays.toString(names));
    }

    static class Greeting {
        private String name;

        public Greeting() {
        }

        public Greeting(String name) {
            this.name = name;
        }

        public String hello(String name) {
            return "Hello " +name;
        }

        public static String hi(String name) {
            return "Hi " +name;
        }

        public String getName() {
            return name;
        }
    }
}
