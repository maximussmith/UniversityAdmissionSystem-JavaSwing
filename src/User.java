import java.io.*;
import java.lang.Cloneable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, Cloneable {

    private String username;
    private String password;

    private String name;
    private String birthdate;
    private String bio;

    private String gpa;
    private String classesTaken;
    private String major;

    private int appStatus;

    //inner class containing methods to write to file and modify list of users
    public class UserGateway {
        private List<User> users;
        private List<User> prevApps;

        public UserGateway() {
            users = new ArrayList<>();
            prevApps = new ArrayList<>();

            //add all objects from file to list of users
            try {
                FileInputStream F = new FileInputStream("USPW.txt");
                ObjectInputStream o = new ObjectInputStream(F);

                while (F.available() != 0) {
                    User user = (User) o.readObject();
                    users.add(user);
                }
            } catch (IOException e) {
                System.out.println("No objects in file");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //collect all previous applications
            try {
                FileInputStream F = new FileInputStream("Applications.txt");
                ObjectInputStream o = new ObjectInputStream(F);

                while (F.available() != 0) {
                    User user = (User) o.readObject();
                    prevApps.add(user);
                }
            } catch (IOException e) {
                System.out.println("No objects in file");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //add a user to the list
        public void saveUser(User user) {
            users.add(user);
        }

        //add application to closed app list and write it to file
        public void saveApp(User user) {
            prevApps.add(user);
            try {
                FileOutputStream writer = new FileOutputStream("Applications.txt");
                ObjectOutputStream ow = new ObjectOutputStream(writer);

                for (User tempUser : prevApps) {
                    ow.writeObject(tempUser);
                }

                ow.close();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //finds the given user within the users list and returns an index
        public int findMatch(User user) {
            for (int i = 0; i < users.size(); i++) {
                if (user.getUsername().equals(users.get(i).getUsername())) {
                    return i;
                }
            }
            return -1;
        }

        //write objects in list to file
        public void writeToFile() {
            try {
                FileOutputStream writer = new FileOutputStream("USPW.txt");
                ObjectOutputStream ow = new ObjectOutputStream(writer);

                for (User user : users) {
                    ow.writeObject(user);
                }

                ow.close();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public List<User> getUsers() {
            return users;
        }

        public List<User> getPrevApps() {
            return prevApps;
        }
    }

    //constructors
    public User() {
        username = null;
        password = null;
        name = "N/A";
        birthdate = "N/A";
        bio = "N/A";
        gpa = "N/A";
        classesTaken = "N/A";
        major = "N/A";
        appStatus = -1;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        name = "N/A";
        birthdate = "N/A";
        bio = "N/A";
        gpa = "N/A";
        classesTaken = "N/A";
        major = "N/A";
        appStatus = -1;
    }

    //clone method, returns same data with different memory address
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //get methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getBio() {
        return bio;
    }

    public String getGpa() {
        return gpa;
    }

    public String getClassesTaken() {
        return classesTaken;
    }

    public String getMajor() {
        return major;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public void setClassesTaken(String classesTaken) {
        this.classesTaken = classesTaken;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }

}