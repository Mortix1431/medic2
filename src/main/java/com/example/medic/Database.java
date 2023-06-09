package com.example.medic;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Map;

public class Database {

    public static final String URL = "jdbc:mysql://45.132.1.160:3306/praktika";
    public static final String USER = "root";
    public static final String PASSWORD = "Dospoker24";

    public static String code = "1234";

    public static AsyncTask add = new addTask();
    public static AsyncTask addInt = new addTaskInt();
    public static AsyncTask sendCode = new codeSend();
    public static AsyncTask get = new getTask();

}

class addTask extends AsyncTask<String, Void, Map<String, String>> {

    @Override
    protected Map<String, String> doInBackground(String... params) {
        try (Connection connection = (Connection) DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD)) {
            String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", params[0], params[1], params[2]);
            Log.e("Database", "Внесены значения в бд");
        }catch (Exception e) {
            Log.e("InfoAsyncTask", "Error reading school information", e);
        }
        return null;
    }
}

class addTaskInt extends AsyncTask<String, Void, Map<String, String>> {

    @Override
    protected Map<String, String> doInBackground(String... params) {
        try (Connection connection = (Connection) DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD)) {
            @SuppressLint("DefaultLocale") String sql = String.format("INSERT INTO %s (%s) VALUES (%d)", params[0], params[1], Integer.getInteger(params[2]));
            Log.e("Database", "Внесены значения в бд");
        }catch (Exception e) {
            Log.e("InfoAsyncTask", "Error reading school information", e);
        }
        return null;
    }
}

class codeSend extends AsyncTask<String, Void, Map<String, String>> {

    @Override
    protected Map<String, String> doInBackground(String... params) {
        try (Connection connection = (Connection) DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD)) {
            @SuppressLint("DefaultLocale") String sql = String.format("INSERT INTO code (code,mail) VALUES ( %d, %s)", Integer.getInteger(params[1]), params[2]);
            Statement stmt = (Statement) connection.createStatement();
            stmt.executeQuery(sql);
            Log.e("Database", "Внесены значения в бд");
        }catch (Exception e) {
            Log.e("InfoAsyncTask", "Error reading school information", e);
        }
        return null;
    }
}

class getTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        try (Connection connection = (Connection) DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD)) {
            @SuppressLint("DefaultLocale") String sql = String.format("SELECT FROM %s WHERE %s = %s", params[0], params[1], params[2]);
            Statement stmt = (Statement) connection.createStatement();

            // executing SELECT query
            ResultSet rs = stmt.executeQuery(sql);
            Log.e("Database", "Внесены значения в бд");
            String res = Integer.toString(rs.getInt(3));
            return res;
        }catch (Exception e) {
            Log.e("InfoAsyncTask", "Error reading school information", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String integer) {
        super.onPostExecute(integer);
        //Database.code = integer.toString();
        code_email.code= integer;
    }
}
