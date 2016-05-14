/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

/**
 *
 * @author Ajay
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.*;
import sg.edu.nus.iss.ussa.manager.ProductManager;

public class CheckoutTableModel extends AbstractTableModel {

    private static String[] columnNames = {"Product ID", "Product Name",
        "Quantity", "Price",};

    private static Object[][] data = new Object[0][columnNames.length];

    private static ArrayList<Product> prodList;
    private Object[][] cartdata;
    private JPanel parent;

    public CheckoutTableModel(int row, JPanel parent) {
        this.parent = parent;
        removeRow(row);
    }

    public CheckoutTableModel(int mode, String value, JPanel parent) {
        this.parent = parent;
        getProductData();
        if (mode == 0) {
            Object[] prod = addbyBarcode(value);

            addtoCartData(prod);
            fireTableDataChanged();
        }

    }

    public int getProductRow(String barcode) {
        for (int i = 0; i < getRowCount(); i++) {
            if (data[i][0].equals(barcode)) {
                return i;
            }
        }

        return -1;
    }

    public void getProductData() {
        prodList = ProductManager.getProductList();

    }

    public Object[] addbyBarcode(String Barcode) {
        Object[] product = new Object[columnNames.length];
        for (Product prod : prodList) {
            if (Barcode.equals(prod.getBarCodeNumber())) {
                product[0] = prod.getProductId();
                product[1] = prod.getName();

                product[2] = 1;

                product[3] = prod.getPrice();
                return product;
            }
        }
        return product;
    }

    public void addtoCartData(Object[] datax) {
        if (datax[0] == null) {
            JOptionPane.showMessageDialog(parent,
                    "Invalid Barcode",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (data.length > 0) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].toString().equals(datax[0].toString())) {
                    data[i][2] = new Integer(data[i][2].toString()) + 1;
                    return;
                }
            }
        }

        Object[][] newData = new Object[data.length + 1][];
        for (int j = 0; j < data.length; j++) {
            newData[j] = data[j];
        }
        newData[data.length] = datax;
        //data= new Object[newData.length][];
        data = newData;

    }

    public void removeAll() {
        data = new Object[0][columnNames.length];
        fireTableDataChanged();
    }

    public void removeRow(int row) {
        int rowCount = getRowCount();

        if (rowCount > 0 && row < rowCount) {
            Object[][] newData = new Object[rowCount - 1][];

            for (int i = 0; i < row; i++) {
                newData[i] = data[i];
            }

            for (int i = row + 1; i < rowCount; i++) {
                newData[i - 1] = data[i];
            }

            data = newData;
            fireTableDataChanged();
        }
    }

    public void addRow(String code, String barcode, String desc, double price,
            double tax, int quantity) {
        int rowCount = getRowCount();
        Object[][] newData = new Object[rowCount + 1][];

        for (int i = 0; i < rowCount; i++) {
            newData[i] = data[i];
        }

        Object[] newRow = new Object[6];
        newRow[0] = barcode;
        newRow[1] = desc;
        newRow[2] = new Double(price);
        newRow[3] = new Double(tax);
        newRow[4] = new Integer(quantity);
        newRow[5] = code;
        newData[rowCount] = newRow;
        data = newData;
        fireTableDataChanged();
    }

    /**
     * Returns the number of columns in the model.
     *
     * @return the number of columns in the model
     * @todo Implement this javax.swing.table.TableModel method
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the number of rows in the model.
     *
     * @return the number of rows in the model
     * @todo Implement this javax.swing.table.TableModel method
     */
    public int getRowCount() {
        if (data != null) {
            return data.length;
        } else {
            return 0;
        }
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     * @todo Implement this javax.swing.table.TableModel method
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        }
        return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (col == 2 || col == 3) {
            int newValue = new Integer(value.toString());
            if (newValue < 0) {
                return;
            }

            //  newValue = Math.round(newValue * 100) / 100.0;
            value = newValue;
        }

        if (col == 4 && ((Integer) value).intValue() < 0) {
            return;
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
