package com.multi.gradle.module.core.utils;

import com.multi.gradle.module.core.security.PasswordDriveBytes;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

@Service
public class EncryptionService {
    private static final Logger LOG = LoggerFactory.getLogger(EncryptionService.class);

    private final static String ENCRYPT_PASSWORD = "dptmdpaxkdnsskdn!%#0";
    private static final byte[] SALT = { 0x49, 0x76, 0x61, 0x6e, 0x20, 0x4d, 0x65, 0x64, 0x76, 0x65, 0x64, 0x65, 0x76 };

    public String encrypt(String clearText){
        String encryptedString = "";
        byte[] clearBytes, password;

        try{
           clearBytes = clearText.getBytes("UTF-16LE");
           password = ENCRYPT_PASSWORD.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException(e);
        }
        PKCS5S1ParametersGenerator generator = new PasswordDriveBytes(
                new SHA1Digest());

        generator.init(password, SALT, 100);
        // 왜 자꾸 이게 나오는지 확인
        //byte[] pdb = ((KeyParameter) ((PasswordDriveBytes) generator).generateDriveParameters(512)).getKey();
        byte[] pdb = ((KeyParameter) generator.generateDerivedParameters(512)).getKey();


        byte[] Key = getKeyBytes(pdb, 32);
        byte[] IV = getIvBytes(pdb, 16);

        byte[] encryptedData = encrypt(clearBytes, Key, IV);

        encryptedString = Base64.encodeBase64String(encryptedData);

        return encryptedString;
    }

    public static byte[] encrypt(byte[] clearData, byte[] Key, byte[] IV) {

        byte[] encrypted = null;

        try {

            SecretKeySpec secret = new SecretKeySpec(Key, "AES");

            Cipher cipher = Cipher.getInstance("Rijndael/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(IV));

            encrypted = cipher.doFinal(clearData);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return encrypted;
    }

    public String decrypt(String cipherText) {
        String decryptedString = "";

        byte[] cipherBytes = Base64.decodeBase64(cipherText), password = null;

        try {
            password = ENCRYPT_PASSWORD.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        PKCS5S1ParametersGenerator generator = new PasswordDriveBytes(
                new SHA1Digest());
        generator.init(password, SALT, 100);
        byte[] pdb = ((KeyParameter) generator
                .generateDerivedParameters(512)).getKey();

        byte[] Key = getKeyBytes(pdb, 32);
        byte[] IV = getIvBytes(pdb, 16);

        byte[] decryptedBytes = decrypt(cipherBytes, Key, IV);

        try {
            decryptedString = new String(decryptedBytes, "UTF-16LE");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return decryptedString;

    }
        public static byte[] decrypt(byte[] cipherBytes, byte[] Key, byte[] IV) {

        byte[] decrypted = null;

        try {

            SecretKeySpec secret = new SecretKeySpec(Key, "AES");

            Cipher cipher = Cipher.getInstance("Rijndael/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(IV));

            decrypted = cipher.doFinal(cipherBytes);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return decrypted;
    }

    private static byte[] getKeyBytes(byte[] pdb, int cb) {
        byte[] keyByte = new byte[cb];
        System.arraycopy(pdb, 0, keyByte, 0, 32);

        return keyByte;
    }

    private static byte[] getIvBytes(byte[] pdb, int cb) {
        byte[] ivByte = new byte[cb];
        System.arraycopy(pdb, 8, ivByte, 0, 8);
        System.arraycopy(pdb, 40, ivByte, 8, 8);

        return ivByte;
    }
}
