package Servlets;

import com.Db.DBManager;
import com.Db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/changePassword")
public class ChangePass extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        Long id = Long.parseLong(ids);
        User user = dbManager.getUserByID(id);
        String oldPass,newPass;
        oldPass=request.getParameter("OldPass");
        newPass=request.getParameter("NewPass");
        if (oldPass.equals(user.getPassword())){
            dbManager.changePassById(id,newPass);
            response.sendRedirect("/");
        }else {
            System.out.println("WrongPass");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either Old password is wrong.</font>");
        }
        System.out.println(oldPass+" ---  "+newPass);

    }

}
