
package com.test.servlet;


import com.test.db.UserDB;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


public class FormService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //得到用户名密码，数据库中检查
         /*if true 跳转到 用户界面user.jsp
        欢迎用户：XXXXX
        增加add.jsp
        删除delete.jsp
        查询 search.jsp
        预览 view.jsp
        完成其中的某个跳转回user.jsp
        */
        //else 登录失败 提示登录失败再跳回登录页面
        Boolean success = false;
        String userID = req.getParameter("username");
        String userPSD = req.getParameter("password");
        HashMap<String,String> map = UserDB.getID_PSD();
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            if(userID.equals(entry.getKey())){
                if(userPSD.equals(entry.getValue())){
                    success = true;
                }
            }
        }
        if(success){
            ServletContext sc = getServletContext();
            sc.setAttribute("userID",userID);
            req.setAttribute("userID",userID);
            req.getRequestDispatcher("user2.jsp").forward(req,resp);
        }
        else {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('提交信息有误，请重新登录!');window.location.href='index.jsp'</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
