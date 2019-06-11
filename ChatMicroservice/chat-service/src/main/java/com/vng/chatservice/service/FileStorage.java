package com.vng.chatservice.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorage {

    public static boolean saveFile(byte[] data,String fileName){
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream("/home/cpu11290/ChatService/ChatMicroservice/chat-service/src/data/"+fileName);
            outputStream.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
       catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

}
