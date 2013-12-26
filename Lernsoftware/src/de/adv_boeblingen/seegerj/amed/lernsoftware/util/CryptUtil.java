package de.adv_boeblingen.seegerj.amed.lernsoftware.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Configuration;

public class CryptUtil {
	public static String toSHA1(String convertme) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(Configuration.HASH_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = md.digest(convertme.getBytes());
		return byteArrayToHexString(hash);
	}

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (byte element : b) {
			int value = (element & 0xff) + 0x100;
			result += Integer.toString(value, 16).substring(1);
		}
		return result;
	}
}
