package com.grupo.calculadora;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void multiplicacion_esCorrecta() {
        assertEquals(15.0, MainActivity.multiplicar(3.0, 5.0), 0.0001);
        assertEquals(0.0, MainActivity.multiplicar(5.0, 0.0), 0.0001);
        assertEquals(-12.0, MainActivity.multiplicar(4.0, -3.0), 0.0001);
        assertEquals(12.5, MainActivity.multiplicar(2.5, 5.0), 0.0001);
    }
}
