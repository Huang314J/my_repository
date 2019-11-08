package com.shop.domain;

import java.util.Arrays;
import java.util.List;

public class Customer {
    private User user;
    private Product product;
    private int[] ids;
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "user=" + user +
                ", product=" + product +
                ", ids=" + Arrays.toString(ids) +
                ", users=" + users +
                '}';
    }
}
