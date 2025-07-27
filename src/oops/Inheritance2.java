package oops;

public class Inheritance2 extends Inheritance1{

    public static void main(String[] args) {
        Inheritance2 inheritance2 = new Inheritance2();
        Inheritance1 inheritance1 = new Inheritance1();  // sub class object
        Inheritance inheritance = new Inheritance();     // super class object

        System.out.println(x);
        System.out.println(inheritance1.c);
        System.out.println(inheritance2.c);
//	System.out.println(y);    private not possibe to call
        System.out.println(inheritance1.z);
        System.out.println(inheritance2.z);

        alpha();
        inheritance1.beta();
        inheritance2.beta();


    }
}
