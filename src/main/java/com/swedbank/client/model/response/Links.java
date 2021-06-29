package com.swedbank.client.model.response;

public class Links {

    private Location next;

    public Links() {
    }

    public Location getNext() {
        return next;
    }

    public void setNext(Location next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Links{" +
                "next=" + next +
                '}';
    }
}
