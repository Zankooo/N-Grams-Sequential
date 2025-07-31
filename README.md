# Analysis of N-grams (simple text analysis) - zaporedena (sekvenčna) izvedba



## <ins>Kaj dela</ins>: Program, ki pokaže: 
### - koliko n-gramov je v nekem besedilu,
### - kolikokrat se n-grami ponovijo v tem besedilu in,
### - koliko je relativna frekvenca na podlagi le prve besede in
### - koliko časa (v miliseconds) je cel proces trajal.

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
### Za testiranje sem uporabil svoj lasten laptop: <ins>Apple MacBook Pro, M1 Max, 64GB/2TB</ins>.
#### (Komp sem kupil za 1600eur in še 16 inch je :) )
### Javi virtual machine sem dal na voljo cca 16GB max heap size (rama) za izvajanje programa. Na trajanje programa je zelo pomembno koliko ga imamo na voljo, saj uporabljamo v programu podatkovno strukturo HashMap in kot input dajemo podatke ki so precej veliki. (HashMap in veliki podatki --> hitrost izvajanja programa odvisna od velikosti rama)</ins>
### Testiranje je bilo opravljenju na petih različno velikih .txt file-ih. Dolzina n-gramov pa je od 2 do 5. Tesitranje je bilo opravljeno brez printanja n-gramov z pojavitvami in relativnimi frekvencami. Če bi jih printali bi program trajal občutno dlje.

| Tabela    | n = 2     | n = 3     | n = 4     | n = 5     |
|-----------|-----------|-----------|-----------|-----------|
| **123MB** | 9,68 sec  | 13,41 sec | 16,74 sec | 16,81 sec |
| **234MB** | 23,20 sec | 31,46 sec | 36,22 sec | 39,31 sec |
| **350MB** | 34,41 sec | 50,56 sec | 53,07 sec | 56,04 sec |
| **490MB** | 35,26 sec | 44,82 sec | 55,34 sec | 62,85 sec |
| **613MB** | 37,01 sec | 55,12 sec | 66,67 sec | 76,80 sec |

#### Opomba: številke so zapisane v evropskem formatu, kjer vejica pomeni decimalko
<hr>

## <ins>Pomembne opombe za uspešno delovanje programa</ins>
### Program te na začetku vpraša; ali želiš vpisati besedilo kot input ali pa boš bral besede iz external file-a (kot input sem dal možnost samo za to, da sem lahko testiral na zelo kratkem besedilu.)
### Ko pa izbereš "naravno pot" oziroma daš 2 (da boš bral iz external file-a), pa je pomembno, da pred tem ustvariš direktorij z imenom *resources* (ustvari ga v mapi projekta, ne v src) in v njega daš datoteke z besedili. Datoteke z besedili so na voljo na tej povezavi: 
### https://drive.google.com/drive/folders/1GnL52MgBBja04Hhqun_TRghp_sVrtZ2F?usp=share_link

<hr>

## <ins>Druge informacije</ins>
### - uporabljal sem trenutno najnovejšo verzijo Jave; JDK 24
### - pri izdelovanju programa sem si pomagal z umetno inteligenco - predvsem ChatGPT-4o. 
