package com.example.project.repo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.example.project.model.TextInput;

@Repository
public class TextInputRepository {

    private Connection conn = null;
    private static TextInputRepository db = new TextInputRepository();

    public TextInputRepository() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
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

    // Query 1: List all text inputs

    public static TextInput[] loadTextInputs() {
        ArrayList<TextInput> list = new ArrayList<>();
        String queryString = "SELECT * FROM text_input";
        try (
                PreparedStatement queryStmt = db.conn.prepareStatement(queryString);
                ResultSet rs = queryStmt.executeQuery();) {

            while (rs.next()) {
                TextInput text = new TextInput(rs.getString("text"), rs.getLong("time"), rs.getLong("text_id"),
                        rs.getString("username"));
                list.add(text);
            }

        }

        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }

        TextInput[] array = new TextInput[list.size()];
        return list.toArray(array);

    }

    // Query 2: List all text inputs by user

    public static TextInput[] loadTextInputsByUser(long userId) {
        ArrayList<TextInput> list = new ArrayList<>();
        String queryString = "SELECT * FROM text_input WHERE user_id = ?";
        try (
                PreparedStatement queryStmt = db.conn.prepareStatement(queryString);) {

            queryStmt.setLong(1, userId);

            try (ResultSet rs = queryStmt.executeQuery()) {
                while (rs.next()) {
                    TextInput text = new TextInput(rs.getString("text"), rs.getLong("time"), rs.getLong("text_id"),
                            rs.getString("username"));
                    list.add(text);
                }
            }

        }

        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }

        TextInput[] array = new TextInput[list.size()];
        return list.toArray(array);

    }

    // Query 3: List all text inputs containing a string

    public static TextInput[] loadTextInputsByContent(String content) {
        ArrayList<TextInput> list = new ArrayList<>();
        String queryString = "SELECT * FROM text_input WHERE text LIKE %?%";
        try (
                PreparedStatement queryStmt = db.conn.prepareStatement(queryString);) {

            queryStmt.setString(1, content);

            try (ResultSet rs = queryStmt.executeQuery()) {
                while (rs.next()) {
                    TextInput text = new TextInput(rs.getString("text"), rs.getLong("time"), rs.getLong("text_id"),
                            rs.getString("username"));
                    list.add(text);
                }
            }

        }

        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }

        TextInput[] array = new TextInput[list.size()];
        return list.toArray(array);

    }

    // Query 4: List all text inputs that were inputted between two times

    public static TextInput[] loadTextInputsByTime(long startTime, long endTime) {
        ArrayList<TextInput> list = new ArrayList<>();
        String queryString = "SELECT * FROM text_input WHERE time BETWEEN startTime AND endTime";
        try (
                PreparedStatement queryStmt = db.conn.prepareStatement(queryString);) {

            queryStmt.setLong(1, startTime);
            queryStmt.setLong(2, endTime);

            try (ResultSet rs = queryStmt.executeQuery()) {
                while (rs.next()) {
                    TextInput text = new TextInput(rs.getString("text"), rs.getLong("time"), rs.getLong("text_id"),
                            rs.getString("username"));
                    list.add(text);
                }
            }

        }

        catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }

        TextInput[] array = new TextInput[list.size()];
        return list.toArray(array);

    }

    // Query 5: Insert new text input

    public static void addTextInput(String text, long time, String username) {

        String query = "INSERT INTO text_input(text, time, username) VALUES (?, ?, ?)";

        try (PreparedStatement insertStmt = db.conn.prepareStatement(query)) {

            insertStmt.setString(1, text);
            insertStmt.setLong(2, time);
            insertStmt.setString(3, username);

            insertStmt.executeUpdate();

        } catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }
    }

    // Query 6: Delete text input by text input id

    public static void deleteTextInput(long time) {
        String query = "DELETE FROM text_input WHERE time = ? LIMIT 1";

        try (PreparedStatement deleteStmt = db.conn.prepareStatement(query)) {

            deleteStmt.setLong(1, time);
            deleteStmt.executeUpdate();

        } catch (Exception ex) {
            System.err.println(ex);
            ex.printStackTrace(System.err);
        }
    }

}