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
public class Member extends Customer {

    private String MemberName;
    private String MemberID;
    private int Lpoints;

    public Member(String MemberName, String MemberID, int Lpoints) {
        super(MemberName);
        this.MemberID = MemberID;
        this.Lpoints = Lpoints;
    }

    public Member()
    {
        
    }
    public void setMemberID(String MemberID) {
        this.MemberID = MemberID;
    }

    public void setLoyaltyPoints(int LoyaltyPoints) {
        this.Lpoints = LoyaltyPoints;
    }

    public void setMemberName(String MemberName) {
        this.MemberName = MemberName;
        super.setCustomerName(MemberName);
    }

    public String getMemberName() {
        MemberName=super.getCustomerName();
        return MemberName;
    }

    public String getMemberID() {
        return MemberID;
    }

    public int getLoyaltyPoints() {
        return Lpoints;
    }
}
