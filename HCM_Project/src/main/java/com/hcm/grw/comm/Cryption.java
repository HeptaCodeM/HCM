package com.hcm.grw.comm;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
* 암호화 클래스
* @author : SDJ
* @since : 2024.01.13
* @version : 1.0
*/
public class Cryption {

	//SHA256 암호화
	/**
	 * @author DJ
	 * @version 1.0.0
	 * @since 2024.01.13
	 * @param encTxt : 비밀번호 일반 Text(String)
	 * @return String  암호화된 텍스트(char 64자)
	 * @throws Exception
	 * @description SHA256 암호화(단방향)
	 */
	public static String sha256Encrypt(String encTxt) throws Exception {
		if(encTxt == "" || encTxt == null) {
			return "";
		}

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(encTxt.getBytes());

		return bytesToHex(md.digest());
	}

	private static String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

	
	
	/* AES 암/복호화 시작 */
	//알고리즘
	private final static String algorithms = "AES/CBC/PKCS5Padding";
	// AES/CBC/PKCS5Padding -> AES, CBC operation mode, PKCS5 padding scheme 으로 초기화된 Cipher 객체
	//키
	private final static String AESKey = "c28500bb18fb5b28ebb49910fe2776c8"; //32byte(SHAKE128->Output 128 Bits:GD74)
	// 초기화 벡터
	private final static String AESIv = AESKey.substring(0, 16); //16byte

	// AES256 암호화
	/**
	 * @author DJ
	 * @version 1.0.0
	 * @since 2024.01.13
	 * @param encTxt : 암호화할 일반 텍스트(String)
	 * @return String 암호화된 텍스트
	 * @throws Exception 
	 * @description AES256 암호화(양방향)
	 */
	public static String aes256Encrypt(String encTxt) throws Exception {
		if(encTxt == "" || encTxt == null) {
			return "";
		}

		// 암호화/복호화 기능이 포함된 객체 생성
		Cipher cipher = Cipher.getInstance(algorithms);

		// 키로 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

		// iv 로 spec 생성
		IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());
		// 매번 다른 IV를 생성하면 같은 평문이라도 다른 암호문을 생성할 수 있다.
		// 또한 IV는 암호를 복호화할 사람에게 미리 제공되어야 하고 키와 달리 공개되어도 상관없다

		// 암호화 적용
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

		// 암호화 실행
		byte[] encrypted = cipher.doFinal(encTxt.getBytes(StandardCharsets.UTF_8)); // ID 암호화(인코딩 설정)

		return Base64.getEncoder().encodeToString(encrypted); // 암호화 인코딩 후 저장
	}

   
	// AES256 복호화
	/**
	 * @author DJ
	 * @version 1.0.0
	 * @since 2024.01.13
	 * @param decTxt : 암호화된 텍스트(String)
	 * @return String 복호화된 텍스트
	 * @throws Exception
	 * @description AES256 복호화(양방향)
	 */
	public static String aes256Decrypt(String decTxt) throws Exception {
	    if (decTxt == null || decTxt.isEmpty()) {
	        return "";
	    }
	    
		// 암호화/복호화 기능이 포함된 객체 생성
		Cipher cipher = Cipher.getInstance(algorithms);

		// 키로 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

		// iv 로 spec 생성
		IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());
		// 매번 다른 IV를 생성하면 같은 평문이라도 다른 암호문을 생성할 수 있다.
		// 또한 IV는 암호를 복호화할 사람에게 미리 제공되어야 하고 키와 달리 공개되어도 상관없다

		// 암호화 적용
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

		//암호 해석
		byte[] decodedBytes = Base64.getDecoder().decode(decTxt);
		byte[] decrypted = cipher.doFinal(decodedBytes);

		return new String(decrypted, StandardCharsets.UTF_8);
		
	}
	/* AES 암/복호화 종료 */
}
