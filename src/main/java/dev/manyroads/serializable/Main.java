package dev.manyroads.serializable;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Main implements Serializable {
    public static void main(String[] args) {
       TestSerial testSerial = new TestSerial();
       testSerial.testName="ffffff";
        testSerial.nonSerializedTestName="Huhh";
        try {
           serialize(testSerial, "testSerial");
       }catch(IOException e){
           System.out.println(e.getMessage());
       }

        try {
            Object obj = deserialize( "testSerial");
            if(obj instanceof TestSerial tS){
                System.out.println(tS.testName);
                System.out.println(tS.nonSerializedTestName);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}



