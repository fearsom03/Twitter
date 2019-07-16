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

@WebServlet("/Registration")
public class Registration extends HttpServlet {

    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name,surname,gender,count,city,mail,password;

    name=request.getParameter("userName");
    surname=request.getParameter("userSurname");
    gender=request.getParameter("userGender"); //1 male  ; 2 female
    count=request.getParameter("userCount");
    city=request.getParameter("userCity");
    mail=request.getParameter("userMail");
    password = request.getParameter("userPassword");

        User user = new User(mail,password,1l);
        UserDATA userDATA = new UserDATA(1l,user,name,surname,gender,count,city);
        dbManager.AddUser(userDATA);
        response.sendRedirect("/");

    }
}
