package com.example.project.repo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.example.project.model.User;

@Repository
public class UserRepository {
    private Connection conn = null;
    private static UserRepository db = new UserRepository();

    public UserRepository() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            
            prop.load(input);
            
            Properties connectionProps = new Properties();
			connectionProps.put("user", prop.getProperty("db.user"));
			connectionProps.put("password", prop.getProperty("db.password"));

			String serverName = prop.getProperty("db.host");
			String port = prop.getProperty("db.port");
			String db = prop.getProperty("db.instance");

			conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + port + "/" + db, connectionProps);

			System.out.println("Connected to database");
		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}
    }

    //Query 1: Add User

    public static void addUser(String name, String password, boolean userTypeAdmin) {

        String query = "INSERT INTO user(username, password, user_type_admin) VALUES (?, ?, ?)";

        try (PreparedStatement insertStmt = db.conn.prepareStatement(query)) {

			insertStmt.setString(1, name);
            insertStmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            insertStmt.setBoolean(3, userTypeAdmin);
        
			insertStmt.executeUpdate();
            
		} catch (Exception ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}
	}

    //Query 2: Load all Users

    public static ArrayList<User> loadUsers() {
        ArrayList<User> list = new ArrayList<>();
        String queryString = "SELECT * FROM user";
        try (
            PreparedStatement queryStmt = db.conn.prepareStatement(queryString);
            ResultSet rs = queryStmt.executeQuery();) {

                while (rs.next()) {
                    User user = new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("user_type_admin"), rs.getLong("user_id"));
                    list.add(user);
                }

            }

        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }
        
        return list;

    }

    //Checks if a username is unique

    public static boolean isUsernameUnique(String username) {

        ArrayList<User> user = loadUsers();

        for (int i = 0; i<user.size(); i++) {
            if (user.get(i).getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    } 
}