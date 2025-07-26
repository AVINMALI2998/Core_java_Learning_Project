package core_java;

public class Variable {

    // Variable : its a peace of memory to store the data
    // byte = 1 byte
    // short = 2 byte
    // int = 4 byte
    // long = 8 byte
    // float = 4 byte
    // double = 8 byte
    // char = 2 byte
    // boolean = 1 byte
    public static void main(String args[]) {
        // Primitive data types
        int a = 10; // integer
        double b = 20.5; // double
        char c = 'c'; // character
        boolean d = true; // Boolean

        // Non-primitive data type
        String str = "Avinash Mali"; // String
        int arr[] = {1, 2, 3, 4, 5}; // Array

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(str);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }

    }
}
