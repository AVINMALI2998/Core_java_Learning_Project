package core_java;

public class Object_In_Java {


    static int a = 10; // Primitive data type
    Integer b = 20; // Wrapper class for int, which is an object
    char c = 'a';
    String str = "Hello"; // String is a class in Java, so it is an

    static void method() {
        System.out.println("Static Method");
    }

    void method1() {
        System.out.println("Non-Static Method");
    }

    static int method3(int x, int y) {
        return x + y;
    }

    static String stringMethod(String str) {
        return str;
    }

    static String stringMethod(String str, int x) {
        return str + " " + x;
    }

    static String stringMethod(char chr, int x) {
        return chr + " " + x;
    }

    public static void main(String[] args) {

        Object_In_Java object_In_Java = new Object_In_Java();
        System.out.println(a);
        System.out.println(object_In_Java.a);
        System.out.println(object_In_Java.b);
        System.out.println(object_In_Java.c);
        System.out.println(object_In_Java.str);

        method();
        object_In_Java.method1();
        int result = method3(5, 10);
        System.out.println("Result of method3: " + result);
        stringMethod("Hello, World!");
        System.out.println(stringMethod("Hello, World!"));
        String output = stringMethod("Avinash", 5);
        System.out.println(output);

        String output1 = stringMethod('c', 5);
        System.out.println(output1);

    }

}

