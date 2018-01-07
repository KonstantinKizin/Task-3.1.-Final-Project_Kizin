package by.online.pharmacy.entity;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private Map<String,ProductItem> productItemMap = new HashMap<>();
    private int count;
    private float price;
    private float dosage;
    private byte[] image;
    private boolean prescription;
    private int id;

    public Product(){}

    public Product(int count, float price, float dosage, byte[] image, boolean prescription) {
        this.count = count;
        this.price = price;
        this.dosage = dosage;
        this.prescription = prescription;
        this.image = image;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }



    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, ProductItem> getProductItemMap(){
        return productItemMap;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Float.compare(product.price, price) != 0) return false;
        if (Float.compare(product.dosage, dosage) != 0) return false;
        if (prescription != product.prescription) return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        int result = (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (dosage != +0.0f ? Float.floatToIntBits(dosage) : 0);
        result = 31 * result + (prescription ? 1 : 0);
        result = 31 * result + id;
        return result;
    }


    @Override
    public String toString() {
        return "Product{" +
                "count=" + count +
                ", price=" + price +
                ", dosage=" + dosage +
                ", prescription=" + prescription +
                ", id=" + id +
                '}';
    }
}

