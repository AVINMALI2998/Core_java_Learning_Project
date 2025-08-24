package core_java;

public class Methods {

    void method1() {
        System.out.println("Non static Method 1");
    }

    static void method2() {
        System.out.println("static Method 2");
    }

    static void main() {
        System.out.println("Static method");
    }

    void main1(int x) {
        System.out.println("Static method");
    }

    void demo() {
        System.out.println("Static method");
    }

    static void demo1(char c) {
        System.out.println("Static method");
    }

    public static void main(String[] args) {
        Methods m = new Methods();
        m.method1(); // Instance method call
        method2();   // Static method call
        m.method2();   // Static method call


        main();
        m.main1(2);
        m.demo();
        demo1('A');

    }
}
