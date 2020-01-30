package com.example.myapplication.paillier;

import java.math.BigInteger;

public class PaillierTest {
    public static void main(String args[]){
        PaillierKeyGenerator keyGenerator = new PaillierKeyGenerator(512,64);
        PaillierPublicKey publicKey = keyGenerator.getPaillierPublicKey();
        PaillierPrivateKey privateKey = keyGenerator.getPaillierPrivateKey();
        BigInteger m1 = new BigInteger("20");
        BigInteger m2 = new BigInteger("80");
        PaillierEncryptor encryptor = new PaillierEncryptor(publicKey);
        PaillierDecryptor decryptor = new PaillierDecryptor(privateKey);
        BigInteger em1 = encryptor.encryptIt(m1);
        BigInteger em2 = encryptor.encryptIt(m2);
        System.out.println("After encrypted, m1 is "+ em1.toString());
        System.out.println("After encrypted, m2 is "+ em2.toString());
        System.out.println("After decrypted, em1 is " + decryptor.decryptIt(em1).toString());
        System.out.println("After decrypted, em2 is " + decryptor.decryptIt(em2).toString());
        PaillierCalculator calculator = new PaillierCalculator(publicKey);
        BigInteger sumEm1Em2 = calculator.addIt(em1,em2);
        System.out.println("em1 + em2 is "+sumEm1Em2.toString());
        System.out.println("After decrypted, em1 sum em2 is " + decryptor.decryptIt(sumEm1Em2).toString());
        BigInteger multipleEm1M2 = calculator.multiplyIt(em1, m2);
        System.out.println("multipleEm1M2 is " + multipleEm1M2);
        System.out.println("After decrypted, multipleEm1M2 is " + decryptor.decryptIt(multipleEm1M2));
        System.out.println(publicKey.getJsonStringPublicKey());
    }
}
