package CommonActionListeners;

import GUI.UserLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {
    public LogoutListener(JFrame currentPage) {
        this.currentPage = currentPage;
    }

    JFrame currentPage;
    @Override
    public void actionPerformed(ActionEvent e) {
        currentPage.dispose();

            new UserLogin();

    }
}
