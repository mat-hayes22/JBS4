package mh.pos.test;


import org.junit.Assert;
import org.junit.Test;
import java.util.Collections;
import java.util.Map;

public class FindPriceInMemoryCataglogTest {

    @Test
    public void productFound() throws Exception {
        Price foundPrice = Price.cents(1250);
        Catalog catalog =  new InMemoryCatalog(Collections.singletonMap("12345", foundPrice));
        Assert.assertEquals(foundPrice  , catalog.findPrice("12345"));
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = new InMemoryCatalog(Collections.emptyMap());
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    public static class InMemoryCatalog implements Catalog {

        private final Map<String, Price> pricesByBarCode;

        public InMemoryCatalog(Map<String, Price> pricesByBarCode) {
            this.pricesByBarCode = pricesByBarCode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarCode.get(barcode);
        }


    }

}
