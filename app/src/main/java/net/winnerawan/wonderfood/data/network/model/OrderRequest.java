package net.winnerawan.wonderfood.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */
public class OrderRequest {

    private OrderRequest() {

    }

    public static class Init {

        @SerializedName("customer_id")
        @Expose
        private int customer_id;

        public Init(int customer_id) {
            this.customer_id = customer_id;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }
    }

    public static class Place {

        public static class Update {
            @SerializedName("order_id")
            @Expose
            private int order_id;

            public Update(int order_id) {
                this.order_id = order_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }
        }
        @SerializedName("order_id")
        @Expose
        private int order_id;
        @SerializedName("desk_id")
        @Expose
        private int desk_id;
        @SerializedName("menu_id")
        @Expose
        private int menu_id;
        @SerializedName("qty")
        @Expose
        private int qty;
        @SerializedName("price_total")
        @Expose
        private double price_total;

        public Place(int order_id, int desk_id, int menu_id, int qty, double price_total) {
            this.order_id = order_id;
            this.desk_id = desk_id;
            this.menu_id = menu_id;
            this.qty = qty;
            this.price_total = price_total;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getDesk_id() {
            return desk_id;
        }

        public void setDesk_id(int desk_id) {
            this.desk_id = desk_id;
        }

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public double getPrice_total() {
            return price_total;
        }

        public void setPrice_total(int price_total) {
            this.price_total = price_total;
        }
    }

    public static class Delivery {
        @SerializedName("order_id")
        @Expose
        private int order_id;
        @SerializedName("menu_id")
        @Expose
        private int menu_id;
        @SerializedName("qty")
        @Expose
        private int qty;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("notes")
        @Expose
        private String notes;
        @SerializedName("price_total")
        @Expose
        private double price_total;

        public Delivery(int order_id, int menu_id, int qty, String address, String phone, String notes, double price_total) {
            this.order_id = order_id;
            this.menu_id = menu_id;
            this.qty = qty;
            this.address = address;
            this.phone = phone;
            this.notes = notes;
            this.price_total = price_total;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public double getPrice_total() {
            return price_total;
        }

        public void setPrice_total(int price_total) {
            this.price_total = price_total;
        }
    }
}
