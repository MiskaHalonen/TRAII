package Viikko2;

import java.util.Collection;

public interface TRAII_24_X2 {

    /**
     * Measures the speed (time) of the contains() -operation of given
     * Collection. Result is a typical time of the contains() operation in nanoseconds
     * in a case that the element is not found from the collection.
     * Does not change the collection (does not add or remove elements).
     *
     * @param C collection under test
     * @return typical time of a contains() operaation in nanoseconds
     */
    public long containsTime(Collection<Double> C);

}