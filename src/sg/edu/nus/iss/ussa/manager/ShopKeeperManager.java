/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import sg.edu.nus.iss.ussa.domain.ShopKeeper;
import sg.edu.nus.iss.ussa.util.StoreUtil;

/**
 *
 * @author a0134449b
 */
public final class ShopKeeperManager {

    private static final ArrayList<ShopKeeper> shopKeeperList = new ArrayList<ShopKeeper>();
    private static final String C_DataFolderPath = "./data/";
    private static final String FILE_NAME = "StoreKeepers.dat";
    private static final String FILE_SEP = ",";
    private static final int NUM_FIELD = 2;

    public static void loadShopKeeperDataFromFile() throws IOException {
        ArrayList<String> shopKeeperData = new ArrayList<String>();

        shopKeeperData = StoreUtil.loadStringFromFile(C_DataFolderPath + FILE_NAME);
        for (String userData : shopKeeperData) {
            String[] filterData = userData.split(FILE_SEP);
            ShopKeeper user = new ShopKeeper();
            if (filterData.length == NUM_FIELD) {
                user.setUserName(filterData[0]);
                user.setPassword(filterData[1]);
                shopKeeperList.add(user);
            }
        }

    }

    public static ArrayList<ShopKeeper> getShopkeeperList() {
        try {
            loadShopKeeperDataFromFile();
        } catch (Exception e) {
            System.out.println(e);

        }
        return shopKeeperList;

    }
}
