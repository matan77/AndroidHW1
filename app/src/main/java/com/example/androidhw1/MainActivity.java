    package com.example.androidhw1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_res;
    private EditText ed_num1;
    private EditText ed_num2;
    private Spinner sp_op;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_res = findViewById(R.id.tv_res);
        ed_num1 = findViewById(R.id.ed_num1);
        ed_num2 = findViewById(R.id.ed_num2);
        sp_op = findViewById(R.id.spn_op);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.ops,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_op.setAdapter(adapter);


        findViewById(R.id.btn_calc).setOnClickListener(btnCalcClick());


    }

    View.OnClickListener btnCalcClick () {
        return v->{
            try {
                double num1 = Integer.parseInt(ed_num1.getText().toString());
                double num2 = Integer.parseInt(ed_num2.getText().toString());
                String operation = sp_op.getSelectedItem().toString();
                double res = 0;

                switch (operation) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0)
                        {
                              throw new Exception("can't make division by zero");
                        }
                        res = num1 / num2;
                        break;
                    case "^":
                        res = Math.pow(num1, num2);
                        break;
                }

                tv_res.setText(String.valueOf(res));
            }
            catch (Exception e) {
                tv_res.setText(String.format(getString(R.string.error) , e.getMessage()));
            }
        };
    }

}