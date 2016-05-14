/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.application;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sg.edu.nus.iss.ussa.domain.ShopKeeper;
import sg.edu.nus.iss.ussa.gui.Login;
import sg.edu.nus.iss.ussa.gui.StoreMainFrame;
import sg.edu.nus.iss.ussa.manager.CategoryManager;
import sg.edu.nus.iss.ussa.manager.MemberManager;
import sg.edu.nus.iss.ussa.manager.ProductManager;
import sg.edu.nus.iss.ussa.manager.ShopKeeperManager;

/**
 *
 * @author Ajay
 */
public class ShopApp {

    private ShopKeeperManager fmgr;
    private Login Loginbox;
    private StoreMainFrame storeframe;

    public ShopApp() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            fmgr = new ShopKeeperManager();
            Loginbox = new Login(new ShopKeeper());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ShopApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ShopKeeperManager getFmgrInstance() {
        return this.fmgr;
    }

    @SuppressWarnings("empty-statement")
    public void startup() throws IOException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dlgSize = Loginbox.getPreferredSize();
        Loginbox.setLocation((screenSize.width - dlgSize.width) / 2,
                (screenSize.height - dlgSize.height) / 2);
        Loginbox.setModal(true);
        Loginbox.pack();
        Loginbox.setVisible(true);
        if (Loginbox.getUser().getUserName() == null) {
            System.exit(0);
        }
        CategoryManager cmgr = new CategoryManager();
        ProductManager pmgr = new ProductManager();
        MemberManager mmgr = new MemberManager();
        try {
            pmgr.loadDataFromFile();
            cmgr.loadData();
            mmgr.loadData();
        } catch (IOException e) {
            System.out.println(e);
        }
        storeframe = new StoreMainFrame();
        //  storeframe.selectedPanel();
        // storeframe.setSize(600, 800);
    }

    public static void main(String[] args) throws IOException {
        ShopApp shopapp = new ShopApp();
        shopapp.startup();
    }
}
