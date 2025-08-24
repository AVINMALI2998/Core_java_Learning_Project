package core_java;

public class Operators {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = 30;
        int d = 40;

        boolean z = (a < b);
        System.out.println(z);

        boolean z1 = (a < b) && (c < d); // Adding two conditions
        System.out.println(z1);

        boolean z2 = (a > b) && (c < d); // Adding two conditions
        System.out.println(z2);

        // Now operator
        boolean z3 = (a > b) || (c < d);
        System.out.println(z3);
    }
}

