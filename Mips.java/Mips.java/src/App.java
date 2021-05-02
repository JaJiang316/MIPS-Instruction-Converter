import java.util.HashMap;
import java.util.ArrayList;

public class App {
    public static String convertToBin(String input) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        StringBuilder hex = new StringBuilder();
        map.put('0', "0000");
        map.put('1', "0001");
        map.put('2', "0010");
        map.put('3', "0011");
        map.put('4', "0100");
        map.put('5', "0101");
        map.put('6', "0110");
        map.put('7', "0111");
        map.put('8', "1000");
        map.put('9', "1001");
        map.put('A', "1010");
        map.put('B', "1011");
        map.put('C', "1100");
        map.put('D', "1101");
        map.put('E', "1110");
        map.put('F', "1111");
        int start = 0;

        while (start < input.length()) {
            hex.append(map.get(input.charAt(start)));
            start++;
        }

        return hex.toString();
    }

    public static String findOp(String input) {
        HashMap<String, String> map = new HashMap();
        map.put("100000", "add");
        map.put("100010", "sub");
        map.put("001000", "addi");
        map.put("100100", "and");
        map.put("100101", "or");
        map.put("101010", "slt");
        map.put("100011", "lw");
        map.put("101011", "sw");
        map.put("000100", "beq");

        return map.get(input);

    }

    public static double register(String input) {
        return Integer.parseInt(input, 2);
    }

    public static String findFormat(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ('1')) {
                return "I";
            }
        }
        return "R";
    }

    public static void main(String[] args) throws Exception {

        ArrayList<String> list = new ArrayList();
        list.add("02324022");
        list.add("22280000");
        list.add("8E510064");
        list.add("AE330064");
        list.add("02538822");
        list.add("014B9025");
        list.add("222800A5");
        list.add("014B482A");
        list.add("02324020");
        list.add("12320064");
        list.add("012A9824");

        for (String num : list) {
            System.out.println("Input: ");
            System.out.println(num);
            System.out.println();
            String type = "";
            String operation = "";
            double source1;
            double source2;
            double dest;
            double shift = 0;
            double offset = 0;
            String binary = convertToBin(num);
            type = findFormat(binary.substring(0, 6));
            // System.out.println(binary);
            if (type.equals("R")) {
                operation = findOp(binary.substring(26, 32));
                source1 = register(binary.substring(6, 11));
                source2 = register(binary.substring(11, 16));
                dest = register(binary.substring(16, 21));
                shift = register(binary.substring(21, 26));
                System.out.println("Instruction Format: " + type);
                System.out.println("Operation: " + operation);
                System.out.println("Source Registers: " + source1 + ", " + source2);
                System.out.println("Destination Register: " + dest);
                System.out.println("Shift amount: " + shift);
                System.out.println("Constant/Offset: none");
            } else {
                operation = findOp(binary.substring(0, 6));
                source1 = register(binary.substring(6, 11));
                dest = register(binary.substring(11, 16));
                offset = register(binary.substring(16, 32));
                System.out.println("Instruction Format: " + type);
                System.out.println("Operation: " + operation);
                System.out.println("Source Registers: " + source1);
                System.out.println("Destination Register: " + dest);
                System.out.println("Shift amount: none");
                System.out.println("Constant/Offset: " + offset);
            }
            System.out.println();
        }
    }

}
