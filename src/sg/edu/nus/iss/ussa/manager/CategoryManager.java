/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.util.ArrayList;
import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Vendor;
import sg.edu.nus.iss.ussa.exception.StoreException;
import sg.edu.nus.iss.ussa.util.StoreUtil;

/**
 *
 * @author Ajay
 */
public class CategoryManager {

    private static ArrayList<Category> categoryList;
    // this VendorList only exist for maintain data consistency
    // for example, if CLO and MUG share one vendor Nancy's , 
    // then in CLO and MUG, their vendors should reference to same instance of vendor  
    private ArrayList<Vendor> vendorList;

    private static final String FILE_SEP = ",";
    private static final String DataFolderPath = "./data/";
    private static final String File_Name = "Category.dat";
    private static final int Field_No = 2;

    /**
     *
     * @throws IOException
     * @throws DataFileException
     */
    public CategoryManager() throws IOException {

        loadData();
    }

    /**
     * load data from file
     *
     * @throws IOException
     * @throws DataFileException
     */
    public void loadData() throws IOException {
        ArrayList<String> stringList = null;
        stringList = StoreUtil.loadStringFromFile(DataFolderPath + File_Name);
        categoryList = new ArrayList<Category>();
        StringBuffer err = new StringBuffer();
        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {
            String line = stringList.get(lineNo);
            String[] fields = line.split(FILE_SEP);
            if (fields.length != Field_No) {
                err.append("datafile[" + File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }
            String code = fields[0];
            String name = fields[1];
            Category category = new Category(code, name, null);
            categoryList.add(category);
        }
        if (err.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + err;
            throw new IOException(exceptionMsg);
        }

    }

    public static ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public static void setCategoryList(Object[][] data) throws IOException, StoreException {
        setCatListArray(data);
        ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            StringBuffer line = new StringBuffer();
            for (int j = 0; j < Field_No; j++) {

                line.append(data[i][j] + StoreUtil.C_Separator);
            }
            stringList.add(line.toString());
        }
        StoreUtil.saveStringToFile(DataFolderPath + File_Name, stringList);

    }

    public static void setCatListArray(Object[][] fields) throws StoreException {
        categoryList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            String code = fields[i][0].toString();
            String name = fields[i][1].toString();
            Category cat = new Category(code, name);
            categoryList.add(cat);
        }
    }

    public static void deletebyCategoryID(int id) throws IOException {
        categoryList.remove(id);
        SaveCatListToFile();
    }

    public static void SaveCatListToFile() throws IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Category category : categoryList) {
            StringBuffer line;
            line = new StringBuffer(category.getCategoryCode() + FILE_SEP);
            line.append(category.getCategoryName() + FILE_SEP);
            stringList.add(line.toString());
        }
        StoreUtil.saveStringToFile(DataFolderPath + File_Name, stringList);
    }

    public static void updateCategory(int id, Object[] data) throws IOException {
        Category cat = new Category(data[0].toString(), data[1].toString());
        categoryList.set(id, cat);
        SaveCatListToFile();
    }

    public void addCategory(String code, String name, ArrayList<Vendor> vendorList) {
        Category category = new Category(code, name, vendorList);
        this.categoryList.add(category);

        this.maintainVendorList();
    }

    public static Category getCategorybycode(String code) {

        Category Cat_ret = null;
        for (Category cat : categoryList) {
            if (code.equals(cat.getCategoryCode())) {
                Cat_ret = new Category(cat.getCategoryCode(), cat.getCategoryName());
                return Cat_ret;
            }
        }
        return Cat_ret;
    }

    /**
     *
     * @param code
     * @param name
     * @param vendorList
     */
//	public void updCategory(String code, String name, ArrayList<Vendor> vendorList){
//		Category category = this.getCategoryByCode(code);
//		
//		category.setName(name);
//		category.setVendorList(vendorList);
//		
//		this.maintainVendorList();
//	}
    /**
     *
     * @param code
     */
//	public void delCategory(String code){
//		Category category = this.getCategoryByCode(code);
//		this.categoryList.remove(category);
//		this.maintainVendorList();
//	}
//	
    /**
     * When there is any change about category happens, this method will be
     * called to maintain a non-duplicate vendor list
     */
    private void maintainVendorList() {
        ArrayList<Vendor> newVendorList = new ArrayList<Vendor>();
        for (Category category : this.categoryList) {
            for (Vendor vendor : category.getVendorList()) {
                if (newVendorList.contains(vendor)) {
                    continue;
                } else {
                    newVendorList.add(vendor);
                }
            }
        }
        this.vendorList = newVendorList;
    }

}
