import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MainKomentarji {

    public static String izbiraTeksta() {
        /**
         * funkcija ki ti omogoca da izberes datoteko oz korpus oz tekst
         */
        // SEZNAM DATOTEK
        String[] datoteke = {"123MB.txt", "234MB.txt", "350MB.txt", "490MB.txt", "613MB.txt"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Izberi besedilo:");
        for (int i = 0; i < datoteke.length; i++) {
            // IZPISE VSE
            System.out.println((i+1) + ". " + datoteke[i]);
        }
        // ZANKO PONAVLJAMO DOKLER NE IZBEREMO PRIMERNE DOLŽINE NGRAA (1-5)
        while (true) {
            System.out.print("Vaša izbira (1-" + datoteke.length + "): ");
            if (scanner.hasNextInt()) {
                int izbira = scanner.nextInt();
                if (izbira >= 1 && izbira <= datoteke.length) {
                    return "resources/" + datoteke[izbira-1];
                }
            } else {
                scanner.next(); // Počisti napačen vnos
            }
            System.out.println("Neveljavna izbira. Prosimo, vnesite številko med 1 in " + datoteke.length + ".");
        }
    }


    public static void main(String[] args) {
        // HEAP SIZE, KER PAČ JE ZA HASHMAP POMEMBEN RAM IN VELIKOST RAMA
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        System.out.println("Max Heap Size: " + (maxHeapSize / (1024 * 1024)) + " MB");
        long zacetek;
        long konec;

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------");
        // DVE MOŽNOSTI IZ TXT ALI BOŠ VPISAL
        System.out.println("1. Bos vpisal besedilo");
        System.out.print("2. Bos bral iz external file-a: ");
        System.out.println();
        int kaj = scanner.nextInt();
        if (kaj == 1) {
            System.out.print("Vpisi dolzino n-gramov (ene sekvence) (1-5): ");
            int n = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Vpisi besedilo; ");
            String text = scanner.nextLine();
            zacetek = System.currentTimeMillis();
            narediVseInput(n, text);
            konec = System.currentTimeMillis();
        } else {
            System.out.print("Vpisi dolzino n-gramov (ene sekvence) (1-5): ");
            int n = scanner.nextInt();
            zacetek = System.currentTimeMillis();
            narediVseTxt(n);
            konec = System.currentTimeMillis();
        }
        scanner.close();
        System.out.println("Celoten proces je trajal: " + (konec - zacetek) + " ms");
    }

    public static void narediVseTxt(int n) {
        System.out.println("--------------------------------");
        String filePath = izbiraTeksta();
        String prebrano = preberiIzTxt(filePath);
        String cleaned = odstraniZnakce(prebrano);
        Map<String, Integer> nGrams = generateNGrams(n, cleaned);
        Map<String, Double> relFrekvence = izracunajRelativneFrekvence(nGrams);
        izpisiVse(nGrams, relFrekvence);
        System.out.println("--------------------------------");
    }

    public static void narediVseInput(int n, String besedilo) {
        System.out.println("--------------------------------");
        String cleaned = odstraniZnakce(besedilo);
        Map<String, Integer> nGrams = generateNGrams(n, cleaned);
        Map<String, Double> relFrekvence = izracunajRelativneFrekvence(nGrams);
        izpisiVse(nGrams, relFrekvence);
        System.out.println("--------------------------------");
    }

    public static String preberiIzTxt(String path) {
        // PREBERE TXT FILE IN GA VRNE KOT STRING
        Path filePath = Path.of(path);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return "napaka, pri branju besed iz file-a";
    }

    public static Map<String, Integer> generateNGrams(int n, String text) {
        // INICIALIZIRAMO HASH MAP KER JE NAJBOLJ UPORABNA
        // PODATKOVNA STRUKTURA ZA NAŠ PROBLEM
        // V HASH MAPU JE OGROMNO PODATKOV IN HITRO ISKANJE
        // ZATO JE GUT!
        Map<String, Integer> nGrams = new HashMap<>();
        // CELO BESEDILO RAZDELIMO NA POVEDI IN SHRANIMO V ARRAY STRINGOV
        String[] povedi = text.split("[.!?]");
        System.out.println("Število povedi: " + povedi.length);


        for (int i = 0; i < povedi.length; i++) {
            // FOR LOOP DA GREMO ZA VSAKO POVED
            // ODSTRANIMO MOREBITNE PREDLEDKE NA ZAČEKU ALI KONCU: " Maček lovi miško ", TO ODSTRANIMO
            String enaPoved = povedi[i].trim();
            // ČE JE POVED PRAZNA JO PRESKOČIMO
            if (enaPoved.isEmpty()) continue;
            // RAZDELIMO POVED NA BESEDE, NA PODLAGI ENEGA ALI VEČ PRESLEDKOV
            String[] besede = enaPoved.split("\\s+");
            // ČE JE V POVEDI PREMALO BESED ZA TVORBO N-GRAMA; RECIMO 5-GRAM V 3 BESEDNI POVEDI JO PRESKOČIMO
            if (besede.length < n) continue;
            // ZANKA JE TAKO NAREJENA DA ZAJAME VSE MOŽNE N-GRAME
            //["Maček", "lovi", "miško"] -> j=0 → "Maček lovi", j=1 → "lovi miško"
            for (int j = 0; j <= besede.length - n; j++) {
                // ustvarimo array iz besed na podlagi dolžine n grama
                // j je začetek j+n je pa koneec brez da bi upoštevali zadnega (tko kt python pr loopih)
                String[] ngramArray = Arrays.copyOfRange(besede, j, j + n);
                // združimo besede v en niz med katerimi je presledek
                String ngram = String.join(" ", ngramArray).trim();

                if (nGrams.containsKey(ngram)) {
                    // ce n-gram že obstaja, povečamo števec za 1
                    nGrams.put(ngram, nGrams.get(ngram) + 1);
                } else {
                    // ce n-gram še ne obstaja, ga dodamo s števcem 1
                    nGrams.put(ngram, 1);
                }

            }
        }
        // vrne hash map z ngrami
        return nGrams;
    }

    public static Map<String, Double> izracunajRelativneFrekvence(Map<String, Integer> ngrams) {
        // namen je izračunati relativno frekvenco za vsak n-gram na podlagi prve besede n-grama
        // sprejme hash map ngramov in pojavitve -> { "maček lovi": 2, "maček spi": 1, "pes laja": 3 }
        // vrne hash map iz ngramom in relativno frekvenco -> { "maček lovi": 0.6667, "maček spi": 0.3333, "pes laja": 1.0 }

        // hrani vsoto pojavitev vseh ngramov, ki s začnejo z isto prvo besedo
        Map<String, Integer> zacetneBesede = new HashMap<>();
        // tukaj bo pa končni rezultat kamor shranimo izračunane frekvence
        Map<String, Double> relativneFrekvence = new HashMap<>();

        // for loop ki gre čez vse ključe v hashmapi ngramov
        for (String ngram : ngrams.keySet()) {
            // razbije ngram na besede glede na presledke
            String[] parts = ngram.split(" ");
            if (parts.length == 0) continue;
            // vzame prvo besedo
            String zacetek = parts[0];
            // namesto if else.. poveca števec pojavitve te prve besede, če besede še ni pa vstavi nono besedo z začetno vrednostjo iz ngram.get
            zacetneBesede.put(zacetek, zacetneBesede.getOrDefault(zacetek, 0) + ngrams.get(ngram));
        }

        // zanka za izračun frekvenc
        // gre čez vse ngrame skupaj z njihovimi pojavitvami
        for (Map.Entry<String, Integer> entry : ngrams.entrySet()) {
            // dobi ngram njegov kljuc pac
            String ngram = entry.getKey();
            String[] parts = ngram.split(" ");
            if (parts.length == 0) continue;
            // ponovno vzame prvo besedo
            String zacetek = parts[0];
            // nardi izračun
            // entry predstavlja en n-gram in njegovo stevilo pojavitev
            // entry.getValue() pa vrne stevilo pojavitev tega ngrama
            // zacetne besede pa hrani pojavitev vseh ngramov ki se začnejo s to prvo besedo
            // verjetnost: st pojavitev n-grama / st pojavitev prve besede
            double verjetnost = (double) entry.getValue() / zacetneBesede.get(zacetek);
            // rezultat shrani v relativne frekvence
            relativneFrekvence.put(ngram, verjetnost);
        }
        return relativneFrekvence;
    }

    public static String odstraniZnakce(String text) {
        // odstranimo nepotrebne znakce v besedilu
        return text.replaceAll("[,;:¡¿]", "");
    }

    public static void izpisiVse(Map<String, Integer> ngrams, Map<String, Double> relativneFrekvence) {
        // izpise vse ngrame skupaj z njihovimi pojavitvami in relativno frekvenco
        // kot input sprejme dve hashmapi, eno z ngrami in drugo z frekvencami
        System.out.println("N-GRAMI -> PONOVITVE -> RELATIVNE FREKVENCE");
        for (String ngram : ngrams.keySet()) {
            // pridobi pojavitve
            int ponovitve = ngrams.get(ngram);
            // iz pridobi relatativn frekvenco za ta ngram in pomnoži z 100
            double relFrekvenca = relativneFrekvence.getOrDefault(ngram, 0.0) * 100;
            System.out.printf("%s -> %d -> %.4f%%%n", ngram, ponovitve, relFrekvenca);
        }
    }
}
