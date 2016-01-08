package mh.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class sellOneItemControllerTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();


    @Test
    public void productFound() throws Exception {

       final Catalog catalog =  context.mock(Catalog.class);
       final Display display = context.mock(Display.class);
       final Price irrelevantPrice = Price.cents(795);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(irrelevantPrice));
            oneOf(display).displayPrice(with(irrelevantPrice));
        }});

        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("12345");
    }

    @Test
    public void productNotFound() throws Exception {
        final Catalog catalog =  context.mock(Catalog.class);
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::product not found::"));
            will(returnValue(null));
            oneOf(display).displayProductNotFound(with("::product not found::"));

        }});
        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("::product not found::");

    }

    public interface Catalog{
        Price findPrice(String price);
    }

    public interface Display{
        void displayPrice(Price price);

        void displayProductNotFound(String with);
    }

    public static class SaleController{
        private Catalog catalog;
        private Display display;

        public SaleController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            Price p = catalog.findPrice(barcode);
            if (p == null)
                display.displayProductNotFound(barcode);
            else
                display.displayPrice(p);

        }
    }

    public static class Price{
        public static Price cents(int centsValue){
            return new Price();
        }

        @Override
        public String toString() {
            return "a Price";
        }
    }

}

