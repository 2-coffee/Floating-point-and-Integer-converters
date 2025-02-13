import java.io.ByteArrayInputStream;

class FloatingPointConvTester extends FloatingPointConv{
    public static void main(String[] args) {
        testIntegers();
        testDecimals();
    }
    public static void testIntegers(){
        // FloatingPointConv Converter = new FloatingPointConv();
        String data_stream = String.format("%s\n%s\n%s\n%s\n%s",
                                            "123",
                                            "75",
                                            "10000000000",
                                            "-10",
                                            "exit");        // make sure to exit the program
        System.setIn(new ByteArrayInputStream(data_stream.getBytes()));
        FloatingPointConv.main(new String[] {});
    }
    public static void testDecimals(){
        // FloatingPointConv Converter = new FloatingPointConv();
        String data_stream = String.format("%s\n%s\n%s\n%s\n%s",
                                            "123.123",
                                            "45.1289",
                                            "10000000000",
                                            "-10.2543",
                                            "exit");        // make sure to exit the program
        System.setIn(new ByteArrayInputStream(data_stream.getBytes()));
        FloatingPointConv.main(new String[] {});
    }
}
