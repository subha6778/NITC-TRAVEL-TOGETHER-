import Database.DatabaseClass;
import GUI.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.SwingUtilities.updateComponentTreeUI;

@SuppressWarnings("unused")
public class Main {
    public static void main(String[] arg)
    {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            System.out.println(info);




       JFrame jFrame = new UserLogin();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            updateComponentTreeUI(jFrame);

            updateComponentTreeUI(jFrame);

        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException  | NullPointerException ignored) {

        }





    }
}