/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uscs33_project.component;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Arrays;
/**
 *
 * @author USER
 */
public class WishList extends javax.swing.JFrame {

    /**
     * Creates new form WishList
     */
    public int count;
    
    public WishList() {
        initComponents();
        Username();
        
        
    }
    private void Username(){
        Path file = Paths.get("src/uscs33_project/component/REALTIME_CUSTOMER.txt");
        System.out.print("MAsukkkk");
        InputStream input = null;
        String username = null;
        try{
            input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            for (int i = 0;i < 2;i++){
                username = reader.readLine();
            }
            System.out.print("W USERNAME: " + username);
            
        }catch(IOException e){
            System.out.println(e);
        }
        FileExtraction(username);
        
    }
    
    private void FileExtraction(String username){
        System.out.print("Masuk");
        ArrayList<String> full = new ArrayList<String>();
        //ArrayList<String> each = new ArrayList<String>();
        Path file = Paths.get("src/uscs33_project/component/WishListInfo.txt");
        InputStream input = null;
        int x = 10, y = 10,i = 0;
        try{
            input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String wl = null;
            while(true){
                wl = reader.readLine();
                if (wl.equals(username)){
                while(true){
                    wl = reader.readLine();
                    if (!wl.equals("\\")){
                    String[] line = wl.split("\\|");
                    ArrayList<String> each = new ArrayList<>(Arrays.asList(line));
                    //System.out.println(each);
                    if (i == 3){
                        i = 0;
                        y += 370;
                        x = 10;
                    }
                    //System.out.println(each);
                    addWishPanel(each,x,y);
                    x +=  380;
                    i++;
                    }
                    else
                        break;
                }
                
                input.close();
            }
              
               
        }}
        catch (IOException e){
            System.out.print(e);
        }       
        
    }
    
    private void addWishPanel(ArrayList<String> data,int x, int y){
        count ++;
        JPanel ItemPanel = new JPanel();
        ItemPanel.setBounds(x,y,370, 348);
        ItemPanel.setBackground(new java.awt.Color(218,223,255));
        ItemPanel.setBorder(BorderFactory.createEtchedBorder());
        ItemPanel.setLayout(null);
        
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(115, 25, 131, 133);
        
        ImageIcon myimage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(data.get(3))));
        Image img1 = myimage.getImage();
        Image img2 = img1.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i = new ImageIcon(img2);
        
        JLabel BrandLabel = new JLabel();
        BrandLabel.setBounds(34, 180, 250, 20);
        BrandLabel.setFont(new Font("Verdana",Font.BOLD,12));
        BrandLabel.setText(data.get(1));
        
        JLabel NameLabel = new JLabel();
        NameLabel.setBounds(34, 210, 250, 20);
        NameLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        NameLabel.setText(data.get(0));
        
        JLabel ShadeLabel = new JLabel();
        ShadeLabel.setBounds(34, 240, 250, 20);
        ShadeLabel.setFont(new Font("Verdana",Font.PLAIN,12));
        ShadeLabel.setText(data.get(2));
        
        JButton CartButton = new JButton();
        CartButton.setBounds(80, 280, 250, 50);
        CartButton.setFont(new Font("Verdana",Font.BOLD,14));
        CartButton.setText("ADD TO CART");
        
        JButton deleteButton = new JButton("Remove");
        deleteButton.setBounds(280,25,80,25);
        deleteButton.addActionListener(e -> {
        int result = JOptionPane.showConfirmDialog(WishPanel, "Are you sure to delete the product?", "Delete Item",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION){
            WishPanel.remove(ItemPanel);
            WishPanel.revalidate();
            WishPanel.repaint();
            //int indexToRemove = (int) ((JButton) e.getSource()).getClientProperty("itemIndex");
            if (count >= 0 && count < data.size()) {
                data.remove(count);
                
                
            }
            
        }
    });
        
        
        
        imageLabel.setIcon(i);
        ItemPanel.add(imageLabel);
        ItemPanel.add(BrandLabel);
        ItemPanel.add(NameLabel);
        ItemPanel.add(ShadeLabel);
        ItemPanel.add(CartButton);
        ItemPanel.add(deleteButton);
        WishPanel.add(ItemPanel);
    }
    
    public JPanel getPanel(){
        return jPanel3;
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        WishPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1440, 900));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(1290, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 750));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1190, 750));

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("WISHLIST   ");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        WishPanel.setBackground(new java.awt.Color(241, 241, 255));
        WishPanel.setPreferredSize(new java.awt.Dimension(1188, 700));

        javax.swing.GroupLayout WishPanelLayout = new javax.swing.GroupLayout(WishPanel);
        WishPanel.setLayout(WishPanelLayout);
        WishPanelLayout.setHorizontalGroup(
            WishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1188, Short.MAX_VALUE)
        );
        WishPanelLayout.setVerticalGroup(
            WishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(WishPanel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(542, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(486, 486, 486))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(WishList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WishList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WishList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WishList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WishList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel WishPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
