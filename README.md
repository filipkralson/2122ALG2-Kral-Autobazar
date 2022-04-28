### 2122ALG2-Kral-Autobazar

# Autobazar

Majitel prestižního autobazaru v Liberci tě požádal, aby si mu pomohl se simulací prodejů v jeho autobazaru. Musíš autobazar vytvořit, pojmenovat, zadat mu počet aut, které si dáš do stavu autobazaru a počet prodejců, kteří budou auta prodávat. Každému prodejci nastavíš jméno, příjmení, věk a úroveň zkušenosti, kterou bude disponovat při prodeji. Na začátku si ze seznamu aut vybereš, která auta si chceš koupit a přemístit na bazar (nejmenší počet aut je 1). Musíš vyplnit všechny pozice pro prodejce a auta, které sis při vytvoření navolil!
Prodej bude vypadat následnovně:
- přijde zákazník a vybere si auto
- vybereš prodejce, který auto prodá
- prodejce a zákazník se domluví na ceně a auto se prodá

Pracovní týden má 5 dní, pracovní doba je od 9:00 do 17:00, přičemž denně lze **prodat právě 3 auta**, tak, aby se den ukončil. Když v jakýkoliv den dojdou auta ze stavu, týden tímto končí.

UI bude vypadat následovně: 
  1. ukázat seznam aut
  2. ukázat seznam prodejců se zkušenostmi a počtem prodaných aut
  3. zahaj prodej
      * Přišel zákazník, vybral si auto xy
        * <vypíše se objekt auta s atributy>
      * Kterého prodejce si chceš vybrat?
        * <seznam_prodejců> -> vybere prodejce
      * Na základně zkušeností prodejce se auto prodalo za cenu x
      * Připisuji peníze na účet bazaru a účet prodejce, zapisuji čas prodeje a vytvářím záznam o prodeji do výsledkové listiny

Po ukončení týdne se do terminálu a do souboru vypíše týdenní výsledková listina, na které budou údaje o koupi a prodeji všech aut a o provizích. Na konci bude celkový výsledek hospodaření celého podniku. Program se ukončí. 

