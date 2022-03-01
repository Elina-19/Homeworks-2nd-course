package ru.itis.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {
    public static String hash(String str) {
        str = str + str.substring(0, str.length()/2);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            String hashPassword = DatatypeConverter.printHexBinary(digest).toLowerCase();
            return hashPassword;
        }catch (NoSuchAlgorithmException e){
            throw new IllegalArgumentException(e);
        }
    }
}
