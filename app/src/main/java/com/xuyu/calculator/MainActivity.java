package com.xuyu.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计算器
 * @author 徐禹
 */
public class MainActivity extends AppCompatActivity {

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置按钮
        input = findViewById(R.id.input);
        Button btnZero = findViewById(R.id.btnZero);
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);
        Button btnThree = findViewById(R.id.btnThree);
        Button btnFour = findViewById(R.id.btnFour);
        Button btnFive = findViewById(R.id.btnFive);
        Button btnSix = findViewById(R.id.btnSix);
        Button btnSeven = findViewById(R.id.btnSeven);
        Button btnEight = findViewById(R.id.btnEight);
        Button btnNine = findViewById(R.id.btnNine);
        Button btnBackspace = findViewById(R.id.btnBackspace);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnPoint = findViewById(R.id.btnPoint);
        Button btnEqual = findViewById(R.id.btnEqual);
        //设置点击事件
        OnClick on = new OnClick();
        btnZero.setOnClickListener(on);
        btnOne.setOnClickListener(on);
        btnTwo.setOnClickListener(on);
        btnThree.setOnClickListener(on);
        btnFour.setOnClickListener(on);
        btnFive.setOnClickListener(on);
        btnSix.setOnClickListener(on);
        btnSeven.setOnClickListener(on);
        btnEight.setOnClickListener(on);
        btnNine.setOnClickListener(on);
        btnBackspace.setOnClickListener(on);
        btnClear.setOnClickListener(on);
        btnDivide.setOnClickListener(on);
        btnMultiply.setOnClickListener(on);
        btnSubtract.setOnClickListener(on);
        btnAdd.setOnClickListener(on);
        btnPoint.setOnClickListener(on);
        btnEqual.setOnClickListener(on);
    }

    private class OnClick implements View.OnClickListener {

        //保存所有运算符
        List<String> strings = Arrays.asList("+","-","X","/");

        @Override
        public void onClick(View view) {
            String s = input.getText().toString();
            //判断点击按钮
            switch (view.getId()) {
                case R.id.btnZero:
                case R.id.btnOne:
                case R.id.btnTwo:
                case R.id.btnThree:
                case R.id.btnFour:
                case R.id.btnFive:
                case R.id.btnSix:
                case R.id.btnSeven:
                case R.id.btnEight:
                case R.id.btnNine:
                case R.id.btnPoint:
                    input.setText(s + ((Button) view).getText());//将按钮按下的数字输入
                    break;
                case R.id.btnAdd:
                case R.id.btnSubtract:
                case R.id.btnMultiply:
                case R.id.btnDivide:
                    //重复输入运算符过滤
                    if (!strings.contains(s.substring(input.length() - 1,input.length()))){
                        input.setText(s + "" + ((Button) view).getText() + "");//将按钮按下的计算符输入
                    }
                    break;
                case R.id.btnEqual:
                    //点击等于计算结果
                    double sum = getNum();
                    s = Double.toString(sum);
                    input.setText(s);
                    break;
                case R.id.btnBackspace:
                    //退格判断
                    if (s.length() > 0){
                        String tui = s.substring(0, input.length() - 1);//退格：减去现有字符最后一格
                        input.setText(tui);
                    }
                    break;
                case R.id.btnClear:
                    input.setText("");
                    break;
            }
        }
    }

    //处理输入值
    public double getNum() {
        String sa = input.getText().toString();
        System.out.println("sa = "+sa);
        String[] str = null;
        char symbol = ' ';//初始化运算符
        for (int i = 0; i < sa.length(); i++) {//判断运算符
            char aa = sa.charAt(i);
            if (aa == '+') {
                str = sa.split("\\+");//切割字符串
                symbol = '+';
            }
            if (aa == '-') {
                str = sa.split("-");
                symbol = '-';
            }
            if (aa == 'X') {
                str = sa.split("X");
                symbol = 'X';
            }
            if (aa == '/') {
                str = sa.split("/");
                symbol = '/';
            }
        }
        //计算结果
        assert str != null;
        return calculate(str, symbol);
    }

    public static double calculate(String[] shuzi, char symbol) {//计算
        ArrayList<Double> ar = new ArrayList<>();
        double sum = 0;
        for (String value : shuzi) {
            double s = Double.parseDouble(value);
            ar.add(s);
        }
        if (symbol == '+') {
            sum = ar.get(0) + ar.get(1);
        }
        if (symbol == '-') {
            sum = ar.get(0) - ar.get(1);
        }
        if (symbol == 'X') {
            sum = ar.get(0) * ar.get(1);
        }
        if (symbol == '/') {
            sum = ar.get(0) / ar.get(1);
        }
        if (symbol == ' ') {
            return sum;
        }
        return sum;
    }
}
