package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller;

public class RequestPage {

    private  int index;
    private  int size;

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void setSize(int size){
        this.size=size;
    }

    public int getSize() {
        return this.size;
    }
}