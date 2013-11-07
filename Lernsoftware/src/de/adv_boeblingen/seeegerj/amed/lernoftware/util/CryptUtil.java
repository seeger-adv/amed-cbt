package de.adv_boeblingen.seeegerj.amed.lernoftware.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtil {
	public static String toSHA1(String convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    byte[] hash = md.digest(convertme.getBytes());
	    return byteArrayToHexString(hash);
	}

	public static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
}
