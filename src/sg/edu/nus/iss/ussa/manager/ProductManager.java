/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.exception.StoreException;
import sg.edu.nus.iss.ussa.util.StoreUtil;

/**
 *
 * @author Ajay
 */
public class ProductManager {

    private static ArrayList<Product> ProductDataList;
    private String[] columnNames = {"Product ID", "Product Name",
        "Description", "Quantity", "Price", "Barcode",
        "ReOrder Quantity", "Threshold"};

    private static final String[] cattest = {"CLO", "MUG", "STA", "DIA"};
    private static final String FILE_SEP = ",";
    private static final String DataFolderPath = "./data/";
    private static final String File_Name = "Products.dat";
    private static final int Field_No = 8;

    public void loadDataFromFile() throws IOException {

        ArrayList<String> stringList = null;
        stringList = StoreUtil.loadStringFromFile(DataFolderPath + File_Name);
        StringBuffer errMsg = new StringBuffer();
        //ArrayList<Product> ProductList=new ArrayList<Product>();
        ProductDataList = new ArrayList<Product>();

        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {
            try {

                String line = stringList.get(lineNo);
                String[] fields = line.split(FILE_SEP);
                // when the No. of fields of a record is less then C_Field_No, skip this record
                if (fields.length != Field_No) {
                    errMsg.append("datafile[" + File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                    continue;
                }
                String productId = fields[0];
                String categoryCode = productId.substring(0, 3);
                Category category = CategoryManager.getCategorybycode(categoryCode);

                String name = fields[1];
                String briefDescription = fields[2];
                int quantityAvaible = StoreUtil.castInt(fields[3]);
                double price = StoreUtil.castDouble(fields[4]);
                String barCodeNumber = fields[5];
                int reorderQuantity = StoreUtil.castInt(fields[6]);
                int orderQuantity = StoreUtil.castInt(fields[7]);
                Product product = new Product(productId, category, name, briefDescription,
                        quantityAvaible, price, barCodeNumber, reorderQuantity, orderQuantity);

                ProductDataList.add(product);
            } catch (StoreException ex) {
                Logger.getLogger(ProductManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (errMsg.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
            throw new IOException(exceptionMsg);
        }
    }

    public static void setProdListArray(Object[][] fields) throws StoreException {
        ProductDataList = new ArrayList<Product>();
        for (int i = 0; i < fields.length; i++) {
            String productId = fields[i][0].toString();
            String categoryCode = productId.substring(0, 3);
            Category category = CategoryManager.getCategorybycode(categoryCode);

            String name = fields[i][1].toString();
            String briefDescription = fields[i][2].toString();
            int quantityAvaible = new Integer(fields[i][3].toString());
            double price = StoreUtil.castDouble(fields[i][4].toString());
            String barCodeNumber = fields[i][5].toString();
            int reorderQuantity = StoreUtil.castInt(fields[i][6].toString());
            int orderQuantity = StoreUtil.castInt(fields[i][7].toString());
            Product product = new Product(productId, category, name, briefDescription,
                    quantityAvaible, price, barCodeNumber, reorderQuantity, orderQuantity);

            ProductDataList.add(product);
        }
    }
//Test function

    public static Category getCategorybycode(String code) {

        Category Cat = new Category("", "");
        for (int i = 0; i < cattest.length; i++) {
            if (code.equals(cattest[i])) {
                Cat.setCategoryCode(code);
                Cat.setCategoryName(code + "");
                return Cat;
            }
        }
        return Cat;
    }

    public static ArrayList<Product> getProductList() {
        return ProductDataList;
    }

    public static void setProductList(Object[][] data) throws IOException, StoreException {
        setProdListArray(data);
        ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            StringBuffer line = new StringBuffer();
            for (int j = 0; j < 8; j++) {

                line.append(data[i][j] + StoreUtil.C_Separator);
            }
            stringList.add(line.toString());
        }
        StoreUtil.saveStringToFile(DataFolderPath + File_Name, stringList);

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

    public static void SaveProdListToFile() throws IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Product product : ProductDataList) {
            StringBuffer line;
            line = new StringBuffer(product.getProductId() + FILE_SEP);
            line.append(product.getName() + FILE_SEP);
            line.append(product.getBriefDescription() + FILE_SEP);
            line.append(product.getQuantityAvailable() + FILE_SEP);
            line.append(product.getPrice() + FILE_SEP);
            line.append(product.getBarCodeNumber() + FILE_SEP);
            line.append(product.getReorderQuantity() + FILE_SEP);
            line.append(product.getOrderQuantity());
            stringList.add(line.toString());
        }
        StoreUtil.saveStringToFile(DataFolderPath + File_Name, stringList);

    }

    public static void deletebyProductID(String id) throws IOException {
        Boolean ListChanged = false;
        int rowno = 0;
        ArrayList<Product> newList = ProductDataList;
        for (Product prod : newList) {
            if (id.equals(prod.getProductId())) {

                rowno = newList.indexOf(prod);
                ListChanged = true;
            }

        }
        if (ListChanged) {
            ProductDataList.remove(rowno);
            SaveProdListToFile();

        }
    }

    public static void updatebyProductID(String id, Object[] data) throws IOException {
        Boolean ListChanged = false;

        for (Product prod : ProductDataList) {
            if (id.equals(prod.getProductId())) {

                prod.setName(data[1].toString());
                prod.setBriefDescription(data[2].toString());
                prod.setQuantityAvailable(new Integer(data[3].toString()));
                prod.setPrice(new Double(data[4].toString()));
                prod.setOrderQuantity(new Integer(data[7].toString()));
                // prod.setOrderQuantity(new Integer(data[8].toString()));

                ProductDataList.set(ProductDataList.indexOf(prod), prod);
                ListChanged = true;
            }

        }
        if (ListChanged) {
            SaveProdListToFile();
        }
    }
    public static Product getProductbyID(String id)
    {     
    for(Product prod:ProductDataList)
    {
    if(id.equals(prod.getProductId())) 
    {
      return prod;     
    }
    }
    return null;
    }
}
