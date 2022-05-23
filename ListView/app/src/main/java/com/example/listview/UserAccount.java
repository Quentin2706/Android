package com.example.listview;

public class UserAccount {

    private String userName;
    private String userType;
    private Boolean active;

    public String getUserName() {
        return this.userName;
    }

    public UserAccount setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserType() {
        return this.userType;
    }

    public UserAccount setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public Boolean getActive() {
        return this.active;
    }

    public UserAccount setActive(Boolean bool) {
        this.active = bool;
        return this;
    }


    public UserAccount(String name, String type)
    {
        this.userName = name;
        this.userType =  type;
        this.active =  false;
    }

    public UserAccount(String name, String type, Boolean active)
    {
        this.userName = name;
        this.userType =  type;
        this.active = active;
    }

    public Boolean isActive()
    {
        return this.active;
    }


    @Override
    public String toString(){
        return this.userName+"("+this.userType+")";
    }


}
