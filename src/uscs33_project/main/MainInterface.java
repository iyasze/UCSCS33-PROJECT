/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uscs33_project.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import uscs33_project.main.StoreFront;
import uscs33_project.form.ShoppingCart;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.nio.*;
import uscs33_project.component.BrowseFilter;
import uscs33_project.component.BrowseFilterDisabled;
import uscs33_project.component.LogInPage;
import uscs33_project.form.ShoppingCart;
import uscs33_project.component.SignUpPage;
import uscs33_project.component.WishList;
import uscs33_project.component.promoBanner;
import uscs33_project.event.ImageUtils;
/**
 *
 * @author iyasnaufalnazlim
 */
public class MainInterface extends javax.swing.JFrame {

    /**
     * Creates new form MainInterface
     */
    
    ImageIcon icon1 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_SEARCHBUTTON.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_CARTICON.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHICON.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_PANELDECO .png"));
    ImageIcon icon5 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_PROFILE PIC.png"));
    ImageIcon icon6 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHGUEST.png"));
    ImageIcon icon7 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHENTERED.png"));
    ImageIcon icon8 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_CARTENTERED.png"));
    
   
    

    
    private CardLayout cardLayout;
    private CardLayout cardLayout2;
    private ArrayList<String> userInfo;
    
    public int cardPage = 0;
    
    public MainInterface() {
        initComponents();
        
        wishDisabled();
        icon2 = ImageUtils.getCircularIcon(icon2, 55);
        icon3 = ImageUtils.getCircularIcon(icon3, 55);
                
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        
        promoBanner promo = new promoBanner();
        searchPanel.add(promo,"Center");
        
        jLabel2.setIcon(icon4);
        cartIcon.setIcon(icon2);
        wishIcon.setIcon(icon3);      
        userIcon.setIcon(icon5);
        jLabel3.setText("CART");
        jLabel4.setText("WISHLIST");
        
        
        userInfo = new ArrayList<>();
        
        //THIS IS FOR TESTING CART ONLY
        
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
        
        //THIS IS FOR TESTING CART ONLY
        
        
        loadUser();
        displayUser();
        
        cardLayout = new CardLayout();
        cardLayout2 = new CardLayout();
                
        menuPanel.setLayout(cardLayout); 
        leftPanel.setLayout(cardLayout2);
        
        StoreFront storeFront = new StoreFront();
        ShoppingCart cart = new ShoppingCart(product);
        WishList wishlist = new WishList();

        JPanel cartPanel = cart.getPanel();
        JPanel storePanel = storeFront.getPanel();
        JPanel wishPanel = wishlist.getPanel();
        
        
        
        menuPanel.add(storePanel, "STORE");
        menuPanel.add(cartPanel, "CART");
        menuPanel.add(wishPanel, "WISHLIST");
        
        
        BrowseFilter filter = new BrowseFilter();
        
        //PANEL WITH OVERLAY
        JPanel overlayedFilterPanel = new JPanel();
        overlayedFilterPanel.setOpaque(false);
        overlayedFilterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     
                        
        leftPanel.add(filter, "FILTER");
        leftPanel.add(overlayedFilterPanel, "DISABLE");

        
        
        
        
        
    }    
    
    private void wishDisabled(){
        icon6 = ImageUtils.getCircularIcon(icon6, 55);
        
        if(usernameDisplay.getText().equals("GUEST")){
            wishIcon.setIcon(icon6);
            wishIcon.setEnabled(false);
        }
    }
    
    private void loadUser(){
        userInfo.clear();
        
        try{
            
            userInfo.clear();
            
            BufferedReader reader = new BufferedReader(new FileReader("src/uscs33_project/component/REALTIME_CUSTOMER.txt"));
            int i = 0;
            String line;
            while((line = reader.readLine()) != null){
                userInfo.add(line);                
                i++;
            }
            
            System.out.println("STORING USER DATA");
            
            
            
            reader.close();
            
            System.out.println("ARRAY CONTENT " + userInfo);
            
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Message 1: " + e);
        }
        catch(Exception e){
            System.out.println("Message 2: " + e);
        }
        
        
        displayUser();
    }
    
    private void displayUser(){  
        System.out.println("USERNAME TO SET: " + userInfo.get(1));       
        usernameDisplay.setText(userInfo.get(1));
        
        if(!(usernameDisplay.getText().equals("GUEST"))){
            System.out.println("LOG IN CHANGE TO LOG OUT");
            System.out.println("SIGN UP DISABLED");
            jLabel1.setText("LOG OUT");
            jLabel5.setText("");
            jLabel5.setEnabled(false);
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

        upperPanel = new javax.swing.JPanel();
        TITLE = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        profilePanel = new javax.swing.JPanel();
        usernameDisplay = new javax.swing.JLabel();
        userIcon = new javax.swing.JLabel();
        cartwishPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cartIcon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        wishIcon = new javax.swing.JLabel();
        loginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        leftPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        upperPanel.setBackground(new java.awt.Color(204, 204, 255));
        upperPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        upperPanel.setPreferredSize(new java.awt.Dimension(1440, 150));

        TITLE.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        TITLE.setFont(new java.awt.Font("ITF Devanagari Marathi", 0, 50)); // NOI18N
        TITLE.setText(" M  A  K  L  U  V");

        searchPanel.setBackground(new java.awt.Color(204, 204, 255));
        searchPanel.setOpaque(false);
        searchPanel.setLayout(new java.awt.BorderLayout());

        profilePanel.setBackground(new java.awt.Color(204, 204, 255));
        profilePanel.setOpaque(false);

        usernameDisplay.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N

        userIcon.setBackground(new java.awt.Color(204, 204, 255));
        userIcon.setForeground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(userIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cartwishPanel.setBackground(new java.awt.Color(204, 204, 255));
        cartwishPanel.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(55, 55));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });

        cartIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cartIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cartIconMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(55, 55));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });

        wishIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wishIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wishIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                wishIconMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wishIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wishIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cartwishPanelLayout = new javax.swing.GroupLayout(cartwishPanel);
        cartwishPanel.setLayout(cartwishPanelLayout);
        cartwishPanelLayout.setHorizontalGroup(
            cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartwishPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        cartwishPanelLayout.setVerticalGroup(
            cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartwishPanelLayout.createSequentialGroup()
                .addGroup(cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loginPanel.setOpaque(false);
        loginPanel.setPreferredSize(new java.awt.Dimension(100, 30));
        loginPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginPanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel1.setText("LOG IN");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel5.setText("SIGN UP");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel3.setText(" CART");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setText("WISHLIST");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("C O S M E T I C S  &  B E A U T Y");
        jLabel7.setToolTipText("");

        javax.swing.GroupLayout upperPanelLayout = new javax.swing.GroupLayout(upperPanel);
        upperPanel.setLayout(upperPanelLayout);
        upperPanelLayout.setHorizontalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(TITLE, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(818, 818, 818))
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addComponent(cartwishPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                    .addContainerGap(689, Short.MAX_VALUE)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(751, Short.MAX_VALUE)))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                    .addContainerGap(1007, Short.MAX_VALUE)
                    .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(208, Short.MAX_VALUE)))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                    .addContainerGap(1220, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE)))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        upperPanelLayout.setVerticalGroup(
            upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(cartwishPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(upperPanelLayout.createSequentialGroup()
                        .addComponent(TITLE, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)))
                .addGap(20, 20, 20))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                    .addContainerGap(75, Short.MAX_VALUE)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(44, Short.MAX_VALUE)))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
                    .addContainerGap(103, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
            .addGroup(upperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        leftPanel.setBackground(new java.awt.Color(204, 204, 255));
        leftPanel.setPreferredSize(new java.awt.Dimension(250, 750));
        leftPanel.setLayout(new java.awt.CardLayout());

        menuPanel.setPreferredSize(new java.awt.Dimension(1190, 750));
        menuPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(upperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1442, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginPanelMouseClicked
        if(jLabel1.getText().equals("LOG IN")){
            
        }        
    }//GEN-LAST:event_loginPanelMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        
        //TESTING POP UP AND BLOCK BACKGROUND ACTIVITIES               
        if(jLabel1.getText().equals("LOG OUT")){
            System.out.println("ATTEMPT POP UP!!!");
            setupPopup();
        }
        else{
            backtoLogIn();
        }
        
        //TESTING POP UP AND BLOCK BACKGROUND ACTIVITIES
        
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        
        try{
                
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/uscs33_project/component/REALTIME_CUSTOMER.txt")));
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
       
       userInfo.clear();
       
       signup.setVisible(true);
       signup.setLocationRelativeTo(null);
       
       this.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void wishIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishIconMouseClicked
       if(jLabel4.getText().equals("WISHLIST")){
           cardPage = 1;
           cardLayout.show(menuPanel, "WISHLIST");
           jLabel4.setText("RETURN");
           changeLeftPanel();
           
       }
       else{
           cardPage = 0;
           
           cardLayout.show(menuPanel, "STORE");
           jLabel4.setText("WISHLIST");
           changeLeftPanel();
       }
    }//GEN-LAST:event_wishIconMouseClicked

    private void cartIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseClicked
        if(jLabel3.getText().equals("CART")){
            cardLayout.show(menuPanel, "CART");
            jLabel3.setText("RETURN");
            cardPage = 1;
            changeLeftPanel();
            if(jLabel4.getText().equals("RETURN")){
                jLabel3.setText("CART");
            }
        }
        else{
            cardLayout.show(menuPanel, "STORE");
            jLabel3.setText("CART");
            cardPage = 0;
            changeLeftPanel();
            if(jLabel3.getText().equals("RETURN")){
                jLabel4.setText("WISHLIST");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cartIconMouseClicked

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        jPanel1.setBackground(new Color(144, 238, 144));
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        jPanel1.setBackground(new Color(204,204,255));        
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        jPanel2.setBackground(new Color(144, 238, 144));
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        jPanel2.setBackground(new Color(204,204,255)); 
    }//GEN-LAST:event_jPanel2MouseExited

    private void cartIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseEntered
        icon8 = ImageUtils.getCircularIcon(icon8, 55);
        cartIcon.setIcon(icon8);        
    }//GEN-LAST:event_cartIconMouseEntered

    private void cartIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseExited
        icon2 = ImageUtils.getCircularIcon(icon2, 55);
        cartIcon.setIcon(icon2);
    }//GEN-LAST:event_cartIconMouseExited

    private void wishIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishIconMouseEntered
        icon7 = ImageUtils.getCircularIcon(icon7, 55);
        wishIcon.setIcon(icon7);
    }//GEN-LAST:event_wishIconMouseEntered

    private void wishIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishIconMouseExited
        icon3 = ImageUtils.getCircularIcon(icon3, 55);
        wishIcon.setIcon(icon3);
    }//GEN-LAST:event_wishIconMouseExited
    
    private void backtoLogIn(){
        try{
             
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/uscs33_project/component/REALTIME_CUSTOMER.txt")));
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
       
       userInfo.clear();
       
       login.setVisible(true);
       login.setLocationRelativeTo(null);

       this.setVisible(false); 
    }
    
    private void setupPopup(){
        System.out.println("POP UP IS RUNNING!!!");
        
        JDialog popup = new JDialog(this, "MESSAGE", true);
        popup.setSize(250,150);
        popup.setLocationRelativeTo(this);
        popup.setLayout(new BorderLayout());
        
        JPanel overlay = new JPanel(){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(new Color(204,204,255,150));
                g.fillRect(0,0, getWidth(), getHeight());
            }
        };
        
        overlay.setOpaque(false);
        overlay.setBounds(0,0, this.getWidth(), this.getWidth());
        overlay.setLayout(null);
        this.setGlassPane(overlay);
        
        
        JLabel label = new JLabel(("Do you want to proceed"),SwingConstants.CENTER);
        JButton proceed = new JButton("PROCEED");
        JButton back = new JButton("BACK");
        
        proceed.addActionListener(e ->{
            backtoLogIn();
            overlay.setVisible(false);
            popup.dispose();
        });
        
        back.addActionListener(e -> {
            overlay.setVisible(false);
            popup.dispose();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(proceed);
        buttonPanel.add(back);
        
        popup.add(label, BorderLayout.CENTER);
        popup.add(buttonPanel, BorderLayout.SOUTH);
        
        
        
        
        
        try{
            overlay.setVisible(true);
            popup.setVisible(true);
        }
        catch(Exception e){
            System.out.println("POPUP FAILED TO RUN");
        }
        
        
        System.out.println("POP UP APPEARS!");
    }
    
    public void changeLeftPanel(){
        if(cardPage == 0){
            System.out.println("FILTER PANEL ENABLED");
            cardLayout2.show(leftPanel, "FILTER");
        }
        else{
            System.out.println("FILTER PANEL DISABLED");
            cardLayout2.show(leftPanel, "DISABLE");
        }
    }
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
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TITLE;
    private javax.swing.JLabel cartIcon;
    private javax.swing.JPanel cartwishPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel usernameDisplay;
    private javax.swing.JLabel wishIcon;
    // End of variables declaration//GEN-END:variables
}
