package uscs33_project.form;

import uscs33_project.model.ModelItem;
import uscs33_project.component.Item;
import uscs33_project.component.PopUp;
import uscs33_project.event.BackBtnPopUp;
import uscs33_project.event.EventItem;
import uscs33_project.swing.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

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

    public FormHome() {
        initComponents();
        scroll.setVerticalScrollBar(new ScrollBar());
        
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
        PopUp popup = new PopUp()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        popup.setOpaque(false);
        popup.setBackground(new Color(213, 134, 145, 123));
        
        popup.setData(item);
        popup.setBounds(0,0,this.getWidth(), this.getHeight());
        
        layeredPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
//                popup.setBounds(0, 0, layeredPane.getParent().getWidth(), layeredPane.getParent().getHeight());
                popup.setBounds(0, 0, scroll.getWidth(), scroll.getHeight());
//                System.out.println("Width: " + layeredPane.getParent().getWidth());
//                System.out.println("Height: " + layeredPane.getParent().getHeight());
                popup.revalidate();
                popup.repaint();
            }
        });
        
        
        layeredPane.add(popup, JLayeredPane.PALETTE_LAYER);
        
//        sidebar.setVisible(false);
//        scroll.setVisible(false);
        
        popup.setDestroyEvent(new BackBtnPopUp() {
            @Override
            public void PopUpDestroy() {
                layeredPane.remove(layeredPane.getIndexOf(popup));
                layeredPane.revalidate();
                layeredPane.repaint();
//                sidebar.setVisible(true);
//                scroll.setVisible(true);
            }
        });
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        container = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        panelItem = new uscs33_project.swing.PanelItem();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        container.setLayout(new java.awt.BorderLayout());

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelItem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelItem.setPreferredSize(new java.awt.Dimension(1290, 500));
        scroll.setViewportView(panelItem);

        container.add(scroll, java.awt.BorderLayout.CENTER);

        layeredPane.setLayer(container, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 713, Short.MAX_VALUE)
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
        );

        add(layeredPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel container;
    private javax.swing.JLayeredPane layeredPane;
    private uscs33_project.swing.PanelItem panelItem;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables

        
}
