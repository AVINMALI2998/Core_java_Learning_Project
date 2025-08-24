package core_java;

public class Conditional_statement {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = 30;
        int d = 40;
        System.out.println("Conditional_statement_class");
        if (a > b) {
            System.out.println("a is less than b");
        } else {
            System.out.println("a is not less than b");
        }
    }

    public static class Conditional_statement_1 {
        public static void main(String[] args) {
            int a = 10;
            int b = 20;
            int c = 30;
            int d = 40;
            System.out.println("Conditional_statement_class_1");
            if (a < b) {
                System.out.println("a is less than b");
            } else if (a < c) {
                System.out.println("a is less than c");
            } else if (b < d) {
                System.out.println("b is less than d");
            } else {
                System.out.println("None of the conditions are met");
            }
        }
    }
}
