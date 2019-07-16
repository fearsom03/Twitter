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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/AddTweet")
public class AddTwit extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String textTw = request.getParameter("tweetText");
        User user = (User) ses.getAttribute("user");
        String date1=dbManager.getDate();
        dbManager.addTweet(user,textTw,date1);
        response.sendRedirect("/");
    }
}
