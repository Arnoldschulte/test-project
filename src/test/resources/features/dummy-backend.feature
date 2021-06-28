#language: nl

Functionaliteit: Dummy Backend testen

  Scenario: 1. Als dummy testproject wil ik een simpele rekensom oplossen
    Gegeven het getal 4
    Als ik daar 5 bij optel
    En ik daar 3 bij aftrek
    Dan heb ik een totaal van 6

  @stubs
  Scenario: 2. Als dummy testproject wil ik een simpele stub maken en deze via REST valideren
    Gegeven een dummy stub is aanwezig met de tekst 'Dit is een test'
    Als ik via REST 'http://localhost:8080/dummy' bevraag met een 'GET'
    Dan krijg ik een status 200
    En krijg ik de tekst 'Dit is een test' terug in de body

    Als ik via REST 'http://localhost:8080/pieter' bevraag met een 'GET'
    Dan krijg ik een status 404