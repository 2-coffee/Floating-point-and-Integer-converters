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
                } else if (response.length() == 0) {
                    System.out.println("Empty input, try again.");
                }
                float number = Float.parseFloat(response);
                System.out.println(number);
                String standard_rep = Simplified_FloatingPoint(number);
                String IEEE_rep = IEEE_FloatingPoint(number);

            } catch (Exception e) {
                System.out.println("Invalid input, please provide a decimal number...\n-----\n");
            }
        }
    }
    private static String DecimalToBinary(float number){
        StringBuilder bin_res = new StringBuilder();
        float left_radix = (float)Math.floor(number); //just the whole numbers
        int whole_number = (int)left_radix;
        while (whole_number > 0){
            bin_res.append((char)whole_number%2);
            whole_number /= 2;
        }
        int radix_index = bin_res.length();                 // technically, the radix_index is the exponent we want
        float right_radix = number - left_radix;

        for (int i = 0; i < String.valueOf(whole_number).length(); i++) {
            float temp = right_radix * 2;
            right_radix = (float)Math.floor(temp);
            bin_res.append((char)(int)(temp-right_radix));
            right_radix = temp - right_radix;
        }

        String radix_ind = Integer.toString(radix_index);   // to store the radix_decimal
        bin_res.append(radix_ind);                          // append to string so we can access it
        bin_res.append(radix_ind.length());                 // to tell us how many expo characters we need
        return bin_res.toString();
    }
    
    private static String Simplified_FloatingPoint(float number){
        // The simplified model uses 1 bit for the sign, 5 bits for the exponent, and 8 bits for the significand.  
        String bin_buffer = DecimalToBinary(number);        // convert floating number to binary
        int length = bin_buffer.length();
        int expo_length = bin_buffer.charAt(length - 1);
        // get the exponent
        int expo = Integer.parseInt(bin_buffer.substring(length-expo_length-1, length-1));  // 110141 
        return "";
    }

    private static String IEEE_FloatingPoint(float number){
        // The IEEE-754 single precision floating point standard uses an 8-bit exponent (with a bias of 127) and a 23-bit significand.  
        String bin_buffer = DecimalToBinary(number);
        int length = bin_buffer.length();

        return "";
    }
}
