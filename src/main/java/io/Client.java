package io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        DataInputStream din = new DataInputStream(new BufferedInputStream(new DecryptInputStream(new FileInputStream("/Users/jingxian/IdeaProjects/data_structure/src/main/java/a.txt")) ));
        int a = din.read();
        while (a != -1){
            char ch = (char)a;
            System.out.println(ch);
            a = din.read();
        }
        din.close();
    }
}
