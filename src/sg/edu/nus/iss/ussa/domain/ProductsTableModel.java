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
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import sg.edu.nus.iss.ussa.exception.StoreException;
import sg.edu.nus.iss.ussa.manager.CategoryManager;
import sg.edu.nus.iss.ussa.manager.ProductManager;

public class ProductsTableModel extends AbstractTableModel {

    private String[] columnNames = {"Product ID", "Product Name",
        "Description", "Quantity", "Price", "Barcode",
        "ReOrder Quantity", "Threshold"};

    private String[] fieldNames = {"products_barcode", "products_description",
        "products_price", "products_tax",
        "products_quantity", "product_reorder_quantity", "product_threshold"};
    // 

    private static Object[][] data;
    private static Object[][] ProductDataList;
    private int catId;
    private static final String C_File_Name = "Products.dat";
    // determine if the No. of fields of a record is correct
    private static final int C_Field_No = 8;

    public ProductsTableModel(int catId) {
        loadDatatoTableObject();
        setCategory(catId);
    }

    public ProductsTableModel(int catId, int mode) throws IOException {
        if (mode == 1) {
            createProduct(catId);
            loadDatatoTableObject();
            setCategory(catId);
        } else if (mode == 3) {
            Object[] dx = data[catId];
            ProductManager.deletebyProductID(dx[0].toString());
        } else {
            Object[] dx = data[catId];
            ProductManager.updatebyProductID(dx[0].toString(), dx);
        }
    }

    public void createProduct(int catid) {
        String[] clist = BindCategoryCombo();
        try {

            String prodId = clist[catid] + "/" + (int) Math.ceil(Math.random() * 1000);
            Object[] prod = {prodId, "New Product", "", 0,
                new Double(0.00), "",
                new Integer(0), 0};
            Object[][] newData = new Object[ProductDataList.length + 1][];

            for (int i = 0; i < ProductDataList.length; i++) {
                newData[i] = ProductDataList[i];
            }

            newData[ProductDataList.length] = prod;

            ProductDataList = newData;
            data = ProductDataList;
            saveDataToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] BindCategoryCombo() {
        ArrayList<Category> catlist = CategoryManager.getCategoryList();
        String[] combo = new String[catlist.size()];
        for (Category cat : catlist) {
            combo[catlist.indexOf(cat)] = cat.getCategoryCode();
        }
        return combo;
    }

    public void saveDataToFile() throws IOException, StoreException {
        ProductManager.setProductList(ProductDataList);

    }

    public void setCategory(int catId) {
        this.catId = catId;
        data = getProductsbyCat(catId);

    }

    public Object[][] getProductsbyCat(int cat) {
        Object[][] temp = new Object[getNumberofRowsforCat(cat)][8];
        String[] clist = BindCategoryCombo();
        int datarow = 0;
        for (int row = 0; row < ProductDataList.length; row++) {
            if (clist[cat].equals((ProductDataList[row][0].toString()).substring(0, 3))) {
                temp[datarow][0] = ProductDataList[row][0];
                temp[datarow][1] = ProductDataList[row][1];
                temp[datarow][2] = ProductDataList[row][2];
                temp[datarow][3] = ProductDataList[row][3];
                temp[datarow][4] = ProductDataList[row][4];
                temp[datarow][5] = ProductDataList[row][5];
                temp[datarow][6] = ProductDataList[row][6];
                temp[datarow][7] = ProductDataList[row][7];
                datarow++;
            } else {
                continue;
            }
        }
        return temp;
    }

    public int getNumberofRowsforCat(int cat) {
        String[] clist = BindCategoryCombo();
        int rows = 0;
        for (int row = 0; row < ProductDataList.length; row++) {
            if (clist[cat].equals((ProductDataList[row][0].toString()).substring(0, 3))) {
                rows++;
            }
        }
        return rows;

    }

    public void deleteProducts(int[] rows) {
        try {
//            StoreDB db = new StoreDB();
            for (int i = 0; i < rows.length; i++) {
                int prodId = ((Integer) data[rows[i]][7]).intValue();
                //              db.update("delete from products where products_id = " + prodId);
            }
            Object[][] newData = new Object[data.length - rows.length][];
            int j = 0;

            for (int i = 0; i < data.length; i++) {
                boolean deleted = false;

                for (int k = 0; k < rows.length; k++) {
                    if (i == rows[k]) {
                        deleted = true;
                        break;
                    }
                }

                if (!deleted) {
                    newData[j++] = data[i];
                }
            }

            data = newData;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDatatoTableObject() {
        ArrayList<Product> prodList = ProductManager.getProductList();
        ProductDataList = new Object[prodList.size()][9];

        for (int i = 0; i < prodList.size(); i++) {
            ProductDataList[i][0] = prodList.get(i).getProductId();
            ProductDataList[i][1] = prodList.get(i).getName();
            ProductDataList[i][2] = prodList.get(i).getBriefDescription();
            ProductDataList[i][3] = prodList.get(i).getQuantityAvailable();
            ProductDataList[i][4] = prodList.get(i).getPrice();
            ProductDataList[i][5] = prodList.get(i).getBarCodeNumber();
            ProductDataList[i][6] = prodList.get(i).getReorderQuantity();
            ProductDataList[i][7] = prodList.get(i).getOrderQuantity();
            ProductDataList[i][8] = prodList.get(i).getOrderQuantity();
        }
        data = ProductDataList;
    }

    public int findProduct(String barcode) {

        for (int i = 0; i < getRowCount(); i++) {
            if (data[i][5].equals(barcode)) {
                return i;
            }
        }

        return -1;
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
        return columnNames[col].toString();
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
        if (col < 8) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        try {
            if (col > 8) {
                return;
            }

            data[row][col] = value;
            //saveDataFile();
/*            int prodId = ((Integer) data[row][7]).intValue();
            
             StoreDB db = new StoreDB();
             db.update("update products set " + fieldNames[col] + " = '" +
             value +
             "' where products_id = " + prodId);
             */
            fireTableCellUpdated(row, col);
        } catch (Exception e) {
        }
    }
}
