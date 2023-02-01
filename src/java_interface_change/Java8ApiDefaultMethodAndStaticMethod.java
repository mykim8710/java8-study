package java_interface_change;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class Java8ApiDefaultMethodAndStaticMethod {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("mykim");
        names.add("khjang");
        names.add("jskim");
        names.add("jwyang");

        // forEach
        //names.forEach(name -> System.out.println(name));
        names.forEach(System.out::println);
        System.out.println("");

        // spliterator : Collection을 병렬적으로 나눠서 처리할때 유용
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();// 쪼개기

        //while (spliterator.tryAdvance(n -> System.out.println(n)));
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("====================");
        while (spliterator1.tryAdvance(System.out::println));
        System.out.println("");

        // stream()
        List<String> m = names.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("M"))
                .collect(Collectors.toList());
        System.out.println(m);
        System.out.println("");

        // removeIf()
        names.removeIf(s -> s.startsWith("j"));
        names.forEach(System.out::println);
        System.out.println("");

        //Comparator
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed()); // 역순
        names.forEach(System.out::println);
        System.out.println("");
    }
}
