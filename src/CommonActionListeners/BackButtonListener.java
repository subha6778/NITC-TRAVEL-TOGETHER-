package CommonActionListeners;

import GUI.LandingPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class BackButtonListener implements ActionListener {

    JFrame currentPage;
    JFrame lastPage;


    public BackButtonListener(JFrame currentPage, JFrame lastPage) {
        this.currentPage = currentPage;
        this.lastPage = lastPage;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        currentPage.dispose();
        lastPage.setVisible(true);
    }
}
