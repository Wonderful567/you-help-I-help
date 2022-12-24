<%@ page import="java.sql.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.mysql.cj.x.protobuf.MysqlxDatatypes" %><%--
  Created by IntelliJ IDEA.
  User: 14234
  Date: 2022/12/17
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  ServletContext sc = request.getServletContext();
    String user  = (String) sc.getAttribute("userID");
    out.print("欢迎"+user);
    Map<String,String> map = new HashMap<>();
    try {
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
                map.put(rs.getString(1),rs.getString(2));
                %>
            <option value=<%=rs.getString(1)%> ><%=rs.getString(2)%></option>
            <%}%>
        </select>
        关键字匹配(可以不填)：<input type="text" name="key">
        <input type="hidden" name="who" value="user">
        <input type="submit" value="查询"><br>

    </form>

   //添加物品
    <form action="add" method="get">
        <select name="option">
            <% for (Map.Entry<String,String> entry:map.entrySet()){ %>
            <option  value=<%=entry.getKey()%> ><%=entry.getValue()%></option>
            <%}%>
        </select>
                   添加的物品：      <input type="text" name="goods"><br>
                   数量：           <input type="number" name="num"><br>
        添加描述(可以是保质期，说明等)：<input type="text" name="detail"><br>
                   地址             <input type="text" name="address"><br>
              联系方式(手机或邮箱)    <input type="text" name="con"><br>
        <input type="hidden" name="userID" value=<%=user%>>
        <input type="submit" value="添加物品">

    </form>


   <%
       }catch (Exception e) {
       e.printStackTrace();
       System.out.println("数据库连接失败");
       }
   %>
</body>
</html>
