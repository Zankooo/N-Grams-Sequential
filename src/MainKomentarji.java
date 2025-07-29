import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MainKomentarji {
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
     * @return slovar ki ima kljuc()n-gram in vrednost(stevilo ponovitev)
     */
    public static Map<String, Integer> generateNGrams(int n, String text) {
        //hash map za shranjevanje  n-gramov in njihovih frekvenc
        Map<String, Integer> nGrams = new HashMap<>();
        // razbijemo besedilo na locene stavke glede na locila
        String[] povedi = text.split("[.!?]");
        //izpisemo koliko stavkov je
        System.out.println("Število povedi: ");
        System.out.println(String.format("%,d", povedi.length).replace(",", "."));


        //text = "dan je lep. dan je dolg." n = 2
        // Stavki: "dan je lep" in " dan je dolg"
        // n grami:
        // "dan je" → 2×
        // "je lep" → 1×
        // "je dolg" → 1×
        // to naredi ta koda spodaj

        for (int i = 0; i < povedi.length; i++) {
            //stavek razdelimo na besede in jih shranimo v array stringov. Recimo stavek; "dan je lep sončen dan"
            // gre tkole -> besede = ["dan", "je", "lep", "sončen", "dan"]
            String enaPoved = povedi[i];
            String[] besede = enaPoved.split(" ");

            // besede.length - n -> poskrbi da ne gremo cez rob tabele
            // ustvarimo n-gramse
            // recimo j = 0; j < 5 - 3 = 2
            // kle je zgleda <= prav? to jutri preverit
            // "To je en lep dan" -> {"To", "je", "en", "lep", "dan"};
            // 5 - 3 = 2
            // ta for loop je tko strukturiran da pokrije vse besede in da zajame vse n-grame v povedi
            for (int j = 0; j <= besede.length - n; j++) {
                // koliko zajamemo pac
                // j=0 do j+n = 3 -> {"To", "je", "en"}
                // j=1 do j+3=4 -> {"je", "en", "lep"}
                // j=2 do j+3 = 5 -> {"en", "lep", "dan"}
                // ta of range deluje na podlagi indeksov in zaden je eno vec kot, kot v pythonu za for loop
                // vzame besede na teh indeksih, od indeksa j do indeksa (j + n), n (ne vključujoč zadnjega), torej predzadnji, (j+n)-1
                String[] ngramArray = Arrays.copyOfRange(besede, j, j + n);
                // in potem te arraye besed zdruzimo v string
                String ngram = String.join(" ", ngramArray);

                // preverimo če ta n-gram že obstaja v hashmapi in če ja; povečamo števec za 1
                if (nGrams.containsKey(ngram)) {
                    nGrams.put(ngram, nGrams.get(ngram) + 1);
                } else {
                    // če ne obstaja še v hashmapi, ga dodamo in mu damo vrednost ena
                    nGrams.put(ngram, 1);
                }
            }
        }
        //vrnemo hasmmap z ngrami
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
        return text.replaceAll("[,;:¡¿]", "");
    }


    /**
     * FUNKCIJA izpisiSekvencoInPonovitve
     * Funkcija ki v for loopu sprinta dejansko vse iz hashmape (vse ngrame - kljuce) in njihove vrednosti
     * @param ngrams  -> hashmap ključev (ngramov) in njihov pojavitev (vrednosti)
     */
    public static void izpisiSekvencoInPonovitve(Map<String, Integer> ngrams) {
        for (Map.Entry<String, Integer> entry : ngrams.entrySet()) {
            // sprinta vse iz hashmape -> torej key (ngrame) in vrednost
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

}

