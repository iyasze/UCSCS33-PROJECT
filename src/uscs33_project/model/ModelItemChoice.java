/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uscs33_project.model;

/**
 *
 * @author amani
 */
public class ModelItemChoice extends ModelItem{
    private int quantity;
    private String selectedVariant;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSelectedVariant() {
        return selectedVariant;
    }

    public void setSelectedVariant(String selectedVariant) {
        this.selectedVariant = selectedVariant;
    }
    
    public ModelItemChoice(ModelItem base, int quantity, String selectedVString) {
        super(base);
        this.quantity = quantity;
        this.selectedVariant = selectedVString;
    }
}
