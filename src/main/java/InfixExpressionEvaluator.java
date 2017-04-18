import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by emigore on 2017-03-15.
 */
public class InfixExpressionEvaluator {

    public final static String OPERATOR_REGEX = "[+\\-*^/]";
    public final static String OPERAND_REGEX = "[1-9][0-9]*\\.?[0-9]*";
    private static final Map<String, Integer> PRIORITIES = Collections.unmodifiableMap(
            new HashMap<String, Integer>() {{
                put("+", 1);
                put("-", 1);
                put("*", 2);
                put("/", 2);
                put("^", 2);
            }}
    );

    private Stack<String> operandStack = new Stack<String>();
    private Stack<String> operatorStack = new Stack<String>();

    private List<String> inputList = new ArrayList<String>();
    private ListIterator<String> listIterator;

    private double op1;
    private double op2;
    private char operator;
    private double currentResult;


    /**
     * Performs calculations on 2 topmost operands using the topmost operator, then puts the result on the operandStack
     */
    private void calculate() {

        op1 = Double.parseDouble(operandStack.pop());
        operator = operatorStack.pop().charAt(0);
        op2 = Double.parseDouble(operandStack.pop());

        switch (operator) {
            case '+':
                currentResult = op1 + op2;
                break;
            case '-':
                currentResult = op2 - op1;
                break;
            case '*':
                currentResult = op1 * op2;
                break;
            case '/':
                if (op2 != 0) {
                    currentResult = op2 / op1;
                }
                break;
            default:
                return;
        }
        operandStack.push(String.valueOf(currentResult));
    }

    /**
     * Evaluates the result of the input expression ( delimiter - space)
     * @param inputExpression
     * @return
     */
    public Double evaluateExpression(String inputExpression) {

        /*
        TODO: Input security
         */
        List<String> infixList = StringToListConverter.convertStringToList(inputExpression);
        listIterator = infixList.listIterator();
        while (listIterator.hasNext()) {
            String currentWord = listIterator.next();
            if (Pattern.matches(OPERAND_REGEX, currentWord)) {
                operandStack.push(currentWord);
            } else if (Pattern.matches(OPERATOR_REGEX, currentWord)) {
                if (operatorStack.isEmpty()) {
                    operatorStack.push(currentWord);
                } else if (hasPriority(currentWord, operatorStack.peek())) {
                    operatorStack.push(currentWord);
                } else {
                    calculate();
                    operatorStack.push(currentWord);
                }
            } else if (currentWord.equals("(")) {
                operatorStack.push(currentWord);
            } else if (currentWord.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                        calculate();
                    }
                operatorStack.pop();

            } else {
                calculate();
            }
        }
        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().equals("(")) {
                break;
            } else {
                calculate();
            }
        }
        return Double.parseDouble(operandStack.pop());
    }

    /**
     * Performs evaluation step based on the input and items on stacks
     * The same functionality as evaluateExpression, but in steps - ready to implement with a GUI
     * @param currentWord
     */
    public void calculationStep(String currentWord) {

        if (Pattern.matches(OPERAND_REGEX, currentWord)) {
            operandStack.push(currentWord);
        } else if (Pattern.matches(OPERATOR_REGEX, currentWord)) {
            if (operatorStack.isEmpty()) {
                operatorStack.push(currentWord);
            } else if (hasPriority(currentWord, operatorStack.peek())) {
                operatorStack.push(currentWord);
            } else {
                calculate();
                operatorStack.push(currentWord);
            }
        } else if (currentWord.equals("(")) {
            operatorStack.push(currentWord);
        } else if (currentWord.equals(")")) {
            while (!operatorStack.peek().equals("(")) {
                calculate();
            }
            operatorStack.pop();

        } else {
            calculate();
        }
        if(currentWord.equals("=")) {
            while (!operatorStack.isEmpty()) {
                if (operatorStack.peek().equals("(")) {
                    break;
                } else {
                    calculate();
                }
            }
        }
    }

    public String getOperandStackPeek(){
        if(operandStack.isEmpty()){
            return "empty";
        }
        return operandStack.peek();
    }
    public Stack<String> getOperandStack() {
        return operandStack;
    }

    public List<String> getInputList() {
        return inputList;
    }

    /**
     * Determines which of the given operators has higher precedence
     * @param currentOperator
     * @param topmostOperator
     * @return
     */
    private boolean hasPriority(String currentOperator, String topmostOperator) {
        if (topmostOperator.equals("(") || topmostOperator.equals(")")) {
            return true;
        } else if (PRIORITIES.get(currentOperator) > PRIORITIES.get(topmostOperator)) {
            return true;

        }
        return false;
    }
}


