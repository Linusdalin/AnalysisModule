package language;

import classifiers.ClassifierInterface;
import classifiers.baseClassifiers.URLClassifier;
import classifiers.englishClassifiers.*;
import classifiers.swedishClassifiers.NumberExpressionClassifierSV;
import openNLP.NLParser;
import stemming.EnglishStemmer;
import stemming.StemmerInterface;

/****************************************************************************************
 *
 *          Basic functionality for the English language:
 *
 *           - Supported extractors
 *           - Ignore lists for search
 *           - common words for language recognition
 *
 */


public class English extends Language implements LanguageInterface{

    private static final String[] ignoreList = {"and", "or", "is"};

    private static NLParser parser = null;


    public static ClassifierInterface[] supportedClassifiers = {


            //new SentenceClassifierEN(),
            new NumberClassifierEN(),
            new NumberExpressionClassifierEN(),
            new PercentageClassifierEN(),
            new DateClassifierEN(),
            new EmailClassifierEN(),
            //new URLClassifierEN(),
            new AddressClassifierEN(),
            new SignatureClassifierEN(),
            new LegalReferenceClassifierEN(),
            new AmountClassifierEN(),
            new DeadlineClassifierEN(),
            new DefinitionClassifierEN(),
            new DefinitionSourceClassifierEN(),
            //new ReferenceClassifierEN(),
            new RiskMgmntClassifierEN(),

            new UnspecificClassifierEN(),
            new RestrictionClassifierEN(),
            new ExemptionClassifierEN(),
            new RightsClassifierEN(),
            new ExclusivityClassifierEN(),

            new ContractDeliveryClassifierEN(),
            new RegulationClassifierEN(),
            new SolutionClassifierEN(),
            new DeliveryClassifierEN(),
            new DeliverablesClassifierEN(),
            new RightsAndObligationsClassifierEN(),
            new ResponsibilitiesClassifierEN(),
            new TermClassifierEN(),
            new TerminationClassifierEN(),
            new TermAndTerminationClassifierEN(),
            new StructureClassifierEN(),
            new HighlightClassifierEN(),
            new LegalClassifierEN(),
            new GeneralClassifierEN(),
            new ContentClassifierEN(),
            new BackgroundClassifierEN(),

            new GeneralProvisionsClassifierEN(),
            new PrecedenceClassifierEN(),
            new ArbitrationClassifierEN(),
            new ConfidentialityClassifierEN(),

            new AcceptanceClassifierEN(),
            new AcceptanceCriteriaClassifierEN(),
            new PartsClassifierEN(),
            new PartyUsageClassifierEN(),
            new LegalEntityClassifierEN(),

            new GovernanceClassifierEN(),
            new ReportingClassifierEN(),
            new AuditClassifierEN(),
            new DisclosureClassifierEN(),
            new ChangeMgmntClassifierEN(),

            new IPRClassifierEN(),

            new ComplianceClassifierEN(),
            new LegalComplianceClassifierEN(),
            new RegulatoryComplianceClassifierEN(),
            new StandardsComplianceClassifierEN(),

            new FinancialClassifierEN(),
            new TermsClassifierEN(),
            new CompensationClassifierEN(),
            new ExpencesClassifierEN(),
            new PaymentClassifierEN(),
            new InvoicingClassifierEN(),
            new DamagesClassifierEN(),
            new PenaltiesClassifierEN(),
            new LiquidatedDamagesClassifierEN(),
            new LimitationOfLiabilityClassifierEN(),
            new IndemnificationClassifierEN(),
            new WarrantyClassifierEN(),

            new ResourcesClassifierEN(),
            new StaffingClassifierEN(),
            new SubcontractorClassifierEN(),

            new PhasesClassifierEN(),
            new FulfillmentClassifierEN(),
            new PresigningClassifierEN(),

            new SLAClassifierEN(),
            new SecurityClassifierEN(),
            new DelaysClassifierEN(),
            new DefectsClassifierEN(),
            new BusinessContinuityClassifierEN(),

            new ScopeClassifierEN(),
            new PreconditionClassifierEN(),
            new SupportMaintenanceClassifierEN(),
            //new ReqSpecClassifierEN(),                 Moved


            //new HeadlineClassifierEN(),


    };

    private static final String[][] POSCorrections = {

    };


    public English(){

        super(supportedClassifiers, referenceClassifiers, postProcessClassifiers, ignoreList, POSCorrections);
    }


    public String[] getClassifierKeywords(){

        if(classifierKeywords == null){

            classifierKeywords = generateClassifierKeywords(supportedClassifiers);
        }

        return classifierKeywords;

    }



    public static ClassifierInterface[] referenceClassifiers = {

            new OpenReferenceClassifierEN(),
    };


        // Rerun all classifiers depending on the definition usage

    public static ClassifierInterface[] postProcessClassifiers = {


            new DefinitionRepetitionClassifierEN(),
            new DefinitionUsageClassifierEN(),
            new ReqSpecClassifierEN(),
            new RiskClassifierEN(),
            new ObligationClassifierEN(),
            new ReferenceClassifierEN(),


            //new OpenReferenceAnalyser(),

    };



    public static final String[] commonWords = {"yes", "no", "half", "full", "single", "during", "past","at", "this","may","will",
            "shall", "all", "normal", "high", "low", "an","a","can","is","to","you","make","made","be","for","as","and",
            "or","which", "not", "get", "on", "of", "the", "year", "price", "incl", "in", "these", "those"};

    @Override
    public boolean isLanguage(String bodyText) {

        return containsWords(commonWords, bodyText, getLanguageCode());
    }


    @Override
    public LanguageCode getLanguageCode() {
        return new LanguageCode("EN");
    }

    @Override
    public String getModelPrefix() {
        return "en";
    }

    @Override
    public String getName() {
        return "english";
    }


    @Override
    public StemmerInterface getStemmer() {

        return new EnglishStemmer();
    }



    @Override
    public String wash(String text) {

        String washedText = super.wash(text);

        washedText = washedText.replaceAll("vs\\.", "vs");
        return washedText;
    }

    @Override
    public NLParser getParser(String modelDirectory) {

        // Generate parser on request. //TODO: This should go into a warm-up

        if(parser == null)
            parser = new NLParser(this, modelDirectory);

        return parser;
    }



}
