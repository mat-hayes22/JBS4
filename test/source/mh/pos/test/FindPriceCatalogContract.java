package mh.pos.test;

import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceCatalogContract {
    @Test
    public void productFound() throws Exception {
        Price foundPrice = new Price(1250);
        Catalog catalog = catalogWith("12345", foundPrice);
        Assert.assertEquals(foundPrice  , catalog.findPrice("12345"));
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = catalogWithout("12345");
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    protected abstract FindPriceInMemoryCataglogTest.InMemoryCatalog catalogWithout(String barcodeToAvoid);

    protected abstract FindPriceInMemoryCataglogTest.InMemoryCatalog catalogWith(String barcode, Price price);
}
