package UserPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    private ArrayList<User> listofUsers = new ArrayList<>();

    public UserList() {
    }

    public UserList(ArrayList<User> listofUsers) {
        this.listofUsers = listofUsers;
    }

    public ArrayList<User> getListofUsers() {
        return listofUsers;
    }

    public void setListofUsers(ArrayList<User> listofUsers) {
        this.listofUsers = listofUsers;
    }

    public void readUserFile(String filename){
        File file = new File(filename);
        Scanner sc = null;

        try {
            sc = new Scanner(file);
        } catch (Exception e) {
            System.err.println("File not found.");
        }

        while (sc.hasNextLine()){
            String data_str = sc.nextLine();
            String[] data_split = data_str.split(",");
            String id_str = data_split[0];
            int id = Integer.parseInt(id_str);
            String userType = data_split[1];
            String name = data_split[2];
            String other = data_split[3];
            User user = UserFactory.CreateUser(id, userType, name, other);
            listofUsers.add(user);
        }
    }
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User List\n");
        for (User user: listofUsers){
            sb.append(user.toString()).append("\n");
        }

        return sb.toString();
    }

}
