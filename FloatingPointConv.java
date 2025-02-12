import java.util.*;

public class FloatingPointConv {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Input a decimal number or type Exit to exit.");
                String response = in.nextLine(); // get input number
                if (response.equalsIgnoreCase("exit")){
                    System.out.println("Shutting down...\n-----\n-----");
                    in.close();
                    return;
                }
                float number = Float.parseFloat(response);
                String standard_rep = Simplified_FloatingPoint(number);
                String IEEE_rep = IEEE_FloatingPoint(number);

            } catch (Exception e) {
                System.out.println("Invalid input, please provide a decimal number...\n-----\n");
            }
        }
    }
    private static String DecimalToBinary(float number){
        StringBuilder bin_res = new StringBuilder();
        float left_radix = (float)Math.floor(number);
        int whole_number = (int)left_radix;
        while (whole_number > 0){
            bin_res.append((char)whole_number%2);
            whole_number /= 2;
        }
        System.out.println(bin_res.reverse().toString());
        return "";
    }
    private static String Simplified_FloatingPoint(float number){
        // The simplified model uses 1 bit for the sign, 5 bits for the exponent, and 8 bits for the significand.  
        DecimalToBinary(number);
        return "";
    }

    private static String IEEE_FloatingPoint(float number){
        // The IEEE-754 single precision floating point standard uses an 8-bit exponent (with a bias of 127) and a 23-bit significand.  
        return "";
    }
}
