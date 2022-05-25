### 2122ALG2-Kral-Autobazar

# Autobazar

## Zadání

Majitel prestižního autobazaru v Liberci tě požádal, aby si mu pomohl se simulací prodejů v jeho autobazaru. Musíš autobazar vytvořit a pojmenovat. Budeš pracovat se souborem aut, který je právě v autobazaru k dispozici a se souborem prodejců, kteří pro majitele pracují. Když v jakýkoliv den dojdou auta ze stavu, týden tímto končí.

Program bude pracovat následovně:

  1. ukázat seřazený seznam aut
  3. ukázat seřazený seznam prodejců se zkušenostmi
  4. zahaj prodej
      * Kterého prodejce si chceš vybrat?
        * <seznam_prodejců> -> vybere prodejce
      * Na základně zkušeností prodejce se auto prodalo za cenu x.
      * Připsání peněž na účet prodejce a autobazaru.
  5. ukončit 

Srážení procent na základě zkušeností bude vypadat následovně: 

- | 0. až 3. úroveň -> 15 - 11% z částky auta
- | 4. až 7. úroveň -> 10 - 6% z částky auta
- | 8. až 10. úroveň -> 5 - 0% z čátky auta

Bude možnost vybírat ze souborů .csv, ve kterém budou údaje o autech (značka, model, kilometry, rok výroby, palivo, barva, VIN, cena) a o prodejcích(jméno, příjmení, věk, zkušenosti).

Soubor .csv s auty je veřejně dostupný dataset ze stránky: https://www.kaggle.com/datasets/doaaalsenani/usa-cers-dataset

Po ukončení týdne se do terminálu a do souboru vypíše týdenní výsledková listina, na které bude částka za všechna prodaná auta, seznam všech prodejců s jejich atributy a provizemi. Na konci bude celkový výsledek hospodaření celého podniku. Program se ukončí. 

## Řešení

### Funkční řešení

Návrh menu s metodami
1. Seznam aut
   * printCarsSorted();  
3. Seznam prodejců
   * printSellersSorted();
5. Prodej
   * Přeješ si pokračovat?
   * if ano
      * printSellersSorted();
      * "Vyber prodejce: "
      * Výpis prodaného auta, vypsání jaký prodejce auto prodal, kolik má provizi z prodeje a celkovou provizi autobazaru
   * else
      * zpět na začátek celkového výběru  
7. Konec
   * saveToPdf(File results);
   * saveToBinary(File results);
   * readBinaryResults(File results);

### Popis struktury vstupních a výstupních souborů

Vstupní soubory jsou typu .csv a jednotlivé údaje o autech a prodejcích jsou odděleny středníkem (;). U objektů File s cestou k souborům je jasně dán název souborů a to cars.csv a sellers.csv.

Výstupní soubory budou 2. Jeden soubor bude ve formátu .pdf s jasně danou ukládací cestou do ../Autobazar/src/data/results.pdf. Budou v něm data, která jsem již zmiňoval z zadání práce. Ve stejném čase se uloží i binární soubor .bin s jasně danou ukládací cestou do ../Autobazar/src/data/results.dat, do kterého se uloží stejná data jako do .pdf a bude z něj následně vypisováno přes metodu do konzole.

### Class diagram

 ![Autobazar - Class diagram](/stuff/class_diagram.png)

## Testování

TODO

## Popis fungování externí knihovny

### iText7

Pro práci jsem zvolil knihovnu **iText pdf verze 7**. Pro správnou funkčnost bylo potřeba zmigrovat můj stávající projekt do Mavenu. Byla potřeba zapsat potřebné dependencies do souboru pom.xml, které jsem našel na oficiálních stránkách knihovny. Následně již vše funguje tak, jak má. Knihovna má několik tříd s několika objekty např. tyto: (jsou to ty, které jsem konkrétně použil)

- PdfDocument - vytvoření samotného pdf dokumentu
- PdfWriter - pro zapisování do dokumentu
- Document - hlavní část skladby pdf dokumentu, nastavování rozměrů stránky, přidávání odstavců atd.
- FontProgram - vytváření fontů
- PdfFont - samotné vytvoření a nastavení fontů pro další použití v kódu
- Text - samotný text, který chcete vložit, nastavuje se v něm i např. font, barva, bold, italic
- Paragraph - část textu, může jít o text, obrázek, tabulku, přidává se do objetu Document přes metodu .add()

Pro lepší přehlednost a vysvětlení postupu instalace a použití přikládám URL odkazy:

- https://kb.itextpdf.com/home/it7kb/installation-guidelines/installing-itext-7-for-java -> instalace
- https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-chapter-1 -> návody pro použití (více chapterů)
