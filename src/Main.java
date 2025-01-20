import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    static String filePath = "resources/613MB.txt";
    public static void main(String[] args) {
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        System.out.println("Max Heap Size: " + (maxHeapSize / (1024 * 1024)) + " MB");
        long zacetek;
        long konec;

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------");

        System.out.println("1. Bos vpisal besedilo");
        System.out.println("2. Bos bral iz external file-a: ");
        System.out.println();
        int kaj = scanner.nextInt();
        if (kaj == 1){
            System.out.print("Vpisi dolzino n-gramov (ene sekvence): ");
            int n = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Vpisi besedilo; ");
            String text = scanner.nextLine();
            zacetek = System.currentTimeMillis();
            narediVseInput(n, text);
            konec = System.currentTimeMillis();
        }
        else{
            System.out.print("Vpisi dolzino n-gramov (ene sekvence): ");
            int n = scanner.nextInt();
            zacetek = System.currentTimeMillis();
            narediVseTxt(n);
            konec = System.currentTimeMillis();
        }
        scanner.close();
        // trajanje programa
        System.out.println("Celoten proces je trajal: " + (konec - zacetek) + " ms");

    }

    /**
     * FUNKCIJA narediVseTxt
     * funkcija ki naredi vse za besedilo iz txt file-a
     * ker bi morali vsako funkcijo posebej klicat in potem skranjevat v vmesne variablese je fajn
     * ce imamo samo eno funkcijo ki klice vse. Torej uporabimo to funkcijo in ona klice vse ostale in toj to
     * @param n . Dolzina n grama
     */
    public static void narediVseTxt(int n){
        System.out.println("--------------------------------");
        System.out.println("Tole so sekvence vseh n-gramov in ponovitve vsakega n-grama v besedilu: ");
        String prebrano = preberiIzTxt(filePath);
        String cleaned = odstraniZnakce(prebrano);
        Map<String, Integer> nGrams = generateNGrams(n, cleaned);
        izpisiSekvencoInPonovitve(nGrams);
        System.out.println("--------------------------------");
    }
    /**
     * FUNKCIJA narediVseInput
     * funkcija ki naredi vse za besedilo, ki ga vpisemo kot input scanner fora
     * ker bi morali vsako funkcijo posebej klicat in potem skranjevat v vmesne variablese je fajn
     * ce imamo samo eno funkcijo ki klice vse. Torej uporabimo to funkcijo in ona klice vse ostale in toj to
     * @param n  Dolzina n grama
     * @param besedilo Besedilo iz katerega bomo delali n-grame
     */
    public static void narediVseInput(int n, String besedilo){
        System.out.println("--------------------------------");
        System.out.println("Tole so sekvence vseh n-gramov in ponovitve vsakega n-grama v besedilu: ");
        String cleaned = odstraniZnakce(besedilo);
        Map<String, Integer> nGrams = generateNGrams(n, cleaned);
        izpisiSekvencoInPonovitve(nGrams);
        System.out.println("--------------------------------");
    }
    /**
     * FUNKCIJA preberi txt file
     * prebere besedilo iz txt file-a in ga shrani v String
     * @return vrne besedilo iz txt file kot String
     */
    public static String preberiIzTxt(String path) {
        // kle damo path do file-a ki vsebuje besedilo
        Path filePath = Path.of(path);
        try {
            return Files.readString(filePath);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return "napaka, pri branju besed iz file-a";
    }

    /**
     * FUNKCIJA generateNGrams
     * Ustvari n-grame
     * @param n Dolzina n-gramov (koliko besed naj bo v enem delcku)
     * @param text Besedilo na podlagi katerega ustvarimo n-grame
     * @return A StringBuilder n gramov.
     */
    public static Map<String, Integer> generateNGrams(int n, String text) {
        Map<String, Integer> nGrams = new HashMap<>();

        String[] stavki = text.split("[.!?]");
        System.out.println("Število povedi: " + stavki.length);

        for (String stavek : stavki) {
            String[] besede = stavek.split(" ");
            for (int j = 0; j < besede.length - n; j++) {
                String[] ngramArray = Arrays.copyOfRange(besede, j, j + n);
                String ngram = String.join(" ", ngramArray);
                if (nGrams.containsKey(ngram)) {
                    nGrams.put(ngram, nGrams.get(ngram) + 1);
                } else {
                    nGrams.put(ngram, 1);
                }
            }
        }
        return nGrams;
    }
    /**
     * FUNKCIJA odstraniZnakce
     * Ker mi samo splitamo samo po " ", se lahko v sekvencah pojavijo klicaji, dvopicja, vprasaji, vejice
     * Primer: "lep dan." ali "lep dan!"
     * Torej ta funkcija odstrani vse te neuporabne znakce; vejice in pike in klicaje in vprasaje...
     * @param text List teh vseh sekvenc oziroma v nasem primeru bodo to n-grami
     * @return a Map <String, Integer> list teh sekvenc brez teh znakcov
     */
    public static String odstraniZnakce(String text) {
        String cleaned = text.replaceAll("[,;:¡¿]", "");
        return cleaned;
    }

    /**
     * FUNKCIJA prestejPonovitve
     * Presteje kolikokrat se vsaka sekvenca ponovi
     * @param list List teh vseh sekvenc oziroma v nasem primeru bodo to n-grami
     * @return a Map <String, Integer> counts ki vsebuje frekvenco (kolikokrat se ponovi) vsakege/a sekvence/a.
     */
    public static Map<String, Integer> prestejPonovitve(List<String> list) {
        Map<String, Integer> counts = new HashMap<>();
        for (String element : list) {
            counts.put(element, counts.getOrDefault(element, 0) + 1);
        }
        return counts;
    }

    /**
     * FUNKCIJA izpisiSekvencoInPonovitve
     * Funkcija ki v for loopu sprinta dejansko vse pojavitve vsake frekvence
     * @param  counts Torej frekvence vseh sekvenc, basically output funkcije countOccurences
     */
    public static void izpisiSekvencoInPonovitve(Map<String, Integer> counts) {
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

}

