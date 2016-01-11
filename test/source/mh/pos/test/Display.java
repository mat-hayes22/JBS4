package mh.pos.test;

public interface Display {
    void displayPrice(Price price);

    void displayProductNotFound(String barcode);

    void displayEmptyBarcodeMessage();
}
