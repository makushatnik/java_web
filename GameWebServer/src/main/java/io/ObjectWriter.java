package io;

import java.io.*;

/**
 * Created by Ageev Evgeny on 24.01.2016.
 */
public class ObjectWriter {
    public static void main(String[] args) {
        write("How I can catch the world in 24d day.", "./object.bin");
        String fromFile = (String)read("./object.bin");
        System.out.println(fromFile);
    }

    public static void write(Object obj, String fileName) {
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            BufferedOutputStream bout = new BufferedOutputStream(out);
            ObjectOutputStream dout = new ObjectOutputStream(bout);
            dout.writeObject(obj);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object read(String fileName) {
        try (FileInputStream in = new FileInputStream(fileName)) {
            BufferedInputStream bin = new BufferedInputStream(in);
            ObjectInputStream din = new ObjectInputStream(bin);
            return din.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}