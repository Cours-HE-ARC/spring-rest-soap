package ch.hearc.springsoaprest.rest;

public class PostPaysResponse {

    private String message;

    public PostPaysResponse(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
