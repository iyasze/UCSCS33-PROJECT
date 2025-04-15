/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uscs33_project.component;

import uscs33_project.event.BackBtnPopUp;
import uscs33_project.model.ModelItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 *
 * @author amani
 */
public class PopUp extends javax.swing.JPanel {
    
    public void setDestroyEvent(BackBtnPopUp event) {
        this.eventDestroy = event;
    }
    
    private BackBtnPopUp eventDestroy;
    private ModelItem data;

    public PopUp() {
        initComponents();
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventDestroy.PopUpDestroy();
            }
        });
       
    }
    
    public void setData(ModelItem data) {
        this.data = data;
        lbBrand.setText(data.getBrandName());
        lbName.setText(data.getItemName());
        
        DecimalFormat df = new DecimalFormat("$#.00");
        lbPrice.setText(df.format(data.getPrice()));
        
        TextAreaDesc.setText(data.getDescription());
        
        image.setImage(data.getImage());
        
        
        
        if (data.getOptions().length != 0) {
            
        }
        else {
            PERMshade.setVisible(false);
            ComboBoxShade.setVisible(false);
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
        image = new uscs33_project.swing.PictureBox();
        lbBrand = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        PERMshade = new javax.swing.JLabel();
        PERMdesc = new javax.swing.JLabel();
        addToCart = new javax.swing.JButton();
        addToWishlist = new javax.swing.JButton();
        ComboBoxShade = new javax.swing.JComboBox<>();
        btnBack = new javax.swing.JButton();
        ScrollPaneDesc = new javax.swing.JScrollPane();
        TextAreaDesc = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 153));
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        image.setImage(new javax.swing.ImageIcon(getClass().getResource("/uscs33_project/image/elephant1.png"))); // NOI18N

        lbBrand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbBrand.setText("Brand");

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbName.setText("Name");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPrice.setText("Price");

        PERMshade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PERMshade.setText("Shade");

        PERMdesc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PERMdesc.setText("Description: ");

        addToCart.setText("Add to Cart");

        addToWishlist.setText("Add to Wishlist");
        addToWishlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToWishlistActionPerformed(evt);
            }
        });

        ComboBoxShade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBack.setText("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        TextAreaDesc.setColumns(20);
        TextAreaDesc.setLineWrap(true);
        TextAreaDesc.setRows(5);
        TextAreaDesc.setWrapStyleWord(true);
        ScrollPaneDesc.setViewportView(TextAreaDesc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addToCart)
                                .addGap(55, 55, 55)
                                .addComponent(addToWishlist))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbBrand)
                                .addGap(267, 267, 267)
                                .addComponent(btnBack))
                            .addComponent(lbName)
                            .addComponent(lbPrice)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(PERMshade)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBoxShade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PERMdesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPaneDesc)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbBrand)
                            .addComponent(btnBack))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPrice)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PERMshade)
                            .addComponent(ComboBoxShade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addToWishlist)
                            .addComponent(addToCart))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PERMdesc)
                    .addComponent(ScrollPaneDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToWishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToWishlistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addToWishlistActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxShade;
    private javax.swing.JLabel PERMdesc;
    private javax.swing.JLabel PERMshade;
    private javax.swing.JScrollPane ScrollPaneDesc;
    private javax.swing.JTextArea TextAreaDesc;
    private javax.swing.JButton addToCart;
    private javax.swing.JButton addToWishlist;
    private javax.swing.JButton btnBack;
    private uscs33_project.swing.PictureBox image;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBrand;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    // End of variables declaration//GEN-END:variables
}
