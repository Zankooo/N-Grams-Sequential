import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static String izbiraTeksta() {
        String[] datoteke = {"123MB.txt", "234MB.txt", "350MB.txt", "490MB.txt", "613MB.txt"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Izberi besedilo:");
        for (int i = 0; i < datoteke.length; i++) {
            System.out.println((i+1) + ". " + datoteke[i]);
        }
        while (true) {
            System.out.print("Vaša izbira (1-" + datoteke.length + "): ");
            if (scanner.hasNextInt()) {
                int izbira = scanner.nextInt();
                if (izbira >= 1 && izbira <= datoteke.length) {
                    return "resources/" + datoteke[izbira-1];
                }
            } else {
                scanner.next();
            }
            System.out.println("Neveljavna izbira. Prosimo, vnesite številko med 1 in " + datoteke.length + ".");
        }
    }

    public static void main(String[] args) {
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        System.out.println("Max Heap Size (količina rama za JVM): " + (maxHeapSize / (1024 * 1024)) + " MB");

        long zacetek;
        long konec;

        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------");
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
        //izpisiVse(nGrams, relFrekvence);
        System.out.println("--------------------------------");
    }

    public static void narediVseInput(int n, String besedilo) {
        System.out.println("--------------------------------");
        String cleaned = odstraniZnakce(besedilo);
        Map<String, Integer> nGrams = generateNGrams(n, cleaned);
        Map<String, Double> relFrekvence = izracunajRelativneFrekvence(nGrams);
        //izpisiVse(nGrams, relFrekvence);
        System.out.println("--------------------------------");
    }

    public static String preberiIzTxt(String path) {
        Path filePath = Path.of(path);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return "napaka, pri branju besed iz file-a";
    }

    public static Map<String, Integer> generateNGrams(int n, String text) {
        Map<String, Integer> nGrams = new HashMap<>();
        String[] povedi = text.split("[.!?]");
        System.out.println("Število povedi: " + povedi.length);


        for (int i = 0; i < povedi.length; i++) {
            String enaPoved = povedi[i].trim();
            if (enaPoved.isEmpty()) continue;
            String[] besede = enaPoved.split("\\s+");
            if (besede.length < n) continue;
            for (int j = 0; j <= besede.length - n; j++) {
                String[] ngramArray = Arrays.copyOfRange(besede, j, j + n);
                String ngram = String.join(" ", ngramArray).trim();

                if (nGrams.containsKey(ngram)) {
                    nGrams.put(ngram, nGrams.get(ngram) + 1);
                } else {
                    nGrams.put(ngram, 1);
                }
            }
        }
        return nGrams;
    }

    public static Map<String, Double> izracunajRelativneFrekvence(Map<String, Integer> ngrams) {
        Map<String, Integer> zacetneBesede = new HashMap<>();
        Map<String, Double> relativneFrekvence = new HashMap<>();

        for (String ngram : ngrams.keySet()) {
            String[] parts = ngram.split(" ");
            if (parts.length == 0) continue;
            String zacetek = parts[0];
            zacetneBesede.put(zacetek, zacetneBesede.getOrDefault(zacetek, 0) + ngrams.get(ngram));
        }

        for (Map.Entry<String, Integer> entry : ngrams.entrySet()) {
            String ngram = entry.getKey();
            String[] parts = ngram.split(" ");
            if (parts.length == 0) continue;
            String zacetek = parts[0];
            double verjetnost = (double) entry.getValue() / zacetneBesede.get(zacetek);
            relativneFrekvence.put(ngram, verjetnost);
        }
        return relativneFrekvence;
    }

    public static String odstraniZnakce(String text) {
        return text.replaceAll("[,;:¡¿]", "");
    }

    public static void izpisiVse(Map<String, Integer> ngrams, Map<String, Double> relativneFrekvence) {
        System.out.println("N-GRAMI -> PONOVITVE -> RELATIVNE FREKVENCE");
        for (String ngram : ngrams.keySet()) {
            int ponovitve = ngrams.get(ngram);
            double relFrekvenca = relativneFrekvence.getOrDefault(ngram, 0.0) * 100;
            System.out.printf("%s -> %d -> %.4f%%%n", ngram, ponovitve, relFrekvenca);
        }
    }
}
