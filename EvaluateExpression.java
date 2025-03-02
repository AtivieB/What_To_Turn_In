import java.util.Stack;

public class EvaluateExpression {
    public static void main(String[] args) {
        String expression = "( 3 + 5 ) * 2 - 8 / 4";
        System.out.println("Result: " + evaluateExpression(expression));
    }

    public static int evaluateExpression(String expression) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        String[] tokens = insertBlanks(expression).split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) continue;
            if (Character.isDigit(token.charAt(0))) {
                operandStack.push(Integer.parseInt(token));
            } else if (token.charAt(0) == '(') {
                operatorStack.push('(');
            } else if (token.charAt(0) == ')') {
                while (operatorStack.peek() != '(') {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.pop();
            } else if ("+-*/".indexOf(token.charAt(0)) != -1) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token.charAt(0))) {
                    processAnOperator(operandStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));
            }
        }
        while (!operatorStack.isEmpty()) {
            processAnOperator(operandStack, operatorStack);
        }
        return operandStack.pop();
    }

    private static void processAnOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();
        switch (operator) {
            case '+': operandStack.push(operand1 + operand2); break;
            case '-': operandStack.push(operand1 - operand2); break;
            case '*': operandStack.push(operand1 * operand2); break;
            case '/': operandStack.push(operand1 / operand2); break;
        }
    }

    private static int precedence(char operator) {
        return (operator == '+' || operator == '-') ? 1 : 2;
    }

    private static String insertBlanks(String expression) {
        return expression.replaceAll("([()*/+-])", " $1 ");
    }
}