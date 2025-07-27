package oops;

public class Inheritance1 extends Inheritance{

    public static void alpha(){
        System.out.println("Static Alpha method in Inheritance1");
    }

    public void beta(){
        System.out.println("Non-static Beta method in Inheritance1");
    }

    public static void main(String [] args){

        Inheritance1 inheritance1 = new Inheritance1();  // sub class object
        Inheritance inheritance = new Inheritance();     // super class object

        System.out.println(x);
        System.out.println(inheritance1.c);
//	System.out.println(y);    private not possibe to call
        System.out.println(inheritance1.z);


    }
}
