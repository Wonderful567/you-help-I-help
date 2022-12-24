<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: 14234
  Date: 2022/12/20
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        HashMap<String,String> hm = (HashMap<String, String>) request.getAttribute("hm");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/db_goods?" + "user=root&password=XUmai789");
            System.out.println("数据库连接成功！");
            Statement stmt = null;

            ResultSet rs = null;

            String sql = "SELECT * FROM nmlgcb WHERE INSTR(name,?) or INSTR(detail,?);"; //查询语句
            sql = sql.replace("nmlgcb",hm.get("option"));
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1,hm.get("key"));
            preparedStatement.setObject(2,hm.get("key"));


            ResultSet rss = preparedStatement.executeQuery();
    %>
    <table>
        <tr>
            <th>物品</th>
            <th>数量</th>
            <th>贡献者</th>
            <th>地址</th>
            <th>联系方式</th>
            <th>详情</th>
            <th>是否需要</th>
        </tr>
            <%while (rss.next()){%>
        <tr>
            <td><%=rss.getString("name")%></td>
            <td><%=rss.getInt("num")%></td>
            <td><%=rss.getString("attri")%></td>
            <td><%=rss.getString("address")%></td>
            <td><%=rss.getString("cont")%></td>
            <td><%=rss.getString("detail")%></td>
            <td>
                <form action="delete" method="get">
                    <input type="hidden" name="id" value=<%=rss.getInt("id")%>>
                    <input type="hidden" name="num" value=<%=rss.getInt("num")%>>
                    <input type="hidden" name="tb_list" value=<%=hm.get("option")%>>
                    <input type="hidden" name="key" value<%=hm.get("key")%>>
                    <input type="number" name="need" value=0>
                    <input type="submit" value="我要了">
                </form>
            </td>
        </tr>
        <%}%>
    </table>
    <%
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    %>
    <input type="button" onclick="window.location.href='user2.jsp';" value="返回">
</body>
</html>
