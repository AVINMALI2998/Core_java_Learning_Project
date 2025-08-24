package core_java_1;

import core_java.Access_specifiers;

public class Access_specifiers4 extends Access_specifiers {

    public static void main(String[] args) {

        Access_specifiers4 access_specifiers_4 = new Access_specifiers4();
        System.out.println(a);
//	System.out.println(b); // not possible
        System.out.println(c);
        System.out.println(access_specifiers_4.d);
        System.out.println(e);

        main();

        access_specifiers_4.main1();
    }
}
