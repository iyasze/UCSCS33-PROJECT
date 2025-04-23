
package uscs33_project.component;

import uscs33_project.event.MouseDetector;
import uscs33_project.model.ModelItem;
import java.awt.AWTEvent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import uscs33_project.event.WishlistListenerFromProduct;
import uscs33_project.event.WishlistListenerFromWishlist;
import uscs33_project.event.addToCartBtnClicked;
import uscs33_project.model.ModelItemChoice;


public class Item extends javax.swing.JPanel {
    
    private boolean selected;
    private addToCartBtnClicked eventBuy;
    private WishlistListenerFromWishlist eventUpdate2;
    private WishlistListenerFromProduct eventUpdate1;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }
    
    public String getWishlistButtonText() {
        return btnWishlist.getText();
    }
    
    public void setWishlistButtonText(String heartColor) {
        btnWishlist.setText(heartColor);
    }

    public Item(addToCartBtnClicked listener, WishlistListenerFromProduct event) {
        
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eventBuy = listener;
        this.eventUpdate1 = event;
        
        
        layerCart.setVisible(false);
        
        Toolkit.getDefaultToolkit().addAWTEventListener(new MouseDetector(this, layerCart), AWTEvent.MOUSE_EVENT_MASK);
        
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = 1;
                if (quantity < data.getItemStock()) {
                    
                    String selectedVariant;
                    if (data.getOptions().length != 0) {
                        selectedVariant = (String) data.getOptions()[0];
                    }
                    else {
                        selectedVariant = "";
                    }
                    
                    ModelItemChoice itemBought = new ModelItemChoice(data, quantity, selectedVariant);
                
                    eventBuy.buy(itemBought);
                }
            }
        });
    }
    
    public Item(addToCartBtnClicked listener, WishlistListenerFromWishlist event) {
        
        initComponents();
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.eventBuy = listener;
        this.eventUpdate2 = event;
        
        layerCart.setVisible(false);
        
        Toolkit.getDefaultToolkit().addAWTEventListener(new MouseDetector(this, layerCart), AWTEvent.MOUSE_EVENT_MASK);
        
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = 1;
                if (quantity < data.getItemStock()) {
                    
                    String selectedVariant;
                    if (data.getOptions().length != 0) {
                        selectedVariant = (String) data.getOptions()[0];
                    }
                    else {
                        selectedVariant = "";
                    }
                    
                    ModelItemChoice itemBought = new ModelItemChoice(data, quantity, selectedVariant);
                
                    eventBuy.buy(itemBought);
                }
            }
        });
    }
    
    private ModelItem data;
    
    public ModelItem getData() {
        return data;
    }
    
    public void setData(ModelItem data) {
        
        this.data = data;
        
        int labelWidth = 150;
        img.setImage(data.getImage());
        lbItemName.setText("<html><body style='width: " + labelWidth + "px'>" + data.getItemName() + "</body></html>");
        lbBrand.setText("<html><body style='width: " + labelWidth + "px'>" + data.getBrandName() + "</body></html>");
        DecimalFormat df = new DecimalFormat("RM#,###.00");
        lbPrice.setText(df.format(data.getPrice()));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(242,242,242));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (selected) {
            g2.setColor(new Color(94, 156, 255));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        }
        g2.dispose();
        super.paint(g);
    }
    
    public void addToCart() {
        
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        img = new uscs33_project.swing.PictureBox();
        layerCart = new javax.swing.JLayeredPane();
        btnAddToCart = new javax.swing.JButton();
        btnWishlist = new javax.swing.JButton();
        lbBrand = new javax.swing.JLabel();
        lbItemName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();

        img.setImage(new javax.swing.ImageIcon(getClass().getResource("/uscs33_project/image/makeup1.png"))); // NOI18N
        img.setPreferredSize(new java.awt.Dimension(228, 104));
        img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgMouseExited(evt);
            }
        });

        layerCart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        layerCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                layerCartMouseEntered(evt);
            }
        });

        btnAddToCart.setText("Add to Cart");
        btnAddToCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddToCartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddToCartMouseExited(evt);
            }
        });
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnWishlist.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnWishlist.setText("♡");
        btnWishlist.setBorderPainted(false);
        btnWishlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnWishlistMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnWishlistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnWishlistMouseExited(evt);
            }
        });
        btnWishlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWishlistActionPerformed(evt);
            }
        });

        layerCart.setLayer(btnAddToCart, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerCart.setLayer(btnWishlist, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layerCartLayout = new javax.swing.GroupLayout(layerCart);
        layerCart.setLayout(layerCartLayout);
        layerCartLayout.setHorizontalGroup(
            layerCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddToCart, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layerCartLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layerCartLayout.setVerticalGroup(
            layerCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerCartLayout.createSequentialGroup()
                .addComponent(btnWishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        img.setLayer(layerCart, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout imgLayout = new javax.swing.GroupLayout(img);
        img.setLayout(imgLayout);
        imgLayout.setHorizontalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layerCart)
                .addContainerGap())
        );
        imgLayout.setVerticalGroup(
            imgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layerCart)
                .addContainerGap())
        );

        lbBrand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbBrand.setForeground(new java.awt.Color(76, 76, 76));
        lbBrand.setText("Brand");

        lbItemName.setText("Item Name");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbPrice.setText("$0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbItemName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbBrand, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(img, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBrand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPrice)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMouseExited

    }//GEN-LAST:event_imgMouseExited

    private void imgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_imgMouseEntered

    private void btnWishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWishlistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnWishlistActionPerformed

    private void btnWishlistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWishlistMouseExited

    }//GEN-LAST:event_btnWishlistMouseExited

    private void btnWishlistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWishlistMouseEntered

    }//GEN-LAST:event_btnWishlistMouseEntered

    private void btnWishlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnWishlistMouseClicked
        // TODO add your handling code here:
        if (btnWishlist.getText().equals("♡")) {
            btnWishlist.setText("♥");
            if (this.eventUpdate1 != null) {
                this.eventUpdate1.AddRemoveFromWishlist1("add", data.getItemID());
            }
        }
        else {
            btnWishlist.setText("♡");
            if (this.eventUpdate2 != null) {
                this.eventUpdate2.AddRemoveFromWishlist2("remove", data.getItemID());
            }
        }
        System.out.println(btnWishlist.getText());
        repaint();
    }//GEN-LAST:event_btnWishlistMouseClicked

    private void layerCartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_layerCartMouseEntered
        // TODO add your handling code here:
        java.awt.Point p = new java.awt.Point(evt.getLocationOnScreen());
        SwingUtilities.convertPointFromScreen(p, evt.getComponent());
        if (evt.getComponent().contains(p)) {return;}
        layerCart.setVisible(true);
    }//GEN-LAST:event_layerCartMouseEntered

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnAddToCartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddToCartMouseExited
        // TODO add your handling code here:
        //        layerCart.setVisible(false);
        //        btnAddToCart.setVisible(false);
        //        layerWishlist.setVisible(false);
        //        btnWishlist.setVisible(false);
    }//GEN-LAST:event_btnAddToCartMouseExited

    private void btnAddToCartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddToCartMouseEntered
        //        layerCart.setVisible(true);
        //        btnAddToCart.setVisible(true);
        //        layerWishlist.setVisible(true);
        //        btnWishlist.setVisible(true);
    }//GEN-LAST:event_btnAddToCartMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnWishlist;
    private uscs33_project.swing.PictureBox img;
    private javax.swing.JLayeredPane layerCart;
    private javax.swing.JLabel lbBrand;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    // End of variables declaration//GEN-END:variables
}
