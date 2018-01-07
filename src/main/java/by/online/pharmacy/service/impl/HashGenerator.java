package by.online.pharmacy.service.impl;

import by.online.pharmacy.service.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private final  char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };
    private final String ALGHORITHM = "SHA-1";

    public String generateHashPassword(String pw) throws ServiceException {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance(ALGHORITHM);
            byte[] hashedBytes = sha.digest(pw.getBytes());
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(DIGITS[(b & 0xf0) >> 4]);
                hash.append(DIGITS[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Exception from creating generateHashPassword method",e);
        }
        return hash.toString();
    }

}
