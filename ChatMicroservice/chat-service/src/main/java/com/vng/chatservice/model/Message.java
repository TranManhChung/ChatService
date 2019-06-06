package com.vng.chatservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.nio.ByteBuffer;
import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "message")
public class Message {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String mess;

    private String email;

    private Blob fileMess;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    public Message(String mess, String email, Room room,Blob file){
        this.mess = mess;
        this.email = email;
        this.room = room;
        this.fileMess = file;
    }

}
