package com.example.myapplication.paillier;

import java.math.BigInteger;
import java.util.Random;

public class PaillierEncryptor {
    private BigInteger r;
    private PaillierPublicKey publicKey;

    public PaillierEncryptor(PaillierPublicKey publicKey){
        this.publicKey = publicKey;
        this.r = new BigInteger(publicKey.getBitLength(), new Random());
    }
    public BigInteger encryptIt(BigInteger m){
        return publicKey.getG().modPow(m, publicKey.getNSquare()).multiply(r.modPow(publicKey.getN(), publicKey.getNSquare())).mod(publicKey.getNSquare());
    }
}
