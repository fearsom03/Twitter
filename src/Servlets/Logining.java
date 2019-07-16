package Servlets;

import com.Db.DBManager;
import com.Db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Logining")
public class Logining extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login,password;
        login = request.getParameter("login");
        password=request.getParameter("pass");
        System.out.println(login+"  --  "+password);
        User a = new User(login,password,null);
        User user =dbManager.getUser(a);
        if (user!=null){
            System.out.println("ok");
            HttpSession ses = request.getSession();
            ses.setAttribute("user",user);
            request.setAttribute("user",user);
            response.sendRedirect("/");
        }else if (user==null){
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
        }
    }
}
