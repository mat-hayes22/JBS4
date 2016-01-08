package mh.pos.test;

/**
 * Created by mat on 08/01/2016.
 */
public class Price {

    public static Price cents(int centsValue) {
        return new Price();
    }

    @Override
    public String toString() {
        return "a Price";
    }
}
