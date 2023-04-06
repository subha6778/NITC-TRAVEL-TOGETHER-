package CommonActionListeners;

import DatabaseObjectWrapper.Person;
import GUI.EditProfile;
import GUI.LandingPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileEditListener implements ActionListener {

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    Person currentPerson;
    LandingPage lastPage;

    public ProfileEditListener(LandingPage lastPage, Person currentPerson) {
       this.currentPerson=currentPerson;
       this.lastPage=lastPage;


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        lastPage.setVisible(false);
        new EditProfile(lastPage,currentPerson);
    }
}

