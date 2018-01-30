package by.online.pharmacy.entity;

public class ProductItem {


private String description;
private String manufacture;
private String name;
private String CategoryName;



public ProductItem(){}

public ProductItem(String description, String manufacture, String name, String categoryName) {
        this.description = description;
        this.manufacture = manufacture;
        this.name = name;
        CategoryName = categoryName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }


    public String getShortDescription(){

    return this.description.length() > 100
            ? this.description.substring(0,100)+" ..." : description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductItem that = (ProductItem) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (manufacture != null ? !manufacture.equals(that.manufacture) : that.manufacture != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return CategoryName != null ? CategoryName.equals(that.CategoryName) : that.CategoryName == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (manufacture != null ? manufacture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (CategoryName != null ? CategoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "description='" + description + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", name='" + name + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }
}
