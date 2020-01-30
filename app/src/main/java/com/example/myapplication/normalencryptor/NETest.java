package com.example.myapplication.normalencryptor;

import com.example.myapplication.normalencryptor.helper.AESHelper;
import com.example.myapplication.normalencryptor.helper.DESedeHelper;
import com.example.myapplication.normalencryptor.helper.RSAHelper;
import com.example.myapplication.normalencryptor.helper.SignatureHelper;
import com.example.myapplication.normalencryptor.utils.Hex;

/**
 * HEX,AES,DESede,RSA,MD5等总测试
 * 
 * @author steven-pan
 * 
 */
public class NETest {

	/**
	 * @param args
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		SignatureHelper.main(args);
		System.out.println("------------------------------------\n");

		Hex.main(args);
		System.out.println("------------------------------------\n");

		AESHelper.main(args);
		System.out.println("------------------------------------\n");
		
		DESedeHelper.main(args);
		System.out.println("------------------------------------\n");


		RSAHelper.main(args);
		System.out.println("------------------------------------\n");

		RSAHelper.main(args);
		System.out.println("------------------------------------\n");
		RSAHelper rsaHelper = new RSAHelper();
	}

}
