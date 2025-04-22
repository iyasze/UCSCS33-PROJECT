package uscs33_project.model;

import javax.swing.Icon;


public class ModelItem {
    
    String itemID;
    int itemStock;
    String itemName;
    String brandName;
    double price;
    Icon image;
    String[] options;
    String category;
    String description;
    
    public ModelItem() {
    }

    public ModelItem(String itemID, int stock, String itemName, String brandName, double price, Icon image, String[] options, String category, String description) {
        this.itemID = itemID;
        this.itemStock = stock;
        this.itemName = itemName;
        this.brandName = brandName;
        this.price = price;
        this.image = image;
        if (options.length == 0) {
            this.options = new String[0];
        }
        else {
            this.options = options;
        }
        this.category = category;
        this.description = description;
    }
    
    public ModelItem(ModelItem base) {
        this.itemID = base.getItemID();
        this.itemStock = base.getItemStock();
        this.itemName = base.getItemName();
        this.brandName = base.getBrandName();
        this.price = base.getPrice();
        this.image = base.getImage();
        if (base.getOptions().length == 0) {
            this.options = new String[0];
        }
        else {
            this.options = base.getOptions();
        }
        this.category = base.getCategory();
        this.description = base.getDescription();
        
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
    
    public int getItemStock() {
        return itemStock;
    }

    public void setItemStock(int itemStock) {
        this.itemStock = itemStock;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] option) {
        this.options = option;
    }
}
