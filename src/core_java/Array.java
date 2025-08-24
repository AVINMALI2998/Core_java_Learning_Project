package core_java;

public class Array {
    public static void main(String[] args) {


        int arr[] = {1, 2, 3, 4, 5};
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        arr[0] = 100;

        for (int j = 0; j < size; j++) {
            System.out.print(arr[j] + ",");
        }
        System.out.println();

        for (int a = size - 1; a >= 0; a--) {
            System.out.print(arr[a] + ",");
        }


    }
}
