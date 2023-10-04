package com.employee.employee.api.model;
public class UserAge {

    private int start;
    private int end;
    private String label;
    public UserAge() {}
    public  UserAge(int start, int end, String label, int count) {
        this.start = start;
        this.end = end;
        this.label = label;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
