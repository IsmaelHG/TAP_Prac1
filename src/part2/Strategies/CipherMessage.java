package part2.Strategies;

import part1.MailStore.MailStore;

import javax.crypto.Cipher;
import java.util.Base64;

public class CipherMessage extends Wrapper{
    private Cipher cipher;
    private java.security.Key aesKey;

    public CipherMessage(MailStore mail) {
        super(mail);
        CreateCipher();
    }

    public void CreateCipher() {
        String key = "IWantToPassTAP12";  // 128 bit key
        aesKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
        try {
            this.cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String MessageWrapper(String body) {
        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(body.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String MessageUnWrapper(String body) {
        byte[] encrypted = Base64.getDecoder().decode(body.getBytes());
        String decrypted = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}
