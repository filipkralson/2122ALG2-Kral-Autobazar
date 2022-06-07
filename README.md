### 2122ALG2-Kral-Autobazar

# Autobazar

## Zadání

Majitel prestižního autobazaru v Liberci tě požádal, aby si mu pomohl se simulací prodejů v jeho autobazaru. Musíš autobazar vytvořit a pojmenovat. Budeš pracovat se souborem aut, který je právě v autobazaru k dispozici a se souborem prodejců, kteří pro majitele pracují. Když dojdou auta ze stavu, týden tímto automaticky končí.

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

Bude možnost vybírat ze souborů .csv, ve kterém budou údaje o autech (značka, model, míle, rok výroby, VIN číslo, stát, země, barva, cena) a o prodejcích(jméno, příjmení, věk, zkušenosti).

Soubor .csv s auty je veřejně dostupný dataset ze stránky: https://www.kaggle.com/datasets/doaaalsenani/usa-cers-dataset

Po ukončení týdne se do terminálu a do souboru vypíše týdenní výsledková listina, na které bude částka za všechna prodaná auta, seznam všech prodejců s jejich atributy a s provizemi. Na konci bude celkový výsledek hospodaření celého podniku. Program se ukončí. 

## Řešení

### Funkční řešení

Návrh menu s metodami
1. Seznam aut
   * vyprintování seřazeného seznamu aut do konzole
3. Seznam prodejců
   * vyprintování seřazeného seznamu prodejců do konzole
5. Prodej
   * cyklený výběr pro prodej
   * pro kladný výběr
      * vyprintování seřazeného seznamu prodejců do konzole
      * zvolení toho prodejce, který bude prodávat
      * náhodný výběr auta, který simuluje pozici zákazníka; na základně zkušeností prodejce se auto prodá za cenu X
      * vyprintování prodaného auta se všemi atributy do konzole, vypsání za jakou částku se auto prodalo a vypsání aktuálního času prodeje
   * pro záporný výběr
      * zpět na začátek hlavního výběru  
7. Konec
   * uložení do .pdf
   * uložení do binárního souboru
   * čtení a vypsání binárního souboru do konzole

### Popis struktury vstupních a výstupních souborů

Vstupní soubory jsou typu .csv a jednotlivé údaje o autech a prodejcích jsou odděleny středníkem (;). U objektů File s cestou k souborům je jasně dán název souborů a to carsUSA.csv a sellers.csv. Soubory musí být uloženy v **src** složce projektu.

Výstupní soubory budou 2. Jeden soubor bude ve formátu .pdf s volitelnou ukládací cestou. Budou v něm následující data v tomto pořadí:

-  výpis seznamu prodejců s jejich provizemi z prodejů
-  výpis, kolik aut se prodalo
-  výpis, jaká byla celková provize za prodaná auta
-  výpis, jaká byla celková provize u autobazaru (celkově prodaná auta - celkové provize prodejců)

Ve stejném čase se uloží i binární soubor .bin s jasně danou ukládací cestou do ./Autobazar/src/data/results.dat, do kterého se uloží stejná data jako do .pdf a bude z něj následně vypisováno přes metodu do konzole. Do binárního soubrou se budou ukládat následující data v tomto pořadí:

-  cyklený zápis atributů u všech prodejců(jméno, příjmení, věk, zkušenosti, peníze)
-  zápis, kolik aut se prodalo
-  zápis, jaká byla celková provize za prodaná auta
-  zápis, jaká byla celková provize u prodejcůTODO

### Class diagram

 ![Autobazar - Class diagram](/stuff/class_diagram.png)

## Testování

| **Číslo testu** | **Typ testu, popis**                                            | **Očekávaný výsledek / Skutečný výsledek** | **Prošel (ano/ne)** |
|-----------------|-----------------------------------------------------------------|--------------------------------------------|---------------------|
| 01              | Nevalidní vstup; špatná cesta k jakémukoliv vstupnímu souboru   | ![ExceptionFileNotFound](/stuff/_screens/ExceptionFileNotFound.png)| Ano  |
| 02              | Nevalidní vstup; nevalidní výpis z binárního souboru            | ![EOFException](/stuff/_screens/EOFException.png)   | Ano                 |
| 03              | Nevalidní vstup; špatně zadaná hodnota v hlavním výběrovém menu | ![Main](/stuff/_screens/Main.png)                   | Ano                 |
| 04              | Nevalidní vstup; špatně zadaná hodnota v podmenu "Prodej"       | ![Prodej](/stuff/_screens/Prodej.png)               | Ano                 |
| 05              | Nevalidní vstup; zadaný prodejce není v seznamu                 | ![Prodejci](/stuff/_screens/Prodejci.png)           | Ano                 |
| 06              | Běžný stav; výběr požadavků v hlavním menu                      | ![Main normal](/stuff/_screens/Main_norm.png)       | Ano                 |
| 07              | Běžný stav; výběr požadavků v podmenu "Prodej"                  | ![Prodej normal](/stuff/_screens/Prodej_norm.png)   | Ano                 |
| 08              | Běžný stav; výběr pro cestu k souboru                           | ![PDF window](/stuff/_screens/Pdf_window.png)       | Ano                 |
| 09              | Běžný stav; .pdf soubor                                         | ![PDF results](/stuff/_screens/Pdf_res.png)         | Ano                 |
| 10              | Běžný stav; print binárního souboru do konzole                  | ![Binary results](/stuff/_screens/Binary_res.png)   | Ano                 |

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
