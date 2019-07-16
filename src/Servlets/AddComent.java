package Servlets;

import com.Db.DBManager;
import com.Db.Tweet_answers;
import com.Db.Tweets;
import com.Db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddComent")
public class AddComent extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        Long id = Long.parseLong(ids);
        String text =request.getParameter("comment");
        String data = dbManager.getDate();
        Tweets tweets=dbManager.getTweetsByID(id);
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");

//ok till to here
        Tweet_answers tweet_answers = new Tweet_answers(null,tweets,text,data,user);
        dbManager.addTweetComent(tweet_answers);
        response.sendRedirect("/");
    }
}
