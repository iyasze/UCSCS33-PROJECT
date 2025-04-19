/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uscs33_project.form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import uscs33_project.component.BrowseFilter;
import uscs33_project.component.LogInPage;
import uscs33_project.component.ShoppingCart;
import uscs33_project.component.SignUpPage;
import uscs33_project.event.EventItem;
import uscs33_project.event.addToCartBtnClicked;
import uscs33_project.model.ModelItem;
import uscs33_project.model.ModelItemChoice;

/**
 *
 * @author amani
 */
public class StoreInterface extends javax.swing.JPanel implements addToCartBtnClicked {

    /**
     * Creates new form StoreInterface
     */
    
    private CardLayout cardLayout;
    private ArrayList<String> userInfo;
    private FormHome menu;
    private ShoppingCart cart;
        
    public StoreInterface() {
        
        initComponents();
        paintBg();
        setImage();
        
        ArrayList<ArrayList<String>> product = new ArrayList<ArrayList<String>>();
        cart = new ShoppingCart(product);
        menu = new FormHome(this);
        
        importData();
        
        menu.setClickEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                menu.setSelected(com);
                System.out.println(item.getItemID());
                menu.createPopup(item);
            }
        });
   
        JPanel cartPanel = cart.getPanel();
        JPanel storePanel = menu;

        menuPanel.add(storePanel, "STORE");
        menuPanel.add(cartPanel, "CART");
        
        BrowseFilter filter = new BrowseFilter();
        leftPanel.add(filter, "FILTER");
    }
   
    public JPanel getUpperPanel() {
        return upperPanel;
    }
    
    @Override
    public void buy(ModelItemChoice item) {
        System.out.println(item.getQuantity());
    }
    
    private void importData() {
        int ID = 1;
        Path file = Paths.get("src/uscs33_project/main/products.txt").toAbsolutePath();
        System.out.println(file);
        try {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = reader.readLine();
            
            while(s != null) {
                String[] parts = s.split("\\|");
                
                String imgLink = "/uscs33_project/image/" + parts[3];
                
                // File array order
                // 0 = Item Name, 
                // 1 = Item Brand, 
                // 2 = Item Price, 
                // 3 = Image file name, 
                // 4 = Choices
                // 5 = Desc
                String[] choices = {};
                
                if (!parts[4].equals("")) {
                    choices = parts[4].split(",");
                }
//                System.out.println("Arr len: " + choices.length);
                
                System.out.println(imgLink);
                ModelItem itemTemp = new ModelItem(ID++, parts[0], parts[1], Double.parseDouble(parts[2]), new ImageIcon(getClass().getResource(imgLink)), choices, parts[5]);
                menu.addItem(itemTemp);
                s = reader.readLine();
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
//        home.addItem(new ModelItem(ID++, "4DFWD PULSE", "Desc Sampel", 120.88, "The Ordinary", new ImageIcon(getClass().getResource("/com/raven/image/ordinary1.png"))));
//        home.addItem(new ModelItem(ID++, "4DFWD PULSE", "Desc Sampel", 120.88, "Drunk Elephant", new ImageIcon(getClass().getResource("/com/raven/image/elephant1.png"))));
//        home.addItem(new ModelItem(ID++, "4DFWD PULSE", "Desc Sampel", 120.88, "Blush?", new ImageIcon(getClass().getResource("/com/raven/image/makeup1.png"))));
    }
    
    private void setImage() {
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_SEARCHBUTTON.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_CARTICON.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHICON.png"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_PANELDECO .png"));
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_PROFILE PIC.png"));
        
//        jLabel2.setIcon(icon4);
        searchButton.setIcon(icon1);
        cartButton.setIcon(icon2);
        wishlistButton.setIcon(icon3);      
        userIcon.setIcon(icon5);
        cardLayout = new CardLayout();
        menuPanel.setLayout(cardLayout); // ✅ Set layout instead of overwriting the panel
    }
    
    private void paintBg() {
        // 1. First get references to all components and their constraints
        Map<Component, GridBagConstraints> componentsMap = new HashMap<>();
        GridBagLayout gbl = (GridBagLayout) upperPanel.getLayout();

        // Store all components and constraints
        for (Component comp : upperPanel.getComponents()) {
            componentsMap.put(comp, gbl.getConstraints(comp));
        }
        
        // 2. Create a new panel with background image support
        JPanel newUpperPanel = new JPanel(new GridBagLayout()) {
            private Image backgroundImage;
    
            {
                // Load the image
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/uscs33_project/image/MAIN_PANELDECO .png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    int imgWidth = backgroundImage.getWidth(this);
                    int imgHeight = backgroundImage.getHeight(this);
        
                    if (imgWidth > 0 && imgHeight > 0) {
                        double imgRatio = (double) imgWidth / imgHeight;
                        double panelRatio = (double) getWidth() / getHeight();
            
                        int drawWidth, drawHeight;
                        int x = 0, y = 0;
            
                        // Modified logic to prioritize width coverage
                        if (panelRatio >= imgRatio) {
                            // When panel is wider than image (relative to height)
                            // OR when equal aspect ratios
                            drawWidth = getWidth(); // Always match panel width
                            drawHeight = (int) (getWidth() / imgRatio);
                            y = (getHeight() - drawHeight) / 2; // Center vertically
                        } else {
                            // When panel is taller than image (relative to width)
                            drawWidth = getWidth(); // Still match panel width first
                            drawHeight = (int) (getWidth() / imgRatio);
                
                            // If this doesn't cover height, then scale to height
                            if (drawHeight < getHeight()) {
                                drawHeight = getHeight();
                                drawWidth = (int) (getHeight() * imgRatio);
                                x = (getWidth() - drawWidth) / 2; // Center horizontally
                            } else {
                                y = (getHeight() - drawHeight) / 2; // Center vertically
                            }
                        }
            
                        // High-quality rendering
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                               RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        g2d.drawImage(backgroundImage, x, y, drawWidth, drawHeight, this);
                    }
                }
            }
        };
        
        // 3. Make the panel non-opaque to show background
        newUpperPanel.setOpaque(false);

        // 4. Transfer all components with their original constraints
        for (Map.Entry<Component, GridBagConstraints> entry : componentsMap.entrySet()) {
            Component comp = entry.getKey();
            GridBagConstraints gbc = entry.getValue();
    
            // Make each component non-opaque (transparent)
            if (comp instanceof JComponent) {
                ((JComponent) comp).setOpaque(false);
            }
    
            newUpperPanel.add(comp, gbc);
        }
        
        // 5. Replace the old panel with the new one
        this.remove(upperPanel);
        this.add(newUpperPanel, BorderLayout.NORTH);
        upperPanel = newUpperPanel; // Update reference if needed

        // 6. Refresh the UI
        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        upperPanel = new javax.swing.JPanel();
        TITLE = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usernameDisplay = new javax.swing.JLabel();
        cartwishPanel = new javax.swing.JPanel();
        wishlistButton = new javax.swing.JButton();
        cartButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        leftPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        upperPanel.setBackground(new java.awt.Color(204, 204, 255));
        upperPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        upperPanel.setPreferredSize(new java.awt.Dimension(1440, 150));
        upperPanel.setLayout(new java.awt.GridBagLayout());

        TITLE.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        TITLE.setFont(new java.awt.Font("ITF Devanagari Marathi", 0, 50)); // NOI18N
        TITLE.setText(" M  A  K  L  U  V");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 4);
        upperPanel.add(TITLE, gridBagConstraints);

        jPanel1.setOpaque(false);

        userIcon.setBackground(new java.awt.Color(204, 204, 255));
        userIcon.setForeground(new java.awt.Color(204, 204, 255));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel5.setText("SIGN UP");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel1.setText("LOG IN");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        usernameDisplay.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        usernameDisplay.setText("GUEST");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usernameDisplay)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        upperPanel.add(jPanel1, gridBagConstraints);

        cartwishPanel.setBackground(new java.awt.Color(204, 204, 255));
        cartwishPanel.setOpaque(false);

        wishlistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wishlistButtonActionPerformed(evt);
            }
        });

        cartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("    CART        WISHLIST");

        javax.swing.GroupLayout cartwishPanelLayout = new javax.swing.GroupLayout(cartwishPanel);
        cartwishPanel.setLayout(cartwishPanelLayout);
        cartwishPanelLayout.setHorizontalGroup(
            cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartwishPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(cartwishPanelLayout.createSequentialGroup()
                        .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(wishlistButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cartwishPanelLayout.setVerticalGroup(
            cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartwishPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wishlistButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        upperPanel.add(cartwishPanel, gridBagConstraints);

        searchPanel.setBackground(new java.awt.Color(204, 204, 255));
        searchPanel.setOpaque(false);

        searchButton.setBorderPainted(false);
        searchButton.setMaximumSize(new java.awt.Dimension(72, 52));
        searchButton.setMinimumSize(new java.awt.Dimension(72, 52));
        searchButton.setOpaque(true);
        searchButton.setPreferredSize(new java.awt.Dimension(72, 50));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchField.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchField)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 13);
        upperPanel.add(searchPanel, gridBagConstraints);

        jLabel7.setText("C O S M E T I C S  &  B E A U T Y");
        jLabel7.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 96, 3, 3);
        upperPanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 3;
        upperPanel.add(jLabel2, gridBagConstraints);

        add(upperPanel, java.awt.BorderLayout.NORTH);

        leftPanel.setBackground(new java.awt.Color(204, 204, 255));
        leftPanel.setPreferredSize(new java.awt.Dimension(170, 600));
        leftPanel.setLayout(new java.awt.CardLayout());
        add(leftPanel, java.awt.BorderLayout.WEST);

        menuPanel.setPreferredSize(new java.awt.Dimension(1190, 600));
        menuPanel.setLayout(new java.awt.CardLayout());
        add(menuPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void wishlistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wishlistButtonActionPerformed

    }//GEN-LAST:event_wishlistButtonActionPerformed

    private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartButtonActionPerformed
        cardLayout.show(menuPanel, "CART");
    }//GEN-LAST:event_cartButtonActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        try{
            File file = new File("src/uscs33_project/component/REALTIME_CUSTOMER.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("null@gmail.com\n");
            writer.write("GUEST\n");
            writer.write("-\n");
            writer.write("-\n");
            writer.write("-\n");
            writer.write("-\n");
            writer.flush();
            writer.close();
        }
        catch(Exception e){
            System.out.println("Message: " + e);
        }

        LogInPage login = new LogInPage();

        login.setVisible(true);
        login.setLocationRelativeTo(null);

        this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        try{
            File file = new File("src/uscs33_project/component/REALTIME_CUSTOMER.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("null@gmail.com\n");
            writer.write("GUEST\n");
            writer.write("-\n");
            writer.write("-\n");
            writer.write("-\n");
            writer.write("-\n");
            writer.flush();
            writer.close();
        }
        catch(Exception e){
            System.out.println("Message: " + e);
        }

        SignUpPage signup = new SignUpPage();

        signup.setVisible(true);
        signup.setLocationRelativeTo(null);

        this.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TITLE;
    private javax.swing.JButton cartButton;
    private javax.swing.JPanel cartwishPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel usernameDisplay;
    private javax.swing.JButton wishlistButton;
    // End of variables declaration//GEN-END:variables
}
