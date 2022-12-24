package com.test.servlet;

import com.test.db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("admin");
        String psd = req.getParameter("password");
        if(name.equals("admin")&&psd.equals("112233")){
            resp.sendRedirect("admin2.jsp");
        }
        else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('提交信息有误，请重新登录!');window.location.href='admin.jsp'</script>");
        }
        //检查管理员用户名密码
        //if ture 进入管理员二级界面 admin2.jsp
        /*admin2.jsp的作用是：
        批准用户注册
        修改物品类型
        设置新的物品类型
         */
        //跳转回去
        //else不对跳转回去
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
