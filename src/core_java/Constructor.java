package core_java;

public class Constructor {

    Constructor() { // This is a non-argumented constructor
        // It is called when an object is created without any arguments
        // It initializes the object with default values
        System.out.println("Non Argumented Constructor");
    }

    // Constructor overloading
    // This is a feature in Java where multiple constructors can have the same name
    Constructor(int i) { // This is an argumented constructor
        // It is Constructor overload when an object is created with an integer argument
        System.out.println("integer type Constructor");
    }


    Constructor(char c) {
        System.out.println("charactar type Constructor");
    }

    Constructor(String s) {
        System.out.println("String type Constructor");
    }

    Constructor(String s, int x) {
        System.out.println("String & integer type Constructor");
    }

    Constructor(int s, char x) {
        System.out.println("integer & charactar type Constructor");
    }

    public static void main(String[] args) {

        new Constructor();
        new Constructor(5);
        new Constructor('c');
        new Constructor("Avinash");
        new Constructor("Avinash", 5);
        new Constructor(2, 'c');


    }
}
