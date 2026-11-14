package com.grupo.calculadora;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textoPantalla;
    private double primerNumero = 0;
    private String operacionPendiente = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        textoPantalla = findViewById(R.id.textoPantalla);
        Button botonSumar = findViewById(R.id.botonSumar);
        Button botonRestar = findViewById(R.id.botonRestar);
        botonSumar.setOnClickListener(v -> prepararOperacion("+"));
        botonRestar.setOnClickListener(v -> prepararOperacion("-"));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.principal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void prepararOperacion(String operacion) {
        primerNumero = Double.parseDouble(textoPantalla.getText().toString());
        operacionPendiente = operacion;
        textoPantalla.setText("0");
    }
    public double sumar(double a, double b) {
        return a + b;
    }
    public double restar(double a, double b) {
        return a - b;
    }
    public double calcularSumaResta(double segundoNumero) {
        if (operacionPendiente.equals("+")) {
            return sumar(primerNumero, segundoNumero);
        }
        return restar(primerNumero, segundoNumero);
    }

}