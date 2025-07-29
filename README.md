# Analysis of N-grams (simple text analysis) - sekvenčna izvedba



## <ins>Kaj dela</ins>: Program, ki pokaže: 
### - koliko n-gramov je v nekem besedilu,
### - kolikokrat se n-grami ponovijo v tem besedilu in,
### - koliko je relativna frekvenca na podlagi le prve besede in
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
### <ins>Za testiranje sem uporabil svoj lasten laptop: Macbook Pro, M1 Max, 64gb/2Tera. Java virtual machine (max heap size) sem dal na voljo 16,384 GB rama za izvajanje programa. </ins>
### Testiranje je bilo opravljenju na petih različno velikih .txt file-ih.
#### - 123MB -> <ins>1.</ins> n = 2, t = 21435ms; <ins>2.</ins> n = 3, t = 43535ms; <ins>3.</ins> n = 4, t = 66938ms; <ins>4.</ins> n = 5, t = 97387ms;
#### - 234MB -> <ins>1.</ins> n = 2, t = 82482ms; <ins>2.</ins> n = 3, t = 151894ms; <ins>3.</ins> n = 4, t = 198564ms; <ins>4.</ins> n = 5, t = 208350ms;
#### - 350MB -> <ins>1.</ins> n = 2, t = 121338ms; <ins>2.</ins> n = 3, t = 207659ms; <ins>3.</ins> n = 4, t = 261413ms; <ins>4.</ins> n = 5, t = 282025ms;
#### - 490MB -> <ins>1.</ins> n = 2, t = 62269ms; <ins>2.</ins> n = 3, t = 129345ms; <ins>3.</ins> n = 4, t = 189522ms; <ins>4.</ins> n = 5, t = 234982ms;
#### - 613MB -> <ins>1.</ins> n = 2, t = 75392ms; <ins>2.</ins> n = 3, t = 151535ms; <ins>3.</ins> n = 4, t = 227903ms; <ins>4.</ins> n = 5, t = 285432ms;
###  
<hr>

## <ins>Pomembne opombe o delovanju programa: </ins>
### Program te na začetku vpraša; ali želiš vpisati besedilo kot input ali pa boš bral besede iz external file-a (kot input sem dal možnost samo za to, da sem lahko testiral na zelo kratkem besedilu.)
### Ko pa izbereš "naravno pot" oziroma daš 2 (da boš bral iz external file-a), pa je pomembno da pred tem ustvariš direktorij z imenom *resources* in v njega daš file z besedilom. Na voljo so na tej povezavi: 
### https://drive.google.com/drive/folders/1GnL52MgBBja04Hhqun_TRghp_sVrtZ2F?usp=share_link

 

<hr>

## <ins>Največji problemi pri implementaciji</ins>
### Največji problem mi je zagotovo predstavljal *error heap space*. Torej koliko memory-ja (rama) lahko Java uporabi na računalniku. Imel sem 2g vendar to ni bilo dosti in sem moral povečati da je nato delovalo pravilno! 