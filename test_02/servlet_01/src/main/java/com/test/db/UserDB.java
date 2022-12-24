package com.test.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserDB {
    static public HashMap<String,String> getID_PSD(){
        //获得db_user中的用户名和密码，暂存在HashMap里
        HashMap<String,String> res = new HashMap<>();
        Connection connect = null;
        // Statement 类的对象向 mysql 发送 sql 语句进行操作
        Statement stmt = null;
        // ResultSet 类的对象接收操作之后得到的结果
        ResultSet rs = null;
        try {
            // 加载驱动，固定语句
            // 使用这个必须处理异常
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not loading Driver");
        }
        try {
            // 下面这一行也是固定语句，getConnection括号里面的模式：
            // “jdbc:mysql://数据库的地址（mysql server 的地址）/数据库名字?（要mysql中操作的数据库的名字）”
            // 后面再 + 上用来登录的用户名和密码
            // 同时，使用这个函数，必须处理异常
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_user?" + "user=root&password=XUmai789");
            if (connect != null) {
                System.out.println("Connect successfully!");
            }
            try {
                // 先创造一个可以给 mysql 发送 sql 语句的
                stmt = connect.createStatement();
                // executeQuery 用于发送 sql 语句，括号内的就是 sql 语句
                rs = stmt.executeQuery("select * from tb_user2 where isCheck=true");
                // 输出数据的用法
                //rs.next(); 表示下一个得到的结果数据集，如果不为空，就继续输出
                // 每一次输出完之后，会自动切换到下一个数据集，相当于换行，select * from 表，这样操作一次看到结果
                // 的图就知道是为什么了
                while (rs.next()){
                    res.put(rs.getString(1),rs.getString(4));
                    // rs.get数据类型（括号内是列数（第 n 列））
                }
            }
            catch (SQLException e){ // 老规矩，必须处理异常
                e.printStackTrace();
            }
        } catch (SQLException e) { // 这个getConnection 规定必须处理异常
            e.printStackTrace();
        }
        finally {
            try {
                // 关闭连接
                connect.close(); // 规定必须处理异常
                System.out.println("Shutdown connection successfully!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }
    static public void addMessage(String id,String address,String phone,String psd){
        Connection connect = null;
        String sql = "insert into tb_user2 values(?,?,?,?,?)";
        try {
            // 加载驱动，固定语句
            // 使用这个必须处理异常
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not loading Driver");
        }
        try {
            // 下面这一行也是固定语句，getConnection括号里面的模式：
            // “jdbc:mysql://数据库的地址（mysql server 的地址）/数据库名字?（要mysql中操作的数据库的名字）”
            // 后面再 + 上用来登录的用户名和密码
            // 同时，使用这个函数，必须处理异常
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_user?" + "user=root&password=XUmai789");
            if (connect != null) {
                System.out.println("Connect successfully!");
            }
            try {
                PreparedStatement pst = connect.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
                pst.setString(1, id);
                pst.setString(2, address);
                pst.setString(3,phone);
                pst.setString(4,psd);
                pst.setInt(5,0);
                pst.executeUpdate();

            }
            catch (Exception e){ // 老规矩，必须处理异常
                e.printStackTrace();
            }
        } catch (SQLException e) { // 这个getConnection 规定必须处理异常
            e.printStackTrace();
        }
        finally {
            try {
                // 关闭连接
                connect.close(); // 规定必须处理异常
                System.out.println("Shutdown connection successfully!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /*
    static public HashMap<String,List<String>> getList(){
        //获得db_user中的用户名和密码，暂存在HashMap里
        HashMap<String,List<String>> res = null;
        Connection connect = null;
        // Statement 类的对象向 mysql 发送 sql 语句进行操作
        Statement stmt = null;
        // ResultSet 类的对象接收操作之后得到的结果
        ResultSet rs = null;
        try {
            // 加载驱动，固定语句
            // 使用这个必须处理异常
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Could not loading Driver");
        }
        try {
            // 下面这一行也是固定语句，getConnection括号里面的模式：
            // “jdbc:mysql://数据库的地址（mysql server 的地址）/数据库名字?（要mysql中操作的数据库的名字）”
            // 后面再 + 上用来登录的用户名和密码
            // 同时，使用这个函数，必须处理异常
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_user?" + "user=root&password=XUmai789");
            if (connect != null) {
                System.out.println("Connect successfully!");
            }
            try {
                // 先创造一个可以给 mysql 发送 sql 语句的
                stmt = connect.createStatement();
                // executeQuery 用于发送 sql 语句，括号内的就是 sql 语句
                rs = stmt.executeQuery("select * from tb_user2 where isCheck=false");
                // 输出数据的用法
                //rs.next(); 表示下一个得到的结果数据集，如果不为空，就继续输出
                // 每一次输出完之后，会自动切换到下一个数据集，相当于换行，select * from 表，这样操作一次看到结果
                // 的图就知道是为什么了
                List<String> list = new ArrayList<>();
                while (rs.next()){
                    list.add(rs.getString(1));
                    list.add(rs.getString(2));
                    list.add(rs.getString(3));
                    list.add(rs.getString(4));
                    // rs.get数据类型（括号内是列数（第 n 列））
                    res.put(rs.getString(1),list);
                }

            }
            catch (SQLException e){ // 老规矩，必须处理异常
                e.printStackTrace();
            }
        } catch (SQLException e) { // 这个getConnection 规定必须处理异常
            e.printStackTrace();
        }
        finally {
            try {
                // 关闭连接
                connect.close(); // 规定必须处理异常
                System.out.println("Shutdown connection successfully!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }

     */
    static public void Pass(String userID) {
        Connection connect =null;
        //通过
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_user?" + "user=root&password=XUmai789");
            System.out.println("连接成功");
            String sql = "UPDATE tb_user2 SET isCheck=1 WHERE userID=?;"; //查询语句
            PreparedStatement pst = connect.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
            pst.setString(1,userID);
            pst.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                // 关闭连接
                connect.close(); // 规定必须处理异常
                System.out.println("Shutdown connection successfully!");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    static public void UnPass(String userID) {
        Connection connect = null;
        //通过
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_user?" + "user=root&password=XUmai789");
            System.out.println("连接成功");
            String sql = "DELETE FROM tb_user2 WHERE userID=?"; //查询语句
            PreparedStatement pst = connect.prepareStatement(sql);//用来执行SQL语句查询，对sql语句进行预编译处理
            pst.setString(1, userID);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接
                connect.close(); // 规定必须处理异常
                System.out.println("Shutdown connection successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
