package one.clownless.blockify.util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Credit to
 * https://www.appsdeveloperblog.com/pkce-code-verifier-and-code-challenge-in-java/
 */

import java.security.SecureRandom;
import java.util.Base64;

public class PKCEUtil
{
    public static String generateCodeVerifier()
    {
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(
                codeVerifier);
    }


    public static String generateCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException
    {
        byte[] bytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();

        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }
}