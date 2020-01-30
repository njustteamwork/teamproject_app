package com.example.myapplication.normalencryptor.helper;

import com.example.myapplication.normalencryptor.utils.Base64;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;


/**
 * RSA非对称加密算法。用法：1公钥加密(C)，私钥解密(S)；2私钥加密(S)，公钥解密(C)。
 */
public class RSAHelper {
	
	/** 指定加密算法为RSA */
	private static String ALGORITHM = "RSA";
	
	/** 指定key的大小 */
	private static int KEYSIZE = 1024;
	
	/** 指定公钥存放文件 */
	private static String PUBLIC_KEY_FILE = "PublicKey";
	
	/** 指定私钥存放文件 */
	private static String PRIVATE_KEY_FILE = "PrivateKey";

	public static void main(String[] args) throws Exception {
		System.out.println("RSA加解密测试：");
		RSAHelper rsaHelper = new RSAHelper();
		rsaHelper.generateKeyPair();
		System.out.println();
		final String source = "73C58BAFE578C59366D8C995CD0B9";// 要加密的字符串
		System.out.println("加密前:" + source);

		String cryptograph = rsaHelper.encrypt(source, rsaHelper.getPublicKey());// 生成的密文
		System.out.println("Base64 format:" + cryptograph);

		String target = rsaHelper.decrypt(cryptograph,rsaHelper.getPrivateKey());// 解密密文
		System.out.println("解密后:" + target);
	}

	/**
	 * 生成密钥对
	 */
	public void generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		/** 清空缓存，关闭文件输出流 */
		oos1.close();
		oos2.close();
	}

	/**
	 * 已有密钥，导入到对象
	 */
	public void setPublicKey(Key publicKey) throws Exception{
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
		oos1.writeObject(publicKey);
		/** 清空缓存，关闭文件输出流 */
		oos1.close();
	}

	public void setPrivateKey(Key privateKey) throws Exception{
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
		oos2.writeObject(privateKey);
		/** 清空缓存，关闭文件输出流 */
		oos2.close();

	}

	/**
	 * 加密方法 source： 源数据
	 */
	public String encrypt(String source, Key publicKey) throws Exception {
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		return Base64.encode(b1);
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public String decrypt(String cryptograph, Key privateKey) throws Exception {
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] b1 = Base64.decode(cryptograph);
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	public Key getPublicKey()throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		return key;
	}

	public Key getPrivateKey() throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		return key;
	}

}