package codist.garmin.uploader.util;


import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

import com.google.common.base.Preconditions;

/**
 * Utility class om een List als root object te mappen in Dozer
 * 
 * @author Xander Arling (in532arl)
 * 
 * @param <T>
 *            source
 * @param <E>
 *            destination type
 */
public final class DozerCollectionMapper<T, E> {

    /**
     * 
     */
    private DozerCollectionMapper() {

    }

    /**
     * Maps all objects in the source List to a destination object. The destination objects are added to a new List which
     * is returned.
     * 
     * @param mapper
     *            the dozer mapper which is able to map the source object to the destination
     * @param source
     *            the List with source objects
     * @param destType
     *            the destination object type.
     * @param <T>
     *            the source type
     * @param <U>
     *            the destination type
     * @return a list with destination objects
     */
    public static <T, U> List<U> map(final Mapper mapper, final Iterable<T> source, final Class<U> destType) {
        final List<U> dest = new ArrayList<U>();

        Preconditions.checkArgument(mapper != null, "Mapper mag niet null zijn");

        if (source != null) {
            for (T element : source) {
                dest.add(mapper.map(element, destType));
            }
        }

        return dest;
    }
    
    
}
