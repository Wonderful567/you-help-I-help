<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%--
  Created by IntelliJ IDEA.
  User: 14234
  Date: 2022/12/17
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>

<body >
    <%
        Map<String,String> map = new HashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_user?" + "user=root&password=XUmai789");
            System.out.println("数据库连接成功！");
    %>
    注册请求：
    <table>
        <tr>
            <th>用户名</th>
            <th>住址</th>
            <th>手机号</th>
            <th>密码</th>
            <th>是否通过</th>
        </tr>
        <%
            Statement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM tb_user2 where isCheck=false;"; //查询语句
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
        %>
        <tr>
            <td><%=rs.getString("userID")%></td>
            <td><%=rs.getString("address")%></td>
            <td><%=rs.getString("phone")%></td>
            <td><%=rs.getString("psd")%></td>
            <td>
            <form action="admin2" method="get">
                <input type="hidden" name="userID" value=<%=rs.getString("userID")%>>
                <input type="submit" name="pass" value="通过">
                <input type="submit" name="pass" value="拒绝">
            </form>
            </td>
        </tr>

        <%
            connect.close();
            }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据库连接失败");
            }

        %>
    </table>


        <br>
        <br>
        <br>
        <br>
        <br>
        <%
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            System.out.println("数据库连接成功！");
            Statement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM tb_list;"; //查询语句
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            out.print("查找：");
            out.print("<br/>");
        %>
    <form action="find" method="get">
        <select name="option">
            <% while (rs.next()){
            map.put(rs.getString(1),rs.getString(2));%>
            <option value=<%=rs.getString(1)%> ><%=rs.getString(2)%></option>
            <%}%>
        </select>
        关键字匹配(可以不填)：<input type="text" name="key">

        <input type="submit" value="查询"><br>

    </form>

        <%
        connect.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据库连接失败");
            }
        %>

        <form action="name" method="get">
            <select name="option">
                <% for (Map.Entry<String,String> entry:map.entrySet()){ %>
                <option  value=<%=entry.getKey()%> ><%=entry.getValue()%></option>
                <%}%>
            </select>
            改名成：<br>
            <input type="text" name="new_name">
            <input type="submit" value="修改">
        </form>
        <form action="name2" method="get">
            <br>
            建立新的种类<br>
            新类型名称：<br>
            <input type="text" name="view_name"><br>
            表名（建议为tb_+名称英语例如tb_food）：<br>
            <input type="text" name="list_name"><br>
            <input type="submit" value="修改">
        </form>




</body>
</html>
