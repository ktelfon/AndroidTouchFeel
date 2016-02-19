package com.example.deniss.helloworldapp.models;

/**
 * Created by Deniss on 19-Feb-16.
 */
public class ButtonPair {

    private int id;
    private CustomButton first;
    private CustomButton second;

    public ButtonPair() {
    }

    public ButtonPair(int id, CustomButton first, CustomButton second) {
        this.id = id;
        this.first = first;
        this.second = second;
    }

    public CustomButton getFirst() {
        return first;
    }

    public void setFirst(CustomButton first) {
        this.first = first;
    }

    public CustomButton getSecond() {
        return second;
    }

    public void setSecond(CustomButton second) {
        this.second = second;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
