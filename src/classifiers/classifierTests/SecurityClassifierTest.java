package classifiers.classifierTests;

import classifiers.englishClassifiers.ArbitrationClassifierEN;
import classifiers.englishClassifiers.DefinitionUsageClassifierEN;
import classifiers.englishClassifiers.NumberClassifierEN;
import classifiers.englishClassifiers.SecurityClassifierEN;
import classifiers.swedishClassifiers.*;
import featureTypes.FeatureTypeTree;
import language.English;
import language.Swedish;
import openNLP.NLParser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*************************************************************************''
 *
 *
 *
 */


public class SecurityClassifierTest extends AnalysisTest {

    private static NLParser englishParser;
    private static NLParser swedishParser;


    @BeforeClass
    public static void preAmble(){

        englishParser = new NLParser(new English(), "TextAnalysis/models");
        swedishParser = new NLParser(new Swedish(), "TextAnalysis/models");
    }


    /***********************************************************
     *
     *          Not implemented classifier, so the tests should not work
     *
     */

    @Test
    public void basicTest(){



        try {



            new ClassificationTester(
                        "eHälsomyndighetenLeverantören ska bedriva ett heltäckande och processorienterat " +
                        "informationssäkerhetsarbete, baserat på ISO 27000 eller motsvarande standard, med ett tydligt " +
                        "gränssnitt för samverkan in mot EHM för att säkerställandet av uppfyllandet av Servicenivåer och säkerhetskrav.")
                     .withParser(swedishParser)
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new SecurityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                             .withTag("information")
                     )
                 .test();

            new ClassificationTester(
                        "Ramverket, som är baserat på ISO 27000 standard, gäller samtliga medarbetare inom Schibsted Sverige AB liksom alla berörda underleverantörer. IT Policyn godkänns av Schibsted Sveriges koncernledning och förvaltas av CIO.")
                     .withParser(swedishParser)
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new SecurityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                             .withTag("information")
                     )
                 .test();


            new ClassificationTester(
                        "Leverantören ska övervaka och säkerhetsgranska applikationsservrarna för optimal prestanda och " +
                                "rapportera avvikelser till EHM, samt ge förslag på proaktiva åtgärder och nödvändiga konfigurationsförändringar.")
                     .withParser(swedishParser)
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new SecurityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                             .withTag("process")
                     )
                 .test();

            new ClassificationTester(
                        "Leverantören ska genomföra återkommande intern uppföljning av informationssäkerheten samt bistå " +
                                "vid uppföljningar initierade av EHM. Sådana uppföljningar kan genomföras av EHM eller av " +
                                "denne utsedd tredje part. Uppföljningarna kan variera i omfattning och utförande, allt från " +
                                "traditionell IT- revision till tekniska granskningar och intrångstester.")
                     .withParser(swedishParser)
                     .withProject(mockProject, mockDocument)
                     .withClassifier(new NumberClassifierSV())
                     .withClassifier(new DefinitionUsageClassifierSV())
                     .withClassifier(new SecurityClassifierSV())
                     .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                             .withTag("information")
                     )
                 .test();




        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

    }


        /***********************************************************
         *
         *      Testing Classification by examples for tag #SECURITY
         *      Document:   " Frågor Och Svar"
         *      Language:   "SV"
         *
         */


        @Test
        public void testFrågorOchSvar(){

            try {
                   new ClassificationTester("34. Många kunder väljer att ha antivirus"+
                                                 "skydd och antispam och i vissa fall även"+
                                                 " skydd mot DDoS attacker. Hur ser EHM på"+
                                                 " det behovet för myndighetens IT-miljö o"+
                                                 "ch om det skall ingå, var skall det pris"+
                                                 "sättas i prisbilagan?")
                            .withParser(swedishParser)
                            .withHeadline("34. Många kunder väljer att ha antivirusskydd och antispam och i vissa fall även skydd mot DDoS attacker. Hur ser EHM på det behovet för myndighetens IT-miljö och om det skall ingå, var skall det prissättas i prisbilagan?")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("Svar: Ja, det ingår i Tjänsten. Kravet <"+
                                                 "/b>på Tjänstens tillgänglighet"+
                                                 " och säkerhet för-<"+
                                                 "/b> utsätter skyddsåtgärder mot illasinnad"+
                                                 "e attacker så som DDoS, SPAM och <"+
                                                 "b>Vi-")
                            .withParser(swedishParser)
                            .withHeadline(" 2014-08-11")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }





        /***********************************************************
         *
         *      Testing Classification by examples for tag #Security
         *      Document:   " Avtalsbilaga 7"
         *      Language:   "SV"
         *
         */


        @Test
        public void testAvtalsbilaga7Examples(){
            try {
                   new ClassificationTester("a iso 27001, och")
                            .withParser(swedishParser)
                            .withHeadline("a) SS-ISO/IEC 27001, och")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("e) tillser att godkänna metoder för risk"+
                                                 "bedömning, informationsklassning och godkännande,")
                            .withParser(swedishParser)
                            .withHeadline("e) tillser att godkänna metoder för riskbedömning, informationsklassning och godkännande,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information"))

                        .test();


                   new ClassificationTester("11.4 Systemsäkerhetsanalys enligt punkte"+
                                                 "n 11.2 ovan ska beskriva (i) de säkerhet"+
                                                 "skrav som Leve- rantören uppställt med a"+
                                                 "vseende på IT-systemet och (ii) hur Leve"+
                                                 "rantören tillgodosett dessa säkerhetskra"+
                                                 "v.")
                            .withParser(swedishParser)
                            .withHeadline("11.4 Systemsäkerhetsanalys enligt punkten 11.2 ovan ska beskriva (i) de säkerhetskrav som Leve- rantören uppställt med avseende på IT-systemet och (ii) hur Leverantören tillgodosett dessa säkerhetskrav.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("system")
                          )
                        .test();


                   new ClassificationTester("e) anta rutiner för återställning till n"+
                                                 "ormal drift efter det att en incident ha"+
                                                 "r åtgärdats.")
                            .withParser(swedishParser)
                            .withHeadline("e) anta rutiner för återställning till normal drift efter det att en incident har åtgärdats.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("incident")
                          )
                        .test();


                   new ClassificationTester("d) IT-utrustning förses med lämpligt sky"+
                                                 "dd i form av brandvägg, virusskydd m.m.,"+
                                                 "")
                            .withParser(swedishParser)
                            .withHeadline("d) IT-utrustning förses med lämpligt skydd i form av brandvägg, virusskydd m.m.,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("system")
                          )
                        .test();


                   new ClassificationTester("i) att all IT-utrustning är försedd med "+
                                                 "skärmsläckare, eller liknande säkerhetså"+
                                                 "tgärd, som akti- veras när användaren in"+
                                                 "te använt IT-utrustningen under en viss "+
                                                 "tidsperiod och som endast kan låsas upp "+
                                                 "med hjälp av lösenord, och")
                            .withParser(swedishParser)
                            .withHeadline("i) att all IT-utrustning är försedd med skärmsläckare, eller liknande säkerhetsåtgärd, som akti- veras när användaren inte använt IT-utrustningen under en viss tidsperiod och som endast kan låsas upp med hjälp av lösenord, och")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("aktivitet")
                          )
                        .test();


                   new ClassificationTester("e) all reparation och service av IT-utru"+
                                                 "stningen utförs på ett sådant sätt att p"+
                                                 "ersonuppgifter inte blir tillgängliga fö"+
                                                 "r obehöriga,")
                            .withParser(swedishParser)
                            .withHeadline("e) all reparation och service av IT-utrustningen utförs på ett sådant sätt att personuppgifter inte blir tillgängliga för obehöriga,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("a) Leverantörens övergripande säkerhetsstrategi,")
                            .withParser(swedishParser)
                            .withHeadline("a) Leverantörens övergripande säkerhetsstrategi,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("16. Hantering av säkerhetsincidenter")
                            .withParser(swedishParser)
                            .withHeadline("16. Hantering av säkerhetsincidenter")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("incident")
                          )
                        .test();


                   new ClassificationTester("11.2 Leverantören ska göra en analys av "+
                                                 "hotbilden mot Leverantörens behandling a"+
                                                 "v Personuppgifter.")
                            .withParser(swedishParser)
                            .withHeadline("11.2 Leverantören ska göra en analys av hotbilden mot Leverantörens behandling av Personupp- gifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("15.4.3 Grund: Datainspektionen Dnr 1739-2008 (CSN).")
                            .withParser(swedishParser)
                            .withHeadline("15.4.3 Grund: Datainspektionen Dnr 1739-2008 (CSN).")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("13.3 Informationssäkerhetspolicyn ska fi"+
                                                 "nnas allmänt tillgänglig inom Leverantör"+
                                                 "ens organisation (t.ex. genom publicerin"+
                                                 "g på Leverantörens intranät eller på ann"+
                                                 "at lämpligt sätt). All Perso- nal ska få"+
                                                 " del av eller informeras om innehållet i"+
                                                 " informationssäkerhetspolicyn.")
                            .withParser(swedishParser)
                            .withHeadline("13.3 Informationssäkerhetspolicyn ska finnas allmänt tillgänglig inom Leverantörens organisation (t.ex. genom publicering på Leverantörens intranät eller på annat lämpligt sätt). All Perso- nal ska få del av eller informeras om innehållet i informationssäkerhetspolicyn.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("16.3 Leverantören ska utreda misstanke o"+
                                                 "m obehörig tillgång till omfattade perso"+
                                                 "nuppgifter.")
                            .withParser(swedishParser)
                            .withHeadline("16.3 Leverantören ska utreda misstanke om obehörig tillgång till omfattade personuppgifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("intrång")
                          )
                        .test();


                   new ClassificationTester("b) SS-ISO/IEC 27002, eller senare public"+
                                                 "erad version av dessa standarder (tillsa"+
                                                 "mmans \"Regelverket\")")
                            .withParser(swedishParser)
                            .withHeadline("b) SS-ISO/IEC 27002, eller senare publicerad version av dessa standarder (tillsammans \"Regelverket\")")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("b) tillämpa tillträdeskontroll (så att e"+
                                                 "nbart behöriga personer ges tillträde ti"+
                                                 "ll lokalerna eller IT- utrustningen och "+
                                                 "endast vid behov),")
                            .withParser(swedishParser)
                            .withHeadline("b) tillämpa tillträdeskontroll (så att enbart behöriga personer ges tillträde till lokalerna eller IT- utrustningen och endast vid behov),")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("(ii) behörighetsåterkallande av tillgång"+
                                                 " till IT-utrustning och omfattade person"+
                                                 "uppgifter.")
                            .withParser(swedishParser)
                            .withHeadline("(ii) behörighetsåterkallande av tillgång till IT-utrustning och omfattade personuppgifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("h) Instruktioner för behörighetstilldeln"+
                                                 "ing och behörighetsåterkallelse (se punk"+
                                                 "ten 15.2.2) ")
                            .withParser(swedishParser)
                            .withHeadline("h) Instruktioner för behörighetstilldelning och behörighetsåterkallelse (se punkten 15.2.2) ")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("15.4 Sekretessmarkering")
                            .withParser(swedishParser)
                            .withHeadline("15.4 Sekretessmarkering")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("13.6 Vid eventuella motstridigheter mell"+
                                                 "an denna instruktion och informationssäk"+
                                                 "erhetspolicyn eller säkerhetsinstruktion"+
                                                 "erna ska denna instruktion äga företräde"+
                                                 ". Det är Leverantörens an- svar att best"+
                                                 "ämmelserna om behandling av personuppgif"+
                                                 "ter i informationssäkerhetspolicyn och s"+
                                                 "äkerhetsinstruktionerna efterföljer vad "+
                                                 "som anges i denna instruktion.")
                            .withParser(swedishParser)
                            .withHeadline("13.6 Vid eventuella motstridigheter mellan denna instruktion och informationssäkerhetspolicyn eller säkerhetsinstruktionerna ska denna instruktion äga företräde. Det är Leverantörens an- svar att bestämmelserna om behandling av personuppgifter i informationssäkerhetspolicyn och säkerhetsinstruktionerna efterföljer vad som anges i denna instruktion.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("3) regelbunden omprövning av åtkomst til"+
                                                 "l IT-utrustning,")
                            .withParser(swedishParser)
                            .withHeadline("3) regelbunden omprövning av åtkomst till IT-utrustning,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("15.2 Fysisk och miljörelaterad säkerhet")
                            .withParser(swedishParser)
                            .withHeadline("15.2 Fysisk och miljörelaterad säkerhet")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("15.3.4 Till förtydligande av punkten 15."+
                                                 "3.2 ovan är det inte tillräckligt att en"+
                                                 "bart autentisera användaren genom anvä"+
                                                 "ndarnamn och lösenord. Användarens ident"+
                                                 "itet ska säkerställas med en teknisk fun"+
                                                 "ktion som ger en stark autentisering.")
                            .withParser(swedishParser)
                            .withHeadline("15.3.4 Till förtydligande av punkten 15.3.2 ovan är det inte tillräckligt att enbart autentisera an- vändaren genom användarnamn och lösenord. Användarens identitet ska säkerställas med en teknisk funktion som ger en stark autentisering.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("b) Riskanalys avseende behandling av Omf"+
                                                 "attade personuppgifter (se punkten 11) ")
                            .withParser(swedishParser)
                            .withHeadline("b) Riskanalys avseende behandling av Omfattade personuppgifter (se punkten 11) ")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("f) tillser att identifiera viktiga ändringar av den övergripande hotbilden, och")
                            .withParser(swedishParser)
                            .withHeadline("f) tillser att identifiera viktiga ändringar av den övergripande hotbilden, och")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("c) Systemsäkerhetsanalys avseende behand"+
                                                 "ling av Omfattade personuppgifter (se pu"+
                                                 "nkten 11) ")
                            .withParser(swedishParser)
                            .withHeadline("c) Systemsäkerhetsanalys avseende behandling av Omfattade personuppgifter (se punkten 11) ")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("system")
                          )
                        .test();


                   new ClassificationTester("a) informera Personalen om hur och till "+
                                                 "vem rapportering av säkerhetsincidenter "+
                                                 "ska ske,")
                            .withParser(swedishParser)
                            .withHeadline("a) informera Personalen om hur och till vem rapportering av säkerhetsincidenter ska ske,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("incident")
                          )
                        .test();


                   new ClassificationTester("d) Leverantörens generella säkerhetskrav"+
                                                 " för Omfattade personuppgifter och andra"+
                                                 " utpekade områden av särskild betydelse "+
                                                 "för organisationen.")
                            .withParser(swedishParser)
                            .withHeadline("d) Leverantörens generella säkerhetskrav för Omfattade personuppgifter och andra utpekade områden av särskild betydelse för organisationen.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("15.2 Behörighetskontroll")
                            .withParser(swedishParser)
                            .withHeadline("15.2 Behörighetskontroll")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("h) fasta och löstagbara lagringsmedia so"+
                                                 "m innehåller personuppgifter och som int"+
                                                 "e längre an- vänds för sitt ändamål utpl"+
                                                 "ånas på ett sådant sätt att uppgifterna "+
                                                 "inte kan återskapas. All ut- plåning ska"+
                                                 " ske i enlighet med tillämplig industris"+
                                                 "tandard,")
                            .withParser(swedishParser)
                            .withHeadline("h) fasta och löstagbara lagringsmedia som innehåller personuppgifter och som inte längre an- vänds för sitt ändamål utplånas på ett sådant sätt att uppgifterna inte kan återskapas. All ut- plåning ska ske i enlighet med tillämplig industristandard,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("15. Leverantörens säkerhetsåtgärder")
                            .withParser(swedishParser)
                            .withHeadline("15. Leverantörens säkerhetsåtgärder")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("aktivitet")
                          )
                        .test();


                   new ClassificationTester("e) Informationssäkerhetspolicy (se punkt"+
                                                 "en 13) ")
                            .withParser(swedishParser)
                            .withHeadline("e) Informationssäkerhetspolicy (se punkten 13) ")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("15.3.2 Leverantören ska säkerställa att "+
                                                 "Känsliga personuppgifter är krypterade n"+
                                                 "är de skickas över öppna nät (t.ex. geno"+
                                                 "m VPN-teknik).")
                            .withParser(swedishParser)
                            .withHeadline("15.3.2 Leverantören ska säkerställa att Känsliga personuppgifter är krypterade när de skickas över öppna nät (t.ex. genom VPN-teknik).")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("15.2.2 Leverantören ska inrätta och uppr"+
                                                 "ätthålla skriftliga rutiner för (i) behö"+
                                                 "righetstilldelning och")
                            .withParser(swedishParser)
                            .withHeadline("15.2.2 Leverantören ska inrätta och upprätthålla skriftliga rutiner för (i) behörighetstilldelning och")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("Grund: Datainspektionen Dnr 1082-2006 "+
                                                 "(Nacka kommun/e-post) och Datainspe"+
                                                 "ktionen Dnr 1161- 2007 (Apoteke"+
                                                 "t).")
                            .withParser(swedishParser)
                            .withHeadline("---")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("13.5 Leverantören är medveten om att det"+
                                                 " är av stor vikt att både informationssä"+
                                                 "kerhetspolicyn och säkerhetsinstruktione"+
                                                 "rna hålls aktuella och åtar sig att vid "+
                                                 "behov uppdatera dessa.")
                            .withParser(swedishParser)
                            .withHeadline("13.5 Leverantören är medveten om att det är av stor vikt att både informationssäkerhetspolicyn och säkerhetsinstruktionerna hålls aktuella och åtar sig att vid behov uppdatera dessa.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("1) Personalen använder personlig inloggn"+
                                                 "ing (personliga användarnamn och lösenor"+
                                                 "d),")
                            .withParser(swedishParser)
                            .withHeadline("1) Personalen använder personlig inloggning (personliga användarnamn och lösenord),")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("15.2.5 Leverantören ska utföra återkomma"+
                                                 "nde och systematiska kontroller av behan"+
                                                 "dlingshistorik för Känsliga personuppgif"+
                                                 "ter i syfte att upptäcka obehörig åtkoms"+
                                                 "t till Känsliga personupp- gifter.")
                            .withParser(swedishParser)
                            .withHeadline("15.2.5 Leverantören ska utföra återkommande och systematiska kontroller av behandlingshistorik för Känsliga personuppgifter i syfte att upptäcka obehörig åtkomst till Känsliga personupp- gifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();




                   new ClassificationTester(" Grund: Datainspektionen Dnr 1739-2008 ("+
                                                 "CSN)")
                            .withParser(swedishParser)
                            .withHeadline(" Grund: Datainspektionen Dnr 1739-2008 (CSN)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("15.2.4 Leverantören ska föra en s.k. log"+
                                                 "g utvisande åtkomsthistorik till Känslig"+
                                                 "a personuppgifter. Loggen ska utvisa (i)"+
                                                 " vem som berett sig åtkomst till Känslig"+
                                                 "a personuppgifter, (ii) när åt- komsten "+
                                                 "ägde rum och (iii) om användaren ändrat "+
                                                 "Känsliga personuppgifter. All behand- li"+
                                                 "ngshistorik ska vara utformad på ett såd"+
                                                 "ant sätt att det går att utreda felaktig"+
                                                 " och obehörig användning av Känsliga per"+
                                                 "sonuppgifter. Åtkomsten till sådan(a) lo"+
                                                 "gg(ar) ska begränsas till användare som "+
                                                 "behöver åtkomst för att fullgöra sina ar"+
                                                 "betsuppgifter.")
                            .withParser(swedishParser)
                            .withHeadline("15.2.4 Leverantören ska föra en s.k. logg utvisande åtkomsthistorik till Känsliga personuppgifter. Loggen ska utvisa (i) vem som berett sig åtkomst till Känsliga personuppgifter, (ii) när åt- komsten ägde rum och (iii) om användaren ändrat Känsliga personuppgifter. All behand- lingshistorik ska vara utformad på ett sådant sätt att det går att utreda felaktig och obehörig användning av Känsliga personuppgifter. Åtkomsten till sådan(a) logg(ar) ska begränsas till användare som behöver åtkomst för att fullgöra sina arbetsuppgifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("2) åtkomst till IT-utrustning begränsas,"+
                                                 "")
                            .withParser(swedishParser)
                            .withHeadline("2) åtkomst till IT-utrustning begränsas,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("a) Analys av hotbild (se punkten 11)")
                            .withParser(swedishParser)
                            .withHeadline("a) Analys av hotbild (se punkten 11)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("13.2 Leverantören ska upprätta en skrift"+
                                                 "lig informationssäkerhetspolicy. Informa"+
                                                 "tions- säkerhetspolicyn ska åtminstone a"+
                                                 "vhandla följande områden:")
                            .withParser(swedishParser)
                            .withHeadline("13.2 Leverantören ska upprätta en skriftlig informationssäkerhetspolicy. Informations- säkerhetspolicyn ska åtminstone avhandla följande områden:")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("15.3.3 Leverantören ska säkerställa att "+
                                                 "lämpliga tekniska åtgärder införs och an"+
                                                 "vänds för att autentisera mottagaren a"+
                                                 "v Känsliga personuppgifter, så att enbar"+
                                                 "t den tilltänkta mottagaren kan ta del a"+
                                                 "v Känsliga personuppgifter när de skicka"+
                                                 "s över öppna nät. Användares identitet s"+
                                                 "ka säkerställa genom t.ex. kryptering,"+
                                                 " engångslösenord eller motsvarande.")
                            .withParser(swedishParser)
                            .withHeadline("15.3.3 Leverantören ska säkerställa att lämpliga tekniska åtgärder införs och används för att autenti- sera mottagaren av Känsliga personuppgifter, så att enbart den tilltänkta mottagaren kan ta del av Känsliga personuppgifter när de skickas över öppna nät. Användares identitet ska sä- kerställa genom t.ex. kryptering, engångslösenord eller motsvarande.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("f) utskrivet material innehållandes Käns"+
                                                 "liga personuppgifter och mobila enheter "+
                                                 "(t.ex. bärbara datorer) som erbjuder til"+
                                                 "lgång till Känsliga personuppgifter håll"+
                                                 "s under uppsikt och förvaras i säkra utr"+
                                                 "ymmen när de inte används,")
                            .withParser(swedishParser)
                            .withHeadline("f) utskrivet material innehållandes Känsliga personuppgifter och mobila enheter (t.ex. bärbara datorer) som erbjuder tillgång till Känsliga personuppgifter hålls under uppsikt och förvaras i säkra utrymmen när de inte används,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("5) tillämpa rutiner för att ta bort behö"+
                                                 "righeter (t.ex. när Personal slutar elle"+
                                                 "r service och reparation avslutats).")
                            .withParser(swedishParser)
                            .withHeadline("5) tillämpa rutiner för att ta bort behörigheter (t.ex. när Personal slutar eller service och reparation avslutats).")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("15.2.1 Leverantören ska inrätta och uppr"+
                                                 "ätta lämplig behörighetskontroll som inn"+
                                                 "ebär att enbart behöriga personer ges ti"+
                                                 "llgång till IT-utrustning. Leverantörer "+
                                                 "ska bl.a. tillse att:")
                            .withParser(swedishParser)
                            .withHeadline("15.2.1 Leverantören ska inrätta och upprätta lämplig behörighetskontroll som innebär att enbart behöriga personer ges tillgång till IT-utrustning. Leverantörer ska bl.a. tillse att:")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("13. Informationssäkerhetspolicy och säkerhetsinstruktioner")
                            .withParser(swedishParser)
                            .withHeadline("13. Informationssäkerhetspolicy och säkerhetsinstruktioner")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("16.2 Leverantören ska säkerställa att sä"+
                                                 "kerhetsincidenter hanteras på ett lämpli"+
                                                 "gt sätt. Leverantö- ren ska bl.a.:")
                            .withParser(swedishParser)
                            .withHeadline("16.2 Leverantören ska säkerställa att säkerhetsincidenter hanteras på ett lämpligt sätt. Leverantö- ren ska bl.a.:")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("incident")
                          )
                        .test();


                   new ClassificationTester("(i) genomföra en riskanalys med avseende"+
                                                 " på den planerade behandlingen av Omfatt"+
                                                 "ade per- sonuppgifter och (ii) en system"+
                                                 "säkerhetsanalys för varje IT-system i vi"+
                                                 "lket Omfattade per- sonuppgifter kommer "+
                                                 "att behandlas.")
                            .withParser(swedishParser)
                            .withHeadline("(i) genomföra en riskanalys med avseende på den planerade behandlingen av Omfattade per- sonuppgifter och (ii) en systemsäkerhetsanalys för varje IT-system i vilket Omfattade per- sonuppgifter kommer att behandlas.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("15.3.5 Vid överföring av Omfattade perso"+
                                                 "nuppgifter via trådlösa nät ska Leverant"+
                                                 "ören särskilt tillse att säkerheten i så"+
                                                 "dana nät tillgodosetts.")
                            .withParser(swedishParser)
                            .withHeadline("15.3.5 Vid överföring av Omfattade personuppgifter via trådlösa nät ska Leverantören särskilt tillse att säkerheten i sådana nät tillgodosetts.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("b) säkerställa att det finns resurser fö"+
                                                 "r att prioritera och åtgärda inträffade "+
                                                 "säkerhetsincidenter,")
                            .withParser(swedishParser)
                            .withHeadline("b) säkerställa att det finns resurser för att prioritera och åtgärda inträffade säkerhetsincidenter,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("incident")
                          )
                        .test();



                   new ClassificationTester("a) Leverantörens lokaler har ett lämplig"+
                                                 "t tillträdesskydd (låsta dörrar, bemanna"+
                                                 "d reception, att tillträde registreras o"+
                                                 "ch uppgifterna förvaras säkert osv.),")
                            .withParser(swedishParser)
                            .withHeadline("a) Leverantörens lokaler har ett lämpligt tillträdesskydd (låsta dörrar, bemannad reception, att tillträde registreras och uppgifterna förvaras säkert osv.),")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("10.2 Leverantören ska tillse att dennes "+
                                                 "organisation har en tillfredsställande o"+
                                                 "rdning för informationssäkerhet, som u"+
                                                 "ppfyller de krav som följer av Regelverk"+
                                                 "et.")
                            .withParser(swedishParser)
                            .withHeadline("10.2 Leverantören ska tillse att dennes organisation har en tillfredsställande ordning för informat- ionssäkerhet, som uppfyller de krav som följer av Regelverket.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("11.3 Analys av hotbilden enligt punkten "+
                                                 "11.1 ovan ska (i) redogöra för vilka hän"+
                                                 "delser som kan drabba den egna IT-miljön"+
                                                 ", (ii) hur stor risken är att ett hot re"+
                                                 "aliseras och konsekvenserna  av detta, s"+
                                                 "amt (iii) vilka resurser som en obehörig"+
                                                 " behöver eller vilka omständigheter som "+
                                                 "krävs för att hotet ska bli verklighet. "+
                                                 "Leverantören ska planera sin verksamhet "+
                                                 "utifrån hot- bilden. Leverantören är med"+
                                                 "veten om att bedömningen av hotbilden be"+
                                                 "höver uppdateras från tid till annan. Fö"+
                                                 "re behandling av Omfattade personuppgift"+
                                                 "er påbörjas ska Leverantören")
                            .withParser(swedishParser)
                            .withHeadline("11.3 Analys av hotbilden enligt punkten 11.1 ovan ska (i) redogöra för vilka händelser som kan drabba den egna IT-miljön, (ii) hur stor risken är att ett hot realiseras och konsekvenserna  av detta, samt (iii) vilka resurser som en obehörig behöver eller vilka omständigheter som krävs för att hotet ska bli verklighet. Leverantören ska planera sin verksamhet utifrån hot- bilden. Leverantören är medveten om att bedömningen av hotbilden behöver uppdateras från tid till annan. Före behandling av Omfattade personuppgifter påbörjas ska Leverantören")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("d) tillser att identifiera eventuella be"+
                                                 "hov av intern eller extern rådgivning i "+
                                                 "informationssäkerhetsfrågor,")
                            .withParser(swedishParser)
                            .withHeadline("d) tillser att identifiera eventuella behov av intern eller extern rådgivning i informationssä- kerhetsfrågor,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("15.4.2 Leverantören ansvarar för och ska"+
                                                 " säkerställa att det finns rutiner för s"+
                                                 "ekretessmarkering av media som innehålle"+
                                                 "r Känsliga personuppgifter.")
                            .withParser(swedishParser)
                            .withHeadline("15.4.2 Leverantören ansvarar för och ska säkerställa att det finns rutiner för sekretessmarkering av media som innehåller Känsliga personuppgifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("4) minimalt antal personer som har tillg"+
                                                 "ång till Omfattade personuppgifter, och")
                            .withParser(swedishParser)
                            .withHeadline("4) minimalt antal personer som har tillgång till Omfattade personuppgifter, och")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("15.2.3 Åtkomstbegränsning ska utformas s"+
                                                 "å att användarens åtkomst till IT-utrust"+
                                                 "ning begränsas till vad som behövs för a"+
                                                 "tt användaren ska kunna fullgöra sina ar"+
                                                 "betsuppgifter.")
                            .withParser(swedishParser)
                            .withHeadline("15.2.3 Åtkomstbegränsning ska utformas så att användarens åtkomst till IT-utrustning begränsas till vad som behövs för att användaren ska kunna fullgöra sina arbetsuppgifter.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("10.4 Leverantören åtar sig att tillse at"+
                                                 "t Leverantörens ledning har ett särskilt"+
                                                 " ansvar för informationssäkerhetsarbet"+
                                                 "et och att Leverantörens ledning:")
                            .withParser(swedishParser)
                            .withHeadline("10.4 Leverantören åtar sig att tillse att Leverantörens ledning har ett särskilt ansvar för informat- ionssäkerhetsarbetet och att Leverantörens ledning:")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("j) att media innehållandes personuppgift"+
                                                 "er säkerhetskopieras i syfte att förhind"+
                                                 "ra förlust av data och att det finns bep"+
                                                 "rövade rutiner för återställande av säke"+
                                                 "rhetskopior.")
                            .withParser(swedishParser)
                            .withHeadline("j) att media innehållandes personuppgifter säkerhetskopieras i syfte att förhindra förlust av data och att det finns beprövade rutiner för återställande av säkerhetskopior.")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("Grund: Datainspektionen"+
                                                 " Dnr 1082-20"+
                                                 "06 (Nacka ko"+
                                                 "mmun/e-post), Datainspekti"+
                                                 "onen Dnr 1623- "+
                                                 "2006, Datainspektionen<"+
                                                 "/i> Dnr 1161-2007 (Apoteket). Datai"+
                                                 "nspektionen Dnr 473-2008 (Soc-t"+
                                                 "anter).")
                            .withParser(swedishParser)
                            .withHeadline("---")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();



                   new ClassificationTester("11. Analys av risker: hotbild, riskanaly"+
                                                 "s och systemsäkerhetsanalys")
                            .withParser(swedishParser)
                            .withHeadline("11. Analys av risker: hotbild, riskanalys och systemsäkerhetsa- nalys")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("risk")
                          )
                        .test();


                   new ClassificationTester("c) under vilka förutsättningar åtkomst t"+
                                                 "ill och behandling av Omfattade personup"+
                                                 "pgifter är tillåten,")
                            .withParser(swedishParser)
                            .withHeadline("c) under vilka förutsättningar åtkomst till och behandling av Omfattade personuppgifter är tillåten,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


                   new ClassificationTester("d) säkerställa uppföljning av säkerhetsi"+
                                                 "ncidenter, och")
                            .withParser(swedishParser)
                            .withHeadline("d) säkerställa uppföljning av säkerhetsincidenter, och")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("incident")
                          )
                        .test();


                   new ClassificationTester("13.4 Leverantören ska upprätta skriftlig"+
                                                 "a säkerhetsinstruktioner som ska ge konk"+
                                                 "reta riktlinjer för/avseende:")
                            .withParser(swedishParser)
                            .withHeadline("13.4 Leverantören ska upprätta skriftliga säkerhetsinstruktioner som ska ge konkreta riktlinjer för/avseende:")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


                   new ClassificationTester("g) löstagbara media innehållandes person"+
                                                 "uppgifter endast lämnas ut till behörig "+
                                                 "personal och i krypterat format,")
                            .withParser(swedishParser)
                            .withHeadline("g) löstagbara media innehållandes personuppgifter endast lämnas ut till behörig personal och i krypterat format,")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("information")
                          )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }


    /***********************************************************
         *
         *      Testing Classification by examples for tag #SECURITY
         *      Document:   "Avtalsbilaga 2 EHM_s krav på Tjänsten.docx"
         *      Language:   "SV"
         *
         */


        @Test
        public void testAvtalsbilaga2EHM_skravpåTjänstenExamples(){
            try {
                   new ClassificationTester("- Säkerhetshantering (Information Securi"+
                                                 "ty Management)")
                            .withParser(swedishParser)
                            .withHeadline("- Säkerhetshantering (Information Security Management)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierSV())
                            .withClassifier(new DefinitionUsageClassifierSV())
                            .withClassifier(new SecurityClassifierSV())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("process")
                          )
                        .test();


               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }



    /***********************************************************
        *
        *      Testing Classification by examples for tag #SECURITY
        *      Document:   " Avtalsbilaga 2a"
        *      Language:   "SV"
        *
        */


       @Test
       public void testAvtalsbilaga2aExamples(){
           try {
                  new ClassificationTester("Trafik som passerar fler än två zongräns"+
                                                "er är inte tillåten. Exempelvis måste tr"+
                                                "afik mellan Untrusted- och Trustedzon te"+
                                                "rmineras i zonen Semitrusted. Terminerin"+
                                                "gen kan exempelvis göras i en lastbalans"+
                                                "erare eller en proxyserver.")
                           .withParser(swedishParser)
                           .withHeadline("Inledning")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SecurityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                               .withTag("systemarkitektur")
                         )
                       .test();


                  new ClassificationTester("- Trusted för interna system som inte ha"+
                                                "r direkta kopplingar till osäkra nät ell"+
                                                "er lagrar känslig data. I Trusted hamnar"+
                                                " huvuddelen av alla servrar.")
                           .withParser(swedishParser)
                           .withHeadline("- Trusted för interna system som inte har direkta kopplingar till osäkra nät eller lagrar känslig data. I Trusted hamnar huvuddelen av alla servrar.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SecurityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                               .withTag("systemarkitektur")
                         )
                       .test();


                  new ClassificationTester("De zoner som finns i Burtonmodellen är:")
                           .withParser(swedishParser)
                           .withHeadline("Inledning")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SecurityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                               .withTag("systemarkitektur")
                         )
                       .test();


                  new ClassificationTester("Inloggningen i kontorsnätet ger vissa an"+
                                                "vändare tillgång till resurser i test oc"+
                                                "h utveckling då rollbaserad inloggning t"+
                                                "illämpas som i sin tur ger access till t"+
                                                "est och utvecklingsmiljöerna.")
                           .withParser(swedishParser)
                           .withHeadline("Inledning")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SecurityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                               .withTag("access")
                         )
                       .test();


                  new ClassificationTester("- Untrusted för osäkra nät som exempelvis Internet ")
                           .withParser(swedishParser)
                           .withHeadline("- Untrusted för osäkra nät som exempelvis Internet ")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SecurityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                               .withTag("systemarkitektur")
                         )
                       .test();


                  new ClassificationTester("- Semitrusted för publika tjänster och i"+
                                                "nnehåller system som har anslutningar ti"+
                                                "ll untrusted-zon, exempelvis webservrar "+
                                                "och lastbalanserare. Semitrusted motsvar"+
                                                "ar det som ofta brukar kallas DMZ.")
                           .withParser(swedishParser)
                           .withHeadline("- Semitrusted för publika tjänster och innehåller system som har anslutningar till untrusted-zon, exempelvis webservrar och lastbalanserare. Semitrusted motsvarar det som ofta brukar kallas DMZ.")
                           .withProject(mockProject, mockDocument)
                           .withClassifier(new NumberClassifierSV())
                           .withClassifier(new DefinitionUsageClassifierSV())
                           .withClassifier(new SecurityClassifierSV())
                           .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                               .withTag("systemarkitektur")
                         )
                       .test();


              } catch (Exception e) {
                   e.printStackTrace();
                   assertTrue(false);
              }
           }


    /***********************************************************
       *
       *      Testing Classification by examples for tag #BUSINESS_CONTINUITY
       *      Document:   "  Request for ProposalNew TMSMain Document"
       *      Language:   "EN"
       *
       */


      @Test
      public void testNewTMSMainDocumentExamples(){
          try {
                 new ClassificationTester("— Vendor has a robust plan for ris"+
                                               "k mitigation and can evidence previously"+
                                               " successful projects of a similar scale "+
                                               "and complexity")
                          .withParser(englishParser)
                          .withHeadline("— Vendor has a robust plan for risk mitigation and can evidence previously successful projects of a similar scale and complexity")
                          .withProject(mockProject, mockDocument)
                          .withClassifier(new NumberClassifierEN())
                          .withClassifier(new DefinitionUsageClassifierEN())
                          .withClassifier(new SecurityClassifierEN())
                          .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                  .withTag("risk")
                          )
                      .test();


             } catch (Exception e) {
                  e.printStackTrace();
                  assertTrue(false);
             }
          }


    /***********************************************************
         *
         *      Testing Classification by examples for tag #SECURITY
         *      Document:   "Request for ProposalNew TMSAppendix B, Functional & Non-functional Requirements"
         *      Language:   "EN"
         *
         */


        @Test
        public void testNewTMSAppendixBExamples(){
            try {
                   new ClassificationTester("4.1.1.14In the GUI, there must be the ab"+
                                                 "ility to create, modify and delete roles"+
                                                 " and users (an audit trail is required f"+
                                                 "or all actions and administrative action"+
                                                 "s)")
                            .withParser(englishParser)
                            .withHeadline("4.1.1.14In the GUI, there must be the ability to create, modify and delete roles and users (an audit trail is required for all actions and administrative actions)")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierEN())
                            .withClassifier(new DefinitionUsageClassifierEN())
                            .withClassifier(new SecurityClassifierEN())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();



                   new ClassificationTester("4.1.1.16There must be different roles an"+
                                                 "d there must be restrictions for what th"+
                                                 "e role can do/see and don't do/see in th"+
                                                 "e system. Example of roles:")
                            .withParser(englishParser)
                            .withHeadline("4.1.1.16There must be different roles and there must be restrictions for what the role can do/see and don't do/see in the system. Example of roles:")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierEN())
                            .withClassifier(new DefinitionUsageClassifierEN())
                            .withClassifier(new SecurityClassifierEN())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();


                   new ClassificationTester("4.1.1.13There is a need to manage differ"+
                                                 "ent types of users to get access to diff"+
                                                 "erent information in the system since th"+
                                                 "ere is sensitive data and country unique"+
                                                 " settings")
                            .withParser(englishParser)
                            .withHeadline("4.1.1.13There is a need to manage different types of users to get access to different information in the system since there is sensitive data and country unique settings")
                            .withProject(mockProject, mockDocument)
                            .withClassifier(new NumberClassifierEN())
                            .withClassifier(new DefinitionUsageClassifierEN())
                            .withClassifier(new SecurityClassifierEN())
                            .expectingClassification(new ClassificationAssertion(FeatureTypeTree.Security, 1)
                                .withTag("access")
                          )
                        .test();



               } catch (Exception e) {
                    e.printStackTrace();
                    assertTrue(false);
               }
            }


}
