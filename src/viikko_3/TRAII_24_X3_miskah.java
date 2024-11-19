package viikko_3;

import java.util.*;



public class TRAII_24_X3_miskah implements TRAII_24_X3 {
    //                  OWN user-id here

    /**
     * SELF-EVALUTION HERE:
     *
     *    Itse prosessit olivat semi yksinkertaisia mutta lämmitelyyn meni aikaa
     *    keksiä mutta kun lämmittelyä ajoi useamman kerran alkoivat tulokset menemään
     *    paremmin. päätin myös joka alkio määrän kohdalla ajaa operaatiot kymmeniä
     *    kertoja ja ottaa niistä keskiarvo jotta mitta virheet vähenisivät
     *    näillä ajomäärillä eniten aikaa vei vektori ja viimeisimmällä ajolla
     *    aikaa meni keskimäärin ~1.9 sekuntia per elementti määrä
     *
     *
     */


    /**
     * Test the time and remove elements from lists A and B.
     * Measures the time for each input size n= { min, min*2, min*4, min*8, ... <=max}.
     * For each input size n, measure the time to execute the following operation sequence:
     * 1. add n elements to list A
     * 2. iterate list A and add each element to list B
     * 3. remove n times last element of A and last element of B
     * (both lists will be emptied)
     * Last input size to measure is the largest min*2^k which is larger or equal than max.
     * As a reusult, return a map where the key id each input size and as the value
     * the measured time for that input size in nanoseconds.
     * @param A list under test
     * @param B list under test
     * @param min minumum number of elements
     * @param max maximum number of elements
     * @return map containing all the test results
     */
    @Override
    public SortedMap<Integer, Long> listSpeed(List<Integer> A, List<Integer> B, int min, int max) {
        SortedMap<Integer, Long> result = new TreeMap<>();

        int n = min;

        while(n<max){
            //lämmittely metodi ajetaan useasti
            for (int i = 0; i < 15; i++) {
                warmUp(A, B, n);
            }

            // Aloitetaam ajan mittaus
            long aikaAlkaa = System.nanoTime();


            for (int j = 0; j<20; j++) {

                // Lisätää n alkiota listaan A
                for (int i = 0; i < n; i++) {
                    A.add(i);
                }

                // Käydääm läpi lista A ja lisätään sen alkiot listaan B
                for (int element : A) {
                    B.add(element);
                }

                // Poistetaa n kertaa viimeinen alkio molemmista listoista
                for (int i = 0; i < n; i++) {
                    A.remove(A.size() - 1);
                    B.remove(B.size() - 1);
                }
            }
                // Lopetetaa ajan mittaus
                long aikaLoppuu = System.nanoTime();
                long kokonaisAika = aikaLoppuu - aikaAlkaa;


            // Tallennetaan tulokset
            result.put(n, kokonaisAika/20);

            // Lisätään input size seuraavaan kierrokseen exponentiaalisesti
            n *= 2;
            if (n > max && n / 2 < max) {
                n = max;
            }
        }

        return result;
    }
    private static void warmUp(List<Integer> listaA, List<Integer> listaB, int n) {
        for (int i = 0; i < n; i++) {
            listaA.add(i);
        }
        for (int element : listaA) {
            listaB.add(element);
        }
        for (int i = 0; i < n; i++) {
            listaA.remove(listaA.size() - 1);
            listaB.remove(listaB.size() - 1);
        }
    }

}