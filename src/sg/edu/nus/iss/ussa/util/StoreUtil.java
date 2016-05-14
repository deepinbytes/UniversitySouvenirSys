/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import sg.edu.nus.iss.ussa.exception.StoreException;

/**
 *
 * @author Ajay
 */
public class StoreUtil {

    public static final String C_Separator = ",";

    public static final String C_Date_Format = "yyyy-MM-dd";

    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * sub method
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * mul method
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * div method
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static double div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2).doubleValue();
    }

    public static ArrayList<String> loadStringFromFile(String fullpath) throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();

        File inFile = new File(fullpath);
        FileReader fr = new FileReader(inFile);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            stringList.add(line);
        }

        br.close();
        fr.close();

        return stringList;
    }

    public static double castDouble(String s) throws StoreException {

        double result;

        try {
            result = Double.parseDouble(s);

        } catch (NumberFormatException e) {
            throw new StoreException(s + " is not double");
        }

        return result;
    }

    /**
     * String cast to date
     *
     * @return cast text to date
     * @throws DataInputException
     */
    public static Date castDate(String s) throws StoreException {

        Date result;
        SimpleDateFormat sdf = new SimpleDateFormat(C_Date_Format);

        try {
            result = sdf.parse(s);

        } catch (Exception e) {
            throw new StoreException(s + " is not a valid date");
        }

        return result;
    }

    /**
     * Cast date to String in yyyy-MM-dd format
     *
     * @param date
     * @return String
     */
    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(C_Date_Format);
        return sdf.format(date);
    }

    /**
     *
     * @param date
     * @param period
     * @return
     */
    public static Date addDays(Date date, int period) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, period);
        return cal.getTime();
    }

    public static int castInt(String s) throws StoreException {

        int result;

        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new StoreException(s + " is not integer");
        }

        return result;

    }

    public static void saveStringToFile(String fullpath, ArrayList<String> stringList) throws IOException {
        File outFile = new File(fullpath);
        BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(outFile));
        PrintWriter pw = new PrintWriter(bw);

        for (String line : stringList) {
            pw.println(line);
        }
        pw.flush();

        pw.close();
        bw.close();
    }
}
