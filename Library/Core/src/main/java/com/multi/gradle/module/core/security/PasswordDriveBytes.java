package com.multi.gradle.module.core.security;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.generators.PKCS5S1ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

public class PasswordDriveBytes extends PKCS5S1ParametersGenerator {
    private final Digest d;
    private byte[] outPut = null;

    public PasswordDriveBytes(Digest d){
        super(d);
        this.d = d;
    }

    public CipherParameters generateDriveParameters(int keySize){
        keySize = keySize / 8;
        byte[] result = new byte[keySize];
        int done = 0;
        int count = 0;
        byte[] b = null;
        while(done < result.length){
            if (b == null){
                b = generateInitialKey();
            }else if(++count < 1000){
                b = generateExtendedKey(count);
            }else {
                throw  new RuntimeException("Excceeded limit");
            }
            int use = Math.min(b.length, result.length - done);
            System.arraycopy(b, 0, result, done, use);
            done += use;
        }
        return new KeyParameter(result);
    }

    private byte[] generateOutput(){
        byte[] digestBytes = new byte[d.getDigestSize()];
        d.update(password, 0, password.length);
        d.update(salt, 0, salt.length);
        d.doFinal(digestBytes, 0);
        for(int i = 1; i < (iterationCount - 1); i++){
            d.update(digestBytes, 0, digestBytes.length);
            d.doFinal(digestBytes, 0);
        }

        return digestBytes;

    }

    private byte[] generateInitialKey(){
        outPut = generateOutput();
        d.update(outPut, 0, outPut.length);
        byte[] digetBytes = new byte[d.getDigestSize()];
        d.doFinal(digetBytes, 0);
        return null;
    }

    private byte[] generateExtendedKey(int count) {
        byte[] prefix = Integer.toString(count).getBytes();
        d.update(prefix, 0, prefix.length);
        d.update(outPut, 0, outPut.length);
        byte[] digestBytes = new byte[d.getDigestSize()];
        d.doFinal(digestBytes, 0);
        return digestBytes;
    }

}
