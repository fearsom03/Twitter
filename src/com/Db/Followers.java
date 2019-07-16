package com.Db;

public class Followers {
    Long id;
    User userMe, UserToFollow;

    public Followers() {
    }

    public Followers(Long id, User userMe, User userToFollow) {
        this.id = id;
        this.userMe = userMe;
        UserToFollow = userToFollow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserMe() {
        return userMe;
    }

    public void setUserMe(User userMe) {
        this.userMe = userMe;
    }

    public User getUserToFollow() {
        return UserToFollow;
    }

    public void setUserToFollow(User userToFollow) {
        UserToFollow = userToFollow;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "id=" + id +
                ", userMe=" + userMe +
                ", UserToFollow=" + UserToFollow +
                '}';
    }
}
