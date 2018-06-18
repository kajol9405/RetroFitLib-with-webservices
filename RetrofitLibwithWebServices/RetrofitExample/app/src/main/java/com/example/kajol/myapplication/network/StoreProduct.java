package com.example.kajol.myapplication.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajol on 3/23/2017.
 */
public class StoreProduct
{

    /**
     * error : false
     * product : {"UniqueProductId":16,"name":"kpp","email":"kp21@gmail.com","productname":"kpk"}
     */

    @SerializedName("error")
    private boolean error;
    @SerializedName("product")
    private ProductEntity product;
    /**
     * error_msg : Unknown error occurred in registration!
     */

    @SerializedName("error_msg")
    private String errorMsg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class ProductEntity {
        /**
         * UniqueProductId : 16
         * name : kpp
         * email : kp21@gmail.com
         * productname : kpk
         */

        @SerializedName("UniqueProductId")
        private int UniqueProductId;
        @SerializedName("name")
        private String name;
        @SerializedName("email")
        private String email;
        @SerializedName("productname")
        private String productname;

        public int getUniqueProductId() {
            return UniqueProductId;
        }

        public void setUniqueProductId(int UniqueProductId) {
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
