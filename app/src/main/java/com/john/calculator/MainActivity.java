package com.john.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Button button_ac,button_divide,button_multiple,button_minus,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0,
            button_plus,button_backspace,button_dot,button_equal,button_bo,button_bc;

    TextView textView_history, textView_cal;

    ScrollView historyScroll ;

    String previousValue;
    String currentValue;
    double previousTotal;
    String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = (Button)findViewById(R.id.button_0);
        button_1 = (Button)findViewById(R.id.button_1);
        button_2 = (Button)findViewById(R.id.button_2);
        button_3 = (Button)findViewById(R.id.button_3);
        button_4 = (Button)findViewById(R.id.button_4);
        button_5 = (Button)findViewById(R.id.button_5);
        button_6 = (Button)findViewById(R.id.button_6);
        button_7 = (Button)findViewById(R.id.button_7);
        button_8 = (Button)findViewById(R.id.button_8);
        button_9 = (Button)findViewById(R.id.button_9);
        button_ac = (Button)findViewById(R.id.button_ac);
        button_plus = (Button)findViewById(R.id.button_plus);
        button_minus = (Button)findViewById(R.id.button_minus);
        button_divide = (Button)findViewById(R.id.button_divide);
        button_multiple = (Button)findViewById(R.id.button_multiple);
        button_dot = (Button)findViewById(R.id.button_dot);
        button_backspace = (Button)findViewById(R.id.button_backspace);
        button_equal = (Button)findViewById(R.id.button_equal);
        button_bo = (Button)findViewById(R.id.button_bo); // bracket open
        button_bc = (Button)findViewById(R.id.button_bc); // bracket close

        textView_history = (TextView) findViewById(R.id.textview_history);
        textView_cal = (TextView) findViewById(R.id.textview_cal);

        previousValue = "";
        currentValue = "";
        operator = null;

        historyScroll = (ScrollView) findViewById(R.id.history_scroll);

    }

    public void onClick(View v) {
        Log.i("click",v.getId()+"");

        switch (v.getId()){
            case R.id.button_0:
                appendNumber("0");

                break;
            case R.id.button_1:
                appendNumber("1");

                break;
            case R.id.button_2:
                appendNumber("2");

                break;
            case R.id.button_3:
                appendNumber("3");

                break;
            case R.id.button_4:
                appendNumber("4");

                break;
            case R.id.button_5:
                appendNumber("5");

                break;
            case R.id.button_6:
                appendNumber("6");

                break;
            case R.id.button_7:
                appendNumber("7");

                break;
            case R.id.button_8:
                appendNumber("8");

                break;
            case R.id.button_9:
                appendNumber("9");

                break;
            case R.id.button_ac:
                clearAll();

                break;
            case R.id.button_multiple:
                operator("*");

                break;
            case R.id.button_divide:
                operator("/");

                break;
            case R.id.button_plus:
                operator("+");

                break;
            case R.id.button_minus:
                operator("-");

                break;
            case R.id.button_dot:
                appendNumber(".");

                break;
            case R.id.button_backspace:
                backspace();

                break;
            case R.id.button_equal:
                Log.i("calculate",currentValue);
                calculate();

                break;
            case R.id.button_bo:

                break;
            case R.id.button_bc:

                break;
        }

    }

    void appendNumber(String number) {
        if(number == "." && currentValue.indexOf(".") != -1) return;
        currentValue += number;
        textView_cal.setText(currentValue);
    }

    void clearAll() {
        previousValue = "";
        currentValue = "";
        previousTotal = 0;
        textView_cal.setText(currentValue);
        textView_history.setText(previousValue);
        operator = null;
    }

    void calculate() {
        if(currentValue == "" || operator == null) return;;

        double val = Double.parseDouble(currentValue);

        switch (operator) {
            case "+":
                previousTotal = previousTotal + val;
                break;
            case "-":
                previousTotal = previousTotal - val;
                break;
            case "*":
                previousTotal = previousTotal * val;
                break;
            case "/":
                previousTotal = previousTotal / val;
                break;
        }
        previousValue += "\n" + currentValue ;
        textView_history.setText(previousValue);
        scrollToBottom();
        currentValue = "";

        textView_cal.setText(previousTotal+"");

    }

    void operator(String op) {
        if(currentValue != "") {
            previousTotal += Double.parseDouble(currentValue);
            previousValue += "\n" + currentValue + "\n";
        }
        else {
            previousValue += "\n";
        }
        previousValue += op;
        currentValue = "";
        textView_history.setText(previousValue);
        scrollToBottom();

        textView_cal.setText(currentValue);
        if(operator == null) {
            operator = op;
        }
        else {
            operator = op;
            calculate();
        }

    }

    void backspace() {
       if(currentValue.length() > 0){
           currentValue = currentValue.substring(0,currentValue.length() - 1);
           textView_cal.setText(currentValue);
       }
    }

    void scrollToBottom()
    {
        historyScroll.post(new Runnable()
        {
            public void run()
            {
                historyScroll.smoothScrollTo(0, textView_history.getBottom());
            }
        });
    }
}