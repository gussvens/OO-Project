package zombienado_v1.utilities;

/**
 * Created by Gustav on 16-05-20.
 */
public class MiscUtilites {
    public static int[] getCertanNumberArray(int length, int number){
        int[] array = new int[length];
        for(int i = length-1; i>=0; i--){
            if(number < Math.pow(10,i)){
                array[length-1-i] = 0;
            }else{
                array[length-1-i] = number/(int)Math.pow(10,i);
                number -= (number/(int)Math.pow(10,i))*(int)Math.pow(10,i);
            }
        }
        return array;
    }

    public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static double log2(double x) {
        return Math.log(x)/Math.log(2.0d);
    }
}
