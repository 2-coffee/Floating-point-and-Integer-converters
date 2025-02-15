import java.util.*;

public class SignedRep {

    public static void main(String[] args){
        InputCaller();
    }

    public static void InputCaller(){
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.println("Input a Binary in Signed Magnitude or 2's Complement. Exit to exit...");
            String response = in.nextLine();
            boolean invalid_flag = false;       // easy way to restart input loop
            if (response.equalsIgnoreCase("exit")){
                System.out.println("Shutting down...\n-----\n-----");
                in.close();
                return;
            }
            for (int i =0; i<response.length(); i++){
                char bit = response.charAt(i);
                if (bit != '0' && bit!= '1'){
                    invalid_flag = true;
                    break;
                }
            }
            if (invalid_flag){  // restart input loop
                System.out.println("Invalid Binary Bits");
                continue;
            }
            if (response.length() < 2){
                System.out.println("Input must have at least 2 bits");
                continue;
            }
            while (true){
                System.out.println("What was the Input, Signed or 2's Complement?");
                String rep = in.nextLine();
                if (rep.equalsIgnoreCase("signed")){
                    String sTO2 = SignedTO2(response);
                    System.out.println("Signed to 2's Complement: " + sTO2);

                    break;
                }
                else if (rep.equalsIgnoreCase("2's complement") 
                    || rep.equalsIgnoreCase("2s complement")){
                    String twosComp = twoCompsTOSigned(response);
                    System.out.println("2's Complement to Signed: " + twosComp);

                    break;
                } else {
                    System.out.println("Invalid Representation...");
                }
            }
        }   
    }
    private static String SignedTO2(String binary){
        // check first bit for negative
        // if positive do nothing
        // just flip every bit  and add 1 -> special case: all 0s for -0 
        int count_zeros = 0;
        char[] bin_arr = binary.toCharArray();
        if (bin_arr[0] == '1'){
            // bin_arr[0] = '0';
            for (int i =1; i < bin_arr.length ;i++){    // flip the bits and count zeros
                if (bin_arr[i] == '0'){
                    count_zeros++;
                    bin_arr[i] = '1';
                } else {
                    bin_arr[i] = '0';
                }
            }
            if (count_zeros == bin_arr.length -1){      // special case of -0
                char[] result = new char[bin_arr.length];
                Arrays.fill(result,'0');
                return String.valueOf(result);          // return all 0s
            }
            if (bin_arr[bin_arr.length-1] == '0'){  // add 1 to 2's complement 
                bin_arr[bin_arr.length-1] = '1';
                return String.valueOf(bin_arr);     // no need to do any additional work
            }   
            int previous_carry = 0;                 
            int carry = 0;
            for (int i = bin_arr.length - 1; i >= 0; i--){   // add 1 to 2's complement 
                previous_carry = carry;
                char bit = bin_arr[i];
                if (bit == '1'){    
                    bin_arr[i] = '0';
                    carry = 1;
                    if (i==0){  // carry all the way to the end, check for valid overflow
                        // theoretically this should never happen because signed bits represent less unique numbers than 2's comp
                        if (carry != previous_carry){
                            return "Overflow Error";
                        }
                    }
                } else {    // bit is 0
                    if (carry == 1){    // there's a carry so add
                        bin_arr[i] = '1';
                        if (i==0){      // if there is a carry and this is a 0, then carry in signed bit must be 1 and carry out is 0
                                        // this is an overflow
                            return "Overflow Error";
                        } else {
                            return String.valueOf(bin_arr); // else it's valid
                        }
                    }
                }
            }
        }
        return binary;
    }
    private static String twoCompsTOSigned(String binary){
        // 1101
        // 0111
        // 11000
        if (binary.charAt(0)=='1'){     // is negative
            // construct an array that has room for one extra bit incase of overflow since 2's complement can represent more numbers
            char[] comp_buffer = new char[binary.length()+1];
            for (int i = binary.length(); i > 0; i--){     // flips the bits
                char bit = binary.charAt(i-1);
                if (bit == '0'){
                    comp_buffer[i] = '1';
                } else {
                    comp_buffer[i] = '0';
                }
            }
            for (int i = comp_buffer.length-1; i > 0; i--){  // add 1 to the flipped bits
                char bit = comp_buffer[i];
                if (bit == '1'){
                    comp_buffer[i] = '0';
                } else {        // bit == '0'
                    comp_buffer[i] = '1';
                    if (i==1){
                        comp_buffer[0] = '1';
                    }
                    break;
                }
            }
            if (comp_buffer[0] == 0){
                comp_buffer[1] = '1';
            }
            return String.valueOf(comp_buffer);
            // add one to the flipped bits and see if there's an overflow needed
        }
        return binary;
    }
}
