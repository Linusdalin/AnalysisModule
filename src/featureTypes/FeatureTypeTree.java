package featureTypes;

/***********************************************************************************************
 *
 *
 *          The feature type tree defines the hierarchy of the classifications
 *
 *
 */
public class FeatureTypeTree {

    public static final FeatureTypeInterface None = new FeatureTypeNone();

    public static final FeatureTypeInterface Root = new FeatureTypeRoot();

    // Structure



    public static final FeatureTypeInterface    Deadline = new FeatureTypeDeadline();
    public static final FeatureTypeInterface    Period = new FeatureTypePeriod();
    public static final FeatureTypeInterface        Date = new FeatureTypeDate();
    public static final FeatureTypeInterface        Amount = new FeatureTypeAmount();
    public static final FeatureTypeInterface    Percentage= new FeatureTypePercentage();
    public static final FeatureTypeInterface            Numex = new FeatureTypeNumber();
    public static final FeatureTypeInterface    Regulation = new FeatureTypeRegulation();


    public static final FeatureTypeInterface Headline = new FeatureTypeHeadline();


    public static final FeatureTypeInterface  ContractDelivery                   = new FeatureTypeContractDelivery();

    // Legal Requirements

    public static final FeatureTypeInterface    General                             = new FeatureTypeGeneral();
    public static final FeatureTypeInterface        TERM_AND_TERMINATION            = new FeatureTypeTermAndTermination();
    public static final FeatureTypeInterface            TERM                        = new FeatureTypeTerm();
    public static final FeatureTypeInterface            TERMINATION                 = new FeatureTypeTermination();

    public static final FeatureTypeInterface    Legal                               = new FeatureTypeLegal();
    public static final FeatureTypeInterface        LegalEntity                     = new FeatureTypeEntity();
    public static final FeatureTypeInterface            PARTY                       = new FeatureTypeParts();
    public static final FeatureTypeInterface            PARTY_USAGE                 = new FeatureTypePartyUsage();

    public static final FeatureTypeInterface        GeneralProvisions               = new FeatureTypeGeneralProvisions();
    public static final FeatureTypeInterface            ARBITRATION                 = new FeatureTypeArbitration();
    public static final FeatureTypeInterface            PRECEDENCE                  = new FeatureTypePrecedence();
    public static final FeatureTypeInterface        CONFIDENTIALITY                 = new FeatureTypeConfidentiality();

    public static final FeatureTypeInterface    Financial                           = new FeatureTypeFinancial();
    public static final FeatureTypeInterface        TERMS                           = new FeatureTypeTerms();
    public static final FeatureTypeInterface            COMPENSATION                = new FeatureTypeCompensation();
    public static final FeatureTypeInterface            EXPENSES                    = new FeatureTypeExpenses();
    public static final FeatureTypeInterface            PAYMENT                     = new FeatureTypePayment();
    public static final FeatureTypeInterface            INVOICING                   = new FeatureTypeInvoicing();
    public static final FeatureTypeInterface        DAMAGES                         = new FeatureTypeDamages();
    public static final FeatureTypeInterface            PENALTIES                   = new FeatureTypePenalties();
    public static final FeatureTypeInterface            LIQUIDATED_DAMAGES          = new FeatureTypeLiquidatedDamages();
    public static final FeatureTypeInterface            LIMITATION_OF_LIABILITY     = new FeatureTypeLimitationOfLiability();
    public static final FeatureTypeInterface            INDEMNIFICATION             = new FeatureTypeIndemnification();
    public static final FeatureTypeInterface        WARRANTY                        = new FeatureTypeWarranty();

    public static final FeatureTypeInterface    GOVERNANCE                          = new FeatureTypeGovernance();
    public static final FeatureTypeInterface        REPORTING                       = new FeatureTypeReporting();
    public static final FeatureTypeInterface        AUDIT                           = new FeatureTypeAudit();
    public static final FeatureTypeInterface        DISCLOSURE                      = new FeatureTypeDisclosure();
    public static final FeatureTypeInterface        CHANGE_MGMNT                    = new FeatureTypeChangeMgmnt();
    public static final FeatureTypeInterface    IPR                                 = new FeatureTypeIPR();

    public static final FeatureTypeInterface    COMPLIANCE                          = new FeatureTypeCompliance();
    public static final FeatureTypeInterface        LEGAL_COMPLIANCE                = new FeatureTypeLegalCompliance();
    public static final FeatureTypeInterface        REGULATORY_COMPLIANCE           = new FeatureTypeRegulatoryCompliance();
    public static final FeatureTypeInterface        STANDARDS_COMPLIANCE            = new FeatureTypeStandardsCompliance();

    public static final FeatureTypeInterface    SOLUTION                            = new FeatureTypeSolution();
    public static final FeatureTypeInterface        DELIVERABLES                    = new FeatureTypeDeliverables();
    public static final FeatureTypeInterface        RESOURCES                       = new FeatureTypeResources();
    public static final FeatureTypeInterface            STAFFING                    = new FeatureTypeStaffing();
    public static final FeatureTypeInterface            SUBCONTRACTORS              = new FeatureTypeSubcontractors();

    public static final FeatureTypeInterface        ACCEPTANCE                      = new FeatureTypeAcceptance();
    public static final FeatureTypeInterface            ACCEPTANCE_CRITERIA         = new FeatureTypeAcceptanceCriteria();

    public static final FeatureTypeInterface        Scope                       = new FeatureTypeScope();
    public static final FeatureTypeInterface            Preconditions           = new FeatureTypePreconditions();
    public static final FeatureTypeInterface            SupportMaint            = new FeatureTypeSupportMaint();
    public static final FeatureTypeInterface            SolutionReq             = new FeatureTypeSolutionReq();
    public static final FeatureTypeInterface                SLA                  = new FeatureTypeSLA();
    public static final FeatureTypeInterface                    Delays           = new FeatureTypeDelays();
    public static final FeatureTypeInterface                    Defects          = new FeatureTypeDefects();
    public static final FeatureTypeInterface                    BusinessContinuity = new FeatureTypeBusinessContinuity();
    public static final FeatureTypeInterface            Delivery                = new FeatureTypeDelivery();

    public static final FeatureTypeInterface    Phases                          = new FeatureTypePhases();
    public static final FeatureTypeInterface        Fulfillment                 = new FeatureTypeFulfillment();
    public static final FeatureTypeInterface        Presigning                  = new FeatureTypePreSigning();

    public static final FeatureTypeInterface RiskMgmnt                          = new FeatureTypeRiskMgmnt();
    public static final FeatureTypeInterface     Security                       = new FeatureTypeSecurity();


    public static final FeatureTypeInterface Highlight                          = new FeatureTypeHighlight();
    public static final FeatureTypeInterface Structure = new FeatureTypeStructure();
    public static final FeatureTypeInterface  Content                           = new FeatureTypeContent();
    public static final FeatureTypeInterface        DEFINITION_CONCEPT          = new FeatureTypeDefinition();
    public static final FeatureTypeInterface            DEFINITION              = new FeatureTypeDefinitionDef();
    public static final FeatureTypeInterface            DEFINITION_USAGE        = new FeatureTypeDefinitionUsage();
    public static final FeatureTypeInterface            DefinitionRepetition    = new FeatureTypeDefinitionRepetition();
    public static final FeatureTypeInterface        Background                  = new FeatureTypeBackground();


    // Risk

    public static final FeatureTypeInterface    Risk                            = new FeatureTypeRisk();
    public static final FeatureTypeInterface        Exclusivity                 = new FeatureTypeExclusivity();
    public static final FeatureTypeInterface        Unspecific                  = new FeatureTypeUnspecific();


    // Rights

    public static final FeatureTypeInterface    RIGHTS_AND_OBLIGATIONS          = new FeatureTypeRightsAndRestrictions();
    public static final FeatureTypeInterface        RIGHT                       = new FeatureTypeRight();
    public static final FeatureTypeInterface        RESTRICTION                     = new FeatureTypeRestriction();
    public static final FeatureTypeInterface        RESPONSIBILITY                  = new FeatureTypeResponsibility();
    public static final FeatureTypeInterface            OBLIGATION                  = new FeatureTypeObligation();
    public static final FeatureTypeInterface        EXEMPTION                       = new FeatureTypeExemption();

    public static final FeatureTypeInterface Reference                          = new FeatureTypeReference();
    public static final FeatureTypeInterface    LegalReference                  = new FeatureTypeLegalReference();



    public static final FeatureTypeInterface EMAIL = new FeatureTypeEmail();
    public static final FeatureTypeInterface URL = new FeatureTypeURL();
    public static final FeatureTypeInterface ADDRESS = new FeatureTypeAddress();
    public static final FeatureTypeInterface SIGNATURE = new FeatureTypeSignature();

    public static final FeatureTypeInterface ITOperation = new FeatureTypeITOPeration();

    public static final FeatureTypeInterface DetailedValue = new FeatureTypeDetailedValue();


}
