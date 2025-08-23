package java_stream;

import java.util.Arrays;
import java.util.List;

public class A {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Avinash", "Ashish", "Shazneen", "Kumar");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }

}
