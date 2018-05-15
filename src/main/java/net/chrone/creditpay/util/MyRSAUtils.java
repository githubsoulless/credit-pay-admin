package net.chrone.creditpay.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Base64Utils;



/**
 * 
 * @author zj
 * 
 * @date:2016年4月8日上午11:51:36
 */
public final class MyRSAUtils {
	
	private static String RSA = "RSA";
	
	public static final String  MD5_SIGN_ALGORITHM = "MD5withRSA";
	
	public static final String  SHA1_SIGN_ALGORITHM = "SHA1withRSA";
	
	static{
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
            Security.addProvider(new BouncyCastleProvider());
        }
	}

	/**
	 * RSA签名
	 * @param privateKey:私钥
	 * @param plainText:待签名明文串
	 * @param algorithm:签名算法,默认MD5withRSA
	 * @return
	 */
	public static String sign(String  privateKey, String plainText, String algorithm) {
		try {
			byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyf = KeyFactory.getInstance(RSA);
			PrivateKey prikey = keyf.generatePrivate(priPKCS8);
			Signature signet = java.security.Signature.getInstance(algorithm);
			signet.initSign(prikey);
			signet.update(plainText.getBytes());
			return Base64Utils.encodeToString(signet.sign());
		} catch (java.lang.Exception e) {
			System.out.println("签名失败");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * RSA公钥验证
	 * @param publickey:公钥
	 * @param hexSigned：签名信息
	 * @param plainText：待签名明文
	 * @param algorithm:签名算法,默认MD5withRSA
	 * @return
	 */
	public static boolean verifySignature(String publickey, String hexSigned, String plainText,String algorithm) {
		try {
			PublicKey publicKey = loadPublicKey(publickey);
			Signature signetCheck = Signature.getInstance(algorithm);
			signetCheck.initVerify(publicKey);
			signetCheck.update(plainText.getBytes());
			if (signetCheck.verify(Base64Utils.decodeFromString(hexSigned))) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decodeFromString(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	public static PrivateKey loadPrivateKey(String privateKeyStr)
			throws Exception {
		try {
			byte[] buffer = Base64Utils.decodeFromString(privateKeyStr);
			// X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}
	
	private static final int MAX_ENCRYPT_BLOCK = 117;// RSA最大加密明文大小

	private static final int MAX_DECRYPT_BLOCK = 128;// RSA最大解密密文大小
	
	/**
	 * 私钥加密
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm(),BouncyCastleProvider.PROVIDER_NAME);
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}
	public static void main(String[] args) {
		String testPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHNW5pUr5eHguZL6pU+mkrtxsoG5BRuZZxuNJgpbF//CSY/e6Y1ATqd67guzDLUkGHQmij+mJiR5OJqVlz9VhZpdJRsUwBqgAd/TNZhekF5TofpUrGUlUKrLKGszePqHJKdGnYF5pEqxm0TvczCcA4JvHexY+RZOB+teQztKEttVoEcaaasfPNm6JtoRFqMaKwIErejXIxbqPye7zgoohLKAXpkEMJpLMigDRbg+PCenFdZu3imW7tgi7e0WKTO4JAd3bwVGqgS1LmG34yISMmEXBRnAN4IKMWIRoWCjCOpuHmr5/0J+EVtWVuV7WpgygqPKxFxSErQunAvO1dSihFAgMBAAECggEABSiECGQlG8jJYhkS3wpw6LngESTNkuAsXht8DxabRwYfaHrCz+BFlFCvDZCrTch+e+MjVIOqQoM59cmV0GPc+s1JaeYeYrilMiGZT8sg4o8x9xnPmt0M6K7aHJTNboHdG8Jx7y+FRhvsGXgl/INXH90aTDtvYUNscLuDAje+KLHKxMPPJJttQCm/uAKQ6+f+vH8HJferET6weidm3C+K4ZLOLLcwIVphXiJNf2thiPEKHn8vPyeEk+3m4B2wtlxe3ztyTcirY+dUxBipPGEw7fGe9xE/s1QsTBQD4Wal4p6J91k+suW5Qy/MXtZC/wDx+DiSuoXXgDfQKBqem3byYQKBgQDoT0M1kRK9AqKSx2T2IOTzKD5YRzuq5i1QnapYES3F8U1X6sqR8utIZWgUWsRibzJja/l0oUiIWRmM1XXgDSzBxpiLiF9HCf7l2J7TMi94iqMILGiDB+1WKRGX1MQmq9WVCbC0Cyaw03JAKEZKC0leTFhkA3RDKkI1qSpJ7mP7aQKBgQDbhgcVGru/OicW7T6H1I0A6weQYrLsatIJb7A0JYiC0uMDwLdiQtj81soJt+dWbxpWHxc5PeHC6R3WrpvMvIDp3r6OS0ybu0zHDF3X8sFYrD5y5yrwImDqOrQY5VLRbRZNom6ykGOsH7Ubhe9aOtJOcVhyonmYJjdoOYx/NyZ2fQKBgGOGWafgUCPTtiyl6hYcP5oA2rzdP63W0QEhmvIB4hdUaqtItqJSbQRmFXHq9Qmq0+6OOqAB3N41NeIFLWvgmRUnbQESWqdHnAGCjYCzlmzREEmXJOtGASMRkyz2BVb/9Q/Vx/NKd7f3zpG3720XasZQdOncJPoFZ7ovkCN+Uy1JAoGANGJ0WDXr7YMcBt2QWWuARmLPiEX1OnN3MadEXf1C6S0X3TJk0H7xNsyuo8kN3FNg7oI20YMR16g3ZZ5/MUkeXjC4Ok5/tn3ooWszDbAxMU7RDCxb07fwdpSRm4tFyxIEC3yie9v23QEn/6xjZJ26x9tGZvyclZExEZ8CBu2LMSUCgYEAzL5IidSuSxp3M3kM4SOk89uJpVWKPAym0VTQhMETOH/k/3DOG7mHpTXKK6oX/52IJ2cNmt9Sc6sqHnnyDKyRp7dCQVBdL0M+YEnrnOXnhVsu55cTeoczSaPFD6t+BRE1Rs7Du3PwgS6Pmz4A2kUd6SkBelNCJAxzh8oIXQgMD/g=";
		String testPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxzVuaVK+Xh4LmS+qVPppK7cbKBuQUbmWcbjSYKWxf/wkmP3umNQE6neu4Lswy1JBh0Joo/piYkeTialZc/VYWaXSUbFMAaoAHf0zWYXpBeU6H6VKxlJVCqyyhrM3j6hySnRp2BeaRKsZtE73MwnAOCbx3sWPkWTgfrXkM7ShLbVaBHGmmrHzzZuibaERajGisCBK3o1yMW6j8nu84KKISygF6ZBDCaSzIoA0W4PjwnpxXWbt4plu7YIu3tFikzuCQHd28FRqoEtS5ht+MiEjJhFwUZwDeCCjFiEaFgowjqbh5q+f9CfhFbVlble1qYMoKjysRcUhK0LpwLztXUooRQIDAQAB";
		String txt= "account=15000000000&amount=50000&fee=500&orderDt=20170706&orderNo=14993060670050259705&orgId=001031&orgOrderNo=ff8080815ccf1784015d159af286020b&paySt=2&respCode=200&subject=iPhone 7 plus";
		String sign = sign(testPrivateKey,txt , "MD5withRSA");
		System.out.println(sign);
		System.out.println(verifySignature(testPublicKey, sign, txt,MyRSAUtils.MD5_SIGN_ALGORITHM));
	}
}
