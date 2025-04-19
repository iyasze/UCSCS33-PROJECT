package uscs33_project.model;

import javax.swing.Icon;


public class ModelItem {

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
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

    public ModelItem(int itemID, String itemName, String brandName, double price, Icon image, String[] options, String description) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.brandName = brandName;
        if (options.length == 0) {
            this.options = new String[0];
        }
        else {
            this.options = options;
        }
        this.image = image;
    }
    
    public ModelItem(ModelItem base) {
        this.itemID = base.getItemID();
        this.itemName = base.getItemName();
        this.description = base.getDescription();
        this.price = base.getPrice();
        this.brandName = base.getBrandName();
        if (base.getOptions().length == 0) {
            this.options = new String[0];
        }
        else {
            this.options = base.getOptions();
        }
        this.image = base.getImage();
    }
    
    

    public ModelItem() {
    }
    
  
    int itemID;
    String itemName;
    String description;
    double price;
    String brandName;
    Icon image;
    String[] options;

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] option) {
        this.options = option;
    }
}
