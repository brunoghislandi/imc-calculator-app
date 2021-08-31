package com.example.imccalculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText editValorAltura, editValorPeso;
    TextView txtResultado, txtHelp, txtIMC, txtClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        editValorAltura = findViewById(R.id.editValorAltura);
        editValorPeso = findViewById(R.id.editValorPeso);
        txtResultado = findViewById(R.id.txtResultado);
        txtHelp = findViewById(R.id.txtHelp);
        txtIMC = findViewById(R.id.txtIMC);
        txtClass = findViewById(R.id.txtClass);
    }

    public void match(View view) {
        String AlturaString = editValorAltura.getText().toString();
        String PesoString = editValorPeso.getText().toString();

        if(!AlturaString.equals("") && !PesoString.equals("")) {

            double Altura = Double.parseDouble(AlturaString);
            double Peso = Double.parseDouble(PesoString);
            double Resultado = Peso / ((Altura/100)*(Altura/100));
            DecimalFormat decimal = new DecimalFormat("0.00");
            String FinalResult = decimal.format(Resultado);

            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtResultado.getWindowToken(), 0);

            if(Resultado < 18.5){
                txtHelp.setTextColor(Color.parseColor("#00BFFF"));
                txtHelp.setText("Abaixo do Peso");
                txtResultado.setTextColor(Color.parseColor("#00BFFF"));
            }else if(Resultado >= 18.5 && Resultado <= 24.9){
                txtHelp.setTextColor(Color.parseColor("#32CD32"));
                txtHelp.setText("Peso Normal");
                txtResultado.setTextColor(Color.parseColor("#32CD32"));
            }else if(Resultado >= 25 && Resultado <= 29.9){
                txtHelp.setTextColor(Color.parseColor("#FF7F50"));
                txtHelp.setText("Sobrepeso");
                txtResultado.setTextColor(Color.parseColor("#FF7F50"));
            }else if(Resultado >= 30 && Resultado <= 34.9){
                txtHelp.setTextColor(Color.parseColor("#FF4500"));
                txtHelp.setText("Obesidade Grau I");
                txtResultado.setTextColor(Color.parseColor("#FF4500"));
            }else if(Resultado >= 35 && Resultado <= 39.9){
                txtHelp.setTextColor(Color.parseColor("#DC143C"));
                txtHelp.setText("Obesidade Grau II");
                txtResultado.setTextColor(Color.parseColor("#DC143C"));
            }else if(Resultado >= 40){
                txtHelp.setTextColor(Color.parseColor("#FF1493"));
                txtHelp.setText("Obesidade Grau III ou Mórbida");
                txtResultado.setTextColor(Color.parseColor("#FF1493"));
            }
            txtIMC.setText("Seu IMC é ");
            txtClass.setText("Classificado como: ");
            txtResultado.setText(FinalResult);
        }else{
            txtHelp.setTextColor(Color.parseColor("#FF1493"));
            txtHelp.setText("Preencha os valores corretamente.");
            editValorAltura.setText("");
            editValorPeso.setText("");
            txtResultado.setText("");
            txtClass.setText("");
            txtIMC.setText("");
        }
        editValorAltura.setText("");
        editValorPeso.setText("");
    }
}