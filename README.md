# Analysis of N-grams (simple text analysis)
## <ins>Kaj dela</ins>: Program, ki pokaže: 
### - koliko n-gramov je v nekem besedilu,
### - kolikokrat se n-grami ponovijo v tem besedilu in,
### - koliko časa (v miliseconds) je cel proces trajal.

<hr>

## <ins>Uporaba</ins>: Program uporabljamo tako da mu določimo dva argumenta. 
### - prvi je neko dolgo besedilo in,
### - drugi n, ki je dolžina n-gramov.

<hr>

## <ins>Primer uporabe</ins>: 
### Inputa (argumenta) -> 
#### 1. Besedilo; "Danes je lep dan, lep dan je danes." 
#### 2. n = 2.


### Outputa (rezultati) -> 
#### - danes je -> 1
#### - je lep -> 1
####  - lep dan -> 2
#### - dan lep -> 1
#### - dan je -> 1
#### - je danes -> 1
#### Celoten proces je trajal: 10 ms

<hr>

## <ins> Testiranje </ins>
### Za testiranje sem uporabil svoj lasten laptop: Macbook Pro, M1, 8gb/256gb.
### Testiranje je bilo opravljenju na petih različno velikih .txt file-ih. 
#### - 123MB -> <ins>1.</ins> n = 2, t = 8897ms; <ins>2.</ins> n = 3, t = 13963ms; <ins>3.</ins> n = 4, t = 19126ms; <ins>4.</ins> n = 5, t = 22953ms;
#### - 234MB -> <ins>1.</ins> n = 2, t = 29865ms; <ins>2.</ins> n = 3, t = 85826ms; <ins>3.</ins> n = 4, t = 14256ms; <ins>4.</ins> n = 5, t = 262990ms;
#### - 350MB -> <ins>1.</ins> n = 2, t = 61968ms; <ins>2.</ins> n = 3, t = 19877ms; <ins>3.</ins> n = 4, t = 227018ms; <ins>4.</ins> n = 5, t = 499184ms;
#### - 490MB -> <ins>1.</ins> n = 2, t = 35336ms; <ins>2.</ins> n = 3, t = 74074ms; <ins>3.</ins> n = 4, t = 15973ms; <ins>4.</ins> n = 5, t = 386747ms;
#### - 613MB -> <ins>1.</ins> n = 2, t = 57517ms; <ins>2.</ins> n = 3, t = 138248ms; <ins>3.</ins> n = 4, t = 314026ms; <ins>4.</ins> n = 5, t = 1507870ms;
### Vredno je omeniti, da so časovni rezultati dokaj porazni. To lahko v veliki meri pripišemo dejstvu, da ima moj laptop 8gb unified memory-ja (rama). Imel sem namreč možnost narediti en test tudi na laptopu; MacBook Pro, M2 Pro, 16gb/512gb in časovni rezultat pri 613MB file-u je bil nekajkrat hitrejši! 
<hr>

## <ins>Pomembne opombe o delovanju programa: </ins>
### Program te na začetku vpraša; ali želiš vpisati besedilo kot input ali pa boš bral besede iz external file-a (kot input sem dal možnost samo za to, da sem lahko testiral na zelo kratkem besedilu.)
### Ko pa izbereš "naravno pot" oziroma daš 2 (da boš bral iz external file-a), pa je pomembno da pred tem ustvariš direktorij z imenom *resources* in v njega daš file z besedilom. Na voljo so na tej povezavi: 
### https://drive.google.com/drive/folders/1GnL52MgBBja04Hhqun_TRghp_sVrtZ2F?usp=share_link

### Iz katerega file-a boš bral besede pa določiš direkt pod classom Main, 7 vrstica kode. 

<hr>

## <ins>Največji problemi pri implementaciji</ins>
### Največji problem mi je zagotovo predstavljal *error heap space*. Torej koliko memory-ja lahko Java uporabi na računalniku. Imel sem 2g vendar to ni bilo dovolj in moral sem dati na 8gb vendar mi nikakor ni uspelo, ampak po mnogih poskusih je le nekako uspelo.  