package language;

import classifiers.ClassifierInterface;
import classifiers.swedishClassifiers.*;
import extractors.extractorsSE.RegulatoryComplianceClassifierSV;
import openNLP.NLParser;
import stemming.StemmerInterface;
import stemming.SwedishStemmer;

/**
 * Swedish language
 *
 *
 *         //TODO: refactor all common functionality by passing classifiers and language to super constructor
 *
 */


public class Swedish extends Language implements LanguageInterface{

    private static final String[] ignoreList = {"om", "med", "i", "på", "och"};

    private static NLParser parser = null;

    public static ClassifierInterface[] supportedClassifiers = {

            new SentenceClassifierSE(),
            new NumberClassifierSV(),
            new NumberExpressionClassifierSV(),
            new PercentageClassifierSV(),
            new DateClassifierSV(),
            new EmailClassifierSE(),
            //new URLClassifierSV(),
            new SignatureClassifierSV(),
            new AddressClassifierSV(),
            new LegalReferenceClassifierSE(),
            new AmountClassifierSE(),
            new DeadlineClassifierSV(),
            new DefinitionClassifierSV(),
            new DefinitionSourceClassifierSV(),
            //new ReferenceClassifierSE(),
            new RiskMgmntClassifierSV(),
            new UnspecificClassifierSV(),
            new RestrictionClassifierSV(),
            new ExemptionClassifierSV(),
            new RightsClassifierSV(),
            new ExclusivityClassifierSE(),

            new ContractDeliveryClassifierSV(),
            new RegulationClassifierSV(),
            new SolutionClassifierSV(),
            new DeliveryClassifierSV(),
            new DeliverablesClassifierSV(),
            new RightsAndObligationsClassifierSV(),
            new ResponsibilitiesClassifierSV(),
            new TermClassifierSV(),
            new TerminationClassifierSV(),
            new TermAndTerminationClassifierSE(),
            new StructureClassifierSE(),
            new HighlightClassifierSE(),
            new LegalClassifierSV(),
            new GeneralClassifierSV(),
            new ContentClassifierSE(),
            new BackgroundClassifierSV(),

            new GeneralProvisionsClassifierSV(),
            new PrecedenceClassifierSV(),
            new ArbitrationClassifierSE(),
            new ConfidentialityClassifierSV(),

            new AcceptanceClassifierSV(),
            new AcceptanceCriteriaClassifierSV(),
            new PartsClassifierSV(),
            new PartyUsageClassifierSV(),
            new LegalEntityClassifierSV(),

            new GovernanceClassifierSV(),
            new ReportingClassifierSV(),
            new AuditClassifierSV(),
            new DisclosureClassifierSV(),
            new ChangeMgmntClassifierSV(),

            new IprClassifierSV(),

            new ComplianceClassifierSV(),
            new LegalComplianceClassifierSV(),
            new RegulatoryComplianceClassifierSV(),
            new StandardsComplianceClassifierSV(),

            new FinancialClassifierSV(),
            new TermsClassifierSV(),
            new CompensationClassifierSV(),
            new ExpensesClassifierSV(),
            new PaymentClassifierSV(),
            new InvoicingClassifierSV(),
            new DamagesClassifierSV(),
            new PenaltiesClassifierSV(),
            new LiquidatedDamagesClassifierSV(),
            new LimitationOfLiabilityClassifierSV(),
            new IndemnificationClassifierSV(),
            new WarrantyClassifierSV(),

            new ResourcesClassifierSV(),
            new StaffingClassifierSV(),
            new SubcontractorClassifierSV(),

            new PhasesClassifierSV(),
            new FulfillmentClassifierSV(),
            new PresigningClassifierSV(),

            new SLAClassifierSV(),
            new SecurityClassifierSV(),
            new DelaysClassifierSV(),
            new DefectsClassifierSV(),
            new BusinessContinuityClassifierSV(),

            new ScopeClassifierSV(),
            new PreconditionClassifierSV(),
            new SupportMaintenanceClassifierSV(),
            //new ReqSpecClassifierSV(),                  Moved

            //new HeadlineClassifierSE(),


    };

    private static final String[][] POSCorrections = {

            {"åtgärda", "VB"},
            {"informera", "VB"}
    };


    public Swedish(){

        super(supportedClassifiers, referenceClassifiers, postProcessClassifiers, ignoreList, POSCorrections);
    }

    public String[] getClassifierKeywords(){

        if(classifierKeywords == null){

            classifierKeywords = generateClassifierKeywords(supportedClassifiers);
        }

        return classifierKeywords;

    }



    public static ClassifierInterface[] referenceClassifiers = {

            new OpenReferenceClassifierSV(),
    };

    // Rerun all classifiers depending on the definition usage

    public static ClassifierInterface[] postProcessClassifiers = {

            new DefinitionRepetitionClassifierSV(),
            new DefinitionUsageClassifierSV(),
            new ReqSpecClassifierSV(),
            new ObligationClassifierSV(),
            new RiskClassifierSE(),
            new ReferenceClassifierSV(),
            //new OpenReferenceAnalyser(),

    };




    public static final String[] commonWords = {"ja", "nej", "det", "är", "från", "till", "ska","ett", "två","ut","in","vi", "på", "att", "om",
            "av", "ta", "i", "då", "kan", "för", "som", "med", "men", "och", "pris", "kr", "kronor", "AB", "fysisk", "virtuell", "år", "inkl", "exkl"};

    @Override

    public boolean isLanguage(String bodyText) {

        return containsWords(commonWords, bodyText, getLanguageCode());
    }


    @Override
    public LanguageCode getLanguageCode() {
        return new LanguageCode("SV");
    }

    @Override
    public String getModelPrefix() {
        return "se";
    }


    @Override
    public String getName() {
        return "swedish";
    }



    @Override
    public StemmerInterface getStemmer() {

        return new SwedishStemmer();
    }


    @Override
    public String wash(String text) {

        String washedText = super.wash(text);
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
