/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.caf.crypto;

import co.id.caf.FileText.ReadFileText;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author DJ
 */
public class TripleDES {
    private String KEY1;
    private String KEY2;
    private String KEY3;
    
    public TripleDES(){
        ReadFileText ListKunci=new ReadFileText("C:\\tmp\\KEY.txt");
        ArrayList hasil = ListKunci.Read();
        KEY1 = hasil.get(0).toString().trim();
        KEY2 = hasil.get(1).toString().trim();
        KEY3 = hasil.get(2).toString().trim();
    }
    
    public String encrypt3DES(String message) throws Exception {
        final byte[] plainTextBytes = message.getBytes("utf-8");
        byte[] codedtext1 = this.encrypt(plainTextBytes,KEY1);
        byte[] codedtext2 = this.encrypt(codedtext1,KEY2);
        byte[] codedtext3 = this.encrypt(codedtext2,KEY3);
        
        String str = Hex.encodeHexString(codedtext3);
        return str;
    }
    
    public String decrypt3DES(String message) throws Exception {
//        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] plainTextBytes = DatatypeConverter.parseHexBinary(message);

        byte[] ciphertext1 = this.decrypt(plainTextBytes,KEY3);
        byte[] ciphertext2 = this.decrypt(ciphertext1,KEY2);
        byte[] ciphertext3 = this.decrypt(ciphertext2,KEY1);
        return new String(ciphertext3, "UTF-8");
    }
    
    public byte[] encrypt(byte[] message, String KEY) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(KEY
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

//        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(message);
        return cipherText;
    }
    
    public byte[] decrypt(byte[] message,String KEY) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(KEY
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        final byte[] plainText = decipher.doFinal(message);

        return plainText;//new String(plainText, "UTF-8");
    }
}