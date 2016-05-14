/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import java.util.Date;

/**
 *
 * @author a0134449b
 */
public class MemberDiscount extends Discount {

    public MemberDiscount(String discountCode, String discountDescription, Date startDate, int period, double percent, String Applicable) {
        super(discountCode, discountDescription, startDate, period, percent, Applicable);
    }

}
