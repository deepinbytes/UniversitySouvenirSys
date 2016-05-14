/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

/**
 *
 * @author Ajay
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import sg.edu.nus.iss.ussa.domain.Member;

public class CashPaymentDlg extends JDialog {

    public CashPaymentDlg() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public CashPaymentDlg(JFrame parent, double total, Member mbr) {
        super(parent);
        this.parent = parent;
        this.total = total;
        this.mbr = mbr;
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    JFrame parent;
    double total;
    int lpoints;
    private Member mbr;
    private int paid=0;
//    CashPayment cash = null;

    private void jbInit() throws Exception {
        jButton3.setMnemonic('O');
        jButton3.setText("OK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jTextField6.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                jTextField6_textChanged(e);
            }

            public void removeUpdate(DocumentEvent e) {
                jTextField6_textChanged(e);
            }

            public void insertUpdate(DocumentEvent e) {
                jTextField6_textChanged(e);
            }
        });

        jPanel15.setLayout(flowLayout4);
        jButton2.setMnemonic('C');
        //jScrollPane1.setPreferredSize(new Dimension(0, 0));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        flowLayout4.setAlignment(FlowLayout.RIGHT);
        flowLayout4.setHgap(0); //jTable2.setSelectionForeground(Color.white);
        component1 = Box.createHorizontalStrut(5);
        gridLayout2.setColumns(1);
        gridLayout2.setHgap(5);
        gridLayout2.setRows(3);
        gridLayout2.setVgap(5);
        gridLayout5.setColumns(1);
        gridLayout5.setHgap(5);
        gridLayout5.setRows(3);
        gridLayout5.setVgap(5);
        jLabel6.setDisplayedMnemonic('P');
        jLabel6.setLabelFor(jTextField6);
        jLabel7.setLabelFor(jTextField7);
        jLabel6.setText("Amount Tendered" + ": ");
        jLabel7.setText("Change" + ": ");
        jLabel8.setText("Redeemed Points" + ": ");
        jTextField6.setColumns(20);

        jTextField7.setEditable(false);

        jPanel10.setBorder(null);
        jPanel10.setLayout(flowLayout3);
        this.setModal(true);
        this.setTitle("Cash");
        jPanel10.add(jPanel12);
        border8 = BorderFactory.createCompoundBorder(border7,
                BorderFactory.
                createEmptyBorder(5, 5, 5, 5));
        jPanel12.setLayout(borderLayout1);
        flowLayout3.setAlignment(FlowLayout.LEFT);
        jPanel12.add(jPanel13, BorderLayout.WEST);

        jPanel13.add(jLabel7, null);
        jPanel13.add(jLabel6, null);
        jPanel13.add(jLabel7, null);
        jPanel13.add(jLabel8, null);
        jPanel12.add(jPanel14, BorderLayout.CENTER);

        jPanel14.add(jTextField6, null);
        jPanel14.add(jTextField7, null);
        jPanel14.add(jTextField8, null);
        int test=(int)total/10;
        jTextField8.setText(Integer.toString(test));
        jPanel12.add(jPanel15, java.awt.BorderLayout.SOUTH);
        jPanel15.add(jButton3, null);
        jPanel15.add(jButton2);
        jPanel15.add(component1);
        jPanel13.setLayout(gridLayout2);
        jPanel14.setLayout(gridLayout5);
        add(jPanel10, BorderLayout.CENTER);
    }

    Component component1 = Box.createHorizontalStrut(8);
    GridLayout gridLayout2 = new GridLayout();
    GridLayout gridLayout5 = new GridLayout();
    JPanel jPanel15 = new JPanel();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();
    FlowLayout flowLayout4 = new FlowLayout();

    JTextField jTextField8 = new JTextField();
    JTextField jTextField6 = new JTextField();
    JTextField jTextField7 = new JTextField();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();

    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel13 = new JPanel();
    JPanel jPanel14 = new JPanel();
    JPanel jPanel12 = new JPanel();
    JPanel jPanel10 = new JPanel();
    FlowLayout flowLayout3 = new FlowLayout();
    Border border6 = BorderFactory.createEtchedBorder(Color.white,
            new Color(148, 145, 140));
    Border border7 = new TitledBorder(border6, "Cash");
    Border border8 = BorderFactory.createCompoundBorder(border7,
            BorderFactory.createEmptyBorder(5, 5, 5, 5));

    void jTextField6_textChanged(DocumentEvent e) {
        Document doc = e.getDocument();
        String txt = doc.toString();

        try {
            NumberFormat nf = NumberFormat.getInstance();
            //double total = nf.parse(jTextField5.getText()).doubleValue();
            double tendered = nf.parse(jTextField6.getText()).doubleValue();
            double change = tendered - total;
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            jTextField7.setText(nf.format(change));
        } catch (Exception ex) {
            //ex.printStackTrace();
            // tendered
        }
    }

    public int hasCustomerPaid()
    {
        return paid;
    }
    void jButton3_actionPerformed(ActionEvent e) {
        try {
            NumberFormat nf = NumberFormat.getInstance();
            double change = nf.parse(jTextField7.getText()).doubleValue();

            if (change < 0) {
                JOptionPane.showMessageDialog(parent,
                        "Change amount is incorrect.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double tendered = nf.parse(jTextField6.getText()).doubleValue();
//            cash = new CashPayment(tendered, change);
            paid=1;
            setVisible(false);

        } catch (Exception ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(parent,
                    ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public CashPayment getPayment() {
//        return cash;z
//    }
    public void jButton2_actionPerformed(ActionEvent e) {
//        cash = null;
        setVisible(false);
    }
}
