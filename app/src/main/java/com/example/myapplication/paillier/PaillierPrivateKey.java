package com.example.myapplication.paillier;
import java.math.*;

public class PaillierPrivateKey {
    private BigInteger n;
    private BigInteger nSquare;
    private BigInteger g;
    private BigInteger lambda;

    public PaillierPrivateKey(BigInteger n, BigInteger g, BigInteger lambda){
        this.n = n;
        nSquare = n.multiply(n);
        this.g = g;
        this.lambda = lambda;
    }

    public BigInteger getN(){return n;}
    public BigInteger getnSquare(){return nSquare;}
    public BigInteger getG(){return g;}
    public BigInteger getLambda(){return lambda;}
}
