import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
       // dodat moram time in milis ampak ne vem kko merit ker pac smo odvisni od nasega inputa
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------");
        System.out.println("1. Bos vpisal besedilo");
        System.out.println("2. Bos bral iz external file-a: ");
        System.out.println();
        int kaj = scanner.nextInt();
        if (kaj == 1){
            System.out.print("Vpisi zeljeno dolzino n-gramov - n: ");
            int n = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Vpisi besedilo; ");
            String text = scanner.nextLine();
            narediVseInput(n, text);
        }
        else{
            System.out.print("Vpisi zeljeno dolzino n-gramov - n: ");
            int n = scanner.nextInt();
            narediVseTxt(n);
        }
        scanner.close();
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        System.out.println("Maksimalnen heap space je: " + (maxHeapSize / (1024 * 1024)) + " MB");
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
        String prebrano = preberiIzTxt();
        List<String> nGrams = generateNGrams(n, prebrano);
        List<String> cleaned = odstraniZnakce(nGrams);
        Map<String, Integer> prestete = prestejPonovitve(cleaned);
        izpisiSekvencoInPonovitve(prestete);
        System.out.println("--------------------------------");
    }
    /**
     * FUNKCIJA narediVseInput
     * funkcija ki naredi vse za besedilo, ki ga vpisemo kot input scanner fora
     * ker bi morali vsako funkcijo posebej klicat in potem skranjevat v vmesne variablese je fajn
     * ce imamo samo eno funkcijo ki klice vse. Torej uporabimo to funkcijo in ona klice vse ostale in toj to
     * @param n . Dolzina n grama
     * @param besedilo Besedilo iz katerega bomo delali n-grame
     */
    public static void narediVseInput(int n, String besedilo){
        System.out.println("--------------------------------");
        System.out.println("Tole so sekvence vseh n-gramov in ponovitve vsakega n-grama v besedilu: ");
        List<String> nGrams = generateNGrams(n, besedilo);
        List<String> cleaned = odstraniZnakce(nGrams);
        Map<String, Integer> prestete = prestejPonovitve(cleaned);
        izpisiSekvencoInPonovitve(prestete);
        System.out.println("--------------------------------");
    }
    /**
     * FUNKCIJA preberi txt file
     * prebere besedilo iz txt file-a in ga shrani v String
     * @return vrne besedilo iz txt file kot String
     */
    public static String preberiIzTxt() {
        // kle damo path do file-a ki vsebuje besedilo
        Path filePath = Path.of("resources/350MB.txt");
        try {
            return Files.readString(filePath);

        } catch (IOException e) {
            System.err.println("Napaka pri branju file-a " + e.getMessage());
        }
        return "Napaka, pri branju besed iz file-a";
    }

    /**
     * FUNKCIJA generateNGrams
     * Ustvari n-grame
     * @param n Dolzina n-gramov (koliko besed naj bo v enem delcku)
     * @param text Besedilo na podlagi katerega ustvarimo n-grame
     * @return A StringBuilder n gramov.
     */
    public static List<String> generateNGrams(int n, String text) {
        List<String> nGrams = new ArrayList<>();
        String[] words = text.split(" ");
        if (n <= 0 || n > words.length) {
            System.out.println("Napacna vrednost n. Mora biti vecja od 0 in manjsa ali od stevila besed v textu");
            return nGrams;
        }
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder nGram = new StringBuilder();
            for (int j = 0; j < n; j++) {
                nGram.append(words[i + j]);
                if (j < n - 1) {
                    nGram.append(" ");
                }
            }
            nGrams.add(nGram.toString());
        }
        return nGrams;
    }
    /**
     * FUNKCIJA odstraniZnakce
     * Ker mi samo splitamo samo po " ", se lahko v sekvencah pojavijo klicaji, dvopicja, vprasaji, vejice
     * Primer: "lep dan." ali "lep dan!"
     * Torej ta funkcija odstrani vse te neuporabne znakce; vejice in pike in klicaje in vprasaje...
     * @param list List teh vseh sekvenc oziroma v nasem primeru bodo to n-grami
     * @return a Map <String, Integer> list teh sekvenc brez teh znakcov
     */
    public static List<String> odstraniZnakce(List<String> list) {
        List<String> cleanedStrings = new ArrayList<>();
        for (String signs : list) {
            // Remove punctuation using regex
            String cleaned = signs.replaceAll("[.,!?;:¡¿]", "");
            cleanedStrings.add(cleaned);
        }
        return cleanedStrings;
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

