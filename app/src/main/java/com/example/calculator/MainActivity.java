package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner2;
    private TextView input, ans, textView;

    private String collect2 = "", text = "BIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        ans = findViewById(R.id.ans);
        textView = findViewById(R.id.spinner1);

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.to, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(MainActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void button(View view) {
        String collect = String.valueOf(view.getTag());

        if (!collect.equals("AC") && !collect.equals("C") && !collect.equals("Ans")){
            collect2 = String.valueOf(input.getText());
            collect2 += collect;
            input.setText(collect2);

        }else if (collect.equals("C") && input != null){
            collect2 = String.valueOf(input.getText());
            if (!collect2.isEmpty()) {
                StringBuffer sb = new StringBuffer(collect2);
                sb.deleteCharAt(sb.length() - 1);
                input.setText(sb);
            }

        }else if (collect.equals("AC")){
            input.setText("");
            ans.setText("");

        }else {
            collect2 = String.valueOf(input.getText());

            if (text.equals("BIN")) {
                collect2 = Integer.toBinaryString(Integer.parseInt(collect2));
            }else if (text.equals("OCT")){
                collect2 = Integer.toOctalString(Integer.parseInt(collect2));
            }else if (text.equals("HEX")){
                collect2 = Integer.toHexString(Integer.parseInt(collect2));
            }

            ans.setText(collect2.toUpperCase());
        }
    }

    String decimalToBinary(int number) {
        return Integer.toBinaryString(number);
    }
}