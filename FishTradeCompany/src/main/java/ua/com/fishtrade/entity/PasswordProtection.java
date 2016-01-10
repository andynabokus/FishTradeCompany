package ua.com.fishtrade.entity;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordProtection {

	public static void main(String[] args) {
		 
        String password = "MyPassword123";
        String password2 = "MyPassword123";
        System.out.println("MD5 in hex: " + md5(password));        
        if(md5(password).equals(md5(password2))) System.out.println("Passwords are matched");

}
 
public static String md5(String input) {
     
    String md5 = null;
     
    if(null == input) return null;
     
    try {
         
    //Create MessageDigest object for MD5
    MessageDigest digest = MessageDigest.getInstance("MD5");
     
    //Update input string in message digest
    digest.update(input.getBytes(), 0, input.length());

    //Converts message digest value in base 16 (hex) 
    md5 = new BigInteger(1, digest.digest()).toString(16);

    } catch (NoSuchAlgorithmException e) {

        e.printStackTrace();
    }
    return md5;
	}

}


//http://howtodoinjava.com/2013/07/22/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/