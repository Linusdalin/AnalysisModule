package classifiers.classifierTests;

import classifiers.swedishClassifiers.*;
import featureTypes.FeatureTypeTree;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *          Award Criteria tests
 *
 */


public class ObligationClassifierTest extends AnalysisTest {


    @BeforeClass
    public static void preAmble(){

        init();
    }



    @Test
    public void basicTest(){


        try {



              new ClassificationTester("Säljaren garanterar per Tillträdesdagen att denne är ensam ägare till Aktierna, vilka har betalats till fullo, samt att dessa är fria från alla belastningar och fritt överlåtbara i enlighet med detta Avtal.")
                       .withParser(swedishParser)
                       .withHeadline("Table 1")
                       .withProject(mockProject, mockDocument)
                       .withClassifier(new NumberClassifierSV())
                       .withClassifier(new DefinitionUsageClassifierSV())
                       .withClassifier(new ObligationClassifierSV())
                       .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                               .withTag("")
                       )
                   .test();




        } catch (Exception e) {
               e.printStackTrace();
               assertTrue(false);
        }

    }


    /***********************************************************
       *
       *      Testing Classification by examples for tag #OBLIGATION
       *      Document:   "Bilaga 1 - Kravspecifikation webbutbildning,2014-04-28.docx"
       *      Language:   "SV"
       *
       */


      @Test
      public void testLVExamples(){
          try {


              /*

                    // Is this really an obligation?

                 new ClassificationTester("8.3 Uppdrag, har anspråk på sådan nu sagd ytterligare ersättning ska L"+
                                                                          "everantören skriftligen meddela Las Vegas detta. Har Leverantören inte"+
                                                                          ", innan utförande av det ändrade Uppdraget påbörjas, specificerat den "+
                                                                          "merkostnad som ändringen medför samt framställt anspråk om kompensatio"+
                                                                          "n därför, ska ytterligare ersättning för det ändrade Uppdraget inte ut"+
                                                                          "gå.")
                                                               .withParser(swedishParser)
                                                               .withHeadline(" add headline...")
                                                               .withProject(mockProject, mockDocument)
                                                               .withClassifier(new NumberClassifierSV())
                                                               .withClassifier(new DefinitionUsageClassifierSV())
                                                               .withClassifier(new ObligationClassifierSV())
                                                               .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Obligation, 1)
                                                                   .withPattern("...")
                                                                   .withTag("...")
                                                             )
                                                           .test();

                                                         // Is this really an obligation? It is an If A then B will have to do C

                                                                    new ClassificationTester("22.1 Oaktat vad som sagts i punkt 19 ovan (Ansvar) ska Leverantören, v"+
                                                                          "id sådana brister, brott eller fall som anges i det följande i denna p"+
                                                                          "unkt 22.1, oavsett om någon påvisbar skada uppkommit, vid anfordran, t"+
                                                                          "ill Las Vegas utge vite:")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ObligationClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Obligation, 1)
                              .withPattern("...")
                              .withTag("...")
                        )
                      .test();


                             new ClassificationTester("23.2 För det fallet att Uppdrag innefattar att Leverantören behandlar "+
                                                 "personuppgifter för Las Vegass räkning ska ett särskilt sk personuppgi"+
                                                 "ftsbiträdesavtal ingås, enligt Bilaga 5, och biläggas till Avtalet.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Obligation, 1)
                                          .withPattern("...")
                                          .withTag("...")
                                    )
                                  .test();


                             new ClassificationTester("27.7 Om visst Avropsavtal upphör i förtid, och detta inte beror på Lev"+
                                                 "erantörens avtalsbrott, ska Leverantören erhålla ersättning enligt Avr"+
                                                 "opsavtalet. Las Vegass ersättningsskyldighet är dock begränsat till at"+
                                                 "t ersätta Leverantören för det arbete som faktiskt utförts fram till d"+
                                                 "agen för Avropsavtalets upphörande. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Obligation, 1)
                                          .withPattern("...")

                                    )
                                  .test();


                */

                             new ClassificationTester("11.2 Säljaren ska utan oskäligt uppehåll efter sådant överlämnande sk"+
                                                 "riftligen godkänna eller underkänna Uppdrags utförande. Uppdrag ska go"+
                                                 "dkännas i de fall Resultatet motsvarar den mellan Parterna överenskomn"+
                                                 "a specifikationen i aktuellt Avropsavtal och Uppdraget i övrigt har ut"+
                                                 "förts i enlighet med Avtalet. Beslut om underkännande ska motiveras av"+
                                                 " Las Vegas. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska utan oskäligt uppehåll efter sådant överlämnande skriftligen godkänna")

                                    )
                                  .test();


                             new ClassificationTester("För det fall Leverantören får tillgång till Las Vegass IT-miljö i samband med Uppdrags utförande förbinder sig Säljaren att")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("förbinder sig Säljaren att")

                                    )
                                  .test();


                             new ClassificationTester("12.2 Om Säljaren finner eller befarar att Avtalad Leveransdag inte"+
                                                 " kan hållas, ska Säljaren genast underrätta Las Vegas om detta. Le"+
                                                 "verantören ska samtidigt underrätta Las Vegas om anledningen till dröj"+
                                                 "smålet och om när fullgörelse beräknas kunna ske.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren genast underrätta")

                                    )
                                  .test();


                             new ClassificationTester("8.6 Oaktat vad som anges ovan, ska Säljaren alltid utföra mindre ä"+
                                                 "ndringar i ett Uppdrag såsom exempelvis men inte uteslutande stavfel, "+
                                                 "buggar m.m., på Las Vegass anmodan, utan tillkommande kostnad.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren alltid utföra")

                                    )
                                  .test();



                             new ClassificationTester("6.1.2 Säljaren ska följa de instruktioner, riktlinjer och anvisnin"+
                                                 "gar som utfärdas av Las Vegas. Leverantören leder i övrigt arbetet, be"+
                                                 "stämmer arbetsmetoder, svarar för uppföljning samt tillhandahållande a"+
                                                 "v konsulter med lämplig bakgrund och erforderlig kompetens för utföran"+
                                                 "de av Uppdrags respektive delmoment.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska följa")

                                    )
                                  .test();


                             new ClassificationTester("11.3 Säljaren ansvarar för att snarast åtgärda de brister och/elle"+
                                                 "r avvikelser som påtalats av Las Vegas och som föranlett underkännande"+
                                                 ". Leverantören ska därefter överlämna reviderat Resultat till Las Vega"+
                                                 "s för nytt godkännande. Om åtgärdandet av de brister och/eller avvikel"+
                                                 "ser som påtalats medför att Uppdrag inte kan genomföras i tid ska Leve"+
                                                 "rantören utbetala vite enligt punkt 22 nedan.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ansvarar för att snarast åtgärda")

                                    )
                                  .test();


                             new ClassificationTester("4.5 Vid beställning av Uppdrag ska Las Vegas tillställa Leverantören e"+
                                                 "n uppdragsbeskrivning, vilken utförligt beskriver det arbete Las Vegas"+
                                                 " vill ha utfört. Säljaren ska inom tio (10) arbetsdagar svara Las "+
                                                 "Vegas med eventuella förtydliganden och ytterligare specifikationer av"+
                                                 " Uppdraget samt begärd ersättning för Uppdraget.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska inom tio ( 10 ) arbetsdagar svara")

                                    )
                                  .test();




                             new ClassificationTester("10.2 När ett Uppdrag har slutförts eller på annat sätt upphört ska Säljaren på eget initiativ"+
                                                 " eller på Las Vegass begäran omedelbart till Las Vegas överlämna allt Material (inklusive samtliga kopior därav),"+
                                                 " Resultatet och all övrig information som Leverantören innehar och som har direkt anknytning till Uppdraget. Detta gäller oavsett om denna h"+
                                                 "ar framtagits av Leverantören i samband med fullgörandet av Uppdraget eller har överlämnats av Las Vegas till Leverantören.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren på eget initiativ eller på Las Vegass begäran omedelbart till Las Vegas överlämna")

                                    )
                                  .test();



                             new ClassificationTester("15.5 Begäran om prisjustering i enlighet med punkten 15.4 ovan ska ske"+
                                                 " skriftligen minst en (1) månad före ikraftträdandet. Säljaren ska in"+
                                                 "om två (2) veckor från mottagandet av begäran om prisjustering skriftl"+
                                                 "igen bekräfta dess överenstämmelse med avtalade prisjusteringsvillkor."+
                                                 " Det nya timpriset får tillämpas först från och med påföljande kalende"+
                                                 "rmånadsskifte. Vid fastställande av nytt pris ska det nya priset allti"+
                                                 "d avrundas nedåt till närmast hela krontal. Överenskommelse om prisjus"+
                                                 "tering ska för att vara giltig vara skriftlig och behörigen underteckn"+
                                                 "ad av båda Parter. I överenskommelsen ska alltid anges den aktuella pr"+
                                                 "isjusteringen och datum för när prisjusteringen träder i kraft. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska inom två ( 2 ) veckor från mottagandet av begäran om prisjustering skriftligen bekräfta")
                                    )
                                  .test();


                             new ClassificationTester("7.1 För det fall Säljaren får tillgång till Las Vegass IT-miljö i "+
                                                 "samband med Uppdrags utförande förbinder sig Säljaren att:")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("förbinder sig Säljaren att")

                                    )
                                  .test();


                             new ClassificationTester("4.3 För varje Uppdrag ska Säljaren ingå ett särskilt Avropsavtal, där "+
                                                 "Uppdraget i fråga specificeras och övriga för Uppdraget specifika vill"+
                                                 "kor, såsom ersättning, tidplan, avtalstid, uppsägningstid samt huruvid"+
                                                 "a hosting inklusive support ska ske av Leverantören, regleras. Avropsa"+
                                                 "vtal ska utformas i enlighet med Bilaga 4.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren ingå")

                                    )
                                  .test();



                             new ClassificationTester("15.1 För Leverantörens samtliga åtaganden och för allt arbete som Leve"+
                                                 "rantören utför åt Las Vegas enligt mellan Parterna från tid till annan"+
                                                 " ingångna Avropsavtal ska Köparen till Leverantören erlägga ersättni"+
                                                 "ng i enlighet med vad som anges i respektive Avropsavtal (\"Ersätt"+
                                                 "ningen\"). Ersättningen ska baseras på de priser och villkor som f"+
                                                 "ramgår av Bilaga 3. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Köparen till Leverantören erlägga")
                                    )
                                  .test();



                             new ClassificationTester("6.1.6 Leverantören äger inte rätt att utan Las Vegass skriftliga medgi"+
                                                 "vande byta ut sådan person som enligt Avropsavtal ska utföra visst Upp"+
                                                 "drag, eller delar av Uppdrag. Detta gäller dock inte för det fall det "+
                                                 "finns en skälig anledning till varför person inte kan fullgöra Uppdrag"+
                                                 " (exempelvis på grund av långvarig sjukdom eller på grund av att perso"+
                                                 "nen avslutar sin anställning hos Leverantören). För det fall Las Vegas"+
                                                 " önskar få viss person utbytt, och detta inte är uppenbart opåkallat e"+
                                                 "ller annars oskäligt, ska Säljaren efterkomma sådant önskemål.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren efterkomma")

                                    )
                                  .test();



                             new ClassificationTester("15.3 Parterna ska specificera i respektive Avropsavtal vid vilken tidp"+
                                                 "unkt betalning ska erläggas eller vilken betalningsplan som ska gälla "+
                                                 "för respektive Uppdrag.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Parterna ska specificera")

                                    )
                                  .test();



                             new ClassificationTester("6.1.1 Part ska: ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part ska")

                                    )
                                  .test();


                             new ClassificationTester("14.6 Säljaren ansvarar för att ha erforderliga verktyg som kan mät"+
                                                 "a de avtalade servicenivåerna enligt denna punkt 14. Leverantören ska "+
                                                 "inom femton (15) dagar efter utgången av varje kalendermånad tillställ"+
                                                 "a Las Vegas en redovisning av gjorda mätningar av avtalade servicenivå"+
                                                 "er, om inte annat överenskommes.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ansvarar")

                                    )
                                  .test();




                             new ClassificationTester("21.8 Bryter Part mot tystnadsplikten enligt Avtalet ska Parten, oavset"+
                                                 "t om uppsåt eller oaktsamhet kan påvisas, vid anfordran ersätta den an"+
                                                 "dra Parten för all den skada, såväl direkt som indirekt, som den andra"+
                                                 " Parten åsamkats i anledning av avtalsbrottet. Parterna är införstådda"+
                                                 " med och accepterar att Konfidentiell Information ska anses vara föret"+
                                                 "agshemlighet i enlighet med Lag 1990:409 om skydd för företagshem"+
                                                 "ligheter och att röjande därav kan vara straffbart.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Parten , oavsett om uppsåt eller oaktsamhet kan påvisas , vid anfordran ersätta")

                                    )
                                  .test();



                             new ClassificationTester("18.2 Säljaren ska meddela Leverantören om önskad tidpunkt för sådan r"+
                                                 "evision senast fjorton (14) dagar innan planerat revisionsdatum. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska meddela")

                                    )
                                  .test();


                             new ClassificationTester("19.4 Det åligger Part att på egen bekostnad teckna och under h"+
                                                 "ela Avtalets giltighetstid vidmakthålla erforderliga försäkringar avse"+
                                                 "ende Leverantörens egendom och verksamhet samt ansvarsförsäkringar i e"+
                                                 "nlighet med vid var tid gällande myndighetskrav och branschpraxis samt"+
                                                 " hålla försäkringar avseende samtliga skador som avses i denna punkt 1"+
                                                 "9 och under alla händelser fullgod sak-, personskade- samt ansvarsförs"+
                                                 "äkring med betryggande ansvarsbelopp med hänsyn till arten och omfattn"+
                                                 "ingen av Uppdrag och Avtalet.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Det åligger Part")

                                    )
                                  .test();



                             new ClassificationTester("8.4 Om tillämpligt ska Part, i sitt medgivande, beskriva vilka"+
                                                 " konsekvenser den av Las Vegas begärda ändringen får med avseende på U"+
                                                 "ppdraget, på avtalat pris samt i alla andra avseenden rörande Parterna"+
                                                 "s mellanhavanden med anledning av Uppdraget. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Part , i sitt medgivande , beskriva")

                                    )
                                  .test();



                             new ClassificationTester("17.2 Vid eventuell brist i Leverantörens nu lämnade garanti ska Säljaren"+
                                                 " hålla Las Vegas skadeslöst avseende såväl indirekt som direkt s"+
                                                 "kada som Las Vegas kan komma att lida. Därtill ska Leverantören, om in"+
                                                 "trång föreligger eller befaras föreligga, på egen bekostnad och efter "+
                                                 "eget val, antingen tillförsäkra Las Vegas rätt att fortsätta använda R"+
                                                 "esultatet, eller modifiera Resultatet så att intrång inte längre förel"+
                                                 "igger eller kan befaras föreligga. Modifiering får dock endast ske und"+
                                                 "er förutsättning av att det inte negativt påverkar Resultatets funktio"+
                                                 "nalitet eller Las Vegass användning av detta negativt.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren hålla")

                                    )
                                  .test();


                             new ClassificationTester("19.5 Säljaren ska, om Las Vegas så begär, inför Las Vegas dokument"+
                                                 "era sina försäkringar. Las Vegass mottagande av bevis om försäkring ut"+
                                                 "gör inte, om detta inte uttryckligen avtals, en begränsning av Leveran"+
                                                 "törens skyldighet att teckna försäkringar enligt Avtalet.  Om Las Vega"+
                                                 "s förorsakas skada, för vilken Leverantören kan erhålla försäkringsers"+
                                                 "ättning, ska Leverantören efter bästa förmåga söka utfå sådan försäkri"+
                                                 "ngsersättning och utan dröjsmål utbetala densamma till Las Vegas.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska , om Las Vegas så begär , inför Las Vegas dokumentera")

                                    )
                                  .test();


                             new ClassificationTester("13.2 Köparen ska till Leverantören reklamera fel eller brist senast "+
                                                 "trettio (30) dagar efter det att felet eller bristen upptäckts av Las "+
                                                 "Vegas. Las Vegas ska, där så är tillämpligt, vara Leverantören behjälp"+
                                                 "lig vid felrättning genom att visa hur felet eller bristen har yttrat "+
                                                 "sig.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Köparen ska till Leverantören reklamera")

                                    )
                                  .test();



                             new ClassificationTester("12.3 Vid försening av Uppdrag (dvs att Faktisk Leveransdag inträffar s"+
                                                 "enare än Avtalad Leveransdag), som beror på omständighet för vilken La"+
                                                 "s Vegas inte svarar för, samt Las Vegas ej heller har godkänt att tidp"+
                                                 "unkten för Avtalad Leveransdag överskrids utan påföljd för Leverantöre"+
                                                 "n, ska Säljaren till Las Vegas utbetala ett vite i enlighet med pu"+
                                                 "nkt 22 nedan. För det fall att Las Vegass skada överstiger vad som kan"+
                                                 " utgå i vite enligt punkt 22 nedan, har Las Vegas rätt till ersättning"+
                                                 " även för överskjutande belopp. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren till Las Vegas utbetala")

                                    )
                                  .test();


                             new ClassificationTester("14.5 Vid eventuella fel eller brister i den aktuella interaktiva utbil"+
                                                 "dningen, ska Säljaren åtgärda bristen inom åtta (8) timmar från de"+
                                                 "t att felet/bristen rapporterades av Las Vegas eller upptäcktes av Lev"+
                                                 "erantören. Åtgärdstiden räknas bara mellan kl. 06-24. Vid brist i avta"+
                                                 "lad åtgärdstid har Las Vegas rätt till vite om tio (10) procent av mån"+
                                                 "adsavgiften för hostingen av den aktuella utbildningen per påbörjad ti"+
                                                 "mme utöver åtta (8) timmar, dock med ett sammanlagt högsta vite om hun"+
                                                 "dra (100) procent av månadsavgiften för hostingen av den aktuella utbi"+
                                                 "ldningen.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("ska Säljaren åtgärda")

                                    )
                                  .test();


                             new ClassificationTester("6.1.3 Part ska tillhandahålla personella resurser, material, v"+
                                                 "erktyg och annan utrustning som fordras för Uppdrags genomförande. Upp"+
                                                 "drag ska, om annat ej i förväg överenskommits och med undantag av såda"+
                                                 "na delmoment som uppenbarligen bör ske hos Las Vegas, utföras i Levera"+
                                                 "ntörens lokaler. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part ska tillhandahålla")

                                    )
                                  .test();



                             new ClassificationTester("21.1 Part ska under Avtalsperioden och även därefter iakttaga tystnads"+
                                                 "plikt i fråga om all Konfidentiell Information vilken Part har fått de"+
                                                 "l av på grund av och/eller i anledning av Avtalet. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part ska under Avtalsperioden och även därefter iakttaga")

                                    )
                                  .test();



                             new ClassificationTester("28.5 Part äger, med undantag av det sagda, föra fullgörelsetalan vid a"+
                                                 "llmän domstol, med Stockholms tingsrätt som första instans, om klar oc"+
                                                 "h förfallen fordran enligt Avtalet.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new RightsClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RIGHT, 1)
                                          .withPattern("Part äger , med undantag av det sagda , föra")

                                    )
                                  .test();


                             new ClassificationTester("15.9 Las Vegas tar emot e-fakturor via Basware BT (Business Transactio"+
                                                 "ns) tjänst. Las Vegas kan ta emot fakturor i olika standardiserade xml"+
                                                 "-format såsom e2b (3.1-4.3), EHF, Svefaktura, FInvoice 1.2, TEAPPS 2.6"+
                                                 ", NESUBL med flera, vanligast är Svefaktura. Säljaren ska tillsamm"+
                                                 "ans med Las Vegas arbeta för att i ett senare skede införa en EDI-lösn"+
                                                 "ing för fakturering och därmed förenkla hanteringen.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren ska tillsammans med Las Vegas arbeta")

                                    )
                                  .test();



                             new ClassificationTester("Part åtar sig att hosta och tillhandahålla support för den för Uppdraget aktuella utbildningen.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part åtar sig")

                                    )
                                  .test();


                             new ClassificationTester("Part är skyldig att under en tid om sex (6) månader efter"+
                                                 " att Uppdrag har slutförts samt utan särskild ersättning härför, åtgär"+
                                                 "da fel och/eller brister som är hänförliga till Leverantörens fullgöra"+
                                                 "nde av Uppdraget.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         // .withPattern("Part är skyldig att")        //TODO: Error in extraction here

                                    )
                                  .test();


                             new ClassificationTester("19.1 Parterna ska i alla hänseenden fullgöra sina åtaganden i överenss"+
                                                 "tämmelse med Avtalet samt ska utge ersättning till motparten avseende "+
                                                 "samtliga skador för vilka Parten ansvarar eller som orsakas av Parten,"+
                                                 " dess personal eller annan som har anlitas av Parten, detta oaktat om "+
                                                 "motparten godkänt sådan underleverantör eller inte. Parterna ansvarar "+
                                                 "dock inte, om annat ej uttryckligen har angivits, för följdskador elle"+
                                                 "r indirekta skador, såsom utebliven vinst, kostnader som blivit onytti"+
                                                 "ga och förväntad besparing som uteblivit. I det fall Leverantören hant"+
                                                 "erar Las Vegass data till följd av Uppdraget (t ex i de fall Leverantö"+
                                                 "ren ansvarar för hosting av en interaktiv utbildning) ska Leverantören"+
                                                 "s ansvar för direkt skada omfatta förlust av data, oberoende av om såd"+
                                                 "an skada och förlust i andra sammanhang skulle vara att betrakta som d"+
                                                 "irekt skada och förlust. ")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Parterna ska i alla hänseenden fullgöra")

                                    )
                                  .test();



                             new ClassificationTester("Part åtar sig vidare att ersätta Las Vegas fullt ut för h"+
                                                 "avda ombudskostnader och de ersättningar och skadestånd som Las Vegas "+
                                                 "genom av Leverantören godkända förlikningar, eller genom lagakraftvunn"+
                                                 "en dom, kan bli skyldig att utge för sådant intrång eller annat krav s"+
                                                 "om anges i denna punkt 17.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part åtar sig")

                                    )
                                  .test();



                             new ClassificationTester("21.7 För det fallet att Part får anledning att misstänka att Konfident"+
                                                 "iell Information har lämnats ut till någon tredje part, åligger det Pa"+
                                                 "rten att, förutom att omedelbart meddela den andra Parten därom, göra "+
                                                 "sitt bästa för att begränsa skadan därav.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("åligger det Parten")

                                    )
                                  .test();


                             new ClassificationTester("8.1 I den mån det är förenligt med LOU äger Las Vegas rätt att, efter "+
                                                 "skriftligt meddelande och med rimlig framförhållning, under hand ändra"+
                                                 " ett Uppdrag, dock ska vid väsentlig förändring Leverantörens medgivan"+
                                                 "de först inhämtas. Part ska inte oskäligen och/eller utan att "+
                                                 "ange sakliga skäl underlåta att lämna sådant medgivande.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part ska inte oskäligen och / eller utan att ange sakliga skäl underlåta att lämna")

                                    )
                                  .test();

                             new ClassificationTester("4.4 I varje Avropsavtal ska specificeras vilka uppgifter som ingår i r"+
                                                 "espektive Uppdrag. Säljaren har ett helhetsansvar för respektive U"+
                                                 "ppdrag och har således ansvar för samtliga moment och delar av Uppdrag"+
                                                 "et, förutom vad som uttryckligen anges som Las Vegass ansvar enligt if"+
                                                 "rågavarande Avropsavtal.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new DefinitionUsageClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Säljaren har ett helhetsansvar")

                                    )
                                  .test();


                             new ClassificationTester("21.2 Part förbinder sig att inte utan den andra Partens skriftliga sam"+
                                                 "tycke för tredje man avslöja Konfidentiell Information och att inte he"+
                                                 "ller använda den i annat syfte än att fullgöra Avtalet.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                              .withPattern("Part förbinder sig")

                                      )
                                  .test();



                             new ClassificationTester("9.1 Part har rätt att, när som helst,"+
                                                 " helt eller delvis, avbeställa ej utförda delar av Uppdrag. Sker sådan"+
                                                 " avbeställning ska Part utge ersättning till Leverantören för red"+
                                                 "an utfört arbete. Sen andra Parten har även rätt till ersättning i skälig "+
                                                 "omfattning för direkta kostnader som Leverantören haft med anledning a"+
                                                 "v Las Vegass avbeställning, om sådana kostnader inte skäligen borde ha"+
                                                 " kunnat undvikas. Den andra Parten har dock ingen rätt till ersättning för"+
                                                 " sådana direkta kostnader om Las Vegass avbeställning beror på att Lev"+
                                                 "erantören vid sitt fullgörande av Uppdrag har gjort sig skyldigt till "+
                                                 "fel eller försummelse.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                              .withPattern("ska Part utge"))
                                  .test();


                             new ClassificationTester("21.3 Part förbinder sig att inte till anställda, styrelseledamöter, un"+
                                                 "derleverantörer eller konsulter som inte för Avtalets fullgörande är b"+
                                                 "eroende därav utlämna eller avslöja, Konfidentiell Information. Part f"+
                                                 "örbinder sig att tillse att anställda, styrelseledamöter, underleveran"+
                                                 "törer eller konsulter som anlitats av Part inte i strid med Avtalet an"+
                                                 "vänder, eller till utomstående avslöjar, Konfidentiell Information. De"+
                                                 "t åligger därvid Part att tillse att personer som kan antas komma i ko"+
                                                 "ntakt med Konfidentiell Information är bundna att hemlighålla denna i "+
                                                 "samma utsträckning som följer av detta sekretessåtagande.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new RestrictionClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                          .withPattern("Part förbinder sig att inte")

                                    )
                                  .test();



                             new ClassificationTester("17.3 Part åtar sig att på egen bekostnad biträda Las Vegas om "+
                                                 "krav riktas eller talan förs mot Las Vegas (eller parter som hänför si"+
                                                 "na rättigheter från Las Vegas) på grund av intrång i upphovsrätt eller"+
                                                 " annan rätt hänförlig till innehav eller nyttjande av Resultatet. Om P"+
                                                 "arterna är ense därom ska Leverantören ha rätt att överta talan, om kr"+
                                                 "av riktas eller talan förs enligt ovan mot Las Vegas eller mot parter "+
                                                 "som hänför sina rättigheter från Las Vegas.")
                                      .withParser(swedishParser)
                                      .withHeadline(" add headline...")
                                      .withProject(mockProject, mockDocument)
                                      .withClassifier(new NumberClassifierSV())
                                      .withClassifier(new PartyUsageClassifierSV())
                                      .withClassifier(new ObligationClassifierSV())
                                      .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                          .withPattern("Part åtar sig")

                                    )
                                  .test();



                 new ClassificationTester("Köparen ska erbjuda ett konkurrenskraftigt utbud av spännande och un"+
                                     "derhållande spel på ett ansvarsfullt och säkert sätt.")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ObligationClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                              .withPattern("Köparen ska erbjuda")
                        )
                      .test();


                 new ClassificationTester("Vid eventuella fel eller brister i den aktuella interaktiva utbildning"+
                                     "en, ska Köparen åtgärda bristen inom åtta (8) timmar från det att"+
                                     " felet/bristen rapporterades av Las Vegas eller upptäcktes av Leverant"+
                                     "ören. Åtgärdstiden räknas bara mellan kl. 06-24. ")
                          .withParser(swedishParser)
                          .withHeadline(" add headline...")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierSV())
                          .withClassifier(new DefinitionUsageClassifierSV())
                          .withClassifier(new ObligationClassifierSV())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                              .withPattern("ska Köparen åtgärda")
                        )
                      .test();

              new ClassificationTester("Köparen skall utan dröjsmål informera Personuppgiftsbiträdet om"+
                                                " förändringar i behandlingen vilken påverkar Personuppgiftsbiträdets s"+
                                                "kyldigheter. Las Vegas skall tillika informera Personuppgiftsbiträdet "+
                                                "om tredje parts, däribland Datainspektionen och registrerades åtgärder"+
                                                " med anledning av behandlingen.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                             .withPattern("Köparen skall utan dröjsmål informera")

                                     )
                                 .test();
             
             

                            new ClassificationTester("4.1  Köparen förbinder sig att inte till tredje man läm"+
                                                "na ut eller eljest röja information om behandling av personuppgifter s"+
                                                "om omfattas av detta avtal eller annan information som Personuppgiftsb"+
                                                "iträdet erhållit till följd av detta avtal. Detta åtagande gäller inte"+
                                                " information som Personuppgiftsbiträdet föreläggs utge till myndighet."+
                                                " Sekretessåtagandet gäller även efter att detta avtal i övrigt upphört"+
                                                " att gälla.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                             .withPattern("Köparen förbinder sig att inte")
                                   )
                                 .test();
             
             
                            new ClassificationTester("2.10  Köparen skall assistera Las Vegas med att ta fram"+
                                                " information som begärts av Datainspektionen eller registrerad.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         .withPattern("Köparen skall assistera")
                                   )
                                 .test();
             
             
                            new ClassificationTester("2.9  Köparen skall när detta avtal upphör att gälla, el"+
                                                "ler vid annan tidpunkt om Las Vegas så anvisar, överlämna personuppgif"+
                                                "ter på av Las Vegas angivet medium och se till att det inte finns någr"+
                                                "a personuppgifter kvar hos Personuppgiftsbiträdet.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         .withPattern("Köparen skall när detta avtal upphör att gälla , eller vid annan tidpunkt om Las Vegas så anvisar , överlämna")

                                   )
                                 .test();
             
             
                            new ClassificationTester("2.4 Köparen får endast använda sig av underbiträden för"+
                                                " behandling av Las Vegass personuppgifter, i den mån Las Vegas har skr"+
                                                "iftligen godkänt detta i förväg. Personuppgiftsbiträdet ska hålla Las "+
                                                "Vegas löpande informerad om användning av underbiträden. Om godkännand"+
                                                "e till användning av underbiträde ges, ger Las Vegas Personuppgiftsbit"+
                                                "rädet mandat att ingå underbiträdesavtal med underbiträden som ska inn"+
                                                "ehålla villkor motsvarande de villkor som framgår av detta Avtal (inkl"+
                                                "usive Las Vegass revisionsrätt enligt Avtalet).   ")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new RestrictionClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.RESTRICTION, 1)
                                         .withPattern("Köparen får endast")
                                   )
                                 .test();
             
             
                            new ClassificationTester("Köparen skall tillse att behandlingen sker i enlighet med PUL. "+
                                                "Las Vegas ansvarar bland annat för att informera de Registrerade om be"+
                                                "handlingen, för att i nödvändiga fall inhämta samtycke från de registr"+
                                                "erade och för att i tillämpliga fall anmäla behandlingen till Datainsp"+
                                                "ektionen.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         .withPattern("Köparen skall tillse")

                                   )
                                 .test();
             
             
                            new ClassificationTester("Säljaren skall vidta skäliga tekniska och organisat"+
                                                "oriska åtgärder för att skydda personuppgifter mot obehörig åtkomst, f"+
                                                "örstörelse och ändring. Personuppgiftsbiträdet skall därvid särskilt i"+
                                                "aktta Datainspektionens instruktioner i dess allmänna råd \"Säkerh"+
                                                "et för personuppgifter\" eller andra föreskrifter som Datainspekti"+
                                                "onen ger ut.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         .withPattern("Säljaren skall vidta")
                                   )
                                 .test();
             
             
                            new ClassificationTester("Säljaren har rätt att på egen bekostnad själv eller genom tredje man kontrollera att"+
                                                " Personuppgiftsbiträdet följer detta avtal. Köparen skall därvid lämna Las Vegas den assistans som behövs.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         .withPattern("Köparen skall därvid lämna")
                                   )
                                 .test();
             
             
                            new ClassificationTester("2.2  Säljaren och den eller de personer som arbetar unde"+
                                                "r dennes ledning får endast behandla personuppgifter i enlighet med de"+
                                                " instruktioner som lämnats av Las Vegas och för det ändamål som Person"+
                                                "uppgiftsbiträdet anlitats för. För det fall att Personuppgiftsbiträdet"+
                                                " saknar instruktioner som Personuppgiftsbiträdet bedömer är nödvändiga"+
                                                " för att genomföra uppdraget som Personuppgiftsbiträdet erhållit från "+
                                                "Las Vegas skall Köparen, utan dröjsmål, informera Las V"+
                                                "egas om sin inställning och invänta de instruktioner som Las Vegas bed"+
                                                "ömer erfordras.")
                                     .withParser(englishParser)
                                     .withHeadline(" add headline...")
                                     .withProject(mockProject, mockDocument)
                                     .withClassifier(new NumberClassifierSV())
                                     .withClassifier(new DefinitionUsageClassifierSV())
                                     .withClassifier(new ObligationClassifierSV())
                                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                                         .withPattern("skall Köparen , utan dröjsmål , informera")              //TODO: extracting comme

                                   )
                                 .test();
              

             } catch (Exception e) {
                  e.printStackTrace(System.out);
                  assertTrue(false);
             }
          }
    @Test
    public void testVague(){
        try {


            new ClassificationTester("Säljaren ska ha en hög tillgänglighet och snabb svarstid.")
                     .withParser(englishParser)
                     .withHeadline(" add headline...")
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new ObligationClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.OBLIGATION, 1)
                             .withPattern("Säljaren ska ha en hög")
                             .withTag("Vague")


                     )
                 .test();






} catch (Exception e) {
     e.printStackTrace(System.out);
     assertTrue(false);
}
}
}
