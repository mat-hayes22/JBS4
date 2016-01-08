package mh.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {

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

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = context.mock(Display.class);

        context.checking(new Expectations(){{
            oneOf(display).displayEmptyBarcodeMessage();
        }});

        SaleController saleController = new SaleController(null, display);
        saleController.onBarcode("");

    }

    public interface Display{
        void displayPrice(Price price);

        void displayProductNotFound(String barcode);

        void displayEmptyBarcodeMessage();
    }

    public static class SaleController{
        private Catalog catalog;
        private Display display;

        public SaleController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode) )
            {
                display.displayEmptyBarcodeMessage();
                return;
            }
            Price p = catalog.findPrice(barcode);
            if (p == null)
                display.displayProductNotFound(barcode);
            else
                display.displayPrice(p);

        }
    }

}

