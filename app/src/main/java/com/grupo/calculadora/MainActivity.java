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
    private boolean nuevaEntrada = true;

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

        configurarBotones();
    }

    // Funcion Sumar , restar y calcular la operacion

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

    // Funcionamiento de botones

    private void configurarBotones() {
        // Configurar botones numéricos (0-9)
        int[] idsNumeros = {
                R.id.boton0, R.id.boton1, R.id.boton2, R.id.boton3, R.id.boton4,
                R.id.boton5, R.id.boton6, R.id.boton7, R.id.boton8, R.id.boton9
        };

        for (int id : idsNumeros) {
            Button boton = findViewById(id);
            if (boton != null) {
                boton.setOnClickListener(v -> agregarNumero(boton.getText().toString()));
            }
        }

        // Botón decimal
        Button botonDecimal = findViewById(R.id.botonDecimal);
        if (botonDecimal != null) {
            botonDecimal.setOnClickListener(v -> agregarDecimal());
        }

        // Botón Borrar (C)
        Button botonBorrar = findViewById(R.id.botonBorrar);
        if (botonBorrar != null) {
            botonBorrar.setOnClickListener(v -> borrarTodo());
        }

        // Botón Multiplicar (×)
        Button botonMultiplicar = findViewById(R.id.botonMultiplicar);
        if (botonMultiplicar != null) {
            botonMultiplicar.setOnClickListener(v -> prepararOperacion("×"));
        }


        // Botón Dividir (÷)
        Button botonDividir = findViewById(R.id.botonDividir);
        if (botonDividir != null) {
            botonDividir.setOnClickListener(v -> prepararOperacion("÷"));
        }


        // Botón Igual (=)
        Button botonIgual = findViewById(R.id.botonIgual);
        if (botonIgual != null) {
            botonIgual.setOnClickListener(v -> calcularResultado());
        }
    }

    private void agregarNumero(String numero) {
        if (nuevaEntrada || textoPantalla.getText().toString().equals("0")) {
            textoPantalla.setText(numero);
            nuevaEntrada = false;
        } else {
            textoPantalla.append(numero);
        }
    }

    private void agregarDecimal() {
        if (nuevaEntrada) {
            textoPantalla.setText("0.");
            nuevaEntrada = false;
        } else if (!textoPantalla.getText().toString().contains(".")) {
            textoPantalla.append(".");
        }
    }

    private void borrarTodo() {
        textoPantalla.setText("0");
        primerNumero = 0;
        operacionPendiente = "";
        nuevaEntrada = true;
    }

    public void prepararOperacion(String operacion) {
        try {
            primerNumero = Double.parseDouble(textoPantalla.getText().toString());
        } catch (NumberFormatException e) {
            primerNumero = 0;
        }
        operacionPendiente = operacion;
        nuevaEntrada = true;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public double calcularMultiplicacion(double segundoNumero) {
        return multiplicar(primerNumero, segundoNumero);
    }


    public double dividir(double a, double b) {
        return a / b;
    }

    public double calcularDivision(double segundoNumero) {
        return dividir(primerNumero, segundoNumero);
    }


    private void calcularResultado() {

        if (operacionPendiente.equals("+") || operacionPendiente.equals("-")) {
            try {
                double segundoNumero = Double.parseDouble(textoPantalla.getText().toString());
                double resultado = calcularSumaResta(segundoNumero);

                if (resultado == (long) resultado) {
                    textoPantalla.setText(String.valueOf((long) resultado));
                } else {
                    textoPantalla.setText(String.valueOf(resultado));
                }
            } catch (NumberFormatException e) {
                textoPantalla.setText("Error");
            }
            operacionPendiente = "";
            nuevaEntrada = true;
        }


        if (operacionPendiente.equals("×") || operacionPendiente.equals("*")) {
            try {
                double segundoNumero = Double.parseDouble(textoPantalla.getText().toString());
                double resultado = calcularMultiplicacion(segundoNumero);

                if (resultado == (long) resultado) {
                    textoPantalla.setText(String.valueOf((long) resultado));
                } else {
                    textoPantalla.setText(String.valueOf(resultado));
                }
            } catch (NumberFormatException e) {
                textoPantalla.setText("Error");
            }
            operacionPendiente = "";
            nuevaEntrada = true;
        }


        if (operacionPendiente.equals("÷")) {
            try {
                double segundoNumero = Double.parseDouble(textoPantalla.getText().toString());

                if (segundoNumero == 0) {
                    textoPantalla.setText("Error");
                } else {
                    double resultado = calcularDivision(segundoNumero);

                    if (resultado == (long) resultado) {
                        textoPantalla.setText(String.valueOf((long) resultado));
                    } else {
                        textoPantalla.setText(String.valueOf(resultado));
                    }
                }
            } catch (NumberFormatException e) {
                textoPantalla.setText("Error");
            }
            operacionPendiente = "";
            nuevaEntrada = true;
        }
    
    }
}