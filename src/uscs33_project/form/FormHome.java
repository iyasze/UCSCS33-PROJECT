package uscs33_project.form;

import java.awt.Color;
import uscs33_project.model.ModelItem;
import uscs33_project.component.Item;
import uscs33_project.component.PopUp;
import uscs33_project.event.EventItem;
import uscs33_project.swing.ScrollBar;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import uscs33_project.event.BackBtnPopUp;
import uscs33_project.event.LeftPanelFilter;
import uscs33_project.event.addToCartBtnClicked;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author amani
 */
public class FormHome extends javax.swing.JPanel {

    /**
     * Creates new form FormHome
     */
    public void setClickEvent(EventItem event) {
        this.eventClick = event;
    }
    
    private EventItem eventClick;
    private LeftPanelFilter eventFilter;
    private addToCartBtnClicked eventBuy;

    public FormHome(addToCartBtnClicked listener) {
        initComponents();
        scroll.setVerticalScrollBar(new ScrollBar());
        
        this.eventBuy = listener;
//        this.eventFilter = listener2;
//        layeredPane.setVisible(false);
    }
    
    public void filterBy(String attributeName, String keyword) {
        if (attributeName.equals("ID")) {
            for (Component com : panelItem.getComponents()) {
                ModelItem item = ((Item) com).getData();
                if (!item.getItemID().toLowerCase().startsWith(keyword)) {
                    com.setVisible(false);
                }
                else {
                    com.setVisible(true);
                }
            }
        }
        else if (attributeName.equals("Brand")) {
            for (Component com : panelItem.getComponents()) {
                ModelItem item = ((Item) com).getData();
                if (!item.getBrandName().toLowerCase().startsWith(keyword)) {
                    com.setVisible(false);
                }
                else {
                    com.setVisible(true);
                }
            }
        }
        
        panelItem.revalidate();
        panelItem.repaint();
        System.out.println(keyword);
    }
    
    public void addItem(ModelItem data) {
        Item item = new Item();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    eventClick.itemClick(item, data);
                }
            }
        });
        panelItem.add(item);
        panelItem.repaint();
        panelItem.revalidate();
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
        
        JFrame frame = (JFrame) this.getRootPane().getParent();
        
        frame.setGlassPane(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(213, 134, 145, 200));
                g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
            }
        });
        
        Container glassPane = (Container) frame.getGlassPane();
        
        glassPane.setVisible(true);
        glassPane.setBackground(new Color(213, 134, 145, 123));
        glassPane.setLayout(new GridBagLayout());
        
        
        PopUp popup = new PopUp(eventBuy)
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
                super.paintComponent(g);
            }
        };
        popup.setOpaque(false);
        popup.setBackground(new Color(0, 0, 0, 0));
        popup.setData(item);
        popup.setBounds(0,0, frame.getWidth(), frame.getHeight());
        
        glassPane.add(popup);
        
        popup.setDestroyEvent(new BackBtnPopUp() {
            @Override
            public void PopUpDestroy() {
                glassPane.remove(popup);
                glassPane.setVisible(false);
            }
        });
       
        
        
//        layeredPane.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
////                popup.setBounds(0, 0, layeredPane.getParent().getWidth(), layeredPane.getParent().getHeight());
//                popup.setBounds(0, 0, scroll.getWidth(), scroll.getHeight());
////                System.out.println("Width: " + layeredPane.getParent().getWidth());
////                System.out.println("Height: " + layeredPane.getParent().getHeight());
//                popup.revalidate();
//                popup.repaint();
//            }
//        });
        
        
//        layeredPane.add(popup, JLayeredPane.PALETTE_LAYER);
        
//        sidebar.setVisible(false);
//        scroll.setVisible(false);
        
//        popup.setDestroyEvent(new BackBtnPopUp() {
//            @Override
//            public void PopUpDestroy() {
//                layeredPane.remove(layeredPane.getIndexOf(popup));
//                layeredPane.revalidate();
//                layeredPane.repaint();
////                sidebar.setVisible(true);
////                scroll.setVisible(true);
//            }
//        });
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        panelItem = new uscs33_project.swing.PanelItem();

        setOpaque(false);

        scroll.setViewportView(panelItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uscs33_project.swing.PanelItem panelItem;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables

        
}
