### 2122ALG2-Kral-Autobazar

# Autobazar

## Zadání

Majitel prestižního autobazaru v Liberci tě požádal, aby si mu pomohl se simulací prodejů v jeho autobazaru. Musíš autobazar vytvořit a pojmenovat. Budeš pracovat se souborem aut, který je právě v autobazaru k dispozici a se souborem prodejců, kteří pro majitele pracují.
Prodej bude vypadat následnovně:
- přijde zákazník a vybere si auto
- vybereš prodejce, který auto prodá
- prodejce a zákazník se domluví na ceně a auto se prodá

Pracovní týden má 5 dní, pracovní doba je od 9:00 do 17:00. Když v jakýkoliv den dojdou auta ze stavu, týden tímto končí.

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

Po ukončení týdne se do terminálu a do souboru vypíše týdenní výsledková listina, na které budou údaje o koupi a prodeji všech aut a o provizích. Na konci bude celkový výsledek hospodaření celého podniku. Program se ukončí. 

## Řešení

## Testování

## Popis fungování externí knihovny

itextpdf
