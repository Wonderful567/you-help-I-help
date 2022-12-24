package com.test.db;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;
import java.sql.SQLException;

public class GoodsDB {
    static public void addGoods(String option,String userID,String goods,int num,String detail,String address,String cont){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not loading Driver");
        }


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            if (connection != null) {
                System.out.println("Connect successfully!");
            }
            String sql = "INSERT into wcndm VALUES (NULL,?,?,?,?,?,?)";
            sql = sql.replace("wcndm",option);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,goods);
            pst.setInt(2,num);
            pst.setString(3,userID);
            pst.setString(4,address);
            pst.setString(5,cont);
            pst.setString(6,detail);
            pst.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    static public void changeName(String option,String new_name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not loading Driver");
        }


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            if (connection != null) {
                System.out.println("Connect successfully!");
            }
            String sql = "UPDATE tb_list SET view_name=? WHERE list_name=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,new_name);
            pst.setString(2,option);
            pst.executeUpdate();

            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    static public void addTable(String view_name,String list_name){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not loading Driver");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            if (connection != null) {
                System.out.println("Connect successfully!");
            }
            String sql = "INSERT INTO tb_list VALUES (?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,list_name);
            pst.setString(2,view_name);
            pst.executeUpdate();

            connection.close();
            GoodsDB.addTable2(list_name);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    static public void addTable2(String list_name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not loading Driver");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            if (connection != null) {
                System.out.println("Connect successfully!");
            }
            String sql = "CREATE TABLE tb_name(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20), num INT,attri VARCHAR(20), address VARCHAR(50),cont VARCHAR(50), detail VARCHAR(50));";
            sql=sql.replace("tb_name", list_name);
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void delete(String tb_list,int id,int num){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not loading Driver");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            if (connection != null) {
                System.out.println("Connect successfully!");
            }
            String sql = "UPDATE tb_list SET num = ? WHERE id=?;";
            sql=sql.replace("tb_list",tb_list);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,num);
            pst.setInt(2,id);
            pst.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void drop(String tb_list,int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not loading Driver");
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            if (connection != null) {
                System.out.println("Connect successfully!");
            }
            String sql = "DELETE FROM tb_list WHERE id=?;";
            sql=sql.replace("tb_list",tb_list);
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
