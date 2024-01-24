package project20280.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Wk1 {

    public static void q1() {
        int[] my_array = {25, 14, 56, 15, 36, 56, 77, 18, 29, 49};

        double Ans = 0;
        for(int num : my_array){
            Ans += num;

        }

        Ans /= 10;
        System.out.println(Ans);
    }

    public static void q2(){
        int[] arr = { 90, 77, 67, 55, 75, 57, 98, 17, 50, 23, 30, 100, 34, 75, 28, 43, 14, 52, 64, 13 };
        int x = 64;
        int indexOf = getIndexOf(arr, x); // your function
        System.out.println("index of " + x + " : " + indexOf);
    }

    public static int getIndexOf(int[] arr, int x){
        for (int i = 0; i < arr.length; i++){
            if(x == arr[i]){
                return i;
            }
        }
        return -1;
    }

    public static void q3(){
        String[] array1 = {"nail", "cure", "vagabond", "riddle", "act", "songs", "zipper", "excite", "magical", "eager", "blood", "coast", "guess", "selfish", "milky", "ticket", "authority"};
        String[] array2 = {"cure", "wicked", "guess", "vagabond", "riddle", "act", "excite", "songs", "troubled", "eager", "blood", "coast", "waiting", "selfish", "milky", "ticket", "authority", "nail"};

        String[] common = getCommonElements(array1, array2); // Call to method fixed
        System.out.println(Arrays.asList(common));
    }

    public static String[] getCommonElements(String[] array1, String[] array2){
        ArrayList<String> myarray = new ArrayList<>();

        for(int i = 0; i < array1.length; i++){
            for (int j = 0; j < array2.length; j++){
                if(array1[i].equals(array2[j])){
                    myarray.add(array1[i]);
                }
            }
        }

        String[] array = new String[myarray.size()];

;        return myarray.toArray(array);
    }

    public static void q4() {
        int m = 5, n = 5; // Corrected variable declaration
        int mat1[][] = new int[m][n];
        int mat2[][] = new int[m][n];

        // Initialize the matrices randomly
        Random rnd = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat1[i][j] = rnd.nextInt(100);
                mat2[i][j] = rnd.nextInt(100);
            }
        }

        int sum[][] = addMatrices(mat1, mat2);

        // Print the sum matrix
        System.out.println("Sum Matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] addMatrices(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int n = mat1[0].length;
        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }

        return sum;
    }

    public static void q5(){

        int m = 5, n = 5; // Corrected variable declaration
        double mat1[][] = new double[m][n];
        double mat2[][] = new double[m][n];

        // Initialize the matrices randomly
        Random rnd = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat1[i][j] = rnd.nextInt(100);
                mat2[i][j] = rnd.nextInt(100);
            }
        }

    }





    public static void main(String [] args) {
        q1();
        q2();
        q3();
        q4();
    }

}


