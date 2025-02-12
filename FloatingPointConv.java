import java.util.*;

public class FloatingPointConv {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                float number = in.nextFloat(); // get input number
                String standard_rep = Standard_FLoatingPoint(number);
                String IEEE_rep = IEEE_FloatingPoint(number);
                
            } catch (Exception e) {
                System.out.println("Invalid input, please provide a decimal number...\n-----\n");
            }
        }
    }

    private static String Standard_FloatingPoint(float number){
        
    }

    private static String IEEE_FloatingPoint(float number){

    }
}
