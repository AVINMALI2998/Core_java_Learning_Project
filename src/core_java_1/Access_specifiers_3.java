package core_java_1;

import core_java.Access_specifiers;

public class Access_specifiers_3 extends Access_specifiers {

    public static void main(String[] args) {

        Access_specifiers_3 access_specifiers_3 = new Access_specifiers_3();
        System.out.println(a);
//	System.out.println(b); // not possible
        System.out.println(c);
        System.out.println(access_specifiers_3.d);
        System.out.println(e);

        main();

        access_specifiers_3.main1();

    }
}
