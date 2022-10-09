package helpers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SumCalculatorHelper implements Calculator {

    public int calculate(int a, int b) {
        return a + b;
    }

    public float calculate(float a, float b) {
        return a + b;
    }

    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public double calculate(double a, double b) {
        return a + b;
    }

    public BigInteger calculate(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public byte calculate(byte a, byte b) {
        return (byte) (a + b);
    }

    public short calculate(short a, short b) {
        return (short) (a + b);
    }

    public long calculate(long a, long b) {
        return a + b;
    }
}
