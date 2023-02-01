package functional_interface_lamda;

public class RunSomethingMain {
    public static void main(String[] args) {
        // 자바 8전, 익명 내부 클래스
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("do it1");
            }
        };

        // 자바 8, 람다식으로 표현
        RunSomething runSomethingLambda = () -> {
            System.out.println("do it2");
        };

        runSomething.doIt();
        runSomethingLambda.doIt();


        RunSomething2 something2 = new RunSomething2() {
            @Override
            public int doIt(int number) {
                return number + 10;
            }
        };

        RunSomething2 something2Lambda = (number) -> number + 10;

        int i = something2Lambda.doIt(1);
        System.out.println("i = " + i);
    }
}
