# Analysis of N-grams (Simple Text Analysis) – Sequential Implementation

[🇸🇮 Read in Slovenian](./slo-readme/README.md)

N-gram analysis is one of the fundamental techniques in NLP and is used in many real-world systems — including Google Search. Search engines use n-grams to better understand how words appear together, which helps them predict user intent, correct spelling errors, find synonyms, and rank results. N-grams are also used in recommendation systems, spam detection, suggestion engines, autocomplete features, and in language modelling for machine translation.

## 🧩 What It Does
This program shows:
- how many n-grams appear in a given text,
- how many times each n-gram occurs,
- what the relative frequency is (based on the first word only),
- and how long (in seconds) the full process took.

## ⚙️ Usage
The program accepts two arguments:
1. A long text input,
2. An integer *n* — the length of the n-grams.

## 🧪 Example
Input arguments:
1. Text: “Today is a nice day, a nice day today.”
2. n = 2.

## 📝 Results
(ngram → occurrences → relative frequency)
- today is → 1 → 100%
- is a → 1 → 50%
- a nice → 2 → 100%
- nice day → 1 → 50%
- day a → 1 → 50%
- day today → 1 → 50%


Total processing time: 10 ms

## 🏁 Testing Setup
Testing (and implementation) was performed on my personal laptop:  
**Apple MacBook Pro, M1 Max, 64GB / 2TB.**  
(I bought the laptop for €1600 and it’s the 16-inch model :) )

The Java virtual machine was given approximately **16GB max heap size** for program execution.  
Available RAM heavily affects execution time, because the program uses a `HashMap` and processes very large input data.  
More RAM → faster execution.

Tests were run on five different `.txt` files of various sizes, with n-gram lengths from 2 to 5.  
All tests were done **without printing** individual n-grams, because printing would drastically slow down execution.  
If you want to print n-grams, simply uncomment the print line inside the `narediVseTxt` function.

### Testing Table

| File Size | n = 2     | n = 3     | n = 4     | n = 5     |
|-----------|-----------|-----------|-----------|-----------|
| **123MB** | 7.68 sec  | 11.41 sec | 14.74 sec | 14.81 sec |
| **234MB** | 21.20 sec | 29.46 sec | 34.22 sec | 37.31 sec |
| **350MB** | 32.41 sec | 48.56 sec | 51.07 sec | 54.04 sec |
| **490MB** | 33.26 sec | 42.82 sec | 53.34 sec | 60.85 sec |
| **613MB** | 35.01 sec | 53.12 sec | 64.67 sec | 74.80 sec |

#### Note:
The numbers follow the **European decimal format**, where commas represent decimals.


## 🚩 Important notes for running the project
1. If you do not have project locally already, use this command in Terminal:
` git clone https://github.com/Zankooo/N-Grams-Sequential.git `
2. In root directory create directory named'resources' and put following files in: 
https://drive.google.com/drive/folders/1GnL52MgBBja04Hhqun_TRghp_sVrtZ2F?usp=share_link 
3. Program now you can run in whatever Integrated development environment - IDE (Visual Studio, Intellij..) and it should work!
4. If you run it from Terminal from src directory use this two commands:
` javac Main.java && java Main `
and probably you will face a problem about 'Working directory'. You can solve it manually; change in path function 'izbiraTeksta'. Before the resources you must put two dots and slash. So fixed line is: 
`  return "../resources/" + datoteke[izbira-1]; `


## 💬 Additional Information About Program Behavior

At the start, the program asks whether you want to enter text manually as input or read the words from an external file.  
(The manual input option exists only so I could test the program quickly on very short text.)  

For correct usage according to the assignment, choose the second option by pressing **'2'**.


## ⚡ Improved Version of the Program

This program is implemented sequentially.  
However, due to its structure, it offers clear opportunities for optimisation.  
An optimised **parallel (multi-core, multithreaded)** version is available here:  
https://github.com/Zankooo/N-Grams-Paralell
