### 2122ALG2-Kral-Autobazar

# Autobazar

## Zadání

Majitel prestižního autobazaru v Liberci tě požádal, aby si mu pomohl se simulací prodejů v jeho autobazaru. Musíš autobazar vytvořit a pojmenovat. Budeš pracovat se souborem aut, který je právě v autobazaru k dispozici a se souborem prodejců, kteří pro majitele pracují. Když v jakýkoliv den dojdou auta ze stavu, týden tímto končí.

Prodej bude vypadat následovně:

UI bude vypadat následovně: 
  1. ukázat seznam aut
      * Zobrazit seřazený nebo originální seznam
  3. ukázat seřazený seznam prodejců se zkušenostmi
  4. zahaj prodej
      * Kterého prodejce si chceš vybrat?
        * <seznam_prodejců> -> vybere prodejce
      * Na základně zkušeností prodejce se auto prodalo za cenu x
      * Připisuji peníze na účet bazaru a účet prodejce, zapisuji čas prodeje a vytvářím záznam o prodeji do výsledkové listiny

Bude možnost vybírat ze souborů .csv, ve kterém budou údaje o autech (značka, model, objem motoru, kW, kilometry, rok výroby, palivo, barva, cena) a o prodejcích(jméno, příjmení, věk, zkušenosti).

Po ukončení týdne se do terminálu a do souboru vypíše týdenní výsledková listina, na které bude částka za všechna prodaná auta, seznam všech prodejců s jejich atributy a provizemi. Na konci bude celkový výsledek hospodaření celého podniku. Program se ukončí. 

## Řešení

### Funkční řešení

Návrh menu s metodami
1. Seznam aut
    * Chceš zobrazit seřazený nebo originální seznam?
    * if seřazený
      * printCarsSorted();
    * else
      * printCars();  
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

## Popis fungování externí knihovny

itextpdf
