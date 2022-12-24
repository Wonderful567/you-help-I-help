package com.test.servlet;

import com.test.db.GoodsDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String tb_list = req.getParameter("tb_list");
        String key = req.getParameter("key");
        HashMap<String,String> hm = new HashMap<>();
        hm.put("option",tb_list);
        hm.put("key",key);
        int num = Integer.parseInt(req.getParameter("num"));
        int need = Integer.parseInt(req.getParameter("need"));
        if(need>=num){
            GoodsDB.drop(tb_list,id);
        }
        else {
            GoodsDB.delete(tb_list,id,num-need);
        }

        req.setAttribute("hm",hm);
        req.getRequestDispatcher("list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
