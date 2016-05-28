package zombienado_beta.utilities;

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
}
