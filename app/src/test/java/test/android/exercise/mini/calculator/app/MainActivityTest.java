package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.exercise.mini.calculator.app.SimpleCalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class MainActivityTest {

    private static final String DEFAULT_CALCULATOR_OUTPUT = "~~~ ready to start ~~~";

    private ActivityController<MainActivity> activityController;
    private MainActivity activityUnderTest;
    private SimpleCalculator mockCalculator;

    /**
     * initialize main activity with a mock calculator
     */
    @Before
    public void setup() {
        mockCalculator = Mockito.mock(SimpleCalculator.class);
        Mockito.when(mockCalculator.output()).thenReturn(DEFAULT_CALCULATOR_OUTPUT);

        activityController = Robolectric.buildActivity(MainActivity.class);
        activityUnderTest = activityController.get();
        activityUnderTest.calculator = mockCalculator;
        activityController.create().start().resume();
    }

    @Test
    public void when_settingUpTheActivity_then_itShouldShowTheExpectedCalculatorOutputRightAway() {
        // setup
        String expectedText = DEFAULT_CALCULATOR_OUTPUT;
        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
        // verify
        assertEquals(expectedText, activityMainTextView.getText().toString());
    }

    @Test
    public void when_userClicksButtonPlus_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        // setup
        String expectedText = "button PLUS clicked";
        Mockito.when(mockCalculator.output()).thenReturn(expectedText);

        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
        View buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);

        // test
        buttonPlus.performClick();

        // verify
        Mockito.verify(mockCalculator).insertPlus(); // make sure that the activity called this method
        assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
    }

    @Test
    public void when_userClicksButtonMinus_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        // setup
        String expectedText = "button MINUS clicked";
        Mockito.when(mockCalculator.output()).thenReturn(expectedText);

        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
        View buttonUnderTest = activityUnderTest.findViewById(R.id.buttonMinus);

        // test
        buttonUnderTest.performClick();

        // verify
        Mockito.verify(mockCalculator).insertMinus(); // make sure that the activity called this method
        assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
    }

    @Test
    public void when_userClicksButtonBackSpace_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        // setup
        String expectedText = "button BackSpace clicked";
        Mockito.when(mockCalculator.output()).thenReturn(expectedText);

        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
        View buttonUnderTest = activityUnderTest.findViewById(R.id.buttonBackSpace);

        // test
        buttonUnderTest.performClick();

        // verify
        Mockito.verify(mockCalculator).deleteLast(); // make sure that the activity called this method
        assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
    }

    @Test
    public void when_userClicksButtonClear_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        // setup
        String expectedText = "button Clear clicked";
        Mockito.when(mockCalculator.output()).thenReturn(expectedText);

        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
        View buttonUnderTest = activityUnderTest.findViewById(R.id.buttonClear);

        // test
        buttonUnderTest.performClick();

        // verify
        Mockito.verify(mockCalculator).clear(); // make sure that the activity called this method
        assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
    }

    @Test
    public void when_userClicksButtonEquals_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        // setup
        String expectedText = "button Equals clicked";
        Mockito.when(mockCalculator.output()).thenReturn(expectedText);

        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
        View buttonUnderTest = activityUnderTest.findViewById(R.id.buttonEquals);

        // test
        buttonUnderTest.performClick();

        // verify
        Mockito.verify(mockCalculator).insertEquals(); // make sure that the activity called this method
        assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
    }

    @Test
    public void when_userClicksButton0_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(0);
    }

    @Test
    public void when_userClicksButton1_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(1);
    }

    @Test
    public void when_userClicksButton2_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(2);
    }

    @Test
    public void when_userClicksButton3_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(3);
    }

    @Test
    public void when_userClicksButton4_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(4);
    }

    @Test
    public void when_userClicksButton5_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(5);
    }

    @Test
    public void when_userClicksButton6_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(6);
    }

    @Test
    public void when_userClicksButton7_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(7);
    }

    @Test
    public void when_userClicksButton8_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(8);
    }

    @Test
    public void when_userClicksButton9_then_activityShouldForwardCallToCalculator_and_ShowTheExpectedCalculatorOutputRightAway() {
        testDigitButtonPressed(9);
    }

    private void testDigitButtonPressed(int digit) {
        // setup
        String expectedText = "button " + digit + " clicked";
        Mockito.when(mockCalculator.output()).thenReturn(expectedText);

        View buttonUnderTest;
        switch (digit) {
            case 0:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button0);
                break;
            case 1:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button1);
                break;
            case 2:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button2);
                break;
            case 3:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button3);
                break;
            case 4:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button4);
                break;
            case 5:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button5);
                break;
            case 6:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button6);
                break;
            case 7:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button7);
                break;
            case 8:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button8);
                break;
            case 9:
                buttonUnderTest = activityUnderTest.findViewById(R.id.button9);
                break;
            default:
                assertFalse(false);
                return;
        }

        TextView activityMainTextView = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);

        // test
        buttonUnderTest.performClick();

        // verify
        Mockito.verify(mockCalculator).insertDigit(digit); // make sure that the activity called this method
        assertEquals(expectedText, activityMainTextView.getText().toString()); // make sure that the activity shows text from calculator.output()
    }

    @Test
    public void when_activityGetsStateSaved_then_shouldAlsoSaveCalculatorState() {
        // setup
        Serializable dummyState = new Serializable() {
        };
        Mockito.when(mockCalculator.saveState()).thenReturn(dummyState);

        Bundle spyBundle = Mockito.spy(new Bundle());

        // test
        activityController.saveInstanceState(spyBundle);

        // verify
        Mockito.verify(spyBundle).putSerializable(anyString(), eq(dummyState)); // make sure that the activity stored the calculator state into the spy bundle
    }


    @Test
    public void when_activityGetsStateRestored_then_shouldAlsoSaveCalculatorState() {
        // setup
        Serializable dummyState = new Serializable() {
        };
        Mockito.when(mockCalculator.saveState()).thenReturn(dummyState);

        // let the activity store the calculator state into the bundle
        Bundle spyBundle = Mockito.spy(new Bundle());
        activityController.saveInstanceState(spyBundle);

        // test
        activityController.restoreInstanceState(spyBundle);

        // verify
        Mockito.verify(mockCalculator).loadState(eq(dummyState)); // make sure that the activity passed the previously-stored state to the calculator to load
    }
}