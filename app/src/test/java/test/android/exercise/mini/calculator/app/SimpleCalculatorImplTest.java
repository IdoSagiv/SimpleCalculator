package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

    @Test
    public void when_noInputGiven_then_outputShouldBe0() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_inputIsPlus_then_outputShouldBe0Plus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertPlus();
        assertEquals("0+", calculatorUnderTest.output());
    }


    @Test
    public void when_inputIsMinus_then_outputShouldBeCorrect() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        assertEquals("0-", calculatorUnderTest.output());
    }

    @Test
    public void when_insertDigitAfterLeadingZeroWithNoOperators_then_leadingZeroIsEmitted() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(0);
        calculatorUnderTest.insertDigit(1);
        assertEquals("1", calculatorUnderTest.output());
    }

    @Test
    public void when_insertDigitAfterLeadingZeroAfterPlus_then_leadingZeroIsEmitted() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(0);
        assertEquals("1+0", calculatorUnderTest.output());
        calculatorUnderTest.insertDigit(2);
        assertEquals("1+2", calculatorUnderTest.output());
    }

    @Test
    public void when_insertDigitAfterLeadingZeroAfterMinus_then_leadingZeroIsEmitted() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(0);
        assertEquals("1-0", calculatorUnderTest.output());
        calculatorUnderTest.insertDigit(2);
        assertEquals("1-2", calculatorUnderTest.output());
    }

    @Test
    public void when_insertDoublePlus_then_outputShouldHaveOnlyOnePlus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertPlus();
        assertEquals("0+", calculatorUnderTest.output());
    }

    @Test
    public void when_insertDoubleMinus_then_outputShouldHaveOnlyOneMinus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertMinus();
        assertEquals("0-", calculatorUnderTest.output());
    }

    @Test
    public void when_insertPlusAfterMinus_then_outputShouldHaveOnlyOneMinus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertPlus();
        assertEquals("0-", calculatorUnderTest.output());
    }

    @Test
    public void when_insertMinusAfterPlus_then_outputShouldHaveOnlyOnePlus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertMinus();
        assertEquals("0+", calculatorUnderTest.output());
    }


    @Test
    public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        try {
            calculatorUnderTest.insertDigit(357);
            fail("should throw an exception and not reach this line");
        } catch (RuntimeException e) {
            // good :)
        }
    }

    @Test
    public void when_callingInsertDigitWithNegativeNumber_then_exceptionShouldBeThrown() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        try {
            calculatorUnderTest.insertDigit(-1);
            fail("should throw an exception and not reach this line");
        } catch (RuntimeException e) {
            // good :)
        }
    }

    @Test
    public void when_callingInsertDigit_then_digitAddToOutput() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(3);
        assertEquals("3", calculatorUnderTest.output());

        calculatorUnderTest.insertDigit(2);
        assertEquals("32", calculatorUnderTest.output());
    }


    @Test
    public void when_callingInsertEqualsTwice_then_outputUpdatesOnce() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(0);
        calculatorUnderTest.insertEquals();
        assertEquals("3", calculatorUnderTest.output());
        calculatorUnderTest.insertEquals();
        assertEquals("3", calculatorUnderTest.output());
    }


    @Test
    public void when_callingInsertEqualsOneTimeWithMultipleOperators_then_outputUpdates() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertEquals();
        assertEquals("33", calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertEqualsWithEmptyOutput_then_outputShouldBe0() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertEquals();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertEqualsWithoutOperator_then_outputShouldBeTheInputNum() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertEquals();
        assertEquals("1", calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertEqualsWithOpenOperator_then_outputShouldIgnoreTheOperator() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertEquals();
        assertEquals("1", calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertEqualsWithOneOperatorPosRes_then_outputShouldIgnoreTheOperator() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(9);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertEquals();
        assertEquals("6", calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertEqualsWithOneOperatorNegRes_then_outputShouldIgnoreTheOperator() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();
        assertEquals("-2", calculatorUnderTest.output());
    }


    @Test
    public void when_callingInsertEqualsTwoTimes_then_outputUpdatesCorrectly() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(9);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertEquals();
        assertEquals("8", calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertEqualsWithNegResult_then_outputUpdates() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertEquals();
        assertEquals("-2", calculatorUnderTest.output());
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertEquals();
        assertEquals("-1", calculatorUnderTest.output());

    }

    @Test
    public void when_callingInsertDigitAfterInsertEquals_then_outputShouldBeCorrect() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(4);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertDigit(5);
        assertEquals("75", calculatorUnderTest.output());
    }


    @Test
    public void when_callingDeleteLast_then_lastOutputShouldBeDeleted() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.deleteLast();
        assertEquals("0", calculatorUnderTest.output());
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertMinus();

        calculatorUnderTest.deleteLast();
        assertEquals("11+2", calculatorUnderTest.output());
        calculatorUnderTest.deleteLast();
        assertEquals("11+", calculatorUnderTest.output());
        calculatorUnderTest.deleteLast();
        assertEquals("11", calculatorUnderTest.output());
        calculatorUnderTest.deleteLast();
        assertEquals("1", calculatorUnderTest.output());
        calculatorUnderTest.deleteLast();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_callingClear_then_outputShouldBeCleared() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());
        calculatorUnderTest.insertDigit(1);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_savingState_should_loadThatStateCorrectly() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        // give some input
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);

        // save current state
        Serializable savedState = calculatorUnderTest.saveState();
        assertNotNull(savedState);

        // call `clear` and make sure calculator cleared
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());

        // load the saved state and make sure state was loaded correctly
        calculatorUnderTest.loadState(savedState);
        assertEquals("5+7", calculatorUnderTest.output());
    }

    @Test
    public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();

        // give some input
        firstCalculator.insertDigit(5);
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(7);

        // save current state
        Serializable savedState = firstCalculator.saveState();
        assertNotNull(savedState);

        // load the saved state to the second calculator and make sure state was loaded correctly
        secondCalculator.loadState(savedState);
        assertEquals("5+7", secondCalculator.output());
    }
}