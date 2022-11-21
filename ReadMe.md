# ReadMe

Programflöde version 0.1:

1. Server startar, printar ut IP-adress och portnummer i consolen. Inväntar connection från en client.
2. Client 1 startar. Startar GUI. Frågar om IP-adress. Öppnar connection med server.
3. Version 0.1 funktion: Server döper 1:a connection till “user 1” osv.
4. Server hämtar fråga 1 / 2 från databas ink vilket alternativ som är korrekt. Skickar till client.
5. Client tar emot fråga och visar upp i GUI tillsammans med svarsalternativ. Väntar på svar från användare.
6. Client visar “rätt svar/fel svar” i GUI. Skickar resultat till server.
7. Server sparar resultat. Skickar fråga 2 / 2 till användare.
8. Återupprepa 5 + 6.
9. Client 1 går in i “väntar på resultat från andra spelare”-läge
10. Server sparar resultaten.
11. Client 2 startar. Startar GUI. Frågar om IP-adress. Öppnar connection med server.
12. Server namnger Client 2.
13. Återupprepa 4-8.
14. Server skickar ut resultat av frågesvaren samt om någon vann eller om det blev oavgjort till båda clienterna. Visar prompt för att starta om.
15. Version 0.1 funktion: Svarsresultat nollställs.
16. Server inväntar att en av clienterna startar om.
17. Återupprepar fr.o.m. 4.
