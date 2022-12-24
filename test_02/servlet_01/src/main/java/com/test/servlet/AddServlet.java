package com.test.servlet;

import com.test.db.GoodsDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        String goods = req.getParameter("goods");
        int num = Integer.parseInt(req.getParameter("num"));
        String detail = req.getParameter("detail");
        String address = req.getParameter("address");
        String con = req.getParameter("con");
        String userID = req.getParameter("userID");

        GoodsDB.addGoods(option,userID,goods,num,detail,address,con);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print("<script>alert('提交成功!');window.location.href='user2.jsp'</script>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
