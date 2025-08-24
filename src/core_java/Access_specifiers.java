package core_java;

public class Access_specifiers {

    public static int a = 10; // Public access specifier
    private int b = 20; // Private access specifier
    protected static int c = 30; // Protected access specifier
   public int d = 40; // Default access specifier (no modifier)
   public static int e = 50; // Static variable, accessible without an object


    public static void main(){
        System.out.println("Static Method");
    }

    public void main1(){
        System.out.println("Non-Static Method");
    }

}
