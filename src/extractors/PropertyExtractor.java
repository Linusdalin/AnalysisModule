package extractors;

import analysis.*;
import parse.AnalysisFragment;
import textPatterns.TextPattern;

/**
 * Created with IntelliJ IDEA.
 * User: Linus
 *
 *
 * The Real Property clause (closely related to the Title to Assets clause) generally represents that all property interests, real or leaseholds, are disclosed
 * in a specific section in the agreement and that the Buyer has been furnished copies of any agreements pursuant to these property interests. If applicable,
 * the clause will usually explicitly state that the Seller owns no real property. The clause further represent that any leases are in full effect and have not
 * been defaulted on, breached or transferred to another party.
 *
 * Clause examples differ significantly in the number and type of representations for each class of property.
 * The Standard Clause includes all of the most common elements, however, many individual clauses only include some of the elements while including additional
 * rarer, sometimes deal-specific, representations.
 *
 *      Examples:
 *
 * Owned Property. Neither the Company nor any of its Subsidiaries currently own [or in the past have owned] any real property.
 *
 * OR
 *
 *[Schedule X] sets forth a sufficient description of all real property owned by the Company. [Except as expressly listed on [Schedule X],
 * the Company has good and indefeasible title to all property listed on [Schedule X] free and clear of Encumbrances. Neither the Company
 * nor any of its Subsidiaries has leased or otherwise granted to any Person the right to use or occupy any portion of such Property.
 * There are no outstanding options, rights of first offer or rights of first refusal to purchase any portion or interest of such Property.
 *
 *Leased Property. [Schedule X] lists all real property the Company leases. [Schedule X] also contains an accurate and complete
 * list of all leases and other Contracts in respect of real property the Company leases, accurate and complete copies
 * [and all material modifications] of which have been delivered to Buyer. The Company has a good and valid leasehold interest in
 * each lease listed on [Schedule X]. Except as set forth on [Schedule X], each of the leases and Contracts required to be listed on
 * [Schedule X] is Enforceable against the Company in accordance with its terms and is in full force and effect. To the knowledge of
 * the Company, no termination event or condition or default of a material nature exists under any of the leases on [Schedule X].


 The Tangible Property (or Personal Property) clause (closely related to the Title to Assets and Real Property clauses) generally represents
 that the Seller owns all of the tangible tssets necessary to conduct business. The clause may offer additional warranties such as suitability
 for the asset's current purpose and that it is free from material defects.

 Clause examples differ slightly in the types of warranties offered for the tangible Assets. Some clauses will also require an explicit list of
 all assets and/or except assets, encumbrances or damages that do not materially affect the business or do not meet a specific dollar amount.

 Tangible assets. [Except as explicitly set forth in the Tangible Asset Schedule], the Company owns or leases all buildings, machinery, equipment,
 and other tangible assets necessary for the conduct of its business as currently conducted and as currently proposed to be conducted by the Company.
 [To the Seller's knowledge] Each such tangible asset [has been maintained in accordance with normal industry practice,] is free from material defects
 (patent and latent), is in good operating condition and repair (subject to normal wear and tear), and is suitable for the purposes for which it currently
 is used and currently is proposed to be used by the Company.
 *
 *
 *
 * The Intellectual Property representation is one of the most heavily negotiated representations, as evidenced by its variability. In the case of short clauses,
 * covering intellectual property required to operate a business, the intellectual property representation will typically represent that the party:

     owns or has valid licenses to all its intellectual property;
     has taken adequate steps to protect its intellectual property;
     has sufficient intellectual property necessary to conduct its business;
     is not conducting its business in a manner that infringes the intellectual property of others; and
     no one else is infringing the representing party's intellectual property rights.

 However, in the case of longer clauses the range of clause elements and language is highly inconsistent. The clauses are rarely clearly organized, sometimes
 containing a set of overlapping representations without clear indication to what forms of intellectual property each representation covers. The clause elements
 and language proposed is therefore an amalgam of the best elements of all clauses analysis. No one clause example captures all of the elements and language proposed.

     Proposed Categories of Intellectual Property:
         Proprietary Intellectual Property
             Registered Intellectual Property
             Commercial Intellectual Property (Out-Bound Licenses)
             Proprietary Operational Intellectual Property (developed and customized Intellectual Property)
             Conceptual Intellectual Property
         Licensed Intellectual Property
             Operational Intellectual Property (In-Bound Licenses)
     Proposed Representations
         Disclosure
         Ownership
         Commercial Intellectual Property and Out-bound Licenses
         Operational Intellectual Property and In-Bound Licenses
         Data Protection and Compliance

 Selection of the clause elements will likely depend on whether the representing party:

     develops technology for re-sale (requiring all representations),
     utilizes technology as a critical part of its business operations requiring customized technology systems (requiring representations regarding disclosure, ownership, and licensing of Proprietary and Licensed intellectual property) , or
     utilizes technology as an operational part of the business managed mainly by commercially available software (requiring the standard representation, and others as necessary).


        Examples:



    Disclosure.
        The Intellectual Property Disclosure Schedule contains a list of all Intellectual Property owned, developed, commissioned, licensed or used by [Party], other than commercially available or off-the-shelf software.

    Intellectual Property Rights.
        Except as set forth in the Intellectual Property Disclosure Schedule, [Party];

            (i) owns the right to use all Intellectual Property;
            (ii) such Intellectual Property is sufficient in order to conduct [Party]’s business and operations;
            (iii) the use of any Intellectual Property does not violate any license agreement with any third party or infringe on the rights of any Person; and
            (iv) to the knowledge of the [Party], no person is infringing the Intellectual Property.



         1.1.  Registered Intellectual Property.  The term Registered Intellectual Property means:

            (a)     patents and patent applications [including any abandoned applications],
            (b)     trademark and service mark registrations and applications,
            (c)     copyright registrations and applications, and
            (d)     domain names and domain name registrations.

        1.2.  Commercial Intellectual Property. The term Commercial Intellectual Property means:

        (a)     all products and services currently produced, manufactured, marketed, licensed, sold or distributed by [Party] and
        (b)     all products and services currently under development that the [Party] intends to make commercially available within [x] months of the date of this agreement.

        1.3.  Operational Intellectual Property. The term Operational Intellectual Property means all :

        (a)     technology platforms,
        (b)     software,
        (c)     web sites, publications, databases and other content; and
        (d)     business processes

        material to the operation of the business.

        1.4. Conceptual Intellectual Property. The term Conceptual Intellectual Property means:

        (a)     inventions, whether or not patentable, whether or not reduced to practice or whether or not yet made the subject of a pending patent application or applications,
        (b)     ideas and conceptions of potentially patentable subject matter, including, without limitation, any patent disclosures, whether or not reduced to practice and whether or not yet made the subject of a pending Patent application or applications,
        (c)     trade secrets and confidential, technical or business information (including ideas, formulas, compositions, designs, inventions, and conceptions of inventions whether patentable or un-patentable and whether or not reduced to practice),
        (d)    technology (including know-how), manufacturing and production processes and techniques, methodologies, research and development information, drawings, specifications, designs, plans, proposals, technical data, copyrightable works, financial, marketing and business data, pricing and cost information, business and marketing plans and customer and supplier lists and information,

 */

public class PropertyExtractor extends FeatureExtractor implements FeatureExtractorInterface {

    // Patterns to look for

    private static final TextPattern[] patterns = {

            // TODO: Allow definition of word class. (E.g. "patent" below should match "patents" but not "patentable"

            // Intellectual Property

            new TextPattern(new String[] {"registered", "intellectual", "property"}).veryClose().withTag("Registered Intellectual Property"),
            new TextPattern(new String[] {"copyright", "registration" }).veryClose().withTag("Registered Intellectual Property"),
            new TextPattern(new String[] {"domain", "registration" }).close().withTag("Registered Intellectual Property"),

            new TextPattern(new String[] {"commercial", "intellectual", "property"}).veryClose().withTag("Commercial Intellectual Property"),

            new TextPattern(new String[] {"operational", "intellectual", "property"}).veryClose().withTag("Operational Intellectual Property"),
            new TextPattern(new String[] {"technology", "platform"}).veryClose().withTag("Operational Intellectual Property"),
            new TextPattern(new String[] {"software"}).withTag("Operational Intellectual Property"),
            new TextPattern(new String[] {"web", "site"}).adjacent().withTag("Operational Intellectual Property"),
            new TextPattern(new String[] {"business", "process"}).adjacent().withTag("Operational Intellectual Property"),

            new TextPattern(new String[] {"conceptual", "intellectual", "property"}).veryClose().withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"invention"}).withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"pending", "patent"}).close().withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"conception", "formula", "design", "drawing", "specification"}).anyOf().withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"trade", "secret"}).veryClose().withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"know-how"}).veryClose().withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"production", "process"}).veryClose().withTag("Conceptual Intellectual Property"),
            new TextPattern(new String[] {"research", "development"}).veryClose().withTag("Conceptual Intellectual Property"),

            new TextPattern(new String[] {"patent", "trademark", "service mark", }).anyOf().withTag("Registered Intellectual Property"),
            new TextPattern(new String[] {"product", "service", "produced", "marketed"}).mostOf().withTag("Commercial Intellectual Property"),

            new TextPattern(new String[] {"intellectual", "property"}).veryClose().withTag("Intellectual Property"),


            // Real property

            new TextPattern(new String[] {"real", "property"}).veryClose().withTag("Real Property"),
            new TextPattern(new String[] {"owned", "property"}).veryClose().withTag("Real Property"),
            new TextPattern(new String[] {"leased", "property"}).veryClose().withTag("Real Property"),

            // Tangible Property

            new TextPattern(new String[] {"tangible", "property"}).veryClose().withTag("Tangible Property"),
            new TextPattern(new String[] {"tangible", "asset"}).veryClose().withTag("Tangible Property"),
            new TextPattern(new String[] {"personal", "property"}).veryClose().withTag("Tangible Property"),
            new TextPattern(new String[] {"building", "machinery", "equipment", "necessary"}).mostOf().withTag("Tangible Property"),


    };


    // Name and special keywords that will be used to tag the fragment

    public static final String name = "Property";
    public static final String searchKeywords[] = new String[] {"asset"};


    public PropertyExtractor(){

        super();

    }

    public AnalysisOutcome classify(AnalysisFragment fragment){


        AnalysisOutcome outcome = null;

        for(TextPattern p : patterns){

            outcome = new AnalysisOutcome().addDefinition(new FragmentCondition(fragment).contains(p).testClassification(FeatureActionType.CLASSIFY, type, name));

        }

        return outcome;
    }


}
