package io.crowdcode.benchmarking.hashgenerator;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
public interface HashAware<T extends HashAware> {

    String getHashValue();

    T setHashValue(String hash);
}
