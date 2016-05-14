///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package sg.edu.nus.iss.ussa.manager;
//
///**
// *
// * @author Ajay
// */
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import sg.edu.nus.iss.ussa.domain.Discount;
//import sg.edu.nus.iss.ussa.util.StoreUtil;
//
///*
//*
//*
//* @ Ajay
//*/
//public class DiscountManager {
//    
//private static ArrayList<Discount> discountList;
//
//public DiscountManager(){
//
//discountList = new ArrayList<>();
//}
//    private static final String FILE_SEP = ",";
//    private static final String DataFolderPath = "./data/";
//    private static final String File_Name="Discounts.dat";
//    private static final int Field_No = 6;
//    
//    
//public void loadDataFromFile() throws IOException
//    {
//        	ArrayList<String> stringList = null;
//stringList = StoreUtil.loadStringFromFile(DataFolderPath + File_Name);
//ArrayList<Discount> discountList = new ArrayList<Discount>();
//StringBuffer errMsg = new StringBuffer();
//for(int lineNo = 0; lineNo < stringList.size() ; lineNo++){
//String line = stringList.get(lineNo);
//String[] fields = line.split(FILE_SEP);
//// when the No. of fields of a record is less then C_Field_No, skip this record
//if (fields.length != Field_No) {
//errMsg.append("datafile[" + File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
//continue;
//}
//try {
//String discountCode = fields[0];
//String discountDescription = fields[1];
//Date startDate;
//if (!fields[2].equalsIgnoreCase("ALWAYS"))
//startDate = Util.castDate(fields[2]);
//else
//startDate = new Date();
//int period = 0;
//if (!fields[3].equalsIgnoreCase("ALWAYS"))
//period = Util.castInt(fields[3]);
//int percent = Util.castInt(fields[4]);
//String Applicable=(fields[5]);
//Discount discount;
//if(Applicable.contains("M")){
//discount = new MemberDiscount(discountCode,discountDescription,percent,Applicable);
//}else{
//discount = new OcassionalDiscount(discountCode, discountDescription, startDate, period, percent, Applicable);
//}
//discountList.add(discount);
//}catch(DataInputException e){
//errMsg.append("datafile[" + C_File_Name + "] LineNo:" + (lineNo + 1) + System.getProperty("line.separator"));
//}
//}
//if (errMsg.length() > 0){
//String exceptionMsg = "Following data in file is not correct:" + System.getProperty("line.separator") + errMsg;
//throw new DataFileException(exceptionMsg);
//}
//return discountList;
//    }
//public void loadData() throws IOException, DataFileException{
//discountList = discountDao.loadDataFromFile();
//}
//public void saveData() throws IOException{
//discountDao.saveDataToFile(discountList);
//}
//public ArrayList<Discount> getDiscountlist(){
//return this.discountList;
//}
///**
//*
//* @param discountCode
//* @param discountDescription
//* @param startDate
//* @param period
//* @param percent
//* @param Applicable
//* @return
//*/
//public ArrayList<Discount> registerDiscount(String discountCode, String discountDescription,
//Date startDate, int period, int percent, String Applicable) {
//discountList.add(new MemberDiscount(discountCode, discountDescription, period, Applicable));
//return discountList;
//}
//public ArrayList<Discount> getDiscountList(){
//return discountList;
//}
//public Discount getDiscountCode(String discountCode) {
//Iterator<Discount> i = discountList.iterator();
//while (i.hasNext()) {
//Discount disc = i.next();
//if (disc.getDiscountcode().equals(discountCode))
//return disc;
//}
//return null;
//}
//public void writeFile() throws IOException {
//discountDao.saveDataToFile(discountList);
//}
//public void readFile() throws IOException, DataFileException {
//discountList = discountDao.loadDataFromFile();
//}
///**
//* according to customer's type, return the applicable and highest discount
//* @tanuj
//* @param customer
//* @return highest discount (may be null)
//*/
//public Discount getMaxDiscount(Customer customer){
//boolean isMember = false;
//boolean hasTransaction=false;
//Discount maxDiscount = null;
//Date currentDate= new Date();
//if(customer instanceof Member){
//isMember=true;
//if(((Member) customer).getLoyaltyPoint() != -1){
//hasTransaction=true;
//}
//}
//for(Discount discount:this.discountList){
//if (discount instanceof MemberDiscount && isMember){
//// discount for member only && customer is a member
//if(!hasTransaction && discount.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
//// discount for member's first purchase only
//maxDiscount = discount.getHigherDiscount(maxDiscount);
//}
//else if(!discount.getDiscountcode().equalsIgnoreCase("MEMBER_FIRST")){
//// discount for member's subseq purchase
//maxDiscount = discount.getHigherDiscount(maxDiscount);
//}
//}
//else if(discount instanceof OcassionalDiscount) {
//OcassionalDiscount od = (OcassionalDiscount)discount;
//if(od.getStartDate().compareTo(currentDate) <= 0 &&
//Util.addDays(od.getStartDate(), od.getPeriod()).compareTo(currentDate) >=0 ) {
//// occasional discount is valid for current date
//maxDiscount = discount.getHigherDiscount(maxDiscount);
//}
//}
//}
//return maxDiscount;
//}
//public void addDiscount(String discountCode, String discountDescription,
//Date startDate, int period, int percent, String Applicable){
//Discount newDiscount = new OcassionalDiscount(discountCode, discountDescription,
//startDate, period, percent, Applicable);
//this.discountList.add(newDiscount);
//}
//public void modifyMemberDiscount(String discountCode, String discountDescription,
//int percent) {
//Discount result = getDiscountByCode(discountCode);
//result.setDiscountDescription(discountDescription);
//result.setPercent(percent);
//}
//public void modifyOcassionalDiscount(String discountCode, String discountDescription,
//Date startDate, int period, int percent) {
//Discount result = getDiscountByCode(discountCode);
//OcassionalDiscount od = (OcassionalDiscount) result;
//od.setDiscountDescription(discountDescription);
//od.setStartDate(startDate);
//od.setPeriod(period);
//od.setPercent(percent);
//}
//public void deleteDiscount(String code) {
//discountList.remove(getDiscountByCode(code));
//}
//public Discount getDiscountByCode(String code){
//Discount result = null;
//for(Discount d:discountList){
//if (code.equals(d.getDiscountcode())){
//result = d;
//break;
//}
//}
//return result;
//}
//}
