package io.crowdcode.benchmarking.hashgenerator;

/**
 * HashGenerator to generate some work do to during benchmarking
 *
 * @param <T>
 * @author Ingo Düppe (CROWDCODE)
 */
public interface HashGenerator<T extends HashAware> {

    T generateHashFromAlbum(T entity);

}
