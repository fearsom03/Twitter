package Servlets;

import com.Db.DBManager;
import com.Db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteAcc")
public class DeleteAcc extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        Long id = Long.parseLong(ids);
        User user = dbManager.getUserByID(id);
        String pass;
        pass=request.getParameter("Password");
        if(user.getPassword().equals(pass)){
            HttpSession ses = request.getSession(false);
            ses.removeAttribute("user");
            dbManager.deleteUser(user);
            response.sendRedirect("/");
        }else {

            System.out.println("WrongPass");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either  password is wrong.</font>");
        }
    }

}
