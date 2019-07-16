package com.Db;

import Servlets.Follow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DBManager {
    private Connection connection;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "");

        } catch (Exception e) {
            System.out.println();
            System.out.println("PROBLEM WITH CONNECTION");
            System.out.println();
            e.printStackTrace();
        }
    }

    public void AddUser(UserDATA userDATA) {

        try {
            PreparedStatement pr = connection.prepareStatement("INSERT into user (id,email,password) values (null ,?,?)");
            pr.setString(1, userDATA.getUser().getEmail());
            pr.setString(2, userDATA.getUser().getPassword());
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        User a = getUser(userDATA.getUser());
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT into UserData (id, userID, name, surname, gender, country, city) values (null ,?,?,?,?,?,?)");
            pr.setLong(1, a.getId());
            pr.setString(2, userDATA.getName());
            pr.setString(3, userDATA.getSurname());
            pr.setString(4, userDATA.getGender());
            pr.setString(5, userDATA.getCountry());
            pr.setString(6, userDATA.getCity());
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(User user) {
        User a = null;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT  * from user where email=?");
            pr.setString(1, user.getEmail());
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                a = new User(user.getEmail(), password, id);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {


            if (a.getEmail().equals(user.getEmail()) && a.getPassword().equals(user.getPassword())) {
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDATA getUserData(User user) {
        UserDATA userDATA = new UserDATA();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * from userData where userID=?");
            pr.setLong(1, user.getId());
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                //id`, `userID`, `name`, `surname`, `gender`, `country`, `city`
                String name, surname, gender, count, city;
                Long id = resultSet.getLong("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                gender = resultSet.getString("gender");
                count = resultSet.getString("country");
                city = resultSet.getString("city");
                userDATA = new UserDATA(id, user, name, surname, gender, count, city);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDATA;
    }

    public User getUserByID(Long id) {
        User a = new User();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * from user where id=?");
            pr.setLong(1, id);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                String mail, password;
                mail = resultSet.getString("email");
                password = resultSet.getString("password");
                a = new User(mail, password, id);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public void changeUserData(UserDATA userDATA) {
        try {
            PreparedStatement pr = connection.prepareStatement("update userData set name=?,surname=?,gender=?,country=?,city=? where userID=?");
            pr.setLong(6, userDATA.getUser().getId());
            pr.setString(1, userDATA.getName());
            pr.setString(2, userDATA.getSurname());
            pr.setString(3, userDATA.getGender());
            pr.setString(4, userDATA.getCountry());
            pr.setString(5, userDATA.getCity());
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void changePassById(Long id, String newPass) {
        try {
            PreparedStatement pr = connection.prepareStatement("UPDATE user set password=? where id=?");
            pr.setLong(2, id);
            pr.setString(1, newPass);
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteUser(User user) {
        try {
            PreparedStatement pr = connection.prepareStatement("delete from user where id=?");
            pr.setLong(1, user.getId());
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<UserDATA> searchByName(String name) {
        Set<UserDATA> arr = new HashSet<>();
        try {
            PreparedStatement pr = connection.prepareStatement("select * from userData where name=?");
            pr.setString(1, name);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                String surname, gender, count, city;
                Long id = resultSet.getLong("id");
                Long userID = resultSet.getLong("userID");
                surname = resultSet.getString("surname");
                gender = resultSet.getString("gender");
                count = resultSet.getString("country");
                city = resultSet.getString("city");
                User a = getUserByID(userID);
                UserDATA userDATA = new UserDATA(id, a, name, surname, gender, count, city);
                arr.add(userDATA);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void follow(Long idMe, Long idOther) {
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO Follow(id, idMe,idOther) VALUES (null ,?,?)");
            pr.setLong(1, idMe);
            pr.setLong(2, idOther);
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Followers> getAllFollow() {
        Set<Followers> arr = new HashSet<>();
        try {
            PreparedStatement pr = connection.prepareStatement("select * from Follow");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Long id, idMe, idOther;
                id = resultSet.getLong("id");
                idMe = resultSet.getLong("idMe");
                idOther = resultSet.getLong("idOther");
                User a1 = getUserByID(idMe);
                User a2 = getUserByID(idOther);
                Followers followers = new Followers(id, a1, a2);
                arr.add(followers);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return arr;
    }

    public boolean checkFollow(Set<Followers> arr, Long idMe, Long idOther) {
        for (Followers a : arr) {
            if (a.getUserMe().getId().equals(idMe) && a.getUserToFollow().getId().equals(idOther)) {
                return true;
            }
        }
        return false;
    }

    public void addTweet(User user, String textTw, String date1) {
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO Tweet(id, user_id, tweet,date_tw) VALUES (null ,?,?,?)");
            pr.setLong(1, user.getId());
            pr.setString(2, textTw);
            pr.setString(3, date1);
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Tweets> getAllTweets() {
        Set<Tweets> arr = new HashSet<>();
        try {
            PreparedStatement pr = connection.prepareStatement("select * from Tweet");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long userID = resultSet.getLong("user_id");
                User user = getUserByID(userID);
                String text = resultSet.getString("tweet");
                String date = resultSet.getString("date_tw");
                Tweets tweets = new Tweets(id, user, text, date);
                arr.add(tweets);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void deletePost(Long id) {
        try {
            PreparedStatement pr = connection.prepareStatement("delete from Tweet where id=?");
            pr.setLong(1, id);
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeTweet(Long id, String text, String date1) {
        try {
            PreparedStatement pr = connection.prepareStatement("UPDATE Tweet SET tweet=?,date_tw=? WHERE id=?");
            pr.setLong(3, id);
            pr.setString(1, text);
            pr.setString(2, date1);
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    public Tweets getTweetsByID(Long id) {
        Tweets tweets = new Tweets();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * from Tweet where id=?");
            pr.setLong(1, id);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Long userID = resultSet.getLong("user_id");
                User user = getUserByID(userID);
                String text = resultSet.getString("tweet");
                String date = resultSet.getString("date_tw");
                tweets = new Tweets(id, user, text, date);
            }
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweets;
    }

    public void addTweetComent(Tweet_answers tweet_answers) {
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO TweetAnswer(id, tweet_id, user_id, text, date_coment) VALUES (null,?,?,?,?)");
            pr.setLong(1, tweet_answers.getTweets().getId());
            pr.setLong(2, tweet_answers.getUser().getId());
            pr.setString(3, tweet_answers.getAnswer());
            pr.setString(4, tweet_answers.getPost_date());
            pr.executeUpdate();
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Tweet_answers> getAllComent() {
        Set<Tweet_answers> arr = new HashSet<>();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM TweetAnswer");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                //id, tweet_id, user_id, text, date_coment
                Long id, tweetid, userid;
                String text, data;
                id=resultSet.getLong("id");
                tweetid=resultSet.getLong("tweet_id");
                userid=resultSet.getLong("user_id");
                text=resultSet.getString("text");
                data=resultSet.getString("date_coment");
                Tweets tweets = getTweetsByID(tweetid);
                User user = getUserByID(userid);
                Tweet_answers a = new Tweet_answers(id,tweets,text,data,user);
                arr.add(a);
            }pr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return arr;
    }

    public void deleteFollow(Long a) {
        try {
            PreparedStatement pr = connection.prepareStatement("DELETE from Follow where id=?");
            pr.setLong(1,a);
            pr.executeUpdate();
            pr.close();
        }catch (Exception e){e.printStackTrace();}
    }
}
