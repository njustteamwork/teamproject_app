package com.example.myapplication.paillier;
import java.math.*;

public class PaillierPublicKey {
    private BigInteger n;
    private BigInteger nSquare;
    private BigInteger g;
    private int bitLength;
    public PaillierPublicKey(BigInteger n, BigInteger g, int bitLength){
        this.n = n;
        nSquare = n.multiply(n);
        this.g = g;
        this.bitLength = bitLength;
    }

    public BigInteger getN() {return n;}
    public BigInteger getNSquare(){return nSquare;}
    public BigInteger getG(){return g;}
    public int getBitLength(){return bitLength;}

}
