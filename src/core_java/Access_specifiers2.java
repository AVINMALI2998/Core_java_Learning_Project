package core_java;

public class Access_specifiers2 extends Access_specifiers {
    public static void main(String[] args) {

        Access_specifiers1 Access_specifiers2 = new Access_specifiers1();

        System.out.println(a);
        System.out.println(Access_specifiers2.c);
        System.out.println(Access_specifiers2.d);
        System.out.println(e);

        main();
        Access_specifiers2.main1();
    }
}
