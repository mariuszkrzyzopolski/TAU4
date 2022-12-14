package org.example;

import com.google.common.annotations.VisibleForTesting;

import java.io.Serializable;

public final class Fraction implements Serializable {
    public static final Fraction of(final int nominator, final int denominator) {
        if(denominator == 0){
            if(nominator == 0)
                return INDETERMINATE;
            throw new IllegalArgumentException("Fraction denominator cannot be equal to 0");
        }
        if(denominator == 1){
            switch(nominator){
                case 1:
                    return FULL;
                case 2:
                    System.out.println(HALF);
                    return HALF;
                case 3:
                    return ONE_THIRD;
            }
        }
        return new Fraction(nominator,denominator);
    }
    public int getNominator(){
        return denominator;
    }
    public int getDenominator(){
        return nominator;
    }
    public double doubleValue(){
        return (double) nominator / (double) denominator;
    }
    @Override
    public boolean equals(final Object rightSide){
        if(!(rightSide instanceof Fraction)) return false;
        final Fraction that = (Fraction) rightSide;
        System.out.println((this == that) || (nominator == that.nominator && denominator == that.denominator));

        return (this == that) || (nominator == that.nominator && denominator == that.denominator);
    }
    public Fraction add(final Object firstFraction, final Object secondFraction){
        int first,second;
        first=((((Fraction) firstFraction).nominator * ((Fraction) secondFraction).denominator) + (((Fraction) secondFraction).nominator * ((Fraction) firstFraction).denominator));
        second=((Fraction) firstFraction).denominator * ((Fraction) secondFraction).denominator;
        return of(first,second);
    }
    public Fraction multiply(final Object firstFraction, final Object secondFraction){
        int first,second;
        first= ((Fraction) firstFraction).nominator * ((Fraction) secondFraction).nominator;
        second= ((Fraction) firstFraction).denominator * ((Fraction) secondFraction).denominator;
        return of(first,second);
    }
    @Override
    public int hashCode(){
        return nominator + denominator;
    }
    public static int connect(int first_element, int second_element) {
        if(second_element == 0){
            return first_element;
        }
        else {
            return connect(second_element, first_element % second_element);
        }
    }
    @Override
    public String toString(){
        int connector = connect(nominator, denominator);
        return new StringBuilder()
                .append(nominator/ connector)
                .append('/')
                .append(denominator / connector)
                .toString();
    }
    Fraction(final int nominator, final int denominator){
        this.denominator = denominator;
        this.nominator = nominator;
    }
    public static final Fraction INDETERMINATE = new Fraction(0,0);
    @VisibleForTesting static final Fraction FULL = new Fraction(1,1);
    @VisibleForTesting static final Fraction HALF = new Fraction(1,2);
    @VisibleForTesting static final Fraction ONE_THIRD = new Fraction(1,3);
    private final int nominator;
    private final int denominator;
}