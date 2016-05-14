/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//functions ---add member, delete member, update
package sg.edu.nus.iss.ussa.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.exception.StoreException;
import sg.edu.nus.iss.ussa.gui.MemberPanel;
import sg.edu.nus.iss.ussa.util.StoreUtil;

/**
 *
 * @author A0134543L
 */
public class MemberManager {

    private static String DataFolderPath = "./data/";
    private static String File_Name = "Members.dat";
    private static final int NUM_FIELD = 3;
    private static final String FILE_SEP = ",";
    private static ArrayList<Member> memberList;

    String MemberID = "";

    public MemberManager() {
        memberList = new ArrayList<Member>();
    }

    public void loadData() throws IOException {
        ArrayList<String> stringList = null;
        stringList = StoreUtil.loadStringFromFile(DataFolderPath
                + File_Name);

        StringBuffer errMsg = new StringBuffer();
        for (int lineNo = 0; lineNo < stringList.size(); lineNo++) {
            String line = stringList.get(lineNo);
            String[] fields = line.split(FILE_SEP);
            if (fields.length != NUM_FIELD) {
                errMsg.append("datafile[" + File_Name + "] LineNo:"
                        + (lineNo + 1) + System.getProperty("line.separator"));
                continue;
            }
            String name = fields[0];
            String memberID = fields[1];
            int loyaltyPoints = new Integer(fields[2].toString());
            Member member = new Member(name, memberID, loyaltyPoints);
            memberList.add(member);
        }
        if (errMsg.length() > 0) {
            String exceptionMsg = "Following data in file is not correct:"
                    + System.getProperty("line.separator") + errMsg;
            throw new IOException(exceptionMsg);
        }

    }

    public static void setMemberList(Object[][] data) throws IOException, StoreException {
        setMemberListArray(data);
        saveDataToFile(memberList);

    }

    public static void setMemberListArray(Object[][] fields) throws StoreException {
        memberList = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            String id = fields[i][0].toString();
            String name = fields[i][1].toString();
            int lpoints = new Integer(fields[i][2].toString());
            Member member = new Member(name, id, lpoints);
            memberList.add(member);
        }
    }

    public static ArrayList<Member> getMemberList() {
        return memberList;
    }

    public static void saveDataToFile(ArrayList<Member> dataList) throws IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        for (Member member : dataList) {
            StringBuffer line;
            line = new StringBuffer(member.getMemberName().toUpperCase() + FILE_SEP);
            line.append(member.getMemberID().toUpperCase() + FILE_SEP);
            line.append(member.getLoyaltyPoints());
            stringList.add(line.toString());
        }
        StoreUtil.saveStringToFile(DataFolderPath + File_Name,
                stringList);
    }

    public static String getMemberIDbyName(String name)
    {
        String memID="PUBLIC";
        for (Member mbr:memberList)
        if(name.equals(mbr.getMemberName()))
            memID=mbr.getMemberID();
          return memID;  
    }
    /**
     * ******************************************************************************************************
     */
    /**
     * ******************************************************************************************************
     */
    public static void addMemberToMemberList(Member m) {
        memberList.add(m);
    }

    public static Member getMember(String Id) {

        for (Member mbr : memberList) {
            if (Id.equals(mbr.getMemberID())) {
                return mbr;
            }
        }
        return null;
    }

    /**
     * ******************************************************************************************************
     */
    public static void deleteMemberFromMemberList(Member m) {
        memberList.remove(m);
    }

    public static void deletebyMemberID(int id) throws IOException {
        memberList.remove(id);
        saveDataToFile(memberList);
    }

    public static void updateMember(int id, Object[] data) throws IOException {
        Member member = new Member(data[1].toString(), data[0].toString(), new Integer(data[2].toString()));
        memberList.set(id, member);
        saveDataToFile(memberList);
    }
}
