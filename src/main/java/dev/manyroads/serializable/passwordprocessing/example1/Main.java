package dev.manyroads.serializable.passwordprocessing.example1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class UserProfile implements Serializable {
    //private static final long serialVersionUID = 26292552485L;

    private String login;
    private String email;
    private transient String password;

    public UserProfile(String login, String email, String password) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        String encryptedPassword = encryptPassword(this.password);
        oos.writeObject(encryptedPassword);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.password = decryptPassword((String) ois.readObject());
    }

    String encryptPassword(String password) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char a = password.charAt(i);
            sb.append(a += 1);
        }
        return sb.toString();
    }

    String decryptPassword(String password) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char a = password.charAt(i);
            sb.append(a -= 1);
        }
        return sb.toString();
    }
    // implement readObject and writeObject properly

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
