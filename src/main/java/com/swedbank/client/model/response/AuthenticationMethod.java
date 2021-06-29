package com.swedbank.client.model.response;

public class AuthenticationMethod {
    private Location location;
    private String code;
    private String message;

    public AuthenticationMethod() {
    }

    public AuthenticationMethod(Location location, String code, String message) {
        this.location = location;
        this.code = code;
        this.message = message;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthenticationMethod{" +
                "location=" + location +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
