package io.crowdcode.benchmarking.jdbc;

import io.crowdcode.benchmarking.jdbc.model.Album;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashGenerator {


    public Album generateHashFromAlbum(Album album) {
        if (album == null) {
            return null;
        }

        String albumHash = "";
        try {
            albumHash = getHashString(album);
        } catch (NoSuchAlgorithmException e) {
            //nothing to do
        }

        album.setHashValue(albumHash);

        return album;
    }

    public boolean verifyGeneratedHashFromAlbum(Album album) {
        if (album == null) {
            return false;
        }

        String albumHash = "";
        try {
            albumHash = getHashString(album);
        } catch (NoSuchAlgorithmException e) {
            //nothing to do
        }

        return albumHash.equals(album.getHashValue());
    }

    private String getHashString(Album album) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(album.toString().getBytes());
        final byte[] digest = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
