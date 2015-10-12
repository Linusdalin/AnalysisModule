package test.oldTests;

import analysis.AnalysisOutcome;
import analysis.FeatureDefinition;
import extractors.*;
import featureTypes.FeatureTypeTree;
import org.junit.Test;
import parse.AnalysisFragment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*********************************************************************
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 */
 



public class ExtractorTest {

    private static final FeatureTypeTree tree = new FeatureTypeTree();



    @Test
    public void testLitigation(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("no current proceeding");
        new LiabilitiesExtractor().classify( f, o, tree );


        d= o.getDefinitions().get( 0 );
        assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Liability"));
        assertThat(d.getTag(), is("Litigation"));

        f = new AnalysisFragment("no outstanding judgments");
        new LiabilitiesExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Liability"));
        assertThat(d.getTag(), is("Litigation"));

        f = new AnalysisFragment("no pending investigations");
        new LiabilitiesExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Liability"));
        assertThat(d.getTag(), is("Litigation"));

        f = new AnalysisFragment("There is no suit, action or proceeding pending or, to the knowledge of [Seller], threatened against [Seller] that, individually or in the aggregate, would reasonably be expected to have a Material Adverse Effect.");
        new LiabilitiesExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Liability"));
        assertThat(d.getTag(), is("Litigation"));

        f = new AnalysisFragment("There is no Litigation pending or, to the knowledge of [Purchaser], threatened that is reasonably likely to prohibit or restrain the ability of [Purchaser] to enter into this Agreement or to consummate the transactions contemplated hereby.");
        new LiabilitiesExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Liability"));
        assertThat(d.getTag(), is("Litigation"));

        f = new AnalysisFragment("[To the Company's knowledge], the Company has no material obligations or liabilities [in excess of (some dollar amount), individually or in the aggregate] [matured or unmatured, fixed or contingent] other than");
        new LiabilitiesExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Liability"));
        assertThat(d.getTag(), is("Liabilities"));


    }

    @Test
    public void testSufficientFunds(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("Buyer has [and shall have at Closing] available cash resources and financing [or other sources of " +
                "immediately available funds] in an amount sufficient to consummate the transactions contemplated in this " +
                "Agreement [including resources to pay all expenses, fees and any assumed liabilities]");
        new SufficientFundsExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "SufficientFunds"));
        assertThat(d.getTag(), is("Sufficient Funds"));

        f = new AnalysisFragment("Parent has cash and cash equivalents on hand and committed credit facilities (without restrictions on " +
                "the use of such facilities for the funding of the Transactions for such purposes or conditions precedent with " +
                "respect to funding) sufficient for payment of the Merger Consideration, to consummate the Merger in accordance " +
                "with the terms of this Agreement and to satisfy all of its own and Sub's obligations under this Agreement.");
        new SufficientFundsExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "SufficientFunds"));
        assertThat(d.getTag(), is("Sufficient Funds"));

    }




    @Test
    public void testIntellectualProperty(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("no one else is infringing the representing party's intellectual property rights");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Intellectual Property"));

        f = new AnalysisFragment("is not conducting its business in a manner that infringes the intellectual property of others and");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Intellectual Property"));

        f = new AnalysisFragment("has sufficient intellectual property necessary to conduct its business");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Intellectual Property"));

        f = new AnalysisFragment("has taken adequate steps to protect its intellectual property");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Intellectual Property"));

        f = new AnalysisFragment("owns or has valid licenses to all its intellectual property");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Intellectual Property"));


        f = new AnalysisFragment("patents and patent applications [including any abandoned applications]");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Registered Intellectual Property"));

        f = new AnalysisFragment("trademark and service mark registrations and applications");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Registered Intellectual Property"));

        f = new AnalysisFragment("copyright registrations and applications");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Registered Intellectual Property"));

        f = new AnalysisFragment("domain names and domain name registrations");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Registered Intellectual Property"));


        f = new AnalysisFragment("all products and services currently produced, manufactured, marketed, licensed, sold or distributed by [Party]");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Commercial Intellectual Property"));

        f = new AnalysisFragment("all products and services currently under development that the [Party] intends to make commercially available within [x] months of the date of this agreement.");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Commercial Intellectual Property"));

        f = new AnalysisFragment("technology platforms");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Operational Intellectual Property"));

        f = new AnalysisFragment("technology platforms");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Operational Intellectual Property"));

        f = new AnalysisFragment("software");
        new PropertyExtractor().classify( f, o, tree );

        //d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());                                                      // Todo: Make this work
        //assertTrue(d.getType() instanceof PropertyExtractor);
        //assertThat(d.getTag(), is("Operational Intellectual Property"));

        f = new AnalysisFragment("web sites, publications, databases and other content");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Operational Intellectual Property"));

        f = new AnalysisFragment("business processes, material to the operation");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Operational Intellectual Property"));

        f = new AnalysisFragment("inventions, whether or not patentable, whether or not reduced to practice or whether or not yet made the subject of a pending patent application or applications");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Conceptual Intellectual Property"));

        f = new AnalysisFragment("ideas and conceptions of potentially patentable subject matter, including, without limitation, any patent disclosures, whether or not reduced to practice and whether or not yet made the subject of a pending Patent application or applications");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Conceptual Intellectual Property"));

        f = new AnalysisFragment("trade secrets and confidential, technical or business information (including ideas, formulas, compositions, designs, inventions, and conceptions of inventions whether patentable or un-patentable and whether or not reduced to practice)");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Conceptual Intellectual Property"));


        f = new AnalysisFragment("technology (including know-how), manufacturing and production processes and techniques, methodologies, research and development information, drawings, specifications, designs, plans, proposals, technical data, copyrightable works, financial, marketing and business data, pricing and cost information, business and marketing plans and customer and supplier lists and information");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Conceptual Intellectual Property"));


    }


    @Test
    public void testAsset(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("[To the Seller's knowledge], the [Seller] [is the sole and exclusive owner and] has good and marketable [and indefeasible] " +
                "title to, or a valid leasehold interest in, the properties and assets it uses, located  on its premises, shown on the Financial Statements, " +
                "or acquired after the date thereof, free and clear of all Encumbrances, except for properties and assets disposed of in the Ordinary " +
                "Course of Business since the date of the Financial Statements.");
        new AssetExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Asset"));
        assertThat(d.getTag(), is("Title to Asset"));

        f = new AnalysisFragment("(...free and clear of all Encumbrances,) except for " +
                "(1) any lien for current taxes not yet due and payable, " +
                "(2) [minor] liens that have arisen in the ordinary course of business and that do not [in any case or in the aggregate] " +
                "materially detract from the value of the assets subject thereto or materially impair the operations of the [Buyer], and " +
                "(3) liens described (in another section).");
        new AssetExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Asset"));
        assertThat(d.getTag(), is("Title to Asset"));

        f = new AnalysisFragment("Condition of Assets. [To the Seller's knowledge], each asset is in good operating condition and has " +
                "been maintained and repaired [in accordance with normal industry practice], subject to normal wear and tear.");
        new AssetExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Asset"));
        assertThat(d.getTag(), is("Title to Asset"));

        f = new AnalysisFragment("Each asset is usable for business [as it is currently conducted] and suitable for the " +
                "purpose for which it is currently used [and is proposed to be used]");
        new AssetExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Asset"));
        assertThat(d.getTag(), is("Title to Asset"));

    }

    @Test
    public void testProperty(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("Neither the Company nor any of its Subsidiaries currently own [or in the past have owned] any real property");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Real Property"));

        f = new AnalysisFragment("[Schedule X] sets forth a sufficient description of all real property owned by the Company. " +
                "[Except as expressly listed on The Owned Property Schedule], the Company has good and indefeasible title to all property listed on " +
                "[The Owned Property Schedule] free and clear of Encumbrances. Neither the Company nor any of its Subsidiaries has leased or otherwise " +
                "granted to any Person the right to use or occupy any portion of such Property. There are no outstanding options, rights of first offer or " +
                "rights of first refusal to purchase any portion or interest of such Property");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Real Property"));

        f = new AnalysisFragment("[Schedule X] lists all real property the Company leases. [The Leased Property Schedule] also contains an accurate and complete list of all leases and other Contracts in respect of real property the Company leases, accurate and complete copies [and all material modifications] of which have been delivered to Buyer. The Company has a good and valid leasehold interest in each lease listed on [The Leased Property Schedule]. Except as set forth on [The Leased Property Schedule], each of the leases and Contracts required to be listed on [The Leased Property Schedule] is Enforceable against the Company in accordance with its terms and is in full force and effect. To the knowledge of the Company, no termination event or condition or default of a material nature exists under any of the leases on [The Leased Property Schedule]");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Real Property"));

        f = new AnalysisFragment("[Except as explicitly set forth in the Tangible Asset Schedule], the Company owns or leases all buildings, machinery, equipment, and other tangible assets necessary for the conduct of its business as currently conducted and as currently proposed to be conducted by the Company. [To the Seller's knowledge] Each such tangible asset [has been maintained in accordance with normal industry practice,] is free from material defects (patent and latent), is in good operating condition and repair (subject to normal wear and tear), and is suitable for the purposes for which it currently is used and currently is proposed to be used by the Company.");
        new PropertyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Property"));
        assertThat(d.getTag(), is("Tangible Property"));

    }


    @Test
    public void testCapitalization(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;

        f = new AnalysisFragment("No Other Stock Reserved for Issuance. There are no outstanding or authorized options, warrants, purchase rights, subscription rights, conversion rights, exchange rights, or other contracts or commitments that could require the Company to issue, sell, or otherwise cause to become outstanding any of its capital stock.");
        new CapitalizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Capitalization"));

        f = new AnalysisFragment("No Other Voting Interests. There are no bonds, debentures, notes or other indebtedness of the Company or any of its Subsidiaries having the right to vote (or convertible into, or exchangeable for, securities having the right to vote) on any matters on which holders of Company Common Stock may vote.");
        new CapitalizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Capitalization"));

        f = new AnalysisFragment("No Contractual Obligations. There are no voting trusts, proxies or other similar agreements or understandings to which the Company or any Company Subsidiary is a party or by which the Company or any Company Subsidiary is bound with respect to the voting of any shares of capital stock of the Company or any Company Subsidiary.");
        new CapitalizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Capitalization"));

        f = new AnalysisFragment("No Transfer Restrictions. There are no contractual obligations or commitments of any character restricting the transfer of, or requiring the registration for sale of, any shares of capital stock.");
        new CapitalizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Capitalization"));

        f = new AnalysisFragment("No Unpaid Dividends. There are no accrued or unpaid dividends with respect to any capital stock.");
        new CapitalizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Capitalization"));



    }



    @Test
    public void testWarranty(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;
        f = new AnalysisFragment("The Software is provided \"as is\" without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement.");
        new WarrantyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Warranty"));

        f = new AnalysisFragment("[Licensor] warrants for a period of [WARRANTY PERIOD] days following performance of the service that its Maintenance Support Services [shall be performed consistent with generally accepted industry standards] ][shall be in substantial compliance with this Agreement][shall be in substantial compliance with the written Support Documentation provided to [Licensee]]");
        new WarrantyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Warranty"));


        f = new AnalysisFragment("No oral or written information or advice given by [licensor], its dealers, distributors, agents or employees shall create a warranty, or in any way increase the scope of this warranty, and you may not rely on any such information or advice.");
        new WarrantyExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Warranty"));



    }


    @Test
    public void testOrganizationExtractor(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;
        f = new AnalysisFragment("[Party] is duly licensed or qualified to do business and is in good standing in each jurisdiction in which the nature of the business conducted by it or the character or location of the properties and assets owned or leased by it makes such licensing or qualification necessary, except where failure to be qualified, licensed or in good standing has not had and would not reasonably expected to have a Material Adverse Effect on [Party] [or prevent or materially delay consummation of the transaction].");
        new OrganizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Organization"));


    }


    @Test
    public void testAuthorizationExtractor(){

        AnalysisFragment f;
        AnalysisOutcome o = new AnalysisOutcome();
        FeatureDefinition d;
        f = new AnalysisFragment("[Party] has the authority to enter into the Agreement and to perform the obligation required.\n");
        new AuthorizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Authorization"));


        f = new AnalysisFragment("The execution and delivery of this Agreement by the [Party] and the consummation by the [Party] of the transactions contemplated by this Agreement have been duly authorized by all necessary corporate action on the part of the [Party] and no other corporate proceedings on the part of the [Party] are necessary to authorize this Agreement or to consummate the transactions contemplated hereby, subject, in the case of the consummation of the Merger, to the obtaining of the Stockholder Approval.");
        new AuthorizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Authorization"));


        f = new AnalysisFragment("This Agreement has been duly executed and delivered by the Company and, assuming the due authorization, execution and delivery by each of the other parties hereto, constitutes a legal, valid and binding obligation of the Company, enforceable against the Company in accordance with its terms, subject to bankruptcy, insolvency, moratorium, reorganization or similar laws affecting the rights of creditors generally and the availability of equitable remedies.\n");
        new AuthorizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Authorization"));

        f = new AnalysisFragment("This Agreement constitutes a valid and legally binding agreement of the Company, enforceable in accordance with its terms and conditions, subject to Applicable Laws of general application relating to public policy, bankruptcy, insolvency and the relief of debtors and Applicable Laws governing specific performance, injunctive relief and other equitable remedies..");
        new AuthorizationExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertThat(d.getType().getName(), is( "Authorization"));

    }

    
   
    
    /* TODO: Fix and put back
     
    
    @Test
    public void testBoardApprovalExtractor(){

        Fragment f;
        AnalysisOutcome o;
        FeatureDefinition d;

        f = new Fragment("The Board of Directors of Company has approved this Agreement");
        new ApprovalExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ApprovalExtractor);

        f = new Fragment("The Board of Directors determined that this Agreement is advisable and in the best interests of the stockholders of Company.");
        new ApprovalExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ApprovalExtractor);

        f = new Fragment("The Board of Directors recommended that the stockholders of Company approve this Agreement and consummation of the Merger.");
        new ApprovalExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ApprovalExtractor);

        f = new Fragment("The Board of Directors will submit the Agreement to the Stockholders for their approval.");
        new ApprovalExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ApprovalExtractor);

    }


    @Test
    public void testGovernmentApprovalExtractor(){

        Fragment f;
        AnalysisOutcome o;
        FeatureDefinition d;

        f = new Fragment("No consent, authorization, order or approval of, or filing or registration with, or notification to any court, administrative agency or commission or other governmental authority or instrumentality is required by the Seller in connection with the execution, delivery and performance by the Seller of this Agreement or the consummation by the Seller of the transactions contemplated hereby. ");
        new ApprovalExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ApprovalExtractor);


        f = new Fragment("The affirmative vote of the holders of [a majority / two-thirds / other percentage] of the outstanding shares of Common Stock entitled to vote on this Agreement is the only vote of the holders of securities of the Company necessary to approve this Agreement. ");
        new ApprovalExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ApprovalExtractor);

    }


    @Test
    public void testClickWrapExtractor(){

        Fragment f;
        AnalysisOutcome o;
        FeatureDefinition d;

        f = new Fragment("YOU AGREE TO BE BOUND BY THE TERMS OF THIS EULA BY INSTALLING, COPYING, OR OTHERWISE USING THE SOFTWARE.");
        new ClickWrapExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ClickWrapExtractor);

        f = new Fragment("IF YOU DO NOT AGREE, DO NOT INSTALL, COPY, OR USE THE SOFTWARE; YOU MAY RETURN IT TO YOUR PLACE OF PURCHASE FOR A FULL REFUND, IF APPLICABLE.");
        new ClickWrapExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ClickWrapExtractor);

        f = new Fragment("By selecting the accept option, breaking the seal on the software package or installing, copying or otherwise using this Software You acknowledge that You have read, understand, and agree to be bound by the terms of this End-User License Agreement.");
        new ClickWrapExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof ClickWrapExtractor);

    }

    @Test
    public void testOwnershipExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;
        FeatureDefinition d;

        f = new Fragment("[Disclosing Party][Licensor] retains all proprietary rights to the [information][Licensed Property].");
        new OwnershipExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof OwnershipExtractor);

        f = new Fragment("No license, express or implied, is granted other than to use the information in the manner and to the extent authorized in this agreement.");
        new OwnershipExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof OwnershipExtractor);


    }

    @Test
    public void testCharterExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("The Company Charter and Company Bylaws as most recently filed as exhibits to the Company SEC Reports are in full force and effect");
        new CharterExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof CharterExtractor);

        f = new Fragment("The Company has delivered or made available to [Party] true, correct, and complete copies of the certificate of " +
                "incorporation and bylaws of the Company, including all amendments thereto. The Company is not in violation of its " +
                "certificate of incorporation or bylaws[ or equivalent organizational documents].");
        new CharterExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof CharterExtractor);


    }


    @Test
    public void testSubsidiaryExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("The Disclosure Schedule lists each Subsidiary and identifies the jurisdiction of formation and names of the officers and directors of each Subsidiary.");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);


        f = new Fragment("Each Subsidiary is a corporation, partnership or limited liability company duly incorporated or organized, validly existing and in good standing under the laws of its jurisdiction of incorporation or organization.");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);

        f = new Fragment("Copies of the organizational documents of each such Subsidiary, in each case as amended to date, have been made available to [Party], are complete and correct. No amendments are pending");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);

        f = new Fragment("None of the Subsidiaries own any capital stock or other securities of, or any proprietary interest in, any Person or entity");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);

        f = new Fragment("Except for the capital stock of, or voting securities or equity interests in, its Subsidiaries, the Company does not own, directly or indirectly, any capital stock of, or voting securities or equity interests in, any corporation, partnership, joint venture, association or other entity");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);

        f = new Fragment("All the outstanding shares of capital stock of, or other equity interests in, each Subsidiary of the Company have been validly issued and are fully paid and non-assessable and are owned, directly or indirectly, by the Company free and clear of all pledges, liens, charges, mortgages, encumbrances or security interests of any kind or nature whatsoever (collectively, \"Liens\"), other than Permitted Liens.");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);

        f = new Fragment("Each Subsidiary is duly licensed or qualified to do business as a foreign corporation, partnership or limited liability company, as applicable, in each other jurisdiction in which the character of its properties or in which the transaction of its business makes such qualification necessary, except where the failure to be so licensed or qualified would not, individually or in the aggregate, have a Material Adverse Effect");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);

        f = new Fragment("Each Subsidiary has all requisite corporate power and authority to own, operate, lease and encumber its properties and carry on its business as currently conducted, except as would not, individually or in the aggregate, have a Material Adverse Effect");
        new SubsidiaryExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof SubsidiaryExtractor);


    }

    @Test
    public void testBooksExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("The minute books, books of account, stock records, and other corporate and financial records of the Seller [all of which have been made available to the Buyers] are [to the knowledge of Seller] complete and correct [in all material respects], have been maintained in accordance with reasonable business practices for companies similar to the Seller, [and are stated in reasonable detail and accurately and fairly reflect the transactions and dispositions]. ");
        new BooksExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof BooksExtractor);

    }


    @Test
    public void testInvoiceTermsExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment(" Invoices shall be in a form approved by .");
        new InvoiceTermsExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof InvoiceTermsExtractor);

        f = new Fragment(" The Vendor will invoice Buyer monthly in accordance with ");
        new InvoiceTermsExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof InvoiceTermsExtractor);

        f = new Fragment("Vendor shall not invoice Buyer, and Buyer will not be obligated to pay, any charges that are not properly invoiced within three months after the end of the month to which the charges correspond.");
        new InvoiceTermsExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof InvoiceTermsExtractor);

        f = new Fragment("Except in the case of a delay in issuing invoices owing to a dispute between the Parties regarding Services provided, or any other default on the part of Buyer, Seller shall not be entitled to issue invoices to Buyer in respect of Services (and taxes associated with such Services) provided by Buyer more than three months previously or such other period separately agreed between the parties.");
        new InvoiceTermsExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof InvoiceTermsExtractor);

    }
    @Test
    public void testTermExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("This Agreement shall become effective on the Effective Date and remain in full force and effect for two (2) years from the Commencement Date (the “original Term”). Six (6) months prior to the Expiration Date, the Vendor shall propose terms to Buyer for renewing the Agreement.");
        new TermExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TermExtractor);

        f = new Fragment("If the Parties are unable to agree upon the terms and conditions for the renewal of the Agreement ninety (90) days prior to the Expiration Date, Buyer may elect to extend the Term for a period of time designated by Buyer of up to twelve (12) months from the expiration date at the then-current terms and conditions.");
        new TermExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TermExtractor);

        f = new Fragment("Buyer will have the right to extend the Agreement for up to two (2) additional successive periods of up to one (1) year each at the then current terms and conditions.");
        new TermExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TermExtractor);


    }

    @Test
    public void testTerminationForCauseExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("BUYER may terminate the Agreement, in whole or in part, if the Vendor (i) breaches in any material respect any of its duties or obligations under the Agreement and fails to cure such breach within thirty (30) days after notice; (ii) materially breaches any duty or obligation under the Agreement which is not capable of being cured within thirty (30) days; or (iii) commits numerous breaches of its duties or obligations under the Agreement which in the aggregate are material; or (iv) files or is declared bankrupt or goes or is put into liquidation (otherwise than solely for the purpose of amalgamation or reconstruction) or if a receiver is appointed over any part of Vendor’s business or if an administration order is made in respect of the Vendor. ");
        new TerminationForCauseExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TerminationForCauseExtractor);

        f = new Fragment("If BUYER chooses to terminate the Agreement in part, the charges payable under the Agreement will be equitably adjusted to reflect those services that are not terminated. BUYER shall not be liable for any termination fees associated with a Termination for Cause, other than payment for Services delivered through the effective date of the termination.");
        new TerminationForCauseExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TerminationForCauseExtractor);

        f = new Fragment("The Vendor may terminate the Agreement if and only if BUYER breaches in a material respect its duty under the Agreement to pay undisputed charges or in a material respect any other duty under the Agreement, and fails to cure such breach within sixty (60) days after notice.");
        new TerminationForCauseExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TerminationForCauseExtractor);


    }
    @Test
    public void testTerminationForConvenienceExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("BUYER may terminate the Agreement for convenience at any time after 12 months from the Effective Date in whole or in part by giving a nine (9) months written notice. Termination fees for Termination for Convenience are set out in");
        new TerminationForConvenienceExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TerminationForConvenienceExtractor);

        f = new Fragment("If BUYER is proposing to only terminate part of the Agreement and the effect of such termination is not covered by other clauses of this Agreement then the Parties shall mutually agree the appropriate changes to the charges in ");
        new TerminationForConvenienceExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TerminationForConvenienceExtractor);

        f = new Fragment("Buyer may terminate the services in part or in whole on giving the Vendor 6 months notice without paying any termination fee.Buyer may provide Vendor with such a termination notice at any point after 6 months after the Commencement Date.");
        new TerminationForConvenienceExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof TerminationForConvenienceExtractor);


    }

    @Test
    public void testLimitationOfLiabilityExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("Each Party shall, for its part, be responsible for any direct damages caused to the other Party by breach of this Agreement. Neither Party shall be liable to the other for any indirect damages, including, but not limited to, lost profits, loss of savings or income or loss of trade incurred by the other Party and arising under or in connection with this Agreement, except for injury to persons, liability under the Clause (Confidentiality), or Clause (Indemnification), excluding the damages under the clause, or in cases of fraud, wilful misconduct or gross negligence.");
        new LimitationOfLiabilityExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof LimitationOfLiabilityExtractor);

        f = new Fragment("Each Party’s total liability per contract year arising under this Agreement shall be limited to the aggregate fees and charges paid under this Agreement during the preceding 12 months or if the Agreement has been valid less than twelve (12) months prior to the damage, the maximum amount of the compensation is calculated according to the average of the monthly invoicing during the validity of the Agreement which shall multiplied with twelve (12). This limitation of liability shall not be applicable to injury to persons, liability under Clause (Confidentiality), or Clause (Indemnification) excluding the damages under the clause, or in cases of fraud, wilful misconduct or gross negligence. ");
        new LimitationOfLiabilityExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof LimitationOfLiabilityExtractor);


    }


    @Test
    public void testPaymentDaysExtractor(){

        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment(" Payment shall be due [PAYMENT DUE DAYS] business days after receipt of invoices.");
        new PaymentDaysExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof PaymentDaysExtractor);

        f = new Fragment(" Buyer will pay undisputed charges within ninety (90) days of receipt of invoice");
        new PaymentDaysExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof PaymentDaysExtractor);

        f = new Fragment("Unless Buyer disputes an invoice in accordance with Exhibit 32, correct Vendor invoices shall be paid to Vendor by Buyer within thirty (30) days of receipt of the invoice.");
        new PaymentDaysExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof PaymentDaysExtractor);

    }

    
//TODO: PLACEHOLDER MARK FOR WAIVEREXSTRACTOR TEST ONLY
    @Test
    public void testWaiverExtractor(){

// Agreement clauses: Multi Lateral Agreement clause


        Fragment f;
        AnalysisOutcome o; FeatureDefinition d;

        f = new Fragment("THIS [TYPE OF] AGREEMENT (\"Agreement\") is [made OR effective] as of [EFFECTIVE DATE] (the \"Effective Date\") and is between [PARTY1 NAME], with its [principal place of business OR place or residence] at [PARTY1 ADDRESS] (\"[PARTY1 ABBR]\") and [PARTY2 NAME], with its [principal place of business OR place or residence] at [PARTY2 ADDRESS] (\"[PARTY2 ABBR]\"), (together, the \"Parties\" and each, a \"Party\")");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Agreement clauses: Click Wrap Agreement clause 

        f = new Fragment("This End-User License Agreement (\"EULA\") is a legal agreement between you (either an individual or a single entity) and Microsoft Corporation for the Microsoft software that accompanies this EULA, which includes computer software and may include associated media, printed materials, \"online\" or electronic documentation, and Internet-based services (\"Software\").\n" +
                "\n" +
                "YOU AGREE TO BE BOUND BY THE TERMS OF THIS EULA BY INSTALLING, COPYING, OR OTHERWISE USING THE SOFTWARE.\n" +
                "\n" +
                "IF YOU DO NOT AGREE, DO NOT INSTALL, COPY, OR USE THE SOFTWARE; YOU MAY RETURN IT TO YOUR PLACE OF PURCHASE FOR A FULL REFUND, IF APPLICABLE.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Activation—Click Though\n" +
                "\n" +
                "By selecting the accept option, breaking the seal on the software package or installing, copying or otherwise using this Software You acknowledge that You have read, understand, and agree to be bound by the terms of this End-User License Agreement.\n" +
                "\n" +
                "Statement of Copyright\n" +
                "\n" +
                "This Software is protected by copyright laws and international copyright treaties, as well as other intellectual property laws and treaties.\n" +
                "\n" +
                "Authority\n" +
                "\n" +
                "If you are accepting on behalf of your employer or another entity, you represent and warrant that you have full legal authority to bind your employer or such entity to these Terms and Conditions. If you do not have the legal authority to bind, or do not wish to be bound by these Terms and Conditions, please press the \"I do not accept\" button below.\"\n" +
                "\n" +
                "Similar language can be found in Google Maps Terms and Conditions.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);



    // Bargain clauses: Type Performance Agreements: Employment



        f = new Fragment("Employment. Employer employs the Employee and Employee accepts such employment upon the terms and conditions of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Employment at Will\n" +
                "\n" +
                "Employment at Will. Company employs Employee, and Employee accepts such employment as an at-will employee. This employment may be terminated at any time for any reason with or without \"Cause\" by the Company or the Employee.\n" +
                "\n" +
                "1.2 Continued Employment\n" +
                "\n" +
                "(a) Continued Employment. Company agrees to continue to employ Employee, and employee agrees to such continued employment upon the terms and conditions set forth in this Agreement.\n" +
                "\n" +
                "1.3 Concurrent Employment\n" +
                "\n" +
                "Concurrent Employment. During the term of this Agreement, Employee and the Company acknowledge that Employee may be concurrently employed by the Company and a subsidiary or an \"Affiliate\", and that all of the terms and conditions of this Agreement shall apply to such concurrent employment. Reference to the Company in this Agreement includes any such concurrent employers.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        // Bargain clauses: Type Performance Agreements: Engagement Consulting

        f = new Fragment("Engagement. [Effective [CONSULTING START DATE], ]Company retains the Consultant and the Consultant agrees to provide Company with [[DESCRIPTION OF SERVICES]/consulting services described in the attached Statement of Work] (the \"Services\").");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Bargain clauses: Type Performance Agreements: Engagement Consulting within Marketing and Public Relations

        f = new Fragment("Consulting Services. Consultant services to Company shall include services customarily performed by a general public relations and marketing services agency, including, but not limited to, the following:\n" +
                "(a) Increasing public awareness of the Brands.\n" +
                "(b) Promoting the design breadth of Company patterns, motifs and colors to consumers and retailers [and to interior designers] as well as to the photo stylists who work with editors.\n" +
                "(c) Reinforcing the Brands image as a reliable, visionary resource for information and products concerning home design, and reinforcing Company reputation for service, quality and innovative marketing to the trade press.\n" +
                "(d) Promoting Company marketing, advertising and public relations success to Company sales force as well as to retailers and the media.\n" +
                "(e) Purchasing all materials and services necessary for the production of press kits and other promotional brochures and materials, unless otherwise supplied by us. (f) Developing, planning and coordinating press events and other special events. (g) Developing, planning and coordinating designer seminars and contests. (h) Paying all charges incurred and assumed by Consultant on Company behalf. (i) Endeavoring to do all of the above on the most advantageous rates, terms, and conditions available.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Bargain clauses: Type Performance Agreements:Financial and Advisory Services

        f = new Fragment("Consulting Services. Consultant will perform such of the following financial advisory services on the Company's behalf as the Company may reasonably request:\n" +
                "(a) Make initial contact with [THIRD PARTY] to gauge interest in pursuing a possible Transaction;\n" +
                "(b) Consultant will familiarize itself to the extent it deems appropriate and feasible with the business, operations, properties and financial condition of the [THIRD PARTY];\n" +
                "(c) If the Company decides to pursue a Transaction, Consultant will assist the Company in preparing a term sheet inclusive of advising on structure, terms and pricing;\n" +
                "(d) Consultant will negotiate the financial aspects of any proposed Transaction under the [Consultant's guidance; and \n" +
                "(e) Consultant will render such other financial advisory and investment-banking services as may from time to time be agreed upon by Consultant and the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Bargain clauses: Type Performance Agreements: Business Services

        f = new Fragment("Consulting Services. Consultant shall render to the Company services consist of:\n" +
                "(a) developing an in-depth familiarization with the Company's business objectives and bring to its attention potential or actual opportunities which meet those objectives or logical extensions thereof;\n" +
                "(b) advising the Company with respect to its corporate development including such factors as position in competitive environment, financial performances vs. competition, strategies, operational viability, etc.;\n" +
                "(c) preparing a business plan with respect to the Company and its business;\n" +
                "(d) assisting in the structuring of the filing of a registration statement with the Securities and Exchange Commission (SEC);\n" +
                "(e) assisting in the preparation of all necessary documentation in connection with the listing of the Company on a national securities exchange, the Over the Counter Bulletin Board or the Pink Sheets, as determined by the Company; and\n" +
                "(f) any and all services in connection with the foregoing. In connection with said services, the Company agrees to fully cooperate with the Consultant in connection with providing all necessary information, documentation and the time of the executive and management staff which shall be required. This shall include, without limitation, providing audited financial statements as required by the SEC and all due diligence material.\n" +
                "The services to be rendered by the Consultant to the Company shall under no circumstances include (a) any activities which could be deemed by the SEC to constitute investment banking or any other activities requiring the Consultant to register as a broker-dealer under the Securities Exchange Act of 1934; (b) any activities which could be deemed by the SEC to be in connection with the offer or sale of securities; or (c) any activities which directly or indirectly promote or maintain a market for the Company's securities.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


            //Bargain clauses: Type Performance Agreements:Severance

        f = new Fragment("Severance. In exchange for the release and covenant not to sue, the Company will pay Executive the Severance Benefits, subject to the terms and conditions of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

            //Bargain clauses: Type Performance Agreements:Provision of Information

        f = new Fragment("Provision of Information. The Parties agree to exchange Confidential Information under the terms and conditions of this Agreement.\n" +
                "\n" +
                "3.1 Included Information. The term \"Confidential Information\" means any material non-public information related to the Purpose of the disclosure and any derivative of that information.\n" +
                "\n" +
                "3.2 Excluded Information. The restrictions of this Agreement on use and disclosure of Confidential Information will not apply to:\n" +
                "\n" +
                "(a) Public Information. Information that is or becomes publicly known without the breach of this Agreement.\n" +
                "\n" +
                "(b) Already Known. Information that at the time of disclosure under this Agreement is already known to the Receiving Party without any restriction on its disclosure.\n" +
                "\n" +
                "(c) Third Party Source. Information that is or subsequently comes into the possession of the Receiving Party from a third party without violation of any contractual or legal obligation.\n" +
                "\n" +
                "(d) Independently Developed. Information that is independently developed by the Receiving Party without the use of Confidential Information or breach of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);



    //Bargain clauses: Type- License Agreements: License Grant-Technology 


        f = new Fragment("Grant of License. Licensor grants to Licensee a [worldwide], non-exclusive, non-transferable, [royalty-free][royalty-bearing] license to use the Licensed Technology in accordance with the terms of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Statement of Uses\n" +
                "\n" +
                "Grant of License. Subject to the terms and conditions of this Agreement, Licensor hereby grants to Licensee, under Licensor's intellectual property rights in and to the Licensed Technology, a worldwide, non-exclusive, non-transferable license: \n" +
                "(i) to integrate the Licensed Technology into Integrated Products; \n" +
                "(ii) to reproduce the Licensed Technology as so integrated into integrated Products; and \n" +
                "(iii) to distribute the Licensed Technology as integrated into Integrated Products solely to End-Users who are subject to an End-User License Agreement. Licensee shall make no use of any copies of the Licensed Technology except as provided in this Section. Licensee may sublicense the distribution rights granted under this Section solely as described in this Agreement. All rights not specifically granted herein shall be retained by Licensor.\n" +
                "\n" +
                "License Grant. BlueStacks grants Licensee a non-exclusive, non-transferable, worldwide and royalty bearing license to use, reproduce, have reproduced, perform, display and distribute the Programs, but solely: (a) for use with or incorporation into Licensee Products; and (b) for the number of Program copies paid for by Licensee at the per Program copy license fees specified in this Agreement. The licenses granted in this Agreement are subject to all terms, conditions, requirements, restrictions and limitations set forth in this Agreement. All rights not expressly granted are reserved by BlueStacks.\n" +
                "(Technology License and Services Agreement, August 18, 2011, [BlueStack Systems, Inc. and Nyxio Technologies Corporation])");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

                // Example

        f = new Fragment("Conveyance of Rights.\n" +
                "\n" +
                "License to Ceres: Monsanto Enabling Technologies Subject to the terms and conditions of this Agreement, including but not limited to Article 8.2, Monsanto grants to Ceres and Ceres Affiliates under Monsanto's and Monsanto Affiliates' interest in Monsanto Enabling Patent Rights, (a) a worldwide non-exclusive license to use Monsanto Enabling Technologies outside the Monsanto Exclusive Field, the Berry Field and the Immunoglobulin Field, and (b) a worldwide non-exclusive license to develop, make, have made, import, use, sell, have sold and offer to sell: i) plants that have been developed with the use of Monsanto Enabling Technologies that contain Traits outside the Monsanto Exclusive Field, the Berry Field and the Immunoglobulin Field and; ii) products produced from such plants, provided that such license shall not include the right to commercialize Drugs prior to September 1, 2002. Ceres and Ceres Affiliates shall not have the right to grant sublicenses under the license granted by this Article to Third Parties to use Monsanto Enabling Technologies (including the right to transform plants with vectors supplied by Ceres or Ceres Affiliates), but shall have the limited right to grant sublicenses under the license granted by this Article to Third Parties (i) to propagate, grow, harvest, use, sell, have sold and offer to sell plants that both contain Ceres Sequences or Licensed-in Sequences and have been developed or transformed by Ceres or Ceres Affiliates with the use of Monsanto Enabling Technologies including progeny of such plants, and (ii) to make, have made, import, use, sell, have sold and offer to sell products produced from such plants that contain Ceres Sequences or Licensed-in Sequences and progeny of such plants; further provided that in case of Licensed-In Sequences, then any such sublicense under this Article shall not be exclusive to the provider of the Licensed-In Sequences. Ceres and Ceres Affiliates shall be responsible for maintaining sufficient records on the use of Monsanto Enabling Technology so that plants can be identified. Ceres or Ceres Affiliates will notify Monsanto in writing as soon as reasonably possible after the grant of any sublicense of the name and address of the sublicensee, the scope of the sublicense and the Monsanto Enabling Technologies covered by this Agreement which are included in the sublicense. Ceres agrees to grant and hereby grants a royalty-free, nonexclusive, worldwide license, with the right to grant sublicenses, to Monsanto and Monsanto Affiliates to any Ceres Improvements.\n" +
                "\n" +
                "License to Ceres for research purposes only: Monsanto Enabling Technology. Subject to the terms and conditions of this Agreement, Monsanto grants to Ceres and Ceres Affiliates under Monsanto's and Monsanto Affiliates' interest in Monsanto Enabling Patent Rights, a worldwide non-exclusive royalty-free license to use Monsanto Enabling Technologies in the Monsanto Exclusive Field for research purposes in Contract Projects only.\n" +
                "\n" +
                "License to Ceres for research purposes only: Glyphosate resistance Subject to the terms and conditions of this Agreement, including, but not limited to Article 8.2, Monsanto grants to Ceres and Ceres Affiliates under Monsanto's and Monsanto Affiliates' interest in Glyphosate Patent Rights a U.S. only, a non-exclusive license to use Glyphosate Tolerance Genes in Arabidopsis for research purposes only.\n" +
                "\n" +
                "Biological Materials. Monsanto agrees to provide Ceres within thirty (30) days of any reasonable request by Ceres, to the extent available, representative samples of materials and data necessary and useful for Ceres to exercise and enjoy any of the rights and licenses granted to Ceres pursuant to Article 2.1. Any such deliveries of Biological Materials to Ceres from Monsanto shall be subject to a materials transfer agreement in the form set forth in Exhibit E.\n" +
                "(Enabling Technology License Agreement, August 29, 2011, [Ceres, Inc. and Monsanto Company])");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Bargain clauses: Type- License Agreements: License Grant-SW EULA

        f = new Fragment("Grant of License. Licensor grants to Licensee a limited, non-transferable, royalty-free license to use the Licensed Technology in accordance with the terms of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("License, Use, Platform, and Purpose\n" +
                "\n" +
                "Grant of License. The [licensor] grants [licensee] a limited, nonexclusive license [royalty-free], [non-transferable], [non-sub licensable], [non-commercial], and [non-assignable] to use the [software]:\n" +
                "(a) solely in executable or [object code] [machine-readable] form\n" +
                "(b) on a single computer [on a single server] [on up to the number of computers or servers as authorized in this Agreement]\n" +
                "(c) for your personal and non-commercial use.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

            // Bargain clauses: Type- License Agreements: SW license restrictions

        f = new Fragment("Restrictions on Use\n" +
                "\n" +
                "(a) Distribution. Licensee may not distribute, license, loan or sell the Software or other content contained or displayed in the Software. (b) Modification. Licensee may not modify, alter or create any derivative works of the Software. (c) Reverse Engineering. Licensee may not reverse engineer, decompile, decode, decrypt, disassemble, or in any way derive source code from the Software (d) Proprietary Notices. Licensee may not remove, alter or obscure any copyright, trademark or other proprietary rights notice on or in the Software.");
        new WaiverExtractor().classify( f, o, tree );

           // Bargain clauses: Type- License Agreements: Additional SW license restritions

        f = new Fragment("Proprietary Notices: \"Licensee may not remove, alter or obscure any proprietary notice that appears on the Software or on any copies made in accordance with this Agreement.\"\n" +
                "\n" +
                "Notify Others: \"Licensee agrees to notify your employees and agents with access to the Software of the limitations set forth in this Agreement.\"\n" +
                "\n" +
                "Competition: \"Licensee may not use the Licensed Products for the purposes of competing with the licensor, including without limitation competitive intelligence.\"\n" +
                "\n" +
                "Confidentiality: \"Licensee may not publish or disclose the results of any benchmarking of the Software, or use such results for any other software development activities.\"\n" +
                "\n" +
                "Personal or Specified Use: \"Licensee may not use the Software or the Software Content for any purpose other than your personal, noncommercial use.\"\n" +
                "\n" +
                "Restrictions on Export: \"Licensee may not use the Software, or allow the transfer, transmission, export or re-export of all or any part of the Software or any product thereof, in violation of any export control laws or regulations of the United States or any other relevant jurisdiction.\"\n" +
                "\n" +
                "Compliance with Laws: \"Licensee may not use the Software in a manner that violates any applicable local, state, national or international law or governmental regulation, policy procedure or ordinance, or any rights of a third party.\"\n" +
                "\n" +
                "Terminated License: \"Licensee may not use the Software if this license has been terminated by licensor.\"\n" +
                "\n" +
                "Policies. \"Licensee may not use the Software in a manner that violates the Terms of Service or Privacy Policy.\"\n" +
                "\n" +
                "Dangerous Use: \"THE IPHONE SOFTWARE AND IPHONE SOFTWARE UPDATES ARE NOT INTENDED FOR USE IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL SYSTEMS, LIFE SUPPORT MACHINES OR OTHER EQUIPMENT IN WHICH THE FAILURE OF THE IPHONE SOFTWARE OR IPHONE SOFTWARE UPDATES COULD LEAD TO DEATH, PERSONAL INJURY, OR SEVERE PHYSICAL OR ENVIRONMENTAL DAMAGE.\"");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

      //Bargain clauses: Type- License Agreements: License Grant Property


        f = new Fragment("Lease of Premises. Lease of Premises. Landlord leases the Premises to Tenant and Tenant leases the Premises from Landlord for the Term, on and subject to the covenants, terms, and conditions of this Lease.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Acceptance\n" +
                "\n" +
                "Acceptance. Except as otherwise specifically set forth in this Lease, Tenant acknowledges and agrees that:\n" +
                "\n" +
                "(a) neither Landlord nor any agent of Landlord has made any representation or warranty regarding the condition of the Premises, the Building or the Project or with respect to the suitability of any of the foregoing for the conduct of Tenant s business or the Permitted Use, \n" +
                "\n" +
                "(b) Landlord has no obligation and has made no promises to alter, remodel, improve, renovate, repair or decorate the Premises, the Building, the Project or any part thereof, and \n" +
                "\n" +
                "(c) Tenant shall accept the Premises, the Building and the Project in their AS IS condition as of the Lease Commencement Date.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Common Areas. Tenant and the Tenant Entities will be entitled to the non-exclusive use of the common areas of the Building as they exist from time to time during the Term, including the parking facilities, subject to Landlord's rules and regulations regarding such use.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Parking. During the term of this Lease, Tenant shall have the non-exclusive use in common with Landlord, other tenants of the Building, their guests and invitees, of the non-reserved common automobile parking areas, driveways, and footways, subject to rules and regulations for the use thereof as prescribed from time to time by Landlord. Landlord reserves the right to designate parking areas within the Building or in reasonable proximity thereto, for Tenant and Tenant's agents and employees.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);



    // Exchange: Type: Performance Agreements: Employment:Terms of Employment


        f = new Fragment("Terms of Employment.\n" +
                "\n" +
                "(a) Duties and Responsibilities. Employee shall serve as [TITLE OR POSITION] and perform such other duties as the Company may from time to time direct.\n" +
                "\n" +
                "(b) Reporting. Employee shall report to [TITLE OF REPORTING POSITION].\n" +
                "\n" +
                "(c) Place of Performance. Employee shall perform the duties at the principal offices of the Company[, subject to reasonable travel requirements as authorized and directed from time to time by the Company\n" +
                "\n" +
                "(d) Dedication. Employee shall devote all Employee's business time to the performance of the employment duties.\n" +
                "\n" +
                "(e) Performance. Employee shall perform the duties faithfully, diligently [and to the best of Employee's capacity OR competently].\n" +
                "\n" +
                "(f) Permitted Activities. Employee shall be entitled to:\n" +
                "(i) serve on industry, trade, civic or charitable boards or committees;\n" +
                "(ii) engage in charitable activities and community affairs; and\n" +
                "(iii) manage personal investments and affairs;\n" +
                "provided that such activities do not materially interfere with the proper performance of the duties and responsibilities under this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Modification of Duties by Mutual Consent\n" +
                "\n" +
                "Duties and Responsibilities. Employee will serve as [POSITION OR TITLE], or in such other capacity mutually agreed between Employee and the Company[ by written amendment of this Agreement].\n" +
                "\n" +
                "Duties and Responsibilities—Definition of Duties as Customary for Position\n" +
                "\n" +
                "Duties and Responsibilities. Employee will serve as [POSITION OR TITLE] and perform all duties and exercise such authority customarily performed and held by persons holding equivalent positions in companies similar in nature and size to the Company as reasonably defined, modified and delegated from time to time by the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
//TODO: java.lang.RuntimeException: Did not understand the posAnalysis '' for Dedication

//        f = new Fragment("Employee Reporting to CEO and Board\n" +
                "\n" +
                "Reporting. Employee will report to the CEO as to day to day matters and to the Board as to fiduciary duties and on other matters as requested by the Board.\n"+
                "\n" +
                "Domestic and International Travel\n" +
                "\n" +
                "Place of Performance. Employee shall be based in [PRINCIPAL WORK LOCATION]. Employee recognizes that the duties will require, from time to time and at the Company's expense, travel to domestic and international locations in accordance with the Company's standard policies.\n" +
                "\n" +
                "No Forced Relocation\n" +
                "\n" +
                "Place of Performance. Employee shall not be required to relocate Employee's residence during the Employment Term without Employee's consent.\n" +
                "Dedication: Full-Time\n" +
                "\n" +
                "Dedication. Employee shall be employed on a full-time basis.\n" +
                "\n" +
                "Dedication Part-Time\n" +
                "\n" +
                "Dedication. Employee shall be employed on a part-time basis, comprising a minimum of [HOURS PER WEEK COMMITMENT].\n" +
                "Benchmarked to the Employee- Best Efforts\n" +
                "\n" +
                "Dedication. Employee agrees to use Employee's best efforts to advance the business and welfare of the Company, and to serve the Company faithfully, diligently and to the best of Employee's ability.\n" +
                "\n" +
                "Benchmarked to the Employment Position- Competence\n" +
                "\n" +
                "Dedication. Employee shall perform the duties faithfully, diligently and competently [or skillfully]\n" +
                "No Outside Activities Without Company Consent\n" +
                "\n" +
                "Outside Activities. Employee may not, during the term of this Agreement, be engaged in any other business activity without the prior written consent of the Company; provided, that this restriction shall not prevent Employee from investing personal assets or performing business entities that are not in competition with the Company or its affiliates.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        //Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits ->Compensation: Base Salary


        f = new Fragment("Base Salary.\n" +
                "\n" +
                "(a) Salary. Employee shall receive a base salary in the amount of [COMPENSATION] (\"Base Salary\").\n" +
                "\n" +
                "(b) Payment. The Base Salary shall be payable in accordance with the customary payroll practices of the Employer, [but in no event less frequently than monthly].\n" +
                "\n" +
                "(c) Adjustment. The Base Salary may be increased [or decreased] from time to time during the term of this Agreement at the [sole] discretion of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Fixed Increases\n" +
                "\n" +
                "For all services to be rendered by the Executive during the Term and in consideration of the Executive's representations and covenants set forth in this Agreement, the Executive shall receive from the Company the following base salary per annum (\"Base Salary\"):\n" +
                "(i) [Period 1 Amount] from the Effective Date through [Period 1 End Date];\n" +
                "(ii) [Period 2 Amount] from the [Period 2 Start Date] through [Period 2 End Date];\n" +
                "(iii) [Period 3 Amount] from the [Period 3 Start Date] through [Period 3 End Date]; and\n" +
                "(iv) [Period 4 Amount] from the [Period 4 Start Date] through [Period 4 End Date];\n" +
                "\n" +
                "Percentage Increases\n" +
                "\n" +
                "On [ANNUAL ADJUSTMENT DATE] of each year during the Term,\n" +
                "(i) Employee's Base Salary shall increase by [PERCENTATGE INCREASE] and\n" +
                "(ii) the Company shall review Employee's performance and may make such additional increases to the Base Salary in its sole discretion.\n" +
                "\n" +
                "Performance Goals\n" +
                "\n" +
                "If the employee achieves the incentive goals set for Employee, the Base Salary for will be increased to [fixed amount or percentage increase].\n" +
                "\n" +
                "Cost of Living Increase\n" +
                "\n" +
                "The Base Salary shall be increased by a percentage no less than the annual increase of the cost of living index for the [National or local COL index].\n" +
                "\n" +
                "Discretionary—Alternative 1\n" +
                "\n" +
                "Base salary shall be reviewed no less frequently than annually and may be increased [but not decreased] in the sole discretion of the Company.\n" +
                "\n" +
                "Discretionary—Alternative 1\n" +
                "\n" +
                "From time to time during the Term, the Company will review the Base Salary and may make such upward adjustments, if any, as the Company, in its sole discretion determines to be appropriate in light of the performance of the Employee.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits ->Compensation: Signing Bonus



        f = new Fragment("Signing Bonus.[Upon execution of this Agreement,] Employer shall pay to Employee an initial signing bonus of [signing bonus amount].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional ProvisionsSigning Bonus with Clawback\n" +
                "\n" +
                "(a) Bonus. Employee shall receive a signing bonus in the amount of [signing bonus amount], less all applicable withholdings, payable within [number of days] business days of the execution of this Agreement.\n" +
                "(b) Repayment. In the event that Employee voluntarily terminates his employment with Employer for any reason whatsoever or Employee's employment with Employer is terminated by Employer for \"Cause\" before the first anniversary of this Agreement, Employee will repay to Employer an amount equal to [signing bonus amount] multiplied by the fraction, the numerator of which is 365 less the number of days during which Employee was employed by Employer, and the denominator of which is 365. Such repayment shall be made by Employee in full within ninety (90) days of his termination of employment with Employer.\n" +
                "(c) Offset. Employee hereby authorizes Employer to immediately offset against and reduce any amounts otherwise due to him for any amounts in respect of the obligation to repay the sign-on bonus.\n" +
                "\n" +
                "Signing Bonus Payable in Installments with Clawback\n" +
                "\n" +
                "(a) Bonus. [Upon execution of this Agreement,] Employer shall pay to Employee an initial signing bonus of [signing bonus amount].\n" +
                "\n" +
                "(b) Installments. Employer shall pay a signing bonus in three installments as follows:\n" +
                "(i) [installment 1 amount] shall be paid within ten days after the parties' execution of this Agreement,\n" +
                "(ii) [installment 2 amount] shall be paid on [installment 2 payment date], and\n" +
                "(iii)[installment 3 amount] shall be paid on [installment 3 payment date]\n" +
                "(each individual payment, an \"Installment Signing Bonus Payment\" and the date each individual payment is scheduled to be made, an \"Installment Signing Bonus Payment Date\").\n" +
                "\n" +
                "Notwithstanding the foregoing, (i) Employee must be employed by Employer on the Installment Signing Bonus Payment Date to be eligible to receive the corresponding Installment Signing Bonus Payment, and (ii) in the event Executive's employment terminates for any reason prior to [installment 3 payment date], Executive shall repay to the Company within thirty days following such termination a prorated portion of the immediately preceding Installment Signing Bonus Payment paid to Executive, based on the number of days Employee was employed from such immediately preceding Installment Signing Bonus Payment Date to the date of termination, provided that Executive shall have no obligation to repay in whole or in part the initial Installment Signing Bonus Payment.\n" +
                "\n" +
                "Signing Bonus for Legal Expenses\n" +
                "\n" +
                "Employee shall receive a signing bonus in the amount of [SIGNING BONUS AMOUNT] to be paid on the Effective Date. Further, a maximum of [MAXIMUM LEGAL EXPENSE REIMBURSEMENT] shall be reimbursed to Employee for legal expenses incurred during the negotiation of this Agreement upon presentation of transaction receipts.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Compensation: Bonus

        f = new Fragment("Bonus. For each [fiscal/calendar] year during the term of employment, the Executive shall be eligible to receive a bonus in the amount, if any, as may be determined from time to time by the Board in its discretion.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Discretionary Bonus\n" +
                "\n" +
                "(a) Bonus. The Company may pay the Employee a bonus if, in the sole judgment of the Company, the earnings of the Company and/or the services of the Employee merit such a bonus.\n" +
                "\n" +
                "Fixed Bonus Amounts\n" +
                "\n" +
                "(a) Bonus. The Company will pay Executive a cash retention bonus in accordance with the following schedule, provided that Executive has remained a full-time employee of the Company during such period and has faithfully and diligently fulfilled his responsibilities and complied in all material respects with his obligations to the Company:\n" +
                "(i) Year Ending [date]: [bonus amount]\n" +
                "(ii) Year Ending [date]: [bonus amount]\n" +
                "(iii) Year Ending [date]: [bonus amount]\n" +
                "(iv) Year Ending [date]: [bonus amount]\n" +
                "(v) Year Ending [date]: [bonus amount]\n" +
                "The retention bonus shall be paid within [number] days after the Year ending period applicable to each bonus.\n" +
                "\n" +
                "Target Percentage Bonus\n" +
                "\n" +
                "(a) Bonus Award. Executive shall be entitled to receive bonuses, cash or otherwise, in the discretion of the Compensation Committee, from time to time.\n" +
                "(b) Bonus Target. Such bonuses will be targeted at [percentage amount]% of Executive's Annual Salary each year, and will be based upon the achievement of performance targets and goals established by the Compensation Committee.\n" +
                "(c) Bonus Payment. Bonuses shall be paid in accordance with the terms of the Company's Executive Incentive Plan, as may be amended from time to time, such other plan as may be adopted by the Compensation Committee, or as otherwise provided by the Board or the Compensation Committee.\n" +
                "\n" +
                "First Year Percentage; Subsequent Years Range\n" +
                "\n" +
                "(a) First Year Bonus. For fiscal year [year], Executive is entitled to receive a bonus equal to [percentage amount]% of Base Salary, prorated based on the number of days actually employed during fiscal year [year], payable to Executive in a lump sum after the end of the fiscal year.\n" +
                "(b) Subsequent Year Bonus. For each fiscal year following fiscal year [year] during the Employment Period, Executive shall be eligible to receive an annual bonus, with the annual bonus potential to be between [minimum percentage amount]% of Base Salary and a maximum of [maximum percentage amount]% of Base Salary, with the \"target\" and \"maximum\" performance goals and bonus criteria to be defined and approved by the Compensation Committee in advance for each fiscal year. The Company shall pay any such annual bonus earned to Executive in a lump sum after the end of the fiscal year.\n" +
                "\n" +
                "Bonus Based On Stated Performance Goals—Alternative 1\n" +
                "\n" +
                "(a) Bonus. Employee shall be eligible to receive a yearly bonus of up [bonus amount], based upon the Employee satisfactorily reaching various budget, financial and other criteria that are established for each calendar year in question. Employee shall only be eligible for such a bonus if he is employed on the last day of the calendar year to which the bonus applies. The Bonus amount can be reviewed by Employer annually.\n" +
                "\n" +
                "Bonus Based On Stated Performance Goals—Alternative 2\n" +
                "\n" +
                "(a) Bonus. Executive shall be eligible to receive an annual cash bonus (the Performance Bonus\") for each Term Year. The Performance Bonus, if any, will be based on the extent to which individual and Company-wide performance goals established by the Board of Directors for each Term Year have been met. Each Performance Bonus, if any, shall be paid on the [payment date] following the completion of the applicable Term Year.\n" +
                "\n" +
                "Bonus Based On Company Earnings—Alternative 1\n" +
                "\n" +
                "(a) During the Employment Period, Employee shall be entitled to receive a bonus (the \"Bonus\") based upon the relationship of actual \"EBITDA\" (hereinafter defined) for a fiscal year to budgeted EBITDA as established by the Board for such fiscal year. If actual EBITDA equals budgeted EBITDA for a fiscal year, the bonus would be an amount equal to 60% of Employee's then Base Salary with the bonus increasing by an amount equal to 2% of Base Salary for each $1 million by which actual EBITDA exceeds budgeted EBITDA for such fiscal year, provided that in no event shall the bonus for any fiscal year exceed $400,000 (or Employee's Base Salary should the Base Salary be increased as herein provided).\n" +
                "(b) \"EBITDA\" shall mean earnings before interest, taxes, depreciation and amortization, as determined in accordance with generally accepted accounting principles, applied on a consistent basis. Actual EBITDA shall be derived from the Employer's audited financial statements by its independent certified public accountants.\n" +
                "(c) In no event shall Employee be entitled to a bonus for any fiscal year unless Employee shall be employed on the last day of such fiscal year unless (i) Employee has been terminated without cause during the Term or(ii) it shall be determined by arbitration under Section 16 that Employer wrongfully discharged Employee.\n" +
                "\n" +
                "Bonus Based On Company Earnings—Alternative 2\n" +
                "\n" +
                "During the Employment Period, Employee shall be entitled to receive a bonus (the \"Bonus\") based upon the change in the EBIT (as defined below) between the current fiscal year and the fiscal year immediately preceding fiscal year. Based on such EBIT Growth, and upon the Compensation Committee's certification of the achievement of any such EBIT Growth, Executive shall be paid a cash bonus (the Annual Bonus\") as follows:\n" +
                "(a) If the Company's EBIT Growth is less than 10%, Executive shall be paid a cash bonus of 2% of the EBIT Growth;\n" +
                "(b) If the Company's EBIT Growth is greater than 10% and less than 20%, Executive shall be paid a cash bonus of 3% of the EBIT Growth; and\n" +
                "(c) If the Company's EBIT Growth is greater than 20%, Executive shall be paid a cash bonus of 5% of the EBIT Growth.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        //Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Compensation: Incentive Compensation

        f = new Fragment("Incentive Compensation. The Executive shall be entitled to participate in all cash incentive, equity incentive, savings and retirement plans, practices, policies, and programs applicable generally to other [senior] executives of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Alternative clauses Equal Availability\n" +
                "\n" +
                "Long-Term Incentive Plans. The Executive shall participate in long-term incentive plans including all stock option plans and other long-term incentive plans the Company may adopt from time to time on a basis no less favorable than that provided to any other executive officers of the Company.\n" +
                "\n" +
                "No Less Favorable than Original Terms\n" +
                "\n" +
                "Long-Term Incentive Plans. The Executive shall participate in long-term incentive plans including all stock option plans and other long-term incentive plans the Company may adopt from time to time: (a) on a basis no less favorable than that provided to any other executive officers of the Company, and (b)at least comparable to those provided to the Executive immediately before the Effective Date.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


     //Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Benefits



        f = new Fragment("Benefits. During the term of Employment, the Executive shall be entitled to participate in employee benefit plans generally made available to [senior] executives of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Benefits. The Executive shall be eligible to participate in and receive all fringe benefits available under all benefit programs normally available to employees of the Company holding positions similar to that of the Executive, as may be in effect from time to time, including such pension, profit sharing, stock option, life insurance, disability insurance, health insurance and dental insurance plans and any other benefits and plans as may be implemented by the Company from time to time.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
// TODO: Does not understand the Vacation word in this fragment
        f = new Fragment("Stated Vacation Leave\n" +
                "\n" +
                "Vacation. Employee shall be entitled to [VACATION ALLOWANCE] of vacation with pay during each calendar year during the period of employment in accordance with the Employer's applicable personnel policy as in effect from time to time.\n" +
                "\n" +
                "Combined Vacation, Illness and Disability Leave\n" +
                "\n" +
                "Vacation, Sick Leave and Disability. Employee shall be entitled to [VACATION ALLOWANCE] days vacation annually and shall be entitled to the same personal time and sick leave, and disability leave as other employees of the Company.\n" +
                "\n" +
                "Favorable Vacation Leave\n" +
                "\n" +
                "(a) Vacation. During the Employment Period, the Executive shall be entitled to paid vacation in accordance with the most favorable plans, policies, programs and practices of the Company and the Affiliated Companies as in effect generally at any time after the Effective Date with respect to other senior executives of the Company; provided, that in no event shall the Executive be entitled to less than [number of] weeks' paid vacation per year.\n" +
                "(b) Holidays. The Executive shall be entitled to all paid Company holidays.\n" +
                "(c) Carry-Over. Any vacation not used during a calendar year may not be used during any subsequent period.\n" +
                "(d) Prorated. Vacation time shall be prorated for any partial calendar year of employment.\n" +
                "(e) Scheduling. The times for such vacations shall be selected by the Executive, provided the dates selected do not interfere materially with the performance of Executive's duties and responsibilities under this agreement.\n" +
                "OR\n" +
                "The vacation time shall be taken at such time or times as Executive and Employer may reasonably determine.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Insurance-Medical Coverage\n" +
                "\n" +
                "Health, Dental, Life and Disability Coverage. The Company shall provide Executive with life, medical, dental and disability coverage made available to its senior executives and key management employees, subject to and on a basis consistent with the terms, conditions and overall administration of such coverage.\n" +
                "\n" +
                "Medical Expense Reimbursement\n" +
                "\n" +
                "Reimbursement of Medical Expenses. The Company shall reimburse the Executive for the full amount of any medical, dental and optical expenses not covered under any group medical plan from time to time in effect for the benefit of Company employees generally.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
//  TODO: Did not understand the posAnalysis '' for [
        f = new Fragment("Provision of Automobile\n" +
                "\n" +
                "Automobile Expenses. The Company shall provide Executive with an automobile for business use in accordance with the automobile policies adopted by the Company from time to time. [The Company shall pay the expenses related to the use and upkeep of the automobile and insurance coverage.]\n" +
                "\n" +
                "Automobile Allowance\n" +
                "\n" +
                "Automobile Allowance. The Company will provide Executive with [AUTOMOBILE ALLOWANCE] per year for the purchase or lease of a vehicle.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Additional Provisions-Amendment of Benefit Plans\n" +
                "\n" +
                "The Company reserves the right to amend, terminate and/or suspend such benefits generally.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


     //Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Expenses

        f = new Fragment("Expenses.\n" +
                "(a) Authority. Employee is authorized to incur reasonable expenses for the promotion of the business of the Employer including expenses for entertainment, travel and other similar items.\n" +
                "(b) Reimbursement. Employer shall [promptly] reimburse Employee for reasonable and necessary expenses incurred on behalf of Employer by Employee in connection with the performance of Employee's duties hereunder [in accordance with the reimbursement policies adopted by Employer].\n" +
                "(c) Substantiation. Employee must provide the Employer [, for review and approval by the [CEO],] adequate records and other documentation as may be required for the substantiation of such expenditures as a business expense.\n" +
                "(d) Payment. Employer agrees to make prompt payment to Executive following receipt and verification of such reports.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Expenses. Employer shall reimburse Employee for reasonable and necessary expenses incurred on behalf of Employer by Employee in connection with the performance of his duties hereunder [in accordance with the reimbursement policies adopted by Company].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("information as may be required to satisfy the requirements of the Internal Revenue Code for deduction of such expenses by the Company.\n" +
                "\n" +
                "All reimbursable expenses shall be appropriately documented in reasonable detail by Employee upon submission of any request for reimbursement, and in a format and manner consistent with the Company's expense reporting policy, as well as applicable federal and state tax record keeping requirements.\n" +
                "\n" +
                "Payment\n" +
                "\n" +
                "All expenses or other reimbursements under this Agreement shall be made on or prior to the last day of the taxable year following the taxable year in which such expenses were incurred by Executive (provided that if any such reimbursements constitute taxable income to Executive, such reimbursements shall be paid no later than March 15th of the calendar year following the calendar year in which the expenses to be reimbursed were incurred), and no such reimbursement or expenses eligible for reimbursement in any taxable year shall in any way affect the expenses eligible for reimbursement in any other taxable year.\n" +
                "\n" +
                "Approval\n" +
                "\n" +
                "The determination of whether any specific business expenses are legitimate, reasonable and necessary, and in accordance with the policies of Employer, shall be made in the sole discretion of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


     //Exchange: Type: Performance Agreements: Consulting-> Terms of Engagment


        f = new Fragment("Terms of Engagement.\n" +
                "\n" +
                "(a) Performance. Consultant shall perform the duties faithfully, diligently [and to the best of Consultant's capacity OR competently OR in a workman-like manner].\n" +
                "\n" +
                "(b) Reporting. Consultant shall report to [TITLE OF REPORTING POSITION].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Alt-Best Efforts\n" +
                "\n" +
                "Duties and Responsibilities. Consultant shall apply its best efforts and devote such time as shall be reasonably necessary to perform its duties hereunder and advance the interests of the Company. The Consultant shall report directly to the Chief Executive Officer of the Company and to such persons as the Chief Executive Officer shall direct.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        //Exchange: Type: Performance Agreements: Consulting-> Payment and compensation clause

        f = new Fragment("Compensation.\n" +
                "\n" +
                "(a) Rate. The Services performed by Consultant shall be performed at the rate [of [CONSULTING SERVICES FEE]/set forth in the Statement of Work] [and not exceed the total estimated amount specified in the Statement of Work].\n" +
                "\n" +
                "(b) Invoices. The Consultant shall deliver invoices to the Company [bi-weekly/monthly] [after performance of the Work].\n" +
                "\n" +
                "(c) Payment. Payment shall be due [PAYMENT DUE DAYS] business days after receipt of invoices.\n" +
                "\n" +
                "(d) Withholding. Company shall not be responsible for federal, state and local taxes derived from the Consultant's net income or for the withholding and/or payment of any federal, state and local income and other payroll taxes, workers' compensation, disability benefits or other legal requirements applicable to Consultant.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
// TODO:Did not understand the posAnalysis $ for $
        f = new Fragment("(a) Compensation and Reimbursement. Subject to the provisions of this agreement, and as consideration for Services rendered, [COMPANY] shall pay Consultant at the rate of $US per day for the time actually expended by Consultant in rendering such Services with the authorization of [COMPANY] point of contact. In no event shall the total amount of compensation payable under this agreement exceed $US ( Dollars), unless [COMPANY] increases such monetary ceiling in writing. Time spent by Consultant in travel at the request of [COMPANY] point of contact shall be considered as time worked, for which payment will be made at one-half the rate of compensation.\n" +
                "\n" +
                "(b) Expenses and Approval. If non-local travel is required by [COMPANY], [COMPANY] will reimburse Consultant in U.S. Dollars for actual travel costs and other reasonable out-of-pocket expenses incurred with Services rendered hereunder, provided that such travel has been authorized or directed by [COMPANY] in advance. All other expenses exceeding $USD must be approved in advance by [COMPANY] s Authorized Representative.\n" +
                "\n" +
                "(c) Commissions In no event shall [COMPANY] be obligated to pay a commission or finder's fee to Consultant.\n" +
                "\n" +
                "(d) No Oter Compensation The compensation payable to Consultant by [COMPANY] under this agreement is the sole and exclusive compensation payable to Consultant for Services rendered. Consultant hereby expressly and irrevocably waives any rights or claims to any other compensation whatsoever, including any right to reimbursement of costs, expenses or disbursements made on behalf of [COMPANY], and any rights or claim to compensation for Services in the event such compensation or reimbursement is not payable under this agreement. Consultant is responsible for and no payment will be made under this agreement for any national, or local taxes, withholdings, fees, claims or the like arising from or connected with provided Services.\n" +
                "\n" +
                "(e) Changes to Compensation The compensation set forth herein shall only be altered by a written and specifically designated amendment to this agreement that has been signed by Consultant and [COMPANY]. Notwithstanding any other provision of this agreement, Consultant is put on special notice that no personnel of [COMPANY] are authorized to alter the amount or basis for compensation to Consultant, either through oral or written statements, other than as specifically described above. Any other statements to the contrary, written or oral, are only expressions of individuals' personal opinions, are not made on behalf of [COMPANY], and shall have no legal or moral effect with respect to this agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);



     // Exchange: Type: Performance Agreements: Provision of Information: Confidentiality Obligations

        f = new Fragment("Protection of Information\n" +
                "\n" +
                "(a) Confidentiality. The Receiving Party agrees to hold Confidential Information in confidence in accordance with the terms of this Agreement.\n" +
                "\n" +
                "(b) Non-Use. The Receiving Party agrees to use Confidential Information solely in accordance with the terms of this Agreement.\n" +
                "\n" +
                "(c) Non-Disclosure. The Receiving Party agrees not to disclose Confidential Information to third parties without the prior written consent of the Disclosing Party.\n" +
                "\n" +
                "(d) Copies and Recording. The Receiving Party may not copy or record the Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Copy Restrictions\n" +
                "\n" +
                "The Receiving Party agrees not copy or reproduce the Confidential Information [other than as strictly necessary in furtherance of the [Business] Purpose] [without the express written consent of the Disclosing Party.\n" +
                "\n" +
                "Any copies which are made will be identified as belonging to the disclosing party and shall be reproduced with the disclosing party's proprietary rights notices in the same manner in which such notices appear in the original copy provided by the disclosing party.\n" +
                "\n" +
                "1.2 Employee Restrictions\n" +
                "\n" +
                "In addition, for a period of two (2) years following the Period of Employment, Employee shall not use or disclose, directly or indirectly, any Confidential Information within the geographical area in which such use or disclosure could harm the Company's existing or potential business interests.\n" +
                "\n" +
                "Employee agrees that he/she will never use any Confidential Information for his/her own benefit or for the benefit of any person or entity other than the Company, and will not permit or allow any Confidential Information to be used in competition with the Company.\n" +
                "\n" +
                "1.3 Intellectual Property Rights\n" +
                "\n" +
                "The Receiving Party not apply for, or directly or indirectly assist any other person to apply for any intellectual property rights or any other proprietary right in respect of any invention, process or design that is based on or utilizes the Confidential Information.\n" +
                "\n" +
                "1.4 Non-Detrimental Use\n" +
                "\n" +
                "The receiving party will not use any Confidential Information in any way detrimental to the disclosing party [or any of its employees or customers].\n" +
                "\n" +
                "1.5 Actions to Prevent Disclosure\n" +
                "\n" +
                "The receiving party will (at its own expense) take all actions necessary to restrain its employees, agents and representatives from making any unauthorized use or disclosure of any of the Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

     // Exchange: Type: Performance Agreements: Provision of Information: Permitted Disclosures

        f = new Fragment("Permitted Disclosure. The Receiving Party shall not disclose the Confidential Information to any third party, except:\n" +
                "\n" +
                "(a) to its officers, directors, employees, attorneys, subsidiaries, affiliates [or third party consultants] on a need-to-know basis but only to the extent necessary to carry out that purpose and subject to all requirements of confidentiality set forth in this Agreement, or\n" +
                "\n" +
                "(b) pursuant to an express written authorization by the disclosing party.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment(" Notification of Confidentiality and Liability\n" +
                "\n" +
                "Permitted Disclosure. The Receiving Party will inform any employee to whom it discloses the Confidential Information of the confidential nature of the information and shall be liable for any breach of the Agreement by such employee.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

             // Exchange: Type: Performance Agreements: Provision of Information: Require Disclosures

        f = new Fragment("Required Disclosure.In the event that the Receiving Party becomes compelled by law to disclose any Confidential Information:\n" +
                "\n" +
                "Notice of Disclosure. The Receiving Party shall provide the Disclosing Party with prompt written notice so that the Disclosing Party may seek a protective order or other appropriate remedy and/or waive compliance with the provisions of this Agreement.\n" +
                "\n" +
                "Cooperation to Seek Protective Order. The Receiving Party shall cooperate with the Disclosing Party to obtain a protective order or other appropriate remedy.\n" +
                "\n" +
                "Limited Disclosure. In the event that a protective order or other remedy is not obtained, or the Disclosing Party waives compliance with the provisions of this Agreement, the Receiving Party shall: (i) disclose only the portion of Confidential Information that is legally required to disclose [in the written opinion of its counsel]; and (ii) exercise all reasonable efforts to obtain reliable assurances that confidential treatment will be afforded to Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Required Disclosure—Scope\n" +
                "\n" +
                "In the event that the receiving party becomes compelled by law [or by any court or governmental agency] to disclose any Confidential Information...\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "In the event that the receiving party [or any of its Representatives] are requested or required by applicable Federal or State law of the United States to disclose any Confidential Information...\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "In the event that the receiving party [or any of its Representatives] are required by any court or legislative or administrative body (by oral questions, interrogatories, requests for information or documents, subpoena or similar process) to disclose any Confidential Information.\n" +
                "\n" +
                "1.2 Notification\n" +
                "\n" +
                "The receiving party shall provide the disclosing party with [adequate prior] written notice so that the disclosing party may seek a protective order or other appropriate remedy and/or waive compliance with the provisions of this Agreement.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "The receiving party shall immediately notify the disclosing party of the existence, terms and circumstances surrounding such a request and consult with the disclosing party on the advisability of taking legally available steps to resist or narrow such request.\n" +
                "\n" +
                "1.3 Cooperation\n" +
                "\n" +
                "If the disclosing party seeks a protective order or other remedy, the receiving party shall provide such cooperation, at the disclosing party's expense, as disclosing party shall reasonably request.\n" +
                "\n" +
                "1.4 Release\n" +
                "\n" +
                "If no protective order is obtained and the receiving party has not received a waiver hereunder before one (1) business day prior to the time the recipient must disclose Confidential Information or else stand liable for contempt or suffer other sanction or penalty, the receiving Party may disclose to the minimum extent legally required the requested Confidential Information.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "If, in the absence of a protective order or other remedy or the receipt by receiving party of a waiver from disclosing party, receiving party [or any of its Representatives] is nonetheless, in the opinion of its legal counsel, legally compelled to Confidential Information to any tribunal or other entity or else stand liable for contempt or suffer other censure or penalty, receiving party [or such Representatives] may, without liability hereunder, disclose to such tribunal or other entity.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "In the absence of a protective order or failure to quash the legal process requiring disclosure or other measure effectively removing the legal compulsion, the receiving party shall have no duty to resist the production of Confidential Information and the production thereof shall not constitute a breach of this Agreement.\n" +
                "\n" +
                "1.5 Limited Disclosure\n" +
                "\n" +
                "The receiving party agrees that if disclosure of such Evaluation Information is required to prevent the receiving party from being held in contempt or subject to other penalty, to furnish only such portion of the Evaluation Information as, in the written opinion of counsel satisfactory to the Disclosing Party.\n" +
                "\n" +
                "1.6 Assurances\n" +
                "\n" +
                "The receiving party will exercise all reasonable efforts to obtain reliable assurances that confidential treatment will be afforded to Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
     // Exchange: Type: License Agreements: License Terms

        //Intentionally Blank

     // Exchange: Type: License Agreements: Delivery and Acceptance


        f = new Fragment("Acceptance.\n" +
                "\n" +
                "1.1 Acceptance Criteria. The Parties shall define the Acceptance Criteria.\n" +
                "\n" +
                "1.2 Acceptance Period. Customer will have [ACCEPTANCE PERIOD] following the date of [delivery OR installation] to evaluate the Software.\n" +
                "\n" +
                "1.3 Completion. If[, in the sole opinion of Customer,] the Software satisfies the Acceptance Criteria, then Developer shall be deemed to have completed its delivery obligations.\n" +
                "\n" +
                "1.4 Rejection. If[, in the sole opinion of Customer,] the Software does not satisfy the Acceptance Criteria, then:\n" +
                "(a) Notification. Customer shall itemize in writing each failure to satisfy the Acceptance Criteria.\n" +
                "(b) Correction. Developer shall [promptly OR use its best efforts to] correct the Software and upon delivery of such correction and deliver the Software to Customer for re-testing and acceptance.\n" +
                "(c) Continued Failure. If Developer's corrections[, in the sole opinion of Customer,] fails to deliver tosatisfy the Acceptance Criteria [on more than [NUMBER OF CORRECTIONS]], then Customer may elect to:\n" +
                "(i) terminate the agreement, or\n" +
                "(ii) adjust the Acceptance Criteria.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Notification of Acceptance\n" +
                "\n" +
                "Notification of Acceptance. If and when the acceptance tests establish that the Software delivered upon completion of [the Software OR any phase of development] complies with the acceptance criteria, Customer shall promptly notify Developer that it accepts the delivered Software.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.2 Acceptance and Retest, with Right to Terminate and Damages\n" +
                "\n" +
                "6. Acceptance Tests and Liquidated Damages\n" +
                "6.1 The Acceptance Tests shall be agreed by the parties in accordance with the Implementation Plan.\n" +
                "6.2 The Developer shall use its reasonable endeavours to ensure that the New Software is ready for acceptance testing by the Planned Acceptance Date. In any event, the Developer shall give the Customer working days' prior notice in writing of the date when it will be ready to commence the Acceptance Tests. Unless otherwise agreed, the Acceptance Tests shall take place on the working day after such notice has been given.\n" +
                "6.3 The Customer shall accept the New Software immediately after the New Software has passed the Acceptance Tests.\n" +
                "6.4 If the New Software fails to pass the Acceptance Tests, repeat tests shall be carried out until the earlier of the following occurs:\n" +
                "6.4.1 the New Software passes the Acceptance Tests;\n" +
                "6.4.2 the Acceptance Tests have been repeated 3 times; or\n" +
                "6.4.3 a 30-day period from the Planned Acceptance Date has expired.\n" +
                "6.5 If at any time the Customer shall commence live running of the whole or any part of the New Software (other than in the Acceptance Tests) then the Customer shall be deemed to have accepted the New Software.\n" +
                "6.6 If the New Software has not been accepted by the Customer on or after the occurrence of the events specified in clauses 6.4.2 or 6.4.3, then the Customer shall be entitled, without prejudice to any other rights or remedies it may have under this Agreement or at law, to terminate forthwith this Agreement by written notice upon the Developer and, notwithstanding the liquidated damages in clause 6.7 below, shall be entitled to damages or compensation for material breach.\n" +
                "6.7 If the New Software is not ready for acceptance testing by the Planned Acceptance Date in accordance with clause 6.2 above then, save where such failure results from the default by the Customer of its obligations under this Agreement, the Developer shall pay to the Customer by way of liquidated damages the sum of per day commencing on the day after the Planned Acceptance Date and expiring on the Acceptance Date subject to a maximum of . Such payment shall be without prejudice to the Developer's obligation to complete the New Software as soon after the Planned Acceptance Date as shall be reasonably possible.\n" +
                "6.8 For the avoidance of doubt time shall be of the essence.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

     // Exchange: Type: License Agreements: Support and Maintenance

        f = new Fragment("Alternatives- Option 1—No Obligation.\n" +
                "\n" +
                "Licensor has no obligation to provide any maintenance, support or other services.\n" +
                "\n" +
                "Option 2—Discretionary.\n" +
                "\n" +
                "Licensor may elect to provide maintenance, support and other services in its sole discretion[, and may terminate such support at any time without notice to Licensee].\n" +
                "\n" +
                "Option 3—First year included.\n" +
                "\n" +
                "The license includes a one-year subscription to receive maintenance and support services commencing on the date of installation. Licensee may renew its maintenance and support subscription after the initial subscription period at then-current rates.\n" +
                "\n" +
                "Option 4—Fee-based Service.\n" +
                "\n" +
                "Licensor shall provide maintenance and support services, provided Licensee is current in the payment of all [license, ]maintenance and support fees.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Scope of Services-Alternative 1—Support Services.\n" +
                "\n" +
                "Support Services. Licensor shall provide Licensee with support services, under the following terms and conditions:\n" +
                "\n" +
                "(a) Technical Support. Licensor shall provide Licensee with access to technical support services, [including OR limited to]:\n" +
                "\n" +
                "(i) clarification of functions and features of the Software;\n" +
                "(ii) clarification of the Software documentation;\n" +
                "(iii) guidance in the operation of the Software; and\n" +
                "(iv) error verification, analysis and correction to the extent possible through remote support.\n" +
                "\n" +
                "(b) Provision of services. Support services [may OR will] be provided by telephone, [email], or [online chat].\n" +
                "\n" +
                "(i) Support Hours. Support services are provided during standard hours of service from 9:00 a.m. through 5:00 p.m. [Time Zone], except for business holidays as observed by Licensor.\n" +
                "(i) Service Level. Licensor will [attempt][use all commercially reasonable efforts] to respond to e-mail inquiries with [the same business day OR [NUMBER] [hours OR days] of receipt].\n" +
                "\n" +
                "Alternative 2—Maintenance Services.\n" +
                "\n" +
                "(a) Updates. Licensor will provide Licensee with updates and upgrades to the Software made generally available by Licensor to its maintenance and support customers, provided Licensee is current in the payment of all [license, ]maintenance [and support] fees.\n" +
                "\n" +
                "(b) Fixes. In the event the Software fails to operate substantially in accordance with the then-current specifications, Licensor will [undertake all commercially reasonable efforts to] provide Licensee with error corrections, bug fixes, patches or other updates to remedy the failure.\n" +
                "\n" +
                "(c) Ownership of Updates. All updates, bug fixes, and patches shall remain the property and are governed by the term of this license agreement.\n" +
                "\n" +
                "Note: consider who has the obligation to install updates.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Limitation of Services-Licensor will have no obligation to provide support for any failure of the Software to operate substantially in accordance with the then-current specifications that:\n" +
                "(a) cannot be reasonably remedied;\n" +
                "(b) is due to use of the Software in combination with third-party products, equipment, software, or data;\n" +
                "(c) is due to any release of the Software other than the most current release;\n" +
                "(d) is due to any modifications to the Software not provided by Licensor;\n" +
                "(e) has been listed as a known issue on Licensor's website, or\n" +
                "(f) is due to breach of the license agreement or is caused by Licensee's negligence, abuse, misapplication, or use of the Software.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Exchange: Type: License Agreements: Rent

        f = new Fragment("Rent.\n" +
                "\n" +
                "(a) Base Rent. Base Rent [for the calendar year _____] is ______ Dollars [or the amount determined by multiplying the square footage of the Leased Premises times the rate per square foot specified in Schedule [ ]].\n" +
                "\n" +
                "(b) Rent Adjustments. On each annual anniversary of the Rent Commencement Date Base Rent then in effect shall be increased [or decreased] by a percentage equal to the percentage increase in the CPI Index.\n" +
                "\n" +
                "(c) Payment. Rent payments are payable in monthly installments of _____ Dollars due in advance, on the first (1st) day of each month during the Lease Term.\n" +
                "\n" +
                "(d) Manner of Payment. Rent payable under this Lease shall be paid by check or wire transfer to Landlord without prior notice or demand therefore, and without deduction or setoff at the designated address, or any other place as Landlord may later designate.\n" +
                "\n" +
                "(e) Late Charges [and Interest].\n" +
                "(i) If any payment of Rent is not received by Landlord within ___ [business] days of its due date, Tenant shall pay to Landlord a late charge in the amount of [fixed dollar amount or a fixed percentage of the Rent] (the \"Late Charge\") per occurrence. (ii) Any amount due from Tenant to Landlord hereunder which is not paid within ___ [business] days after the date due shall bear interest at the lower often percent ___ [interest rate] per annum, or the maximum lawful rate of interest from the due date until paid.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Alternative Terms- 1.1 Amount of Rent\n" +
                "\n" +
                "Amount of Rent. Base Rent for the calendar year _____ is ______ Dollars [or the amount determined by multiplying the square footage of the Leased Premises times the rate per square foot specified in Schedule _]. Rent payments due hereunder are payable in monthly installments of _____ Dollars (\"Minimum Monthly Rent\") due in advance, on the first (1st) day of each month beginning on the Rent Commencement Date [as defined in the Lease Term] and continuing thereafter during the Term hereof. Base Rent for any monthly period during the Term which is less than an entire month shall be pro-rated based on actual days divided by 30 [or the actual number of days in the month] and shall be paid on the first day of the next succeeding calendar month or on the Expiration Date, if earlier.\n" +
                "\n" +
                "1.2 Additional Rent\n" +
                "\n" +
                "Additional Rent. All amounts that Tenant is required to pay to Landlord under this Lease, other than Minimum Monthly Rent, shall be deemed additional rent and referred to as Additional Rent. Minimum Annual Rent and Additional Rent shall be referred to collectively as Rent. All Additional Rent due under this Lease shall be payable concurrently with the monthly installments of Minimum Annual Rent, unless Landlord expressly in writing sets forth another time period for the payment of such Additional Rent.\n" +
                "\n" +
                "1.3 Rent Adjustments\n" +
                "\n" +
                "Rent Adjustments. Commencing on the 1st day of January next following the Rent Commencement Date and on each January 1 thereafter (each such date a Rent Adjustment Date) the Minimum Monthly Rent then in effect shall be increased [or decreased] by a percentage equal to the percentage increase in the CPI Index.\n" +
                "\n" +
                "CPI Index shall mean the Consumer Price Index presently designated as the United States Department of Labor, Bureau of Labor Statistics Consumer Price Index for all Urban Consumers, U.S. City Average [or for the local region], (Base 1982-1984 equals 100), for the month which is four (4) months prior to the Rent Adjustment Date compared to the Index published for the month which is sixteen (16) months prior to such Rent Adjustment Date. In the event that the statistics are not available or in the event that publication of the Consumer Price Index is modified or discontinued in its entirety, the adjustment provided for herein shall be made on the basis of an index chosen by Landlord as a comparable and recognized index of purchasing power of the United States consumer dollar published by the U.S. Department of Labor or other governmental agency. [Additional clause specifying precisely how the increase is calculated.]\n" +
                "\n" +
                "1.4 Manner of Payment\n" +
                "\n" +
                "Manner of Payment. Rent payable under this Lease shall be paid to Landlord or Landlord's agent in lawful money of the United States of America without prior notice or demand therefore, and without deduction, defense, counterclaim, setoff or abatement [and without relief from valuation and appraisement laws], at the designated address, or any other place as Landlord may later designate, by check or wire transfer.\n" +
                "\n" +
                "1.5 Right to Accept Payments\n" +
                "\n" +
                "Right to Accept Payments. No receipt by Landlord of an amount less than the full amount due will be deemed to be other than payment on account, nor will any endorsement or statement on any check or any accompanying letter effect or evidence an accord and satisfaction. Landlord may accept such check or payment without prejudice to Landlord's right to recover the balance or pursue any right of Landlord. No payments by Tenant to Landlord: (a) after the expiration or other termination of the Term will reinstate, continue or extend the Term; or (b) will invalidate or make ineffective any notice (other than a demand for payment of money) given prior to such payment by Landlord to Tenant. After notice or commencement of a suit, or after final judgment granting Landlord possession of the Premises, Landlord may receive and collect any sums of Rent due under this Lease, and such receipt will not void any notice or in any manner affect any pending suit or any judgment obtained.\n" +
                "\n" +
                "1.6 Late Charge on Delinquent Payments\n" +
                "\n" +
                "Late Charge on Delinquent Payments. Tenant hereby acknowledges that late payment by Tenant to Landlord of Rent due hereunder will cause Landlord to incur costs not contemplated by this Lease, the exact amount of which will be extremely difficult to ascertain. Such costs include but are not limited to processing and accounting charges and late charges which may be imposed upon Landlord by terms of any mortgage or trust deed covering the Premises. Accordingly, if any payment of Rent is not received by Landlord within ___ [business] days of its due date, Tenant shall pay to Landlord a late charge in the amount of [fixed dollar amount or a fixed percentage of the Rent] (the \"Late Charge\") per occurrence. The parties hereby agree that such Late Charge represent a fair and reasonable estimate of the cost that Landlord will incur by reason of the late payment by Tenant. This Section shall not relieve Tenant from its obligation to pay Rent at the times and in the manners herein specified. Acceptance by Landlord of the Late Charge shall not constitute a waiver of Tenant's default with respect to said delinquent payment, nor prevent Landlord from exercising any other rights or remedies available to Landlord. In addition to all other rights and remedies provided Landlord, all amounts payable hereunder which remain unpaid within ___ [business] days of its due date shall bear interest from that date to and including the date of payment, at the rate of [number] percent (__%) per annum, or the highest legal rate, whichever is lower.\n" +
                "\n" +
                "1.7 Dishonored Checks\n" +
                "\n" +
                "Dishonored Checks. A charge of _____ Dollars shall be imposed for each dishonored check made by the Tenant to the Landlord. All Late Fees and dishonored check charges shall be due when incurred and shall be included as Additional Rent.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional or Additional- 2.1 Additional Rent\n" +
                "\n" +
                "Common Area Operating Expenses. Lessee shall pay to Lessor during the term hereof, in addition to the Base Rent, Lessee's Share (as specified in Paragraph ___) of all Common Area Operating Expenses, as hereinafter defined, during each calendar year of the term of this Lease, in accordance with the following provisions:\n" +
                "\n" +
                "(a) \"Common Area Operating Expenses\" are defined, for purposes of this Lease, as alt costs incurred by Lessor relating to the ownership and operation of the Project, including, but not limited to, the following:\n" +
                "\n" +
                "(i) The operation, repair and maintenance, in neat, clean, good order and condition, of the following: (aa) The Common Areas and Common Area improvements, including parking areas, loading and unloading areas, trash areas, roadways, walkways, parkways, driveways, landscaped areas, bumpers, irrigation systems, Common Area lighting facilities, fences and gates, elevators, roof and roof drainage systems, (bb) Exterior signs and any tenant directories, (cc) Fire detection and sprinkler systems.\n" +
                "\n" +
                "(ii) The cost of water, gas, electricity and telephone to service the Common Areas and any utilities not separately metered.\n" +
                "\n" +
                "(iii) Trash disposal, pest control services, property management and security services and the costs of any environmental inspections.\n" +
                "\n" +
                "(iv) Reserves set aside for maintenance and repair of Common Areas.\n" +
                "\n" +
                "(v) Real Property Taxes (as defined in Paragraph ___).\n" +
                "\n" +
                "(vi) The cost of the premiums for the insurance policies maintained by Lessor under Paragraph __ hereof.\n" +
                "\n" +
                "(vii) Any deductible portion of an insured loss concerning the Building or the Common Areas.\n" +
                "\n" +
                "(viii) The cost of any Capital Expenditure to the Building or the Project not covered under the provisions of Paragraph ___ provided; however, that Lessor shall allocate the cost of any such Capital Expenditure over a reasonable period and Lessee shall not be required to pay more than Lessee's Share of the cost of such Capital Expenditure in any given month.\n" +
                "\n" +
                "(ix) Any other services to be provided by Lessor that are stated elsewhere in this Lease to be a Common Area Operating Expense.\n" +
                "\n" +
                "2.2 Certified Payments After Dishonored Check\n" +
                "\n" +
                "Certified Payments After Dishonored Check. Tenant agrees to pay all rents, late fees, all notice fees and all costs to honor a returned check with certified funds (Money Order or Cashier's Check). After the Tenant has tendered a check which is dishonored, Tenant hereby agrees to pay all remaining payments including rent due under this Lease by certified funds. Any payments tendered to Landlord thereafter, which are not in the form of certified funds, shall be treated as if Tenant failed to make said payment until certified funds are received.\n" +
                "\n" +
                "2.3 Minimum and Maximum Rent Increase\n" +
                "\n" +
                "Minimum and Maximum Rent Increase. Notwithstanding anything contained herein to the contrary, in no event shall Base Rent plus CPI Charges payable in any Lease Year increase at a rate less than ___ percent or at a rate more than ___ percent over the sum of the Base Rent plus CPI Charges payable in the immediately preceding Lease Year. Until Landlord shall have delivered to Tenant a statement of the adjustment in CPI Charges for a particular Lease Year, Tenant shall pay to Landlord, along with Base Rent, CPI Charges (plus value added tax) for such Lease Year based on the ___ percent minimum increase over the prior Lease Year's payment of Base Rent and CPI Charges.\n" +
                "\n" +
                "2.4 Fair Market Value Adjustment\n" +
                "\n" +
                "Fair Market Value Adjustment .\n" +
                "\n" +
                "(a) Effective on ___ __, 20__, and every _____ years thereafter (\"Base Rent Reset Date\"), either party may elect to reset the Base Rent to the fair market rate for the Premises on such date. Such election shall be made by giving the other party at least thirty (30) days written notice prior to such date.\n" +
                "\n" +
                "(b) Fair Market Rate. The fair market rate shall be determined based on the rents being charged on a [triple-net] basis by landlords in similar office buildings in the geographical market within which the Premises are located as reasonably determined by the parties. In the event that Landlord and Tenant cannot agree on a fair market rate within thirty (30) days after the Term Commencement Date or Base Rent Reset Date, as the case may be, then each party shall appoint a licensed real estate appraiser who shall deliver a written appraisal of the fair market rate to both parties within sixty (60) days after the Term Commencement Date or Base Rent Reset Date, as the case may be. If only one of the appraisers delivers an appraisal within such period, such appraisal shall be final and binding on both parties. If both appraisers deliver appraisals within such period, and the higher is not more than one hundred ten percent (110%) of the lesser, then the average of the two appraisals shall be final and binding on both parties. If both appraisers deliver appraisals within such period, and the higher is more than one hundred ten percent (110%) of the lesser, then the two appraisers shall appoint a third licensed appraiser. The third appraiser shall select one of the two appraisals which better reflects the fair market rate for the Premises, and the appraisal so selected shall be final and binding on both parties.\n" +
                "\n" +
                "2.5 Rent Concession\n" +
                "\n" +
                "Rent Concession. If Tenant occupies the Leased Premises for the purpose of preparing the Premises for their occupancy prior to the Rent Commencement Date, Tenant shall pay any utilities and any pro rated common area expenses from the date of occupancy to the Rent Commencement Date.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

   //Term: Performance Agreements: Term of Employment 

        f = new Fragment("Term of Employment.\n" +
                "\n" +
                "(a) Initial Term. The term of the Executive's employment under this Agreement shall commence on the Effective Date and continue until [INITIAL TERM END DATE] (the \"Term\"), unless employment is sooner terminated.\n" +
                "\n" +
                "(b) Automatic Renewal. Commencing on [INITIAL TERM END DATE] and on each anniversary of that date thereafter, the Term shall be extended for an additional [EXTENSION PERIOD].\n" +
                "\n" +
                "(c) Notice Not to Renew. Either party may give notice of the intention not to extend the Term [in writing] at least [90 days] prior to each such anniversary date.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Additional:\n" +
                "1.1 Non-Renewal\n" +
                "\n" +
                "Non-Renewal. An election not to renew pursuant to this Section shall not constitute a Termination Without Cause.\n" +
                "\n" +
                "1.2 Effect of Change of Control\n" +
                "\n" +
                "Change of Control. Notwithstanding any notice by the Company not to renew, if a Change of Control occurs during the original, or extended term of this Agreement, or after this Agreement has been terminated, but within [12 months] after such notice to terminate the Agreement was given by the Company, the termination shall be deemed ineffective and the Agreement shall continue in effect. In any event, the term of this Agreement shall expire on the [2nd] anniversary of the date of the Change of Control.\n" +
                "\n" +
                "1.3 Maximum Age Limitations\n" +
                "\n" +
                "Age Limitation. The Executive's employment shall cease and shall not extend past the last day of the month in which the Executive attains [age 70].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Employment Term. The Executive's term of employment under this Agreement (such term of employment, as it may be extended or terminated, is herein referred to as the \"Employment Term\") shall be for a term commencing on the Effective Date and, unless terminated earlier as provided in Section 7 hereof, ending on the third anniversary of the Effective Date (the \"Original Employment Term\"), provided that the Employment Term shall be automatically extended, subject to earlier termination as provided in Section 7 hereof, for successive additional one (1) year periods (the \"Additional Terms\"), unless, at least 30 days prior to the end of the Original Employment Term or the then Additional Term, the Company or the Executive has notified the other in writing that the Employment Term shall terminate at the end of the then current term.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Employment. The Company hereby agrees to continue to employ Executive and Executive hereby agrees to continue to serve the Company, on the terms and conditions set forth herein, for the period commencing on the date hereof and expiring on July 31, 2002 (unless sooner terminated as hereinafter set forth); provided, however, that commencing on August 1, 2002, and each year thereafter, the term of this Agreement shall automatically be renewed for one additional year unless, at least 30 days prior to the expiration of the initial or renewal term, the Company or Executive shall have given written notice to the other party that it does not wish to extend this Agreement. The term of this Agreement, as it may from time to time be extended in accordance with this Paragraph, may be referred to herein as the \"Period of Employment.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("no current proceeding");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


   //Term: Performance Agreements: Term of Confidentiality 


        f = new Fragment("Term of Confidentiality. The term of this Agreement will commence on the Effective Date and continue for a period of [TERM OF OBLIGATION].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
  

        f = new Fragment("1.1 Protected Until Public\n" +
                "\n" +
                "Term of Confidentiality. The obligations of the receiving party will commence on the Effective Date and continue in effect until such time as all such Confidential Information disclosed becomes publicly known and made generally available through no action or inaction of the Receiving Party.\n" +
                "\n" +
                "1.2 Protected Until Consummation of Superseding Transaction\n" +
                "\n" +
                "Term of Confidentiality. The obligations of the receiving party will commence on the Effective Date and continue until consummation of an agreement that supersedes this agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);




  //Term: License Agreements: Term of License 

//Fill in clauses


 // Statements: Representations: Basic Representations: Organization


        f = new Fragment("(a) Organized: [Party] is duly organized, validly existing and in good standing under the laws of the jurisdiction of its incorporation or organization.\n" +
                "\n" +
                "(b) Authorized: [Party] has all requisite power and authority to own, operate and lease its properties and to carry on its business as and where such is now being conducted.\n" +
                "\n" +
                "(c) Qualified: [Party] is duly licensed or qualified to do business and is in good standing in each jurisdiction in which the nature of the business conducted by it or the character or location of the properties and assets owned or leased by it makes such licensing or qualification necessary.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Material Adverse Effect\n" +
                "\n" +
                "Qualified: [Party] is duly licensed or qualified to do business and is in good standing in each jurisdiction in which the nature of the business conducted by it or the character or location of the properties and assets owned or leased by it makes such licensing or qualification necessary, except where failure to be qualified, licensed or in good standing has not had and would not reasonably expected to have a Material Adverse Effect on [Party] [or prevent or materially delay consummation of the transaction].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Basic Representations: Authorization

        f = new Fragment("Authorization.\n" +
                "\n" +
                "(a) Authority. The execution, delivery and performance of this agreement has been duly and validly authorized.\n" +
                "\n" +
                "(b) Delivery and Execution. This agreement has been duly executed and delivered.\n" +
                "\n" +
                "(c) Enforceability. This agreement constitutes a legal, valid, and binding obligation, enforceable in accordance with its terms.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Example 1—Concise Form\n" +
                "\n" +
                "Authority: [Party] has the authority to enter into the Agreement and to perform the obligation required.\n" +
                "\n" +
                "Example 2—Qualification for Stockholder Approval\n" +
                "\n" +
                "(a) Authority: [Party] has all requisite corporate power and authority to execute and deliver this Agreement and to consummate the transactions contemplated by this Agreement, subject, in the case of the consummation of the Merger, to the receipt of the Stockholder Approval.\n" +
                "(b) Delivery and Execution. This agreement has been duly executed and delivered.\n" +
                "(c) Enforceability. Subject to due authorization, execution and delivery by all other parties, the agreement constitutes a legal, valid and binding obligation of the Company, enforceable against the Company in accordance with its terms.\n" +
                "\n" +
                "Example 3—Qualification for Stockholder Approval\n" +
                "\n" +
                "Authority: The execution and delivery of this Agreement by the [Party] and the consummation by the [Party] of the transactions contemplated by this Agreement have been duly authorized by all necessary corporate action on the part of the [Party] and no other corporate proceedings on the part of the [Party] are necessary to authorize this Agreement or to consummate the transactions contemplated hereby, subject, in the case of the consummation of the Merger, to the obtaining of the Stockholder Approval.\n" +
                "\n" +
                "Example 4—Qualification for Future, Unforeseen Events\n" +
                "\n" +
                "Authority: This Agreement has been duly executed and delivered by the Company and, assuming the due authorization, execution and delivery by each of the other parties hereto, constitutes a legal, valid and binding obligation of the Company, enforceable against the Company in accordance with its terms, subject to bankruptcy, insolvency, moratorium, reorganization or similar laws affecting the rights of creditors generally and the availability of equitable remedies.\n" +
                "\n" +
                "Example 5—Qualification for Bankruptcy and Insolvency\n" +
                "\n" +
                "Enforceability: This Agreement constitutes a valid and legally binding agreement of the Company, enforceable in accordance with its terms and conditions, subject to Applicable Laws of general application relating to public policy, bankruptcy, insolvency and the relief of debtors and Applicable Laws governing specific performance, injunctive relief and other equitable remedies.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Basic Representations: No Conflicts


        f = new Fragment("No Conflicts. The execution, delivery and performance of this agreement will not result in:\n" +
                "\n" +
                "(a) Charter Documents. A violation of the Party's certificate of incorporation or bylaws.\n" +
                "\n" +
                "(b) Laws. A violation of any law, judgment or order applicable to the Party.\n" +
                "\n" +
                "(c) Contracts. A conflict with, or result in a breach of, or constitute a default, or give rise to any right of termination, acceleration or cancellation, under any [material] contract.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Basic Representations: No Conflicts: Asset Purchase Agreement



        f = new Fragment("Example 1—Asset Purchase Agreement\n" +
                "\n" +
                "No Violation. Neither the execution and delivery of this Agreement by Seller and the performance by Seller of its obligations hereunder nor the consummation by Seller of the transactions contemplated hereby will\n" +
                "(a) violate, conflict with or result in any breach of any provision of the Certificates or Articles of Incorporation or Bylaws of Seller,\n" +
                "(b) violate, conflict with or result in a violation or breach of, or constitute a default (with or without due notice or lapse of time or both) under the terms, conditions or provisions of any note, bond, mortgage, indenture or deed of trust, or any material license, lease or agreement to which Seller is a party or to which any of its property or assets may be bound, or \n" +
                "(c) violate any order, writ, judgment, injunction, decree, statute, rule or regulation, of any court or Governmental Authority applicable to Seller.\n" +
                "Lease Transfer and Asset Purchase Agreement.\n" +
                "\n" +
                "Example 2—Asset Purchase Agreement\n" +
                "\n" +
                "(b) The execution, delivery, and performance of this Agreement and the Ancillary Agreements and the performance of Seller's covenants and agreements herein and therein contained do not and will not \n" +
                "(i) contravene or conflict with or constitute a violation of any provision of applicable law binding upon or applicable to the ownership of the Acquired Assets or the Seller's business; \n" +
                "(ii) conflict with, result in a breach of, constitute a default under or give rise to any right of termination, cancellation or acceleration of any right or obligation of Seller relating to the Acquired Assets or Assumed Liabilities or to a loss of any benefit relating to the Acquired Assets or Assumed Liabilities to which Seller is entitled under any provision of any agreement, contract or other instrument or relating to any of the Acquired Assets; \n" +
                "(iii) result in the creation or imposition of any Encumbrance on any Acquired Asset; or \n" +
                "(iv) conflict with or violate any provision of the articles of incorporation, bylaws, or other governing documents of the Seller as in effect immediately prior to the Closing.\n" +
                "Asset Purchase Agreement");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Basic Representations: No Conflicts: Employment Agreement

        f = new Fragment("Example 3—Employment Agreement\n" +
                "\n" +
                "No Conflicts. Executive represents and warrants to the Company that:\n" +
                "(i) the execution, delivery and performance of this Agreement by Executive do not and shall not conflict with, breach, violate or cause a default under any contract, agreement, instrument, order, judgment or decree to which Executive is a party or by which she is bound,\n" +
                "(ii) Executive is not a party to or bound by any employment agreement, noncompete agreement or confidentiality agreement with any other person or entity, and\n" +
                "(iii) upon the execution and delivery of this Agreement by the Company, this Agreement shall be the valid and binding obligation of Executive, enforceable in accordance with its terms.\n" +
                "Executive acknowledges and represents that Executive has consulted with independent legal counsel regarding the rights and obligations under this Agreement and that Executive fully understands the terms and conditions contained of the agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Consent and Approvals: No Conflicts: Board Approval 

        f = new Fragment("Board Approval. The Board of Directors of [Party] has approved this Agreement and the transactions contemplated hereby and will submit it to the Stockholders for their approval.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Elements and Optional:\n" +
                "(a) Approval: The Board of Directors of Company has approved this Agreement.\n" +
                "\n" +
                "(b) Advisable (optional): The Board of Directors determined that this Agreement is advisable and in the best interests of the stockholders of Company.\n" +
                "\n" +
                "(c) Recommended (optional): The Board of Directors recommended that the stockholders of Company approve this Agreement and consummation of the Merger.\n" +
                "\n" +
                "(d) Submitted: The Board of Directors [has submitted / will submit] the Agreement to the Stockholders for their approval.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Consent and Approvals: No Conflicts: Board Approval: Type Merger Agreement 

        f = new Fragment("Examples:\n" +
                "Board Approval. As of the date of this Agreement, the Company's Board of Directors, by unanimous resolutions duly adopted at a meeting duly called and held, has:\n" +
                "\n" +
                "(i) determined that this Agreement and the Merger are in the best interests of the Company and its shareholders,\n" +
                "\n" +
                "(ii) adopted a unanimous resolution approving this Agreement pursuant to the ORC,\n" +
                "\n" +
                "(iii) recommended that the shareholders of the Company adopt this Agreement (the \"Company Recommendation\") and\n" +
                "\n" +
                "(iv) directed that such matter be submitted for consideration by the Company shareholders at the Company Shareholders Meeting.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Consent and Approvals: No Conflicts: Stockholder Approval 

        f = new Fragment("The affirmative vote of the holders of [a majority / two-thirds / other percentage] of the outstanding shares of Common Stock entitled to vote on this Agreement is the only vote of the holders of securities of the Company necessary to approve this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Alternatives: \n" +
                "1.1 Caveats\n" +
                "Assuming the accuracy of the representations and warranties, the affirmative vote of holders of two-thirds of all the outstanding shares of Company Common Stock entitled to vote thereon at the Stockholders Meeting or any adjournment or postponement thereof to approve this Agreement is the only vote of the holders of any class or series of capital stock of the Company necessary for the Company to adopt this Agreement and approve the transactions contemplated hereby.\n" +
                "\n" +
                "If required by applicable Law to approve the Merger, the adoption of this Agreement at the Company Stockholders' Meeting by the holders of a majority of the issued and outstanding shares of Common Stock entitled to vote at the Company Stockholders' Meeting (the \"Stockholder Approval\") is the only vote of the holders of any class or series of the Company's securities necessary to adopt and approve this Agreement, the Merger and the other Transactions.\n" +
                "\n" +
                "1.2 Alternative\n" +
                "The only vote of the stockholders of the Company required under the DGCL, the NYSE rules or the Company's certificate of incorporation for (a) adoption of this Agreement, (b) amendment of the Company's certificate of incorporation to increase the number of authorized shares of Company Common Stock in connection with the issuance of the Aggregate Merger Consideration and (c) the issuance of the Aggregate Merger Consideration is the affirmative vote of the holders of a majority in voting power of all outstanding shares of Company Common Stock at the Company Stockholders Meeting.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Consent and Approvals: No Conflicts: Government Approval 


        f = new Fragment("No Consents Required. No consent, authorization, order or approval of, or filing or registration with, or notification to any court, administrative agency or commission or other governmental authority or instrumentality is required by the Seller in connection with the execution, delivery and performance by the Seller of this Agreement or the consummation by the Seller of the transactions contemplated hereby.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Alt:\n" +
                " Listed Exceptions. No consent, approval, order or authorization of, or registration, declaration or filing with, any court, administrative agency or commission or other governmental authority or instrumentality is required by or with respect to the Company or any Subsidiary in connection with the execution and delivery of this Agreement or the consummation of the transactions contemplated hereby, except for:\n" +
                "\n" +
                "such consents, approvals, orders, authorizations, registrations, declarations and filings as may be required under the Securities Act of 1933, as amended, applicable state securities laws and the securities laws of any foreign country;\n" +
                "such filings as may be required under the Hart-Scott-Rodino Antitrust Improvements Act of 1976, as amended; and\n" +
                "such other consents, authorizations, filings, approvals and registrations which, if not obtained or made, would not have a Material Adverse Effect on the Company and would not prevent, or materially alter or delay any of the transactions contemplated by this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Corporate Documents: Charter Documents 

        f = new Fragment("Public Company\n" +
                "The Company Charter and Company Bylaws as most recently filed as exhibits to the Company SEC Reports are in full force and effect.\n" +
                "\n" +
                "Private Company\n" +
                "The Company has delivered or made available to [Party] true, correct, and complete copies of the certificate of incorporation and bylaws of the Company, including all amendments thereto. The Company is not in violation of its certificate of incorporation or bylaws[ or equivalent organizational documents].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Additional:\n" +
                "1.1 No Violation\n" +
                "\n" +
                "Additional requirements can be placed on the party to further represent that t is not in violation of its charter document.\n" +
                "\n" +
                "Example 1: \"The Company is not in violation of any provisions of its Certificate of Incorporation or By-Laws in any material respect.\"\n" +
                "\n" +
                "Example 2: \"Neither the Company nor any of its subsidiaries is in violation of any of the provisions of its Certificate of Incorporation or By-laws or equivalent organizational documents, except for violations of the documents which do not and are not reasonably likely to materially interfere with the operations of such entity.\"\n" +
                "\n" +
                "1.2 No Other Organizational Documents\n" +
                "\n" +
                "Example: \"The Certificate of Incorporation of the Company and the By-Laws are in full force and effect and no other organizational documents are applicable to or binding upon the Company.\"");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Corporate Documents: Subsidiaries 

        f = new Fragment("No Subsidiaries.\n" +
                "The Company does not own any interest in any corporation, partnership or other entity [other than those previously disclosed by the Company].\n" +
                "\n" +
                "Disclosed Subsidiaries.\n" +
                "(a) Disclosure. The Disclosure Schedule lists each Subsidiary and identifies the jurisdiction of formation and names of the officers and directors of each Subsidiary.\n" +
                "(b) Ownership. The Company owns, directly or indirectly, of record and beneficially all of the outstanding equity interests of each Subsidiary, free and clear of all Encumbrances.\n" +
                "(c) Organization, Qualification and Authority. Each Subsidiary is duly incorporated, validly existing and in good standing under the Laws of its jurisdiction of formation and is duly qualified and in good standing in each jurisdiction in which the nature of its business or the ownership or leasing of its properties makes such qualification or authorization necessary [other than where the failure to be qualified, authorized or in good standing would not have a Material Adverse Effect].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Clause Elements:\n" +
                "\n" +
                "1.1 Disclosure\n" +
                "\n" +
                "The Disclosure Schedule lists each Subsidiary and identifies the jurisdiction of formation and names of the officers and directors of each Subsidiary.\n" +
                "\n" +
                "1.2 Organization and Power\n" +
                "\n" +
                "Each Subsidiary is a corporation, partnership or limited liability company duly incorporated or organized, validly existing and in good standing under the laws of its jurisdiction of incorporation or organization.\n" +
                "\n" +
                "1.3 Authority\n" +
                "\n" +
                "Each Subsidiary has all requisite corporate power and authority to own, operate, lease and encumber its properties and carry on its business as currently conducted, except as would not, individually or in the aggregate, have a Material Adverse Effect.\n" +
                "\n" +
                "1.4 Qualification\n" +
                "\n" +
                "Each Subsidiary is duly licensed or qualified to do business as a foreign corporation, partnership or limited liability company, as applicable, in each other jurisdiction in which the character of its properties or in which the transaction of its business makes such qualification necessary, except where the failure to be so licensed or qualified would not, individually or in the aggregate, have a Material Adverse Effect.\n" +
                "\n" +
                "1.5 Capitalization\n" +
                "\n" +
                "All the outstanding shares of capital stock of, or other equity interests in, each Subsidiary of the Company have been validly issued and are fully paid and non-assessable and are owned, directly or indirectly, by the Company free and clear of all pledges, liens, charges, mortgages, encumbrances or security interests of any kind or nature whatsoever (collectively, \"Liens\"), other than Permitted Liens.\n" +
                "\n" +
                "1.6 No Other Ownership Interests\n" +
                "\n" +
                "Except for the capital stock of, or voting securities or equity interests in, its Subsidiaries, the Company does not own, directly or indirectly, any capital stock of, or voting securities or equity interests in, any corporation, partnership, joint venture, association or other entity.\n" +
                "\n" +
                "1.7 Subsidiaries' Ownership Interests\n" +
                "\n" +
                "None of the Subsidiaries own any capital stock or other securities of, or any proprietary interest in, any Person or entity.\n" +
                "\n" +
                "1.8 Information Supplied\n" +
                "\n" +
                "Copies of the organizational documents of each such Subsidiary, in each case as amended to date, have been made available to [Party], are complete and correct. No amendments are pending.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        // Statements: Representations: Corporate Documents: Books, Records and Minutes 

        f = new Fragment("Books and Records. The minute books, books of account, stock records, and other corporate and financial records of the Seller [all of which have been made available to the Buyers] are [to the knowledge of Seller] complete and correct [in all material respects], have been maintained in accordance with reasonable business practices for companies similar to the Seller, [and are stated in reasonable detail and accurately and fairly reflect the transactions and dispositions].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Alternatives:\n" +
                "Detailed Specification of Books and Records\n" +
                "\n" +
                "[Seller] has provided or made available to [Buyer] or its counsel true, correct and complete copies of:\n" +
                "\n" +
                "(a) all documents identified on the Disclosure Schedule,\n" +
                "\n" +
                "(b) the Organizational Documents, as currently in effect,\n" +
                "\n" +
                "(c) the Subsidiary Organizational Documents, as currently in effect,\n" +
                "\n" +
                "(d) the minute books containing records of all proceedings, consents, actions and meetings by the boards of directors of the [Seller] and its Subsidiaries, committees of the boards of directors of the [Seller] and its Subsidiaries, and stockholders of the [Seller] and its Subsidiaries,\n" +
                "\n" +
                "(e) the stock ledger, journal and other records of the [Seller], and\n" +
                "\n" +
                "(f) all permits, orders, authorizations and consents issued by any Governmental Entity with respect to the [Seller], its Subsidiaries or any securities of the [Seller] or any [Seller] Subsidiary, and all applications for such permits, orders, authorizations and consents.\n" +
                "\n" +
                "Representations\n" +
                "\n" +
                "The books, records and accounts:\n" +
                "\n" +
                "(a) are true, correct and complete in all respects,\n" +
                "\n" +
                "(b) have been maintained in accordance with reasonable business practices on a basis consistent with prior years, and\n" +
                "\n" +
                "(c) are stated in reasonable detail and accurately and fairly reflect the transactions and dispositions of the assets and properties of the [Seller].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

         // Statements: Representations: Corporate Documents: Books, Records and Minutes: Examples Stockpurchase Agreements 

        f = new Fragment("Examples:\n" +
                "\n" +
                "Books and Records. The books and records of the Company and its Subsidiaries delivered or made available to Buyer are complete and accurate in all material respects and reflect the assets, liabilities, prospects, business, financial condition and results of operations of the Company and its Subsidiaries and have been maintained in accordance with prudent business practices.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Books and Records.\n" +
                "\n" +
                "(a) The books, records and accounts of the Company (i) are accurate and complete in all material respects and have been maintained on a basis consistent with prior years; and (ii) are stated in reasonable detail and accurately and fairly reflect the material transactions and material dispositions of the respective assets and properties of the Company.\n" +
                "\n" +
                "(b) The Company has implemented and maintained a system of internal accounting controls sufficient to provide reasonable assurances that (i) transactions are executed in accordance with management's general or specific authorization; (ii) transactions are recorded as necessary (A) to permit preparation of financial statements in conformity with generally accepted accounting principles consistently applied and (B) to maintain accountability for assets; and (iii) the amount recorded for assets on the respective books and records of the Company is compared with the existing assets at reasonable intervals and appropriate action is taken with respect to any differences.\n" +
                "\n" +
                "(c) The corporate minute books of the Company made available to Buyer are the only minute books of the Company, and contain complete and accurate records in all material respects of all actions taken and summaries of all meetings held by the stockholders and Board of Directors of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Securities Matters: Capitalization 

        f = new Fragment("Short Form\n" +
                "\n" +
                "The Disclosure Schedule sets forth all of the holders of all of the issued and outstanding capital stock.\n" +
                "\n" +
                "Detailed Clause\n" +
                "\n" +
                "(a) Description of Stock. The authorized capital stock consists of [______] shares of Common Stock [and [______] shares of Preferred Stock, par value $[____] per share].\n" +
                "\n" +
                "As of [DATE]:\n" +
                "\n" +
                "(i) [______] shares of Common Stock were issued and outstanding;\n" +
                "\n" +
                "(ii) [no/______] shares of Preferred Stock were issued or outstanding;\n" +
                "\n" +
                "(iii) [no/______] shares of Common Stock were held in treasury; and\n" +
                "\n" +
                "(iv) [______] shares of Common Stock were reserved for issuance pursuant to Company Stock Options.\n" +
                "\n" +
                "(b) Ownership. The Disclosure Schedules sets forth, as of [DATE], each Stockholder and the number of shares of Common Stock owned by, and the number of shares of Common Stock subject to Options and Warrants owned by, such Stockholder and the exercise price per share of any such Options or Warrants.\n" +
                "\n" +
                "(c) Due Authorization. All issued and outstanding shares of Common Stock are [and all shares of Common Stock which may be issued pursuant to the exercise of a Company Stock Option will be, when issued in accordance with the terms thereof], duly authorized, validly issued, fully paid and non-assessable, [and were issued in compliance with all applicable state and federal securities Laws].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Additional Elements:\n" +
                "Except as set forth in the Disclosure Schedule,\n" +
                "\n" +
                "(i) No Other Stock Reserved for Issuance. There are no outstanding or authorized options, warrants, purchase rights, subscription rights, conversion rights, exchange rights, or other contracts or commitments that could require the Company to issue, sell, or otherwise cause to become outstanding any of its capital stock.\n" +
                "\n" +
                "(ii) No Other Voting Interests. There are no bonds, debentures, notes or other indebtedness of the Company or any of its Subsidiaries having the right to vote (or convertible into, or exchangeable for, securities having the right to vote) on any matters on which holders of Company Common Stock may vote.\n" +
                "\n" +
                "(iii) No Contractual Obligations. There are no voting trusts, proxies or other similar agreements or understandings to which the Company or any Company Subsidiary is a party or by which the Company or any Company Subsidiary is bound with respect to the voting of any shares of capital stock of the Company or any Company Subsidiary.\n" +
                "\n" +
                "(iv) No Transfer Restrictions. There are no contractual obligations or commitments of any character restricting the transfer of, or requiring the registration for sale of, any shares of capital stock.\n" +
                "\n" +
                "(v) No Unpaid Dividends. There are no accrued or unpaid dividends with respect to any capital stock.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Securities Matters: SEC Reports (USA) 

        f = new Fragment("(a) Filed. [SELLER] has [timely] filed all forms, reports, statements, certifications and other documents (including all exhibits, amendments and supplements thereto) required to be filed by it with the SEC pursuant to the Exchange Act or other applicable United States federal securities Laws (\"SEC Reports\").\n" +
                "\n" +
                "(b) Compliant. As of their respective filing dates, [except as disclosed in the Disclosure Letter] the SEC Reports complied as to form in all material respects with the applicable requirements of the Securities Act and Exchange Act.\n" +
                "\n" +
                "(c) Accurate. [Except as disclosed in the Disclosure Letter], none of the SEC Reports when filed with the SEC and, if amended, as of the date of such amendment contained any untrue statement of a material fact or omitted to state a material fact required to be stated or incorporated by reference therein or necessary in order to make the statements therein, in the light of the circumstances under which they were made, not misleading.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Optional added elements: \n" +
                "(d) Subsidiaries. None of the [SELLER]'s Subsidiaries is required to file any forms, reports or other documents with the SEC.\n" +
                "\n" +
                "(e) Provision of Reports. [SELLER] has made available to [Buyer] an accurate and complete copy of each: (i) final registration statement, prospectus, report, schedule and definitive proxy statement filed since [date], by the Company with the SEC pursuant to the Securities Act or the Exchange Act and prior to the date hereof, and (ii) communication mailed by the Company to its shareholders since [date].\n" +
                "\n" +
                "(f) Comment Letters. [SELLER] has made available to [Buyer] copies of all comment letters received by the Company from the SEC since [Date], and relating to the [SELLER]'s SEC Documents, together with all [SELLER]'s written responses.\n" +
                "\n" +
                "Note: all reports and comments are available through EDGAR.\n" +
                "\n" +
                "(g) No Unresolved Comments. There are no material unresolved comments issued by the staff of the SEC with respect to any of the SEC Reports.\n" +
                "\n" +
                "(h) No Investigation. Except for normal examinations conducted by a Regulatory Agency in the ordinary course of the business of [SELLER], no Regulatory Agency has initiated any proceeding or, to the knowledge of the [SELLER], investigation into the business or operations of the [SELLER], except where such proceedings or investigation will not, either individually or in the aggregate, have a Material Adverse Effect on the [SELLER].\n" +
                "\n" +
                "(i) No Unresolved Disputes. There is no unresolved violation, criticism, or exception by any Regulatory Agency with respect to any report or statement relating to any examinations of the [SELLER] which, in the reasonable judgment of the [SELLER], will, either individually or in the aggregate, have a Material Adverse Effect on the [SELLER].\n" +
                "\n" +
                "(j) Payment of Fees. [SELLER] has paid all fees and assessments due and payable in connection the filing of the Reports, except where the failure to file such report, registration or statement or to pay such fees and assessments, either individually or in the aggregate, will not have a Material Adverse Effect on the [SELLER].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Financial Matters: Financial Statements

        f = new Fragment("Information Supplied. The Disclosure Schedule sets forth [accurate and complete] audited consolidated financial statements [for the period] and unaudited consolidated interim financial statements [for the period].\n" +
                "\n" +
                "Preparation. The Financial Statements are prepared [in accordance with GAAP applied] on a consistent basis for all periods presented [except as may be indicated in the notes to such financial statements].\n" +
                "\n" +
                "Accuracy.\n" +
                "\n" +
                "(a) The Financial Statements [accurately set out / fairly present, in all material respects] the financial condition and operating results as of the dates, and for the periods, indicated therein, subject to normal year-end audit adjustments.\n" +
                "\n" +
                "[(b) The Financial Statements do not and will not contain any untrue statement of a Material fact or omit to state a Material fact required to be stated therein or necessary in order to make the statements made therein, and in light of the circumstances under which they were made, were not misleading.]\n" +
                "\n" +
                "Note: For public companies, the financial statements provision will typically refer to SEC reports rather than attach the statements as exhibits.\n" +
                "\n" +
                "\"Each of the consolidated financial statements (including, in each case, any notes thereto) contained in the SEC Reports was prepared in accordance with United States generally accepted accounting principles (\"GAAP\") applied on a consistent basis throughout the periods indicated.\"");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Additional: \n" +
                "Auditor Reports. The Independent auditor's report with respect the financial statements do not contain any reservation or supplementary information and such auditor's report(s) certify, and will certify, as applicable such accounts unconditionally and without qualifications.\n" +
                "\n" +
                "Projections. The Disclosure Schedule sets forth as of [DATE], financial projections [for the period] that have been prepared in good faith based on assumptions believed to be reasonable at the time of preparation thereof and there are no statements or conclusions in any of the projections which are based upon or include information known to be misleading in any material respect or which fail to take into account material information regarding the matters reported therein. As of [DATE], the [PARTY] believed that the projections are reasonable and attainable; it being recognized, however, that projections as to future events are not viewed as facts and that the actual results during the period or periods covered by the projections are likely to differ from the projected results and the differences could be material.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Assets: Title of Assets

        f = new Fragment("Valid Ownership. [To the Seller's knowledge], the [Seller] [is the sole and exclusive owner and] has good and marketable [and indefeasible] title to, or a valid leasehold interest in, the properties and assets it uses, located on its premises, shown on the Financial Statements, or acquired after the date thereof, free and clear of all Encumbrances, except for properties and assets disposed of in the Ordinary Course of Business since the date of the Financial Statements.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Optional:\n" +
                " Exceptions to Encumbrances. (...free and clear of all Encumbrances,) except for (1) any lien for current taxes not yet due and payable, (2) [minor] liens that have arisen in the ordinary course of business and that do not [in any case or in the aggregate] materially detract from the value of the assets subject thereto or materially impair the operations of the [Buyer], and (3) liens described (in another section).\n" +
                "\n" +
                "Condition of Assets. [To the Seller's knowledge], each asset is in good operating condition and has been maintained and repaired [in accordance with normal industry practice], subject to normal wear and tear.\n" +
                "\n" +
                "Fitness for Purpose. Each asset is usable for business [as it is currently conducted] and suitable for the purpose for which it is currently used [and is proposed to be used].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Assets: Title of Assets: Securities Purchase Agreement

        f = new Fragment("Examples:\n" +
                "Title to Assets. The Company and the Subsidiaries have good and marketable title in fee simple to all real property owned by them that is material to the business of the Company and the Subsidiaries and good and marketable title in all personal property owned by them that is material to the business of the Company and the Subsidiaries, in each case free and clear of all Liens, except for Liens as do not materially affect the value of such property and do not materially interfere with the use made and proposed to be made of such property by the Company and the Subsidiaries and Liens for the payment of federal, state or other taxes, the payment of which is neither delinquent nor subject to penalties. Any real property and facilities held under lease by the Company and the Subsidiaries are held by them under valid, subsisting and enforceable leases of which the Company and the Subsidiaries are in compliance.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Assets: Real Property

        f = new Fragment("Owned Property. Neither the Company nor any of its Subsidiaries currently own [or in the past have owned] any real property.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "[The Owned Property Schedule] sets forth a sufficient description of all real property owned by the Company. [Except as expressly listed on The Owned Property Schedule], the Company has good and indefeasible title to all property listed on [The Owned Property Schedule] free and clear of Encumbrances. Neither the Company nor any of its Subsidiaries has leased or otherwise granted to any Person the right to use or occupy any portion of such Property. There are no outstanding options, rights of first offer or rights of first refusal to purchase any portion or interest of such Property.\n" +
                "\n" +
                "Leased Property. [The Leased Property Schedule] lists all real property the Company leases. [The Leased Property Schedule] also contains an accurate and complete list of all leases and other Contracts in respect of real property the Company leases, accurate and complete copies [and all material modifications] of which have been delivered to Buyer. The Company has a good and valid leasehold interest in each lease listed on [The Leased Property Schedule]. Except as set forth on [The Leased Property Schedule], each of the leases and Contracts required to be listed on [The Leased Property Schedule] is Enforceable against the Company in accordance with its terms and is in full force and effect. To the knowledge of the Company, no termination event or condition or default of a material nature exists under any of the leases on [The Leased Property Schedule].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Optional Additional: \n" +
                "Clear of Liens. The Company has a good and valid leasehold interest in each parcel of real property used by it free and clear of all Liens, except (i) Liens for Taxes and general and special assessments not in default and payable without penalty and interest, and (ii) Permitted Liens. No party to any such Lease has given written, or to the knowledge of the Company other, notice to the Company of or made a claim in writing against the Company in respect of any breach or default thereunder.\n" +
                "\n" +
                "No Default. The execution, delivery and performance of this Agreement [and related agreements] and the consummation of the transactions contemplated hereby [and thereby] will not cause a material default under any such Lease.\n" +
                "\n" +
                "No Assignment. The Company has not assigned, transferred, conveyed, mortgaged or encumbered any interest in any leased property subject to such Lease.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Assets: Real Property: Examples Stock Purchase Agreement

        f = new Fragment("Examples:\n" +
                "Real Property.\n" +
                "\n" +
                "(a) Section 3.20(a)(i) of the Seller Disclosure Schedule sets forth a true and complete list of all real property and interests in real property owned in fee by the Companies (individually, an \"Owned Property\" and collectively the \"Owned Properties\") and identifies any material lease, or material reciprocal easement relating thereto. Section 3.20(a)(ii) of the Seller Disclosure Schedule sets forth a true and complete list of all real property and interests in real property leased or subleased by the Companies (individually, a \"Leased Property\" and collectively the \"Leased Properties\", and the Owned Properties and Leased Properties being collectively the \"Company Real Property\") which are material to the Business (each a \"Material Leased Property\"). ISP or one of its Subsidiaries, as applicable, holds good and marketable fee title to the Owned Properties, and except in any such case, as would not individually or in the aggregate, have a material impact on the continued use thereof, in each case free and clear of all Encumbrances, except for the Permitted Encumbrances.\n" +
                "\n" +
                "(b) With respect to each of the Owned Properties, except in any such case, as would not individually or in the aggregate, have a material impact on the continued use thereof, (i) other than as set forth on Section 3.20(a)(i) of the Seller Disclosure Schedule, the Companies have not leased or otherwise granted to anyone the right to use or occupy any of the Owned Properties or any portion thereof, (ii) other than as set forth on Section 3.20(a)(i) of the Seller Disclosure Schedule there are no outstanding contracts, options, rights of first offer or rights of first refusal to purchase any of the Owned Properties or any portion thereof or interest therein, (iii) all improvements upon any Owned Property are in good condition and repair (ordinary wear and tear excepted) and sufficient in all material respects for the conduct of the Business as currently conducted and (iv) there is no condemnation or other proceeding in eminent domain, pending or threatened, affecting any parcel of an Owned Property or any portion thereof or interest therein.\n" +
                "\n" +
                "(c) With respect to the Leased Properties, except in any such case, as would not individually or in the aggregate, have a material impact on the continued use thereof, as of the date of this Agreement, (i) each lease of a Leased Property is valid, subsisting and in full force and effect, (ii) none of the Companies has subleased, licensed or otherwise granted anyone the right to use or occupy any Material Leased Property or any portion thereof or collaterally assigned or granted any other security interest in any such leasehold estate or any interest therein, (iii) all improvements upon a Material Leased Property are in good condition and repair (ordinary wear and tear excepted) and sufficient in all material respects for the conduct of the Business as currently conducted and (iv) there is no condemnation or other proceeding in eminent domain pending or threatened, affecting any portion of a Material Leased Property.\n" +
                "\n" +
                "(d) The Company Real Property comprises in all material respects all of the real property utilized by the Companies in the conduct of the Business in the Ordinary Course.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Financial Matters: Assets: Real Property: Examples Stock Issuance Agreement

        f = new Fragment("Title to Properties. (Real Property) The Corporation does not own any real property or any buildings or other structures, nor have options or any contractual obligations to purchase or acquire any interest in real property. Schedule 5.14(b) lists all real property leases to which the Corporation is a party and each amendment thereto. All such current leases are in full force and effect, are valid and effective in accordance with their respective terms, and there is not, under any of such leases, any existing default or event of default (or event that with notice or lapse of time, or both, would constitute a default). The Corporation, in its capacity as lessee, is not in violation of any zoning, building or safety ordinance, regulation or requirement or other law or regulation applicable to the operation of its leased properties, nor has it received any notice of violation with which it has not complied.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Assets: Tangible Property

        f = new Fragment("Tangible assets. [Except as explicitly set forth in the Tangible Asset Schedule], the Company owns or leases all buildings, machinery, equipment, and other tangible assets necessary for the conduct of its business as currently conducted and as currently proposed to be conducted by the Company. [To the Seller's knowledge] Each such tangible asset [has been maintained in accordance with normal industry practice,] is free from material defects (patent and latent), is in good operating condition and repair (subject to normal wear and tear), and is suitable for the purposes for which it currently is used and currently is proposed to be used by the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional:\n" +
                "Permitted Encumbrances. The Company has good and marketable title to all personal property owned by it, free and clear of any and all Encumbrances other than Permitted Encumbrances. With respect to each such item of personal property, (i) there are no leases, subleases, licenses, options, rights, concessions or other agreements, written or oral, granting to any party or parties the right of use of any portion of such item of personal property, (ii) there are no outstanding options or rights of first refusal in favor of any other party to purchase any such item of personal property or any portion thereof or interest therein and (iii) there are no parties (other than the Company) who are in possession of or who are using any such item of personal property.\n" +
                "\n" +
                "Exceptions.\n" +
                "\n" +
                "...other than items or categories of items having a book value of less than [a specific dollar amount] individually.\n" +
                "\n" +
                "...except as would not reasonably be expected to have a materially adverse effect.\n" +
                "\n" +
                "See also Real Property & Title to Assets");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Financial Matters: Assets: Tangible Property: Example Agreement for Purchase and Sale of Common Stock

        f = new Fragment("Examples:\n" +
                "Tangible Assets. Except as set forth on Schedule 2(l), the Company owns or leases all buildings, machinery, equipment, and other tangible assets necessary for the conduct of their businesses as presently conducted. The tangible assets, in the aggregate, are free from material defects (patent and latent), have been maintained in accordance with normal industry practice, are in good operating condition and repair (subject to normal wear and tear), and are suitable for the purposes for which it presently is used, except where the failure of the tangible assets to meet the foregoing standards would not have a material adverse effect on the Business, financial condition, operations, results of operations, or future prospects of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

                // Statements: Representations: Financial Matters: Assets: Tangible Property: Example Agreement and Plan of Merge

        f = new Fragment("Tangible Personal Property. MediSync owns, and holds valid leasehold interests in or valid contractual rights to use, all of the assets, tangible and intangible, used by, or necessary for the conduct of the business of, MediSync. The tangible physical assets of MediSync are in good working order, normal wear and tear excepted, are being used or are useful in the business of MediSync at its present level of activity and have been maintained and repaired in accordance with the terms of any lease or other agreements covering any such tangible physical assets. The tangible physical assets of MediSync are of a type sufficient to conduct the business of MediSync as now being conducted.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Financial Matters: Assets: IP


        f = new Fragment("(a) Disclosure. The Intellectual Property Disclosure Schedule contains a list of all Intellectual Property owned, developed, commissioned, licensed or used by [Party], other than commercially available or off-the-shelf software.\n" +
                "\n" +
                "(b) Intellectual Property Rights. Except as set forth in the Intellectual Property Disclosure Schedule, [Party];\n" +
                "\n" +
                "(i) owns the right to use all Intellectual Property;\n" +
                "\n" +
                "(ii) such Intellectual Property is sufficient in order to conduct [Party]’s business and operations;\n" +
                "\n" +
                "(iii) the use of any Intellectual Property does not violate any license agreement with any third party or infringe on the rights of any Person; and\n" +
                "\n" +
                "(iv) to the knowledge of the [Party], no person is infringing the Intellectual Property.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Clause Elements:\n" +
                "1.       Definitions\n" +
                "\n" +
                "1.1.  Registered Intellectual Property.  The term Registered Intellectual Property means:\n" +
                "\n" +
                "(a)     patents and patent applications [including any abandoned applications],\n" +
                "\n" +
                "(b)     trademark and service mark registrations and applications,\n" +
                "\n" +
                "(c)     copyright registrations and applications, and\n" +
                "\n" +
                "(d)     domain names and domain name registrations.\n" +
                "\n" +
                "1.2.  Commercial Intellectual Property. The term Commercial Intellectual Property means:\n" +
                "\n" +
                "(a)     all products and services currently produced, manufactured, marketed, licensed, sold or distributed by [Party] and\n" +
                "\n" +
                "(b)     all products and services currently under development that the [Party] intends to make commercially available within [x] months of the date of this agreement.\n" +
                "\n" +
                "1.3.  Operational Intellectual Property. The term Operational Intellectual Property means all :\n" +
                "\n" +
                "(a)     technology platforms,\n" +
                "\n" +
                "(b)     software,\n" +
                "\n" +
                "(c)     web sites, publications, databases and other content; and\n" +
                "\n" +
                "(d)     business processes\n" +
                "\n" +
                "material to the operation of the business.\n" +
                "\n" +
                "1.4. Conceptual Intellectual Property. The term Conceptual Intellectual Property means:\n" +
                "\n" +
                "(a)     inventions, whether or not patentable, whether or not reduced to practice or whether or not yet made the subject of a pending patent application or applications,\n" +
                "\n" +
                "(b)     ideas and conceptions of potentially patentable subject matter, including, without limitation, any patent disclosures, whether or not reduced to practice and whether or not yet made the subject of a pending Patent application or applications,\n" +
                "\n" +
                "(c)     trade secrets and confidential, technical or business information (including ideas, formulas, compositions, designs, inventions, and conceptions of inventions whether patentable or un-patentable and whether or not reduced to practice),\n" +
                "\n" +
                "(d)    technology (including know-how), manufacturing and production processes and techniques, methodologies, research and development information, drawings, specifications, designs, plans, proposals, technical data, copyrightable works, financial, marketing and business data, pricing and cost information, business and marketing plans and customer and supplier lists and information,\n" +
                "\n" +
                "Collectively, Registered Intellectual Property, Commercial Intellectual Property, Operational Intellectual Property and Conceptual Intellectual Property are referred to as “Intellectual Property.” “Proprietary Intellectual Property” means Intellectual Property owned, commissioned, developed, or created by the [Party].\n" +
                "\n" +
                "2.       Disclosure\n" +
                "\n" +
                "2.1.  Registered Intellectual Property.  For each item of Registered Intellectual Property, the Intellectual Property Disclosure Schedule accurately identifies:\n" +
                "\n" +
                "(a)     the applicable jurisdiction,\n" +
                "\n" +
                "(b)    registration number (or application number) and\n" +
                "\n" +
                "(c)     the date issued (or date filed).\n" +
                "\n" +
                "2.2.  Commercial Intellectual Property.  For each item of Commercial Intellectual Property, the Intellectual Property Disclosure Schedule accurately identifies:\n" +
                "\n" +
                "(a)     each of the products and services (by name and version number) currently produced, manufactured, marketed, licensed, sold, or distributed by the [Party] such agreements being referred to as \"Out-Bound License Agreements\") and\n" +
                "\n" +
                "(b)    each product and service currently under development by [Party] that the [Party] intends to make generally commercially available within [x] months after the Agreement Date;\n" +
                "\n" +
                "(c)     and each license agreement pursuant to which any person has been granted any license under, or otherwise has received or acquired any right (whether or not currently exercisable) or interest in, any Intellectual Property.\n" +
                "\n" +
                "2.3.  Operational Intellectual Property.  The Intellectual Property Disclosure Schedule lists all material agreements under which the [Party] licenses from a third party material Intellectual Property that is used by the [Party] in the conduct of its business, except for off-the-shelf software programs that the [Party] use in the ordinary course of business (such agreements being referred to as \"In-Bound License Agreements\").\n" +
                "\n" +
                "3.       Ownership\n" +
                "\n" +
                "3.1.  Proprietary Rights.\n" +
                "\n" +
                "(a)     Good Title. [Party] is the sole owner and possesses all right, title, and interest in and to all Proprietary Intellectual Property;\n" +
                "\n" +
                "(b)    Free of Liens and Encumbrances. No Proprietary Intellectual Property is subject to any lien, encumbrances, outstanding injunction, judgment, order, decree, ruling, or charge of which the [Party] has received notice;\n" +
                "\n" +
                "(c)     Assignment of Rights. [Party] has secured valid written assignments from all employees and consultants who contributed to the creation or development of Proprietary Intellectual Property of the rights to such contributions that the [Party] does not already own by operation of law.\n" +
                "\n" +
                "(d)    Third Party Software. The Intellectual Property Disclosure Schedule sets forth a complete and accurate list of all third party software that is incorporated into, embedded into or distributed with, installed with, or otherwise utilized by any Commercial Intellectual Property and all third party software which, to the [Party]’s knowledge is necessary to build, install or embed such third party software, indicating for each whether such software has been modified by the [Party].\n" +
                "\n" +
                "(e)     Open Source Code. The Intellectual Property Disclosure Schedule accurately identifies and describes, since [Date]:\n" +
                "\n" +
                "(i)  each item of Open Source Code that is contained in, distributed with, or used in the development of the Company Products or from which any part of any Company Product is derived,\n" +
                "\n" +
                "(ii)  the applicable license terms for each such item of Open Source Code, and\n" +
                "\n" +
                "(iii)  the Company Product or Company Products to which each such item of Open Source Code relates.\n" +
                "\n" +
                "To the knowledge of the [Party], no [Proprietary] Intellectual Property is, in whole or in part, subject to the provision of any open source or other type of license agreement or distribution model that:\n" +
                "\n" +
                "(i)  requires the distribution or making available of the source code for any [Proprietary] Intellectual Property,\n" +
                "\n" +
                "(ii)   prohibits or limits the [Party] from charging a fee or receiving consideration in connection with sublicensing or distributing any [Proprietary] Intellectual Property,\n" +
                "\n" +
                "(iii)  (except as specifically permitted by Law, grants any right to any person or otherwise allows any such person to decompile, disassemble or otherwise reverse-engineer any [Proprietary] Intellectual Property, or\n" +
                "\n" +
                "(iv)  requires the licensing of any [Proprietary] Intellectual Property for the purpose of making derivative works (any such open source or other type of license agreement or distribution model \n" +
                "described in clause (i), (ii), (iii) or (iv) above, a \"Limited License\").\n" +
                "\n" +
                "To the knowledge of the [Party], no [Proprietary] Intellectual Property incorporates, embeds or are distributed or installed with, any software that is subject to a Limited License, nor does any Owned Intellectual Property constitute a derivative work of, dynamically link with or is otherwise designed to interact with any such software.\n" +
                "\n" +
                "\"Open Source Code\" means any software that is generally publicly distributed without charge, in source code form, and under a license that authorizes each licensee to modify and distribute such software and sublicense such rights to other parties without charge (e.g., Linux). Open Source Code includes without limitation software licensed under the GNU's General Public License (GPL) or Lesser/Library GPL, the Mozilla Public License, the Netscape Public License, the Sun Community Source License, the Sun Industry Standards License, the BSD License, and the Apache License. The Company is in material compliance with the terms and conditions of all applicable licenses for such Open Source Materials.\n" +
                "\n" +
                "(f)      Government Services. No government funding or facilities of a university, college, other educational institution or educational research center was used in the development of the [Commercial] [Proprietary] Intellectual Property, computer software programs or applications owned by the [Party] . To the knowledge of the [Party], no current or former employee, consultant or independent contractor of the [Party] who was involved in, or who contributed to, the creation or development of any [Commercial][Proprietary] Intellectual Property rights has performed services for the government, for a university, college or other educational institution or for an educational research center during a period of time during which such employee, consultant or independent contractor was also performing services for the [Party] whereby the performance of such services has caused such government, university, college, educational institution or educational research center to obtain rights to any [material] [Commercial] [Proprietary] Intellectual Property rights.\n" +
                "\n" +
                "(g)    Escrow. [Party] does not have any escrow agreement or arrangement between [Party] and a licensee that would permit such licensee or any other person to obtain a copy of [Party]'s source code and program documentation upon the liquidation, dissolution or winding up of [Party].\n" +
                "\n" +
                "3.2.  No Infringement. [To the knowledge of Party,], [Party] has not infringed upon or otherwise violated the Intellectual Property Rights of any third party[, except for any such infringement that, individually or in the aggregate, has not had and would not reasonably be expected to have a material adverse effect.] [To the knowledge of Party,], no third party or any third party products or services or other operation of a third party’s business is infringing upon or otherwise violating in any material respect any [Proprietary] Intellectual Property.\n" +
                "\n" +
                "3.3.  No Pending Claims of Suits. no action, suit, proceedings, hearing, investigation, charge, complaint, claim, or demand of which the [Party] has received notice is pending or, to the knowledge of the [Party], is threatened that challenges the legality, validity, enforceability, registrations, use, or ownership of any [Proprietary] Intellectual Property;\n" +
                "\n" +
                "4.       Commercial Intellectual Property and Out-Bound Licenses\n" +
                "\n" +
                "4.1.  Out-Bound License Rights. To the knowledge of the [Party],\n" +
                "\n" +
                "(a)     each In-Bound License Agreement is valid, binding, and in full force and effect;\n" +
                "\n" +
                "(b)    each In-Bound Licenses Agreement will continue to be valid, binding, and in full force and effect on identical terms following the consummation of the transactions contemplated hereby;\n" +
                "\n" +
                "(c)     [Party] is not in default of any such License-In Agreement, and no event has occurred that constitutes a material default or material breach;\n" +
                "\n" +
                "4.2.  Exclusive Rights. [Party] has not granted any other exclusive rights in any [Commercial] [Proprietary] Intellectual Property to any person.\n" +
                "\n" +
                "4.3.  Most Favored Nation Clauses. The Intellectual Property Disclosure Schedule sets forth a complete and accurate list of any and all Out-Bound Intellectual Property Licenses that include a \"most favored nations\" clause, a \"most favored licensee\" clause or similar clause purporting to grant a licensee terms at least as favorable as any other licensee. Neither the Company nor any Company Subsidiary is obligated by any other Contract to provide such terms to any licensee.\n" +
                "\n" +
                "4.4.  No Restrictions. Except for the contracts set forth in the Intellectual Property Disclosure Schedule, [Party] is not subject to, any agreement containing any covenant or other provision that in any way materially limits or restricts the ability of any of [Party] to use, exploit, assert, or enforce any Intellectual Property anywhere in the world.\n" +
                " \n" +
                "4.5.  Warranties.\n" +
                "\n" +
                "(a)     Performance Warranties. [To the knowledge of the [party]], all Commercial Intellectual Property provided by or through the [Party] to customers on or prior to the Closing Date:\n" +
                "\n" +
                "(i)  conforms in all material respects to applicable contractual commitments, express warranties and representations provided to customers in such Contracts; and\n" +
                "\n" +
                "(ii)  the [Party] does not have any material liability (and, to the knowledge of the [Party], there is no legitimate basis for any present or future action, suit, proceeding, hearing, investigation, charge, complaint, claim or demand against the [Party] giving rise to any material liability relating to the foregoing contracts) for replacement or repair thereof or other damages in connection therewith in excess of any warranty reserves reflected on the [Party]’s balance sheet.\n" +
                "\n" +
                "(b)    Error Tracking.\n" +
                "\n" +
                "a.       Intellectual Property Disclosure Schedule contains a complete and accurate list of all known bugs, defects, and errors in each version of the Commercial Intellectual Property\n" +
                "\n" +
                "b.      [Party] has a policy and procedure for tracking material bugs, errors and defects of which it becomes aware in any Company Products or Services, and maintains a database covering the foregoing.\n" +
                "\n" +
                "(c)     No Harmful Code. [To the knowledge of the [party]], no Commercial Intellectual Property contains any “back door,” drop dead device,” “time bomb,” “Trojan Horse,” “virus,” or “worm” (as such terms are commonly understood in the software industry) or other code designed or intended to have, or capable or performing, any of the following functions:\n" +
                "\n" +
                "(i)  disrupting, disabling, harming, otherwise materially impeding in any manner the operation of, or providing unauthorized access to, a computer system or network or other device; or\n" +
                " \n" +
                "(ii)  damaging, destroying or preventing access to any data or file without the user’s consent.\n" +
                "\n" +
                "(d)    Security Protection. For all software used by the Company and the Company Subsidiaries in providing Company Products or Services, or in developing or making available any of the Company Products or Services, the Company and the Company Subsidiaries have used reasonable business judgment in determining whether or not to implement security patches or upgrades that are generally available for that software.\n" +
                "\n" +
                "5.       Operational Intellectual Property and In-Bound Licenses\n" +
                "\n" +
                "5.1.  In-Bound License Rights. To the knowledge of the [Party],\n" +
                "\n" +
                "(a)     each In-Bound License Agreement is valid, binding, and in full force and effect;\n" +
                "\n" +
                "(b)    each In-Bound Licenses Agreement will continue to be valid, binding, and in full force and effect on identical terms following the consummation of the transactions contemplated hereby;\n" +
                "\n" +
                "(c)     [Party] is not in default of any such License-In Agreement, and no event has occurred that constitutes a material default or material breach;\n" +
                "\n" +
                "5.2.  Rights Transferred. All [Proprietary] Intellectual Property will be owned by or available for use by the [Buyer] immediately subsequent to the closing on the same terms and conditions as currently owned or used.\n" +
                "\n" +
                "5.3.  Sufficient Intellectual Property. [To the knowledge of Party,] [Party] owns or licenses all Intellectual Property Rights needed to conduct its business as currently conducted and planned to be conducted.\n" +
                "\n" +
                "5.4.  Error Free. [To the knowledge of Party,] all [Operational] Intellectual Property that is licensed from any other person is free from any significant defect or programming or documentation error including bugs, logic errors or failures of the Software to operate in all material respects as described in the related documentation and conforms to the specifications thereof.\n" +
                "\n" +
                "6.       Information and Data Protection Policies\n" +
                "\n" +
                "6.1.  Protection of Rights.\n" +
                "\n" +
                "(a)     Confidential Information. The Company has taken all necessary and appropriate steps to protect and preserve the confidentiality of all [Conceptual] Intellectual Property not otherwise protected by patents, patent applications or copyright (\"Confidential Information\").\n" +
                "\n" +
                "(b)    Confidentiality Agreements. [Party] has a policy requiring each of its employees and contractors to execute proprietary information and confidentiality agreements substantially in the [Party]’s standard forms and all current and former employees and contractors of the Company and each Subsidiary have executed such an agreement.\n" +
                "\n" +
                "(c)     Non-Disclosure. All use, disclosure or appropriation of Confidential Information owned by the [Party] by or to a third party has been pursuant to the terms of a written agreement between the [Party] and such third party. All use, disclosure or appropriation of Confidential Information not owned by the Company or a Subsidiary has been pursuant to the terms of a written agreement between the Company or a Subsidiary and the owner of such Confidential Information, or is otherwise lawful.\n" +
                "\n" +
                "(d)    Third Party Rights. [To the knowledge of Party,] no current or former employee, consultant or independent contractor of the Company or any Company Subsidiary:\n" +
                "\n" +
                "(i)   is in material violation of any term or covenant of any employment contract, patent disclosure agreement, invention assignment agreement, nondisclosure agreement, noncompetition agreement or any other contract with any other party by virtue of such employee's, consultant's or independent contractor's being employed by, or performing services for, the [Party] or using trade secrets or proprietary information of others without permission; or\n" +
                "\n" +
                "(ii)  has developed any technology, software or other copyrightable, patentable or otherwise proprietary work for the [Party] that is subject to any contract under which such employee, consultant or independent contractor has assigned or otherwise granted to any third party any rights (including Intellectual Property) in or to such technology, software or other copyrightable, patentable or otherwise proprietary work.\n" +
                "\n" +
                "(e)     No Liability. [To the knowledge of Party,] neither the employment of any employee of the [Party], nor the use by the [Party] of the services of any consultant or independent contractor subjects the [Party] to any liability to any third party for improperly soliciting such employee, consultant or independent contractor to work for the [Party], whether such liability is based on contractual or other legal obligations to such third party.\n" +
                "\n" +
                "6.2.  Data Privacy.\n" +
                "\n" +
                "(a)     Compliance with Laws and Regulations. [Party] is in compliance in all material respects with all applicable laws, rules, regulations, and its contractual obligations governing the collection, interception, storage, receipt, purchase, sale, transfer and use (\"Collection and Use\") of personal, consumer, or customer information (\"Customer Information\").\n" +
                "\n" +
                "(b)    Compliance with Policies. [Party]’s Collection and Use of Customer Information is in accordance in all material respects with the [Party]’s privacy policy as published on its website or any other privacy policies presented to consumers or customers and to which the [Party] is bound or otherwise subject and any contractual obligations of the Company to its customers regarding privacy.\n" +
                "\n" +
                "(c)     Data Protection. [Party] takes commercially reasonable actions consistent with industry practice to protect the confidentiality, integrity and security of all Customer Information and to prevent the unauthorized Collection and Use of Customer Information.\n" +
                "\n" +
                "(d)    No Violation. The execution or delivery of this Agreement or the performance of the [Party]’s obligations hereunder, will not materially violate any such applicable law, rule, or regulation or any of the [Party]’s privacy policies or any contractual obligation of the [Party] governing the Collection and Use of Customer Information.\n" +
                "\n" +
                "6.3.  Export Law Compliance. [Party] is in compliance in all material respects with all relevant export, re-export and import laws applicable to [Party] and any Intellectual Property. [[Party] has not shipped or provided any Intellectual Property for delivery to, and are not currently providing any Services in or to, a country, entity or individual in violation of any applicable export or re-export laws, including, without limitation, such laws and regulations promulgated or enforced by the United States Department of Treasury or United States Department of Commerce , and are not currently providing any Services, to a country or an individual in violation of any export or re-export laws.]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


// Statements: Representations: Financial Matters: Assets: Inventory

        f = new Fragment("Inventory. All of the Inventories [shown on the Balance Sheet/Financial Statements] are of a [good and merchantable] quality usable or salable in the ordinary course of business [and fit for the purpose for which it was procured or manufactured]. The value at which the Inventories are carried [on the Balance Sheet/Financial Statements] reflects an inventory valuation policy of the Company which is consistent with industry practice [, the Company's past practice] and in accordance with GAAP, consistently applied.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Additional: \n" +
                "Replenishment. Since the Balance Sheet Date, the Company has continued to replenish inventories in a normal and customary manner consistent with past practices.\n" +
                "\n" +
                "Continued Supply. The Company has not received written or oral notice that it will experience in the foreseeable future any difficulty in obtaining, in the desired quantity and quality and at a reasonable price and upon reasonable terms and conditions, the raw materials, supplies or component products required for the manufacture, assembly or production of its products.\n" +
                "\n" +
                "Unusable Inventory. Since Balance Sheet Date, adequate provision has been made on Company's books in the ordinary course of business in accordance with GAAP applied on a consistent basis for all material slow-moving, obsolete or unusable inventories to their estimated useful or scrap values, and such inventory reserves are adequate to provide for such slow-moving, obsolete or unusable inventory and inventory shrinkage.\n" +
                "\n" +
                "Full Possession. None of the Inventories are in the possession of others, except Inventories in transit or on consignment in the ordinary course of its business. Inventories are not subject to any claim with respect to the use of materials held on consignment.\n" +
                "\n" +
                "Inventory in Distribution. As of the Balance Sheet Date, the inventory of the Company in the distribution channel does not exceed an aggregate of [a specific dollar amount].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples: Inventory. All Inventory reflected on the Recent Balance Sheet (a) had a commercial value at least equal to the value shown on the face of the Recent Balance Sheet, (b) is valued in accordance with GAAP at the lower of cost (on a first-in, first-out basis) or market, less any costs of disposal and (c) consists of a quality and quantity usable and saleable in the ordinary course of business, except for slow-moving, damaged or obsolete items (all of which have been written down to net realizable value or for which adequate reserves have been provided and all intercompany profit or other mark-up has been eliminated). All Inventory manufactured or purchased since the date of the Recent Balance Sheet consists of a quality and quantity usable and saleable in the ordinary course of business. Except as set forth in Schedule 3.8, all Inventory is located at, or is in transit to or from, the Facilities. Except as set forth in Schedule 3.8 (which contains a description of any exceptions and related amounts), (i) all work-in-process contained in Inventory constitutes items in process of production pursuant to Contracts entered into (including orders taken) in the ordinary course of business by regular customers of the Company with no recent history of credit problems with respect to the Company, (ii) neither the Company nor any such customer is in material breach of the terms of any obligation to the other, and (iii) no valid grounds exist for any set off of amounts billable to such customers on the completion of the Contract to which work-in-process relates. All work-in-process consists of a quality ordinarily produced in accordance with the requirements of the Contracts to which such work-in-process relates and will require no rework with respect to services performed prior to the Closing except to the extent labor attributable to such rework has been reasonably taken into consideration in valuing the work-in-process pursuant to Section 2.4. The Company will have on hand as of the Closing such quantities of Inventory as are reasonably required to continue the business of the Company immediately after the Closing consistent with past practice. The Company has purchased all Inventory in compliance with applicable Laws and Orders relating to imported goods.\n" +
                "\n" +
                "Stock Purchase Agreement, May 25, 2011, Foley & Lardner LLP; John P. Zampino, Esq.)\n" +
                "\n" +
                "Inventories; Consignment. The Inventory included in the Purchased Assets consists of a quantity and quality usable and salable in the Ordinary Course, is not physically damaged, previously used, obsolete, discontinued or excess, subject only to the reserve, if any, for inventory write-down set forth on the Financial Statements. Except as set forth in Part 3.7, Seller does not hold any Inventory on consignment or have title to any Inventory in the possession of others.\n" +
                "\n" +
                "Asset Purchase Agreement, May 25, 2011, Bryan Cave LLP; Porter, Wright, Morris & Arthur, LLP");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Assets: Account Receivables


        f = new Fragment("Accounts Receivable.\n" +
                "\n" +
                "(a) Company has made available to Purchaser a list of all accounts receivable of the Company and each Subsidiary reflected on the Financial Statements (\"Accounts Receivable\") along with a range of days elapsed since invoice.\n" +
                "\n" +
                "(b) All Accounts Receivable of the Company arose [from bona fide sales of goods and services] in the ordinary course of business and are carried at values determined in accordance with GAAP consistently applied. No person has any lien on any of such Accounts Receivable and no request or agreement for deduction or discount has been made with respect to any of such Accounts Receivable.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional: Adequate Reserves for Bad Debt. The reserves for doubtful accounts reflected on the Balance Sheet are adequate.\n" +
                "\n" +
                "Fully Collectable. To the Company's knowledge, the Accounts Receivable are and will be collectible in the ordinary and usual course of business and are not subject to any valid set-off or counterclaim, do not represent obligations for goods sold on consignment, on approval or on a sale-or-return basis or subject to any other repurchase or return arrangement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples: Accounts Receivable. All of the accounts receivable of the Company arose in bona fide arms' length transactions in the ordinary course of business, are valid and enforceable claims, are not subject to any known set-off or counterclaim, and, to the Company's knowledge, are fully collectible in the normal course of business, after deducting the allowance for doubtful accounts stated in the Base Balance Sheet in accordance with GAAP. Except as set forth in Section 2.10 of the Disclosure Schedule, as of the date hereof, the Company does not have any accounts receivable from any Person which is known by the Company to be an Affiliate of the Company or any of its directors, officers, employees or stockholders.\n" +
                "\n" +
                "Preferred Stock Purchase Agreement, June 15, 2011, [Bingham McCutchen LLP]\n" +
                "\n" +
                "Accounts Receivable. The accounts and notes receivable of the Business as of June 30, 2010 are all summarized in Section 2.21 of the Company Disclosure Schedule, and (a) arose from bona fide sales transactions in the ordinary course of business, consistent with past practice, and are payable on ordinary trade terms, (b) are valid and binding obligations of the respective debtors, (c) are not subject to any valid set-off, defense or counterclaim and are fully collectable in the ordinary course of business, except to the extent described in Section 2.21 of the Company Disclosure Schedule, and (d) do not represent obligations for goods sold on consignment, on approval or on a sale-or-return basis or subject to any other repurchase or return arrangement.\n" +
                "\n" +
                "Asset Purchase Agreement, June 7, 2011, [Naschitz, Brandes & Co; Squire, Sanders & Dempsey L.L.P.]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Liabilities: Indebtedness: Litigation

        f = new Fragment("Seller Representation\n" +
                "\n" +
                "There is no suit, action or proceeding pending or, to the knowledge of [Seller], threatened against [Seller] that, individually or in the aggregate, would reasonably be expected to have a Material Adverse Effect.\n" +
                "\n" +
                "Buyer Representation\n" +
                "\n" +
                "There is no Litigation pending or, to the knowledge of [Purchaser], threatened that is reasonably likely to prohibit or restrain the ability of [Purchaser] to enter into this Agreement or to consummate the transactions contemplated hereby.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Clause Elements\n" +
                "(a) No Proceedings. [Except as set forth in the Litigation Disclosure Schedule,] There is no claim, suit, action or proceeding pending or [, to the knowledge of the [PARTY]], threatened against the [PARTY] [or any basis therefore] that, individually or in the aggregate, would reasonably be expected to have a Material Adverse Effect.\n" +
                "\n" +
                "(b) No Judgments. [Except as set forth in the Litigation Disclosure Schedule,] There is no Judgment outstanding against the [PARTY] [or any of their respective assets] that, individually or in the aggregate, would reasonably be expected to have a Material Adverse Effect.\n" +
                "\n" +
                "(c) No Investigations. [Except as set forth in the Litigation Disclosure Schedule,] [PARTY] has not received any written notification of, and to the knowledge of the [PARTY] there is no, investigation by any Governmental Entity involving the [PARTY] [or any of their respective assets] that, individually or in the aggregate, would reasonably be expected to have a Material Adverse Effect.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Elements: \n" +
                "(d) Potential Litigation. [To the knowledge of [PARTY]], there is no valid basis for any claim, action, suit, arbitration, proceeding or investigation.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "There are no controversies pending or, to the knowledge of the [PARTY], threatened, which controversies have resulted, or could reasonably be expected to result, in an action, suit, proceeding, claim, arbitration or investigation before any Governmental Entity, foreign or domestic or arbitrator.\n" +
                "\n" +
                "(e) Recent Litigation. The Litigation Disclosure Schedule sets forth a description of any material disputes that have been settled or resolved in the past (number of) years.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples: \n" +
                "Litigation. There is no action, suit, investigation, claim, complaint, demand, summons, cease and desist letter, subpoena, Injunction, notice of violation or other proceeding pending against or threatened in writing against Allied World, Merger Sub or any of their respective Subsidiaries or pending against or threatened in writing against any present or former officer, director or employee of Allied World or any Allied World Subsidiary in connection with which Allied World or any Allied World Subsidiary has an indemnification obligation, before any Governmental Entity (other than insurance and reinsurance claims litigation or arbitration arising in the ordinary course of business), which, if determined or resolved adversely in accordance with the plaintiff's or claimant's demands, would, individually or in the aggregate, reasonably be expected to be material to Allied World and its Subsidiaries, taken as a whole, or would reasonably be expected to prevent or materially delay the consummation of the transactions contemplated hereby. There is no Order outstanding against Allied World or any of its Subsidiaries which would, individually or in the aggregate, reasonably be expected to be material to Allied World and its Subsidiaries, taken as a whole, or would reasonably be expected to prevent or materially delay the consummation of the transactions contemplated hereby.\n" +
                "\n" +
                "Agreement and Plan of Merger, April 21, 2011, [Willkie Farr & Gallagher LLP; Gibson, Dunn & Crutcher LLP]\n" +
                "\n" +
                "Litigation. There are no pending or overtly threatened actions, claims, orders, decrees, investigations, suits or proceedings by or before any governmental authority, arbitrator, court or administrative agency which would have a Material Adverse Effect, or which question the validity of this Agreement or any action taken or to be taken by the Company in connection herewith, or which might result in any impairment of the right or ability of the Company to enter into or perform his obligations under this Agreement.\n" +
                "\n" +
                "Stock Purchase Agreement, June 14, 2011");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Financial Matters: Liabilities: Indebtedness: No Undisclosed Liabilities

        f = new Fragment("No Undisclosed Liabilities. [To the Company's knowledge], the Company has no material obligations or liabilities [in excess of (some dollar amount), individually or in the aggregate] [matured or unmatured, fixed or contingent] other than (i) those set forth or adequately provided for in the Balance Sheet, (ii) those incurred in the ordinary course of business and not required to be set forth in the Balance Sheet under GAAP, (iii) those incurred in the ordinary course of business since the Company Balance Sheet Date and consistent with past practice, and (iv) those incurred in connection with the execution of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Additional: \n" +
                "Material Adverse Effect.\n" +
                "\n" +
                "(other than...) (v) liabilities and obligations which in the aggregate would not have a Material Adverse Effect on the Company.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "(...no material obligations or liabilities...) which have had or would reasonably be expected to have, individually or in the aggregate, a Material Adverse Effect on the Company (, other than...)");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Examples: \n" +
                "Absence of Undisclosed Liabilities. Neither the Company, nor any of the Company Subsidiaries or the entities listed on Schedule 3.1(b) of the Company Disclosure Schedule has any liabilities of a nature required by GAAP to be reflected in a consolidated balance sheet or the notes thereto, other than: (i) liabilities identified as such in the \"liabilities\" column of the Company Balance Sheet; (ii) liabilities that have been incurred by the Company since the date of the Company Balance Sheet in the ordinary course of business consistent with past practice that, individually or in the aggregate, do not exceed $100,000; (iii) liabilities for performance of obligations under Company Contracts; and (iv) liabilities and obligations under this Agreement and investment banking, accounting, legal and other fees incurred by the Company in connection with the negotiation, execution and delivery of this Agreement.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 13, 2011, Paul, Hastings, Janofsky & Walker, LLP; Ropes & Gray LLP\n" +
                "\n" +
                "No Undisclosed Liabilities.\n" +
                "\n" +
                "(a) There are no Liabilities of any kind of the Company Group, except (i) as disclosed in the Most Recent Balance Sheet; (ii) Liabilities not required to be disclosed on the Most Recent Balance Sheet pursuant to GAAP, which are not in any event in excess of $100,000 in the aggregate; (iii) Liabilities incurred in the ordinary course of business since the Most Recent Balance Sheet, whether or not required to be disclosed on a balance sheet pursuant to GAAP, which are not in the aggregate in excess of $100,000; (iv) obligations under the Material Contracts and obligations under customer and supplier Contracts or to employees, in each case, entered into in the ordinary course of business; and (v) as set forth in the Disclosure Letter.\n" +
                "\n" +
                "(b) Full and timely payment has been made, or otherwise properly accrued on the books and records of the Company and any member of the Controlled Group, of all amounts that the Company and any member of the Controlled Group are required under the terms of the Company Employee Plans to have paid as contributions to such Company Employee Plans on or prior to the date hereof (excluding any amounts not yet due). All Liabilities of the Company and its Subsidiaries in respect of any Company Employee Plan (including workers compensation) that have not been paid as of the date of this Agreement, have been properly accrued on the Company's Interim Financial Statements in compliance with GAAP. There are no reserves, assets, surpluses or prepaid premiums with respect to any Company Employee Plan that is an employee welfare benefit plan as defined in Section 3(1) of ERISA.\n" +
                "\n" +
                "Merger Agreement, June 6, 2011, Paul, Weiss, Rifkind, Wharton & Garrison LLP; Cooley LLP");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Financial Matters: Insurance

        f = new Fragment("Insurance. The Insurance Disclosure Schedule sets forth all [current and previous] insurance policies of the Company and its Subsidiaries (\"Insurance Policies\").\n" +
                "\n" +
                "Proper Amount. The Company has policies of insurance and bonds of the type and in the amounts customarily carried by persons conducting businesses or owning assets similar to those of the Company.\n" +
                "\n" +
                "Pending Claims. There is no material claim pending under any of the Insurance Policies as to which coverage has been questioned, denied or disputed by the underwriters of such policies or bonds.\n" +
                "\n" +
                "Premiums Current. All premiums due and payable under all Insurance Policies have been paid and the Company and its Subsidiaries are otherwise in compliance with the terms of such policies and bonds. The Company has no knowledge of any threatened termination of, or material premium increase with respect to, any of such policies.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional:\n" +
                "Full Force. Each of the Insurance Policies remains in full force and effect [and has been in full force and effect for the past (number of) years].\n" +
                "\n" +
                "No Refusals or Limitations. The Company has not been refused any insurance with respect to its business or its Assets and [to the Company's knowledge] no insurance carrier has limited insurance coverage for which the Company has applied for or carried.\n");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples:\n" +
                "Insurance. Schedule 3.13 of the Company Disclosure Schedule lists: (i) all insurance policies (including all workers' compensation insurance policies) covering the business, properties or assets of the Company and the Company Subsidiaries or any Company Employee Benefit Plan or its fiduciaries; (ii) the premiums and coverages of such policies; and (iii) all claims in excess of $100,000 made against any such policies since January 1, 2008. All such policies are in effect, and accurate and complete copies of all such policies have been made available to Parent. The Company has not received written notice of the cancellation or threat of cancellation of any of such policies.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 23, 2011, [Paul, Hastings, Janofsky & Walker, LLP; Ropes & Gray LLP]\n" +
                "\n" +
                "Insurance.\n" +
                "\n" +
                "(a) The Company has been covered since its formation by insurance in scope and amount customary and reasonable for the business in which it has been engaged during such period.\n" +
                "\n" +
                "(b) Schedule 3.19(a) sets forth the following information with respect to each insurance policy (including policies providing property, casualty, directors and officers liability, professional liability insurance, errors and omissions insurance, or workers' compensation coverage and bond and surety arrangements) to which the Company has been a party, a named insured or otherwise the beneficiary of coverage at any time since 2002: (i) the name, address and telephone number of the agent or broker; (ii) the name of the insurer, the name of the policyholder and the name of each covered insured; (iii) the policy number and the period of coverage; (iv) the scope and amount of coverage; (v) a copy of each such policy; (vi) a copy of each broker or consultant Contract and a description of the fees and/or commissions; and (vii) a list of any person or entity listed as an additional insured under any such insurance policy. Except as set forth on Schedule 3.19(b), each of such insurance policies is legal, valid, binding, enforceable and in full force and effect and will continue to be legal, valid, binding, enforceable and in full force and effect on identical terms following consummation of the transactions contemplated by this Agreement and any other Transaction Document. Neither the Company, nor to the Knowledge of the Company, any other Person, is in breach or default under any such insurance policy (including with respect to the payment of premiums or the giving of notices), and to the Knowledge of the Company, no event has occurred that, with notice or the lapse of time or both, would constitute such a breach or default, or permit termination, modification or acceleration, under any such insurance policy. To the Knowledge of the Company, no party to any such insurance policy has repudiated any provision thereof.\n" +
                "\n" +
                "(c) Schedule 3.19(c) describes any self insurance arrangements affecting the Company.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 7, 2011, [Hawley Troxell Ennis & Hawley LLP]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Regulatory Matters: Business Matters (Products and Customers)

        f = new Fragment("(a) Disclosure. The Customer and Supplier Disclosure Schedule sets forth a complete and accurate list of:\n" +
                "\n" +
                "(i) Customers. the (certain number of) largest customers, measured by the revenue generated over the [12-month] period preceding the Agreement Date (collectively \"Major Customers\"), and sets forth the approximate total revenue to the Company for this period for each Major Customer.\n" +
                "\n" +
                "OR\n" +
                "\n" +
                "all customers which individually accounted for more than (a certain percentage)% of the Company's gross revenues during the [12-month] period preceding the Agreement Date [or during the last full fiscal year] (collectively \"Major Customers\"), and sets forth the approximate total revenue to the Company for this period for each Major Customer.\n" +
                "\n" +
                "(ii) Suppliers. the (certain number of) largest suppliers, measured by the dollar amount of purchases over the [12-month] period preceding the Agreement Date (collectively, \"Major Suppliers\"), and sets forth approximate total purchases by the Company for this period for each Major Supplier.\n" +
                "\n" +
                "(b) No Material Decrease or Termination. During the [12-month] period preceding the Agreement Date, no Major Customer or Major Supplier has:\n" +
                "\n" +
                "(i) materially reduced [or materially and adversely modified] its relationship with the Company,\n" +
                "\n" +
                "(ii) cancelled or otherwise terminated its relationship with the Company, or\n" +
                "\n" +
                "(iii) [to the Company's knowledge] notified the Company of its intention to materially reduce, cancel or otherwise terminate its relationship with the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("No Material Disputes. During the [12-month] period preceding the Agreement Date, there has not been any material dispute between the Company and any Major Customer or Major Supplier.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples: \n" +
                "Customers; Customer Relationships. Section 2.27 of AUSA Disclosure Schedule sets forth a complete list of the five largest clients and customers of AUSA or the AUSA Subsidiaries on a combined basis for the six months ended on AUSA Balance Sheet Date and the year ended December 31, 2007. To the knowledge of AUSA, there are no facts or circumstances that are likely to result in the loss of any such client or customer of AUSA or any of the AUSA Subsidiaries or a material change in the relationship of AUSA or any of the AUSA Subsidiaries with any such client or customer, other than in the Ordinary Course of Busines\n" +
                "\n" +
                "Agreement and Plan of Merger, June 2, 2011, [Alliance HealthCard, Inc.; Access Plans USA, Inc.]\n" +
                "\n" +
                "Customers and Suppliers.\n" +
                "\n" +
                "(a) Schedule 4.29(a)(i) lists the names of the top ten (10) customers of the Subject Companies (on a consolidated basis) by dollar volume of sales to such customers for the twelve (12) month period ended December 31, 2010. Since December 31, 2009, except as set forth on Schedule 4.29(a)(ii), no such customer has materially reduced its business with the Subject Companies or has provided written or oral notice to the Subject Companies (other than Rexam Mega), or to the Selling Parties' Knowledge, Rexam Mega, indicating that it intends to materially reduce or terminate its business with the Subject Companies.\n" +
                "\n" +
                "(b) Schedule 4.29(b)(i) lists the names of the top ten (10) suppliers of the Subject Companies (on a consolidated basis) by dollar volume of purchases from such suppliers for the twelve (12) month period ended December 31, 2010. Since December 31, 2009, except as set forth on Schedule 4.29(b)(ii), no such supplier has materially reduced its business with the Subject Companies or has provided written or oral notice to the Subject Companies (other than Rexam Mega), or to the Selling Parties' Knowledge, Rexam Mega, indicating that it intends to materially reduce or terminate its business with the Subject Companies.implied warranties, and The Company has no Liability (and there is no basis for any present or future Proceedings against it giving rise to any Liability) for replacement or repair thereof or other damages in connection therewith, subject only to the reserve for product warranty claims set forth on the face of the Financial Statements (and in any notes thereto) as adjusted for the passage of time through the Closing Date in the Ordinary Course of Business. No product manufactured, sold, leased, or delivered by the Company is subject to any guaranty, warranty, or other indemnity beyond the applicable standard terms and conditions of sale.\n" +
                "\n" +
                "Equity Purchase Agreement, June 24, 2011, [Wachtell, Lipton, Rosen & Katz; Moore & Van Allen PLLC]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

          // Statements: Representations: Regulatory Matters: Material Contracts)

        f = new Fragment("Short Definition\n" +
                "Material Contracts. The Material Contracts Disclosure Schedule contains a list of all contracts which are material to the Company (\"Material Contracts\").\n" +
                "\n" +
                "Itemized Definition\n" +
                "Material Contracts. The Material Contracts Disclosure Schedule contains a list of all contracts and agreements which are material to the Company (\"Material Contracts\"). \"Material Contracts\" shall include, without limitation, the following:\n" +
                "\n" +
                "(a) any Contract (or group of related Contracts) for the lease of personal property to or from any Person providing for lease payments in excess of $(some dollar amount) per annum;\n" +
                "\n" +
                "(b) any Contract that involves performance of services or delivery of goods or materials by the Company [or its subsidiaries];\n" +
                "\n" +
                "(c) any Contract that involves performance of services or delivery of goods or materials to Company [or its subsidiaries] of an amount or value in excess of $(some dollar amount);\n" +
                "\n" +
                "(d) any Contract concerning an investment or interest in a limited liability company, partnership, joint venture, or similar arrangement;\n" +
                "\n" +
                "(e) any Contract (or group of related Contracts) under which it has created, incurred, assumed, or guaranteed any Liability for borrowed money or any capitalized lease in excess of $(some dollar amount), or under which it has imposed or suffered to exist an Encumbrance on any of its assets;\n" +
                "\n" +
                "(f) any Contract concerning confidentiality or purporting to limit the right of the Company [or its subsidiaries] to engage or compete in any line of business;\n" +
                "\n" +
                "(g) any profit sharing, stock option, stock purchase, stock appreciation, deferred compensation, severance, or other similar Contract for the benefit of its current or former directors, officers, and employees;\n" +
                "\n" +
                "(h) any collective bargaining Contract;\n" +
                "\n" +
                "(i) any Contract for the employment of any individual on a full-time, part-time, consulting, or other basis providing annual compensation in excess of $(some dollar amount) or providing severance benefits;\n" +
                "\n" +
                "(j) any Contract under which it has advanced or loaned or guaranteed any loan in any amount to any of its directors or officers or to the Company or, outside the Ordinary Course of Business, to its employees; and\n" +
                "\n" +
                "(k) any other Contract (or group of related Contracts) the performance of which involves consideration in excess of $(some dollar amount).");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional additional Definitional:\n" +
                "SEC Material Contracts. (...shall include...) any \"material contract\" (as such term is defined in Item 601(b)(10) of RegulationS-K promulgated under the Securities Act) filed as an exhibit to the Company SEC Reports.\n" +
                "\n" +
                "Excluded Contracts. Notwithstanding the above section, \"Material Contracts\" shall not include and contract that is (i) is terminable upon one hundred twenty (120) days' or less notice without a penalty or premium, (ii) will be fully performed or satisfied as of or prior to the Agreement Date, or (iii) is solely between the Company and one or more of its Subsidiaries or is solely between the Company's Subsidiaries.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Representations Related to Material Contracts: No Breach. The Company [or any of its Subsidiaries] or [, to the knowledge of the Company,] any other party is not in breach or violation of, or in default under, any Material Contract.\n" +
                "\n" +
                "No Notice of Default. As of the date of this Agreement, the Company [or any of its Subsidiaries] has not received any written notice of default under any Material Contract.\n" +
                "\n" +
                "No Event Causing Breach. [To the Company's knowledge, ] No event has occurred which would result in a breach or violation of, or a default under, any Material Contract.\n" +
                "\n" +
                "No Cancellation. No Material Contract has been cancelled by the Company or any other party.\n" +
                "\n" +
                "No Claims. [To the Company's knowledge, ] there are no claims by any parties pending under any Material Contract.\n" +
                "\n" +
                "Fully Enforceable. Each \"Material Contract\" is legal, valid, binding, enforceable and in full force and effect with respect to the Company and, to the Company's knowledge, the other parties to the contract.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Additional Elements: \n" +
                "No Anticipated Material Change. The Company does not anticipate any termination of or change to, or receipt of a proposal with respect to, any Material Contract as a result of executing this Agreement.\n" +
                "\n" +
                "Copies Disclosed. The Company has disclosed true and complete copies of all Material Contracts together with all amendments, waivers or other changes.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Examples: \n" +
                "Contracts.\n" +
                "\n" +
                "(a) Section 3.11(a) of the Company Disclosure Letter sets forth a complete and accurate list of all contracts and agreements to which the Company or any of its Subsidiaries is a party as of the date of this Agreement (i) in connection with which or pursuant to which the Company and its Subsidiaries paid, in the aggregate during the fiscal year ended January 29, 2011, more than $90,000,000 to any vendor for merchandise resold by the Company and its Subsidiaries, (ii) related to indebtedness for borrowed money owed by the Company or any of its Subsidiaries having an outstanding amount in excess of $2,500,000 individually, other than any such indebtedness between or among any of the Company and any of its Subsidiaries, (iii) that prohibits or otherwise restricts, in any material respect, the Company or any of its Subsidiaries from freely engaging in business anywhere in the world, (iv) that is a \"material contract\" (as such term is defined in Item 601(b)(10) of Regulation S-K of the SEC) with respect to the Company and its Subsidiaries, (v) that is an employment or consulting agreement with any executive officer or other employee of the Company or any of its Subsidiaries or member of the Company Board earning an annual salary from the Company or any of its Subsidiaries in excess of $175,000, (vi) that is a joint venture, partnership, limited liability company or other similar agreement or arrangement in connection with which or pursuant to which the Company and its Subsidiaries paid, or were paid, in the aggregate during the fiscal year ended January 29, 2011, more than $5,000,000 or (vii) that involves the acquisition from another person or disposition to another person (other than acquisitions or dispositions of inventory, merchandise, products, services, properties and other assets in the Ordinary Course of Business), of assets or capital stock or other equity interests for aggregate consideration under such contract (or series of related contracts) in excess of $10,000,000, in each case other than those that are terminable by the Company or any of its Subsidiaries on no more than 30 days' notice without material liability or financial obligation to the Company or any of its Subsidiaries (collectively, the \"Company Material Contracts\"). The Company has made available to the Buyer a complete and accurate copy of each Company Material Contract.\n" +
                "\n" +
                "(b) Each Company Material Contract is a valid and binding agreement of the Company or one of its Subsidiaries, as the case may be, and, to the Knowledge of the Company, any counterparty thereto, and is in full force and effect except where the failure to be in full force and effect would not have a Company Material Adverse Effect. Neither the Company nor any of its Subsidiaries nor, to the Company's Knowledge, any other party to any Company Material Contract is in violation of or in default under (nor does there exist any condition which, upon the passage of time or the giving of notice or both, would cause such a violation of or default under) any Company Material Contract.\n" +
                "\n" +
                "(c) Except for any conflicts, violations, breaches, defaults, terminations, cancellations, accelerations, losses, penalties or Liens, and for any consents or waivers not obtained, that would not have a Company Material Adverse Effect, the execution and delivery of this Agreement by the Company do not, and the consummation by the Company of the transactions contemplated by this Agreement shall not conflict with, or result in any violation or breach of, or constitute (with or without notice or lapse of time, or both) a default (or give rise to a right of termination, cancellation or acceleration of any obligation or loss of any material benefit) under, require a consent or waiver under, constitute a change in control under, require the payment of a penalty under or result in the imposition of any Lien (other than a Permitted Lien) on the Company's or any of its Subsidiary's assets under, any of the terms, conditions or provisions of any Company Material Contract.\n" +
                "\n" +
                "(d) Neither the Company nor any of its Subsidiaries has entered into any transaction that would be subject to disclosure pursuant to Item 404 of Regulation S-K that has not been disclosed in the Company SEC Reports.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 29, 2011, [Latham & Watkins LLP; Simpson Thacher & Bartlett LLP; Wilmer Cutler Pickering Hale and Dorr LLP]\n" +
                "\n" +
                "Agreements, Contracts and Commitments. Schedule 2.9 of the Company Disclosure Letter identifies, except for the Company Contracts set forth in Schedule 2.13 of the Company Disclosure Letter and except for this Agreement and other agreements, contracts or commitments relating to the Contemplated Transactions:\n" +
                "\n" +
                "(a) each Company Contract relating to any deferred compensation, pension, profit-sharing or retirement plans;\n" +
                "\n" +
                "(b) each Company Contract relating to the employment of, or the performance of employment-related services by, any Person, including any employee, consultant or independent contractor, not terminable by the Company on ninety (90) days notice without liability;\n" +
                "\n" +
                "(c) each Company Contract relating to any agreement or plan, including, without limitation, any stock option plan, stock appreciation right plan or stock purchase plan, any of the benefits of which will be increased, or the vesting of benefits of which will be accelerated, by the occurrence of any of the Contemplated Transactions (either alone or in conjunction with any other event, such as termination of employment) or the value of any of the benefits of which will be calculated on the basis of any of the Contemplated Transactions;\n" +
                "\n" +
                "(d) each Company Contract relating to any agreement of indemnification or guaranty not entered into in the Ordinary Course of Business other than indemnification agreements between the Company and any of its respective officers or directors;\n" +
                "\n" +
                "(e) each Company Contract limiting the freedom of the Company to engage or participate, or compete with any other Person, in any line of business, market or geographic area, or to make use of any Intellectual Property to the extent that such Intellectual Property (i) is used in, necessary to, or would be infringed by the conduct of the Company Business and (ii) would be necessary to or would be infringed by the commercial manufacture, use, sale, or import of any of the Company's Clinical Products, as contemplated by the Company to be conducted following the Company's or its licensee's obtaining regulatory approval (if any) for such commercial manufacture, use, sale or import, or any Contract granting most favored nation pricing, exclusive sales, distribution, marketing or other exclusive rights, rights of refusal, rights of first negotiation or similar rights and/or terms to any Person;\n" +
                "\n" +
                "(f) each Company Contract relating to any agreement, contract or commitment relating to capital expenditures and involving obligations after the date of this Agreement in excess of $100,000 and not cancelable without penalty;\n" +
                "\n" +
                "(g) each Company Contract relating to any agreement, contract or commitment currently in force relating to the disposition or acquisition of material assets or any ownership interest in any Entity;\n" +
                "\n" +
                "(h) each Company Contract relating to any mortgages, indentures, loans, notes or credit agreements, security agreements or other agreements or instruments relating to the borrowing of money or extension of credit in excess of $100,000 or creating any material Encumbrances with respect to any assets of the Company or any loans or debt obligations with officers or directors of the Company;\n" +
                "\n" +
                "(i) each Company Contract relating to product supply, manufacturing, distribution or development, or the license of Intellectual Property used in the Company Business, to or from the Company (except for (i) standard biological material transfer agreements, (ii) standard licenses purchased by the Company for generally available commercial software, and (iii) Contracts in which the aggregate payments either to or by the Company are not in excess of $100,000);\n" +
                "\n" +
                "(j) each Company Contract with any Person, including without limitation any financial advisor, broker, finder, investment banker or other Person, providing advisory services to the Company in connection with the Contemplated Transactions; or\n" +
                "\n" +
                "(k) any other agreement, contract or commitment (i) which involves payment or receipt by the Company under any such agreement, contract or commitment of $100,000 or more in the aggregate or obligations after the date of this Agreement in excess of $100,000 in the aggregate, or (ii) that is material to the business or operations of the Company.\n" +
                "\n" +
                "The Company has made available to the Acquiror accurate and complete (except for applicable redactions thereto) copies of all material written Company Contracts, including all amendments thereto. Except as set forth on Schedule 2.9.1 of the Company Disclosure Letter, there are no material Company Contracts that are not in written form. Except as set forth on Schedule 2.9.2 of the Company Disclosure Letter, the Company has not, nor to the Company's Knowledge, as of the date of this Agreement has any other party to a Company Material Contract (as defined below), breached, violated or defaulted under (and no event has occurred which with the passage of time or the giving of notice would result in such breach, violation or default under), or received notice that it has breached, violated or defaulted under, any of the terms or conditions of any of the agreements, contracts or commitments to which the Company is a party or by which it is bound of the type described in clauses (a) through (o) above (any such agreement, contract or commitment, a \"Company Material Contract\") in such manner as would permit any other party to cancel or terminate any such Company Material Contract, or would permit any other party to seek damages which would, individually or in the aggregate, reasonably be expected to have a Company Material Adverse Effect. As to the Company, as of the date of this Agreement, each Company Material Contract is valid, binding on, and enforceable against, the Acquiror, and to the Acquiror's Knowledge, each other party thereto, and is in full force and effect, subject to: (i) laws of general application relating to bankruptcy, insolvency and the relief of debtors; and (ii) rules of law governing specific performance, injunctive relief and other equitable remedies. Except as set forth in Schedule 2.9.3 of the Company Disclosure Letter, the consummation of the Contemplated Transactions will not (either alone or upon the occurrence of additional acts or events) result in any material payment or payments becoming due from the Company (including as the Surviving Corporation) or the Acquiror to any Person under any Company Contract or give any Person the right to terminate or alter the provisions of any Company Contract. No Person is renegotiating, or has a right pursuant to the terms of any Company Material Contract to renegotiate, any material amount paid or payable to the Company under any Company Material Contract or any other material term or provision of any Company Material Contract. Schedule 2.9.4 of the Company Disclosure Letter identifies and provides a brief description of each proposed Contract as to which any written bid, offer, award, proposal, term sheet or similar written document has been submitted or received by the Company (other than term sheets and proposals provided by the Company or to the Company by any party related to the subject matter of this transaction or an Acquisition Proposal made prior to the date hereof) that if entered into by the Company would be a Company Material Contract.\n" +
                "\n" +
                "Agreement and Plan of Merger and Reorganization, June 27, 2011, [Perkins Coie LLP; Fenwick & West LLP]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Regulatory Matters: Product Warranties)


        f = new Fragment("Product Warranties. The Warranty Disclosure Schedule sets forth a complete and accurate statement [including all terms and conditions] of all written warranties, warranty policies, service, and maintenance agreements of the Company to any of its services or products. No products previously sold, delivered or leased nor any services performed by the Company are subject to any guarantee, warranty or other indemnity, other than those sold, delivered, leased or performed in accordance with the standard terms and conditions of sale or lease of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Additional Clause Elements: \n" +
                "Compliance. Each product manufactured, sold, leased or delivered by the Company is in compliance [in all material respects] [with all applicable federal, state, local and foreign laws and] with all warranties described in the Warranty Disclosure Schedule.\n" +
                "\n" +
                "Claims. The Warranty Disclosure Schedule also indicates all warranty and indemnity claims currently pending against Seller. Other than claims disclosed in the Warranty Disclosure Schedule, the Company has no Knowledge of any threatened claims for any product returns, warranty obligations or product services [that would exceed (a dollar amount) individually or in the aggregate] relating to any of its products or services.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Product Warranties. Each product sold, licensed, distributed or delivered by the Company has been in conformity with all applicable contractual commitments and all express and implied warranties, and the Company has no liability (and, to the Knowledge of the Company, there is no basis for any present or future action, suit, proceeding, hearing, investigation, charge, complaint, claim, or demand against the Company giving rise to any liability) for violations thereof or other damages in connection therewith, subject only to the reserve set forth in the Financial Statements. Except as set forth on Schedule 3.24, no product sold, licensed, distributed or delivered by the Company is subject to any guaranty, warranty, or other indemnity beyond the applicable standard terms and conditions of sale or lease. Schedule 3.24 includes copies of the standard terms and conditions of sale or lease for the Company (containing applicable guaranty, warranty, and indemnity provisions)\n" +
                "\n" +
                "Agreement and Plan of Merger, June 23, 2011, [Hawley Troxell Ennis & Hawley LLP]\n" +
                "\n" +
                "Product Warranties. Each product manufactured, sold, or delivered by the Company has been in conformity with all applicable contractual commitments and all express and implied warranties, and The Company has no Liability (and there is no basis for any present or future Proceedings against it giving rise to any Liability) for replacement or repair thereof or other damages in connection therewith, subject only to the reserve for product warranty claims set forth on the face of the Financial Statements (and in any notes thereto) as adjusted for the passage of time through the Closing Date in the Ordinary Course of Business. No product manufactured, sold, leased, or delivered by the Company is subject to any guaranty, warranty, or other indemnity beyond the applicable standard terms and conditions of sale.\n" +
                "\n" +
                "Stock Purchase Agreement, June 24, 2011, [Andrzej Piatkowski; Multi-Color Corporation]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Statements: Representations: Buyer's Representations: Sufficient funds)

        f = new Fragment("Sufficient Funds. Buyer has [and shall have at Closing] available cash resources and financing [or other sources of immediately available funds] in an amount sufficient to consummate the transactions contemplated in this Agreement [including resources to pay all expenses, fees and any assumed liabilities].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples:\n" +
                "Financing. Parent has cash and cash equivalents on hand and committed credit facilities (without restrictions on the use of such facilities for the funding of the Transactions for such purposes or conditions precedent with respect to funding) sufficient for payment of the Merger Consideration, to consummate the Merger in accordance with the terms of this Agreement and to satisfy all of its own and Sub's obligations under this Agreement.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 22, 2011, [Jones Day; Alston & Bird LLP]\n" +
                "\n" +
                "Availability of Funds. Buyer has available and will have available on the Closing Date sufficient funds to enable it to consummate the transactions contemplated hereby.\n" +
                "\n" +
                "Asset Purchase Agreement, June 20, 2011, [Covington & Burling LLP; Snell & Wilmer L.L.P.]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Statements: Representations: Sellers's Representations: No Brokers)

        f = new Fragment("No Brokers. [Except as set forth in the Fee Disclosure Schedule] The Company [or any of its officers, directors or employees] has not employed any broker or finder or incurred any liability for any brokerage fee, commission or finder's fee [or similar fees, commissions or reimbursement expenses] in connection with the transactions contemplated by this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Optional Additional Cluase Elements: Disclosure of Terms. The Company has delivered an accurate and complete copy of the Company's agreement with respect to each of the fee arrangements set forth in the Fee Disclosure Schedule.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Examples:\n" +
                "Finders' Fees. Except for UBS and Barrington, copies of the respective engagement agreements (including all amendments) of which have been provided to Parent prior to the execution of this Agreement, there is no investment banker, broker, finder or other intermediary that has been retained by or is authorized to act on behalf of the Company or any of its Subsidiaries who might be entitled to any fee or commission in connection with the transactions contemplated by this Agreement.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 23, 2011, [Greenberg Traurig, P.A.; Akerman Senterfitt]\n" +
                "\n" +
                "Financial Advisor. Except as set forth on Schedule 3.22 of the Acquiror Disclosure Letter, no broker, finder or investment banker is entitled to any brokerage fee, finder's fee, opinion fee, success fee, transaction fee or other fee or commission in connection with the Merger or any of the other Contemplated Transactions based upon arrangements made by or on behalf of the Acquiror.\n" +
                "\n" +
                "Agreement and Plan of Merger, June 7, 2011, [Perkins Coie LLP; Fenwick & West LLP]\n" +
                "\n" +
                "Brokers or Finders. The Company has not incurred, and will not incur, directly or indirectly, as a result of any action taken by the Company, any liability for brokerage or finders' fees or agents' commissions or any similar charges in connection with any of the Transaction Documents or any of the transactions contemplated hereby and thereby.\n" +
                "\n" +
                "Common Stock Purchase Agreement, June 7, 2011, [Wilson Sonsini Goodrich & Rosati, P.C.; Parsons Behle & Latimer]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


    
 // Statements: Representations: Warranties: Service Warranty 

        f = new Fragment("Consultant warrants that:\n" +
                "\n" +
                "(a) Authority. Consultant has the right to assign all rights in all work product.\n" +
                "\n" +
                "(b) Original. All work product will be Consultant's original work.\n" +
                "\n" +
                "(c) Non Infringement. The work product will not infringe, misappropriate or violate any intellectual property or other right of any person or entity.\n" +
                "\n" +
                "(d) Performance. The Services will be performed in a professional and workmanlike manner, consistent with industry standards.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Legitimacy\n" +
                "\n" +
                "(a) Authority. Consultant has the right to assign all rights in all work product.\n" +
                "\n" +
                "(b) Original. All work product will be Consultant's original work.\n" +
                "\n" +
                "(c) Non Infringement. The work product will not infringe, misappropriate or violate any intellectual property or other right of any person or entity.\n" +
                "\n" +
                "Compliance\n" +
                "\n" +
                "(a) Compliance with Law. Consultant shall comply with all applicable laws and Company safety rules in the course of performing the Services.\n" +
                "\n" +
                "(b) Licenses. Consultant has obtained all required licenses and permits, and such licenses and permits are in full force and effect.\n" +
                "\n" +
                "(e) Security. Consultant will comply with all the Company's physical and information security instructions and procedures.\n" +
                "\n" +
                "Quality\n" +
                "\n" +
                "(a) Performance. Contractor warrants that the Services will be performed under and comply with the highest standards of care, diligence and prudence practiced in the industry.\n" +
                "OR\n" +
                "(a) Performance. Consultant has the skill and staff to perform the Services in a timely, efficient, and professional manner.\n" +
                "\n" +
                "(a) Work Product. The Work Product will:\n" +
                "(i) comply with all specifications and acceptance criteria set forth in the relevant Work Orders; and \n" +
                "(ii) be [substantially] free from defects in material and workmanship.\n" +
                "\n" +
                "(b) Equipment and Material. Consultant will use only equipment, material and other suppplies that are of first quality, new and free of defects.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Supplier's Warranties. XYZ is entering into this Agreement in reliance on Supplier's special abilities with respect to performing the Services. Supplier accepts the relationship of trust and confidence established between XYZ and Supplier by this Agreement.\n" +
                "\n" +
                "Supplier represents and warrants that it and Supplier Related Parties:\n" +
                "\n" +
                "(a) will use its best efforts, skill, judgment, diligence and abilities in performing the Services;\n" +
                "\n" +
                "(b) will perform the Services in strict accordance with this Agreement and in accordance with the highest standards of care, skill, diligence and professional competence applicable to Suppliers engaged in provided similar services;\n" +
                "\n" +
                "(c) have the requisite skill and staff to perform the Services required hereunder fully, in a timely and efficient manner;\n" +
                "\n" +
                "(d) will cooperate with XYZ;\n" +
                "\n" +
                "(e) will perform the Services in accordance with all Applicable Law, including without limitation, privacy and similar laws;\n" +
                "\n" +
                "(f) have maintained, and will maintain throughout the Term, all licenses, certificates, permits and registrations required by Applicable Law to perform the Services;\n" +
                "\n" +
                "(g) the Services will not infringe, misappropriate or violate any patent, copyright, trade secret or other intellectual property right of any third party;\n" +
                "\n" +
                "(h) is not a party to any agreement, nor has executed any document whatsoever, which prohibits, and is not otherwise prohibited from, performing or delivering the Services, including without limitation, disclosing and assigning ideas, inventions, computer software, trade secrets and other intellectual property exclusively to XYZ hereunder;\n" +
                "\n" +
                "(i) has no conflict of interest with respect to the Services;\n" +
                "\n" +
                "(j) will not enter into any such contract or agreement, or execute any such document, which will create a conflict of interest or which will prevent it from freely performing any of the provisions of this Agreement;\n" +
                "\n" +
                "(k) will not knowingly incorporate confidential information of any person or entity not a party to this Agreement into any materials furnished to XYZ ");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

  // Statements: Representations: Warranties: Software Warranty 

        f = new Fragment("No Warranty. The Software is provided \"as is\" without warranty of any kind, either express or implied, including without limitation any implied warranties of condition, uninterrupted use, merchantability, fitness for a particular purpose, or non-infringement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Limited Software Warranty\n" +
                "\n" +
                "[Licensor] warrants for a period of [WARRANTY PERIOD] days following delivery of the Software that the Software will perform substantially in accordance with the Documentation.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("No oral or written information or advice given by [licensor], its dealers, distributors, agents or employees shall create a warranty, or in any way increase the scope of this warranty, and you may not rely on any such information or advice.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

      // Statements: Representations: Warranties: Disclaimer of Warranty

        f = new Fragment("Disclaimer of Warranties. [Licensor] disclaims to the [fullest] extent authorized by law any and all [other] warranties, whether express or implied, including, without limitation, any implied warranties of [title, non-infringement, quiet enjoyment, integration,] merchantability or fitness for a particular purpose.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Express Disclaimers\n" +
                "\n" +
                "Disclaimer of Warranties. Licensor disclaims to the fullest extent authorized by law any and all [other] warranties, whether express or implied, including, without limitation, any implied warranties of title, non-infringement, quiet enjoyment, integration, merchantability or fitness for a particular purpose. Without limitation of the foregoing, Licensor expressly does not warrant that:\n" +
                "(a) the software will meet your requirements or expectations;\n" +
                "(b) the software or the software content] will be free of bugs, errors, viruses or other defects;\n" +
                "(c) any results, output, or data provided through or generated by the software will be accurate, up-to-date, complete or reliable;\n" +
                "(d) the software will be compatible with third party software;\n" +
                "(e) any errors in the software will be corrected.\n" +
                "\n" +
                "Responsibility\n" +
                "\n" +
                "(a) Compliance with Law. Licensee assumes all responsibility for selecting the software and for the results obtained from the use of the software. Licensee shall bear the entire risk as to the quality and the performance of the software.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);




 //Statements:Representations:Acknowledgements:Ownership (License or Use)

        f = new Fragment("Ownership. \n" +
                "\n" +
                "(a) Proprietary Rights. [Disclosing Party][Licensor] retains all proprietary rights to the [information][Licensed Property].\n" +
                "\n" +
                "(b) License Rights. No license, express or implied, is granted other than to use the information in the manner and to the extent authorized in this agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 License Agreement\n" +
                "\n" +
                "Ownership. Licensor retains all proprietary rights to the [Licensed Property].\n" +
                "\n" +
                "1.2 Non-Disclosure Agreement\n" +
                "\n" +
                "No Ownership Rights. The Receiving Party agrees that all rights to any intellectual property in the Confidential Information shall remain the exclusive property of the Disclosing Party.\n" +
                "\n" +
                "No License Rights. The Receiving Party agrees that nothing in this Agreement shall be construed as a grant of any license to any intellectual property in the Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.1 Derivative Property\n" +
                "\n" +
                "\"All right, title and interest in and to the Confidential Information and intellectual property produced based on the Confidential Information is and shall remain the sole property of the Disclosing Party. Subject only to the Receiving Party's limited use of the Confidential Information for the purpose set forth above in this Agreement, the Receiving Party acknowledges and agrees that nothing in this Agreement shall be construed as granting any rights, license or otherwise, to any Confidential Information disclosed pursuant to this Agreement, and the Receiving Party shall not violate any of the Disclosing Party's intellectual property or other rights in or to the Confidential Information.\"\n" +
                "\n" +
                "2.2 Express Reservation of IP Rights\n" +
                "\n" +
                "\"Each party acknowledges and agrees that no license, implied or otherwise, is granted hereby under any patent, copyright, trademark, any application for any of the foregoing or any other intellectual property right. If Confidential Information is or becomes the subject of a patent, copyright, trademark or any application for any of the foregoing, the party originating such Confidential Information will have all the rights and remedies available under such patent, copyright, trademark or application.\"");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

    //Statements:Representations:Acknowledgements: Ownership (Work for Hire)

        f = new Fragment("Ownership. Executive agrees that the Company is the sole and exclusive owner of all intellectual property, including copyrights, trademarks, patents, inventions, work product and know-how, which\n" +
                "(a) relate to the Company's business, or actual or demonstrably anticipated research or development of the Company; or\n" +
                "(b) result from any work performed by the Executive for the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("All inventions, modifications, discoveries, designs, developments, improvements, processes, software programs, works of authorship, documentation, formulae, data, techniques, know-how, trade secrets or intellectual property rights or any interest therein (collectively, the \"Developments\") made by the Executive, either alone or in conjunction with others, at any time or at any place during the Executive's employment with the Company, whether or not reduced to writing or practice during such period of employment, which relate to the business in which the Company is engaged or, to the knowledge of the Executive, in which the Company intends to engage, shall be and hereby are the exclusive property of the Company without any further compensation to the Executive. In addition, without limiting the generality of the prior sentence, all Developments which are copyrightable work by the Executive are intended to be work made for hire as defined in Section 101 of the Copyright Act of 1976, and shall be and hereby are the property of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.1 Ownership\n" +
                "\n" +
                "All inventions, discoveries, developments, and improvements made, conceived or reduced to practice by Executive under or arising out of this Agreement (\"Work Product\") shall, whether or not such Work Product is patentable or copyrightable or made or conceived or reduced to practice or learned by Executive either alone or jointly with others, become and remain the sole and exclusive property of the Company\n" +
                "\n" +
                "2.2 Assignment\n" +
                "\n" +
                "The Executive agrees to assign, and hereby does assign, to Company all rights to any intellectual property that may arise during the term of this Agreement.\n" +
                "\n" +
                "See, Assignment of Intellectual Property for additional elements, such as notification and cooperation.\n" +
                "\n" +
                "2.3 No Contest\n" +
                "\n" +
                "The Executive agrees not to assert any such rights against Company or any third party.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

    //Statements:Representations:Acknowledgements: Independent Contractor

        f = new Fragment("Independent Contractor Status.\n" +
                "\n" +
                "(a) Status. Consultant is an independent contractor of Company. Nothing contained in this Agreement shall be construed to create the relationship of employer and employee, principal and agent, partnership or joint venture, or any other fiduciary relationship.\n" +
                "\n" +
                "(b) No Authority. Consultant has no authority to act as agent for, or on behalf of, Company, or to represent Company, or bind Company in any manner.\n" +
                "\n" +
                "(c) No Benefits. Consultant will not be entitled to worker's compensation, retirement, insurance or other benefits afforded to employees of Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 No Employee Benefits\n" +
                "\n" +
                "Consultant acknowledges and agrees that Consultant will not be eligible for any Company employee benefits and, to the extent Consultant otherwise would be eligible for any Company employee benefits but for the express terms of this Agreement, Consultant hereby expressly declines to participate in such employee benefits of the Company.\n" +
                "\n" +
                "1.2 No Authority to Bind Company\n" +
                "\n" +
                "Consultant has no authority to enter into contracts that bind the Company or create obligations on the part of the Company, and he agrees not to purport to do so.\n" +
                "\n" +
                "1.3 Not an Agent\n" +
                "\n" +
                "The Consultant and Contractor shall not have authority to act as agent of the Company, and the Consultant and Contractor shall not represent themselves as acting as an agent of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

//Statements:Representations:Acknowledgements:Reasonableness (of Restrictions)

        //TODO: java.lang.RuntimeException: Did not understand the posAnalysis '' for [Employee
        f = new Fragment("Reasonableness. [Employee] [expressly] agrees that the covenants of this Agreement [are supported by good and adequate consideration, and] are reasonable and necessary [in terms of duration, scope and geographic area] to protect legitimate business interests.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        

        f = new Fragment("Acknowledgments of Executive. The Executive acknowledges and agrees that during the terms of the Executive�s employment the Executive has acquired special and confidential knowledge regarding the operations of Consumers. Furthermore, although not a term or condition of this Agreement, the Company, the Bank, and the Executive acknowledge and agree that the Executive services have been used and are being used by Consumers in executive, managerial and supervisory capacities throughout the areas in which Consumers does business . The Executive acknowledges and agrees that the noncompete restrictions contained herein are reasonable and fair in scope and necessary to protect the legitimate interests of Consumers.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Acknowledgments of Seller. Seller hereby acknowledges and agrees that:\n" +
                "(a) this Agreement is necessary for the protection of the legitimate business interests of Buyer and its Affiliates, including but not limited to the protection of the goodwill of the Company which Buyer is acquiring;\n" +
                "(b) the restrictions contained in this Agreement regarding geographical scope, length of term and types of activities restricted are reasonable;\n" +
                "(c) the execution and delivery of this Agreement is a mandatory condition precedent to the consummation by Buyer of the transactions provided for in the Merger Agreement;v (d) Seller has no intention of competing with Buyer or any of its Affiliates with respect to the Business within the limitations set forth above;\n" +
                "(e) as an owner of the Company and through his ownership of the Company, Seller has received, either directly or indirectly, adequate and valuable consideration for entering into this Agreement;\n" +
                "(f) Buyer's business is national in nature and Buyer contracts with national clients requiring Buyer to do work throughout the United States;\n" +
                "(g) Seller acknowledges that the Business of the Company is also national in nature; and\n" +
                "(h) Seller acknowledges that this Agreement is not entered into in consideration in whole or in part for any employment relationship or employment contract which is effective for the period after the Closing with Buyer or any Affiliate of Buyer including Rockford Corporation.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

//Conditions (Defines the action and circumstances required to execute the exchange)



 //Obligations (Describes the continuing Obligations of the parties)

//Obligations: Perform Actions: Best Efforts

        f = new Fragment("Best Efforts. Each Party will use its best efforts to take all actions and to do all things necessary, proper, or advisable to consummate, make effective, and comply with all of the terms of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Level of Effort\n" +
                "\n" +
                "Ken Adams of Koncision reviewed a large number of agreements filed with the SEC in January 2004. He captured and tabulated the range of effort standards finding \"that best efforts was the phrase used most often, but commercially reasonable efforts, reasonable best efforts, and reasonable efforts were each used often, in the aggregate more so than best efforts. Good-faith efforts, commercially reasonable best efforts, and diligent efforts were used significantly less often, while good-faith best efforts, every effort, and an oddity, commercially reasonable and diligent efforts, bring up the rear.\" Understanding \"Best Efforts\" And Its Variants (Including Drafting Recommendations), Kenneth A. Adams, 2004. Ken observes that lawyers generally perceive \"best effort\" to be the strictest standard of performance and may require parties to undertake performance even at the risk of bankrupting a business. Courts, on the other hand, may not impose such strict obligations. Ken concludes his examination of efforts clauses stating: \"[a]lthough the term best efforts and its variants are a standard feature of contracts, there is much confusion surrounding what those terms mean. Furthermore, a court could hold that a party subject to an efforts provision was obligated to make efforts out of proportion to the benefits to it under the contract in question.\" Id.\n" +
                "\n" +
                "1.2 Performance\n" +
                "\n" +
                "(a) Actions: take, or cause to be taken, all action and to do, or cause to be done, all things reasonably necessary, proper or advisable under any Applicable Law to consummate the transactions contemplated by this Agreement and any document to be executed in connection with this Agreement;\n" +
                "\n" +
                "(b) Consents and Approvals: obtain all approvals, consents, registrations, permits, authorizations and other confirmations required to be obtained from any third party or Governmental Authority necessary, proper or advisable to consummate the transactions contemplated by this Agreement;\n" +
                "\n" +
                "(c) Execute Documents: execute and deliver such documents, certificates and other papers as a Party may reasonably request to evidence the other Party's satisfaction of its obligations under this Agreement.\n" +
                "\n" +
                "The clause may specify the performance obligation in great detail. For example, the law firm of Cravath, Swaine & Moore LLP typically drafts very detailed requirements. See, for example, Section 5.03 of Agreement and Plan of Merger, dated as of January 28, 2011. The purpose of such detailed statements of expected performance is to add greater certainty to the provision. The basic clause does not provide much detail regarding what action are expected and how much effort is required.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

//Obligations: Perform Actions: Return Property

        f = new Fragment("Ownership. [Upon termination of the agreement or] At the disclosing party's request, all Confidential Information in the possession of the Receiving Party shall be returned to the Disclosing Party or destroyed.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("At the Disclosing Party's request [or upon Termination of this agreement] [or if the Proposed Transaction is not consummated], the Receiving Party will promptly [within x days of receipt of such notification]:\n" +
                "\n" +
                "(i) Return Property: return to the disclosing party all copies of the Confidential Information in its possession [or in the possession of its Representatives], [whether in written form, electronically stored or otherwise] provided by the disclosing party;\n" +
                "\n" +
                "(ii) Destroy Property: destroy all copies [of those portions of any documents] containing any Confidential Information, and\n" +
                "\n" +
                "(iii) Certification: if so requested by the disclosing party, deliver to the disclosing party a certificate executed by one of its duly authorized officers confirming compliance with the return or destruction obligation.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("2.1 Reasonable Standard\n" +
                "\n" +
                "\"At the disclosing party's request, the receiving party will, as far as technically reasonably possible: (a) promptly return to the disclosing party all Confidential Information including all copies, extracts or other reproductions (regardless of the form in which such reproductions are maintained) or (b) if the disclosing party so directs, the receiving party will destroy such Confidential Information.\"\n" +
                "\n" +
                "Note:In most cases, it would be better to reference an objective standard of destruction procedures, rather than leave it for later judicial interpretation.\n" +
                "\n" +
                "2.2 Derivative Information (residuals)\n" +
                "\n" +
                "One of the most challenging aspects of intellectual property is the definition of derivative concepts, that is, ideas and concepts based on, or derivative of, some predecessor information. For example, information provided by the disclosing party may be used by employees of the receiving party to formulate extensions to the concepts contained in the confidential information. Where such derivative information is addressed, most agreements incorporate such residuals into the return or destruction obligation.\n" +
                "\n" +
                "Example 1\n" +
                "PROVIDED, however, that any analyses, compilations, studies or other documents prepared by a [receiving party] based upon or relating to or otherwise constituting Confidential Information shall be deemed to be Confidential Information and will be, at the option of the [disclosing party], either destroyed or held by such [receiving party] and kept confidential and subject to the terms of this Agreement, subject to any documentation retention policies to which such [receiving party] is subject as required by law or regulatory authority.\n" +
                "\n" +
                "Example 2\n" +
                "This provision applies to all documents, memoranda, notes, computer programs and data bases and other writings whatsoever prepared by [receiving party] based on, containing or otherwise reflecting the Confidential Information.\n" +
                "\n" +
                "2.3 Archival Exception\n" +
                "\n" +
                "A number of clause examples make exceptions for archival copies.\n" +
                "\n" +
                "Example 1\n" +
                "Notwithstanding the foregoing, the receiving party may retain copies of the Confidential Information in accordance with policies and procedures of the receiving party solely in order to comply with law, regulation or archival purposes; provided, however, that any Confidential Information so retained will continue to be Confidential Information pursuant to the terms of this Agreement and the receiving party will continue to be bound by the terms of this Agreement with respect to such Confidential Information.\n" +
                "\n" +
                "Example 1\n" +
                "Receiving party's legal department or outside legal counsel may maintain a single copy of disclosing party's Confidential Information for purposes of regulatory compliance and compliance with the terms and conditions of this Agreement.\n" +
                "\n" +
                "2.4 Continuing Obligations\n" +
                "\n" +
                "Notwithstanding the return or destruction of the Evaluation Material, each party and its Representatives will continue to be bound by its obligations of confidentiality and other obligations hereunder.\n" +
                "\n" +
                "2.5 Actions to Prevent Disclosure\n" +
                "\n" +
                "The receiving party will (at its own expense) take all actions necessary to restrain its employees, agents and representatives from making any unauthorized use or disclosure of any of the Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Obligations ->Information and Notification->Access to Information


        f = new Fragment("Access to Information. Between the date of this Agreement and the Closing Date, Seller will permit representatives of Buyer (including legal counsel and accountants) to have full access at all reasonable times, and in a manner so as not to interfere with the normal business operations, to all Seller's premises, properties, personnel, books, records, contracts, and documents.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Time Period\n" +
                "\n" +
                "Unstated\n" +
                "Limited to period between execution of agreement and closing: \"Between the date of this Agreement and the Closing Date,\"\n" +
                "Continue after the closing: \"Prior and subsequent to the Closing,\" \n" +
                "1.2 Grant\n" +
                "\n" +
                "\"Seller will permit...\"\n" +
                "\n" +
                "1.3 Access\n" +
                "\n" +
                "\"[Buyer] and its accountants, counsel and other representatives\"\n" +
                "\n" +
                "1.4 When\n" +
                "\n" +
                "\"[upon notice,] [upon reasonable prior notice and subject to applicable law]\n" +
                "reasonable access during business hours [and in a manner so as not to interfere with the normal business operations]\"\n" +
                "\n" +
                "1.5 Information\n" +
                "\n" +
                "Properties, Books and Records: \"all its properties, books, contracts, commitments and records[ other existing documents and data as Buyer may reasonably request]\"\n" +
                "Personnel: \"to its officers, employees, accountants, counsel and other representatives\"\n" +
                "\n" +
                "1.6 Caveats\n" +
                "\n" +
                "No information or knowledge obtained by Buyer shall affect or be deemed to modify any representation, warranty, covenant, condition or obligation under this Agreement.\n" +
                "All such information shall be held confidential in accordance with the terms of the Confidentiality Agreement.\n" +
                "\n" +
                "Example: Nature of information defined by scope of transaction\n" +
                "\n" +
                "The Company shall, and shall cause each of its Subsidiaries to, afford to Parent, Merger Sub and their respective Representatives reasonable access during normal business hours, during the period prior to the earlier of Effective Time and the termination of this Agreement in accordance with its terms, to such information, properties and personnel regarding the Company as shall be reasonably necessary for Parent or Merger Sub to fulfill their respective obligations pursuant to this Agreement, to confirm that the representations and warranties of the Company contained herein are true and correct, to confirm that the covenants of the Company contained herein have been performed in all material respects and to enable Parent, subject to applicable Law, to conduct integration planning in connection with, and in preparation for, the Merger, and, during such period, the Company shall, and shall cause each of its Subsidiaries to, also furnish promptly to Parent: (a) a copy of each report, schedule, registration statement and other document filed or received by it during such period pursuant to the requirements of federal or state securities laws and (b) all other information concerning its business, properties and personnel as Parent or Merger Sub may reasonably request (including Tax Returns filed); provided, however, that the foregoing shall not require the Company to disclose any information to the extent such disclosure would contravene applicable Law.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.1 Retention of Records\n" +
                "\n" +
                "Seller shall cause the Surviving Company to, until the [number] anniversary of the Closing Date, retain all material books, records and other documents pertaining to the business of the Seller and its Subsidiaries in existence on the Closing Date and to make the same available for inspection and copying by the Representatives or any of the representatives of such Representatives at the expense of the Representatives during the normal business hours of the Surviving Company, upon reasonable request and upon reasonable notice.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Obligations ->Information and Notification->Announcements and Publicity

        f = new Fragment("Announcements The Parties:\n" +
                "\n" +
                "(a) shall consult with each other before issuing any press release or otherwise making any public statements with respect to this Agreement;\n" +
                "\n" +
                "(b) shall not issue any such press release or make any such public statement without the prior consent of the other party, which consent shall not be unreasonably withheld or delayed;\n" +
                "\n" +
                "(c) may, without the prior consent of the other party, issue such press release or make such public statement as may be required by law or a court order.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Service and License Agreements\n" +
                "\n" +
                "Publicity Neither Party shall issue any press release or other public announcement related to this Agreement, written or oral, without the prior written consent of the other party[, except as required by law or a court order].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Initial Press Release\n" +
                "\n" +
                "The initial press release with respect to this Agreement shall be a joint release mutually agreed upon by the Parties.\n" +
                "\n" +
                "The Parties shall use commercially reasonable efforts to develop a joint communications plan with respect to the subject matter of this Agreement and each party shall use commercially reasonable efforts to ensure that all press releases and other public statements with respect to the transactions contemplated hereby shall be consistent with such joint communications plan.\n" +
                "\n" +
                "1.2 Consultation and Review\n" +
                "The Parties shall consult with each other before issuing any press release or otherwise making any public statements with respect to this Agreement.\n" +
                "\n" +
                "In the case of any Required Disclosure the disclosing party shall endeavor, on a basis reasonable under the circumstances, to provide a meaningful opportunity to the other parties to review and comment upon such press release or other announcement and shall give due consideration to all reasonable additions, deletions or changes suggested thereto.\n" +
                "\n" +
                "1.3 Consent\n" +
                "\n" +
                "Neither Party shall issue any press release or other public announcement related to this Agreement, written or oral, without the prior written consent of the other party[,which consent shall not be unreasonably withheld, conditioned, or delayed].\n" +
                "\n" +
                "1.4 Required Disclosure\n" +
                "\n" +
                "The Parties may, without the prior consent of the other party, issue such press release or make such public statement as may be required by law or a court order; but only to the extent required by such Law; provided further that the Party intending to make such release shall use its commercially reasonable efforts consistent with such applicable law to consult with the other party with respect to the timing and content thereof.\n" +
                "\n" +
                "1.5 Confidential Information\n" +
                "\n" +
                "The Parties shall not include in any public statement any information to which such other Party reasonably believes as being within the scope of Confidential Information.\n" +
                "\n" +
                "1.6 Employees and Customers\n" +
                "\n" +
                "The Parties will consult with each other concerning the means by which employees, customers and suppliers and others having dealings with the Parties will be informed of the transactions contemplated hereby.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("2.1 Consult\n" +
                "\n" +
                "The parties shall consult with each other before issuing any press release or otherwise making any public statement with respect to [the subject matter of this Agreement].\n" +
                "\n" +
                "2.2 Consent\n" +
                "\n" +
                "No party shall make any press release or other announcement with respect to the subject matter of this Agreement without the consent of the other party[, which shall not be unreasonably delayed, conditioned or withheld].\n" +
                "\n" +
                "2.3 Written Approval\n" +
                "\n" +
                "No Party shall issue any press release or make any public announcement regarding the subject matter of this Agreement without first obtaining the written approval of the other Party[, which shall not be unreasonably delayed, conditioned or withheld].\n" +
                "\n" +
                "Press Releases and Public Announcements. No Party shall issue any press release or make any public announcement relating to the existence or subject matter of this Agreement without the prior written approval of the other Party; provided, however, that any Party may make any public disclosure it believes in good faith is required by applicable law or any listing or trading agreement concerning its publicly-traded securities (in which case the disclosing Party will use its reasonable best efforts to advise the other Party prior to making the disclosure to the extent practicable and permissible under applicable law); and provided, further, that each of the Parties may make internal announcements to their respective employees that are not inconsistent in any material respects with the Parties' prior public disclosures regarding the transactions contemplated by this Agreement. Intellectual Property Purchase Agreement, February 25, 2011.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("(a) Initial Press Release. The Parties shall agree on the form and content of the initial joint press release regarding the transactions contemplated hereby.\n" +
                "(b) Subsequent Announcements. Thereafter the parties shall consult with each other before issuing any press release or otherwise making any public statement with respect to [the subject matter of the Agreement] [and give the other party the opportunity to review and comment on such press release or other announcement, if practicable].\n" +
                "(c) Required Disclosure. The Parties may make any public disclosure [it believes in good faith is] required by applicable law or any listing or trading agreement concerning its publicly-traded securities (in which case the disclosing Party will use its [reasonable] best efforts to advise the other Parties prior to making the disclosure).\n" +
                "3.2 Purchase Agreement—Private Companies or Transactions\n" +
                "\n" +
                "(a) Public Announcements. [Prior to the Closing Date,] No party shall make any press release or other announcement with respect to the subject matter of this Agreement without the consent of the other party.\n" +
                "(b) Required Disclosure. The Parties may make any public disclosure required by applicable securities laws, market regulations or listing agreements.\n" +
                "3.3 License Agreement—Press Releases; Use of Trademarks\n" +
                "\n" +
                "Press Releases; Use of Trademarks. Neither Party shall (a) issue a press release or make any other public statement that references this Agreement, or (b) use the other Party's names or trademarks for publicity or advertising purposes, except with the prior written consent of the other Party.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


    // Obligations ->Restrictive Covenants->Assignements of Inventions

        f = new Fragment("Inventions. Employee agrees to\n" +
                "\n" +
                "(a) Disclosure: promptly make full written disclosure to the Company of all Inventions, and\n" +
                "\n" +
                "(b) Assign: assign to the Company, or its designee, all right, title and interest in and to any and all Inventions.\n" +
                "\n" +
                "Definition of Inventions\n" +
                "\n" +
                "(a) Included Inventions. The term \"Inventions\" means all original works of authorship, developments, concepts, improvements or trade secrets, whether or not patentable or registrable under copyright or similar laws, that Employee may individually or jointly conceive or develop or reduce to practice, or cause to be conceived or developed or reduced to practice during the period of employment with the Company.\n" +
                "\n" +
                "(b) Excluded Inventions. The provisions of this Agreement requiring assignment of inventions to the Company do not apply to, any invention which qualifies fully for exclusion under the provisions of [Section 2870 of the California Labor Code] [applicable state law].\n" +
                "\n" +
                "(c) Acknowledgement of Works for Hire. Employee further acknowledges that all Inventions are \"works made for hire,\" as that term is defined in the United States Copyright Act.\n" +
                "\n" +
                "Assignment of Future Inventions\n" +
                "\n" +
                "Inventions\n" +
                "\n" +
                "(a) Disclosure. Employee agrees to disclose to the Company all inventions, improvements or discoveries actually made, or copyright registration or patent applications filed, during the term of employment and within [TIME PERIOD] after termination of employment.\n" +
                "\n" +
                "(b) Assignment. Employee hereby assigns to the Company Employee's entire right, title and interest in any inventions, improvements or discoveries relating to the subject matter of employment and made or conceived individually or jointly, within [TIME PERIOD] after termination of employment.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.1. Scope of Included Inventions\n" +
                "\n" +
                "Included Inventions. The term \"Inventions\" means all original works of authorship, developments, concepts, improvements or trade secrets, whether or not patentable or registrable under copyright or similar laws, that Employee may individually or jointly conceive or develop or reduce to practice, or cause to be conceived or developed or reduced to practice:\n" +
                "(i) during the period of employment with the Company,\n" +
                "(ii) or for a period of [NUMBER OF MONTHS] following the termination of employment for any reason,\n" +
                "(iii) developed using the equipment, supplies, facilities or Confidential Information of the Company,\n" +
                "(iv) result from or are suggested by work performed by me for the Company, or\n" +
                "(v) relate to the business, or to the actual or demonstrably anticipated research or development of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment(" I understand that the provisions of this Agreement requiring assignment of Inventions to the Company do not apply to any invention that I developed entirely on my own time without using the Company's equipment, supplies, facilities, or trade secret information, except for those inventions that either: (i) relate at the time of conception or reduction to practice of the invention to the Company's business, or actual or demonstrably anticipated research or development of the Company; or (ii) result from any work performed by me for the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.3. Waiver of Rights\n" +
                "\n" +
                "Employee also hereby forever waives and agrees never to assert any rights Employee may have in or with respect to any Inventions even after termination of employment with the Company.\n" +
                "\n" +
                "2.4. Commercialization\n" +
                "\n" +
                "Employee acknowledges and agrees that the decision whether or not to commercialize or market any invention developed by Employee solely or jointly with others is within the Company's sole discretion and for the Company's sole benefit and that no royalty will be due to Employee as a result of the Company's efforts to commercialize or market any such invention.\n" +
                "\n" +
                "2.4 Acknowledgement of Rights in Future Inventions\n" +
                "\n" +
                "Acknowledgement. Employee recognizes that all inventions, improvements or discoveries relating to the subject matter of employment and made or conceived individually or jointly, within [TIME PERIOD] after termination of employment may have been conceived in significant part while employed by the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Obligations ->Restrictive Covenants->Inventions Retained and Licensed

        f = new Fragment("Inventions Retained and Licensed.\n" +
                "\n" +
                "(a) Retained Inventions. Employee has attached, as Exhibit A, a list [specifically] describing all inventions, original works of authorship, developments, improvements and trade secrets that:\n" +
                "\n" +
                "(i) were made prior to employment with Company;\n" +
                "(ii) belong to employee;\n" +
                "(iii) [relate to Company's proposed business, products or research and development]; and \n" +
                "(iv) are not assigned to Company (\"Retained Inventions\");\n" +
                "\n" +
                "provided that if no such list is attached, employee represents that there are no such Prior Inventions [at the time of signing this Agreement].\n" +
                "\n" +
                "(b) License of Retained Inventions. If in the course of employment with the Company, employee incorporates into a Company product, process or machine any Retained Inventions [or in any invention which employee has an interest], the the Employee hereby grants and the Company shall have a nonexclusive, royalty-free, irrevocable, perpetual, worldwide license to make, have made, modify, use and sell such Prior Invention as part of or in connection with such product, process or machine.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Obligations ->Restrictive Covenants->Inventions Retained and Licensed Microsoft Example

        f = new Fragment("Excluded and Licensed Inventions. I have attached a list describing all. Inventions that I am currently developing and all Inventions belonging to me and made by me prior to my employment with MICROSOFT that I wish to have excluded from this Agreement. If no such list is attached, I represent that there are no such Inventions. As to any Invention in which I have an interest at any time prior to or during my employment, if I use or incorporate such an Invention in any released or unreleased MICROSOFT product, service, program, process, machine, development or work in progress, or if I permit MICROSOFT to use or incorporate such an invention, MICROSOFT is hereby granted and shall have an irrevocable, perpetual, royalty-free, worldwide license to exercise any and all rights with respect to such Invention, including without limitation the right to protect, make, have made, use and sell that Invention without restriction and the right to sublicense those rights to others. This license shall be exclusive, subject to any preexisting non-exclusive licensees or other pre-existing rights not subject to My control.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Obligations ->Restrictive Covenants->Non-Disclosure

        f = new Fragment("Non-Disclosure Obligations.\n" +
                "\n" +
                "(a) Confidentiality. Each party agrees to hold the disclosing party's Confidential Information in [strict] confidence [in accordance with the terms of this Agreement].\n" +
                "\n" +
                "(b) Non-Disclosure. Each party agrees not to disclose any Confidential Information to third parties (including, without limitation, any clients, affiliates, independent contractors and consultants) without the prior, written consent of the disclosing party except as expressly permitted in this Agreement.\n" +
                "\n" +
                "(c) Non-Use. Each party agrees not to use any Confidential Information for any purpose except for the Disclosing Purpose without the prior written consent of the disclosing party.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Best Efforts Standard\n" +
                "\n" +
                "Use of Confidential Information. The receiving party will keep the Confidential Information in confidence and, except as expressly provided in this Agreement, will not disclose it to anyone without the disclosing party's prior written consent. The receiving party will not use, or permit others to use Confidential Information for any purpose other than for the purpose of evaluating possible business arrangements. The receiving party will use its best efforts to avoid disclosure, dissemination or unauthorized use of Confidential Information.\n" +
                "\n" +
                "1.2 Protection of Confidential Information (Employee Agreement)\n" +
                "\n" +
                "Employee agrees that:\n" +
                "(a) Employee shall not use in any manner, directly or indirectly, any Confidential Information except in promoting the Company's business, and as necessary in performing the duties of his/her employment with the Company;\n" +
                "(b) Employee will not use any Confidential Information for his/her own benefit or for the benefit of any person or entity other than the Company, and will not permit or allow any Confidential Information to be used in competition with the Company.\n" +
                "(c) During his/her employment with the Company and at all times thereafter, Employee shall take all reasonable steps to prevent any unauthorized disclosure or use of any and all Confidential Information. Employee further agrees to notify the Company immediately in the event that he/she becomes aware of any unauthorized use or disclosure of Confidential Information.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Obligations ->Restrictive Covenants->Non-Competition

        f = new Fragment("Non-Competition.\n" +
                "\n" +
                "(a) Restrictions. During the Restricted Period and within the Restricted Territory, Executive shall not, directly or indirectly, without the prior written consent of the Company engage in any Restricted Activity for or on behalf of any Restricted Business.\n" +
                "\n" +
                "(b) Exceptions. It is not a breach of this Agreement for Executive to participate as a passive investor holding up to [PASSIVE INVESTMENT PERCENT] of the equity securities of an entity engaged in the Restricted Business, which securities are publicly traded.\n" +
                "\n" +
                "\n" +
                "Definitions\n" +
                "\n" +
                "\"Restricted Activity\" means:\n" +
                "\n" +
                "(i) Provision of Services: employment or provision of any services [or serving as an officer, director, partner, principal, employee, agent, representative, consultant or independent contractor or providing any assistance] to a Restricted Business;\n" +
                "\n" +
                "(ii) Fiscal Interest: having any ownership interest in, or participating in the financing, operation, management or control of, any person, firm, corporation or business.\n" +
                "\n" +
                "(iii) Business Interest: conducting any business [for the party's own account] with a Restricted Business.\n" +
                "\n" +
                "\"Restricted Business\" means any [RESTRICTED BUSINESS].\n" +
                "\n" +
                "\"Restricted Period\" means the period starting on the Effective Date of this Agreement and ending [RESTRICTED TERM] after termination of this Agreement.\n" +
                "\n" +
                "\"Restricted Territory\" means [RESTRICTED TERRITORY].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Described Generally\n" +
                "\n" +
                "Restricted Activities means any competitive activity.\n" +
                "\n" +
                "Restricted Business:\n" +
                "Described by Nature of Business\n" +
                "\n" +
                "Restricted Business means any company or organization involved in: [Nature of Business].\n" +
                "\n" +
                "Described by Similarity\n" +
                "\n" +
                "Restricted Business means any company or organization involved in a business similar to that of the Company.\n" +
                "\n" +
                "Named Competitors\n" +
                "\n" +
                "A \"Competitor\" shall mean (i) Bed Bath & Beyond, Inc., Home Place Inc.,J.C. Penney, Federated Department Stores, Mays, Target, Sears and K-Mart (and any successor or successors thereto); (ii) any home textiles or housewares store, specialty store or other retailer if either $25 million or 40% or more of its annual gross sales revenues (in either case, based on the most recent quarterly or annual financial statements available) are derived from the sale of home textiles, housewares or other goods or merchandise of the types sold in the Company's (or any Subsidiary's) stores; (iii) any corporation or other entity whether independent or owned, funded or controlled by any other entity, engaged or organized for the purpose of engaging, in whole or in part, in the sale of home textiles, housewares or other goods or merchandise of the types sold in the Company's (or any Subsidiary's) stores; (iv) any business that provides buying office services to any business or group of businesses referred to above, or (v) any business (in the U.S. or any country in which the Company or any Subsidiary operates a store or stores) which is in material competition with the Company or any Subsidiary or division thereof and in which Executive's functions would be substantially similar to Executive's functions with the Company.\n" +
                "\n" +
                "Restrictied Period:\n" +
                "Stated Term\n" +
                "\n" +
                "\"Restricted Period\" means the period starting on the Effective Date of this Agreement and ending [RESTRICTED TERM] after termination of this Agreement.\n" +
                "\n" +
                "Variable Term, Depending on Severance\n" +
                "\n" +
                "\"Restricted Period\" means the period starting on the Effective Date of this Agreement and ending [RESTRICTED TERM] after termination of this Agreement (in the event Employee does not receive severance compensation) or [RESTRICTED TERM] (in the event Employee does receive severance compensation.\n" +
                "\n" +
                "Specified Area:\n" +
                "\n" +
                "\"Restricted Territory\" means [RESTRICTED TERRITORY].\n" +
                "\n" +
                "Conduct of Business\n" +
                "\n" +
                "\"Restricted Territory\" means any [country][state][county] in which the company conducts business or markets its products or services.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.1 Extension of Restricted Period\n" +
                "\n" +
                "If, during any period within the Non-Competition Period, the Executive is not in compliance with the terms of this section, the Company shall be entitled to, among other remedies, compliance by the Executive with the terms of this section for an additional period equal to the period of such noncompliance. For purposes of this Agreement, the term \"Non-Competition Period\" shall also include this additional period.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

         // Obligations ->Restrictive Covenants->Non-Solicitation


        f = new Fragment("Non-Solicitation. During the term of this Agreement and for [NON-SOLICITATION PERIOD] after any termination of this Agreement, [Party] will not, without the prior written consent of the Company, either directly or indirectly[, on [Party]'s own behalf or in the service or on behalf of others], solicit or attempt to solicit, divert or hire away any person employed by the Company or any customer of the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Employees and Consultants\n" +
                "\n" +
                "During the term of this Agreement and for [period] after any termination of this Agreement, [Party] will not directly or indirectly solicit, induce, recruit, encourage or otherwise endeavor to cause or attempt to cause any employee or consultant of the Company to terminate their relationship the Company[; provided, however, that nothing in this Section shall prohibit the use of a general solicitation in a publication or by other means.]\n" +
                "\n" +
                "1.2 Customers\n" +
                "\n" +
                "During the term of this Agreement and for [period] after any termination of this Agreement, [Party] will not directly or indirectly, for himself or on behalf of any other person, partnership, company, corporation or other entity, solicit or attempt to solicit, for the purpose of engaging in competition with the Company,\n" +
                "\n" +
                "(i) any person or entity whose account was serviced by [Party] at the Company; or\n" +
                "(ii) any person or entity who is or has been a customer of the Company prior to [Party]'s termination; or\n" +
                "(iii) any person or entity the Company has targeted and contacted prior to [Party]'s termination for the purpose of establishing a customer relationship.\n" +
                "Note: this clause does not contain a time limitation on the period since the person or entity was a customer of the company.\n" +
                "\n" +
                "1.3 Business Opportunities\n" +
                "\n" +
                "During the term of this Agreement and for [period] after any termination of this Agreement, [Party] will not directly or indirectly, in any capacity:\n" +
                "\n" +
                "(i) solicit the business or patronage of any Customer for any other person or entity,\n" +
                "(ii) divert, entice, or otherwise take away from the Companies the business or patronage of any Customer, or attempt to do so, or\n" +
                "(iii) solicit or induce any Customer to terminate or reduce its relationship with the Companies.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Obligations ->Restrictive Covenants->Non-Disparagement

        f = new Fragment("Non-Disparagement. [During the Term and thereafter,] [Employee] agrees to take no action which is intended, or would reasonably be expected, to harm the Company or its or their reputation or which would reasonably be expected to lead to unwanted or unfavorable publicity to the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("You [employee] agree that you will not disparage or comment negatively about the Company, its officers and management, and/or current or former employees. ");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Disparaging remarks, comments or statements are those that impugn the character, honesty, integrity, morality or business acumen or abilities in connection with any aspect of the operation of business of the covered individual or entity.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Mutual Non-Disparagement.\n" +
                "\n" +
                "(a) Each Investor agrees that, during the Standstill Period, neither it nor any of its Affiliates or Associates will, and it will cause each of its Affiliates and Associates not to, directly or indirectly, in any capacity or manner, make, express, transmit speak, write, verbalize or otherwise communicate in any way (or cause, further, assist, solicit, encourage, support or participate in any of the foregoing), any remark, comment, message, information, declaration, communication or other statement of any kind, whether verbal, in writing, electronically transferred or otherwise, that might reasonably be construed to be derogatory or critical of, or negative toward, the Company or any of its directors, officers, Affiliates, subsidiaries, employees, agents or representatives (collectively, the \"Company Representatives\"), or that reveals, discloses, incorporates, is based upon, discusses, includes or otherwise involves any confidential or proprietary information of the Company or its subsidiaries or Affiliates, or to malign, harm, disparage, defame or damage the reputation or good name of the Company, its business or any of the Company Representatives.\n" +
                "\n" +
                "(b) The Company hereby agrees that, during the Standstill Period, neither it nor any of its Affiliates will, and it will cause each of its Affiliates not to, directly or indirectly, in any capacity or manner, make, express, transmit, speak, write, verbalize or otherwise communicate in any way (or cause, further, assist, solicit, encourage, support or participate in any of the foregoing), any remark, comment, message, information, declaration, communication or other statement of any kind, whether verbal, in writing, electronically transferred or otherwise, that might reasonably be construed to be derogatory or critical of, or negative toward, any Investor or any of its agents or representatives (collectively, the \"Investor Representatives\"), or that reveals, discloses, incorporates, is based upon, discusses, includes or otherwise involves any confidential or proprietary information of any Investor or its subsidiaries or Affiliates, or to malign, harm, disparage, defame or damage the reputation or good name of any Investor or Investor Representatives.\n" +
                "\n" +
                "(c)Notwithstanding the foregoing, nothing in this Section 7 or elsewhere in this Agreement shall prohibit any Party from making any statement or disclosure required under the federal securities laws or other applicable laws; provided, that such Party must provide written notice to the other Parties at least two business days prior to making any such statement or disclosure required by under the federal securities laws or other applicable laws that would otherwise be prohibited the provisions of this Section 7, and reasonably consider any comments of such other Parties.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Nothing herein shall prevent [Employee] from making any truthful statement in connection with any legal proceeding or investigation by the Company or any governmental authority.\n" +
                "\n" +
                "2.3 Handling References\n" +
                "\n" +
                "Covenant of Non-disparagement. Each of the Company and the Consultant covenants never to disparage or speak ill of the other party or any of their products, services, affiliates, subsidiaries, officers, directors, employees or shareholders, and will take reasonable steps to prevent and will not knowingly permit any of their respective employees or agents to, disparage or speak ill of such persons. Notwithstanding the foregoing, the Consultant expressly assumes all risk associated with listing any past or present Company employee, consultant or agent, or the Company itself, as a reference in connection with the Consultants pursuit of future employment or engagement, and the Consultant agrees that any such person whom the Consultant lists as a reference shall in response to any request for a reference concerning the Consultant be permitted to provide complete, truthful and accurate information concerning the Consultant without creating any liability for himself or herself, the Company, any affiliated entity, or any employee, consultant, agent or representative of any of the foregoing.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


// Rights ->Termination->Termination of Employement


        f = new Fragment("Termination of Employment. The Executive's employment may be terminated only as follows:\n" +
                "\n" +
                "(a) Termination by the Company\n" +
                "\n" +
                "(i) For Cause. The Company may terminate the Executive's employment for Cause.\n" +
                "(ii) Without Cause. The Company may terminate Executive's employment at any time by giving Executive [NOTICE PERIOD FOR TERMINATION WITHOUT CAUSE] prior written notice of the termination.\n" +
                "[(iii) Change in Control. The Company may terminate the Executive's employment within [CHANGE OF CONTROL TERMINATION PERIOD] of a Change in Control.]\n" +
                "\n" +
                "(b) Termination by the Executive\n" +
                "\n" +
                "(i) For Good Reason. The Executive may terminate the Executive's employment with the Company for Good Reason.\n" +
                "(ii) Without Good Reason. The Executive may voluntarily terminate the Executive's employment with the Company at any time by giving the Company [NOTICE PERIOD FOR TERMINATION WITHOUT GOOD REASON] prior written notice of the termination.\n" +
                "\n" +
                "(c) Termination Upon Death or Disability\n" +
                "\n" +
                "(i) Death. The Executive's employment shall terminate upon the Executive's death.\n" +
                "(ii) Disability. The Company may terminate the Executive's employment upon the Executive's Disability.\n" +
                "\n" +
                "Definitions\n" +
                "\n" +
                "\"Cause\" means:\n" +
                "(a) Breach of Agreement. Executive's material breach of Executive's obligations of this Agreement, not cured after [CURE PERIOD FOR CAUSE] notice from the Company;\n" +
                "(b) Gross Negligence. Executive's gross negligence in the performance of Executive's duties;\n" +
                "(c) Non-Performance. Executive's intentional nonperformance or misperformance of duties, or refusal to comply with the reasonable directives of the Company or the Company's policies and procedures;\n" +
                "(d) Crimes and Dishonesty. Executive's commission of any the following:\n" +
                "(i) a felony criminal conviction;\n" +
                "(ii) any other criminal conviction involving Executive's lack of honesty or moral turpitude; or\n" +
                "(iii) acts of dishonesty, gross carelessness or gross misconduct.\n" +
                "\n" +
                "Change in Control. \"Change of Control\" means:\n" +
                "(a) Sale of Assets. The sale of all or substantially all of the assets of the Company;\n" +
                "(b) Merger or Consolidation. The consummation of any merger, consolidation, or reorganization involving the Company in which, immediately after giving effect to such merger, consolidation or reorganization, less than 51% of the total voting power of outstanding stock of the surviving or resulting entity is then beneficially owned (within the meaning of Rule 13d-3 under the Securities Exchange Act of 1934, as amended) in the aggregate by the stockholders of the Company immediately prior to such merger, consolidation or reorganization.\n" +
                "(c) Change in Voting Power. The acquisition of more than 50% of the voting power of the Company any person or group of affiliated or associated persons not a shareholder on the Effective Date of this Agreement;\n" +
                "(d) Dissolution or Liquidation. The dissolution or liquidation of the Company.\n" +
                "\n" +
                "\"Disability\" means any mental or physical condition that renders the Executive unable to perform the essential functions of the position, with or without reasonable accommodation, for a period in excess of [DISABILITY ABSENCE DAYS] days in the aggregate in any [DISABILITY PERIOD] period.\n" +
                "\n" +
                "\"Good Reason\" means:\n" +
                "(a) Breach of Agreement. The Company's material breach of this Agreement, which breach has not been cured by the Company within [COMPANY CURE PERIOD] after receipt of written notice specifying, in reasonable detail, the nature of such breach or failure from Executive.\n" +
                "(b) Non Payment. The failure of the Company to pay any amount due to Executive hereunder, which failure persists for [COMPANY CURE PERIOD] after written notice of such failure has been received by the Company;\n" +
                "(c) Change of Responsibilities. Any material reduction in Executive's title or a material reduction in Executive's duties or responsibilities (unless such reduction is for Cause);\n" +
                "(d) Change of Compensation. Any material adverse change in Executive's Base Salary (unless such reduction is for Cause) and any material adverse change in Executive's benefits (other than changes that affect other management Executives of the Company to the same or comparable extent);\n" +
                "(e) Change of Location. Any relocation of the premises at which Executive works to a location more than [RELOCATION DISTANCE TRIGGERING GOOD REASON] from such location, without Executive's consent; or");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Grounds for Termination:\n" +
                "\n" +
                "Termination Upon Death\n" +
                "Termination Upon Disability\n" +
                "Termination Upon Retirement\n" +
                "Termination by the Company For Cause\n" +
                "Termination by the Company Without Cause\n" +
                "Termination by Change in Control\n" +
                "Termination by Executive For Good Reason\n" +
                "Termination by Executive Without Good Reason");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("2.1 Cause\n" +
                "\n" +
                "Detrimental Acts. The Executive's commission of any act in direct or indirect competition with or materially detrimental to the best interests of Corporation that is in breach of Executive's fiduciary duties of care, loyalty and good faith to Corporation.\n" +
                "\n" +
                "Absenteeism. The Executive's excessive absenteeism (other than by reason of disability described in this Agreement) without a reasonable justification.\n" +
                "\n" +
                "Impairment. Executive's abuse of alcohol or drugs (legal or illegal) that, in the Company's reasonable judgment, substantially impairs Employee's ability to perform his duties under this Agreement.\n" +
                "\n" +
                "2.2 Change of Control\n" +
                "\n" +
                "Change in Membership. A majority of the members of the Board are replaced during any 12-month period].\n" +
                "\n" +
                "Board Determination. The Board determines in its sole discretion that a Change in Control has occurred, whether or not any event described above has occurred or is contemplated.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        // Rights ->Termination->Specific Events of Termination

        f = new Fragment("Termination Upon Death. This Agreement shall terminate automatically upon the death of the Executive.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination Upon Disability. This Agreement shall terminate automatically upon the Disability of the Executive.\n" +
                "\n" +
                "Disability. Disability means the Executive is unable to perform the duties set forth in this Agreement for a period of [twelve consecutive weeks], or [90] cumulative business days in [any 12-month] period, as a result of physical or mental illness or loss of legal capacity.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Automatic Termination\n" +
                "\n" +
                "Termination Upon Disability. This Agreement shall terminate automatically upon the Disability of the Executive.\n" +
                "\n" +
                "1.2 Optional Termination\n" +
                "\n" +
                "Termination Upon Disability. The Company may terminate this agreement upon the Disability of the Executive.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination on Account of Death or Disability; Non-Qualifying Termination.\n" +
                "(i) In the event of any termination of Executive's employment other than a Qualifying Termination (for example, on account of death or Disability), the Executive shall not be entitled to any additional payments or benefits from the Company under this Agreement, other than payments or benefits with respect to the Accrued Rights.\n" +
                "(ii) For purposes of this Agreement, the Executive shall be deemed to have a \"Disability\" in the event of the Executive's absence for a period of 180 consecutive business days as a result of incapacity due to a physical or mental condition, illness or injury that is determined to be total and permanent by a physician mutually acceptable to the Company and the Executive or the Executive's legal representative (such acceptance not to be unreasonably withheld) after such physician has completed an examination of the Executive. The Executive agrees to make himself available for such examination upon the reasonable request of the Company, and the Company shall be responsible for the cost of such examination.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination for Disability. The Company may terminate the Executive's employment because of the Disability of the Executive and thereafter the Company shall pay to the Executive (or his successors) his unpaid Salary through the Employment Termination Date and any Stock Options which have not vested as of the Employment Termination Date shall be terminated.\n" +
                "\n" +
                "\"Disability\" of the Executive means that, as a result of the Executive's incapacity due to physical or mental illness, the Executive shall have been absent from his duties on a full time basis for thirty (30) days in any three (3) month period. If the Executive is prevented from performing his duties because of Disability, upon request by the Company, the Executive shall submit to an examination by a physician selected by the Company, at the Company's expense, and the Executive shall also authorize his personal physician to disclose to the selected physician all of the Executive's medical records.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination Upon Retirement. The Executive may voluntarily terminate this Agreement at any time by reason of Retirement.\n" +
                "\n" +
                "Retirement. Retirement is the cessation by Executive of all full-time employment of any kind.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Mandatory Retirement\n" +
                "\n" +
                "Retirement. Retirement shall mean termination of Executive's employment:\n" +
                "(a) at age 65 or in accordance with any retirement policy established with Executive's consent; or\n" +
                "(b) at such later time as the Company's Board of Directors may determine.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Retirement. For purposes of this Agreement, \"Retirement\" shall mean the Executive's voluntary termination of employment, during the term of this Agreement, pursuant to late, normal or early retirement under a pension plan sponsored by the Company, as defined in such plan, but only if such retirement occurs prior to a termination by the Company without Cause (and not in anticipation of a termination for Cause).");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Compensation Upon Termination for Death, Disability, Retirement. If the Executive's employment is terminated by reason of Death, Disability or Retirement prior to any other Termination (other than in anticipation of a termination for Cause by the Company), the Executive will receive:\n" +
                "(a) the sum of (i) the Executive's accrued but unpaid base salary through the date of Termination, (ii) the Pro Rata Bonus, and (iii) any compensation previously deferred (excluding any qualified plan deferrals) by the Executive under or into benefit plans of the Company and an amount representing the Executive's accrued but unused vacation days, if any, in each case, in full satisfaction of the Executive's rights thereto; and\n" +
                "(b) the Accrued Benefits (with an offset for any amounts paid under Section 5(a)(iii), above).");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment(".As used in this Article IV, \"Termination by Reason of Retirement\" means a termination by the Company or Executive of Executive's employment based on Executive's having reached age 65 or such other age as shall have been fixed in any arrangement established with Executive's consent with respect to Executive; and in all instances subject to applicable law.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination For Cause. The Company shall have the right to terminate Executive's employment under this Agreement at any time for Cause, which termination shall be effective immediately. Termination for \"Cause\" shall include termination for:\n" +
                "(i) Breach. Material breach of this Agreement by Executive;\n" +
                "(ii) Nonperformance. Intentional nonperformance or misperformance of such duties, or refusal to abide by or comply with the reasonable directives of his superior officers, or the Corporation's policies and procedures;\n" +
                "(iii) Gross Negligence. Executive's gross negligence in the performance of his material duties under this Agreement;\n" +
                "(iv) Dishonesty. Executive's willful dishonesty, fraud or misconduct with respect to the business or affairs of the Corporation, that in the reasonable judgment of the President and/or the Board of Directors materially and adversely affects the Corporation;\n" +
                "(v) Conviction. Executive's conviction of, or a plea of nolo contendere to, a felony or other crime involving moral turpitude; or\n" +
                "(vi) Disloyalty. The commission of any act in direct or indirect competition with or materially detrimental to the best interests of Corporation that is in breach of Executive s fiduciary duties of care, loyalty and good faith to Corporation.\n" +
                "Cause will not, however, include any actions or circumstances constituting Cause under (i) or (ii) above if Executive cures such actions or circumstances within [30 days] of receipt of written notice from Corporation setting forth the actions or circumstances constituting Cause. In the event Executive's employment under this Agreement is terminated for Cause, Executive shall thereafter have no right to receive compensation or other benefits under this Agreement.\n" +
                "\n" +
                "Termination Without Cause. The Company may [, upon a [majority] vote of the Board of Directors,] terminate the Executive's employment under this Agreement without Cause at any time upon [90 days] prior [written] notice to the Executive.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("(vii) Absenteeism. Executive's excessive absenteeism (other than by reason of disability described in this Agreement) without a reasonable justification;\n" +
                "(viii) Substance Abuse. Executive's abuse of alcohol or drugs (legal or illegal) that, in the Company's reasonable judgment, substantially impairs Employee's ability to perform his duties under this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("For Cause. The Company may terminate this Agreement and Employee's employment hereunder at any time for Cause (as defined below) upon written notice given to Employee. As used herein, \"Cause\" means (i) any act of Employee which would constitute a felony (other than a driving offense) or fraud; (ii) a continuing material breach by Employee in performing the duties described in this Agreement (other than by reason of physical or mental disability or impairment) which is not cured by Employee within fifteen (15) days after the Company gives Employee written notice specifying the details of the breach; or (iii) gross neglect, gross malfeasance, willful neglect, willful misconduct, or dishonesty in performance of Employee's duties hereunder.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Without Cause. The Company may terminate this Agreement and Employee's employment hereunder at any time and for any reason or no reason and without Cause, upon written notice to Employee, in which event the Company shall have no further obligation under this Agreement except as set forth in (the Severance Payments section).\n" +
                "2.3 Example 3\n" +
                "\n" +
                "Termination Without Cause by the Company. This Agreement may be terminated without cause by the Company upon 10 days notice. The last day of the Executive's employment shall be referred to as the Termination Date. If this Agreement is terminated under this section 4.2, then the Executive shall be entitled to the following monies and benefits: (i) Base Salary and Benefits through the Termination Date; (ii) Base Salary for a period of 1 full year following the Termination Date, payable in quarterly installments with the first installment due on the Termination Date and the remaining 3 installments due 90 days, 180 days, and 270 days thereafter; (iii) Post-Termination Benefits; and (iv) the pro-rated portion any amounts earned under any Bonus Plan in effect prior to the Termination Date, to be determined after the close of the fiscal year in which the Termination Date occurred. The Company shall not be required to pay any monies or benefits under this section 4.2 unless and until the Executive shall have executed and delivered to the Company a Company-prepared release (\"Release\") of any and all claims or potential claims, against the Company, its directors, officers, employees, shareholders and subsidiaries, arising from or related to any act or omission occurring prior to the Termination Date. This Release shall also include a release of potential claims by the Company against the Executive, arising from or related to any act or omission of the Executive occurring prior to the Termination Date, except for any act or omission by the Executive involving intentional wrongdoing, fraud, or breach of fiduciary duty. Any and all stock options granted to the Executive, which have fully vested prior to the Termination Date, shall expire as set forth in the respective plan documents that granted the options.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination For Good Cause by the Company. Upon written notice to the Executive, the Company may immediately terminate this Agreement for \"Good Cause.\" Good Cause shall include: (i) the Executive's conviction of, or plea of nolo contendere or guilty to, any crime involving dishonesty, fraud or moral turpitude; (ii) the Executive's gross negligence with respect to the performance of the duties of his Position; (iii) the Executive's willful or serious misconduct, or willful or serious violation of Company policies; (iv) the Executive's breach of trust or breach of fiduciary duty in the performance of the duties or responsibilities of his Position; (v) the Executive's willful failure or refusal to comply with a reasonable directive of the Board; or (vi) the Executive's breach of any term or provision of this Agreement. The last day of the Executive's employment shall be referred to as the Termination Date. The Executive shall be entitled to Base Salary and Benefits earned and unpaid through to the Termination Date, and no other money, pay and benefits shall be owed or paid. Any and all stock options granted to the Executive, which have fully vested prior to the Termination Date, shall expire as set forth in the respective plan documents that granted the options.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

       // Rights ->Termination->Specific Events of Termination->Change of Control

        f = new Fragment("Termination upon Change of Control. If the Executive's employment is terminated by the Company without Cause or by the Executive for Good Reason in connection with or within [one year/two years] after Change in Control, the Executive shall be entitled to Severance Benefits as stated in the Termination Benefits section.\n" +
                "\n" +
                "Change in Control. For purposes of this Agreement, unless the Board determines otherwise, a Change of Control of the Company shall be deemed to have occurred at such time as:\n" +
                "(a) Change in Ownership: any person (as the term is used in Sections 13(d) and 14(d) of the Securities Exchange Act of 1934, as amended (the Exchange Act)) is or becomes the beneficial owner (as defined in Rule 13d-3 under the Exchange Act), directly or indirectly, of voting securities of the Company representing more than 50% of the Company s outstanding voting securities or rights to acquire such securities except for any voting securities issued or purchased under any employee benefit plan of the Company or its subsidiaries; or\n" +
                "(b) Sale: any sale, lease, exchange or other transfer (in one transaction or a series of transactions) of all or substantially all of the assets of the Company; or\n" +
                "(c) Liquidation: a plan of liquidation of the Company or an agreement for the sale or liquidation of the Company is approved and completed; or\n" +
                "(d) Board Determination: the Board determines in its sole discretion that a Change in Control has occurred, whether or not any event described above has occurred or is contemplated.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("(e) Board Membership: individuals who, as of the date hereof, constitute the entire Board of Directors of the Company (the \"Incumbent Directors\") cease for any reason to constitute at least a majority of the Board of Directors, provided that any individual becoming a director subsequent to the date hereof whose election or nomination for election was approved by a vote of at least a majority of the then Incumbent Directors shall be, for purposes of this provision, considered as though such individual were an Incumbent Director;\n" +
                "\n" +
                "(f) Consolidation: any consolidation or merger of the Company (including, without limitation, a triangular merger) where the shareholders of the Company, immediately prior to the consolidation or merger, would not, immediately after the consolidation or merger, beneficially own, directly or indirectly, shares representing in the aggregate more than fifty percent (50%) of the combined voting power of all the outstanding securities of the corporation issuing cash or securities in the consolidation or merger (or of its ultimate parent corporation, if any);");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);



        f = new Fragment("Termination Following a Change of Control and Compensation Reduction. In the event that a Change in Control, as hereinafter defined, of the Company shall occur at any time during the Term or Renewal Term, and within 12 months of the occurrence of such Change in Control event the Company terminates the Executive without Cause or the Executive shall terminate the Executive's employment under this Agreement, then, in any such event such termination shall be deemed to be a termination by the Company other than for Cause and the Executive shall be entitled to such compensation and benefits as set forth in Section 6(d) of this Agreement, which shall be paid promptly (but not later than 30 days) following the termination of Executive's employment.\n" +
                "\n" +
                "For purposes of this Agreement, a \"Change in Control\" of the Company shall mean any of the following:\n" +
                "(i) a sale of all or substantially all of the assets of the Company;\n" +
                "(ii) the date there shall have been a change in a majority of the Board of Directors of the Company during a consecutive twelve-month period, unless the nomination for election by the Company's shareholders of each new director was approved.by the vote of two-thirds of the directors then still in office who were in office at the beginning of the twelve-month period;\n" +
                "(iii) the date that any person or entity, entities or group of persons (other than the Executive) both (A) is or becomes the Beneficial Owner (as defined in Rule 13d-3 under the Securities Exchange Act of 1934), directly or indirectly, of securities of the Company.representing more than thirty percent (30%) or more of the combined voting power of the Company's then outstanding securities, and (B) has voting control of the Company;\n" +
                "(iv) consummation of a merger or consolidation of the Company with any corporation or other entity, other than a merger or consolidation which would result in the voting securities of the Company outstanding immediately prior thereto continuing to represent (either by remaining outstanding or by being converted into voting securities of the surviving entity) more than fifty percent (50%) of the combined voting power of the voting securities of the Company or such surviving entity outstanding immediately after such merger or consolidation;\n" +
                "(v) a change in ownership of the Company through a transaction or series of transactions, such that any person or entity is or becomes the Beneficial Owner (as defined in Rule 13d-3 under the Exchange Act), directly or indirectly, of securities of the Company representing fifty percent (50%) or more of securities of the combined voting power of the Company's then outstanding securities; provided that, for such purposes, any acquisition by the Company, in exchange for the Company's securities, shall be disregarded; or\n" +
                "(vi) the Board (or the stockholders if stockholder approval is required by applicable law or under the terms of any relevant agreement) shall approve a plan of complete liquidation of the Company.\n" +
                "provided, however, that a Change of Control shall expressly not include (A) any consolidation or merger effected exclusively to change the domicile of the Company, (B) any transaction or series of transactions principally for bona fide equity financing purposes, or (C) the Transaction.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination Following a Change of Control.\n" +
                "1. In the event that a \"Change in Control,\" as hereinafter defined, shall occur at any time during the Term or Renewal Term hereof, the Executive shall have the right to terminate the Executive's employment under this Agreement upon thirty (30) days written notice given at any time within one (1) year after the occurrence of such event.\n" +
                "\n" +
                "2. For purposes of this Agreement, a \"Change in Control\" of the Company shall mean a change in control:\n" +
                "a)the occurrence of any of the following:\n" +
                "i) any person, group or organization, other than the Executive, is or becomes the beneficial owner, directly or indirectly, of securities of the Company representing fifty percent (50%) or more of the combined voting power of the Company's outstanding securities then having the right to vote at elections of directors; or\n" +
                "ii) the individuals who at the Effective Date of this Agreement constitute the Board of Directors cease for any reason to constitute a majority thereof unless the election, or nomination for election, of each new director was approved by the Executive; or\n" +
                "iii) the business or over fifty percent (50%) of the business revenues of the Company for which the Executive's services are principally performed is/ are sold or otherwise disposed of by the Company (including the stock of a subsidiary of the Company).\n" +
                "\n" +
                "Anything herein to the contrary notwithstanding, this Section 6G2 will not apply where the Executive gives the Executive's explicit written waiver stating that for purposes of this Section 6G2 a Change in Control shall not be deemed to have occurred. The Executive's participation in any negotiations or other matters in relation to a Change in Control shall in no way constitute such a waiver which can only be given by an explicit written waiver as provided in the preceding sentence.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

               // Rights ->Termination->Specific Events of Termination->Termination by Executive

        f = new Fragment("Termination by the Executive for Good Reason. The Executive may terminate his employment under this Agreement for Good Reason, in which case the Executive shall be entitled to Severance Benefits as stated in the Termination Benefits section.\n" +
                "For purposes of this Agreement, \"Good Reason\" shall mean the occurrence of any of the following events without the Executive's written consent:\n" +
                "(i) a material diminution of the Executive's title, authority, status, duties or responsibilities;\n" +
                "(ii) any reduction in the Executive's Base Salary;\n" +
                "(iii) a material breach by the Company of this Agreement; or\n" +
                "(iv) a change in the location of the Company's principal office to a location more than [RELOCATION DISTANCE] outside of the [metropolitan area of the Executive's home city]. \n" +
                "\n" +
                "Termination by the Executive Without Good Reason. The Executive may terminate his employment under this Agreement at any time for any reason or no reason by giving the Company [NOTICE PERIOD] prior written notice of the termination. Following any such notice, the Company may reduce or remove any and all of Executive s duties, positions and titles with the Company, and any such reduction or removal shall not constitute Good Reason.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Notice and Cure. For purposes of this Agreement, Executive may resign his employment from the Company for \"Good Reason\" by providing notice to the Company [setting forth with reasonable detail the nature of the Good Reason] within [30 days] from the [the Executive's knowledge of the] occurrence of a Good Reason event. Executive's resignation for Good Reason shall only be effective if the Company has not cured or remedied the Good Reason event within [30 days] after its receipt of Executive's written notice.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Termination by the Executive for Good Reason. The Executive may terminate his employment and this Agreement for Good Reason by written notice to the Company, and in that event, the Company shall pay Executive promptly (but not later than 30 days) following the termination of his employment a lump sum equal to one year of Severance Pay. \"Good Reason,\" as used in this Agreement, shall mean, without limitation, (A) any material diminution in the Executive's authority, duties and responsibilities, (B) any reduction in the Executive's Base Salary, (C) any material reduction in the total value of the Executive's fringe benefit compensation, (D) a material breach by the Company of this Agreement, or (E) the Company's failure to provide and maintain Directors and Officers' Liability Insurance in agreed amounts. Before terminating this Agreement for Good Reason, the Executive must give the Company a prior written notice indicating his intent to terminate for Good Reason if corrective action is not taken, and stating the reasons why he believes there are grounds to terminate for Good Reason; after receipt of this notice, the Company shall have 15 days to cure the grounds for Good Reason. In the event of a termination for Good Reason, the Executive will be entitled to payment of all Accrued Obligations, which will be paid promptly (but not later than 30 days) following the date on which the Executive's employment is terminated. The Executive shall also be entitled to payment of any Final Bonus, which shall be determined as provided by Section 2(b) of this Agreement. Any such Final Bonus payment shall be made promptly but not later than as provided by Section 2(b).");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("For Good Reason. Employee shall have the right to terminate this Agreement and Employee's employment hereunder at any time for \"Good Reason\" (as defined below) upon written notice given to the Company. As used herein, \"Good Reason\" means (A) Employee having attained the age of 65; or (B) the occurrence (without the consent of Employee) of any of the following:\n" +
                "(i) The removal of Employee from the position of CFO;\n" +
                "(ii) The assignment to Employee of any duties materially inconsistent with Employee's position (including status, offices, titles and reporting requirements), authority, duties or responsibility as contemplated by Section 2, or any other material diminution in such position, authority, duties or responsibilities;\n" +
                "(iii) A change in the geographic location of Employee's principal place of employment to a location more than forty-five (45) miles beyond (A) the present location of the Company's principal executive offices; and (B) Employee's then principal place of residence; or\n" +
                "(iv) Any failure by the Company to comply with the material provisions of this Agreement, including the provisions of Section 3,\n" +
                "provided that (A) Employee gives notice of any condition listed in clause (i) through (iv) above to the Company within 90 days after the initial existence of the condition, which notice gives the Company 30 days within which to cure the condition; and (B) the condition is not cured within such 30-day period. Any such termination of this Agreement shall be effective upon the expiration of such 30-day period or at such earlier date agreed to by the parties in writing.\n" +
                "\n" +
                "Without Good Reason. Employee shall have the right to terminate this Agreement and Employee's employment hereunder without Good Reason at any time upon not less than three (3) months' prior written notice to the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);
               // Rights ->Termination->Specific Events of Termination->Termination by Executive

         // Rights ->Termination->Termination Benefits

        f = new Fragment("Termination Benefits\n" +
                "\n" +
                "(a) Severance Compensation. If the Executive's employment terminates [Without Cause], [For Good Reason] or [upon a Change in Control], the Executive shall be entitled to Accrued Obligations, Severance Benefits, and Severance Compensation.\n" +
                "(b) Severance Benefits. If the Executive's employment terminates due to death or Disability, the Executive shall be entitled to Accrued Obligations and Severance Benefits.\n" +
                "(c) Termination Payments. If the Executive's employment terminates due to For Cause or without Good Reason, the Executive shall be entitled to Accrued Obligations[, and the Company shall have no obligation to provide any other severance payment or benefit].\n" +
                "\n" +
                "Definitions\n" +
                "\n" +
                "\"Accrued Obligations\" means the sum of the following unpaid benefits as of the Date of Termination:\n" +
                "(a) Accrued Salary. Payment of any earned but unpaid portion of Executive's annual base salary as in effect from time to time (\"Base Salary\") through the effective date of such termination;\n" +
                "(b) Accrued Expenses. Reimbursement for any reasonable, unreimbursed and documented business expense he has incurred in performing Executive's duties hereunder;\n" +
                "(c) Accrued Benefits. payment of any accrued but unpaid benefits (including without limitation, any bonus due by virtue of having met all applicable performance targets prior to the effective date of such termination), and any other rights, as required by the terms of any Executive benefit plan or program of Company.\n" +
                "(c) Coverage Election. The Executive shall have the right to elect continuation coverage of insurance benefits to the extent required by law.\n" +
                "[For the purpose of this definition, except as provided in the applicable plan, program or policy, amounts shall be deemed to accrue ratably over the period during which they are earned, but no discretionary compensation shall be deemed earned or accrued until it is specifically approved by the Board in accordance with the applicable plan, program or policy.]\n" +
                "\n" +
                "\"Date of Termination\" means:\n" +
                "\n" +
                "(a) if the Executive's employment is Terminated by the Company for Cause, or by the Executive for Good Reason, the date of receipt of the Notice of Termination or any later date specified therein, as the case may be,\n" +
                "(b) if the Executive's employment is terminated by the Company other than for Cause or Disability, the Date of Termination shall be the date on which the Company notifies the Executive of such termination and\n" +
                "(c) if the Executive's employment is terminated by reason of death, Retirement or Disability, the Date of Termination shall be the date of death or Retirement of the Executive or the Disability Effective Date, as the case may be.\n" +
                "\n" +
                "\"Severance Benefits\" means the following:\n" +
                "(a) Bonus. Payment of a bonus [throughout such remaining term] or [for a period of [SEVERANCE PERIOD]], where such bonus shall be equal to the greater of (i) the Executive's bonus during the year prior to the Executive's termination or (ii) the bonus that the Executive would have earned under the Company's bonus plan in the year that Executive was terminated had Executive remained in its employment\n" +
                "(b) Options. The Executive shall immediately become vested in any unvested stock options.\n" +
                "(c) Benefits. Pay health and/or dental insurance benefits [throughout such remaining term] or [for a period of [SEVERANCE PERIOD]].\n" +
                "\n" +
                "\"Severance Compensation\" means an amount equal to [SEVERANCE PAYMENT AMOUNT].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Specification of Severance and Termination Benefits\n" +
                "\n" +
                "Termination Benefits\n" +
                "\n" +
                "(a) Severance Benefits. If the Executive's employment terminates due to [Disability], [Without Cause], [For Good Reason] or [upon a Change in Control], the Executive shall be entitled to the following:\n" +
                "(i) Salary. The Company shall [continue to pay the Executive the Executive's Base Salary through the remaining term of the Executive's employment under this Agreement |OR| pay to the Executive an amount equal to [SEVERANCE PAYMENT AMOUNT].\n" +
                "(ii) Bonus. The Company shall continue to pay the Executive an annual bonus(es) throughout such remaining term. Each such bonus shall be equal to the greater of (i) the Executive's bonus during the year prior to the Executive's termination or (ii) the bonus that the Executive would have earned under the Company's bonus plan in the year that Executive was terminated had Executive remained in its employment. Such bonuses shall not be paid until they would normally be paid in accordance with the Company's practices.\n" +
                "(iii) Benefits. The Company shall continue to pay health and/or dental insurance benefits for a period of [BENEFITS CONTINUATION PERIOD] following the Termination Date.\n" +
                "[(iv) Options. The Executive shall immediately become vested in any unvested stock options upon such termination. Executive will have no right to any future ungranted options.\n" +
                "\n" +
                "(b) Termination Payments. If the Executive's employment terminates due to Death, For Cause[, Unacceptable Performance] or Voluntary Termination, the Executive shall be entitled to the following:\n" +
                "(i) Salary. The Executive shall be paid the Executive's salary through the Executive's termination date and not thereafter.\n" +
                "(ii) Bonus. The Executive shall not be entitled to any bonus payments which were not fully earned prior to the Executive's termination date, and the Executive shall not be entitled to any unearned pro-rated bonus payment for the year in which the Executive's employment terminates.\n" +
                "[(iii) Options. Any stock options granted to the Executive by the Company shall continue to vest only through the date on which the Executive's employment terminates.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Severance Benefits\n" +
                "\n" +
                "Severance. In the event of Termination Without Cause of the Executive's employment pursuant to this Section, the Company shall continue to pay to the Executive the Executive's then current Annual Salary throughout such [90-day] notice period and shall pay the Executive (a) [six months] Annual Salary at the Executive's then current salary in equal monthly installments over the six month period following the Termination Date, [provided that such payments shall cease if the Executive becomes employed by a company which is in the Business during such six month period,] and (b) all vacation accrued as of the Termination Date.\n" +
                "\n" +
                "Termination Payments\n" +
                "\n" +
                "Termination With Cause by Company or Without Constructive Discharge by Executive. If Company terminates Executive�s employment and the Employment Period with Cause, or if Executive terminates Executive�s employment and the Employment Period other than as a result of a Constructive Discharge, Company shall be obligated to pay Executive (i) any Base Salary amounts that have accrued but have not been paid as of the Termination Date; and (ii) subject to Section 7.14, the unpaid Performance Bonus, if any, with respect to the calendar year preceding the calendar year in which the Termination Date occurs (such Performance Bonus, if any, to be determined in the manner it would have been determined, and payable at the time it would have been payable, under Section 3.2 had there been no termination of the Employment Period.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Rights ->Termination->Termination Procedures

        f = new Fragment("Notice Requirements. Any Termination by the Company for Cause, or by Executive for Good Reason, shall be communicated by Notice of Termination to the other party hereto given in accordance with the Notice section of this Agreement.\n" +
                "\n" +
                "For purposes of this Agreement, a \"Notice of Termination\" means a written notice which:\n" +
                "(i) indicates the specific termination provision in this Agreement relied upon,\n" +
                "(ii) to the extent applicable, sets forth in reasonable detail the facts and circumstances claimed to provide a basis for termination of Executive's employment under the provision so indicated and\n" +
                "(iii) if the Date of Termination (as defined below) is other than the date of receipt of such notice, specifies the termination date.\n" +
                "The failure by Executive or the Company to set forth in the Notice of Termination any fact or circumstance which contributes to a showing of Good Reason or Cause shall not waive any right of Executive or the Company, respectively, hereunder or preclude Executive or the Company, respectively, from asserting such fact or circumstance in enforcing Executive's or the Company's rights hereunder.\n" +
                "\n" +
                "Date of Termination. \"Date of Termination\" means\n" +
                "(i) if the Executive's employment is Terminated by the Company for Cause, or by the Executive for Good Reason, the date of receipt of the Notice of Termination or any later date specified therein, as the case may be,\n" +
                "(ii) if the Executive's employment is terminated by the Company other than for Cause or Disability, the Date of Termination shall be the date on which the Company notifies the Executive of such termination and \n" +
                "(iii) if the Executive's employment is terminated by reason of death, Retirement or Disability, the Date of Termination shall be the date of death or Retirement of the Executive or the Disability Effective Date, as the case may be.\n" +
                "\n" +
                "Release. Notwithstanding anything in the Severance Benefits section to the contrary, in no event shall the Executive be entitled to receive any amounts, rights or benefits under the Severance Benefits section unless the Executive executes a release of claims against the Company in form and substance as set forth in [the attached Release Form].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Conditions on Severance\n" +
                "\n" +
                "Conditions on Severance. Any payments or benefits made or provided pursuant to Severance Payments section are subject to the Executive's:>\n" +
                "(i) compliance with the provisions of [any Confidentiality, Non-Competition, or Non-Solicitation clauses] contained in this Agreement (to the extent applicable);>\n" +
                "(ii) delivery to the Company of an executed Release and Severance Agreement; and>\n" +
                "(iii) delivery to the Company of a resignation from all offices, directorships and fiduciary positions with the Company, its affiliates and employee benefit plans.\n" +
                "\n" +
                "1.2 Termination Disputes\n" +
                "\n" +
                "Termination Disputes. If within thirty days after any Notice of Termination is given the party receiving such Notice of Termination notifies the other party that a dispute exists concerning the termination, the Date of Termination shall be the date on which the dispute is finally determined by mutual written agreement of the parties, by a binding arbitration award, or by a final judgment, order or decree of a court of competent jurisdiction (the time for appeal therefrom having expired and no appeal having been perfected).\n" +
                "\n" +
                "Any party giving notice of a dispute shall pursue the resolution of such dispute with reasonable diligence. Notwithstanding the pendency of any such dispute, the Company will continue to pay the Executive his full compensation in effect when the notice giving rise to the dispute was given (including, but not limited to, base salary) and continue the Executive as a participant in all compensation, employee benefit and insurance plans, programs and arrangements in which the Executive was participating when the notice giving rise to the dispute was given, until the dispute is finally resolved.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Notice of Termination. Except for termination as specified in Section 3(a), any termination of the Executive�s employment by the Company or any such termination by the Executive shall be communicated by written Notice of Termination to the other party hereto. For purposes of this Agreement, a �Notice of Termination� shall mean a notice which shall indicate the specific termination provision in this Agreement relied upon.\n" +
                "\n" +
                "Date of Termination. �Date of Termination� shall mean: (i) if the Executive�s employment is terminated by his death, the date of his death; (ii) if the Executive�s employment is terminated on account of disability under Section 3(b) or by the Company for Cause under Section 3(c) or by the Company without Cause under Section 3(d), the date on which a Notice of Termination is given; (iii) if the Executive�s employment is terminated by the Executive under Section 3(e) without Good Reason, 30 days after the date on which a Notice of Termination is given, and (iv) if the Executive�s employment is terminated by the Executive under Section 3(e) with Good Reason, the date on which a Notice of Termination is given after the end of the Cure Period. Notwithstanding the foregoing, in the event that the Executive gives a Notice of Termination to the Company, the Company may unilaterally accelerate the Date of Termination and such acceleration shall not result in a termination by the Company for purposes of this Agreement.\n" +
                "\n" +
                "Resignation on Termination. On the Date of Termination, the Executive shall resign from all positions with the Company and its subsidiaries. In addition, if the Executive is then serving as a member of the Board or the Board of Directors of a subsidiary, the Executive shall tender his resignation from such directorship(s) on the Date of Termination.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Notice of Termination. Any termination by the Company for Cause, or by the Executive for Good Reason, shall be communicated by Notice of Termination to the other party hereto given in accordance with Section 10 of this Agreement. For purposes of this Agreement, a �Notice of Termination� means a written notice which (i) indicates the specific termination provision in this Agreement relied upon, (ii) to the extent applicable, which sets forth in reasonable detail the facts and circumstances claimed to provide a basis for termination of the Executive�s employment under the provisions as indicated and (iii) if the Date of Termination (as that term is defined below) is other than the date of receipt of such notice, specifies the termination date. The failure by the Executive or the Company to set forth in the Notice of Termination any fact or circumstance which contributes to a showing of Good Reason or Cause shall not waive any right of the Executive or the Company, respectively, hereunder or preclude the Executive or the Company, respectively, from asserting such fact or circumstance in enforcing the Executive�s or the Company�s rights hereunder.\n" +
                "\n" +
                "Date of Termination. �Date of Termination� means the date the Executive�s employment with the Company terminates.\n" +
                "\n" +
                "Release. The Executive�s right to receive any payment of any amount or provision of any benefit under this Section 6 (other than payments due to the Executive�s death), shall be conditioned upon the Executive�s execution of a binding and complete release of the Company and its affiliates and related parties substantially in the form attached hereto as Exhibit B, and expiration of any waiting periods contained in such release.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

              // Rights ->Claims->Employment Indemnification

        f = new Fragment("Indemnification The Company shall indemnify the Executive, to the maximum extent permitted by applicable law [and by its certificate of incorporation], against all costs, charges and expenses incurred or sustained by the Executive in connection with any action, suit or proceeding to which the Executive may be made a party by reason of being an officer, director or employee of the Company or of any subsidiary or affiliate of the Company or any other corporation for which the Executive serves [in good faith] as an officer, director, or employee at the Company's request.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Insurance\n" +
                "\n" +
                "Example: The Executive shall be entitled to the full protection of any insurance policies which the Company may elect to maintain generally for the benefit of its officers.\n" +
                "\n" +
                "Coverage Level\n" +
                "\n" +
                "Example: The Company agrees to maintain Directors and Officers Liability Insurance for the benefit of Executive having coverage and policy limits no less favorable to directors and officers than those in effect at the Effective Date.\n" +
                "\n" +
                "Notice\n" +
                "\n" +
                "Example: The Executive agrees promptly to notify the Company of any actual or threatened claim arising out of or as a result of the Executive's employment with the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Indemnification. Employer shall indemnify Employee against all claims arising out of Employee's actions or omissions occurring during Employee's employment with Employer to the fullest extent provided (A) by Employer's Certificate of Incorporation and/or Bylaws, and (B) under the Nevada or Florida General Corporation Law, as each may be amended from time to time. Employer may maintain a Directors & Officers liability insurance policy (\"D&O Coverage\") covering Employee to the extent Employer provides such coverage for its other executive officers. Employer agrees to make such policy available to Employee within five (5) days, upon request.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Indemnification; Insurance. During your employment and thereafter, the Company shall, on the same basis as is provided for the Company's continuing officers and directors from time to time, indemnify and hold you harmless against any costs or expenses (including attorneys' fees), judgments, fines, losses, claims, damages or liabilities incurred in connection with any claim, action, suit, proceeding or investigation, whether civil, criminal, administrative or investigative, by reason of the fact that you are or were a director, officer, employee or agent of the Company or any Subsidiary, whether asserted or claimed prior to,at or after the date of your termination of employment, to the fullest extent permitted under applicable law and on a basis no less favorable than in existence under the Company's Bylaws and Certificate of Incorporation in effect as of the Effective Date. During your employment and thereafter, Company shall provide to you coverage under a policy of directors' and officers' liability insurance that provides you with coverage on the same basis as is provided for the Company's continuing officers and directors from time to time.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Remedies ->Enforcement->Remedies Cumulative 
//TODO: This is a No Waiver Clause.

        f = new Fragment("Remedies Cumulative. All rights and remedies provided in this Agreement are cumulative and not exclusive of any other rights or remedies that may be available to the parties, whether provided by law, equity, statute, in any other agreement between the parties or otherwise.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Rights and Remedies Cumulative. The enumeration of Lender's rights and remedies set forth in this Loan Agreement is not intended to be exhaustive. The exercise by Lender of any right or remedy under this Loan Agreement does not preclude the exercise of any other rights or remedies, all of which are cumulative and are in addition to any other right or remedy given under this Loan Agreement or under any other agreement between Lender and any Borrower or Guarantor or which may now or subsequently exist in law, in equity, by statute or otherwise.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("No Waiver; Cumulative Remedies. A failure to exercise or a delay in exercising, on the part of the Lender, any right, remedy, power or privilege hereunder or under the other Loan Documents shall not operate as a waiver thereof; nor shall any single or partial exercise of any right, remedy, power or privilege hereunder preclude any other or further exercise thereof or the exercise of any other right, remedy, power or privilege. The rights, remedies, powers and privileges herein provided are cumulative and not exclusive of any rights, remedies, powers and privileges provided by law.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        

        // Remedies ->Enforcement->Specific Performance 

        f = new Fragment("Specific Performance. Any breach of this Agreement may result in irreparable damage to [Party2] for which [Party 2] will not have an adequate remedy at law. Accordingly, in addition to any other remedies and damages available, [Party1] acknowledges and agrees that [Party2] may immediately seek enforcement of this Agreement by means of specific performance or injunction, without any requirement to post a bond or other security.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The Executive acknowledges that in the event of breach or threatened breach by the Executive of the terms of Section 1 hereof, the Company could suffer significant and irreparable harm that could not be satisfactorily compensated in monetary terms, and that the remedies at law available to the Company may otherwise be inadequate and the Company shall be entitled, in addition to any other remedies to which it may be entitled to under law or in equity, to specific performance of this Agreement by the Executive including the immediate ex parte issuance, without bond, of a temporary restraining order enjoining the Executive from any such violation or threatened violation of Section 1 hereof and to exercise such remedies cumulatively or in conjunction with all other rights and remedies provided by law and not otherwise limited by this Agreement. The Executive hereby acknowledges and agrees that the Company shall not be required to post bond as a condition to obtaining or exercising any such remedies, and the Executive hereby waives any such requirement or condition");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("[Party 1] acknowledges that (i) the restrictions contained in this Agreement are reasonable in scope and are necessary to protect the [Party 2]'s legitimate interests in protecting its business, and (ii) any violation of the restrictions contained in this Agreement will cause significant and irreparable harm to the [Party 2] for which the [Party 2] has no adequate remedy at law");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("[Party 2] shall also be entitled to obtain injunctive relief, including but not limited to a temporary restraining order, a temporary or preliminary injunction or a permanent injunction, to enforce the provisions of this Agreement, as well as an equitable accounting of and constructive trust for all profits or other benefits arising out of or related to any such violation, all of which shall constitute rights and remedies to which the [Party 2] may be entitled.\"\n" +
                "\n" +
                "Without Bond or Proof of Damages\n" +
                "\n" +
                "Example\n" +
                "[Party 2] shall be entitled to such relief without the necessity of proving actual damages or posting a bond, in addition to, and not in lieu of, any other rights and remedies available to [Party 2] under law or in equity have the right and remedy to have the provisions of this agreement enforced by injunctive relief in any court of competent jurisdiction, it being agreed that any breach or threatened breach of this agreement would cause irreparable injury to [Party 2] and that damages would not provide an adequate remedy to [Party 2]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Remedies ->Damages->Liquidated Damages 

// Remedies ->Damages->Mitigation (Employement) 

        f = new Fragment("Mitigation\n" +
                "(a) Other Employment. The Employee shall have no duty to mitigate the payment of any amount or benefit provided for in this agreement by seeking other employment or in any other manner.\n" +
                "(b) No Offset or Reduction. No such payment or benefit shall be eliminated, offset or reduced by the amount of any compensation provided to the Employee in any subsequent employment, any retirement benefits, or any other source.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1. Employer Claims\n" +
                "\n" +
                "The Employer can include a clause reserving their right to pursue any claim they may have. While a mitigation clause gives no affirmative duty for the Employee to actively limit the benefits the Employer owes them, this clause explicitly lays out that any further liability the Employee might have remains.\n" +
                "\n" +
                "Example: The Employer's obligation to make the payments provided for in this Agreement and otherwise to perform its obligations hereunder shall not be affected by any set-off, counterclaim, recoupment, defense or other claim, right or action which the Employer may have against the Employee or others, provided that nothing herein shall preclude the Employer from separately pursuing recovery from the Employee based on any such claim.\n" +
                "\n" +
                "1.2. Payments Guaranteed by Law\n" +
                "\n" +
                "The jurisdiction where the severance agreement is signed may have laws that require an Employer to pay an Employee certain benefits under specific circumstances. An Employer can protect against double paying an Employee by reducing the amount they pay by any legally required payments.\n" +
                "\n" +
                "Example: The amount payable under this agreement shall be reduced by the amount of any severance, termination, or notice pay (or any other similar amounts) required by law to be paid to the Employee by the Employer and by any salary or other amounts paid to the Employee during any notice period that the Employer is required by law to provide.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication

        // Adjudication ->Agreement Scope->Entire Agreement 

        f = new Fragment("This Agreement [(together with the documents [referred to in this Agreement] [listed on Exhibit A])] constitute[s] the entire agreement between the parties with respect to its subject matter and supersedes all prior agreements, representations and understandings of the parties, written or oral.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The Transaction Documents, together with the exhibits and schedules thereto, contain the entire understanding of the parties with respect to the subject matter hereof and supersede all prior agreements and understandings, oral or written, with respect to such matters, which the parties acknowledge have been merged into such documents, exhibits and schedules. ");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("This Agreement, together with the exhibits and schedules hereto, constitutes the entire understanding and agreement of the parties hereto with respect to the subject matter hereof and supersedes all prior and contemporaneous agreements or understandings, inducements or conditions, express or implied, written or oral, between the parties with respect hereto other than the Confidentiality Agreement. The express terms hereof control and supersede any course of performance or usage of the trade inconsistent with any of the terms hereof.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Adjudication ->Agreement Scope->Further Assurances 

        f = new Fragment("Further Assurances The parties shall execute such further documents and do any and all such further things as may be necessary to implement and carry out the intent of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Each party shall do and perform, or cause to be done and performed, all such further acts and things, and shall execute and deliver all such other agreements, certificates, instruments and documents, as the other party may reasonably request in order to carry out the intent and accomplish the purposes of this Agreement and the consummation of the transactions contemplated hereby.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The parties shall upon reasonable request of the other, execute such documents as may be necessary or appropriate to carry out the intent of this Agreement.\n" +
                "\n" +
                "1.3 Example 3\n" +
                "\n" +
                "Subject to the terms of this Agreement, at and after the Closing, the Parties shall execute and deliver such documents and other instruments and take such further actions as may be reasonably necessary to carry out the provisions hereof and consummate the Merger.\n" +
                "\n" +
                "1.4 Example 4\n" +
                "\n" +
                "Each party hereto shall execute and cause to be delivered to each other party hereto such instruments and other documents, and shall take such other actions, as such other party may reasonably request (prior to, at or after the Closing) for the purpose of carrying out or evidencing any of the transactions contemplated by this Agreement.\n" +
                "\n" +
                "1.5 Example 5\n" +
                "\n" +
                "In case at any time after the Effective Time any further action is reasonably necessary to carry out the purposes of this Agreement or the transactions contemplated by this Agreement, the proper officers of the Company, Parent and the Surviving Corporation shall take any such reasonably necessary action.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Adjudication ->Agreement Scope->Counterparts 

        f = new Fragment("This Agreement may be executed in counterparts, each of which shall be deemed to be an original, but all of which, taken together, shall constitute one and the same agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Example 1—effective upon signature and delivery\n" +
                "\n" +
                "This Agreement may be executed in counterparts (each of which shall be deemed to be an original but all of which taken together shall constitute one and the same agreement) and shall become effective when one or more counterparts have been signed by each of the parties and delivered to the other party.\n" +
                "\n" +
                "Example 2—facsimile\n" +
                "\n" +
                "This Agreement may be executed in two or more counterparts, and by facsimile, all of which shall be considered one and the same agreement and shall become effective when one or more counterparts have been signed by each of the parties and delivered to the other party, it being understood that all parties need not sign the same counterpart.\n" +
                "\n" +
                "Example 3—electronic execution\n" +
                "\n" +
                "This Agreement may be executed in multiple counterparts and transmitted by facsimile or by electronic mail in \"portable document format\" (\"PDF\") form, or by any other electronic means intended to preserve the original graphic and pictorial appearance of a party's a signature. Each such counterpart and facsimile or PDF signature shall constitute an original and all of which together shall constitute one and the same original.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Adjudication ->Agreement Scope->Amendments 

        f = new Fragment("This Agreement may be amended only by a written instrument signed by the Parties.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Bilateral Amendment\n" +
                "\n" +
                "No amendment to this agreement will be effective unless it is in writing and signed by both parties.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Electronic Amendment\n" +
                "\n" +
                "Licensor reserves the right, in its sole discretion, to amend this Agreement from time to time by posting amendments to the Licensor's web site. [If the Licensee does not accept amendments made to this agreement, then this License will be immediately terminate.]");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Unilateral Amendment\n" +
                "\n" +
                "Sophos may amend the terms and conditions of this End-User Licence Agreement at any time by reasonable notice, including without limitation by posting revised terms on its website at the URL www.sophos.com/legal/, which amended terms and conditions shall be binding upon You. Spohos End User License Agreement");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Authorization Person\n" +
                "\n" +
                "The parties may not amend this agreement, except by written agreement executed by [TITLE OR POSITION OF AUTHORIZED INDIVIDUAL] of each party.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Acquisition Agreement--Approval\n" +
                "\n" +
                "This Agreement may be amended by action taken by Parent and by action taken by or on behalf of the respective boards of directors of Merger Sub and the Company at any time before the Effective Time; provided, however, that after the adoption of this Agreement by the stockholders of the Company, no amendment shall be made which under the DGCL requires further approval by such stockholders without obtaining such further approval. This Agreement may be amended only by an instrument in writing signed by the parties hereto.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Adjudication ->Agreement Scope->Schedules and Exhibits 

        f = new Fragment("Schedules and Exhibits The exhibits and schedules referred to in this Agreement will be deemed to be a part of this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("The Recitals, Schedules and Exhibits to this Agreement are incorporated herein and, by this reference, made a part hereof as if fully set forth herein.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("The recitals, Annexes, Exhibits and Schedules identified in this Agreement are incorporated herein by reference and made a part hereof. Each disclosure in a Schedule referred to in this Agreement shall be deemed to qualify all representations and warranties of the party making such disclosure, notwithstanding the absence of a specific cross-reference, except to the extent that its applicability to a particular representation, warranty, agreement or condition is not reasonably apparent from the disclosure thereof.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The Schedules to this Agreement are hereby incorporated in and made a part of this Agreement as if set forth in full herein and are an integral part of this Agreement. Matters set forth in the Schedules are not necessarily limited to matters required by this Agreement to be reflected in the schedules. Such additional matters are set forth for informational purposes, and the Schedules do not necessarily include other matters of a similar nature. Nothing in this Agreement or in the Schedules constitutes an admission that any information disclosed, set forth or incorporated by reference in the Schedules or this Agreement is material, constitutes a Material Adverse Effect or is otherwise required by the terms of this Agreement to be so disclosed, set forth or incorporated by reference. All information and disclosures contained in the Schedules are made as of the date of this Agreement and their accuracy is confirmed only as of such date and not at any time thereafter. Matters disclosed pursuant to any section of this Agreement are deemed to be disclosed with respect to all sections of this Agreement and the Schedules to the extent this Agreement requires such disclosure; provided that it is clear by appropriate cross-references that a given disclosure is applicable to such other Schedules, sections and paragraphs. Any capitalized terms used in any Schedule or Exhibit but not otherwise defined therein shall be defined as set forth in this Agreement.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication ->Agreement Scope->Effective Date 

        f = new Fragment("Effective Date The effective date of this Employment Agreement (the \"Effective Date\") shall be [DATE].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Controlled by Opening Statement\n" +
                "\n" +
                "This agreement is effective as of the date shown at the top of the first page, even if any signatures are made after that date.\n" +
                "\n" +
                "1.2 Effective Upon Last Signature\n" +
                "\n" +
                "This Credit Agreement shall be binding and deemed effective when executed by Borrower, Administrative Agent, and each Lender whose signature is provided for on the signature pages hereof (the \"Effective Date\").");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication ->Parties and Representatives->Notices 

        f = new Fragment("Notices:\n" +
                "(a) Form of Notice. All notices, requests, claims, demands and other communications between the parties shall be in writing.\n" +
                "\n" +
                "(b) Method of Notice. All notices shall be given (i) by delivery in person (ii) by a nationally recognized next day courier service, (iii) by first class, registered or certified mail, postage prepaid, (iv) by facsimile [or (v) by electronic mail] to the address of the party specified in this Agreement or such other address as either party may specify in writing.\n" +
                "\n" +
                "(c) Receipt of Notice. All notices shall be effective upon (i) receipt by the party to which notice is given, or (ii) on the [fifth (5th)] day following mailing, whichever occurs first.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Form—Writing and Language\n" +
                "\n" +
                "All notices, requests, claims, demands and other communications shall be in writing [in the English language] [and shall be signed by a person duly authorized to provide such notice].\n" +
                "\n" +
                "1.2 How—Method and Address\n" +
                "\n" +
                "Notices permitted or required to be given hereunder shall be deemed sufficient if given by (a) registered or certified mail, postage prepaid, return receipt requested, (b) private courier service, or (c) facsimile addressed to the respective addresses of the parties as first above written or at such other addresses as the respective parties may designate by like notice from time to time.\n" +
                "\n" +
                "1.3 When—Time of Receipt\n" +
                "\n" +
                "Such notice shall be deemed to have been given upon receipt.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("All notices \"shall be deemed to have been duly given (a) when delivered in person, (b) upon confirmation of receipt when transmitted by facsimile transmission or by electronic mail (but, in the case of electronic mail, only if followed by transmittal by national overnight courier or hand for delivery on the next Business Day), (c) upon receipt after dispatch by registered or certified mail, postage prepaid or (d) on the next Business Day if transmitted by national overnight courier (with confirmation of delivery).");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("2.1 Change of Address\n" +
                "\n" +
                "Any party to this Agreement may notify any other party of any changes to the address or any of the other details specified in this paragraph; provided, however, that such notification shall only be effective on the date specified in such notice or five Business Days after the notice is given, whichever is later.\n" +
                "\n" +
                "Refusal of Delivery\n" +
                "\n" +
                "Rejection or other refusal to accept or the inability to deliver because of changed address of which no notice was given shall be deemed to be receipt of the notice as of the date of such rejection, refusal or inability to deliver. Verizon Merger");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication ->Parties and Representatives->Binding Effect 

        f = new Fragment("Binding Effect. This Agreement shall be binding upon and inure to the benefit of the parties and their respective heirs, successors and permitted assigns.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("\n" +
                "No Third Party Beneficiary. The Agreement [does/will] not confer any rights or remedies upon any person other the parties and their respective heirs, executors, administrators, personal representatives, beneficiaries, successors and permitted assigns.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication ->Parties and Representatives->Third Parties 

        f = new Fragment("Third Party Beneficiaries. This agreement does not and is not intended to confer any rights or remedies upon any person other than the parties.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Example 1\n" +
                "\n" +
                "Third Party Beneficiaries. Nothing in this Agreement, express or implied, is intended to or shall confer upon any other person any right, benefit or remedy of any nature whatsoever under or by reason of this Agreement.\n" +
                "\n" +
                "Example 2—Specific Rights Carve-Out\n" +
                "\n" +
                "Third Party Beneficiaries. [Except as set forth in _________], this agreement does not and is not intended to confer any rights or remedies upon any person other than the parties.\n" +
                "\n" +
                "Example 3—Specific Beneficiaries Carve-Out\n" +
                "\n" +
                "Third Party Beneficiaries. This agreement confers rights and remedies upon [named beneficiaries] as set forth in section [___]. No person, other than the parties and such [named beneficiaries], has any rights or remedies under the agreement. The parties reserve the right to amend or terminate this agreement with the consent of the [named beneficiaries].\n" +
                "\n" +
                "Example 4—Reservation of Rights to Amend; No Consent Required\n" +
                "\n" +
                "Third Party Beneficiaries. This agreement confers rights and remedies upon [named beneficiaries] as set forth in section [___]. No person, other than the parties and such [named beneficiaries], has any rights or remedies under the agreement. The parties reserve the right to amend or terminate this agreement with the consent of the [named beneficiaries].\n" +
                "\n" +
                "Example 5—Consent Required\n" +
                "\n" +
                "Third Party Beneficiaries. This agreement confers rights and remedies upon [named beneficiaries] as set forth in section [___]. No person, other than the parties and such [named beneficiaries], has any rights or remedies under the agreement. The parties may not amend or terminate this agreement without the prior written consent of the [named beneficiaries].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication ->Parties and Representatives->Successors and Assigns 


        f = new Fragment("Successors and Assigns. This Agreement shall be binding upon and shall inure to the benefit of the parties and their [permitted] successors and assigns.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("Example 1\n" +
                "\n" +
                "Successors and Assigns. This agreement benefits and binds the parties and their [permitted] successors and assigns.\n" +
                "\n" +
                "Example 2—Application to Natural Persons\n" +
                "\n" +
                "Successors and Assigns. This Agreement shall be binding upon and shall inure to the benefit of the parties and their heirs, executors, administrators, [successors] and [permitted] assigns.\n" +
                "\n" +
                "Note: The term \"successor\" can probably be omitted when applied to natural person, because individuals do not have successors.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

// Adjudication ->Enforcement->Governing Law 


        f = new Fragment("This Agreement shall be governed, construed, and enforced in accordance with the laws of the State of [GOVERNING LAW STATE], without regard to its conflict of laws rules.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("This Agreement shall be governed by and construed in accordance with the laws of the State of [GOVERNING LAW STATE], without regard to its conflict of laws rules.\n");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("This agreement shall be governed by, and construed in accordance with, the [internal OR domestic OR mandatory] laws of the state of:\n" +
                "(a) regardless of the laws that might otherwise govern under applicable principles of conflicts of laws thereof\n" +
                "OR\n" +
                "(b) applicable to agreements made and to be performed solely therein, without giving effect to principles of conflicts of law\n" +
                "OR\n" +
                "(c) without giving effect to any choice or conflict of Law provision or rule (whether of the State of [________] of any other jurisdiction) that would cause the application of the Laws of any jurisdiction other than the State of ");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);


        f = new Fragment("This Agreement shall in all respects be governed by, and construed and interpreted in accordance with, the Laws of the State of New York without giving effect to any conflicts of law principles of such state that might refer the governance, construction or interpretation of this Agreement to the Laws of another jurisdiction.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Adjudication ->Enforcement->Jurisdiction 

        f = new Fragment("Jurisdiction. The parties submit all their disputes arising out of or in connection with this Agreement to the exclusive jurisdiction of the Courts of [ ].");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("Venue.\n" +
                "Courts. Any action at law, suit in equity or judicial proceeding arising directly, indirectly, or otherwise in connection with, out of, related to or from this Agreement shall be litigated only in the courts of the State of [ ]. Consent to Jurisdiction. The Parties consent to the jurisdiction of such courts over the subject matter set forth in Section 8. Waiver of Right to Transfer. The Executive waives any right the Executive may have to transfer or change the venue of any litigation brought against the Executive by the Company.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        // Adjudication ->Interpretation->Severability 

        f = new Fragment("Severability. The invalidity or unenforceability of any provisions of this Agreement shall not affect the validity or enforceability of any other provision of this Agreement, which shall remain in full force and effect.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.1 Severability—Renegotiation\n" +
                "\n" +
                "If any term or other provision of this Agreement is determined to be invalid, illegal or incapable of being enforced by any rule or law, or public policy, all other conditions and provisions of this Agreement shall nevertheless remain in full force and effect so long as the economic or legal substance of the transactions contemplated hereby is not affected in any manner materially adverse to any party. Upon such determination that any term or other provision is invalid, illegal or incapable of being enforced, the parties hereto shall negotiate in good faith to modify this Agreement so as to effect the original intent of the parties as closely as possible in an acceptable manner to the end that transactions contemplated hereby are fulfilled to the extent possible.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("1.2 Reformation (\"Blue Pencil\")\n" +
                "\n" +
                "Each term and provision of this agreement shall be valid and enforceable to the fullest extent permitted by law and any invalid, illegal or unenforceable term or provision shall be deemed replaced by a term or provision that is valid and enforceable and that comes closest to expressing the intention of the invalid, illegal or unenforceable term or provision.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

        f = new Fragment("If the final judgment of such court or arbitrator declares that any term or provision hereof is invalid, void or unenforceable, the parties agree to reduce the scope, duration, area or applicability of the term or provision, to delete specific words or phrases, or to replace any invalid, void or unenforceable term or provision with a term or provision that is valid and enforceable and that comes closest to expressing the original intention of the invalid or unenforceable term or provision.");
        new WaiverExtractor().classify( f, o, tree );

        assertFalse(d.isA());
        assertFalse(d.getType() instanceof WaiverExtractor);

    
    
        // Adjudication ->Interpretation->Waiver 



        f = new Fragment("A party's failure to exercise or delay in exercising any right, power or privilege under this Agreement shall not operate as a waiver; nor shall any single or partial exercise of any right, power or privilege preclude any other or further exercise");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The waiver by the Employer of a breach of any provision of this Agreement by Employee will not operate or be construed as a waiver of any other subsequent breach by Employee.");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);

        f = new Fragment("No General Waivers. The failure of any party at any time to require performance of any provision or to resort to any remedy provided under this Agreement shall in no way affect the right of that party to require performance or to resort to a remedy at any time thereafter, nor shall the waiver by any party of a breach be deemed to be a waiver of any subsequent breach. A waiver shall not be effective unless it is in writing and signed by the party against whom the waiver is being enforced.");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);

        f = new Fragment("No course of dealing, nor any failure to exercise, nor any delay in exercising any right, power or privilege hereunder shall operate as a waiver thereof.");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);

        f = new Fragment("A party's waiver of any breach, default or right under this Agreement must be in writing and signed by the party against whom the waiver is being enforced. Any such waiver shall not be deemed a waiver of any subsequent breach, default or right, whether of the same nature or otherwise.");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The waiver by either party of a breach of any provision of this Agreement must be in writing and shall not operate or be construed as a waiver of any other breach.");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);

        f = new Fragment("The rights and remedies under this Agreement are cumulative and not exclusive of any rights, remedies, powers and privileges that may otherwise be available to the parties.");
        new WaiverExtractor().classify( f, o, tree );

        d= o.getDefinitions().get( 0 ); assertTrue(d.isMatch());
        assertTrue(d.getType() instanceof WaiverExtractor);



    }

    */
     
}
