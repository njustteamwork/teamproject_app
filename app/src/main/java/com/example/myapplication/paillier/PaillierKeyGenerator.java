package com.example.myapplication.paillier;
import java.math.*;
import java.util.*;

public class PaillierKeyGenerator {
    //p,q是两个随机的质数， lambda = lcm(p-1, q-1);
    private BigInteger p, q, lambda;

    // n = p*q
    public BigInteger n;

    // nsquare就是n的平方
    public BigInteger nsquare;
    /**
     * 随机选取一个整数 g,g属于小于n的平方中的整数集,且 g 满足:g的lambda次方对n的平方求模后减一后再除与n，
     * 最后再将其与n求最大公约数，且最大公约数等于一。
     * a random integer in Z*_{n^2} where gcd (L(g^lambda mod nsquare), n) = 1.
     */
    private BigInteger g;
    //bitLength 模量
    private int bitLength;

    /**
     * 产生公钥【N,g】       私钥lamada
     * @param bitLengthVal
     *            number of bits of modulus.
     * @param certainty
     *            certainty - 调用方允许的不确定性的度量。
     *            新的 BigInteger 表示素数的概率超出 (1 - 1/2certainty)。
     *            此构造方法的执行时间与此参数的值是成比例的。
     */
    public PaillierKeyGenerator(int bitLengthVal, int certainty) {  //bitLengthVal = 512 certainty = 64
        while(true){
            bitLength = bitLengthVal;
            //构造两个随机生成的正 大质数，长度可能为bitLength/2，它可能是一个具有指定 bitLength 的素数
            p = new BigInteger(bitLength / 2, certainty, new Random());
            q = new BigInteger(bitLength / 2, certainty, new Random());

            //n = p*q;
            n = p.multiply(q);
            //nsquare = n*n;
            nsquare = n.multiply(n);
            //随机生成一个0~100的整数g
            g = new BigInteger( String.valueOf( (int) (  Math.random()*100 ) ));

            //lamada=lcm(p-1,q-1),即lamada是p-1,q-1的最小公倍数
            //lamada=((p-1)*(q-1)) / gcd(p-1,q-1);
            lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))  //(p-1)*(q-1)
                    .divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
            //检验g是否符合公式的要求， gcd (L(g^lambda mod nsquare), n) = 1.
            if (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
                System.out.println("g is not good. Choose g again.");
            }
            else break;
        }
    }

    public BigInteger getLambda(){return lambda;}
    public BigInteger getN(){return n;}
    public BigInteger getG(){return g;}
    public int getBitLength(){return bitLength;}
    public PaillierPublicKey getPaillierPublicKey(){return new PaillierPublicKey(n, g, bitLength);}
    public PaillierPrivateKey getPaillierPrivateKey(){return new PaillierPrivateKey(n ,g, lambda);}

}
