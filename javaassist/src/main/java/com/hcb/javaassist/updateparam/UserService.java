package com.hcb.javaassist.updateparam;

/**
 * @author ChengBing Han
 * @date 11:43  2018/6/20
 * @description
 */
public class UserService {

    User user ;

    public UserService(User user) {
        this.user = user;
    }

    void f(User user){

        System.out.println("line17");
        System.out.println("line18");

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
