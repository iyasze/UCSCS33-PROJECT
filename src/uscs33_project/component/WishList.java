/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uscs33_project.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Arrays;
import uscs33_project.event.BackBtnPopUp;
import uscs33_project.event.EventItem;
import uscs33_project.event.WishlistListenerFromProduct;
import uscs33_project.event.WishlistListenerFromWishlist;
import uscs33_project.event.addToCartBtnClicked;
import uscs33_project.form.FormHome;
import uscs33_project.model.ModelItem;
/**
 *
 * @author USER
 */
public class WishList extends javax.swing.JFrame {

    /**
     * Creates new form WishList
     */
    public int count;
    private addToCartBtnClicked eventBuy;
    private WishlistListenerFromWishlist eventUpdate2;
    private EventItem eventClick;
    private ArrayList<ModelItem> itemInWishlist;
    private FormHome menu;
    private JFrame parent;
    private String username;
    
    public void setClickEvent(EventItem event) {
        this.eventClick = event;
    }
    
    public WishList(JFrame parent, addToCartBtnClicked listener1, WishlistListenerFromWishlist listener2, ArrayList<ModelItem> itemInWishlist, String username, FormHome menu) {
        initComponents();
        Username();
        this.eventBuy = listener1;
        this.eventUpdate2 = listener2;
        this.itemInWishlist = itemInWishlist;
        this.parent = parent;
        this.menu = menu;
        this.username = username;
        loadItems();
        System.out.println("Current user: " + this.username);
    }
    
    public WishList() {
        initComponents();
        Username();
    }
    
    public void add(ModelItem data) {
        Item item = new Item(eventBuy, eventUpdate2);
        item.setData(data);
        
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    eventClick.itemClick(item, data);
                }
            }
        });
        item.setWishlistButtonText("♥");
        item.setVisible(true);
        panelItem.add(item);
        panelItem.revalidate();
        panelItem.repaint();
    }
    
    public void delete(ModelItem dataToDelete) {
        for (Component item : panelItem.getComponents()) {
            if (item instanceof Item) {
                ModelItem data = ((Item) item).getData();
                if (data.getItemID().equals(dataToDelete.getItemID())) {
                    panelItem.remove(item);
                    panelItem.revalidate();
                    panelItem.repaint();
                    sync(data.getItemID());
                }
            }
        }
    }
    
    public void sync(String itemID) {
        for (Item item : menu.getItems()) {
            if (item.getData().getItemID().equals(itemID)) {
                item.setWishlistButtonText("♡");
            }
        }
    }
    
    public void saveItems() {
        ArrayList<String> newLines = new ArrayList<String>();
        
        Path file = Paths.get("src/uscs33_project/component/WishListInfo.txt").toAbsolutePath();
        try {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = reader.readLine();
            newLines.add(s);
            while(!s.equals(username)) {
                s = reader.readLine();
                newLines.add(s);
            }
            
            if (!itemInWishlist.isEmpty()) {
                for (ModelItem data : itemInWishlist) {
                    newLines.add(data.getItemID());
                }
            }
            
            while (!s.equals("\\")) {
                s = reader.readLine();
            }
            newLines.add("\\");
            
            while((s = reader.readLine())!=null) {
                newLines.add(s);
            }
            
            reader.close();
            
            System.out.println("Need to write " + newLines.size() + " lines");
            for (String line: newLines) {
                System.out.println(line);
            }
            
            BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            for (String line : newLines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            
        }
        catch (Exception e) {
            e.getMessage();
        }
        
    }
    
    public void loadItems() {
        if (!username.equals("GUEST") || !username.equals("ADMIN")) {
            ArrayList<String> itemID = new ArrayList<String>();
            
            Path file = Paths.get("src/uscs33_project/component/WishListInfo.txt").toAbsolutePath();

            try {
                InputStream input = new BufferedInputStream(Files.newInputStream(file));
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String s = reader.readLine();

                while(!s.equals(username)) {
                    s = reader.readLine();
                }
                while(!s.equals("\\")) {
                    s = reader.readLine();
                    if (!s.equals("\\")) {
                        itemID.add(s);
                    }
                }
                input.close();
                
                for (Item item : menu.getItems()) {
                    ModelItem data = item.getData();
                    
                    if (itemID.contains(data.getItemID())) {
                        item.setWishlistButtonText("♥");
                        itemInWishlist.add(data);
                    }
                }
                
                for (ModelItem data : itemInWishlist) {
                    Item item = new Item(eventBuy, eventUpdate2);
                    item.setData(data);
        
                    item.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            if(SwingUtilities.isLeftMouseButton(e)) {
                                eventClick.itemClick(item, data);
                            }
                        }
                    });
                    item.setWishlistButtonText("♥");
                    item.setVisible(true);
                    panelItem.add(item);
                    panelItem.revalidate();
                    panelItem.repaint();
                }
                
                
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            
            
            
            
            
        }
    }
    
    public void setSelected(Component item) {
        for(Component com : panelItem.getComponents()) {
            Item i = (Item) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        ((Item)item).setSelected(true);
    }
    
    public void createPopup(ModelItem item) {
        PopUp popup = new PopUp(eventBuy)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, parent.getWidth(), parent.getHeight());
                super.paintComponent(g);
            }
        };
//        
        parent.setGlassPane(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(213, 134, 145, 200));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        });
//        
        Container glassPane = (Container) parent.getGlassPane();
//        
        glassPane.setVisible(true);
        glassPane.setBackground(new Color(213, 134, 145, 123));
        glassPane.setLayout(new GridBagLayout());

        popup.setOpaque(false);
        popup.setBackground(new Color(0, 0, 0, 0));
        popup.setData(item);
        popup.setBounds(0,0, parent.getWidth(), parent.getHeight());
        
        // Request focus for the popup
        popup.setFocusable(true);
        popup.requestFocusInWindow();
        
        glassPane.addMouseListener(new MouseAdapter() {});
        glassPane.addMouseMotionListener(new MouseMotionAdapter() {});
        glassPane.addKeyListener(new KeyAdapter() {});
        glassPane.setFocusTraversalKeysEnabled(false);
        
        glassPane.add(popup);
        
        popup.setDestroyEvent(new BackBtnPopUp() {
            @Override
            public void PopUpDestroy() {
                glassPane.remove(popup);
                glassPane.setVisible(false);
            }
        });
    }
    
    
    
    private void Username(){
        Path file = Paths.get("src/uscs33_project/component/REALTIME_CUSTOMER.txt");
        
        InputStream input = null;
        String username = null;
        try{
            input = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            for (int i = 0;i < 2;i++){
                username = reader.readLine();
            }
           
            
        }catch(IOException e){
            System.out.println(e);
        }
        FileExtraction(username);
        
    }
    
    private void FileExtraction(String username){
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
                    System.out.println(each);
                    if (i == 3){
                        i = 0;
                        y += 370;
                        x = 10;
                    }
                    System.out.println(each);
                    addWishPanel(each,x,y);
                    x +=  380;
                    i++;
                    }
                    input.close();
                }
               
                }
                else
                    break;
                
            }
              
               
        }
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
//        int result = JOptionPane.showConfirmDialog(WishPanel, "Are you sure to delete the product?", "Delete Item",
//                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        if (result == JOptionPane.YES_OPTION){
//            WishPanel.remove(ItemPanel);
//            WishPanel.revalidate();
//            WishPanel.repaint();
//            //int indexToRemove = (int) ((JButton) e.getSource()).getClientProperty("itemIndex");
//            if (count >= 0 && count < data.size()) {
//                data.remove(count);
//                
//                
//            }
//            
//        }
    });
        
        
        
        imageLabel.setIcon(i);
        ItemPanel.add(imageLabel);
        ItemPanel.add(BrandLabel);
        ItemPanel.add(NameLabel);
        ItemPanel.add(ShadeLabel);
        ItemPanel.add(CartButton);
        ItemPanel.add(deleteButton);
//        WishPanel.add(ItemPanel);
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
        panelItem = new uscs33_project.swing.PanelItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

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
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(1190, 750));

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("WISHLIST   ");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setViewportView(panelItem);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(537, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private uscs33_project.swing.PanelItem panelItem;
    // End of variables declaration//GEN-END:variables
}
