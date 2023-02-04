import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Controller implements ActionListener, ItemListener {

    private User model;
    private View view;
    private User.UserGateway userGateway;

    public Controller(User model, View view) {
        this.model = model;
        this.view = view;
        this.userGateway = model.new UserGateway();
    }

                    //UPDATE VIEWS//
    //------------------------------------------------//
    public void updateLoginView() {
        view.loginView();

        //create listeners for login frame
        view.getEnterLogin().addActionListener(this);
        view.getRegister().addActionListener(this);
        view.getAdminLogin().addActionListener(this);
    }

    public void updateRegisterView() {
        view.registerView();

        //create listeners for register frame
        view.getEnterRegister().addActionListener(this);
        view.getToLogin().addActionListener(this);
    }

    public void updateHomepageView() {
        view.homepageView(model);

        //create listeners for homepage frame
        view.getProfile().addActionListener(this);
        view.getEnterApp().addActionListener(this);
        view.getCloseHomepage().addActionListener(this);
        view.getSubmitApp().addActionListener(this);
    }

    public void updateProfileView() {
        view.profileView();

        //add listeners for profile frame
        view.getSaveProfile().addActionListener(this);
        view.getCloseProfile().addActionListener(this);
    }

    public void updateApplicationView() {
        view.applicationView();

        //add listeners for the application frame
        view.getSaveApp().addActionListener(this);
        view.getCloseApp().addActionListener(this);
    }

    public void updateSubmitView() {
        view.submitView(model);

        //add listeners to submit frame
        view.getCloseApp().addActionListener(this);
        view.getSubmitApp().addActionListener(this);
    }

    public void updateAdminLoginView() {
        view.adminLoginView();

        //add listeners to admin login frame
        view.getEnterAdmin().addActionListener(this);
        view.getToLogin().addActionListener(this);
    }

    public void updateAdminPageView() {
        model = new User();
        view.adminPageView(userGateway.getUsers(), userGateway.getPrevApps());

        //add listeners for admin homepage
        view.getStudentList().addItemListener(this);
        view.getClosedApps().addItemListener(this);
        view.getCloseHomepage().addActionListener(this);
    }

    public void updateAdminAcceptView() {
        view.adminAcceptView(model);

        //add listeners for admin accept view
        view.getAdminAccept().addActionListener(this);
        view.getAdminDeny().addActionListener(this);
        view.getViewLater().addActionListener(this);
    }

    public void updateAdminClosedAppView() {
        view.adminClosedAppView(model);

        //add listeners for closed app view
        view.getViewLater().addActionListener(this);
    }


                //LISTENER BUTTON ACTIONS//

    //---------------------------------------------------------//
    @Override
    public void actionPerformed(ActionEvent e) {
                //LOGIN FRAME BUTTONS//
        //-------------------------------------------//
        if (e.getSource() == view.getEnterLogin()) {

            //login username and password
            model = new User(view.getUserText().getText(), view.getPwText().getText());

            //checks if both the username and password are correct, opens homepage if they are
            if (userGateway.findMatch(model) != -1 && model.getPassword().equals(userGateway.getUsers().get(userGateway.findMatch(model)).getPassword())) {
                model = userGateway.getUsers().get(userGateway.findMatch(model));
                view.closeWindow();
                updateHomepageView();
            }
            else {
                view.getInvalid().setVisible(true);
            }

        }

        //close login frame and open register frame
        else if (e.getSource() == view.getRegister()) {
            view.closeWindow();
            updateRegisterView();
        }

                    //REGISTER FRAME BUTTONS//
        //---------------------------------------------------//
        else if (e.getSource() == view.getEnterRegister()) {
            //set model's username and password to text field
            model = new User(view.getUserText().getText(), view.getPwText().getText());

            //check for a duplicate username, if there is then set duplicate to true
            boolean duplicate = false;
            for (int i = 0; i < userGateway.getUsers().size(); i++) {
                User user = userGateway.getUsers().get(i);
                if (user.getUsername().equals(model.getUsername())) {
                    duplicate = true;
                    break;
                }
            }

            //check if username or password field was left blank
            if (model.getUsername().isEmpty() || model.getPassword().isEmpty()) {
                JOptionPane.showMessageDialog(null, "One or more fields left blank. Please try again.");
                view.closeWindow();
                updateRegisterView();
            }
            //write object to file if duplicate is false, else do not
            else {
                if (!duplicate) {
                    userGateway.saveUser(model);
                    userGateway.writeToFile();
                    JOptionPane.showMessageDialog(null, "Successfully registered new Account");
                    view.closeWindow();
                    updateLoginView();

                } else {
                    view.getInvalid().setVisible(true);
                    JOptionPane.showMessageDialog(null, "User already exists, please choose a new username.");
                    view.closeWindow();
                    updateRegisterView();
                }
            }
        }
        //close register frame and open login frame
        else if (e.getSource() == view.getToLogin()) {
            view.closeWindow();
            updateLoginView();
        }
                    //HOMEPAGE FRAME BUTTONS//
        //--------------------------------------------//
        //close homepage frame and open profile frame
        else if (e.getSource() == view.getProfile()) {
            view.closeWindow();
            updateProfileView();
        }
        //close homepage frame and open application frame
        else if (e.getSource() == view.getEnterApp()) {
            view.closeWindow();
            updateApplicationView();
        }
        //close homepage frame and open login frame
        else if (e.getSource() == view.getCloseHomepage()) {
            view.closeWindow();
            updateLoginView();
        }

                     //PROFILE FRAME BUTTONS//
        //------------------------------------------------//
        //updates user's profile
        else if (e.getSource() == view.getSaveProfile()) {
            //find user match
            model = userGateway.getUsers().get(userGateway.findMatch(model));

            //check for blank text fields
            if(!view.getNameText().getText().isEmpty()) {
                model.setName(view.getNameText().getText());
            }
            if (!view.getBirthdateText().getText().isEmpty()) {
                model.setBirthdate(view.getBirthdateText().getText());
            }
            if (!view.getBioText().getText().isEmpty()) {
                model.setBio(view.getBioText().getText());
            }

            //save data to file
            userGateway.writeToFile();

            JOptionPane.showMessageDialog(null, "Successfully updated Profile");
        }
        //closes profile frame and opens homepage frame
        else if (e.getSource() == view.getCloseProfile()) {
            view.closeWindow();
            updateHomepageView();
        }
                    //APPLICATION FRAME BUTTONS//
        //---------------------------------------------------//
        //submits user application
        else if (e.getSource() == view.getSubmitApp()) {
            //finds user match
            model = userGateway.getUsers().get(userGateway.findMatch(model));

            //check if any fields left blank
            if (model.getGpa() == "N/A" || model.getClassesTaken() == "N/A" || model.getMajor() == "N/A") {
                model.setAppStatus(-1);

                JOptionPane.showMessageDialog(null, "At least one field left blank, please check all fields.");
            }
            else {
                model.setAppStatus(0);

                JOptionPane.showMessageDialog(null, "Successfully submitted Application");
            }

            //writes user back to file
            userGateway.writeToFile();

            view.closeWindow();
            updateHomepageView();
        }
        else if (e.getSource() == view.getSaveApp()) {
            //find user match
            model = userGateway.getUsers().get(userGateway.findMatch(model));

            //check for blank fields
            if (!view.getGpaText().getText().isEmpty()) {
                model.setGpa(view.getGpaText().getText());
            }
            if (!view.getClassesText().getText().isEmpty()) {
                model.setClassesTaken(view.getClassesText().getText());
            }
            if (!view.getMajorText().getText().isEmpty()) {
                model.setMajor(view.getMajorText().getText());
            }
            model.setAppStatus(-1);

            //write user to file
            userGateway.writeToFile();

            //open submit frame
            view.closeWindow();
            updateSubmitView();
        }
        //closes application frame and opens homepage
        else if (e.getSource() == view.getCloseApp()) {
            view.closeWindow();
            updateHomepageView();
        }

                //ADMIN LOGIN FRAME VIEW//
        //----------------------------------------------//
        //closes login frame and opens admin login frame
        else if (e.getSource() == view.getAdminLogin()){
            view.closeWindow();
            updateAdminLoginView();
        }

        //checks for valid admin code, if valid then enters admin page
        else if (e.getSource() == view.getEnterAdmin()) {
            //check for admin code
            if (view.getAdminCode().getText().equals("admin123")){
                view.closeWindow();
                updateAdminPageView();
            }
            else {
                view.getInvalid().setVisible(true);
            }

        }
                    //ADMIN ACCEPT FRAME//
        //------------------------------------------------//
        //changes application status to accepted
        else if (e.getSource() == view.getAdminAccept()) {
            //finds user match
            model = userGateway.getUsers().get(userGateway.findMatch(model));

            model.setAppStatus(1);

            //writes user back to file
            userGateway.writeToFile();

            userGateway.saveApp((User) model.clone());

            JOptionPane.showMessageDialog(null, "Successfully accepted Application");

            view.closeWindow();
            updateAdminPageView();
        }
        //changes application status to denied
        else if (e.getSource() == view.getAdminDeny()) {
            //finds user match and removes from list in order to edit
            model = userGateway.getUsers().get(userGateway.findMatch(model));

            model.setAppStatus(2);

            //writes user back to file
            userGateway.writeToFile();

            userGateway.saveApp((User) model.clone());

            JOptionPane.showMessageDialog(null, "Successfully denied Applicant");

            view.closeWindow();
            updateAdminPageView();
        }
        //closes the admin application screen and opens the admin page
        else if (e.getSource() == view.getViewLater()) {
            view.closeWindow();
            updateAdminPageView();
        }
    }

                        //ADMIN COMBO BOX//
    //---------------------------------------------------------------//
    @Override
    public void itemStateChanged(ItemEvent e) {
        //finds user clicked in the open applications
        if(e.getStateChange() == ItemEvent.SELECTED && e.getSource() == view.getStudentList()){
            //find user selected in combobox
            model.setUsername((String) e.getItem());
            model = userGateway.getUsers().get(userGateway.findMatch(model));

            view.closeWindow();
            updateAdminAcceptView();
        }
        //finds user clicked in the closed applications
        else if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == view.getClosedApps()){
            //find user selected in combobox
            int index = view.getClosedApps().getSelectedIndex();
            model = (User) userGateway.getPrevApps().get(index).clone();

            view.closeWindow();
            updateAdminClosedAppView();
        }
    }
}