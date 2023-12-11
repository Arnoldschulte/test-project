#language: nl

@frontend
Functionaliteit: Dummy Frontend testen

  @12952 @obstacleTwins
  Scenario: 1. Als dummy testproject wil ik een simpele succesmelding kunnen valideren
    Gegeven ik ben op de Tricentis obstacle pagina
    Als ik naar obstacle 12952 ga
    En ik op de juiste knop klik
#    Dan zie ik een succesmelding
    Dan zie ik geen succesmelding
#   TODO: Tijdelijk foutief scenario om screenshots in rapportage te testen

  @12952 @obstacleTwins
  Scenario: 2. Als dummy testproject wil ik de afwezigheid van een succesmelding kunnen valideren
    Gegeven ik ben op de Tricentis obstacle pagina
    Als ik naar obstacle 12952 ga
    En ik op de verkeerde knop klik
    Dan zie ik geen succesmelding

#   Bonus
  @72946 @sueNumber
  Scenario: 3. Als tester wil ik een xml bestand kunnen downloaden en hier een waarde uithalen om in te vullen in de FE
    Gegeven ik ben op de Tricentis obstacle pagina
    Als ik naar obstacle 72946 ga
    Dan download ik het XML bestand
    Als ik het volledige nummer van 'Sue' invul
    Dan zie ik een succesmelding

#    Bonus
  @24499 @autocompleteCounting
  Scenario: 4. Als tester wil ik het aantal opties uit een auto-complete veld tellen en versturen via de FE
    Gegeven ik ben op de Tricentis obstacle pagina
    Als ik naar obstacle 24499 ga
    En ik de zoekterm invul
    Dan tel ik het aantal resultaten en verstuur deze
    En zie ik een succesmelding