package by.online.pharmacy.entity;

public class ProductItem {


    private String engDescription;
    private String rusDescription;
    private String engName;
    private String rusName;
    private String rusManufacture;

    public String getEngCategory() {
        return engCategory;
    }

    public void setEngCategory(String engCategory) {
        this.engCategory = engCategory;
    }

    public String getRusCategory() {
        return rusCategory;
    }

    public void setRusCategory(String rusCategory) {
        this.rusCategory = rusCategory;
    }

    private String engManufacture;
    private String engCategory;
    private String rusCategory;



    public String getRusManufacture() {
        return rusManufacture;
    }

    public void setRusManufacture(String rusManufacture) {
        this.rusManufacture = rusManufacture;
    }

    public String getEngManufacture() {
        return engManufacture;
    }

    public void setEngManufacture(String engManufacture) {
        this.engManufacture = engManufacture;
    }

    public String getEngDescription() {
        return engDescription;
    }

    public void setEngDescription(String engDescription) {
        this.engDescription = engDescription;
    }

    public String getRusDescription() {
        return rusDescription;
    }

    public void setRusDescription(String rusDescription) {
        this.rusDescription = rusDescription;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }
}
