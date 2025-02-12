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

                System.out.println(standard_rep);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please provide a decimal number...\n-----\n");
            } catch (ArithmeticException e){
                System.out.println("Error: Overflow Occurred.");
                System.out.println(e.getMessage());
            }
        }
    }
    private static String DecimalToBinary(float number){
        StringBuilder bin_res = new StringBuilder();
        number = Math.abs(number);
        float left_radix = (float)Math.floor(number);
        float whole_number = left_radix;
        while (whole_number >= 1){
            bin_res.append((char)whole_number%2);
            whole_number = whole_number/2;
        }
        // System.out.println(bin_res.length());            // should be the exponent number
        int radix_index = bin_res.length();                 // technically, the radix_index is the exponent we want
        bin_res.reverse();
        String radix_ind = Integer.toString(radix_index);   // to store the radix_decimal
        bin_res.append(radix_ind);                          // append to string so we can access it
        bin_res.append(radix_ind.length());                 // to tell us how many expo characters we need
        return bin_res.toString();
    }
    
    private static String Simplified_FloatingPoint(float number){
        // The simplified model uses 1 bit for the sign, 5 bits for the exponent, and 8 bits for the significand.  
        // System.out.println(number);
        String bin_buffer = DecimalToBinary(number);        // convert floating number to binary
        int length = bin_buffer.length();
        int expo_length = bin_buffer.charAt(length - 1) -'0';
        // get the exponent
        int expo = Integer.parseInt(bin_buffer.substring(length-expo_length-1, length-1));  // 110141, get the 4
        char[] Simplified_assembler = new char[14];     // 14 bits in the simplified representation
        Arrays.fill(Simplified_assembler, '0');
        if (number < 0){    // negative number
            Simplified_assembler[0] = '1';
        }
        // fill in the exponent bits
        int expo_index = 5;         // 1,2,3,4,5
        while (expo>0 && expo_index>0){ // get the exponent and fill it from index 5 to 1
            if (expo%2 == 1){
                Simplified_assembler[expo_index] = '1';
            }
            expo_index--;
            expo/=2;
        }
        if (expo > 0){  // overflow
            throw new ArithmeticException("Caught in Simplified_FloatingPoint");
        }
        int mantissa_index = 0;       
        while (mantissa_index+6 < 14 && mantissa_index < length-(1+expo_length)){
            Simplified_assembler[mantissa_index+6] = bin_buffer.charAt(mantissa_index);
            mantissa_index++;
        }
        return String.valueOf(Simplified_assembler);
    }

    private static String IEEE_FloatingPoint(float number){
        // The IEEE-754 single precision floating point standard uses an 8-bit exponent (with a bias of 127) and a 23-bit significand.  
        return "";
    }
}
