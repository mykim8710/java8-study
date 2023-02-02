package stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamMain {
    public static void main(String[] args) {

        List<OnlineClass> springClasses = List.of(
            new OnlineClass(1, "spring boot", true),
            new OnlineClass(2, "spring data jpa", true),
            new OnlineClass(3, "spring mvc", false),
            new OnlineClass(4, "spring core", false),
            new OnlineClass(5, "rest api development", false)
        );

        List<OnlineClass> javaClasses = List.of(
                new OnlineClass(6, "The Java, Test", true),
                new OnlineClass(7, "The Java, Code manipulation", true),
                new OnlineClass(8, "The Java, 8 to 11", false)
        );

        List<List<OnlineClass>> mykimEvents = List.of(springClasses, javaClasses);


        System.out.println("1. spring 으로 시작하는 수업 출력 : filter()");
        springClasses.stream()
                    .filter(springClass -> springClass.getTitle().startsWith("spring"))
                    .forEach(springClass -> System.out.println("springClass = " + springClass));
        System.out.println("========================\n");


        System.out.println("2. closed 되지 않은 수업 : filter()");
        springClasses.stream()
                    //.filter(springClass -> !springClass.isClosed())
                    .filter(Predicate.not(OnlineClass::isClosed))
                    .forEach(springClass -> System.out.println("springClass = " + springClass));
        System.out.println("========================\n");


        System.out.println("3. 수업 이름 모아서 스트림 만들기 : map()");
        springClasses.stream()
                        .map(OnlineClass::getTitle)
                        .collect(Collectors.toList())
                        .forEach(title -> System.out.println("title = " + title));
        System.out.println("========================\n");


        System.out.println("4. 두 수업 목록에 들어있는 모든 수업 아이디 출력 : flatMap()");
        mykimEvents.stream()
                    .flatMap(list -> list.stream()) // List<List<OnlineClass>> -> List<OnlineClass>
                    .forEach(oc -> System.out.println("oc.getId() = " + oc.getId()));  //OnlineClass
        System.out.println("========================\n");


        System.out.println("5. 10부터 1씩 증가하는 무제한 스트림 중, 앞에 10개 빼고 최대 10개까지만 출력(20 ~ 29) : Stream.iterate(), skip(), limit()");
        Stream.iterate(10, i -> i + 1)  // 10부터 1씩 증가하는 무제한 스트림
                .skip(10)
                .limit(10)
                .forEach(i -> System.out.println("i = " + i));
        System.out.println("========================\n");


        System.out.println("6. Java 수업 중에 Test가 들어있는 수업이 있는지 확인 : allMatch(), anyMatch()");
        boolean test = javaClasses.stream()
                                   .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println("test = " + test);
        System.out.println("========================\n");


        System.out.println("7. Spring 수업 중에 spring이 들어있는 제목만 모아서 List만들기 : ");
        List<String> spring = springClasses.stream()
                                            .filter(oc -> oc.getTitle().contains("spring"))
                                            .map(oc -> oc.getTitle())
                                            .collect(Collectors.toList());
        spring.forEach(title -> System.out.println("title = " + title));
    }
}
