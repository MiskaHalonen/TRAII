package Viikko2;

import fi.uef.cs.tra.TreeNode;

import java.util.*;




public class TRAII_24_X2_miskah implements TRAII_24_X2 {
    //                  OWN user-id here

    /**
     * SELF-EVALUTION HERE
     * muita prosesseja pyörii tietokoneella niin paljon koko ajan
     * että esim linked list testi lähti menemään lineaarisesti vasta isommilla
     * lista kooilla ohjelma kuitenkin toimii ja jos jaksaisi sulkea kaikki prosessit
     * lukuun ottamatta ide:ä niin silloin saisi varmaankin lähes läydelliset tulokset
     *
     */

    /**
     * Measures the speed (time) of the contains() -operation of given
     * Collection. Result is a typical time of the contains() operation in nanoseconds
     * in a case that the element is not found from the collection.
     * Does not change the collection (does not add or remove elements).
     *
     * @param C collection under test
     * @return typical time of a contains() operaation in nanoseconds
     */
    @Override
    public long containsTime(Collection<Double> C) {
        // testi luvuksi luku jota tuskin löytyy
        double testiluku = Double.MAX_VALUE;

        // paljon ajoja = tarkempi tulos
        int testiAjojenMaara = 10000;
        long ajoAika = 0;

        for(int i = 0; i<testiAjojenMaara; i++){
            long alku = System.nanoTime();
            C.contains(testiluku);
            long loppu = System.nanoTime();

            ajoAika += loppu - alku;

        }

        return ajoAika / testiAjojenMaara;
    }

}