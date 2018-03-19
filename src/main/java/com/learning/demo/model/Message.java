package com.learning.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String fromName;
    private String toName;
    private String body;

    public String getFrom() {
        return fromName;
    }

    public void setFrom(String from) {
        this.fromName = from;
    }

    public String getTo() {
        return toName;
    }

    public void setTo(String to) {
        this.toName = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromName='" + fromName + '\'' +
                ", toName='" + toName + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
