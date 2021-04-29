package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @VisibleForTesting
    public SimpleCalculator calculator;

    private TextView textViewCalculatorOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }

        // find views
        textViewCalculatorOutput = findViewById(R.id.textViewCalculatorOutput);
        TextView[] digitButtons = {findViewById(R.id.button0), findViewById(R.id.button1),
                findViewById(R.id.button2), findViewById(R.id.button3), findViewById(R.id.button4),
                findViewById(R.id.button5), findViewById(R.id.button6), findViewById(R.id.button7),
                findViewById(R.id.button8), findViewById(R.id.button9)};
        TextView buttonEquals = findViewById(R.id.buttonEquals);
        TextView buttonPlus = findViewById(R.id.buttonPlus);
        TextView buttonMinus = findViewById(R.id.buttonMinus);
        View buttonBackSpace = findViewById(R.id.buttonBackSpace);
        TextView buttonClear = findViewById(R.id.buttonClear);

        // initial output value
        textViewCalculatorOutput.setText(calculator.output());


        // assign on click listener to the views
        for (int i = 0; i < digitButtons.length; i++) {
            digitButtons[i].setOnClickListener(digitButtonListener(i));
        }

        buttonBackSpace.setOnClickListener(v -> {
            calculator.deleteLast();
            textViewCalculatorOutput.setText(calculator.output());
        });

        buttonEquals.setOnClickListener(v -> {
            calculator.insertEquals();
            textViewCalculatorOutput.setText(calculator.output());
        });

        buttonPlus.setOnClickListener(v -> {
            calculator.insertPlus();
            textViewCalculatorOutput.setText(calculator.output());
        });

        buttonMinus.setOnClickListener(v -> {
            calculator.insertMinus();
            textViewCalculatorOutput.setText(calculator.output());
        });

        buttonClear.setOnClickListener(v -> {
            calculator.clear();
            textViewCalculatorOutput.setText(calculator.output());
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("calculatorData", calculator.saveState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator.loadState(savedInstanceState.getSerializable("calculatorData"));
        textViewCalculatorOutput.setText(calculator.output());
    }

    private View.OnClickListener digitButtonListener(int digit) {
        return v -> {
            calculator.insertDigit(digit);
            textViewCalculatorOutput.setText(calculator.output());
        };
    }
}