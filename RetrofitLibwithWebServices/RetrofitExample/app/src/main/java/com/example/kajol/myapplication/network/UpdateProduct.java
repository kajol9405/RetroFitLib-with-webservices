package com.example.kajol.myapplication.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajol on 3/25/2017.
 */
public class UpdateProduct
{

    /**
     * error : false
     * message : Updated data successfully
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
