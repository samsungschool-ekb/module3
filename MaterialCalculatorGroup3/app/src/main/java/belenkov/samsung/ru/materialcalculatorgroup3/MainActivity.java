package belenkov.samsung.ru.materialcalculatorgroup3;

import androidx.appcompat.app.AppCompatActivity;
import mathjs.niltonvasques.com.mathjs.MathJS;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText calculatorDisplay;

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonDot;

    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonDivide;
    private Button buttonMultiple;
    private Button buttonEqually;
    private Button buttonCancel;

    MathJS mathJS;


    private View.OnLongClickListener buttonsLongListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.buttonCancel:
                    calculatorDisplay.setText("");
                    return true;
                default:
                    return false;
            }
        }
    };

    private View.OnClickListener buttonsClickListener = new View.OnClickListener() {
        private boolean isValid(String mathExpr) {
            if (!mathExpr.isEmpty()) {
                char lastChar = mathExpr.charAt(mathExpr.length() - 1);
                if (lastChar != '*' &&
                        lastChar != '/' &&
                        lastChar != '+' &&
                        lastChar != '-') {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String oldText = calculatorDisplay.getText().toString();

            switch (v.getId()) {
                case R.id.button0:
                case R.id.button1:
                case R.id.button2:
                case R.id.button3:
                case R.id.button4:
                case R.id.button5:
                case R.id.button6:
                case R.id.button7:
                case R.id.button8:
                case R.id.button9:
                    calculatorDisplay.setText(String.format("%s%s", oldText, b.getText()));
                    break;
                case R.id.buttonPlus:
                case R.id.buttonMultiple:
                case R.id.buttonDivide:
                    if (isValid(oldText)) {
                        calculatorDisplay.setText(String.format("%s%s", oldText, b.getText()));
                    }
                    break;
                case R.id.buttonEqually:
                    if (isValid(oldText)) {
                        mathJS.asyncEval(oldText, new MathJS.MathJSResult() {

                            @Override
                            public void onEvaluated(String result) {
                                calculatorDisplay.setText(result);
                            }
                        });
                    }

                    break;
                case R.id.buttonMinus:
                    calculatorDisplay.setText(String.format("%s%s", oldText, b.getText()));
                    break;
                case R.id.buttonCancel:
                    if (!oldText.isEmpty()) {
                        calculatorDisplay.setText(oldText.substring(0, oldText.length() - 1));
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        mathJS = new MathJS();
    }

    private void initUI() {
        calculatorDisplay = findViewById(R.id.calculatorDisplay);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDot = findViewById(R.id.buttonDot);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiple = findViewById(R.id.buttonMultiple);
        buttonEqually = findViewById(R.id.buttonEqually);
        buttonCancel = findViewById(R.id.buttonCancel);

        button0.setOnClickListener(buttonsClickListener);
        button1.setOnClickListener(buttonsClickListener);
        button2.setOnClickListener(buttonsClickListener);
        button3.setOnClickListener(buttonsClickListener);
        button4.setOnClickListener(buttonsClickListener);
        button5.setOnClickListener(buttonsClickListener);
        button6.setOnClickListener(buttonsClickListener);
        button7.setOnClickListener(buttonsClickListener);
        button8.setOnClickListener(buttonsClickListener);
        button9.setOnClickListener(buttonsClickListener);

        buttonPlus.setOnClickListener(buttonsClickListener);
        buttonMinus.setOnClickListener(buttonsClickListener);
        buttonMultiple.setOnClickListener(buttonsClickListener);
        buttonDivide.setOnClickListener(buttonsClickListener);
        buttonEqually.setOnClickListener(buttonsClickListener);

        buttonCancel.setOnClickListener(buttonsClickListener);
        buttonCancel.setOnLongClickListener(buttonsLongListener);

    }
}
