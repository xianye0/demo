//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.demo.plugins.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Locale;

@Slf4j
public class RSAUtils {
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
    private static final String CHARSET_UTF8 = "UTF-8";

    private RSAUtils() {
    }

    public static String decryptString(KeyPair keyPair, String encryptText) {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        } else {
            log.debug("encrypted password:{} will be decrypted", encryptText);

            try {
                byte[] e = parseHexStr2Byte(encryptText);
                byte[] data = decrypt(keyPair.getPrivate(), e);
                String decryptStr = URLEncoder.encode(StringUtils.reverse(new String(data)), CHARSET_UTF8);
                return URLDecoder.decode(decryptStr, CHARSET_UTF8);
            } catch (DataLengthException var6) {
                log.error("DataLengthException,the length String is {}", encryptText, var6);
            } catch (UnsupportedEncodingException var7) {
                log.error("decryptString encryptText {}", encryptText, var7);
            }

            return null;
        }
    }

    public static String decryptString(PrivateKey privateKey, String encryptText) {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        } else {
            log.debug("encrypted password:{} will be decrypted", encryptText);

            try {
                byte[] e = parseHexStr2Byte(encryptText);
                byte[] data = decrypt(privateKey, e);
                String decryptStr = URLEncoder.encode(StringUtils.reverse(new String(data)), CHARSET_UTF8);
                return URLDecoder.decode(decryptStr, CHARSET_UTF8);
            } catch (DataLengthException var6) {
                log.error("DataLengthException,the length String is {}", encryptText, var6);
            } catch (UnsupportedEncodingException var7) {
                log.error("decryptString encryptText {}", encryptText, var7);
            }

            return "";
        }
    }

    public static String encryptString(PublicKey publicKey, String data) {
        byte[] enResult = encrypt(publicKey, data.getBytes());
        return bytesToHexString(enResult);
    }

    private static byte[] encrypt(PublicKey publicKey, byte[] data) {
        try {
            Cipher e = Cipher.getInstance("RSA", DEFAULT_PROVIDER);
            e.init(1, publicKey);
            int blockSize = e.getBlockSize();
            int outputSize = e.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];

            for (int i = 0; data.length - i * blockSize > 0; ++i) {
                if (data.length - i * blockSize > blockSize) {
                    e.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    e.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
            }

            return raw;
        } catch (NoSuchAlgorithmException var10) {
            log.error("NoSuchAlgorithmException error:", var10);
        } catch (NoSuchPaddingException var11) {
            log.error("NoSuchPaddingException error:", var11);
        } catch (IllegalBlockSizeException var12) {
            log.error("IllegalBlockSizeException error:", var12);
        } catch (BadPaddingException var13) {
            log.error("BadPaddingException error:", var13);
        } catch (InvalidKeyException var14) {
            log.error("InvalidKeyException error:", var14);
        } catch (ShortBufferException var15) {
            log.error("ShortBufferException error:", var15);
        }

        return new byte[1];
    }

    public static KeyPair getKeyPair() {
        try {
            KeyPairGenerator e = KeyPairGenerator.getInstance("RSA", DEFAULT_PROVIDER);
            e.initialize(1024, new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes()));
            return e.generateKeyPair();
        } catch (NoSuchAlgorithmException var2) {
            log.error("init RSA key failed", var2);
            return null;
        }
    }

    /**
     * 生成私钥
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey generateRSAPrivateKey(BigInteger modulus, BigInteger privateExponent)
            throws InvalidKeySpecException {
        KeyFactory keyFac;
        try {
            keyFac = KeyFactory.getInstance("RSA", new BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            log.error("", ex);
            throw new InvalidKeySpecException(ex.getMessage());
        }
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            log.error("", ex);
            throw new InvalidKeySpecException(ex.getMessage());
        }
    }

    private static byte[] decrypt(PrivateKey privateKey, byte[] data) {
        try {
            Cipher exception = Cipher.getInstance("RSA", DEFAULT_PROVIDER);
            exception.init(2, privateKey);
            int blockSize = exception.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(256);

            for (int j = 0; data.length - j * blockSize > 0; ++j) {
                if (data.length - j * blockSize > blockSize) {
                    bout.write(exception.doFinal(data, j * blockSize, blockSize));
                } else {
                    bout.write(exception.doFinal(data, j * blockSize, data.length - j * blockSize));
                }
            }

            return bout.toByteArray();
        } catch (NoSuchAlgorithmException var7) {
            log.error("NoSuchAlgorithmException error:", var7);
        } catch (NoSuchPaddingException var8) {
            log.error("NoSuchPaddingException error:", var8);
        } catch (IllegalBlockSizeException var9) {
            log.error("IllegalBlockSizeException error:", var9);
        } catch (BadPaddingException var10) {
            log.error("BadPaddingException error:", var10);
        } catch (InvalidKeyException var11) {
            log.error("InvalidKeyException error:", var11);
        } catch (IOException var12) {
            log.error("IOException error:", var12);
        }

        return new byte[0];
    }

    private static String bytesToHexString(byte[] buf) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (buf != null && buf.length > 0) {
            byte[] arr = buf;
            int length = buf.length;

            for (int i$ = 0; i$ < length; ++i$) {
                byte b = arr[i$];
                int v = b & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private static byte[] parseHexStr2Byte(String hexString) {
        if (hexString != null && !"".equals(hexString)) {
            String uppCaseString = hexString.toUpperCase(Locale.getDefault());
            int length = uppCaseString.length() / 2;
            char[] hexChars = uppCaseString.toCharArray();
            byte[] result = new byte[length];

            for (int i = 0; i < length; ++i) {
                int pos = i * 2;
                result[i] = (byte) (charToByte(hexChars[pos]) << 4 | (charToByte(hexChars[pos + 1]) & 0xff));
            }

            return result;
        } else {
            return new byte[0];
        }
    }

    public static String decrypt(KeyPair keyPair, String password) {
        return RSAUtils.decryptString(keyPair, password);
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
