# Analysis of N-grams (simple text analysis) - zaporedena (sekvenčna) izvedba



## <ins>Kaj dela</ins>: Program, ki pokaže: 
### - koliko n-gramov je v nekem besedilu,
### - kolikokrat se n-grami ponovijo v tem besedilu,
### - koliko je relativna frekvenca na podlagi le prve besede in
### - koliko časa (v sekundah) je cel proces trajal.

<hr>

## <ins>Uporaba</ins>: Program uporabljamo tako, da mu določimo dva argumenta. 
### - prvi je neko dolgo besedilo in,
### - drugi n, ki je dolžina n-gramov.

<hr>

## <ins>Primer uporabe</ins>: 
### Inputa (argumenta) -> 
#### 1. Besedilo; "Danes je lep dan, lep dan je danes." 
#### 2. n = 2.


### Rezultati (ngram -> pojavitev -> relativna frekvenca) -> 
#### - danes je -> 1 -> 100%
#### - je lep -> 1 -> 50%
####  - lep dan -> 2 -> 100%
#### - dan lep -> 1 -> 50%
#### - dan je -> 1 -> 50%
#### - je danes -> 1 -> 50%
#### Celoten proces je trajal: 10 ms

<hr>

## <ins> Testiranje </ins>
### Za testiranje (in tudi za implementacijo) sem uporabil svoj lasten laptop: <ins>Apple MacBook Pro, M1 Max, 64GB/2TB</ins>.
#### (Komp sem kupil za 1600eur in še 16 inch je :) )
### Javi virtual machine sem dal na voljo cca 16GB max heap size (rama) za izvajanje programa. Na trajanje programa je zelo pomembno koliko ga imamo na voljo, saj uporabljamo v programu podatkovno strukturo HashMap in kot input dajemo podatke ki so precej veliki. (HashMap in veliki podatki --> hitrost izvajanja programa odvisna od velikosti rama)</ins>
### Testiranje je bilo opravljenju na petih različno velikih .txt datotekah. Dolžina n-gramov pa je od 2 do 5. Opravljeno je bilo brez printanja n-gramov z pojavitvami in relativnimi frekvencami. Če bi jih printali bi program trajal občutno dlje. Če želimo printati n-grame samo odkomentiramo vrstico v funkciji 'narediVseTxt'
### Tabela rezultatov testiranja:

Tabela    | n = 2     | n = 3     | n = 4     | n = 5
----------|-----------|-----------|-----------|-----------
**123MB** | 7,68 sec  | 11,41 sec | 14,74 sec | 14,81 sec 
**234MB** | 21,20 sec | 29,46 sec | 34,22 sec | 37,31 sec 
**350MB** | 32,41 sec | 48,56 sec | 51,07 sec | 54,04 sec 
**490MB** | 33,26 sec | 42,82 sec | 53,34 sec | 60,85 sec 
**613MB** | 35,01 sec | 53,12 sec | 64,67 sec | 74,80 sec


#### Opomba: številke so zapisane v evropskem formatu, kjer vejica pomeni decimalko
<hr>

## <ins>Pomembne opombe za uspešen zagon programa (step by step)</ins>
### 1. Če programa še nimaš lokalno, ga pridobiš z komando v terminal:
#### ` git clone https://github.com/Zankooo/N-Grams-Sequential.git `
### 2. V root direktoriju ustvariš direktorij 'resources' in vanj daš datoteke iz tega linka: 
### https://drive.google.com/drive/folders/1GnL52MgBBja04Hhqun_TRghp_sVrtZ2F?usp=share_link 
### 3. Program nato lahko poženeš preko po želji izbranega IDE (Visual Studio, Intellij) in mora delovati!
### 4. Če pa poganjaš program preko Terminala iz src direktorija z komandama:
#### ` javac Main.java && java Main `
### pa zelo verjetno nastane težava zaradi 'Working direktorija'. To rešiš tako da v kodi manualno spremeniš path v funkciji 'izbiraTeksta'. Pred resources moraš dodati dve piki in slash. Torej popravljena cela vrstica: 
#### `  return "../resources/" + datoteke[izbira-1]; `

## <ins>Dodatne informacije o delovanju programa </ins>
### Program te na začetku vpraša; ali želiš vpisati besedilo kot input ali pa boš bral besede iz external file-a (kot input sem dal možnost samo za to, da sem lahko testiral na zelo kratkem besedilu.). Za delovanje po programa po navodilih pa izbereš drugo možnost s pritiskom na '2'. 

<hr>

## <ins>Druge informacije</ins>
### - uporabljal sem trenutno najnovejšo verzijo Jave; JDK 24
<hr>

## <ins>Viri in literatura</ins>
### Primarno sem si pri izdelovanju projekta pomagal z znanjem pridobljenim na predavanjih in vajah:
### https://e.famnit.upr.si/course/view.php?id=6182 - letošnja eučilnica
### https://e.famnit.upr.si/course/view.php?id=4943 - eučilnica preteklih let
### Nekajkrat pa sem poiskal pomoč tudi pri umetni inteligenci: ChatGPT-4o 

