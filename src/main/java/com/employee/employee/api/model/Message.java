package com.employee.employee.api.model;
public class Message {

    private String message;
    private boolean status;
    
    public  Message(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getStatus(){
        return status;
    }
    public void setStatus(boolean status) {
        this.status=status;
    }
}
