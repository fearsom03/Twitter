package Servlets;

import com.Db.DBManager;
import com.Db.User;
import com.Db.UserDATA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeData")
public class ChangeData extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name,surname,gender,count,city,mail,password;
        String ids = request.getParameter("id");
        Long id = Long.parseLong(ids);
        name=request.getParameter("userName");
        surname=request.getParameter("userSurname");
        gender=request.getParameter("userGender"); //1 male  ; 2 female
        count=request.getParameter("userCount");
        city=request.getParameter("userCity");
        mail=request.getParameter("userMail");
        User user = dbManager.getUserByID(id);
        UserDATA userDATA = new UserDATA(user,name,surname,gender,count,city);
        dbManager.changeUserData(userDATA);
        response.sendRedirect("/");

    }
}
