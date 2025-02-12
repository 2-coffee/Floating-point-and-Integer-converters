import java.io.ByteArrayInputStream;
import java.io.*;
import java.util.*;

class FloatingPointConvTester extends FloatingPointConv{
    public static void main(String[] args) {
        testIntegers();
    }
    public static void testIntegers(){
        // FloatingPointConv Converter = new FloatingPointConv();
        String data_stream = String.format("%s\n%s\n%s\n%s",
                                            "123",
                                            "75",
                                            "10000000000",
                                            "exit");
        System.setIn(new ByteArrayInputStream(data_stream.getBytes()));
        FloatingPointConv.main(new String[] {});
    }
}
