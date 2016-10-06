import java.util.*;
import java.io.*;

public class Calculator {

    static String output = "";
    static LinkedList<Float> numbers = new LinkedList();
    static LinkedList<String> operators = new LinkedList();

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new FileReader("InputCalculator.txt"));
        FileWriter out = new FileWriter("OutputCalculator.txt");

        initial(in, out);

        while (in.hasNextLine()) {
            readNumber(in, out);
            executeIfMultiplicationOrDivision();
            readOperator(in, out);
        }
        out.close();
    }

    private static void initial(Scanner in, FileWriter out) throws IOException {
        if (!in.hasNextLine()) return;
        output = "";
        readNumber(in, out);
        readOperator(in, out);
    }

    private static void readNumber(Scanner in, FileWriter out) throws IOException {
        float l = Float.parseFloat(in.nextLine());
        output += l;
        numbers.offer(l);
        writeNumber(out);
    }

    private static void readOperator(Scanner in, FileWriter out) throws IOException {
        String l = in.nextLine();

        if (l.equals("=")) {
            executeAllEquationOfSumAndSubtraction();
            writeNumber(out);
            initial(in, out);
        } else {
            output += l;
            operators.offer(l);
            writeNumber(out);
        }
    }

    private static void writeNumber(FileWriter out) throws IOException {
        out.write(output + "\n");
    }

    private static float executeOperation(float num1, String operator, float num2) {
        if (operator.equals("+")) return num1 + num2;
        else if (operator.equals("-")) return num1 - num2;
        else if (operator.equals("*")) return num1 * num2;
        else if (operator.equals("/")) return num1 / num2;
        else throw new IllegalArgumentException();
    }

    private static void executeIfMultiplicationOrDivision() {
        String lastOper = operators.peekLast();
        if (!(lastOper.equals("*") || lastOper.equals("/"))) return;

        float num2 = numbers.removeLast();
        float num1 = numbers.removeLast();
        numbers.offer(executeOperation(num1, operators.removeLast(), num2));
    }

    private static void executeAllEquationOfSumAndSubtraction() {
        while (numbers.size() != 1)
            numbers.push(executeOperation(numbers.pop(), operators.pop(), numbers.pop()));
        output = numbers.pop().toString();
    }

}
