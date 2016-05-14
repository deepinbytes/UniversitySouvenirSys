/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import sg.edu.nus.iss.ussa.exception.StoreException;
import sg.edu.nus.iss.ussa.manager.CategoryManager;
import sg.edu.nus.iss.ussa.manager.MemberManager;
import sg.edu.nus.iss.ussa.manager.ProductManager;

/**
 *
 * @author Ajay
 */
public class MemberTableModel extends AbstractTableModel {

    private String[] columnNames = {"Member ID", "Member Name", "Loyalty Points"};

    private String[] cattest = {"CLO", "MUG", "STA", "DIA"};

    private static Object[][] data;
    private static Object[][] MemberDataList;
    private int catId;
    private static final String C_File_Name = "Category.dat";
    // determine if the No. of fields of a record is correct
    private static final int C_Field_No = 8;

    public MemberTableModel() {
        loadDatatoTableObject();

    }

    public MemberTableModel(int mode, int rowselect) throws IOException {
        if (mode == 0) {
            createMember();
        } else if (mode == 1) {

            MemberManager.deletebyMemberID(rowselect);
        } else if (mode == 2) {
            Object[] dx = data[rowselect];
            MemberManager.updateMember(rowselect, dx);
        }
    }

    public void createMember() {

        try {

            Object[] memberdef = {"mr" + (int) Math.ceil(Math.random() * 1000), "New Name", -1};
            Object[][] newData = new Object[MemberDataList.length + 1][];

            for (int i = 0; i < MemberDataList.length; i++) {
                newData[i] = MemberDataList[i];
            }

            newData[MemberDataList.length] = memberdef;

            MemberDataList = newData;
            data = MemberDataList;
            saveDataToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDataToFile() throws IOException, StoreException {
        MemberManager.setMemberList(MemberDataList);

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
        ArrayList<Member> mList = MemberManager.getMemberList();
        MemberDataList = new Object[mList.size()][3];

        for (int i = 0; i < mList.size(); i++) {
            MemberDataList[i][0] = mList.get(i).getMemberID();
            MemberDataList[i][1] = mList.get(i).getMemberName();
            MemberDataList[i][2] = mList.get(i).getLoyaltyPoints();
        }
        data = MemberDataList;
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

            fireTableCellUpdated(row, col);
        } catch (Exception e) {
        }
    }
}
