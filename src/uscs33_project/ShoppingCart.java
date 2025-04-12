/*iyas jahat
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uscs33_project;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author USER
 */

public class ShoppingCart extends javax.swing.JFrame {

    public ArrayList<ArrayList<String>> product = new ArrayList<ArrayList<String>>();
    public ArrayList<JSpinner> SpinnerList = new ArrayList<JSpinner>();
    public double SubTotal;
    private JLabel SubTotalLabel = new JLabel();
    
    public ShoppingCart(ArrayList<ArrayList<String>> product) {
        this.product = product; 
        this.QuantitySpinner = new ArrayList<>();
        initComponents();
        int y = 0;
        for (int i = 0; i < product.size();i++){
            addProductPanel(i,y);
            y += 150;
        }
      
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ItemLabel = new javax.swing.JLabel();
        PriceLabel = new javax.swing.JLabel();
        TotalLabel = new javax.swing.JLabel();
        QuantityLabel = new javax.swing.JLabel();
        ProductPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("cart");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setFocusCycleRoot(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        ItemLabel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ItemLabel.setText("Item");

        PriceLabel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        PriceLabel.setText("Price");

        TotalLabel.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        TotalLabel.setText("Total");

        QuantityLabel.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        QuantityLabel.setText("Quantity");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(ItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(QuantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(TotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        ProductPanel.setBackground(new java.awt.Color(239, 239, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("Tax (10%)");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("Subtotal");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("TOTAL");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setText("ORDER SUMMARY");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setText("Shipping Fee");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(45, 45, 45))
        );

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jButton1.setText("CHECKOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("PAYMENT METHOD");

        jRadioButton2.setText("jRadioButton1");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("jRadioButton1");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("jRadioButton1");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(140, 140, 140))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProductPanelLayout = new javax.swing.GroupLayout(ProductPanel);
        ProductPanel.setLayout(ProductPanelLayout);
        ProductPanelLayout.setHorizontalGroup(
            ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductPanelLayout.createSequentialGroup()
                .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ProductPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ProductPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        ProductPanelLayout.setVerticalGroup(
            ProductPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(375, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1060, 2813));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1056, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2809, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed
    public void refreshProductPanels() {
        jPanel2.removeAll();
        for (int i = 0; i < product.size(); i++) {
            addProductPanel(i, i * 150); // or whatever spacing you use
        }
        
        jPanel2.revalidate();
        jPanel2.repaint();
}       

      private void addProductPanel(int itemIndex,int y){
        
        JPanel itemPanel = new JPanel();
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createEtchedBorder());
        //itemPanel.setPreferredSize(new Dimension(300, 600));
        itemPanel.setLayout(null); // Use absolute layout for simplicity (or use GroupLayout if preferred)
        JLabel brandLabel = new JLabel(product.get(itemIndex).get(0));
        brandLabel.setBounds(50, 20, 300, 20);
        brandLabel.setFont(new Font("Verdana",Font.BOLD,12));
        //brandLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLabel nameLabel = new JLabel(product.get(itemIndex).get(1));
        nameLabel.setBounds(50, 45, 300, 20);
        nameLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        //nameLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLabel detailLabel = new JLabel(product.get(itemIndex).get(2));
        detailLabel.setBounds(50, 70, 300, 20);
        detailLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        //detailLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLabel priceLabel = new JLabel(product.get(itemIndex).get(3));
        priceLabel.setBounds(500, 20, 60, 20);
        priceLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        //priceLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLabel totalLabel = new JLabel(product.get(itemIndex).get(3));
        totalLabel.setBounds(850, 20, 60, 20);
        totalLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        //totalLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    
        SpinnerList.add(new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)));
        JSpinner spinner = SpinnerList.get(itemIndex);
        spinner.setBounds(680, 20, 60, 25);

    // Add change listener to update total
        spinner.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            int quantity = (int) spinner.getValue();
            double price = Double.parseDouble(product.get(itemIndex).get(3));
            double total = quantity * price;
            totalLabel.setText(String.format("%.2f", total));
            SubTotal = 0.0;
            for(int i = 0; i < product.size();i++){
                int quantitySub = (Integer) SpinnerList.get(i).getValue();
                double priceSub = Double.parseDouble(product.get(i).get(3));
                SubTotal += (priceSub*quantitySub);
            }
            SubTotalLabel.setText(String.format("%.2f",SubTotal));
            SubTotalLabel.revalidate();
            SubTotalLabel.repaint();

        }
    });
    itemPanel.setBounds(20, y, 1200, 140);

        // Create a label with the image
    /*JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uscs33_project/gloss 3.jpg")));
    imageLabel.setPreferredSize(new Dimension(300,300));*/

    JButton editButton = new JButton("Edit");
    editButton.setBounds(850,90,80,25);
    editButton.addActionListener(e ->{
        String newDetail = JOptionPane.showInputDialog(itemPanel,"Edit Details: ", detailLabel.getText());
        if (newDetail != null & !newDetail.trim().isEmpty()){
            detailLabel.setText(newDetail);
        }
   
    });
    
     
    JButton deleteButton = new JButton("Delete");
    deleteButton.setBounds(950,90,80,25);
    deleteButton.addActionListener(e -> {
        int result = JOptionPane.showConfirmDialog(ItemPanel, "Are you sure to delete the product?", "Delete Item",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
            jPanel2.remove(itemPanel);
            jPanel2.revalidate();
            jPanel2.repaint();
            int indexToRemove = (int) ((JButton) e.getSource()).getClientProperty("itemIndex");
            if (indexToRemove >= 0 && indexToRemove < product.size()) {
                product.remove(indexToRemove);
                SpinnerList.remove(indexToRemove); // Also remove from SpinnerList if needed
                
            }
            
        }
    });
    
    JLabel SubTotalLabel = new JLabel();
    SubTotalLabel.setBounds(200, 20, 60, 20);
    SubTotalLabel.setFont(new Font("Verdana",Font.PLAIN,12));
    SubTotalLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    
    if (itemIndex == product.size() - 1){
        SubTotal = 0.00;
        for(int i = 0; i < product.size();i++){
            int quantity = (Integer) SpinnerList.get(i).getValue();
            double price = Double.parseDouble(product.get(i).get(3));
            SubTotal += (price*quantity);
            SubTotalLabel.setText(String.format("%.2f",SubTotal));
        }
    }
    if (itemIndex == product.size() - 1) {
    SubTotalLabel.setBounds(200, 20, 60, 20);
    SubTotalLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
    SubTotalLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanel4.add(SubTotalLabel);
    }
 
    itemPanel.add(brandLabel);
    itemPanel.add(nameLabel);
    itemPanel.add(detailLabel);
    itemPanel.add(priceLabel);
    itemPanel.add(spinner);
    itemPanel.add(totalLabel);
    itemPanel.add(editButton);
    itemPanel.add(deleteButton);
    //ProductPanel.add(imageLabel);
    
    ProductPanel.revalidate();
    ProductPanel.repaint();
    
    
    jPanel2.add(itemPanel);
    jPanel2.revalidate();
    jPanel2.repaint();
    
}
      
    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShoppingCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShoppingCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShoppingCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShoppingCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrayList<ArrayList<String>> product = new ArrayList<ArrayList<String>>();
                ArrayList<String> item = new ArrayList<String>();
                item.add("Fenty Beauty");
                item.add("Gloss Bomb");
                item.add("Champ Color");
                item.add("79.00");
                product.add(new ArrayList<>(item));
                item.clear();
                item.add("Dior");
                item.add("Matte Blusher");
                item.add("Lovely Pink");
                item.add("59.00");
                product.add(new ArrayList<>(item));
                item.clear();
                item.add("Dior");
                item.add("Lipstick Glaze");
                item.add("Charmer Nude");
                item.add("69.00");
                product.add(new ArrayList<>(item));
                new ShoppingCart(product).setVisible(true);
                
            }
       });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel ItemLabel;
    public javax.swing.JLabel PriceLabel;
    public javax.swing.JPanel ProductPanel;
    public javax.swing.JLabel QuantityLabel;
    public javax.swing.JLabel TotalLabel;
    public javax.swing.JButton jButton1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JRadioButton jRadioButton2;
    public javax.swing.JRadioButton jRadioButton3;
    public javax.swing.JRadioButton jRadioButton4;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    public javax.swing.JLabel BrandText;
    public javax.swing.JButton DeleteButton;
    public javax.swing.JLabel Detailtext;
    public javax.swing.JButton EditButton;
    public javax.swing.JPanel ItemPanel;
    public javax.swing.JLabel NameText;
    public javax.swing.JLabel PriceText;
    public javax.swing.JLabel[] Totaltext;
    public ArrayList<javax.swing.JSpinner> QuantitySpinner;
    }
