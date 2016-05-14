/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.ussa.domain.Customer;
import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Transaction;
import sg.edu.nus.iss.ussa.util.StoreUtil;

/**
 *
 * @author Ajay
 */
public class TranscationManager {

    private static ArrayList<Transaction> Tlist=new ArrayList<Transaction>();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     private static final String DataFolderPath = "./data/";
    private static final String File_Name = "Transactions.dat";
    private static final int Field_No = 6;
    
    public TranscationManager()
    {
        try {
            LoadData();
        } catch (IOException ex) {
            Logger.getLogger(TranscationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setTransaction(Object[][] tdata,Customer cname,double total)
    {
       //clearTransacationList();
        try {
            for(int i=0;i<tdata.length;i++)
            {
                Transaction txn=new Transaction();
                txn.setCashAmount(total);
                if(cname!=null)
                txn.setCustomer(cname);
                else
                 txn.setCustomer(new Customer());  
                txn.setQuantity(new Integer(tdata[i][2].toString()));
                txn.setDate(dateFormat.format(new Date()));
                txn.setId((int) (Math.random()*100));
                Product prod=ProductManager.getProductbyID(tdata[i][0].toString());
                txn.setProduct(prod);
                Tlist.add(txn);
            }
            saveListData();
        } catch (IOException ex) {
            Logger.getLogger(TranscationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearTransacationList()
    {
    Tlist=new ArrayList<>();
    }
    public void LoadData() throws IOException
    {
        ArrayList<String> stringList = null;
        stringList = StoreUtil.loadStringFromFile(DataFolderPath + File_Name);
        Tlist = new ArrayList<Transaction>();
        StringBuffer err = new StringBuffer();
        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {
            String line = stringList.get(lineNo);
            String[] fields = line.split(StoreUtil.C_Separator);
            if (fields.length != Field_No) {
                err.append("datafile[" + File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }
            int id = new Integer(fields[0]);
            String prodid = fields[1];
            Product prod=ProductManager.getProductbyID(prodid);
            String memberid= fields[2];
            
            Customer mbr=MemberManager.getMember(memberid);
                  if(mbr==null)mbr=new Customer();
            int quantity=new Integer(fields[3]);
            String date=fields[4];
            double price=new Double(fields[5]);
            Transaction txn = new Transaction(id, prod,mbr,quantity,date,price);
            Tlist.add(txn);
        }
        if (err.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + err;
            throw new IOException(exceptionMsg);
        }

    }
    public void saveListData() throws IOException
    {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Transaction txn : Tlist) {
            StringBuffer line;
            line = new StringBuffer(txn.getId() + StoreUtil.C_Separator);
            line.append(txn.getProduct().getProductId() + StoreUtil.C_Separator);
            String mgr=MemberManager.getMemberIDbyName(txn.getCustomer().getCustomerName());
            line.append(mgr+ StoreUtil.C_Separator);
            line.append(txn.getQuantity() + StoreUtil.C_Separator);
            line.append(txn.getDate() + StoreUtil.C_Separator);
            line.append(txn.getCashAmount() + StoreUtil.C_Separator);
            stringList.add(line.toString());
        }
        StoreUtil.saveStringToFile(DataFolderPath + File_Name, stringList);
    }
    
    public ArrayList<Transaction> getTransactionListByDate(Date date) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        for (int i = 0; i < Tlist.size(); i++) {
            Transaction t = (Transaction) Tlist.get(i);
            String date1 = StoreUtil.dateToString(date);
            String date2 = t.getDate();
            if (date1.equals(date2)) {
                result.add(t);
            }
        }
        return result;
    }
    
    public ArrayList<Transaction> getTransactionListByID(int ID) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
     
        for(Transaction t:Tlist)
        {
          if (t.getId()==ID) 
                result.add(t);     
        }
        return result;
    }
}
