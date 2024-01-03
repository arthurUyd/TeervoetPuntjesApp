# TeervoetPuntjesApp
opdracht android 2023

Dit is een applicatie gemaakt met een zelfgemaakte Node.js backend voor de bijbehorende Android-applicatie.

De applicatie heeft als doel de vaardigheden die scoutsleden kunnen behalen gedurende hun scoutscarrière online bij te houden.

Een lid moet zich aanmelden bij het startscherm en kan daarna zijn behaalde vaardigheden bekijken. Indien er nieuwe vaardigheden zijn behaald, kan hij deze aanduiden en moet hij een leider vragen om deze te ondertekenen. Ik heb nog geen gebruik kunnen maken van Room en heb geen tijd gehad om tests te schrijven, maar als u kijkt naar de "room" branch, kunt u zien dat ik eraan begonnen was. Alle leden zullen bij het eerste keer opstarten van de applicatie nog geen behaalde vaardigheden hebben.

Als u de app voor de eerste keer zou willen uitvoeren, moet u het volgende doen:
1. In de ApplicatieBackend moet u regel 84-104 van het index.js bestand van de datalaag uncommenten (met uitzondering van regel 95).
2. In het .env-bestand moet u de DATABASE_USERNAME en DATABASE_PASSWORD aanpassen naar uw eigen waarden voor de localhost. De NODE_ENV mag op development blijven.
3. In de Android-app moet u ook de gradle-bestanden synchroniseren.

Inloggegevens:
- Lid:
  - E-mail: lid1@gmail.com
  - Wachtwoord: lid
- Leider:
  - E-mail: leider@gmail.com
  - Wachtwoord: leider
