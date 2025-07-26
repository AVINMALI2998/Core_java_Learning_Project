package core_java;

public class Access_specifiers {

    public static int a = 10; // Public access specifier
    private int b = 20; // Private access specifier
    protected int c = 30; // Protected access specifier
    int d = 40; // Default access specifier (no modifier)
    static int e = 50; // Static variable, accessible without an object


    static void main(){
        System.out.println("Static Method");
    }

    void main1(){
        System.out.println("Non-Static Method");
    }

}
