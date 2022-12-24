package com.test.servlet;

import com.test.db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        用户名<input type="text" name="username"/><br>
    住址<input type="text" name="address"/><br>
    手机<input type="num" name="phone"/><br>
    密码<input type="password" name="password"/><br>
    确认密码<input type="password" name="password2"/><br>
         */
        String name = req.getParameter("username");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String psd = req.getParameter("password");
        String psd2 = req.getParameter("password2");
        if(!psd.equals(psd2)){
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('两次输入密码不一致！');window.location.href='register'</script>");
        }
        else {
            UserDB.addMessage(name,address,phone,psd);
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('提交申请成功，等待管理员通过');window.location.href='index.jsp'</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
