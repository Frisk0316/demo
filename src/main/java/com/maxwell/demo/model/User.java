package com.maxwell.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    
    private Integer userId;

    private String email;

    // Response Body 不能回傳 password, 因此要使用 JsonIgnore 忽略之
    @JsonIgnore
    private String password;

    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
