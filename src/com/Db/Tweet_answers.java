package com.Db;

import java.util.Date;

public class Tweet_answers {
    Long id;
    Tweets tweets;
    String answer;
    String post_date;
    User user;

    public Tweet_answers() {
    }

    public Tweet_answers(Long id, Tweets tweets, String answer, String post_date, User user) {
        this.id = id;
        this.tweets = tweets;
        this.answer = answer;
        this.post_date = post_date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tweets getTweets() {
        return tweets;
    }

    public void setTweets(Tweets tweets) {
        this.tweets = tweets;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tweet_answers{" +
                "id=" + id +
                ", tweets=" + tweets +
                ", answer='" + answer + '\'' +
                ", post_date='" + post_date + '\'' +
                ", user=" + user +
                '}';
    }
}
