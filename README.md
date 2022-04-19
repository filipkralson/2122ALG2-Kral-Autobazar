### ALG2-SemestralProject

# Autobazar

Majitel prestižního autobazaru v Liberci tě požádal, aby si mu pomohl se simulací prodejů v jeho autobazaru. Musíš autobazar vytvořit, pojmenovat, zadat mu počet aut, které si dáš do stavu autobazaru (maximálně 15) a počet prodejců (maximálně 5) , kteří budou auta prodávat. Každému prodejci nastavíš jméno, příjmení, věk a úroveň zkušenosti, kterou bude disponovat při prodeji. Na začátku si ze seznamu aut vybereš, která auta si chceš koupit a přemístit na bazar (nejmenší počet aut je 1), vybereš si také ze seznamu prodejců z úřadu práce, kteří hledají místo pro svojí vysněnou práci prodejce aut. (musíš vyplnit všechny pozice pro prodejce, které sis při vytvoření navolil)
Pracovní týden má 5 dní, pracovní doba je od 9:00 do 17:00, přičemž denně lze **prodat právě 3 auta**, tak, aby se den ukončil. Když v jakýkoliv den dojdou auta ze stavu, týden tímto končí.
Aplikace bude sekvenčně zpracovávat tyto příkazy, které uživatel bude zadávat:
- \1. ukázat seznam aut
- \2. ukázat seznam prodejců se zkušenostmi a počtem prodaných aut
- \3. zahájit prodej
Po ukončení týdne se do terminálu a do souboru vypíše týdenní výsledková listina, na které budou údaje o koupi a prodeji všech aut a o provizích. Na konci bude celkový výsledek hospodaření celého podniku. Program se ukončí. 

## Budeš mít za úkol vytvořit třídu **Autobazar.java** ve které budou následující konstruktory a metody:

- vytvoření autobazaru
- nastavení jména společnosti, počet aut, počet zaměstatnců
- vypsání seřazeného seznamu prodejců podle zkušeností a seznamu aut podle značky (sestupně)
- získání počtu aut a zaměstnanců
- vytvoření účtu autobazaru, do kterého se budou zapisovat částky prodaných aut

## Budeš mít za úkol vytvořit třídu **Prodejci.java** ve které budou následující konstruktory a metody:

- zaměstnat prodejce
- nastavit jim jméno, příjmení, věk a zkušenosti
- nastavení počtu prodaných aut (každý prodejce maximálně 3 auta)
- získání jmen, příjmení, věků, zkušeností a prodaných aut
- vytvořit každému zaměstatnci jeho vlastní "pokladničku", do které se budou sčítat provize z prodejů

## Budeš mít za úkol vytvořit třídu **Prodej.java** ve které budou metody pro samotný prodej v autobazaru:

- metoda pro náhodný výběr aut ze stavu autobazaru, která bude simulovat zákazníky, kteří si vybrali jejich vysněné auto
- metoda pro vybrání požadovaného prodejce, který ještě může prodávat a disponuje svými zkušenostmi z prodejů (čím vyšší úroveň, tím míň zákazník z ceny auta slevý a tím více autobazar z daného auta vydělá), úrovně jsou rozřazené následovně:
  - 1. až 3. úroveň -> 15 - 11% z částky auta
  - 4. až 7. úroveň -> 10 - 6% z částky auta
  - 8. až 10. úroveň -> 5 - 0% z čátky auta
- metoda pro vykonání prodeje (náhodné vybrání procenta z intervalu, které se ztrhne z ceny auta v závislosti na úrovni prodejce), částka, za kterou se auto prodá se zapíše do účtu autobazaru a dannému prodejci se zapíše do jeho "pokladničky" provize z prodeje, každý prodejce má **provizi 20%**, po prodeji se prodejci odečte z počtu prodaných aut 1, pokud dojde na prodejce, který už nebude mít možnost prodávat auta, prodávat nebude a nebude tak dispozici
- vypsání nákupu a prodeje aut, provizí prodejců a celkový výdělek autobazaru za týden (následné uložení do *.pdf*) např.
  +   \+ prodej
  +   \- koupě
  +   \- provize
  +   **TOTAL**


Aplikace by měla být co nejvíce odladělá hlavně při zadávání hodnot a vybírání prodejců.
