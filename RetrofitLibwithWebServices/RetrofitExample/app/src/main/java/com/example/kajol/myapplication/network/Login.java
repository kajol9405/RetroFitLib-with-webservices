package com.example.kajol.myapplication.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajol on 3/22/2017.
 */
public class Login {


    /**
     * error : false
     * uid : 58d4a5fe445f75.92865015
     * user : {"name":"kajol","email":"kajol9405@yahoo.com","isBuyer":0,"isSeller":1,"created_at":"2017-03-23 23:52:14","updated_at":null}
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("uid")
    private String uid;
    @SerializedName("user")
    private UserEntity user;
    /**
     * error_msg : Login credentials are wrong. Please try again!
     */

    @SerializedName("error_msg")
    private String errorMsg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class UserEntity {
        /**
         * name : kajol
         * email : kajol9405@yahoo.com
         * isBuyer : 0
         * isSeller : 1
         * created_at : 2017-03-23 23:52:14
         * updated_at : null
         */

        @SerializedName("name")
        private String name;
        @SerializedName("email")
        private String email;
        @SerializedName("isBuyer")
        private int isBuyer;
        @SerializedName("isSeller")
        private int isSeller;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private Object updatedAt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getIsBuyer() {
            return isBuyer;
        }

        public void setIsBuyer(int isBuyer) {
            this.isBuyer = isBuyer;
        }

        public int getIsSeller() {
            return isSeller;
        }

        public void setIsSeller(int isSeller) {
            this.isSeller = isSeller;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
