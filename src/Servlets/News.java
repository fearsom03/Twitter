package Servlets;

import com.Db.*;
import org.w3c.dom.UserDataHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/News")
public class News extends HttpServlet {
    private DBManager dbManager;

    public void init(){
        dbManager= new DBManager();
        dbManager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            Set<Followers> arr = dbManager.getAllFollow();
            Set<Tweets> arr2 = dbManager.getAllTweets();
            Set<Tweet_answers> arr3 = dbManager.getAllComent();
            ArrayList<User> arr1 = new ArrayList<>();
            for (Followers a:arr) {
                if (user.getId().equals(a.getUserMe().getId())){
                    arr1.add(a.getUserToFollow());
                }}


            PrintWriter out= response.getWriter();
                for (Tweets tw :arr2){
                    for (User us:arr1) {
                        if (us.getId().equals(tw.getUser().getId())){
                            UserDATA a =dbManager.getUserData(tw.getUser());
                            out.println("Name : "+a.getName());
                            out.println("Post : "+ tw.getTweet());
                            out.println("Date "+tw.getPost_date() +"\n");
                            for (Tweet_answers tweetAns : arr3){
                                if (tw.getId().equals(tweetAns.getTweets().getId())){
                                    UserDATA a2=dbManager.getUserData(tweetAns.getUser());
                                    out.println("Name : "+ a2.getName());
                                    out.println(tweetAns.getAnswer());
                                }
                            }
                        }
                    }
                }
        }catch (Exception e){e.printStackTrace();}
    }
}
