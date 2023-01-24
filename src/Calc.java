import java.util.Scanner;

public class Calc {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String expression = scanner.nextLine();
        System.out.print(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-,/,*)");
        }
        oper = detectOperation(expression);

        if (oper == null) {
            throw new Exception("Неподдерживаемая математическая операция");
        }

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;

        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;

            if (num1 == 0 || num2 == 0) {
                throw new Exception("т.к. делить на ноль нельзя");
            }

        } else {
            throw new RuntimeException("т.к. используются разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);

        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("т.к в римской системе нет отрицательных чисел");
            }

            result = Roman.convertToRoman(arabian);

        } else {

            result = String.valueOf(arabian);
        }

        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;

    }

    static int calc(int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

}
