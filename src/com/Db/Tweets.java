package com.Db;

import java.util.Date;

public class Tweets {
    private Long id ;
    private User user;
    private String tweet;
    private String post_date;

    public Tweets(Long id, User user, String tweet, String post_date) {
        this.id = id;
        this.user = user;
        this.tweet = tweet;
        this.post_date = post_date;
    }

    public Tweets() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    @Override
    public String toString() {
        return "Tweets{" +
                "id=" + id +
                ", user=" + user +
                ", tweet='" + tweet + '\'' +
                ", post_date='" + post_date + '\'' +
                '}';
    }
}
