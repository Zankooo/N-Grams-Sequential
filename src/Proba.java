import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Proba {
    // usaj men se zdi da je to ful hitr, 10ms
    public static void main(String[] args) {
        long zacetekT1 = System.currentTimeMillis();
        System.out.println("----------------------------------------------------------------");
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        System.out.println("Max Heap Size: " + (maxHeapSize / (1024 * 1024)) + " MB");
        removeHyphens();
        long zacetekT2 = System.currentTimeMillis();
        System.out.println("Celoten proces je trajal: " + (zacetekT2 - zacetekT1) + " ms");

    }

    //zgleda je problem ker imamo prevec podatkov v memoryju, torej moram pisati podatke
    // v file (da ni v memoryju) in pol brat iz njega in pol tako
    // samo je vprasanje a za vsako operacijo to naredim ali za katero ne
    // gremo probat zdej to naredit za nek majhen tekst..
    // 1. prebrat file
    // 2. odstranit vse znakce
    // 3. zapisat to v file
    // 4. prebrat spet
    // 5. odstranit recimo vse ja-je
    // 6. spet zapisat


    // TOLE DELA,
    // AMPAK JST MOREM PREBRAT, ODSTRANIT MINUSE IN ZAPISAT V NOVI FILE
    // PREBRAT ZGORNJEGA, ODSTRANIT KLICAJE IN ZAPISAT V NOVI FILE
    // PREBRAT ZGORNJEGA, ODSTRANIT VSE "to" BESEDE IN ZAPISAT V NOVEGA
    public static void removeHyphens(){
        String inputFilePath = "resources/maloTeksta.txt";
        String brezMinusov = "brezMinusov.txt";
        String brezKlicajev = "brezKlicajev.txt";
        try {
            // Step 1: Read the content of the original file in shrani v content
            // v content shranimo to kar je file-u maloTeksta.txt -> basically preberemo file
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // odstranimo minuske in shranimo to novo stvar v odstrajneniMinusi
            String odstranjeniMinusi = content.replace("-", "");

            // Zapisemo to novo besedilo brez minuskov v file
            // na levi je ime fajla v katerga hocemo napisat, na desni je pa kaj napisemo v fajl
            Files.write(Paths.get(brezMinusov), odstranjeniMinusi.getBytes());


            // preberemo besedilo iz fajla, torej besedilo ki nima minuskov vec
            String prebranoBrezMinusov = new String(Files.readAllBytes(Paths.get(brezMinusov)));

            //odstranimo klicajcke
            String odstranjeniKlicaji = prebranoBrezMinusov.replace("!", "");

            // zapisemo novo besedilo ki je sedaj tudi brez klicajckov v fajl brezKlicajev
            Files.write(Paths.get(brezKlicajev), odstranjeniKlicaji.getBytes());

            // in izpise nam besedilo brez minusov in klicajev
            System.out.println("----------------------------------------------------------------");
            String result = new String(Files.readAllBytes(Paths.get(brezKlicajev)));
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

    }



}
