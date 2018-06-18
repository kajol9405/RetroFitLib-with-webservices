package com.example.kajol.myapplication.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kajol on 3/25/2017.
 */
public class ProductDisplay
{

    /**
     * error : false
     * result : [{"UniqueProductId":"74","name":"kp94","email":"kp201@gmail.com","productname":"Applewatch"},{"UniqueProductId":"16","name":"kpp","email":"kp21@gmail.com","productname":"Apple"},{"UniqueProductId":"17","name":"kpp","email":"kp21@gmail.com","productname":" kpp"},{"UniqueProductId":"12","name":"kpp","email":"kp21@gmail.com","productname":"kp90"},{"UniqueProductId":"13","name":"kpp","email":"kp21@gmail.com","productname":" k90"},{"UniqueProductId":"14","name":"kpp","email":"kp21@gmail.com","productname":"kp90"},{"UniqueProductId":"15","name":"kpp","email":"kp21@gmail.com","productname":" k90"},{"UniqueProductId":"18","name":"kpp","email":"kp21@gmail.com","productname":"kp90"},{"UniqueProductId":"88","name":"kp","email":"kajol9@gmail.com","productname":"p2"},{"UniqueProductId":"89","name":"kp","email":"kajol9@gmail.com","productname":"p3"},{"UniqueProductId":"160","name":"kp","email":"kajol9@gmail.com","productname":"p1"},{"UniqueProductId":"161","name":"kp","email":"kajol9@gmail.com","productname":"p2"},{"UniqueProductId":"162","name":"kp","email":"kajol9@gmail.com","productname":"p3"}]
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("result")
    private List<ResultEntity> result;
    /**
     * error_msg : Required parameters email or password is missing!
     */

    @SerializedName("error_msg")
    private String errorMsg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class ResultEntity {
        /**
         * UniqueProductId : 74
         * name : kp94
         * email : kp201@gmail.com
         * productname : Applewatch
         */

        @SerializedName("UniqueProductId")
        private String UniqueProductId;
        @SerializedName("name")
        private String name;
        @SerializedName("email")
        private String email;
        @SerializedName("productname")
        private String productname;

        public String getUniqueProductId() {
            return UniqueProductId;
        }

        public void setUniqueProductId(String UniqueProductId) {
            this.UniqueProductId = UniqueProductId;
        }

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

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
    }
}
