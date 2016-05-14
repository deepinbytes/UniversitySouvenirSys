/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.domain;

import sg.edu.nus.iss.ussa.manager.ShopKeeperManager;
import java.util.ArrayList;

/**
 *
 * @author a0134449b
 */
public class ShopKeeper {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ShopKeeper getUser(String name, String pass) {
        ShopKeeper user = null;
        boolean isValidUser = false;
        //fetch from file and verify
        ArrayList<ShopKeeper> sdata = ShopKeeperManager.getShopkeeperList();
        for (ShopKeeper iter : sdata) {
            if (iter.getUserName().equalsIgnoreCase(name) && iter.getPassword().equalsIgnoreCase(pass)) {
                user = iter;
                break;
            }
        }
        return user;
    }
}
