package core_java;

public class Loops {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println("Loops_class");
        }

    }

    public static class Loop_01 {
        public static void main(String[] args) {
            for (int i = 0; i < 20; i++) {
                System.out.println(i);
            }
        }
    }

    public static class Loops_02 {
        public static void main(String[] args) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hi");
                for (int j = 0; j < 5; j++) {
                    System.out.println("Hello");
                }
            }
        }
    }
}
