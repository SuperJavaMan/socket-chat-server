package com.example.socketchat.model;

public class Message {
    private String username;
    private String body;
    private Type type;

    public enum Type {
        JOIN,
        CHAT,
        LEAVE

        }

    public Message() {
    }

    public Message(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
