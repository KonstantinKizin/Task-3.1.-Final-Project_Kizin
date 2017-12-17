package by.online.pharmacy.entity;

import java.io.File;

public class Product {

    private ProductItem productItem;
    private int count;
    private float price;
    private Category category;
    private float dosage;
    private File image;
    private boolean prescription;
    private int id;


    public Product(ProductItem productItem, int count, float price, Category category, float dosage, File image, boolean prescription) {
        this.productItem = productItem;
        this.count = count;
        this.price = price;
        this.category = category;
        this.dosage = dosage;
        this.image = image;
        this.prescription = prescription;
    }

    public Product(){};

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (count != product.count) return false;
        if (Float.compare(product.price, price) != 0) return false;
        if (Float.compare(product.dosage, dosage) != 0) return false;
        if (prescription != product.prescription) return false;
        if (id != product.id) return false;
        if (productItem != null ? !productItem.equals(product.productItem) : product.productItem != null) return false;
        return category != null ? category.equals(product.category) : product.category == null;
    }

    @Override
    public int hashCode() {
        int result = productItem != null ? productItem.hashCode() : 0;
        result = 31 * result + count;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (dosage != +0.0f ? Float.floatToIntBits(dosage) : 0);
        result = 31 * result + (prescription ? 1 : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productItem=name" + productItem.getEngName() +
                ",manufacture= "+productItem.getEngManufacture()+
                ", count=" + count +
                ", price=" + price +
                ", category=" + category +
                ", dosage=" + dosage +
                ", image=" + image.getAbsolutePath() +
                ", prescription=" + prescription +
                ", id=" + id +
                '}';
    }
}

