package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {
    private final String PLUS = "+";
    private final String MINUS = "-";

    private String output = "";

    @Override
    public String output() {
        if (output.isEmpty()) {
            return "0";
        }
        return output;
    }

    @Override
    public void insertDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit is a number in the range of 0-9");
        }

        String digitStr = String.valueOf(digit);

        // deals with leading zero
        int lastOperator = Math.max(output.lastIndexOf(PLUS), output.lastIndexOf(MINUS));
        if (output.substring(Math.max(0, lastOperator + 1)).equals("0")) {
            deleteLast();
        }
        output += digitStr;
    }

    @Override
    public void insertPlus() {
        if (output.isEmpty()) {
            output = "0" + PLUS;
        } else if (Character.isDigit(output.charAt(output.length() - 1))) {
            output += PLUS;
        }
    }

    @Override
    public void insertMinus() {
        if (output.isEmpty()) {
            output = "0" + MINUS;
        } else if (Character.isDigit(output.charAt(output.length() - 1))) {
            output += MINUS;
        }
    }

    @Override
    public void insertEquals() {
        // deals with empty expression
        if (output.isEmpty()) {
            output = "0";
            return;
        }
        // remove extra operators
        if (!Character.isDigit(output.charAt(output.length() - 1))) {
            deleteLast();
        }

        int first = 0, second = 0;
        String op = PLUS;
        for (char c : output.toCharArray()) {
            if (Character.isDigit(c)) {
                second = second * 10 + Character.getNumericValue(c);
            } else {
                first = eval(first, op, second);
                second = 0;
                op = String.valueOf(c);
            }
        }
        first = eval(first, op, second);
        output = String.valueOf(first);
    }

    private int eval(int a, String operator, int b) {
        if (operator.equals(MINUS)) {
            return a - b;
        } else if (operator.equals(PLUS)) {
            return a + b;
        }
        throw new IllegalArgumentException("Illegal operator, supports only '+','-'");
    }

    @Override
    public void deleteLast() {
        if (!output.isEmpty()) {
            output = output.substring(0, output.length() - 1);
        }
    }

    @Override
    public void clear() {
        output = "";
    }

    @Override
    public Serializable saveState() {
        return new CalculatorState(output);
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        output = casted.output;
    }

    private static class CalculatorState implements Serializable {
        String output;

        public CalculatorState(String output) {
            this.output = output;
        }
    }
}
