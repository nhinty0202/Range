package com.example.range;

public class Range<T extends Comparable<T>> {

    static final String BRACKET_OPEN = "(";
    static final String BRACKET_CLOSED= ")";
    static final String SQUARE_BRACKET_OPEN = "[";
    static final String SQUARE_BRACKET_CLOSED = "]";
    static final String INFINITIVE = "Infinitive";
    static final String COMMA = ", ";


    enum Type{
        OPEN_TYPE,CLOSED_TYPE,OPEN_CLOSED_TYPE,CLOSED_OPEN_TYPE,LESS_THAN_TYPE, AT_LEAST_TYPE,AT_MOST_TYPE, GREATER_THAN_TYPE, ALL_TYPE
    }
    private T lowerBound;
    private T upperBound;

    private Type type;

    private Range(T  lowerBound, T upperBound, Type type, boolean isCheck) {
        if(isCheck && lowerBound.compareTo(upperBound) > 0)
            throw new RuntimeException("Should not allow creating a invalid Range");
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.type = type;
    }

    public static <T extends Comparable<T>> Range<T> open(T lowerBound, T upperBound) {
        return new Range<T>(lowerBound, upperBound, Type.OPEN_TYPE,true);
    }
    public static <T extends Comparable<T>> Range<T> closed(T lowerBound, T upperBound){
        return new Range(lowerBound, upperBound, Type.CLOSED_TYPE, true);
    }

    public static <T extends Comparable<T>> Range<T> openClosed(T lowerBound, T upperBound){
        return new Range(lowerBound,upperBound, Type.OPEN_CLOSED_TYPE, true);
    }
    public static <T extends Comparable<T>> Range<T> closedOpen(T lowerBound, T upperBound){
        return new Range(lowerBound,upperBound, Type.CLOSED_OPEN_TYPE, true);
    }

    public static <T extends Comparable<T>> Range<T> lessThan(T value) {
        return new Range<T>(null,value, Type.LESS_THAN_TYPE, false);
    }

    public static <T extends Comparable<T>> Range<T> atLeast(T value) {
        return new Range<T>(value,null, Type.AT_LEAST_TYPE,false);
    }

    public static <T extends Comparable<T>> Range<T> atMost(T value) {
        return new Range<T>(null,value, Type.AT_MOST_TYPE,false);
    }

    public static <T extends Comparable<T>> Range<T> greaterThan(T value) {
        return new Range<T>(value,null, Type.GREATER_THAN_TYPE,false);
    }

    public static <T extends Comparable<T>> Range<T> all() {
        return new Range<T>(null,null, Type.ALL_TYPE,false);
    }

    public boolean contains(T value) {
        boolean result = false;
        switch (type){
            case OPEN_TYPE:
                result = value.compareTo(lowerBound) > 0 && upperBound.compareTo(value) > 0;
                break;
            case CLOSED_TYPE:
                result = value.compareTo(lowerBound) >= 0 && upperBound.compareTo(value) >=0;
                break;
            case OPEN_CLOSED_TYPE:
                result = value.compareTo(lowerBound) >0 && upperBound.compareTo(value) >=0;
                break;
            case CLOSED_OPEN_TYPE:
                result = value.compareTo(lowerBound) >= 0 && upperBound.compareTo(value) >0;
                break;
            case LESS_THAN_TYPE:
                result = upperBound.compareTo(value) > 0;
                break;
            case AT_LEAST_TYPE:
                result = value.compareTo(lowerBound) >= 0;
                break;
            case AT_MOST_TYPE:
                result = upperBound.compareTo(value) >= 0;
                break;
            case GREATER_THAN_TYPE:
                result = value.compareTo(lowerBound) > 0;
                break;
            case ALL_TYPE:
                result = true;
                break;
        }
        return result;
    }

    public String toString() {
        String result = "";
        switch (type){
            case OPEN_TYPE:
                result = BRACKET_OPEN + lowerBound + COMMA + upperBound + BRACKET_CLOSED ;
                break;
            case CLOSED_TYPE:
                result = SQUARE_BRACKET_OPEN + lowerBound + COMMA + upperBound + SQUARE_BRACKET_CLOSED  ;
                break;
            case OPEN_CLOSED_TYPE:
                result = BRACKET_OPEN + lowerBound + COMMA + upperBound + SQUARE_BRACKET_CLOSED  ;
                break;
            case CLOSED_OPEN_TYPE:
                result = SQUARE_BRACKET_OPEN + lowerBound + COMMA + upperBound + BRACKET_CLOSED ;
                break;
            case LESS_THAN_TYPE:
                result = SQUARE_BRACKET_OPEN + INFINITIVE + COMMA + upperBound + BRACKET_CLOSED;
                break;
            case AT_LEAST_TYPE:
                result = SQUARE_BRACKET_OPEN + lowerBound + COMMA + INFINITIVE + SQUARE_BRACKET_CLOSED;
                break;
            case AT_MOST_TYPE:
                result = SQUARE_BRACKET_OPEN + INFINITIVE + COMMA + upperBound + SQUARE_BRACKET_CLOSED;;
                break;
            case GREATER_THAN_TYPE:
                result = BRACKET_OPEN + lowerBound + COMMA + INFINITIVE + SQUARE_BRACKET_CLOSED;
                break;
            case ALL_TYPE:
                result = SQUARE_BRACKET_OPEN + INFINITIVE + COMMA + INFINITIVE + SQUARE_BRACKET_CLOSED  ;;
                break;
        }
        return result;
    }

    public T lowerBound() {
        return lowerBound;
    }

    public T upperBound() {
        return upperBound;
    }

}
