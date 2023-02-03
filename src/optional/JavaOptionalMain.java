package optional;

import common.OnlineClass;

import java.util.List;
import java.util.Optional;

public class JavaOptionalMain {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = List.of(
            new OnlineClass(1, "spring boot", true),
            new OnlineClass(5, "rest api development", false)
        );

        Optional<OnlineClass> optionalOnlineClass = springClasses.stream()
                                                                    .filter(sc -> sc.getTitle().startsWith("spring"))
                                                                    .findAny();

        // Optional에 값이 있는지 없는지 확인하기
        boolean present = optionalOnlineClass.isPresent();  // Optional에 존재하는지?
        System.out.println("isPresent() = " + present);

        boolean empty = optionalOnlineClass.isEmpty();// Optional이 비어있는지?
        System.out.println("isEmpty() = " + empty);


        // Optional에 있는 값 가져오기
        OnlineClass getOnlineClass = optionalOnlineClass.get();    // 없다면 NoSuchElementException 발생
        System.out.println("getOnlineClass = " + getOnlineClass);

        // Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라.
        optionalOnlineClass.ifPresent(oc -> System.out.println("oc.getTitle() = " + oc.getTitle()));

        // Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라
        // !! Optional 내부에 값이 있더라도 orElse()의 내부코드는 반드시 실횅된다.
        Optional<OnlineClass> optionalOnlineClass2 = springClasses.stream()
                                                                    .filter(sc -> sc.getTitle().startsWith("jpa"))
                                                                    .findAny();

        OnlineClass onlineClass = optionalOnlineClass2.orElse(createNewOnlineClass());
        System.out.println("onlineClass = " + onlineClass);

        // Optional에 값이 있으면 가져오고 없는 경우에 ~~를 하라.
        // !! Optional 내부에 값이 있으면 orElseGet()의 내부코드는 실행되지 않는다.
        OnlineClass onlineClass1 = optionalOnlineClass2.orElseGet(() -> createNewOnlineClass());
        System.out.println("onlineClass1 = " + onlineClass1);

        // Optional에 값이 있으면 가져오고 없는 경우 에러를 던져라.
        //OnlineClass empty1 = optionalOnlineClass2.orElseThrow(() -> new IllegalStateException("empty"));
        //System.out.println("empty1 = " + empty1);

        // Optional에 들어있는 값 걸러내기 : 있다는 가정하에 동작
        Optional<OnlineClass> optionalOnlineClass1 = optionalOnlineClass
                                                        .filter(oc -> oc.isClosed());
        System.out.println("optionalOnlineClass1 = " + optionalOnlineClass1);

        // Optional에 들어있는 값 변환하기
        Optional<Integer> integer = optionalOnlineClass.map(oc -> oc.getId());
        System.out.println("integer = " + integer);
    }

    private static OnlineClass createNewOnlineClass() {
        System.out.println("create new online class");
        return new OnlineClass(6, "new class", false);
    }
}
