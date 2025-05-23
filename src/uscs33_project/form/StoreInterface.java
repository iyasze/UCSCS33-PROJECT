/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uscs33_project.form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.Dialog;
import java.awt.event.*;
import java.beans.Beans;
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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import javax.swing.*;
import uscs33_project.component.BrowseFilter;
import uscs33_project.component.LogInPage;
import uscs33_project.component.SignUpPage;
import uscs33_project.event.EventItem;
import uscs33_project.event.addToCartBtnClicked;
import uscs33_project.event.LeftPanelFilter;
import uscs33_project.model.ModelItem;
import uscs33_project.model.ModelItemChoice;
import uscs33_project.component.WishList;
import uscs33_project.component.BrowseFilterDisabled;
import uscs33_project.component.Item;
import uscs33_project.event.ImageUtils;
import uscs33_project.event.WishlistListenerFromProduct;
import uscs33_project.event.WishlistListenerFromWishlist;
import uscs33_project.form.GuestAddress;

/**
 *
 * @author amani
 */
public class StoreInterface extends javax.swing.JPanel implements addToCartBtnClicked, WishlistListenerFromProduct, WishlistListenerFromWishlist {

    /**
     * Creates new form StoreInterface
     */
    
    ImageUtils circleImages = new ImageUtils();
    private CardLayout cardLayout; //menu panel
    private CardLayout cardLayout2; //left panel
    private ArrayList<String> userInfo;
    private FormHome menu;
    private ShoppingCart cart;
    private WishList wishlist;
    private ArrayList<ModelItemChoice> itemInCart;
    private ArrayList<ModelItem> itemInWishlist;
    private int cardPage;
    private LeftPanelFilter eventFilter;
    private JFrame parentFrame;
    
    
        
    public StoreInterface(JFrame parentFrame) {
        
        initComponents();
        this.parentFrame = parentFrame;
        
        
        //AQIL THINGS
        paintBg();
        setImage();
        
        itemInCart = new ArrayList<ModelItemChoice>();
        itemInWishlist = new ArrayList<ModelItem>();
        
        
        
//        cart = new ShoppingCart(product);
        
//        menu.filterBy();
        
        //AQIL THINGS
        
        userInfo = new ArrayList<>();
        
        if (!Beans.isDesignTime()) {
            loadUser();
            displayUser();
        }
        
        wishDisabled();
        
        menu = new FormHome(this, this);
        importData();
        
        
        cart = new ShoppingCart(this, itemInCart);
        wishlist = new WishList(parentFrame, this, this, itemInWishlist, usernameDisplay.getText(), menu);
        
        
        menu.setClickEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                menu.setSelected(com);
                System.out.println(item.getItemID());
                menu.createPopup(item);
            }
        });
        
        wishlist.setClickEvent(new EventItem() {
            @Override
            public void itemClick(Component com, ModelItem item) {
                wishlist.setSelected(com);
                System.out.println(item.getItemID());
                wishlist.createPopup(item);
            }
        });
        
        
        JPanel cartPanel = cart.getPanel();
        JPanel wishPanel = wishlist.getPanel();
        JPanel storePanel = menu;

        menuPanel.add(storePanel, "STORE");
        menuPanel.add(cartPanel, "CART");
        menuPanel.add(wishPanel, "WISHLIST");
        
        BrowseFilter filter = new BrowseFilter((attributeName, keyword) -> {
            menu.filterBy(attributeName, keyword);
        });
        
        
        BrowseFilterDisabled disabledFilter = new BrowseFilterDisabled();
        leftPanel.add(filter, "FILTER");
        leftPanel.add(disabledFilter, "DISABLED");
        
        cardLayout2.show(leftPanel, "FILTER");
        
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e))
                    menu.filterBy("Search", searchField.getText().trim().toLowerCase());
            }
        });
        
//        JFrame frame = (JFrame) SwingUtilities.windowForComponent(this);
//        
//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                exportData();
//            }
//        });

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentShown(ComponentEvent e) {
//                JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(StoreInterface.this);
//                if (frame != null) {
//                    frame.addWindowListener(new WindowAdapter() {
//                        @Override
//                        public void windowClosing(WindowEvent e) {
//                            exportData();
//                            JOptionPane.showMessageDialog(null, "Whaa");
//                            System.out.println("yy");
//                        }
//                    });
//                    // Remove this listener after setup
//                    StoreInterface.this.removeComponentListener(this);
//                }
//            }
//        });
        
    }
   
    public JPanel getUpperPanel() {
        return upperPanel;
    }
    
    @Override
    public void buy(ModelItemChoice item) {
        boolean flag = true;
        System.out.println(item.getQuantity());
        for (ModelItemChoice element : itemInCart) {
            if (element.getItemID().equals(item.getItemID()) && element.getSelectedVariant().equals(item.getSelectedVariant())) {
                flag = false;
                int newQuantity = element.getQuantity() + item.getQuantity();
                if (newQuantity > element.getItemStock()) {
                    JOptionPane.showMessageDialog(null, "Error Adding to Cart" , "You already have the item in your cart. However, you cannot add more due to not enough stock available.", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    element.setQuantity(newQuantity);
                    System.out.println(element.getQuantity());
                }
                
            }
            
        }
        
        if (flag) {
            itemInCart.add(item);
        }
        
        cart.refreshCart();
        
    }
    
    // yy
    
    
    public void exportData() {
        Path file = Paths.get("src/uscs33_project/main/products.txt").toAbsolutePath();
        try{
            
            BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            for (Item item : menu.getItems()) {
                ModelItem data = item.getData();
               
                writer.write(data.getItemID());
                writer.write("|");
                writer.write(Integer.toString(data.getItemStock()));
                writer.write("|");
                writer.write(data.getItemName());
                writer.write("|");
                writer.write(data.getBrandName());
                writer.write("|");
                writer.write(Double.toString(data.getPrice()));
                writer.write("|");
                writer.write(data.getItemID()+".png");
                writer.write("|");
                if (data.getOptions().length != 0) {
                    String[] arr = data.getOptions();
                    String shades = String.join(",", arr);
                    writer.write(shades);
                }
                else {
                    writer.write("");
                }
                writer.write("|");
                writer.write(data.getCategory());
                writer.write("|");
                writer.write(data.getDescription());
                writer.newLine();
                writer.flush();
            }
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void exportWishlistData() {
        wishlist.saveItems();
    }
    
    private void importData() {
        int ID = 1;
        Path file = Paths.get("src/uscs33_project/main/products.txt").toAbsolutePath();
//        System.out.println(file);
        try {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = reader.readLine();
            
            while(s != null) {
                String[] parts = s.split("\\|");
                
                String imgLink = "/uscs33_project/image/" + parts[5];
                
                // File array order
                // 0 = ItemID,
                // 1 = Stock,
                // 2 = Item Name,
                // 3 = Item Brand, 
                // 4 = Item Price, 
                // 5 = Image file name, 
                // 6 = ShadeOptions
                // 7 = Category
                // 8 = Desc
                String[] choices = {};
                
                if (!parts[6].equals("")) {
                    choices = parts[6].split(",");
                }
//                System.out.println("Arr len: " + choices.length);
                
                
                ModelItem itemTemp = new ModelItem(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4]), new ImageIcon(getClass().getResource(imgLink)), choices, parts[7], parts[8]);
                menu.addItem(itemTemp);
//                System.out.println(imgLink);
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
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_SEARCHICON.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_CARTICON.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHICON.png"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_PANELDECO .png"));
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_PROFILE PIC.png"));
        
        icon2 = circleImages.getCircularIcon(icon2,55);
        icon3 = circleImages.getCircularIcon(icon3,55);
        icon1 = circleImages.getCircularIcon(icon1, 52);
        
//        jLabel2.setIcon(icon4);
        searchButton.setIcon(icon1);
        cartIcon.setIcon(icon2);    
        wishIcon.setIcon(icon3);  
        userIcon.setIcon(icon5);
        cardLayout = new CardLayout();
        cardLayout2 = new CardLayout();
        menuPanel.setLayout(cardLayout);
        leftPanel.setLayout(cardLayout2);
        jLabel3.setText("CART");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setVerticalAlignment(SwingConstants.CENTER);
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
    
    private void loadUser(){
        System.out.println("LOADING USER INFO");
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
    
    private void wishDisabled(){
        
        //boolean isEnabled = false;
        
        if(usernameDisplay.getText().trim().equals("GUEST")){
            wishIcon.setIcon(null);
            jLabel8.setText("");
            for(MouseListener listener : wishIcon.getMouseListeners()){ //traverse through each available MouseListener
                wishIcon.removeMouseListener(listener); //disables the MouseListener
            }
            
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
        java.awt.GridBagConstraints gridBagConstraints;

        upperPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        TITLE = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usernameDisplay = new javax.swing.JLabel();
        cartwishPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        wishIcon = new javax.swing.JLabel();
        cartIcon = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1000, 900));
        setLayout(new java.awt.BorderLayout());

        upperPanel.setBackground(new java.awt.Color(204, 204, 255));
        upperPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        upperPanel.setPreferredSize(new java.awt.Dimension(1440, 250));
        upperPanel.setLayout(new java.awt.GridBagLayout());

        jPanel2.setOpaque(false);

        jLabel7.setText("C O S M E T I C S  &  B E A U T Y");
        jLabel7.setToolTipText("");

        TITLE.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        TITLE.setFont(new java.awt.Font("ITF Devanagari Marathi", 0, 50)); // NOI18N
        TITLE.setText(" M  A  K  L  U  V");
        TITLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TITLEMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TITLE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(jLabel7)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(TITLE, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upperPanel.add(jPanel2, gridBagConstraints);

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
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(userIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 23;
        upperPanel.add(jPanel1, gridBagConstraints);

        cartwishPanel.setBackground(new java.awt.Color(204, 204, 255));
        cartwishPanel.setOpaque(false);

        jLabel3.setText("    CART ");

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

        jLabel8.setText("WISHLIST");

        javax.swing.GroupLayout cartwishPanelLayout = new javax.swing.GroupLayout(cartwishPanel);
        cartwishPanel.setLayout(cartwishPanelLayout);
        cartwishPanelLayout.setHorizontalGroup(
            cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartwishPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartwishPanelLayout.createSequentialGroup()
                        .addComponent(cartIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(wishIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartwishPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        cartwishPanelLayout.setVerticalGroup(
            cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartwishPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wishIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cartwishPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        upperPanel.add(cartwishPanel, gridBagConstraints);

        searchPanel.setBackground(new java.awt.Color(204, 204, 255));
        searchPanel.setOpaque(false);

        searchField.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        searchField.setPreferredSize(new java.awt.Dimension(400, 25));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
            .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 13);
        upperPanel.add(searchPanel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        upperPanel.add(jLabel2, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        upperPanel.add(jPanel3, gridBagConstraints);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        upperPanel.add(jPanel4, gridBagConstraints);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        upperPanel.add(jPanel5, gridBagConstraints);

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        upperPanel.add(jPanel6, gridBagConstraints);

        add(upperPanel, java.awt.BorderLayout.NORTH);

        leftPanel.setBackground(new java.awt.Color(204, 204, 255));
        leftPanel.setPreferredSize(new java.awt.Dimension(170, 600));
        leftPanel.setLayout(new java.awt.CardLayout());
        add(leftPanel, java.awt.BorderLayout.WEST);

        menuPanel.setPreferredSize(new java.awt.Dimension(1190, 600));
        menuPanel.setLayout(new java.awt.CardLayout());
        add(menuPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        if(jLabel1.getText().equals("LOG OUT")){
            System.out.println("ATTEMPT POP UP!!!");
            exportData();
            exportWishlistData();

            setupPopup();
        }
        else{
            backtoLogIn();
        }
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

//        this.setVisible(false);

        JFrame c = (JFrame) this.getRootPane().getParent();
        c.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void TITLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TITLEMouseClicked
        cardLayout.show(menuPanel, "STORE");
    }//GEN-LAST:event_TITLEMouseClicked

    private void cartIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseEntered
        ImageIcon icon8 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_CARTENTERED.png"));
        icon8 = circleImages.getCircularIcon(icon8,55);
        cartIcon.setIcon(icon8);
    }//GEN-LAST:event_cartIconMouseEntered

    private void cartIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseExited
        ImageIcon icon6 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_CARTICON.png"));
        icon6 = circleImages.getCircularIcon(icon6,55);
        cartIcon.setIcon(icon6);
    }//GEN-LAST:event_cartIconMouseExited

    private void cartIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartIconMouseClicked
        
        if(usernameDisplay.getText().equals("GUEST") && userInfo.get(3).equals("-") && !(jLabel3.getText().equals("RETURN"))){
            JOptionPane.showMessageDialog(this, "You will need to register your address!");
            
            GuestAddress guest = new GuestAddress();
            guest.setLocationRelativeTo(null);
            guest.setVisible(true);
        }
        
        if(jLabel3.getText().equals("CART")){
            cardLayout.show(menuPanel, "CART");
            jLabel3.setText("RETURN");
            cardPage = 1;
            changeLeftPanel();
            if(jLabel8.getText().equals("RETURN")){
                jLabel3.setText("CART");
                jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel3.setVerticalAlignment(SwingConstants.CENTER);
            }
        }
        else{
            cardLayout.show(menuPanel, "STORE");
            jLabel3.setText("CART");
            jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel3.setVerticalAlignment(SwingConstants.CENTER);
            cardPage = 0;
            changeLeftPanel();
            if(jLabel3.getText().equals("RETURN")){
                jLabel8.setText("WISHLIST");
            }
        } 
        
        cart.refreshCart();
       
        
    }//GEN-LAST:event_cartIconMouseClicked

    private void wishIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishIconMouseEntered
        ImageIcon icon7 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHENTERED.png"));
        icon7 = circleImages.getCircularIcon(icon7, 55);
        wishIcon.setIcon(icon7);
    }//GEN-LAST:event_wishIconMouseEntered

    private void wishIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishIconMouseExited
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_WISHICON.png"));
        icon3 = circleImages.getCircularIcon(icon3, 55);
        wishIcon.setIcon(icon3);
    }//GEN-LAST:event_wishIconMouseExited

    private void wishIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wishIconMouseClicked
        if(jLabel8.getText().equals("WISHLIST")){
           cardPage = 1;
           cardLayout.show(menuPanel, "WISHLIST");
           jLabel8.setText("RETURN");
           changeLeftPanel();
           if(jLabel3.getText().equals("RETURN")){
               jLabel8.setText("WISHLIST");
           }
           
       }
       else{
           cardPage = 0;
           
           cardLayout.show(menuPanel, "STORE");
           jLabel8.setText("WISHLIST");
           changeLeftPanel();
           if(jLabel8.getText().equals("RETURN")){
                jLabel3.setText("CART");
                jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel3.setVerticalAlignment(SwingConstants.CENTER);
            }
       }
    }//GEN-LAST:event_wishIconMouseClicked

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseEntered
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_SEARCHSELECTED.png"));
        icon1 = circleImages.getCircularIcon(icon1,52);
        searchButton.setIcon(icon1);
    }//GEN-LAST:event_searchButtonMouseEntered

    private void searchButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseExited
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/uscs33_project/image/MAIN_SEARCHICON.png"));
        icon1 = circleImages.getCircularIcon(icon1,52);
        searchButton.setIcon(icon1);// TODO add your handling code here:
    }//GEN-LAST:event_searchButtonMouseExited

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
       
       JFrame c = (JFrame) this.getRootPane().getParent();
       c.dispose();
    }
    
    private void setupPopup(){
        System.out.println("POP UP IS RUNNING!!!");
        
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        
        JDialog popup = new JDialog(parentWindow, "MESSAGE", Dialog.ModalityType.APPLICATION_MODAL);
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
        //this.setGlassPane(overlay);
        
        
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
            cardLayout2.show(leftPanel, "DISABLED");
        }
    }
    
    public JFrame getParentFrame() {
        if (parentFrame == null) {
            parentFrame = (JFrame)SwingUtilities.getWindowAncestor(this);
        }
        return parentFrame;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TITLE;
    private javax.swing.JLabel cartIcon;
    private javax.swing.JPanel cartwishPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel usernameDisplay;
    private javax.swing.JLabel wishIcon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void AddRemoveFromWishlist1(String action, String itemID) {
        if (action.equals("add")) {
            ModelItem data;
            for (Item item : menu.getItems()) {
                if (item.getData().getItemID().equals(itemID)) {
                    data = item.getData();
                    if (!itemInWishlist.contains(data)) {
                        itemInWishlist.add(data);
                        wishlist.add(data);
                    }
                }
            }
            System.out.println("WIshlist now has " + itemInWishlist.size() + " items");
        }
    }

    @Override
    public void AddRemoveFromWishlist2(String action, String itemID) {
        if (action.equals("remove")) {
            ModelItem data;
            for (Item item : menu.getItems()) {
                if (item.getData().getItemID().equals(itemID)) {
                    data = item.getData();
                    if (itemInWishlist.contains(data)) {
                        itemInWishlist.remove(data);
                        wishlist.delete(data);
                    }
                }
            }
            System.out.println("WIshlist now has " + itemInWishlist.size() + " items");
        }
    }

    

}
