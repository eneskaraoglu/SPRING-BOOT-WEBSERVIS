package tr.com.bilisim.webservis.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.util.Base64;

public class PBEPasswordEncoder implements PasswordEncoder {

    private static final byte[] salt = { 
    		(byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
    		(byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
    	};
    
    private static final int count = 20;

    public String encode(String input) {
        try {
        	char[] rawPassword = {'b','i','l','i','s','i','m'}; 
            // Create PBE parameter set
            PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);
            PBEKeySpec pbeKeySpec = new PBEKeySpec(rawPassword);

            // create secretkey
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

            // create cipher
            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

            // read input
            byte[] inputByte = input.getBytes("ASCII");

            // encrypt
            byte[] output = pbeCipher.doFinal(inputByte);

            // encode to Base64
            return Base64.getEncoder().encodeToString(output);

        } catch (Exception e) {
            throw new RuntimeException("Error during password encryption", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Encoding raw password to compare with encoded password
        String encodedRawPassword = encode(rawPassword);
        return encodedPassword.equals(encodedRawPassword);
    }

	@Override
	public String encode(CharSequence rawPassword) {
		return encode(rawPassword.toString());
	}
}
