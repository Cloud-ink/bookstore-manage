package com.example.demo.pojo.utils;

import java.util.List;

public class VoPage {
    private List items;
    private int total;

    @Override
    public String toString() {
        return "VoPage{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public VoPage(List items, int total) {
        this.items = items;
        this.total = total;
    }

    public VoPage() {
    }
}
