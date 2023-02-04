import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View {

    private JFrame frame;
    //create necessary items outside of methods in order to use get methods
    private JTextField userText, pwText, nameText, birthdateText, gpaText, classesText, adminCode, majorText, bioText;
    private JButton enterLogin, enterRegister, register, toLogin, profile, closeHomepage, enterApp, enterAdmin, adminLogin;
    private JButton closeProfile, saveProfile, closeApp, saveApp, submitApp, adminAccept, adminDeny, viewLater;
    private JLabel invalid;
    private JComboBox studentList, closedApps;

    public void loginView() {
        JLabel username, password, title;

        //create frame
        frame = new JFrame("Login");

        //title
        title = new JLabel();
        title.setText("Student Admission System");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        title.setBounds(30, 15, 350, 30);

        //Username label
        username = new JLabel("Username");
        username.setFont(new Font("SansSerif", Font.BOLD, 16));
        username.setBounds(25, 60, 100, 30);

        //password label
        password = new JLabel("Password");
        password.setFont(new Font("SansSerif", Font.BOLD, 16));
        password.setBounds(25, 100, 100, 30);

        //username text box
        userText = new JTextField();
        userText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        userText.setBounds(125, 62, 120, 30);

        //password text field
        pwText = new JTextField();
        pwText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pwText.setBounds(125, 102, 120, 30);

        //enter button
        enterLogin = new JButton("Enter");
        enterLogin.setBounds(125, 140, 120, 30);

        //register button
        register = new JButton("Register");
        register.setBounds(125, 175, 120, 30);

        //admin button
        adminLogin = new JButton("Admin");
        adminLogin.setBounds(275,225,100,30);

        //error notice
        invalid = new JLabel();
        invalid.setText("Invalid Username or Password");
        invalid.setBounds(100, 200, 200, 30);
        invalid.setVisible(false);

        //add all items to frame
        frame.add(invalid);
        frame.add(title);
        frame.add(enterLogin);
        frame.add(register);
        frame.add(username);
        frame.add(password);
        frame.add(userText);
        frame.add(pwText);
        frame.add(adminLogin);

        //set frame's size and make it visible
        frame.setSize(400,300);
        frameVisible();
    }

    public void registerView() {
        JLabel username, password, title;

        //create frame
        frame = new JFrame("Register");

        //title
        title = new JLabel("Create New User");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        title.setBounds(90, 15, 350, 30);

        //enter button
        enterRegister = new JButton("Create");
        enterRegister.setBounds(125, 140, 120, 30);

        //to login button
        toLogin = new JButton("To Login");
        toLogin.setBounds(125, 175, 120, 30);

        //Username label
        username = new JLabel("Username");
        username.setFont(new Font("SansSerif", Font.BOLD, 16));
        username.setBounds(25, 60, 100, 30);

        //username text box
        userText = new JTextField();
        userText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        userText.setBounds(125, 62, 120, 30);

        //password label
        password = new JLabel("Password");
        password.setFont(new Font("SansSerif", Font.BOLD, 16));
        password.setBounds(25, 100, 100, 30);

        //password text field
        pwText = new JTextField();
        pwText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        pwText.setBounds(125, 102, 120, 30);

        //add items to frame
        frame.add(title);
        frame.add(username);
        frame.add(password);
        frame.add(userText);
        frame.add(pwText);
        frame.add(toLogin);
        frame.add(enterRegister);

        //set frame size and make visible
        frame.setSize(400,300);
        frameVisible();
    }

    public void homepageView(User u) {
        JLabel title, profileHeader, profileName, profileBirthdate, profileBio, gpa, classes, major, status, recentApp;

        //create frame
        frame = new JFrame("Home Page");

        //title
        title = new JLabel("Student Admissions");
        title.setBounds(130,15,600,60);
        title.setFont(new Font("Serif", Font.BOLD, 50));

        //profile button
        profile = new JButton("Edit Profile");
        profile.setBounds(50,90,200,40);

        //application button
        enterApp = new JButton("Edit Application");
        enterApp.setBounds(450,90,200,40);

        //close button
        closeHomepage = new JButton("Log Out");
        closeHomepage.setBounds(50,400,200,40);

        //profile header
        profileHeader = new JLabel("Profile");
        profileHeader.setFont(new Font("SansSerif", Font.BOLD,24));
        profileHeader.setBounds(50, 150, 250, 40);

        //profile name
        profileName = new JLabel("Name: " + u.getName());
        profileName.setBounds(50, 190, 150, 30);

        //profile birthday
        profileBirthdate = new JLabel("Birthdate: " + u.getBirthdate());
        profileBirthdate.setBounds(50, 230, 150, 30);

        //profile bio
        profileBio = new JLabel("Bio: " + u.getBio());
        profileBio.setBounds(50,270,150,30);

        //recent app label
        recentApp = new JLabel("Recent Application");
        recentApp.setFont(new Font("SansSerif", Font.BOLD,24));
        recentApp.setBounds(450, 150, 250, 40);

        //gpa
        gpa = new JLabel("GPA: " + u.getGpa());
        gpa.setBounds(450,230,100,20);

        //classes taken
        classes = new JLabel("Classes Taken: " + u.getClassesTaken());
        classes.setBounds(450,270,300,20);

        //major
        major = new JLabel("Intended Major: " + u.getMajor());
        major.setBounds(450, 310, 200, 20);

        //submit button
        submitApp = new JButton("Submit Application");
        submitApp.setBounds(450,400,200,40);

        //status
        String s = switch (u.getAppStatus()) {
            case -1 -> "Not submitted";
            case 0 -> "Under Review";
            case 1 -> "Accepted";
            case 2 -> "Denied";
            default -> "N/A";
        };

        status = new JLabel("Status: " + s);
        status.setBounds(450, 190,200,20);

        //make application button visible if no application submitted or denied
        enterApp.setVisible(u.getAppStatus() == -1 || u.getAppStatus() == 2);
        submitApp.setVisible(u.getAppStatus() == -1 || u.getAppStatus() == 2);

        //add items to frame
        frame.add(recentApp);
        frame.add(gpa);
        frame.add(classes);
        frame.add(major);
        frame.add(status);
        frame.add(submitApp);

        frame.add(profileHeader);
        frame.add(profileName);
        frame.add(profileBirthdate);
        frame.add(profileBio);
        frame.add(closeHomepage);
        frame.add(enterApp);
        frame.add(profile);
        frame.add(title);

        //set frame size and make visible
        frame.setSize(750,500);
        frameVisible();
    }

    public void profileView() {
        JLabel name, birthday, bio;

        //create frame
        frame = new JFrame("Profile");

        //close button
        closeProfile = new JButton("Close");
        closeProfile.setBounds(50,200,100,50);

        //save button
        saveProfile = new JButton("Save");
        saveProfile.setBounds(240,200,100,50);

        //name label
        name = new JLabel("Name");
        name.setBounds(40, 25, 100, 30);

        //name text field
        nameText = new JTextField();
        nameText.setBounds(100, 25, 200, 30);

        //birthday label
        birthday = new JLabel("Birthday");
        birthday.setBounds(40, 75, 100, 30);

        //birthday text field
        birthdateText = new JTextField();
        birthdateText.setBounds(100, 75, 200, 30);

        //bio label
        bio = new JLabel("Bio");
        bio.setBounds(40, 125,150,30);

        //bio text field
        bioText = new JTextField();
        bioText.setBounds(100, 125, 200, 30);

        //add items to frame
        frame.add(saveProfile);
        frame.add(birthday);
        frame.add(birthdateText);
        frame.add(name);
        frame.add(nameText);
        frame.add(bio);
        frame.add(bioText);
        frame.add(closeProfile);

        //set frame size and make visible
        frame.setSize(400, 300);
        frameVisible();
    }

    public void applicationView() {
        JLabel gpa, classesTaken, major;

        //create frame
        frame = new JFrame("Application");

        //gpa label
        gpa = new JLabel("GPA");
        gpa.setBounds(35, 25, 100, 30);

        //gpa text field
        gpaText = new JTextField();
        gpaText.setBounds(130,25,200,30);

        //classes taken label
        classesTaken = new JLabel("Classes Taken");
        classesTaken.setBounds(35, 75, 100, 30);

        //classes text field
        classesText = new JTextField();
        classesText.setBounds(130,75,200,30);

        //major label
        major = new JLabel("Intended Major");
        major.setBounds(35, 125, 100, 30);

        //major text field
        majorText = new JTextField();
        majorText.setBounds(130,125,200,30);

        //close button
        closeApp = new JButton("Close");
        closeApp.setBounds(50,200,100,50);

        //save button
        saveApp = new JButton("Save");
        saveApp.setBounds(240,200,100,50);

        //add items to frame
        frame.add(gpa);
        frame.add(gpaText);
        frame.add(classesTaken);
        frame.add(classesText);
        frame.add(major);
        frame.add(majorText);
        frame.add(saveApp);
        frame.add(closeApp);

        //set frame size and make visible
        frame.setSize(400,300);
        frameVisible();
    }
    public void submitView(User u) {
        JLabel success, gpa, classesTaken, major;

        //create frame
        frame = new JFrame("Submit");

        //successful label
        success = new JLabel("Application successfully saved. Would you like to submit?");
        success.setFont(new Font("SansSerif", Font.BOLD, 14));
        success.setBounds(10,10,450,30);

        //gpa label
        gpa = new JLabel("GPA: " + u.getGpa());
        gpa.setBounds(20, 50, 200, 30);

        //classes taken label
        classesTaken = new JLabel("Classes Taken: " + u.getClassesTaken());
        classesTaken.setBounds(20,100,250,30);

        //major label
        major = new JLabel("Intended Major: " + u.getMajor());
        major.setBounds(20,150,250,30);

        //close button
        closeApp = new JButton("Close");
        closeApp.setBounds(20,200,120, 50);

        //submit button
        submitApp = new JButton("Submit");
        submitApp.setBounds(280,200,120, 50);

        //add items to frame
        frame.add(success);
        frame.add(gpa);
        frame.add(classesTaken);
        frame.add(major);
        frame.add(closeApp);
        frame.add(submitApp);

        //set frame size and make visible
        frame.setSize(450, 300);
        frameVisible();
    }
    public void adminLoginView(){
        JLabel adminLabel, title;

        //create frame
        frame = new JFrame("Admin");

        //title
        title = new JLabel();
        title.setText("Administrator Login");
        title.setBounds(90, 15, 200, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        //admin code label
        adminLabel = new JLabel("Admin Code");
        adminLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        adminLabel.setBounds(25, 100, 120, 30);

        //enter button
        enterAdmin = new JButton("Enter");
        enterAdmin.setBounds(125, 140, 120, 30);

        //admin code text field
        adminCode = new JTextField();
        adminCode.setFont(new Font("SansSerif", Font.PLAIN, 14));
        adminCode.setBounds(125, 102, 120, 30);

        //to login button
        toLogin = new JButton("To Login");
        toLogin.setBounds(125, 175, 120, 30);

        //invalid button
        invalid = new JLabel("Invalid code entered");
        invalid.setBounds(125,215,150,20);
        invalid.setVisible(false);

        //add items to frame
        frame.add(title);
        frame.add(enterAdmin);
        frame.add(adminCode);
        frame.add(adminLabel);
        frame.add(toLogin);
        frame.add(invalid);

        //set frame size and make visible
        frame.setSize(400,300);
        frameVisible();
    }
    public void adminPageView(List<User> users, List<User> apps){
        JLabel title, openAppsText, closedAppsText;

        //create frame
        frame = new JFrame("Admin Homepage");

        //title
        title = new JLabel("Student Admissions");
        title.setBounds(130,15,600,60);
        title.setFont(new Font("Serif", Font.BOLD, 50));

        //close button
        closeHomepage = new JButton("CLOSE");
        closeHomepage.setBounds(50,400,200,40);

        //adds all registered students to JComboBox
        studentList = new JComboBox<>();
        studentList.setBounds(100,150,200,50);


        closedApps = new JComboBox<>();
        closedApps.setBounds(425,150,200,50);

        //check for users who have submitted an application and have not been accepted/denied
        for (User user : users) {
            if (user.getAppStatus() == 0) {
                studentList.addItem(user.getUsername());
            }
        }
        for (int i = 0; i < apps.size(); i++) {
            User user = apps.get(i);
            closedApps.addItem(user.getUsername() + "#" + (i + 1));
        }

        //deselects from first user
        studentList.setSelectedIndex(-1);
        closedApps.setSelectedIndex(-1);

        openAppsText = new JLabel("Open Applications");
        openAppsText.setFont(new Font("SansSerif", Font.BOLD, 18));
        openAppsText.setBounds(100, 120, 200, 20);

        closedAppsText = new JLabel("Closed Applications");
        closedAppsText.setFont(new Font("SansSerif", Font.BOLD, 18));
        closedAppsText.setBounds(425, 120, 200, 20);

        //add items to frame
        frame.add(title);
        frame.add(studentList);
        frame.add(closedApps);
        frame.add(closeHomepage);
        frame.add(openAppsText);
        frame.add(closedAppsText);

        //set frame size and make visible
        frame.setSize(750,500);
        frameVisible();

    }

    public void adminAcceptView(User u){
        JLabel name, birthday, username, gpa, classes, major;

        //create frame
        frame = new JFrame("Student Info");

        //username
        username = new JLabel("Username: " + u.getUsername());
        username.setBounds(25,10,200,20);

        //student name
        name = new JLabel("Name: " + u.getName());
        name.setBounds(25,50,200,20);

        //birthdate
        birthday = new JLabel("Birthdate: " + u.getBirthdate());
        birthday.setBounds(25,90,200,20);

        //gpa
        gpa = new JLabel("GPA: " + u.getGpa());
        gpa.setBounds(25,130,100,20);

        //classes taken
        classes = new JLabel("Classes Taken: " + u.getClassesTaken());
        classes.setBounds(25,170,300,20);

        //major
        major = new JLabel("Intended Major: " + u.getMajor());
        major.setBounds(25, 210, 200, 20);

        //deny button
        adminDeny = new JButton("Deny");
        adminDeny.setBounds(20,300,100,50);

        //accept button
        adminAccept = new JButton("Accept");
        adminAccept.setBounds(260,300,100,50);

        //view later button
        viewLater = new JButton("View Later");
        viewLater.setBounds(140, 300, 100, 50);

        //add items to frame
        frame.add(username);
        frame.add(name);
        frame.add(birthday);
        frame.add(gpa);
        frame.add(classes);
        frame.add(major);
        frame.add(adminAccept);
        frame.add(adminDeny);
        frame.add(viewLater);

        //set frame size and make visible
        frame.setSize(400,400);
        frameVisible();
    }
    public void adminClosedAppView(User u) {
        JLabel name, birthday, username, gpa, classes, major, status;

        //create frame
        frame = new JFrame("Student Info");

        //username
        username = new JLabel("Username: " + u.getUsername());
        username.setBounds(25,10,200,20);

        //student name
        name = new JLabel("Name: " + u.getName());
        name.setBounds(25,50,200,20);

        //birthdate
        birthday = new JLabel("Birthdate: " + u.getBirthdate());
        birthday.setBounds(25,90,200,20);

        //gpa
        gpa = new JLabel("GPA: " + u.getGpa());
        gpa.setBounds(25,130,100,20);

        //classes taken
        classes = new JLabel("Classes Taken: " + u.getClassesTaken());
        classes.setBounds(25,170,300,20);

        //major
        major = new JLabel("Intended Major: " + u.getMajor());
        major.setBounds(25, 210, 200, 20);

        //status
        String s = switch (u.getAppStatus()) {
            case 1 -> "Accepted";
            case 2 -> "Denied";
            default -> null;
        };

        status = new JLabel("Status: " + s);
        status.setBounds(25, 250,200,20);

        //view later button
        viewLater = new JButton("Close");
        viewLater.setBounds(140, 300, 100, 50);

        //add items to frame
        frame.add(username);
        frame.add(name);
        frame.add(birthday);
        frame.add(gpa);
        frame.add(classes);
        frame.add(major);
        frame.add(status);
        frame.add(viewLater);

        //set frame size and make visible
        frame.setSize(400,400);
        frameVisible();
    }

    private void frameVisible() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void closeWindow() {
        frame.dispose();
    }

             //BUTTONS//
    //------------------------------//
    public JButton getEnterLogin() {
        return enterLogin;
    }

    public JButton getEnterRegister() {
        return enterRegister;
    }

    public JButton getRegister() {
        return register;
    }

    public JButton getToLogin() {
        return toLogin;
    }

    public JButton getEnterApp() {
        return enterApp;
    }

    public JButton getProfile() {
        return profile;
    }

    public JButton getCloseHomepage() {
        return closeHomepage;
    }

    public JButton getSaveProfile() {
        return saveProfile;
    }

    public JButton getCloseProfile() {
        return closeProfile;
    }

    public JButton getSubmitApp() {
        return submitApp;
    }

    public JButton getSaveApp() {
        return saveApp;
    }

    public JButton getCloseApp() {
        return closeApp;
    }

    public JButton getEnterAdmin() {
        return enterAdmin;
    }

    public JButton getAdminLogin() {
        return adminLogin;
    }

    public JButton getAdminAccept() {
        return adminAccept;
    }

    public JButton getAdminDeny() {
        return adminDeny;
    }

    public JButton getViewLater() {
        return viewLater;
    }

    //TEXT FIELDS//
    //-------------------------//
    public JTextField getUserText() {
        return userText;
    }

    public JTextField getPwText() {
        return pwText;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public JTextField getBirthdateText() {
        return birthdateText;
    }

    public JTextField getBioText() {
        return bioText;
    }

    public JTextField getGpaText() {
        return gpaText;
    }

    public JTextField getClassesText() {
        return classesText;
    }

    public JTextField getMajorText() {
        return majorText;
    }

    public JTextField getAdminCode() {
        return adminCode;
    }

            //LABELS//
    //-----------------------//
    public JLabel getInvalid() {
        return invalid;
    }

                //COMBO BOX//
    //--------------------------------------//
    public JComboBox getStudentList() {
        return studentList;
    }

    public JComboBox getClosedApps() {
        return closedApps;
    }

}