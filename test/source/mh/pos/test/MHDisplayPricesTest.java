package mh.pos.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MHDisplayPricesTest {



/*    @Test
    public void noProductFound() throws Exception {
        TestDisplay display = new TestDisplay();
        String unfoundBarcode = "12345";
        display.displayProductNotFound(unfoundBarcode);
        Assert.assertEquals("Product not found " + unfoundBarcode, display.message());
    }

    @Test
    public void productFound() throws Exception {
        TestDisplay display = new TestDisplay();
        display.displayPrice(new Price(295));
        Assert.assertEquals("Total: $2.95", display.message());
    }

    @Test
    public void emptyBarcode() throws Exception {
        TestDisplay display = new TestDisplay();
        display.displayEmptyBarcodeMessage();
        Assert.assertEquals("No barcode found", display.message());

    }

    private class TestDisplay implements Display {

        String message = null;

        private String formatMonateryAmount(int priceInCents) {
            return String.format("$%,.2f" , priceInCents / 100.0d);
        }


        @Override
        public void displayPrice(Price price) {
            message =  "Total: " + formatMonateryAmount(price.getCentsValue());
        }

        @Override
        public void displayProductNotFound(String barcode) {
            message = "Product not found " + barcode;
        }

        @Override
        public void displayEmptyBarcodeMessage() {
            message = "No barcode found";
        }

        public String message() {
            return message;
        }
    }*/
}
