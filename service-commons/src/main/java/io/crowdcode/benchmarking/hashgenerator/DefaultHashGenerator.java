package io.crowdcode.benchmarking.hashgenerator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * HashGenerator
 *
 * @param <T>
 * @author Ingo Düppe (CROWDCODE)
 */
public class DefaultHashGenerator<T extends HashAware> implements HashGenerator<T> {


    public T generateHashFromAlbum(T entity) {

        String albumHash = "";
        try {
            albumHash = getHashString(entity);
        } catch (NoSuchAlgorithmException e) {
            //nothing to do
        }

        entity.setHashValue(albumHash);

        return entity;
    }

    private String getHashString(HashAware entity) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(entity.toString().getBytes());
        final byte[] digest = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
