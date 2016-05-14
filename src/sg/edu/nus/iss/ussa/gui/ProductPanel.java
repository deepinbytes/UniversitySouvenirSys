/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sg.edu.nus.iss.ussa.domain.ProductsTableModel;

/**
 *
 * @author Toshiba
 */
public class ProductPanel extends javax.swing.JPanel {

    /**
     * Creates new form Add_Product
     */
    private JFrame parent;

    public ProductPanel(JFrame parent) {
        this.parent = parent;
        initComponents();
        jbinit();
    }

    public void jbinit() {
        tb2.setModel(new ProductsTableModel(0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(ProductsTableModel.BindCategoryCombo()));
        tb2.revalidate();
        tb2.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel18 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb2 = new javax.swing.JTable();
        jButton20 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton23 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton26 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Product List", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel18.setLayout(null);

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setText("Add ");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton17);
        jButton17.setBounds(10, 28, 90, 70);

        tb2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tb2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Quantity", "Price", "Bar Code Number", "Re-Order Quantity", "Order Quantity", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane6.setViewportView(tb2);

        jPanel18.add(jScrollPane6);
        jScrollPane6.setBounds(10, 230, 1320, 250);

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setText("Delete");
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton20);
        jButton20.setBounds(120, 30, 90, 70);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel18.add(jSeparator3);
        jSeparator3.setBounds(220, 30, 10, 70);

        jButton23.setContentAreaFilled(false);
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jButton23.setFocusPainted(false);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton23);
        jButton23.setBounds(1250, 115, 50, 70);

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton25.setText("Close");
        jButton25.setToolTipText("");
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton25);
        jButton25.setBounds(1240, 20, 90, 70);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Clothing", "Mugs", "Stationary", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel18.add(jComboBox1);
        jComboBox1.setBounds(150, 150, 200, 20);

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton26.setText("Update");
        jButton26.setActionCommand("Update");
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton26);
        jButton26.setBounds(240, 30, 90, 70);
        jButton26.getAccessibleContext().setAccessibleName("Update");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setText("Product Category");
        jPanel18.add(jLabel54);
        jLabel54.setBounds(20, 150, 100, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 1360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel18.getAccessibleContext().setAccessibleName("ProductManager");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int test = jComboBox1.getSelectedIndex();
        ProductsTableModel ab = new ProductsTableModel(test);

        tb2.setModel(ab);

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            // TODO add your handling code here:
            ProductsTableModel ab = new ProductsTableModel(jComboBox1.getSelectedIndex(), 1);
            ab.loadDatatoTableObject();
            tb2.setModel(ab);
            jComboBox1ActionPerformed(evt);
        } catch (IOException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        try {
            // TODO add your handling code here:
            ProductsTableModel ab = new ProductsTableModel(tb2.getSelectedRow(), 2);
            tb2.setModel(ab);
        } catch (IOException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {
            // TODO add your handling code here:
            ProductsTableModel ab = new ProductsTableModel(tb2.getSelectedRow(), 3);
            tb2.setModel(ab);
            jComboBox1ActionPerformed(evt);
        } catch (IOException ex) {
            Logger.getLogger(ProductPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        new StoreMainFrame();
        parent.dispose();
    }//GEN-LAST:event_jButton25ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tb2;
    // End of variables declaration//GEN-END:variables
}
