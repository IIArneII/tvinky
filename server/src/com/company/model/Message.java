package com.company.model;

import java.io.Serializable;

public class Message implements Serializable, Cloneable{
    /*
     * Типы сообщений:
     * 1. disconnection - клиент серверу или клиент серверу, отключение клиента от сервера
     * 2. character - клиент серверу, пересылает персонажа
     * 3. characters - сервер клиенту, пересылает список персонажей
     * 4. map - сервер клиенту, пересылает карту
     * 5. game - сервер клиенту, пересылает всю игру
     * 6. addCharacter - сервер клиенту, клиент добавляет нового персонажа
     * 7. addCharacters - сервер клиенту, клиент добавляет новых персонажей
     * 8. delCharacter - сервер клиенту, клиент удаляет персонажа
     * */

    private String type;
    private String comment;
    private Object object;

    public Message(){
        this.type = "";
        this.comment = "";
        this.object = new Object();
    }

    public Message(String type){
        this.type = type;
        this.comment = "";
        this.object = new Object();
    }

    public Message(String type, Object object){
        this.type = type;
        this.object = object;
    }

    public Message(String type, String comment, Object object){
        this.type = type;
        this.comment = comment;
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Message copy(){
        Message temp = new Message(type, comment, object);
        return temp;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
