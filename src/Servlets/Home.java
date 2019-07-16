package Servlets;

import com.Db.DBManager;
import com.Db.Followers;
import com.Db.Tweet_answers;
import com.Db.Tweets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@javax.servlet.annotation.WebServlet("/Home")
public class Home extends javax.servlet.http.HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<Followers> arr = dbManager.getAllFollow();
        request.setAttribute("followers",arr);
        Set<Tweets> arr2 = dbManager.getAllTweets();
        System.out.println(arr2.size()+"  Home size tweets");
        request.setAttribute("tweets",arr2);
        Set<Tweet_answers> arr3 = dbManager.getAllComent();
        request.setAttribute("comment",arr3);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

}
