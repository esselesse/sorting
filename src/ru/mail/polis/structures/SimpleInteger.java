package ru.mail.polis.structures;

import java.util.DoubleSummaryStatistics;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical<SimpleInteger> {

    private static final int DIGIT_COUNT = 10;

    private final int data;
    private final int length;

    public SimpleInteger(Integer data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Source must be not null");
        }
        this.data = data;
        int temp = data;
        int tempLength = 0;
        while (temp>0){
            temp/=10;
            tempLength++;
        }
        this.length = tempLength;
    }

    @Override
    public int getDigit(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index " + index);
        } else if (index >= getDigitCount()) {
            return 0;
        } else {
            int temp = data;
            for (int i = 0; i < index; i++) {
                temp/=10;
            }
            return (int)(temp % 10);
        }
    }

    @Override
    public int getDigitMaxValue() {
        return DIGIT_COUNT;
    }

    @Override
    public int getDigitCount() {
        return this.length;
    }

    @Override
    public int compareTo(SimpleInteger anotherSimpleInteger) {
        return Integer.compare(this.data, anotherSimpleInteger.data);
    }

    @Override
    public String toString() {
        return data + "";
    }
}
