package mh.pos.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCataglogTest extends FindPriceCatalogContract {

    @Override
    protected InMemoryCatalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(Collections.singletonMap(
                            "anything but " + barcodeToAvoid, Price.cents(0)));
    }

    @Override
    protected InMemoryCatalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(new HashMap<String, Price>()
        {{
            put("definitely not " + barcode, Price.cents(0));
            put(barcode, price);
            put("again definitely not " + barcode, Price.cents(1000000));
        }});
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
