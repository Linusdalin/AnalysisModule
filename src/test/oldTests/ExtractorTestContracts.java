package test.oldTests;

import analysis.FeatureExtractorInterface;
import extractors.*;
import org.junit.Test;
import parse.AnalysisFragment;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**********************************************************************
 *
 * Created with IntelliJ IDEA.
 * User: Linus
 * To change this template use File | Settings | File Templates.
 *
 *
 *
 */



public class ExtractorTestContracts {



    @Test
    public void testEmploymentContract(){


        FeatureExtractorInterface warrantyExtractor= new WarrantyExtractor();
        FeatureExtractorInterface waiverExtractor= new WaiverExtractor();
        FeatureExtractorInterface subsidiaryExtractor= new SubsidiaryExtractor();
        FeatureExtractorInterface dummyExtractor= new DummyExtractor();


        List<FeatureExtractorInterface> all =  Arrays.asList(

                waiverExtractor, warrantyExtractor, subsidiaryExtractor, dummyExtractor

                );

        FragmentTestClassification[] employmentAgreement = new FragmentTestClassification[]{

/* Bargain clauses: Type Performance Agreements: Employment*/
            new FragmentTestClassification(
                new AnalysisFragment("Employment. Employer employs the Employee and Employee accepts such employment upon the terms and conditions of this Agreement."),
                Arrays.asList(dummyExtractor)),
                /*Arrays.asList indicates Extractors that are related (extractorPositives) to the fragment*/

            new FragmentTestClassification(
                new AnalysisFragment("1.1 Employment at Will\n" +
                        "\n" +
                        "Employment at Will. Company employs Employee, and Employee accepts such employment as an at-will employee. This employment may be terminated at any time for any reason with or without \"Cause\" by the Company or the Employee.\n" +
                        "\n" +
                        "1.2 Continued Employment\n" +
                        "\n" +
                        "(a) Continued Employment. Company agrees to continue to employ Employee, and employee agrees to such continued employment upon the terms and conditions set forth in this Agreement.\n" +
                        "\n" +
                        "1.3 Concurrent Employment\n" +
                        "\n" +
                        "Concurrent Employment. During the term of this Agreement, Employee and the Company acknowledge that Employee may be concurrently employed by the Company and a subsidiary or an \"Affiliate\", and that all of the terms and conditions of this Agreement shall apply to such concurrent employment. Reference to the Company in this Agreement includes any such concurrent employers."),
                 Arrays.asList(dummyExtractor)),
        /*Bargain clauses: Type Performance Agreements: Engagement Consulting*/

        /*Bargain clauses: Type Performance Agreements:Financial and Advisory Services*/

        /*Bargain clauses: Type Performance Agreements: Business Services*/

        /*Bargain clauses: Type Performance Agreements:Severance*/

            new FragmentTestClassification(
                 new AnalysisFragment("Severance. In exchange for the release and covenant not to sue, the Company will pay Executive the Severance Benefits, subject to the terms and conditions of this Agreement."),
                 Arrays.asList(dummyExtractor)),

         /*Bargain clauses: Type Performance Agreements:Provision of Information*/

            new FragmentTestClassification(
                 new AnalysisFragment("Provision of Information. The Parties agree to exchange Confidential Information under the terms and conditions of this Agreement.\n" +
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
                         "(d) Independently Developed. Information that is independently developed by the Receiving Party without the use of Confidential Information or breach of this Agreement."),

                 Arrays.asList(dummyExtractor)),

    /* Exchange: Type: Performance Agreements: Employment:Terms of Employment*/

            new FragmentTestClassification(
                new AnalysisFragment("Terms of Employment.\n" +
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
                        "provided that such activities do not materially interfere with the proper performance of the duties and responsibilities under this Agreement."),
                Arrays.asList(dummyExtractor)),

            new FragmentTestClassification(
                new AnalysisFragment("Modification of Duties by Mutual Consent\n" +
                        "\n" +
                        "Duties and Responsibilities. Employee will serve as [POSITION OR TITLE], or in such other capacity mutually agreed between Employee and the Company[ by written amendment of this Agreement].\n" +
                        "\n" +
                        "Duties and Responsibilities—Definition of Duties as Customary for Position\n" +
                        "\n" +
                        "Duties and Responsibilities. Employee will serve as [POSITION OR TITLE] and perform all duties and exercise such authority customarily performed and held by persons holding equivalent positions in companies similar in nature and size to the Company as reasonably defined, modified and delegated from time to time by the Company."),
                Arrays.asList(dummyExtractor)),

            new FragmentTestClassification(
                new AnalysisFragment("Employee Reporting to CEO and Board\n" +
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
                        "Outside Activities. Employee may not, during the term of this Agreement, be engaged in any other business activity without the prior written consent of the Company; provided, that this restriction shall not prevent Employee from investing personal assets or performing business entities that are not in competition with the Company or its affiliates."),
                Arrays.asList(dummyExtractor)),

        /*Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits ->Compensation: Base Salary*/


            new FragmentTestClassification(
                new AnalysisFragment("Base Salary.\n" +
                        "\n" +
                        "(a) Salary. Employee shall receive a base salary in the amount of [COMPENSATION] (\"Base Salary\").\n" +
                        "\n" +
                        "(b) Payment. The Base Salary shall be payable in accordance with the customary payroll practices of the Employer, [but in no event less frequently than monthly].\n" +
                        "\n" +
                        "(c) Adjustment. The Base Salary may be increased [or decreased] from time to time during the term of this Agreement at the [sole] discretion of the Company."),
                Arrays.asList(dummyExtractor)),

            new FragmentTestClassification(
                new AnalysisFragment("Fixed Increases\n" +
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
                        "From time to time during the Term, the Company will review the Base Salary and may make such upward adjustments, if any, as the Company, in its sole discretion determines to be appropriate in light of the performance of the Employee."),
                Arrays.asList(dummyExtractor)),

/*Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits ->Compensation: Signing Bonus*/

            new FragmentTestClassification(
                new AnalysisFragment("Signing Bonus.[Upon execution of this Agreement,] Employer shall pay to Employee an initial signing bonus of [signing bonus amount]."),
                Arrays.asList(dummyExtractor)),

            new FragmentTestClassification(
                new AnalysisFragment("Optional ProvisionsSigning Bonus with Clawback\n" +
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
                        "Employee shall receive a signing bonus in the amount of [SIGNING BONUS AMOUNT] to be paid on the Effective Date. Further, a maximum of [MAXIMUM LEGAL EXPENSE REIMBURSEMENT] shall be reimbursed to Employee for legal expenses incurred during the negotiation of this Agreement upon presentation of transaction receipts."),
                Arrays.asList(dummyExtractor)),


        /*Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Compensation: Bonus*/
            new FragmentTestClassification(
                new AnalysisFragment("Bonus. For each [fiscal/calendar] year during the term of employment, the Executive shall be eligible to receive a bonus in the amount, if any, as may be determined from time to time by the Board in its discretion."),
                Arrays.asList(dummyExtractor)),

            new FragmentTestClassification(
                new AnalysisFragment(""),
                Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Discretionary Bonus\n" +
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
                                "(c) If the Company's EBIT Growth is greater than 20%, Executive shall be paid a cash bonus of 5% of the EBIT Growth."),
                        Arrays.asList(dummyExtractor)),

                        /*Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Compensation: Incentive Compensation*/

                new FragmentTestClassification(
                        new AnalysisFragment("Incentive Compensation. The Executive shall be entitled to participate in all cash incentive, equity incentive, savings and retirement plans, practices, policies, and programs applicable generally to other [senior] executives of the Company."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("Alternative clauses Equal Availability\n" +
                                "\n" +
                                "Long-Term Incentive Plans. The Executive shall participate in long-term incentive plans including all stock option plans and other long-term incentive plans the Company may adopt from time to time on a basis no less favorable than that provided to any other executive officers of the Company.\n" +
                                "\n" +
                                "No Less Favorable than Original Terms\n" +
                                "\n" +
                                "Long-Term Incentive Plans. The Executive shall participate in long-term incentive plans including all stock option plans and other long-term incentive plans the Company may adopt from time to time: (a) on a basis no less favorable than that provided to any other executive officers of the Company, and (b)at least comparable to those provided to the Executive immediately before the Effective Date."),
                        Arrays.asList(dummyExtractor)),

                    /*Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Benefits*/

                new FragmentTestClassification(
                        new AnalysisFragment("Benefits. During the term of Employment, the Executive shall be entitled to participate in employee benefit plans generally made available to [senior] executives of the Company."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("Benefits. The Executive shall be eligible to participate in and receive all fringe benefits available under all benefit programs normally available to employees of the Company holding positions similar to that of the Executive, as may be in effect from time to time, including such pension, profit sharing, stock option, life insurance, disability insurance, health insurance and dental insurance plans and any other benefits and plans as may be implemented by the Company from time to time."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Stated Vacation Leave\n" +
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
                                "The vacation time shall be taken at such time or times as Executive and Employer may reasonably determine."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Insurance-Medical Coverage\n" +
                                "\n" +
                                "Health, Dental, Life and Disability Coverage. The Company shall provide Executive with life, medical, dental and disability coverage made available to its senior executives and key management employees, subject to and on a basis consistent with the terms, conditions and overall administration of such coverage.\n" +
                                "\n" +
                                "Medical Expense Reimbursement\n" +
                                "\n" +
                                "Reimbursement of Medical Expenses. The Company shall reimburse the Executive for the full amount of any medical, dental and optical expenses not covered under any group medical plan from time to time in effect for the benefit of Company employees generally."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Provision of Automobile\n" +
                                "\n" +
                                "Automobile Expenses. The Company shall provide Executive with an automobile for business use in accordance with the automobile policies adopted by the Company from time to time. [The Company shall pay the expenses related to the use and upkeep of the automobile and insurance coverage.]\n" +
                                "\n" +
                                "Automobile Allowance\n" +
                                "\n" +
                                "Automobile Allowance. The Company will provide Executive with [AUTOMOBILE ALLOWANCE] per year for the purchase or lease of a vehicle."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Additional Provisions-Amendment of Benefit Plans\n" +
                                "\n" +
                                "The Company reserves the right to amend, terminate and/or suspend such benefits generally."),
                        Arrays.asList(dummyExtractor)),

        /*Exchange: Type: Performance Agreements: Employment-> Compensation and Benefits -> Expenses*/

                new FragmentTestClassification(
                        new AnalysisFragment("Expenses.\n" +
                                "(a) Authority. Employee is authorized to incur reasonable expenses for the promotion of the business of the Employer including expenses for entertainment, travel and other similar items.\n" +
                                "(b) Reimbursement. Employer shall [promptly] reimburse Employee for reasonable and necessary expenses incurred on behalf of Employer by Employee in connection with the performance of Employee's duties hereunder [in accordance with the reimbursement policies adopted by Employer].\n" +
                                "(c) Substantiation. Employee must provide the Employer [, for review and approval by the [CEO],] adequate records and other documentation as may be required for the substantiation of such expenditures as a business expense.\n" +
                                "(d) Payment. Employer agrees to make prompt payment to Executive following receipt and verification of such reports."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Expenses. Employer shall reimburse Employee for reasonable and necessary expenses incurred on behalf of Employer by Employee in connection with the performance of his duties hereunder [in accordance with the reimbursement policies adopted by Company]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("information as may be required to satisfy the requirements of the Internal Revenue Code for deduction of such expenses by the Company.\n" +
                                "\n" +
                                "All reimbursable expenses shall be appropriately documented in reasonable detail by Employee upon submission of any request for reimbursement, and in a format and manner consistent with the Company's expense reporting policy, as well as applicable federal and state tax record keeping requirements.\n" +
                                "\n" +
                                "Payment\n" +
                                "\n" +
                                "All expenses or other reimbursements under this Agreement shall be made on or prior to the last day of the taxable year following the taxable year in which such expenses were incurred by Executive (provided that if any such reimbursements constitute taxable income to Executive, such reimbursements shall be paid no later than March 15th of the calendar year following the calendar year in which the expenses to be reimbursed were incurred), and no such reimbursement or expenses eligible for reimbursement in any taxable year shall in any way affect the expenses eligible for reimbursement in any other taxable year.\n" +
                                "\n" +
                                "Approval\n" +
                                "\n" +
                                "The determination of whether any specific business expenses are legitimate, reasonable and necessary, and in accordance with the policies of Employer, shall be made in the sole discretion of the Company."),
                        Arrays.asList(dummyExtractor)),

               /* Exchange: Type: Performance Agreements: Provision of Information: Confidentiality Obligations*/

                new FragmentTestClassification(
                        new AnalysisFragment("Protection of Information\n" +
                                "\n" +
                                "(a) Confidentiality. The Receiving Party agrees to hold Confidential Information in confidence in accordance with the terms of this Agreement.\n" +
                                "\n" +
                                "(b) Non-Use. The Receiving Party agrees to use Confidential Information solely in accordance with the terms of this Agreement.\n" +
                                "\n" +
                                "(c) Non-Disclosure. The Receiving Party agrees not to disclose Confidential Information to third parties without the prior written consent of the Disclosing Party.\n" +
                                "\n" +
                                "(d) Copies and Recording. The Receiving Party may not copy or record the Confidential Information."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Copy Restrictions\n" +
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
                                "The receiving party will (at its own expense) take all actions necessary to restrain its employees, agents and representatives from making any unauthorized use or disclosure of any of the Confidential Information."),
                        Arrays.asList(dummyExtractor)),

/* Exchange: Type: Performance Agreements: Provision of Information: Permitted Disclosures*/

                new FragmentTestClassification(
                        new AnalysisFragment("Permitted Disclosure. The Receiving Party shall not disclose the Confidential Information to any third party, except:\n" +
                                "\n" +
                                "(a) to its officers, directors, employees, attorneys, subsidiaries, affiliates [or third party consultants] on a need-to-know basis but only to the extent necessary to carry out that purpose and subject to all requirements of confidentiality set forth in this Agreement, or\n" +
                                "\n" +
                                "(b) pursuant to an express written authorization by the disclosing party."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment(" Notification of Confidentiality and Liability\n" +
                                "\n" +
                                "Permitted Disclosure. The Receiving Party will inform any employee to whom it discloses the Confidential Information of the confidential nature of the information and shall be liable for any breach of the Agreement by such employee."),
                        Arrays.asList(dummyExtractor)),

/* Exchange: Type: Performance Agreements: Provision of Information: Require Disclosures*/

                new FragmentTestClassification(
                        new AnalysisFragment("Required Disclosure.In the event that the Receiving Party becomes compelled by law to disclose any Confidential Information:\n" +
                                "\n" +
                                "Notice of Disclosure. The Receiving Party shall provide the Disclosing Party with prompt written notice so that the Disclosing Party may seek a protective order or other appropriate remedy and/or waive compliance with the provisions of this Agreement.\n" +
                                "\n" +
                                "Cooperation to Seek Protective Order. The Receiving Party shall cooperate with the Disclosing Party to obtain a protective order or other appropriate remedy.\n" +
                                "\n" +
                                "Limited Disclosure. In the event that a protective order or other remedy is not obtained, or the Disclosing Party waives compliance with the provisions of this Agreement, the Receiving Party shall: (i) disclose only the portion of Confidential Information that is legally required to disclose [in the written opinion of its counsel]; and (ii) exercise all reasonable efforts to obtain reliable assurances that confidential treatment will be afforded to Confidential Information."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Required Disclosure—Scope\n" +
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
                                "The receiving party will exercise all reasonable efforts to obtain reliable assurances that confidential treatment will be afforded to Confidential Information."),
                        Arrays.asList(dummyExtractor)),

/*Term: Performance Agreements: Term of Employment */

                new FragmentTestClassification(
                        new AnalysisFragment("Term of Employment.\n" +
                                "\n" +
                                "(a) Initial Term. The term of the Executive's employment under this Agreement shall commence on the Effective Date and continue until [INITIAL TERM END DATE] (the \"Term\"), unless employment is sooner terminated.\n" +
                                "\n" +
                                "(b) Automatic Renewal. Commencing on [INITIAL TERM END DATE] and on each anniversary of that date thereafter, the Term shall be extended for an additional [EXTENSION PERIOD].\n" +
                                "\n" +
                                "(c) Notice Not to Renew. Either party may give notice of the intention not to extend the Term [in writing] at least [90 days] prior to each such anniversary date."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Optional Additional:\n" +
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
                                "Age Limitation. The Executive's employment shall cease and shall not extend past the last day of the month in which the Executive attains [age 70]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Employment Term. The Executive's term of employment under this Agreement (such term of employment, as it may be extended or terminated, is herein referred to as the \"Employment Term\") shall be for a term commencing on the Effective Date and, unless terminated earlier as provided in Section 7 hereof, ending on the third anniversary of the Effective Date (the \"Original Employment Term\"), provided that the Employment Term shall be automatically extended, subject to earlier termination as provided in Section 7 hereof, for successive additional one (1) year periods (the \"Additional Terms\"), unless, at least 30 days prior to the end of the Original Employment Term or the then Additional Term, the Company or the Executive has notified the other in writing that the Employment Term shall terminate at the end of the then current term."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Employment. The Company hereby agrees to continue to employ Executive and Executive hereby agrees to continue to serve the Company, on the terms and conditions set forth herein, for the period commencing on the date hereof and expiring on July 31, 2002 (unless sooner terminated as hereinafter set forth); provided, however, that commencing on August 1, 2002, and each year thereafter, the term of this Agreement shall automatically be renewed for one additional year unless, at least 30 days prior to the expiration of the initial or renewal term, the Company or Executive shall have given written notice to the other party that it does not wish to extend this Agreement. The term of this Agreement, as it may from time to time be extended in accordance with this Paragraph, may be referred to herein as the \"Period of Employment."),
                        Arrays.asList(dummyExtractor)),

/*Term: Performance Agreements: Term of Confidentiality */

                new FragmentTestClassification(
                        new AnalysisFragment("Term of Confidentiality. The term of this Agreement will commence on the Effective Date and continue for a period of [TERM OF OBLIGATION]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Protected Until Public\n" +
                                "\n" +
                                "Term of Confidentiality. The obligations of the receiving party will commence on the Effective Date and continue in effect until such time as all such Confidential Information disclosed becomes publicly known and made generally available through no action or inaction of the Receiving Party.\n" +
                                "\n" +
                                "1.2 Protected Until Consummation of Superseding Transaction\n" +
                                "\n" +
                                "Term of Confidentiality. The obligations of the receiving party will commence on the Effective Date and continue until consummation of an agreement that supersedes this agreement."),
                        Arrays.asList(dummyExtractor)),

/* Statements: Representations: Basic Representations: No Conflicts: Employment Agreement*/

                new FragmentTestClassification(
                        new AnalysisFragment("Example 3—Employment Agreement\n" +
                                "\n" +
                                "No Conflicts. Executive represents and warrants to the Company that:\n" +
                                "(i) the execution, delivery and performance of this Agreement by Executive do not and shall not conflict with, breach, violate or cause a default under any contract, agreement, instrument, order, judgment or decree to which Executive is a party or by which she is bound,\n" +
                                "(ii) Executive is not a party to or bound by any employment agreement, noncompete agreement or confidentiality agreement with any other person or entity, and\n" +
                                "(iii) upon the execution and delivery of this Agreement by the Company, this Agreement shall be the valid and binding obligation of Executive, enforceable in accordance with its terms.\n" +
                                "Executive acknowledges and represents that Executive has consulted with independent legal counsel regarding the rights and obligations under this Agreement and that Executive fully understands the terms and conditions contained of the agreement."),
                        Arrays.asList(dummyExtractor)),

/*Statements:Representations:Acknowledgements: Ownership (Work for Hire)*/

                new FragmentTestClassification(
                        new AnalysisFragment("Ownership. Executive agrees that the Company is the sole and exclusive owner of all intellectual property, including copyrights, trademarks, patents, inventions, work product and know-how, which\n" +
                                "(a) relate to the Company's business, or actual or demonstrably anticipated research or development of the Company; or\n" +
                                "(b) result from any work performed by the Executive for the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("All inventions, modifications, discoveries, designs, developments, improvements, processes, software programs, works of authorship, documentation, formulae, data, techniques, know-how, trade secrets or intellectual property rights or any interest therein (collectively, the \"Developments\") made by the Executive, either alone or in conjunction with others, at any time or at any place during the Executive's employment with the Company, whether or not reduced to writing or practice during such period of employment, which relate to the business in which the Company is engaged or, to the knowledge of the Executive, in which the Company intends to engage, shall be and hereby are the exclusive property of the Company without any further compensation to the Executive. In addition, without limiting the generality of the prior sentence, all Developments which are copyrightable work by the Executive are intended to be work made for hire as defined in Section 101 of the Copyright Act of 1976, and shall be and hereby are the property of the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Ownership\n" +
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
                                "The Executive agrees not to assert any such rights against Company or any third party."),
                        Arrays.asList(dummyExtractor)),

/*Statements:Representations:Acknowledgements:Reasonableness (of Restrictions)*/

                new FragmentTestClassification(
                        new AnalysisFragment("Reasonableness. [Employee] [expressly] agrees that the covenants of this Agreement [are supported by good and adequate consideration, and] are reasonable and necessary [in terms of duration, scope and geographic area] to protect legitimate business interests."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Acknowledgments of Executive. The Executive acknowledges and agrees that during the terms of the Executive�s employment the Executive has acquired special and confidential knowledge regarding the operations of Consumers. Furthermore, although not a term or condition of this Agreement, the Company, the Bank, and the Executive acknowledge and agree that the Executive services have been used and are being used by Consumers in executive, managerial and supervisory capacities throughout the areas in which Consumers does business . The Executive acknowledges and agrees that the noncompete restrictions contained herein are reasonable and fair in scope and necessary to protect the legitimate interests of Consumers."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Acknowledgments of Seller. Seller hereby acknowledges and agrees that:\n" +
                                "(a) this Agreement is necessary for the protection of the legitimate business interests of Buyer and its Affiliates, including but not limited to the protection of the goodwill of the Company which Buyer is acquiring;\n" +
                                "(b) the restrictions contained in this Agreement regarding geographical scope, length of term and types of activities restricted are reasonable;\n" +
                                "(c) the execution and delivery of this Agreement is a mandatory condition precedent to the consummation by Buyer of the transactions provided for in the Merger Agreement;v (d) Seller has no intention of competing with Buyer or any of its Affiliates with respect to the Business within the limitations set forth above;\n" +
                                "(e) as an owner of the Company and through his ownership of the Company, Seller has received, either directly or indirectly, adequate and valuable consideration for entering into this Agreement;\n" +
                                "(f) Buyer's business is national in nature and Buyer contracts with national clients requiring Buyer to do work throughout the United States;\n" +
                                "(g) Seller acknowledges that the Business of the Company is also national in nature; and\n" +
                                "(h) Seller acknowledges that this Agreement is not entered into in consideration in whole or in part for any employment relationship or employment contract which is effective for the period after the Closing with Buyer or any Affiliate of Buyer including Rockford Corporation."),
                        Arrays.asList(dummyExtractor)),

/*Obligations: Perform Actions: Best Efforts*/

                new FragmentTestClassification(
                        new AnalysisFragment("Best Efforts. Each Party will use its best efforts to take all actions and to do all things necessary, proper, or advisable to consummate, make effective, and comply with all of the terms of this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Ownership. [Upon termination of the agreement or] At the disclosing party's request, all Confidential Information in the possession of the Receiving Party shall be returned to the Disclosing Party or destroyed."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("At the Disclosing Party's request [or upon Termination of this agreement] [or if the Proposed Transaction is not consummated], the Receiving Party will promptly [within x days of receipt of such notification]:\n" +
                                "\n" +
                                "(i) Return Property: return to the disclosing party all copies of the Confidential Information in its possession [or in the possession of its Representatives], [whether in written form, electronically stored or otherwise] provided by the disclosing party;\n" +
                                "\n" +
                                "(ii) Destroy Property: destroy all copies [of those portions of any documents] containing any Confidential Information, and\n" +
                                "\n" +
                                "(iii) Certification: if so requested by the disclosing party, deliver to the disclosing party a certificate executed by one of its duly authorized officers confirming compliance with the return or destruction obligation."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Reasonable Standard\n" +
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
                                "The receiving party will (at its own expense) take all actions necessary to restrain its employees, agents and representatives from making any unauthorized use or disclosure of any of the Confidential Information."),
                        Arrays.asList(dummyExtractor)),

        /* Obligations ->Information and Notification->Access to Information*/

                new FragmentTestClassification(
                        new AnalysisFragment("Access to Information. Between the date of this Agreement and the Closing Date, Seller will permit representatives of Buyer (including legal counsel and accountants) to have full access at all reasonable times, and in a manner so as not to interfere with the normal business operations, to all Seller's premises, properties, personnel, books, records, contracts, and documents."),
                        Arrays.asList(dummyExtractor)),



                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Time Period\n" +
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
                                "The Company shall, and shall cause each of its Subsidiaries to, afford to Parent, Merger Sub and their respective Representatives reasonable access during normal business hours, during the period prior to the earlier of Effective Time and the termination of this Agreement in accordance with its terms, to such information, properties and personnel regarding the Company as shall be reasonably necessary for Parent or Merger Sub to fulfill their respective obligations pursuant to this Agreement, to confirm that the representations and warranties of the Company contained herein are true and correct, to confirm that the covenants of the Company contained herein have been performed in all material respects and to enable Parent, subject to applicable Law, to conduct integration planning in connection with, and in preparation for, the Merger, and, during such period, the Company shall, and shall cause each of its Subsidiaries to, also furnish promptly to Parent: (a) a copy of each report, schedule, registration statement and other document filed or received by it during such period pursuant to the requirements of federal or state securities laws and (b) all other information concerning its business, properties and personnel as Parent or Merger Sub may reasonably request (including Tax Returns filed); provided, however, that the foregoing shall not require the Company to disclose any information to the extent such disclosure would contravene applicable Law."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Retention of Records\n" +
                                "\n" +
                                "Seller shall cause the Surviving Company to, until the [number] anniversary of the Closing Date, retain all material books, records and other documents pertaining to the business of the Seller and its Subsidiaries in existence on the Closing Date and to make the same available for inspection and copying by the Representatives or any of the representatives of such Representatives at the expense of the Representatives during the normal business hours of the Surviving Company, upon reasonable request and upon reasonable notice."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Information and Notification->Announcements and Publicity*/

                new FragmentTestClassification(
                        new AnalysisFragment("Announcements The Parties:\n" +
                                "\n" +
                                "(a) shall consult with each other before issuing any press release or otherwise making any public statements with respect to this Agreement;\n" +
                                "\n" +
                                "(b) shall not issue any such press release or make any such public statement without the prior consent of the other party, which consent shall not be unreasonably withheld or delayed;\n" +
                                "\n" +
                                "(c) may, without the prior consent of the other party, issue such press release or make such public statement as may be required by law or a court order."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Service and License Agreements\n" +
                                "\n" +
                                "Publicity Neither Party shall issue any press release or other public announcement related to this Agreement, written or oral, without the prior written consent of the other party[, except as required by law or a court order]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Initial Press Release\n" +
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
                                "The Parties will consult with each other concerning the means by which employees, customers and suppliers and others having dealings with the Parties will be informed of the transactions contemplated hereby."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Consult\n" +
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
                                "Press Releases and Public Announcements. No Party shall issue any press release or make any public announcement relating to the existence or subject matter of this Agreement without the prior written approval of the other Party; provided, however, that any Party may make any public disclosure it believes in good faith is required by applicable law or any listing or trading agreement concerning its publicly-traded securities (in which case the disclosing Party will use its reasonable best efforts to advise the other Party prior to making the disclosure to the extent practicable and permissible under applicable law); and provided, further, that each of the Parties may make internal announcements to their respective employees that are not inconsistent in any material respects with the Parties' prior public disclosures regarding the transactions contemplated by this Agreement. Intellectual Property Purchase Agreement, February 25, 2011."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("(a) Initial Press Release. The Parties shall agree on the form and content of the initial joint press release regarding the transactions contemplated hereby.\n" +
                                "(b) Subsequent Announcements. Thereafter the parties shall consult with each other before issuing any press release or otherwise making any public statement with respect to [the subject matter of the Agreement] [and give the other party the opportunity to review and comment on such press release or other announcement, if practicable].\n" +
                                "(c) Required Disclosure. The Parties may make any public disclosure [it believes in good faith is] required by applicable law or any listing or trading agreement concerning its publicly-traded securities (in which case the disclosing Party will use its [reasonable] best efforts to advise the other Parties prior to making the disclosure).\n" +
                                "3.2 Purchase Agreement—Private Companies or Transactions\n" +
                                "\n" +
                                "(a) Public Announcements. [Prior to the Closing Date,] No party shall make any press release or other announcement with respect to the subject matter of this Agreement without the consent of the other party.\n" +
                                "(b) Required Disclosure. The Parties may make any public disclosure required by applicable securities laws, market regulations or listing agreements.\n" +
                                "3.3 License Agreement—Press Releases; Use of Trademarks\n" +
                                "\n" +
                                "Press Releases; Use of Trademarks. Neither Party shall (a) issue a press release or make any other public statement that references this Agreement, or (b) use the other Party's names or trademarks for publicity or advertising purposes, except with the prior written consent of the other Party."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Restrictive Covenants->Assignements of Inventions*/

                new FragmentTestClassification(
                        new AnalysisFragment("Inventions. Employee agrees to\n" +
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
                                "(b) Assignment. Employee hereby assigns to the Company Employee's entire right, title and interest in any inventions, improvements or discoveries relating to the subject matter of employment and made or conceived individually or jointly, within [TIME PERIOD] after termination of employment."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1. Scope of Included Inventions\n" +
                                "\n" +
                                "Included Inventions. The term \"Inventions\" means all original works of authorship, developments, concepts, improvements or trade secrets, whether or not patentable or registrable under copyright or similar laws, that Employee may individually or jointly conceive or develop or reduce to practice, or cause to be conceived or developed or reduced to practice:\n" +
                                "(i) during the period of employment with the Company,\n" +
                                "(ii) or for a period of [NUMBER OF MONTHS] following the termination of employment for any reason,\n" +
                                "(iii) developed using the equipment, supplies, facilities or Confidential Information of the Company,\n" +
                                "(iv) result from or are suggested by work performed by me for the Company, or\n" +
                                "(v) relate to the business, or to the actual or demonstrably anticipated research or development of the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment(" I understand that the provisions of this Agreement requiring assignment of Inventions to the Company do not apply to any invention that I developed entirely on my own time without using the Company's equipment, supplies, facilities, or trade secret information, except for those inventions that either: (i) relate at the time of conception or reduction to practice of the invention to the Company's business, or actual or demonstrably anticipated research or development of the Company; or (ii) result from any work performed by me for the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.3. Waiver of Rights\n" +
                                "\n" +
                                "Employee also hereby forever waives and agrees never to assert any rights Employee may have in or with respect to any Inventions even after termination of employment with the Company.\n" +
                                "\n" +
                                "2.4. Commercialization\n" +
                                "\n" +
                                "Employee acknowledges and agrees that the decision whether or not to commercialize or market any invention developed by Employee solely or jointly with others is within the Company's sole discretion and for the Company's sole benefit and that no royalty will be due to Employee as a result of the Company's efforts to commercialize or market any such invention.\n" +
                                "\n" +
                                "2.4 Acknowledgement of Rights in Future Inventions\n" +
                                "\n" +
                                "Acknowledgement. Employee recognizes that all inventions, improvements or discoveries relating to the subject matter of employment and made or conceived individually or jointly, within [TIME PERIOD] after termination of employment may have been conceived in significant part while employed by the Company."),
                        Arrays.asList(dummyExtractor)),


        /* Obligations ->Restrictive Covenants->Inventions Retained and Licensed*/
                new FragmentTestClassification(
                        new AnalysisFragment("Inventions Retained and Licensed.\n" +
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
                                "(b) License of Retained Inventions. If in the course of employment with the Company, employee incorporates into a Company product, process or machine any Retained Inventions [or in any invention which employee has an interest], the the Employee hereby grants and the Company shall have a nonexclusive, royalty-free, irrevocable, perpetual, worldwide license to make, have made, modify, use and sell such Prior Invention as part of or in connection with such product, process or machine."),
                        Arrays.asList(dummyExtractor)),


       /* Obligations ->Restrictive Covenants->Non-Disclosure*/

                new FragmentTestClassification(
                        new AnalysisFragment("Non-Disclosure Obligations.\n" +
                                "\n" +
                                "(a) Confidentiality. Each party agrees to hold the disclosing party's Confidential Information in [strict] confidence [in accordance with the terms of this Agreement].\n" +
                                "\n" +
                                "(b) Non-Disclosure. Each party agrees not to disclose any Confidential Information to third parties (including, without limitation, any clients, affiliates, independent contractors and consultants) without the prior, written consent of the disclosing party except as expressly permitted in this Agreement.\n" +
                                "\n" +
                                "(c) Non-Use. Each party agrees not to use any Confidential Information for any purpose except for the Disclosing Purpose without the prior written consent of the disclosing party."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Best Efforts Standard\n" +
                                "\n" +
                                "Use of Confidential Information. The receiving party will keep the Confidential Information in confidence and, except as expressly provided in this Agreement, will not disclose it to anyone without the disclosing party's prior written consent. The receiving party will not use, or permit others to use Confidential Information for any purpose other than for the purpose of evaluating possible business arrangements. The receiving party will use its best efforts to avoid disclosure, dissemination or unauthorized use of Confidential Information.\n" +
                                "\n" +
                                "1.2 Protection of Confidential Information (Employee Agreement)\n" +
                                "\n" +
                                "Employee agrees that:\n" +
                                "(a) Employee shall not use in any manner, directly or indirectly, any Confidential Information except in promoting the Company's business, and as necessary in performing the duties of his/her employment with the Company;\n" +
                                "(b) Employee will not use any Confidential Information for his/her own benefit or for the benefit of any person or entity other than the Company, and will not permit or allow any Confidential Information to be used in competition with the Company.\n" +
                                "(c) During his/her employment with the Company and at all times thereafter, Employee shall take all reasonable steps to prevent any unauthorized disclosure or use of any and all Confidential Information. Employee further agrees to notify the Company immediately in the event that he/she becomes aware of any unauthorized use or disclosure of Confidential Information."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Restrictive Covenants->Non-Disclosure*/

                new FragmentTestClassification(
                        new AnalysisFragment("Non-Competition.\n" +
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
                                "\"Restricted Territory\" means [RESTRICTED TERRITORY]."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("Described Generally\n" +
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
                                "A \"Competitor\" shall mean (i) Bed Bath & Beyond, Inc., Home Place Inc.,J.C. Penney, Federated Department Stores, Mays, Target, Sears and K-Mart (and any successor or successors thereto); (ii) any home textiles or housewares store, specialty store or other retailer if either 25 million Dollars or 40% or more of its annual gross sales revenues (in either case, based on the most recent quarterly or annual financial statements available) are derived from the sale of home textiles, housewares or other goods or merchandise of the types sold in the Company's (or any Subsidiary's) stores; (iii) any corporation or other entity whether independent or owned, funded or controlled by any other entity, engaged or organized for the purpose of engaging, in whole or in part, in the sale of home textiles, housewares or other goods or merchandise of the types sold in the Company's (or any Subsidiary's) stores; (iv) any business that provides buying office services to any business or group of businesses referred to above, or (v) any business (in the U.S. or any country in which the Company or any Subsidiary operates a store or stores) which is in material competition with the Company or any Subsidiary or division thereof and in which Executive's functions would be substantially similar to Executive's functions with the Company.\n" +
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
                                "\"Restricted Territory\" means any [country][state][county] in which the company conducts business or markets its products or services."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Extension of Restricted Period\n" +
                                "\n" +
                                "If, during any period within the Non-Competition Period, the Executive is not in compliance with the terms of this section, the Company shall be entitled to, among other remedies, compliance by the Executive with the terms of this section for an additional period equal to the period of such noncompliance. For purposes of this Agreement, the term \"Non-Competition Period\" shall also include this additional period."),
                        Arrays.asList(dummyExtractor)),

   /* Obligations ->Restrictive Covenants->Non-Solicitation*/

                new FragmentTestClassification(
                        new AnalysisFragment("Non-Solicitation. During the term of this Agreement and for [NON-SOLICITATION PERIOD] after any termination of this Agreement, [Party] will not, without the prior written consent of the Company, either directly or indirectly[, on [Party]'s own behalf or in the service or on behalf of others], solicit or attempt to solicit, divert or hire away any person employed by the Company or any customer of the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Employees and Consultants\n" +
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
                                "(iii) solicit or induce any Customer to terminate or reduce its relationship with the Companies."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Restrictive Covenants->Non-Disparagement*/

                new FragmentTestClassification(
                        new AnalysisFragment("Non-Disparagement. [During the Term and thereafter,] [Employee] agrees to take no action which is intended, or would reasonably be expected, to harm the Company or its or their reputation or which would reasonably be expected to lead to unwanted or unfavorable publicity to the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("You [employee] agree that you will not disparage or comment negatively about the Company, its officers and management, and/or current or former employees. "),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Disparaging remarks, comments or statements are those that impugn the character, honesty, integrity, morality or business acumen or abilities in connection with any aspect of the operation of business of the covered individual or entity."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Mutual Non-Disparagement.\n" +
                                "\n" +
                                "(a) Each Investor agrees that, during the Standstill Period, neither it nor any of its Affiliates or Associates will, and it will cause each of its Affiliates and Associates not to, directly or indirectly, in any capacity or manner, make, express, transmit speak, write, verbalize or otherwise communicate in any way (or cause, further, assist, solicit, encourage, support or participate in any of the foregoing), any remark, comment, message, information, declaration, communication or other statement of any kind, whether verbal, in writing, electronically transferred or otherwise, that might reasonably be construed to be derogatory or critical of, or negative toward, the Company or any of its directors, officers, Affiliates, subsidiaries, employees, agents or representatives (collectively, the \"Company Representatives\"), or that reveals, discloses, incorporates, is based upon, discusses, includes or otherwise involves any confidential or proprietary information of the Company or its subsidiaries or Affiliates, or to malign, harm, disparage, defame or damage the reputation or good name of the Company, its business or any of the Company Representatives.\n" +
                                "\n" +
                                "(b) The Company hereby agrees that, during the Standstill Period, neither it nor any of its Affiliates will, and it will cause each of its Affiliates not to, directly or indirectly, in any capacity or manner, make, express, transmit, speak, write, verbalize or otherwise communicate in any way (or cause, further, assist, solicit, encourage, support or participate in any of the foregoing), any remark, comment, message, information, declaration, communication or other statement of any kind, whether verbal, in writing, electronically transferred or otherwise, that might reasonably be construed to be derogatory or critical of, or negative toward, any Investor or any of its agents or representatives (collectively, the \"Investor Representatives\"), or that reveals, discloses, incorporates, is based upon, discusses, includes or otherwise involves any confidential or proprietary information of any Investor or its subsidiaries or Affiliates, or to malign, harm, disparage, defame or damage the reputation or good name of any Investor or Investor Representatives.\n" +
                                "\n" +
                                "(c)Notwithstanding the foregoing, nothing in this Section 7 or elsewhere in this Agreement shall prohibit any Party from making any statement or disclosure required under the federal securities laws or other applicable laws; provided, that such Party must provide written notice to the other Parties at least two business days prior to making any such statement or disclosure required by under the federal securities laws or other applicable laws that would otherwise be prohibited the provisions of this Section 7, and reasonably consider any comments of such other Parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Nothing herein shall prevent [Employee] from making any truthful statement in connection with any legal proceeding or investigation by the Company or any governmental authority.\n" +
                                "\n" +
                                "2.3 Handling References\n" +
                                "\n" +
                                "Covenant of Non-disparagement. Each of the Company and the Consultant covenants never to disparage or speak ill of the other party or any of their products, services, affiliates, subsidiaries, officers, directors, employees or shareholders, and will take reasonable steps to prevent and will not knowingly permit any of their respective employees or agents to, disparage or speak ill of such persons. Notwithstanding the foregoing, the Consultant expressly assumes all risk associated with listing any past or present Company employee, consultant or agent, or the Company itself, as a reference in connection with the Consultants pursuit of future employment or engagement, and the Consultant agrees that any such person whom the Consultant lists as a reference shall in response to any request for a reference concerning the Consultant be permitted to provide complete, truthful and accurate information concerning the Consultant without creating any liability for himself or herself, the Company, any affiliated entity, or any employee, consultant, agent or representative of any of the foregoing."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Termination->Termination of Employement*/

                new FragmentTestClassification(
                        new AnalysisFragment("Termination of Employment. The Executive's employment may be terminated only as follows:\n" +
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
                                "(e) Change of Location. Any relocation of the premises at which Executive works to a location more than [RELOCATION DISTANCE TRIGGERING GOOD REASON] from such location, without Executive's consent; or"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Grounds for Termination:\n" +
                                "\n" +
                                "Termination Upon Death\n" +
                                "Termination Upon Disability\n" +
                                "Termination Upon Retirement\n" +
                                "Termination by the Company For Cause\n" +
                                "Termination by the Company Without Cause\n" +
                                "Termination by Change in Control\n" +
                                "Termination by Executive For Good Reason\n" +
                                "Termination by Executive Without Good Reason"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Cause\n" +
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
                                "Board Determination. The Board determines in its sole discretion that a Change in Control has occurred, whether or not any event described above has occurred or is contemplated."),
                        Arrays.asList(dummyExtractor)),

        /* Rights ->Termination->Specific Events of Termination*/

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Upon Death. This Agreement shall terminate automatically upon the death of the Executive."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Upon Disability. This Agreement shall terminate automatically upon the Disability of the Executive.\n" +
                                "\n" +
                                "Disability. Disability means the Executive is unable to perform the duties set forth in this Agreement for a period of [twelve consecutive weeks], or [90] cumulative business days in [any 12-month] period, as a result of physical or mental illness or loss of legal capacity."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Automatic Termination\n" +
                                "\n" +
                                "Termination Upon Disability. This Agreement shall terminate automatically upon the Disability of the Executive.\n" +
                                "\n" +
                                "1.2 Optional Termination\n" +
                                "\n" +
                                "Termination Upon Disability. The Company may terminate this agreement upon the Disability of the Executive."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination on Account of Death or Disability; Non-Qualifying Termination.\n" +
                                "(i) In the event of any termination of Executive's employment other than a Qualifying Termination (for example, on account of death or Disability), the Executive shall not be entitled to any additional payments or benefits from the Company under this Agreement, other than payments or benefits with respect to the Accrued Rights.\n" +
                                "(ii) For purposes of this Agreement, the Executive shall be deemed to have a \"Disability\" in the event of the Executive's absence for a period of 180 consecutive business days as a result of incapacity due to a physical or mental condition, illness or injury that is determined to be total and permanent by a physician mutually acceptable to the Company and the Executive or the Executive's legal representative (such acceptance not to be unreasonably withheld) after such physician has completed an examination of the Executive. The Executive agrees to make himself available for such examination upon the reasonable request of the Company, and the Company shall be responsible for the cost of such examination."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination for Disability. The Company may terminate the Executive's employment because of the Disability of the Executive and thereafter the Company shall pay to the Executive (or his successors) his unpaid Salary through the Employment Termination Date and any Stock Options which have not vested as of the Employment Termination Date shall be terminated.\n" +
                                "\n" +
                                "\"Disability\" of the Executive means that, as a result of the Executive's incapacity due to physical or mental illness, the Executive shall have been absent from his duties on a full time basis for thirty (30) days in any three (3) month period. If the Executive is prevented from performing his duties because of Disability, upon request by the Company, the Executive shall submit to an examination by a physician selected by the Company, at the Company's expense, and the Executive shall also authorize his personal physician to disclose to the selected physician all of the Executive's medical records."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Upon Retirement. The Executive may voluntarily terminate this Agreement at any time by reason of Retirement.\n" +
                                "\n" +
                                "Retirement. Retirement is the cessation by Executive of all full-time employment of any kind."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Mandatory Retirement\n" +
                                "\n" +
                                "Retirement. Retirement shall mean termination of Executive's employment:\n" +
                                "(a) at age 65 or in accordance with any retirement policy established with Executive's consent; or\n" +
                                "(b) at such later time as the Company's Board of Directors may determine."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Retirement. For purposes of this Agreement, \"Retirement\" shall mean the Executive's voluntary termination of employment, during the term of this Agreement, pursuant to late, normal or early retirement under a pension plan sponsored by the Company, as defined in such plan, but only if such retirement occurs prior to a termination by the Company without Cause (and not in anticipation of a termination for Cause)."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("Compensation Upon Termination for Death, Disability, Retirement. If the Executive's employment is terminated by reason of Death, Disability or Retirement prior to any other Termination (other than in anticipation of a termination for Cause by the Company), the Executive will receive:\n" +
                                "(a) the sum of (i) the Executive's accrued but unpaid base salary through the date of Termination, (ii) the Pro Rata Bonus, and (iii) any compensation previously deferred (excluding any qualified plan deferrals) by the Executive under or into benefit plans of the Company and an amount representing the Executive's accrued but unused vacation days, if any, in each case, in full satisfaction of the Executive's rights thereto; and\n" +
                                "(b) the Accrued Benefits (with an offset for any amounts paid under Section 5(a)(iii), above)."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("As used in this Article IV, \"Termination by Reason of Retirement\" means a termination by the Company or Executive of Executive's employment based on Executive's having reached age 65 or such other age as shall have been fixed in any arrangement established with Executive's consent with respect to Executive; and in all instances subject to applicable law."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination For Cause. The Company shall have the right to terminate Executive's employment under this Agreement at any time for Cause, which termination shall be effective immediately. Termination for \"Cause\" shall include termination for:\n" +
                                "(i) Breach. Material breach of this Agreement by Executive;\n" +
                                "(ii) Nonperformance. Intentional nonperformance or misperformance of such duties, or refusal to abide by or comply with the reasonable directives of his superior officers, or the Corporation's policies and procedures;\n" +
                                "(iii) Gross Negligence. Executive's gross negligence in the performance of his material duties under this Agreement;\n" +
                                "(iv) Dishonesty. Executive's willful dishonesty, fraud or misconduct with respect to the business or affairs of the Corporation, that in the reasonable judgment of the President and/or the Board of Directors materially and adversely affects the Corporation;\n" +
                                "(v) Conviction. Executive's conviction of, or a plea of nolo contendere to, a felony or other crime involving moral turpitude; or\n" +
                                "(vi) Disloyalty. The commission of any act in direct or indirect competition with or materially detrimental to the best interests of Corporation that is in breach of Executive s fiduciary duties of care, loyalty and good faith to Corporation.\n" +
                                "Cause will not, however, include any actions or circumstances constituting Cause under (i) or (ii) above if Executive cures such actions or circumstances within [30 days] of receipt of written notice from Corporation setting forth the actions or circumstances constituting Cause. In the event Executive's employment under this Agreement is terminated for Cause, Executive shall thereafter have no right to receive compensation or other benefits under this Agreement.\n" +
                                "Termination Without Cause. The Company may [, upon a [majority] vote of the Board of Directors,] terminate the Executive's employment under this Agreement without Cause at any time upon [90 days] prior [written] notice to the Executive.\n+" +
                                "(vii) Absenteeism. Executive's excessive absenteeism (other than by reason of disability described in this Agreement) without a reasonable justification;\\n\" +\n" +
                                "(viii) Substance Abuse. Executive's abuse of alcohol or drugs (legal or illegal) that, in the Company's reasonable judgment, substantially impairs Employee's ability to perform his duties under this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("For Cause. The Company may terminate this Agreement and Employee's employment hereunder at any time for Cause (as defined below) upon written notice given to Employee. As used herein, \"Cause\" means (i) any act of Employee which would constitute a felony (other than a driving offense) or fraud; (ii) a continuing material breach by Employee in performing the duties described in this Agreement (other than by reason of physical or mental disability or impairment) which is not cured by Employee within fifteen (15) days after the Company gives Employee written notice specifying the details of the breach; or (iii) gross neglect, gross malfeasance, willful neglect, willful misconduct, or dishonesty in performance of Employee's duties hereunder."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Without Cause. The Company may terminate this Agreement and Employee's employment hereunder at any time and for any reason or no reason and without Cause, upon written notice to Employee, in which event the Company shall have no further obligation under this Agreement except as set forth in (the Severance Payments section).\n" +
                                "2.3 Example 3\n" +
                                "\n" +
                                "Termination Without Cause by the Company. This Agreement may be terminated without cause by the Company upon 10 days notice. The last day of the Executive's employment shall be referred to as the Termination Date. If this Agreement is terminated under this section 4.2, then the Executive shall be entitled to the following monies and benefits: (i) Base Salary and Benefits through the Termination Date; (ii) Base Salary for a period of 1 full year following the Termination Date, payable in quarterly installments with the first installment due on the Termination Date and the remaining 3 installments due 90 days, 180 days, and 270 days thereafter; (iii) Post-Termination Benefits; and (iv) the pro-rated portion any amounts earned under any Bonus Plan in effect prior to the Termination Date, to be determined after the close of the fiscal year in which the Termination Date occurred. The Company shall not be required to pay any monies or benefits under this section 4.2 unless and until the Executive shall have executed and delivered to the Company a Company-prepared release (\"Release\") of any and all claims or potential claims, against the Company, its directors, officers, employees, shareholders and subsidiaries, arising from or related to any act or omission occurring prior to the Termination Date. This Release shall also include a release of potential claims by the Company against the Executive, arising from or related to any act or omission of the Executive occurring prior to the Termination Date, except for any act or omission by the Executive involving intentional wrongdoing, fraud, or breach of fiduciary duty. Any and all stock options granted to the Executive, which have fully vested prior to the Termination Date, shall expire as set forth in the respective plan documents that granted the options."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination For Good Cause by the Company. Upon written notice to the Executive, the Company may immediately terminate this Agreement for \"Good Cause.\" Good Cause shall include: (i) the Executive's conviction of, or plea of nolo contendere or guilty to, any crime involving dishonesty, fraud or moral turpitude; (ii) the Executive's gross negligence with respect to the performance of the duties of his Position; (iii) the Executive's willful or serious misconduct, or willful or serious violation of Company policies; (iv) the Executive's breach of trust or breach of fiduciary duty in the performance of the duties or responsibilities of his Position; (v) the Executive's willful failure or refusal to comply with a reasonable directive of the Board; or (vi) the Executive's breach of any term or provision of this Agreement. The last day of the Executive's employment shall be referred to as the Termination Date. The Executive shall be entitled to Base Salary and Benefits earned and unpaid through to the Termination Date, and no other money, pay and benefits shall be owed or paid. Any and all stock options granted to the Executive, which have fully vested prior to the Termination Date, shall expire as set forth in the respective plan documents that granted the options."),
                        Arrays.asList(dummyExtractor)),

       /* Rights ->Termination->Specific Events of Termination->Change of Control*/

                new FragmentTestClassification(
                        new AnalysisFragment("Termination upon Change of Control. If the Executive's employment is terminated by the Company without Cause or by the Executive for Good Reason in connection with or within [one year/two years] after Change in Control, the Executive shall be entitled to Severance Benefits as stated in the Termination Benefits section.\n" +
                                "\n" +
                                "Change in Control. For purposes of this Agreement, unless the Board determines otherwise, a Change of Control of the Company shall be deemed to have occurred at such time as:\n" +
                                "(a) Change in Ownership: any person (as the term is used in Sections 13(d) and 14(d) of the Securities Exchange Act of 1934, as amended (the Exchange Act)) is or becomes the beneficial owner (as defined in Rule 13d-3 under the Exchange Act), directly or indirectly, of voting securities of the Company representing more than 50% of the Company s outstanding voting securities or rights to acquire such securities except for any voting securities issued or purchased under any employee benefit plan of the Company or its subsidiaries; or\n" +
                                "(b) Sale: any sale, lease, exchange or other transfer (in one transaction or a series of transactions) of all or substantially all of the assets of the Company; or\n" +
                                "(c) Liquidation: a plan of liquidation of the Company or an agreement for the sale or liquidation of the Company is approved and completed; or\n" +
                                "(d) Board Determination: the Board determines in its sole discretion that a Change in Control has occurred, whether or not any event described above has occurred or is contemplated.\n"+
                                "(e) Board Membership: individuals who, as of the date hereof, constitute the entire Board of Directors of the Company (the \"Incumbent Directors\") cease for any reason to constitute at least a majority of the Board of Directors, provided that any individual becoming a director subsequent to the date hereof whose election or nomination for election was approved by a vote of at least a majority of the then Incumbent Directors shall be, for purposes of this provision, considered as though such individual were an Incumbent Director;\n" +
                                "(f) Consolidation: any consolidation or merger of the Company (including, without limitation, a triangular merger) where the shareholders of the Company, immediately prior to the consolidation or merger, would not, immediately after the consolidation or merger, beneficially own, directly or indirectly, shares representing in the aggregate more than fifty percent (50%) of the combined voting power of all the outstanding securities of the corporation issuing cash or securities in the consolidation or merger (or of its ultimate parent corporation, if any);"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Following a Change of Control and Compensation Reduction. In the event that a Change in Control, as hereinafter defined, of the Company shall occur at any time during the Term or Renewal Term, and within 12 months of the occurrence of such Change in Control event the Company terminates the Executive without Cause or the Executive shall terminate the Executive's employment under this Agreement, then, in any such event such termination shall be deemed to be a termination by the Company other than for Cause and the Executive shall be entitled to such compensation and benefits as set forth in Section 6(d) of this Agreement, which shall be paid promptly (but not later than 30 days) following the termination of Executive's employment.\n" +
                                "\n" +
                                "For purposes of this Agreement, a \"Change in Control\" of the Company shall mean any of the following:\n" +
                                "(i) a sale of all or substantially all of the assets of the Company;\n" +
                                "(ii) the date there shall have been a change in a majority of the Board of Directors of the Company during a consecutive twelve-month period, unless the nomination for election by the Company's shareholders of each new director was approved.by the vote of two-thirds of the directors then still in office who were in office at the beginning of the twelve-month period;\n" +
                                "(iii) the date that any person or entity, entities or group of persons (other than the Executive) both (A) is or becomes the Beneficial Owner (as defined in Rule 13d-3 under the Securities Exchange Act of 1934), directly or indirectly, of securities of the Company.representing more than thirty percent (30%) or more of the combined voting power of the Company's then outstanding securities, and (B) has voting control of the Company;\n" +
                                "(iv) consummation of a merger or consolidation of the Company with any corporation or other entity, other than a merger or consolidation which would result in the voting securities of the Company outstanding immediately prior thereto continuing to represent (either by remaining outstanding or by being converted into voting securities of the surviving entity) more than fifty percent (50%) of the combined voting power of the voting securities of the Company or such surviving entity outstanding immediately after such merger or consolidation;\n" +
                                "(v) a change in ownership of the Company through a transaction or series of transactions, such that any person or entity is or becomes the Beneficial Owner (as defined in Rule 13d-3 under the Exchange Act), directly or indirectly, of securities of the Company representing fifty percent (50%) or more of securities of the combined voting power of the Company's then outstanding securities; provided that, for such purposes, any acquisition by the Company, in exchange for the Company's securities, shall be disregarded; or\n" +
                                "(vi) the Board (or the stockholders if stockholder approval is required by applicable law or under the terms of any relevant agreement) shall approve a plan of complete liquidation of the Company.\n" +
                                "provided, however, that a Change of Control shall expressly not include (A) any consolidation or merger effected exclusively to change the domicile of the Company, (B) any transaction or series of transactions principally for bona fide equity financing purposes, or (C) the Transaction."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Following a Change of Control.\n" +
                                "1. In the event that a \"Change in Control,\" as hereinafter defined, shall occur at any time during the Term or Renewal Term hereof, the Executive shall have the right to terminate the Executive's employment under this Agreement upon thirty (30) days written notice given at any time within one (1) year after the occurrence of such event.\n" +
                                "\n" +
                                "2. For purposes of this Agreement, a \"Change in Control\" of the Company shall mean a change in control:\n" +
                                "a)the occurrence of any of the following:\n" +
                                "i) any person, group or organization, other than the Executive, is or becomes the beneficial owner, directly or indirectly, of securities of the Company representing fifty percent (50%) or more of the combined voting power of the Company's outstanding securities then having the right to vote at elections of directors; or\n" +
                                "ii) the individuals who at the Effective Date of this Agreement constitute the Board of Directors cease for any reason to constitute a majority thereof unless the election, or nomination for election, of each new director was approved by the Executive; or\n" +
                                "iii) the business or over fifty percent (50%) of the business revenues of the Company for which the Executive's services are principally performed is/ are sold or otherwise disposed of by the Company (including the stock of a subsidiary of the Company).\n" +
                                "\n" +
                                "Anything herein to the contrary notwithstanding, this Section 6G2 will not apply where the Executive gives the Executive's explicit written waiver stating that for purposes of this Section 6G2 a Change in Control shall not be deemed to have occurred. The Executive's participation in any negotiations or other matters in relation to a Change in Control shall in no way constitute such a waiver which can only be given by an explicit written waiver as provided in the preceding sentence."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Termination->Specific Events of Termination->Termination by Executive*/

                new FragmentTestClassification(
                        new AnalysisFragment("Termination by the Executive for Good Reason. The Executive may terminate his employment under this Agreement for Good Reason, in which case the Executive shall be entitled to Severance Benefits as stated in the Termination Benefits section.\n" +
                                "For purposes of this Agreement, \"Good Reason\" shall mean the occurrence of any of the following events without the Executive's written consent:\n" +
                                "(i) a material diminution of the Executive's title, authority, status, duties or responsibilities;\n" +
                                "(ii) any reduction in the Executive's Base Salary;\n" +
                                "(iii) a material breach by the Company of this Agreement; or\n" +
                                "(iv) a change in the location of the Company's principal office to a location more than [RELOCATION DISTANCE] outside of the [metropolitan area of the Executive's home city]. \n" +
                                "\n" +
                                "Termination by the Executive Without Good Reason. The Executive may terminate his employment under this Agreement at any time for any reason or no reason by giving the Company [NOTICE PERIOD] prior written notice of the termination. Following any such notice, the Company may reduce or remove any and all of Executive s duties, positions and titles with the Company, and any such reduction or removal shall not constitute Good Reason."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Notice and Cure. For purposes of this Agreement, Executive may resign his employment from the Company for \"Good Reason\" by providing notice to the Company [setting forth with reasonable detail the nature of the Good Reason] within [30 days] from the [the Executive's knowledge of the] occurrence of a Good Reason event. Executive's resignation for Good Reason shall only be effective if the Company has not cured or remedied the Good Reason event within [30 days] after its receipt of Executive's written notice."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("Termination by the Executive for Good Reason. The Executive may terminate his employment and this Agreement for Good Reason by written notice to the Company, and in that event, the Company shall pay Executive promptly (but not later than 30 days) following the termination of his employment a lump sum equal to one year of Severance Pay. \"Good Reason,\" as used in this Agreement, shall mean, without limitation, (A) any material diminution in the Executive's authority, duties and responsibilities, (B) any reduction in the Executive's Base Salary, (C) any material reduction in the total value of the Executive's fringe benefit compensation, (D) a material breach by the Company of this Agreement, or (E) the Company's failure to provide and maintain Directors and Officers' Liability Insurance in agreed amounts. Before terminating this Agreement for Good Reason, the Executive must give the Company a prior written notice indicating his intent to terminate for Good Reason if corrective action is not taken, and stating the reasons why he believes there are grounds to terminate for Good Reason; after receipt of this notice, the Company shall have 15 days to cure the grounds for Good Reason. In the event of a termination for Good Reason, the Executive will be entitled to payment of all Accrued Obligations, which will be paid promptly (but not later than 30 days) following the date on which the Executive's employment is terminated. The Executive shall also be entitled to payment of any Final Bonus, which shall be determined as provided by Section 2(b) of this Agreement. Any such Final Bonus payment shall be made promptly but not later than as provided by Section 2(b)."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("For Good Reason. Employee shall have the right to terminate this Agreement and Employee's employment hereunder at any time for \"Good Reason\" (as defined below) upon written notice given to the Company. As used herein, \"Good Reason\" means (A) Employee having attained the age of 65; or (B) the occurrence (without the consent of Employee) of any of the following:\n" +
                                "(i) The removal of Employee from the position of CFO;\n" +
                                "(ii) The assignment to Employee of any duties materially inconsistent with Employee's position (including status, offices, titles and reporting requirements), authority, duties or responsibility as contemplated by Section 2, or any other material diminution in such position, authority, duties or responsibilities;\n" +
                                "(iii) A change in the geographic location of Employee's principal place of employment to a location more than forty-five (45) miles beyond (A) the present location of the Company's principal executive offices; and (B) Employee's then principal place of residence; or\n" +
                                "(iv) Any failure by the Company to comply with the material provisions of this Agreement, including the provisions of Section 3,\n" +
                                "provided that (A) Employee gives notice of any condition listed in clause (i) through (iv) above to the Company within 90 days after the initial existence of the condition, which notice gives the Company 30 days within which to cure the condition; and (B) the condition is not cured within such 30-day period. Any such termination of this Agreement shall be effective upon the expiration of such 30-day period or at such earlier date agreed to by the parties in writing.\n" +
                                "\n" +
                                "Without Good Reason. Employee shall have the right to terminate this Agreement and Employee's employment hereunder without Good Reason at any time upon not less than three (3) months' prior written notice to the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Benefits\n" +
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
                                "\"Severance Compensation\" means an amount equal to [SEVERANCE PAYMENT AMOUNT]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Specification of Severance and Termination Benefits\n" +
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
                                "[(iii) Options. Any stock options granted to the Executive by the Company shall continue to vest only through the date on which the Executive's employment terminates."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Termination->Termination Benefits*/


                new FragmentTestClassification(
                        new AnalysisFragment("Termination Benefits\n" +
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
                                "\"Severance Compensation\" means an amount equal to [SEVERANCE PAYMENT AMOUNT]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Specification of Severance and Termination Benefits\n" +
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
                                "[(iii) Options. Any stock options granted to the Executive by the Company shall continue to vest only through the date on which the Executive's employment terminates."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Severance Benefits\n" +
                                "\n" +
                                "Severance. In the event of Termination Without Cause of the Executive's employment pursuant to this Section, the Company shall continue to pay to the Executive the Executive's then current Annual Salary throughout such [90-day] notice period and shall pay the Executive (a) [six months] Annual Salary at the Executive's then current salary in equal monthly installments over the six month period following the Termination Date, [provided that such payments shall cease if the Executive becomes employed by a company which is in the Business during such six month period,] and (b) all vacation accrued as of the Termination Date.\n" +
                                "\n" +
                                "Termination Payments\n" +
                                "\n" +
                                "Termination With Cause by Company or Without Constructive Discharge by Executive. If Company terminates Executive�s employment and the Employment Period with Cause, or if Executive terminates Executive�s employment and the Employment Period other than as a result of a Constructive Discharge, Company shall be obligated to pay Executive (i) any Base Salary amounts that have accrued but have not been paid as of the Termination Date; and (ii) subject to Section 7.14, the unpaid Performance Bonus, if any, with respect to the calendar year preceding the calendar year in which the Termination Date occurs (such Performance Bonus, if any, to be determined in the manner it would have been determined, and payable at the time it would have been payable, under Section 3.2 had there been no termination of the Employment Period."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Termination->Termination Procedures*/

                new FragmentTestClassification(
                        new AnalysisFragment(""),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Notice Requirements. Any Termination by the Company for Cause, or by Executive for Good Reason, shall be communicated by Notice of Termination to the other party hereto given in accordance with the Notice section of this Agreement.\n" +
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
                                "Release. Notwithstanding anything in the Severance Benefits section to the contrary, in no event shall the Executive be entitled to receive any amounts, rights or benefits under the Severance Benefits section unless the Executive executes a release of claims against the Company in form and substance as set forth in [the attached Release Form]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Conditions on Severance\n" +
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
                                "Any party giving notice of a dispute shall pursue the resolution of such dispute with reasonable diligence. Notwithstanding the dependency of any such dispute, the Company will continue to pay the Executive his full compensation in effect when the notice giving rise to the dispute was given (including, but not limited to, base salary) and continue the Executive as a participant in all compensation, employee benefit and insurance plans, programs and arrangements in which the Executive was participating when the notice giving rise to the dispute was given, until the dispute is finally resolved."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Notice of Termination. Except for termination as specified in Section 3(a), any termination of the Executive�s employment by the Company or any such termination by the Executive shall be communicated by written Notice of Termination to the other party hereto. For purposes of this Agreement, a �Notice of Termination� shall mean a notice which shall indicate the specific termination provision in this Agreement relied upon.\n" +
                                "\n" +
                                "Date of Termination. �Date of Termination� shall mean: (i) if the Executive�s employment is terminated by his death, the date of his death; (ii) if the Executive�s employment is terminated on account of disability under Section 3(b) or by the Company for Cause under Section 3(c) or by the Company without Cause under Section 3(d), the date on which a Notice of Termination is given; (iii) if the Executive�s employment is terminated by the Executive under Section 3(e) without Good Reason, 30 days after the date on which a Notice of Termination is given, and (iv) if the Executive�s employment is terminated by the Executive under Section 3(e) with Good Reason, the date on which a Notice of Termination is given after the end of the Cure Period. Notwithstanding the foregoing, in the event that the Executive gives a Notice of Termination to the Company, the Company may unilaterally accelerate the Date of Termination and such acceleration shall not result in a termination by the Company for purposes of this Agreement.\n" +
                                "\n" +
                                "Resignation on Termination. On the Date of Termination, the Executive shall resign from all positions with the Company and its subsidiaries. In addition, if the Executive is then serving as a member of the Board or the Board of Directors of a subsidiary, the Executive shall tender his resignation from such directorship(s) on the Date of Termination."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Claims->Employment Indemnification*/

                new FragmentTestClassification(
                        new AnalysisFragment("Indemnification The Company shall indemnify the Executive, to the maximum extent permitted by applicable law [and by its certificate of incorporation], against all costs, charges and expenses incurred or sustained by the Executive in connection with any action, suit or proceeding to which the Executive may be made a party by reason of being an officer, director or employee of the Company or of any subsidiary or affiliate of the Company or any other corporation for which the Executive serves [in good faith] as an officer, director, or employee at the Company's request."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Insurance\n" +
                                "\n" +
                                "Example: The Executive shall be entitled to the full protection of any insurance policies which the Company may elect to maintain generally for the benefit of its officers.\n" +
                                "\n" +
                                "Coverage Level\n" +
                                "\n" +
                                "Example: The Company agrees to maintain Directors and Officers Liability Insurance for the benefit of Executive having coverage and policy limits no less favorable to directors and officers than those in effect at the Effective Date.\n" +
                                "\n" +
                                "Notice\n" +
                                "\n" +
                                "Example: The Executive agrees promptly to notify the Company of any actual or threatened claim arising out of or as a result of the Executive's employment with the Company."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Indemnification. Employer shall indemnify Employee against all claims arising out of Employee's actions or omissions occurring during Employee's employment with Employer to the fullest extent provided (A) by Employer's Certificate of Incorporation and/or Bylaws, and (B) under the Nevada or Florida General Corporation Law, as each may be amended from time to time. Employer may maintain a Directors & Officers liability insurance policy (\"D&O Coverage\") covering Employee to the extent Employer provides such coverage for its other executive officers. Employer agrees to make such policy available to Employee within five (5) days, upon request."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Indemnification; Insurance. During your employment and thereafter, the Company shall, on the same basis as is provided for the Company's continuing officers and directors from time to time, indemnify and hold you harmless against any costs or expenses (including attorneys' fees), judgments, fines, losses, claims, damages or liabilities incurred in connection with any claim, action, suit, proceeding or investigation, whether civil, criminal, administrative or investigative, by reason of the fact that you are or were a director, officer, employee or agent of the Company or any Subsidiary, whether asserted or claimed prior to,at or after the date of your termination of employment, to the fullest extent permitted under applicable law and on a basis no less favorable than in existence under the Company's Bylaws and Certificate of Incorporation in effect as of the Effective Date. During your employment and thereafter, Company shall provide to you coverage under a policy of directors' and officers' liability insurance that provides you with coverage on the same basis as is provided for the Company's continuing officers and directors from time to time."),
                        Arrays.asList(dummyExtractor)),

/* Remedies ->Enforcement->Remedies Cumulative */

                new FragmentTestClassification(
                        new AnalysisFragment("Remedies Cumulative. All rights and remedies provided in this Agreement are cumulative and not exclusive of any other rights or remedies that may be available to the parties, whether provided by law, equity, statute, in any other agreement between the parties or otherwise."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Rights and Remedies Cumulative. The enumeration of Lender's rights and remedies set forth in this Loan Agreement is not intended to be exhaustive. The exercise by Lender of any right or remedy under this Loan Agreement does not preclude the exercise of any other rights or remedies, all of which are cumulative and are in addition to any other right or remedy given under this Loan Agreement or under any other agreement between Lender and any Borrower or Guarantor or which may now or subsequently exist in law, in equity, by statute or otherwise."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("No Waiver; Cumulative Remedies. A failure to exercise or a delay in exercising, on the part of the Lender, any right, remedy, power or privilege hereunder or under the other Loan Documents shall not operate as a waiver thereof; nor shall any single or partial exercise of any right, remedy, power or privilege hereunder preclude any other or further exercise thereof or the exercise of any other right, remedy, power or privilege. The rights, remedies, powers and privileges herein provided are cumulative and not exclusive of any rights, remedies, powers and privileges provided by law."),
                        Arrays.asList(dummyExtractor)),

/* Remedies ->Enforcement->Specific Performance */

                new FragmentTestClassification(
                        new AnalysisFragment("Specific Performance. Any breach of this Agreement may result in irreparable damage to [Party2] for which [Party 2] will not have an adequate remedy at law. Accordingly, in addition to any other remedies and damages available, [Party1] acknowledges and agrees that [Party2] may immediately seek enforcement of this Agreement by means of specific performance or injunction, without any requirement to post a bond or other security."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Executive acknowledges that in the event of breach or threatened breach by the Executive of the terms of Section 1 hereof, the Company could suffer significant and irreparable harm that could not be satisfactorily compensated in monetary terms, and that the remedies at law available to the Company may otherwise be inadequate and the Company shall be entitled, in addition to any other remedies to which it may be entitled to under law or in equity, to specific performance of this Agreement by the Executive including the immediate ex parte issuance, without bond, of a temporary restraining order enjoining the Executive from any such violation or threatened violation of Section 1 hereof and to exercise such remedies cumulatively or in conjunction with all other rights and remedies provided by law and not otherwise limited by this Agreement. The Executive hereby acknowledges and agrees that the Company shall not be required to post bond as a condition to obtaining or exercising any such remedies, and the Executive hereby waives any such requirement or condition"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("[Party 1] acknowledges that (i) the restrictions contained in this Agreement are reasonable in scope and are necessary to protect the [Party 2]'s legitimate interests in protecting its business, and (ii) any violation of the restrictions contained in this Agreement will cause significant and irreparable harm to the [Party 2] for which the [Party 2] has no adequate remedy at law"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("[Party 2] shall also be entitled to obtain injunctive relief, including but not limited to a temporary restraining order, a temporary or preliminary injunction or a permanent injunction, to enforce the provisions of this Agreement, as well as an equitable accounting of and constructive trust for all profits or other benefits arising out of or related to any such violation, all of which shall constitute rights and remedies to which the [Party 2] may be entitled.\"\n" +
                                "\n" +
                                "Without Bond or Proof of Damages\n" +
                                "\n" +
                                "Example\n" +
                                "[Party 2] shall be entitled to such relief without the necessity of proving actual damages or posting a bond, in addition to, and not in lieu of, any other rights and remedies available to [Party 2] under law or in equity have the right and remedy to have the provisions of this agreement enforced by injunctive relief in any court of competent jurisdiction, it being agreed that any breach or threatened breach of this agreement would cause irreparable injury to [Party 2] and that damages would not provide an adequate remedy to [Party 2]"),
                        Arrays.asList(dummyExtractor)),

/* Remedies ->Damages->Mitigation (Employement) */

                new FragmentTestClassification(
                        new AnalysisFragment("Mitigation\n" +
                                "(a) Other Employment. The Employee shall have no duty to mitigate the payment of any amount or benefit provided for in this agreement by seeking other employment or in any other manner.\n" +
                                "(b) No Offset or Reduction. No such payment or benefit shall be eliminated, offset or reduced by the amount of any compensation provided to the Employee in any subsequent employment, any retirement benefits, or any other source."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1. Employer Claims\n" +
                                "\n" +
                                "The Employer can include a clause reserving their right to pursue any claim they may have. While a mitigation clause gives no affirmative duty for the Employee to actively limit the benefits the Employer owes them, this clause explicitly lays out that any further liability the Employee might have remains.\n" +
                                "\n" +
                                "Example: The Employer's obligation to make the payments provided for in this Agreement and otherwise to perform its obligations hereunder shall not be affected by any set-off, counterclaim, recoupment, defense or other claim, right or action which the Employer may have against the Employee or others, provided that nothing herein shall preclude the Employer from separately pursuing recovery from the Employee based on any such claim.\n" +
                                "\n" +
                                "1.2. Payments Guaranteed by Law\n" +
                                "\n" +
                                "The jurisdiction where the severance agreement is signed may have laws that require an Employer to pay an Employee certain benefits under specific circumstances. An Employer can protect against double paying an Employee by reducing the amount they pay by any legally required payments.\n" +
                                "\n" +
                                "Example: The amount payable under this agreement shall be reduced by the amount of any severance, termination, or notice pay (or any other similar amounts) required by law to be paid to the Employee by the Employer and by any salary or other amounts paid to the Employee during any notice period that the Employer is required by law to provide."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Entire Agreement */

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement [(together with the documents [referred to in this Agreement] [listed on Exhibit A])] constitute[s] the entire agreement between the parties with respect to its subject matter and supersedes all prior agreements, representations and understandings of the parties, written or oral."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Transaction Documents, together with the exhibits and schedules thereto, contain the entire understanding of the parties with respect to the subject matter hereof and supersede all prior agreements and understandings, oral or written, with respect to such matters, which the parties acknowledge have been merged into such documents, exhibits and schedules. "),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement, together with the exhibits and schedules hereto, constitutes the entire understanding and agreement of the parties hereto with respect to the subject matter hereof and supersedes all prior and contemporaneous agreements or understandings, inducements or conditions, express or implied, written or oral, between the parties with respect hereto other than the Confidentiality Agreement. The express terms hereof control and supersede any course of performance or usage of the trade inconsistent with any of the terms hereof."),
                        Arrays.asList(dummyExtractor)),

        /* Adjudication ->Agreement Scope->Further Assurances */

                new FragmentTestClassification(
                        new AnalysisFragment("Further Assurances The parties shall execute such further documents and do any and all such further things as may be necessary to implement and carry out the intent of this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Each party shall do and perform, or cause to be done and performed, all such further acts and things, and shall execute and deliver all such other agreements, certificates, instruments and documents, as the other party may reasonably request in order to carry out the intent and accomplish the purposes of this Agreement and the consummation of the transactions contemplated hereby."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The parties shall upon reasonable request of the other, execute such documents as may be necessary or appropriate to carry out the intent of this Agreement.\n" +
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
                                "In case at any time after the Effective Time any further action is reasonably necessary to carry out the purposes of this Agreement or the transactions contemplated by this Agreement, the proper officers of the Company, Parent and the Surviving Corporation shall take any such reasonably necessary action."),
                        Arrays.asList(dummyExtractor)),

        /* Adjudication ->Agreement Scope->Counterparts */


                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement may be executed in counterparts, each of which shall be deemed to be an original, but all of which, taken together, shall constitute one and the same agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Example 1—effective upon signature and delivery\n" +
                                "\n" +
                                "This Agreement may be executed in counterparts (each of which shall be deemed to be an original but all of which taken together shall constitute one and the same agreement) and shall become effective when one or more counterparts have been signed by each of the parties and delivered to the other party.\n" +
                                "\n" +
                                "Example 2—facsimile\n" +
                                "\n" +
                                "This Agreement may be executed in two or more counterparts, and by facsimile, all of which shall be considered one and the same agreement and shall become effective when one or more counterparts have been signed by each of the parties and delivered to the other party, it being understood that all parties need not sign the same counterpart.\n" +
                                "\n" +
                                "Example 3—electronic execution\n" +
                                "\n" +
                                "This Agreement may be executed in multiple counterparts and transmitted by facsimile or by electronic mail in \"portable document format\" (\"PDF\") form, or by any other electronic means intended to preserve the original graphic and pictorial appearance of a party's a signature. Each such counterpart and facsimile or PDF signature shall constitute an original and all of which together shall constitute one and the same original."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Amendments */

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement may be amended only by a written instrument signed by the Parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Bilateral Amendment\n" +
                                "\n" +
                                "No amendment to this agreement will be effective unless it is in writing and signed by both parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Authorization Person\n" +
                                "\n" +
                                "The parties may not amend this agreement, except by written agreement executed by [TITLE OR POSITION OF AUTHORIZED INDIVIDUAL] of each party."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Schedules and Exhibits */

                new FragmentTestClassification(
                        new AnalysisFragment("Schedules and Exhibits The exhibits and schedules referred to in this Agreement will be deemed to be a part of this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Recitals, Schedules and Exhibits to this Agreement are incorporated herein and, by this reference, made a part hereof as if fully set forth herein."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The recitals, Annexes, Exhibits and Schedules identified in this Agreement are incorporated herein by reference and made a part hereof. Each disclosure in a Schedule referred to in this Agreement shall be deemed to qualify all representations and warranties of the party making such disclosure, notwithstanding the absence of a specific cross-reference, except to the extent that its applicability to a particular representation, warranty, agreement or condition is not reasonably apparent from the disclosure thereof."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Schedules to this Agreement are hereby incorporated in and made a part of this Agreement as if set forth in full herein and are an integral part of this Agreement. Matters set forth in the Schedules are not necessarily limited to matters required by this Agreement to be reflected in the schedules. Such additional matters are set forth for informational purposes, and the Schedules do not necessarily include other matters of a similar nature. Nothing in this Agreement or in the Schedules constitutes an admission that any information disclosed, set forth or incorporated by reference in the Schedules or this Agreement is material, constitutes a Material Adverse Effect or is otherwise required by the terms of this Agreement to be so disclosed, set forth or incorporated by reference. All information and disclosures contained in the Schedules are made as of the date of this Agreement and their accuracy is confirmed only as of such date and not at any time thereafter. Matters disclosed pursuant to any section of this Agreement are deemed to be disclosed with respect to all sections of this Agreement and the Schedules to the extent this Agreement requires such disclosure; provided that it is clear by appropriate cross-references that a given disclosure is applicable to such other Schedules, sections and paragraphs. Any capitalized terms used in any Schedule or Exhibit but not otherwise defined therein shall be defined as set forth in this Agreement."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Effective Date */

                new FragmentTestClassification(
                        new AnalysisFragment("Effective Date The effective date of this Employment Agreement (the \"Effective Date\") shall be [DATE]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Controlled by Opening Statement\n" +
                                "\n" +
                                "This agreement is effective as of the date shown at the top of the first page, even if any signatures are made after that date.\n" +
                                "\n" +
                                "1.2 Effective Upon Last Signature\n" +
                                "\n" +
                                "This Credit Agreement shall be binding and deemed effective when executed by Borrower, Administrative Agent, and each Lender whose signature is provided for on the signature pages hereof (the \"Effective Date\")."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Notices */

                new FragmentTestClassification(
                        new AnalysisFragment("Notices:\n" +
                                "(a) Form of Notice. All notices, requests, claims, demands and other communications between the parties shall be in writing.\n" +
                                "\n" +
                                "(b) Method of Notice. All notices shall be given (i) by delivery in person (ii) by a nationally recognized next day courier service, (iii) by first class, registered or certified mail, postage prepaid, (iv) by facsimile [or (v) by electronic mail] to the address of the party specified in this Agreement or such other address as either party may specify in writing.\n" +
                                "\n" +
                                "(c) Receipt of Notice. All notices shall be effective upon (i) receipt by the party to which notice is given, or (ii) on the [fifth (5th)] day following mailing, whichever occurs first."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Form—Writing and Language\n" +
                                "\n" +
                                "All notices, requests, claims, demands and other communications shall be in writing [in the English language] [and shall be signed by a person duly authorized to provide such notice].\n" +
                                "\n" +
                                "1.2 How—Method and Address\n" +
                                "\n" +
                                "Notices permitted or required to be given hereunder shall be deemed sufficient if given by (a) registered or certified mail, postage prepaid, return receipt requested, (b) private courier service, or (c) facsimile addressed to the respective addresses of the parties as first above written or at such other addresses as the respective parties may designate by like notice from time to time.\n" +
                                "\n" +
                                "1.3 When—Time of Receipt\n" +
                                "\n" +
                                "Such notice shall be deemed to have been given upon receipt."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("All notices \"shall be deemed to have been duly given (a) when delivered in person, (b) upon confirmation of receipt when transmitted by facsimile transmission or by electronic mail (but, in the case of electronic mail, only if followed by transmittal by national overnight courier or hand for delivery on the next Business Day), (c) upon receipt after dispatch by registered or certified mail, postage prepaid or (d) on the next Business Day if transmitted by national overnight courier (with confirmation of delivery)."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Change of Address\n" +
                                "\n" +
                                "Any party to this Agreement may notify any other party of any changes to the address or any of the other details specified in this paragraph; provided, however, that such notification shall only be effective on the date specified in such notice or five Business Days after the notice is given, whichever is later.\n" +
                                "\n" +
                                "Refusal of Delivery\n" +
                                "\n" +
                                "Rejection or other refusal to accept or the inability to deliver because of changed address of which no notice was given shall be deemed to be receipt of the notice as of the date of such rejection, refusal or inability to deliver. Verizon Merger"),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Binding Effect */

                new FragmentTestClassification(
                        new AnalysisFragment("Binding Effect. This Agreement shall be binding upon and inure to the benefit of the parties and their respective heirs, successors and permitted assigns."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("\n" +
                                "No Third Party Beneficiary. The Agreement [does/will] not confer any rights or remedies upon any person other the parties and their respective heirs, executors, administrators, personal representatives, beneficiaries, successors and permitted assigns."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Third Parties */

                new FragmentTestClassification(
                        new AnalysisFragment("Third Party Beneficiaries. This agreement does not and is not intended to confer any rights or remedies upon any person other than the parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Example 1\n" +
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
                                "Third Party Beneficiaries. This agreement confers rights and remedies upon [named beneficiaries] as set forth in section [___]. No person, other than the parties and such [named beneficiaries], has any rights or remedies under the agreement. The parties may not amend or terminate this agreement without the prior written consent of the [named beneficiaries]."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Successors and Assigns */

                new FragmentTestClassification(
                        new AnalysisFragment("Successors and Assigns. This Agreement shall be binding upon and shall inure to the benefit of the parties and their [permitted] successors and assigns."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Example 1\n" +
                                "\n" +
                                "Successors and Assigns. This agreement benefits and binds the parties and their [permitted] successors and assigns.\n" +
                                "\n" +
                                "Example 2—Application to Natural Persons\n" +
                                "\n" +
                                "Successors and Assigns. This Agreement shall be binding upon and shall inure to the benefit of the parties and their heirs, executors, administrators, [successors] and [permitted] assigns.\n" +
                                "\n" +
                                "Note: The term \"successor\" can probably be omitted when applied to natural person, because individuals do not have successors."),
                        Arrays.asList(dummyExtractor)),

                /* Adjudication ->Enforcement->Governing Law */

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement shall be governed, construed, and enforced in accordance with the laws of the State of [GOVERNING LAW STATE], without regard to its conflict of laws rules."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement shall be governed by and construed in accordance with the laws of the State of [GOVERNING LAW STATE], without regard to its conflict of laws rules.\n"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This agreement shall be governed by, and construed in accordance with, the [internal OR domestic OR mandatory] laws of the state of:\n" +
                                "(a) regardless of the laws that might otherwise govern under applicable principles of conflicts of laws thereof\n" +
                                "OR\n" +
                                "(b) applicable to agreements made and to be performed solely therein, without giving effect to principles of conflicts of law\n" +
                                "OR\n" +
                                "(c) without giving effect to any choice or conflict of Law provision or rule (whether of the State of [________] of any other jurisdiction) that would cause the application of the Laws of any jurisdiction other than the State of "),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement shall in all respects be governed by, and construed and interpreted in accordance with, the Laws of the State of New York without giving effect to any conflicts of law principles of such state that might refer the governance, construction or interpretation of this Agreement to the Laws of another jurisdiction."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Enforcement->Jurisdiction */

                new FragmentTestClassification(
                        new AnalysisFragment("Jurisdiction. The parties submit all their disputes arising out of or in connection with this Agreement to the exclusive jurisdiction of the Courts of [ ]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Venue.\n" +
                                "Courts. Any action at law, suit in equity or judicial proceeding arising directly, indirectly, or otherwise in connection with, out of, related to or from this Agreement shall be litigated only in the courts of the State of [ ]. Consent to Jurisdiction. The Parties consent to the jurisdiction of such courts over the subject matter set forth in Section 8. Waiver of Right to Transfer. The Executive waives any right the Executive may have to transfer or change the venue of any litigation brought against the Executive by the Company."),
                        Arrays.asList(dummyExtractor)),

        /* Adjudication ->Interpretation->Severability */

                new FragmentTestClassification(
                        new AnalysisFragment("Severability. The invalidity or unenforceability of any provisions of this Agreement shall not affect the validity or enforceability of any other provision of this Agreement, which shall remain in full force and effect."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Severability—Renegotiation\n" +
                                "\n" +
                                "If any term or other provision of this Agreement is determined to be invalid, illegal or incapable of being enforced by any rule or law, or public policy, all other conditions and provisions of this Agreement shall nevertheless remain in full force and effect so long as the economic or legal substance of the transactions contemplated hereby is not affected in any manner materially adverse to any party. Upon such determination that any term or other provision is invalid, illegal or incapable of being enforced, the parties hereto shall negotiate in good faith to modify this Agreement so as to effect the original intent of the parties as closely as possible in an acceptable manner to the end that transactions contemplated hereby are fulfilled to the extent possible."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.2 Reformation (\"Blue Pencil\")\n" +
                                "\n" +
                                "Each term and provision of this agreement shall be valid and enforceable to the fullest extent permitted by law and any invalid, illegal or unenforceable term or provision shall be deemed replaced by a term or provision that is valid and enforceable and that comes closest to expressing the intention of the invalid, illegal or unenforceable term or provision."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("If the final judgment of such court or arbitrator declares that any term or provision hereof is invalid, void or unenforceable, the parties agree to reduce the scope, duration, area or applicability of the term or provision, to delete specific words or phrases, or to replace any invalid, void or unenforceable term or provision with a term or provision that is valid and enforceable and that comes closest to expressing the original intention of the invalid or unenforceable term or provision."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Interpretation->Waiver */

                new FragmentTestClassification(
                        new AnalysisFragment("A party's failure to exercise or delay in exercising any right, power or privilege under this Agreement shall not operate as a waiver; nor shall any single or partial exercise of any right, power or privilege preclude any other or further exercise"),
                        Arrays.asList(waiverExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The waiver by the Employer of a breach of any provision of this Agreement by Employee will not operate or be construed as a waiver of any other subsequent breach by Employee."),
                        Arrays.asList(waiverExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("No General Waivers. The failure of any party at any time to require performance of any provision or to resort to any remedy provided under this Agreement shall in no way affect the right of that party to require performance or to resort to a remedy at any time thereafter, nor shall the waiver by any party of a breach be deemed to be a waiver of any subsequent breach. A waiver shall not be effective unless it is in writing and signed by the party against whom the waiver is being enforced."),
                        Arrays.asList(waiverExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("No course of dealing, nor any failure to exercise, nor any delay in exercising any right, power or privilege hereunder shall operate as a waiver thereof."),
                        Arrays.asList(waiverExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("A party's waiver of any breach, default or right under this Agreement must be in writing and signed by the party against whom the waiver is being enforced. Any such waiver shall not be deemed a waiver of any subsequent breach, default or right, whether of the same nature or otherwise."),
                        Arrays.asList(waiverExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The waiver by either party of a breach of any provision of this Agreement must be in writing and shall not operate or be construed as a waiver of any other breach."),
                        Arrays.asList(waiverExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The rights and remedies under this Agreement are cumulative and not exclusive of any rights, remedies, powers and privileges that may otherwise be available to the parties."),
                        Arrays.asList(waiverExtractor)),

        };

        for (FragmentTestClassification classification:employmentAgreement) {

            classification.testIt(all);
        }


    }

    /**
     * This method only tests the general clauses, with no consideration to agreement type
     */

    @Test
    public void testGeneralContract(){


        FeatureExtractorInterface warrantyExtractor= new WarrantyExtractor();
        FeatureExtractorInterface waiverExtractor= new WaiverExtractor();
        FeatureExtractorInterface subsidiaryExtractor= new SubsidiaryExtractor();
        FeatureExtractorInterface dummyExtractor= new DummyExtractor();


        List<FeatureExtractorInterface> all =  Arrays.asList(

                waiverExtractor, warrantyExtractor, subsidiaryExtractor, dummyExtractor

        );

        FragmentTestClassification[] employmentAgreement = new FragmentTestClassification[]{

/* Bargain clauses: Type Performance Agreements: Employment*/
        /*Bargain clauses: Type Performance Agreements: Engagement Consulting*/

        /*Bargain clauses: Type Performance Agreements:Financial and Advisory Services*/

        /*Bargain clauses: Type Performance Agreements: Business Services*/

        /*Bargain clauses: Type Performance Agreements:Severance*/

                new FragmentTestClassification(
                        new AnalysisFragment("Severance. In exchange for the release and covenant not to sue, the Company will pay Executive the Severance Benefits, subject to the terms and conditions of this Agreement."),
                        Arrays.asList(dummyExtractor)),

         /*Bargain clauses: Type Performance Agreements:Provision of Information*/

                new FragmentTestClassification(
                        new AnalysisFragment("Provision of Information. The Parties agree to exchange Confidential Information under the terms and conditions of this Agreement.\n" +
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
                                "(d) Independently Developed. Information that is independently developed by the Receiving Party without the use of Confidential Information or breach of this Agreement."),

                        Arrays.asList(dummyExtractor)),

               /* Exchange: Type: Performance Agreements: Provision of Information: Confidentiality Obligations*/

                new FragmentTestClassification(
                        new AnalysisFragment("Protection of Information\n" +
                                "\n" +
                                "(a) Confidentiality. The Receiving Party agrees to hold Confidential Information in confidence in accordance with the terms of this Agreement.\n" +
                                "\n" +
                                "(b) Non-Use. The Receiving Party agrees to use Confidential Information solely in accordance with the terms of this Agreement.\n" +
                                "\n" +
                                "(c) Non-Disclosure. The Receiving Party agrees not to disclose Confidential Information to third parties without the prior written consent of the Disclosing Party.\n" +
                                "\n" +
                                "(d) Copies and Recording. The Receiving Party may not copy or record the Confidential Information."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Copy Restrictions\n" +
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
                                "The receiving party will (at its own expense) take all actions necessary to restrain its employees, agents and representatives from making any unauthorized use or disclosure of any of the Confidential Information."),
                        Arrays.asList(dummyExtractor)),

/* Exchange: Type: Performance Agreements: Provision of Information: Permitted Disclosures*/

                new FragmentTestClassification(
                        new AnalysisFragment("Permitted Disclosure. The Receiving Party shall not disclose the Confidential Information to any third party, except:\n" +
                                "\n" +
                                "(a) to its officers, directors, employees, attorneys, subsidiaries, affiliates [or third party consultants] on a need-to-know basis but only to the extent necessary to carry out that purpose and subject to all requirements of confidentiality set forth in this Agreement, or\n" +
                                "\n" +
                                "(b) pursuant to an express written authorization by the disclosing party."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment(" Notification of Confidentiality and Liability\n" +
                                "\n" +
                                "Permitted Disclosure. The Receiving Party will inform any employee to whom it discloses the Confidential Information of the confidential nature of the information and shall be liable for any breach of the Agreement by such employee."),
                        Arrays.asList(dummyExtractor)),

/* Exchange: Type: Performance Agreements: Provision of Information: Require Disclosures*/

                new FragmentTestClassification(
                        new AnalysisFragment("Required Disclosure.In the event that the Receiving Party becomes compelled by law to disclose any Confidential Information:\n" +
                                "\n" +
                                "Notice of Disclosure. The Receiving Party shall provide the Disclosing Party with prompt written notice so that the Disclosing Party may seek a protective order or other appropriate remedy and/or waive compliance with the provisions of this Agreement.\n" +
                                "\n" +
                                "Cooperation to Seek Protective Order. The Receiving Party shall cooperate with the Disclosing Party to obtain a protective order or other appropriate remedy.\n" +
                                "\n" +
                                "Limited Disclosure. In the event that a protective order or other remedy is not obtained, or the Disclosing Party waives compliance with the provisions of this Agreement, the Receiving Party shall: (i) disclose only the portion of Confidential Information that is legally required to disclose [in the written opinion of its counsel]; and (ii) exercise all reasonable efforts to obtain reliable assurances that confidential treatment will be afforded to Confidential Information."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Required Disclosure—Scope\n" +
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
                                "The receiving party will exercise all reasonable efforts to obtain reliable assurances that confidential treatment will be afforded to Confidential Information."),
                        Arrays.asList(dummyExtractor)),


/*Term: Performance Agreements: Term of Confidentiality */

                new FragmentTestClassification(
                        new AnalysisFragment("Term of Confidentiality. The term of this Agreement will commence on the Effective Date and continue for a period of [TERM OF OBLIGATION]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Protected Until Public\n" +
                                "\n" +
                                "Term of Confidentiality. The obligations of the receiving party will commence on the Effective Date and continue in effect until such time as all such Confidential Information disclosed becomes publicly known and made generally available through no action or inaction of the Receiving Party.\n" +
                                "\n" +
                                "1.2 Protected Until Consummation of Superseding Transaction\n" +
                                "\n" +
                                "Term of Confidentiality. The obligations of the receiving party will commence on the Effective Date and continue until consummation of an agreement that supersedes this agreement."),
                        Arrays.asList(dummyExtractor)),


/*Statements:Representations:Acknowledgements:Reasonableness (of Restrictions)*/


                new FragmentTestClassification(
                        new AnalysisFragment("Acknowledgments of Seller. Seller hereby acknowledges and agrees that:\n" +
                                "(a) this Agreement is necessary for the protection of the legitimate business interests of Buyer and its Affiliates, including but not limited to the protection of the goodwill of the Company which Buyer is acquiring;\n" +
                                "(b) the restrictions contained in this Agreement regarding geographical scope, length of term and types of activities restricted are reasonable;\n" +
                                "(c) the execution and delivery of this Agreement is a mandatory condition precedent to the consummation by Buyer of the transactions provided for in the Merger Agreement;v (d) Seller has no intention of competing with Buyer or any of its Affiliates with respect to the Business within the limitations set forth above;\n" +
                                "(e) as an owner of the Company and through his ownership of the Company, Seller has received, either directly or indirectly, adequate and valuable consideration for entering into this Agreement;\n" +
                                "(f) Buyer's business is national in nature and Buyer contracts with national clients requiring Buyer to do work throughout the United States;\n" +
                                "(g) Seller acknowledges that the Business of the Company is also national in nature; and\n" +
                                "(h) Seller acknowledges that this Agreement is not entered into in consideration in whole or in part for any employment relationship or employment contract which is effective for the period after the Closing with Buyer or any Affiliate of Buyer including Rockford Corporation."),
                        Arrays.asList(dummyExtractor)),

/*Obligations: Perform Actions: Best Efforts*/

                new FragmentTestClassification(
                        new AnalysisFragment("Best Efforts. Each Party will use its best efforts to take all actions and to do all things necessary, proper, or advisable to consummate, make effective, and comply with all of the terms of this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Ownership. [Upon termination of the agreement or] At the disclosing party's request, all Confidential Information in the possession of the Receiving Party shall be returned to the Disclosing Party or destroyed."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("At the Disclosing Party's request [or upon Termination of this agreement] [or if the Proposed Transaction is not consummated], the Receiving Party will promptly [within x days of receipt of such notification]:\n" +
                                "\n" +
                                "(i) Return Property: return to the disclosing party all copies of the Confidential Information in its possession [or in the possession of its Representatives], [whether in written form, electronically stored or otherwise] provided by the disclosing party;\n" +
                                "\n" +
                                "(ii) Destroy Property: destroy all copies [of those portions of any documents] containing any Confidential Information, and\n" +
                                "\n" +
                                "(iii) Certification: if so requested by the disclosing party, deliver to the disclosing party a certificate executed by one of its duly authorized officers confirming compliance with the return or destruction obligation."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Reasonable Standard\n" +
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
                                "The receiving party will (at its own expense) take all actions necessary to restrain its employees, agents and representatives from making any unauthorized use or disclosure of any of the Confidential Information."),
                        Arrays.asList(dummyExtractor)),

        /* Obligations ->Information and Notification->Access to Information*/

                new FragmentTestClassification(
                        new AnalysisFragment("Access to Information. Between the date of this Agreement and the Closing Date, Seller will permit representatives of Buyer (including legal counsel and accountants) to have full access at all reasonable times, and in a manner so as not to interfere with the normal business operations, to all Seller's premises, properties, personnel, books, records, contracts, and documents."),
                        Arrays.asList(dummyExtractor)),



                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Time Period\n" +
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
                                "The Company shall, and shall cause each of its Subsidiaries to, afford to Parent, Merger Sub and their respective Representatives reasonable access during normal business hours, during the period prior to the earlier of Effective Time and the termination of this Agreement in accordance with its terms, to such information, properties and personnel regarding the Company as shall be reasonably necessary for Parent or Merger Sub to fulfill their respective obligations pursuant to this Agreement, to confirm that the representations and warranties of the Company contained herein are true and correct, to confirm that the covenants of the Company contained herein have been performed in all material respects and to enable Parent, subject to applicable Law, to conduct integration planning in connection with, and in preparation for, the Merger, and, during such period, the Company shall, and shall cause each of its Subsidiaries to, also furnish promptly to Parent: (a) a copy of each report, schedule, registration statement and other document filed or received by it during such period pursuant to the requirements of federal or state securities laws and (b) all other information concerning its business, properties and personnel as Parent or Merger Sub may reasonably request (including Tax Returns filed); provided, however, that the foregoing shall not require the Company to disclose any information to the extent such disclosure would contravene applicable Law."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Retention of Records\n" +
                                "\n" +
                                "Seller shall cause the Surviving Company to, until the [number] anniversary of the Closing Date, retain all material books, records and other documents pertaining to the business of the Seller and its Subsidiaries in existence on the Closing Date and to make the same available for inspection and copying by the Representatives or any of the representatives of such Representatives at the expense of the Representatives during the normal business hours of the Surviving Company, upon reasonable request and upon reasonable notice."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Information and Notification->Announcements and Publicity*/

                new FragmentTestClassification(
                        new AnalysisFragment("Announcements The Parties:\n" +
                                "\n" +
                                "(a) shall consult with each other before issuing any press release or otherwise making any public statements with respect to this Agreement;\n" +
                                "\n" +
                                "(b) shall not issue any such press release or make any such public statement without the prior consent of the other party, which consent shall not be unreasonably withheld or delayed;\n" +
                                "\n" +
                                "(c) may, without the prior consent of the other party, issue such press release or make such public statement as may be required by law or a court order."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Service and License Agreements\n" +
                                "\n" +
                                "Publicity Neither Party shall issue any press release or other public announcement related to this Agreement, written or oral, without the prior written consent of the other party[, except as required by law or a court order]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Initial Press Release\n" +
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
                                "The Parties will consult with each other concerning the means by which employees, customers and suppliers and others having dealings with the Parties will be informed of the transactions contemplated hereby."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Consult\n" +
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
                                "Press Releases and Public Announcements. No Party shall issue any press release or make any public announcement relating to the existence or subject matter of this Agreement without the prior written approval of the other Party; provided, however, that any Party may make any public disclosure it believes in good faith is required by applicable law or any listing or trading agreement concerning its publicly-traded securities (in which case the disclosing Party will use its reasonable best efforts to advise the other Party prior to making the disclosure to the extent practicable and permissible under applicable law); and provided, further, that each of the Parties may make internal announcements to their respective employees that are not inconsistent in any material respects with the Parties' prior public disclosures regarding the transactions contemplated by this Agreement. Intellectual Property Purchase Agreement, February 25, 2011."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("(a) Initial Press Release. The Parties shall agree on the form and content of the initial joint press release regarding the transactions contemplated hereby.\n" +
                                "(b) Subsequent Announcements. Thereafter the parties shall consult with each other before issuing any press release or otherwise making any public statement with respect to [the subject matter of the Agreement] [and give the other party the opportunity to review and comment on such press release or other announcement, if practicable].\n" +
                                "(c) Required Disclosure. The Parties may make any public disclosure [it believes in good faith is] required by applicable law or any listing or trading agreement concerning its publicly-traded securities (in which case the disclosing Party will use its [reasonable] best efforts to advise the other Parties prior to making the disclosure).\n" +
                                "3.2 Purchase Agreement—Private Companies or Transactions\n" +
                                "\n" +
                                "(a) Public Announcements. [Prior to the Closing Date,] No party shall make any press release or other announcement with respect to the subject matter of this Agreement without the consent of the other party.\n" +
                                "(b) Required Disclosure. The Parties may make any public disclosure required by applicable securities laws, market regulations or listing agreements.\n" +
                                "3.3 License Agreement—Press Releases; Use of Trademarks\n" +
                                "\n" +
                                "Press Releases; Use of Trademarks. Neither Party shall (a) issue a press release or make any other public statement that references this Agreement, or (b) use the other Party's names or trademarks for publicity or advertising purposes, except with the prior written consent of the other Party."),
                        Arrays.asList(dummyExtractor)),


        /* Obligations ->Restrictive Covenants->Inventions Retained and Licensed*/
                new FragmentTestClassification(
                        new AnalysisFragment("Inventions Retained and Licensed.\n" +
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
                                "(b) License of Retained Inventions. If in the course of employment with the Company, employee incorporates into a Company product, process or machine any Retained Inventions [or in any invention which employee has an interest], the the Employee hereby grants and the Company shall have a nonexclusive, royalty-free, irrevocable, perpetual, worldwide license to make, have made, modify, use and sell such Prior Invention as part of or in connection with such product, process or machine."),
                        Arrays.asList(dummyExtractor)),


       /* Obligations ->Restrictive Covenants->Non-Disclosure*/

                new FragmentTestClassification(
                        new AnalysisFragment("Non-Disclosure Obligations.\n" +
                                "\n" +
                                "(a) Confidentiality. Each party agrees to hold the disclosing party's Confidential Information in [strict] confidence [in accordance with the terms of this Agreement].\n" +
                                "\n" +
                                "(b) Non-Disclosure. Each party agrees not to disclose any Confidential Information to third parties (including, without limitation, any clients, affiliates, independent contractors and consultants) without the prior, written consent of the disclosing party except as expressly permitted in this Agreement.\n" +
                                "\n" +
                                "(c) Non-Use. Each party agrees not to use any Confidential Information for any purpose except for the Disclosing Purpose without the prior written consent of the disclosing party."),
                        Arrays.asList(dummyExtractor)),


                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Best Efforts Standard\n" +
                                "\n" +
                                "Use of Confidential Information. The receiving party will keep the Confidential Information in confidence and, except as expressly provided in this Agreement, will not disclose it to anyone without the disclosing party's prior written consent. The receiving party will not use, or permit others to use Confidential Information for any purpose other than for the purpose of evaluating possible business arrangements. The receiving party will use its best efforts to avoid disclosure, dissemination or unauthorized use of Confidential Information.\n" +
                                "\n" +
                                "1.2 Protection of Confidential Information (Employee Agreement)\n" +
                                "\n" +
                                "Employee agrees that:\n" +
                                "(a) Employee shall not use in any manner, directly or indirectly, any Confidential Information except in promoting the Company's business, and as necessary in performing the duties of his/her employment with the Company;\n" +
                                "(b) Employee will not use any Confidential Information for his/her own benefit or for the benefit of any person or entity other than the Company, and will not permit or allow any Confidential Information to be used in competition with the Company.\n" +
                                "(c) During his/her employment with the Company and at all times thereafter, Employee shall take all reasonable steps to prevent any unauthorized disclosure or use of any and all Confidential Information. Employee further agrees to notify the Company immediately in the event that he/she becomes aware of any unauthorized use or disclosure of Confidential Information."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Restrictive Covenants->Non-Disclosure*/

                new FragmentTestClassification(
                        new AnalysisFragment("Described Generally\n" +
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
                                "A \"Competitor\" shall mean (i) Bed Bath & Beyond, Inc., Home Place Inc.,J.C. Penney, Federated Department Stores, Mays, Target, Sears and K-Mart (and any successor or successors thereto); (ii) any home textiles or housewares store, specialty store or other retailer if either 25 million Dollars or 40% or more of its annual gross sales revenues (in either case, based on the most recent quarterly or annual financial statements available) are derived from the sale of home textiles, housewares or other goods or merchandise of the types sold in the Company's (or any Subsidiary's) stores; (iii) any corporation or other entity whether independent or owned, funded or controlled by any other entity, engaged or organized for the purpose of engaging, in whole or in part, in the sale of home textiles, housewares or other goods or merchandise of the types sold in the Company's (or any Subsidiary's) stores; (iv) any business that provides buying office services to any business or group of businesses referred to above, or (v) any business (in the U.S. or any country in which the Company or any Subsidiary operates a store or stores) which is in material competition with the Company or any Subsidiary or division thereof and in which Executive's functions would be substantially similar to Executive's functions with the Company.\n" +
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
                                "\"Restricted Territory\" means any [country][state][county] in which the company conducts business or markets its products or services."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Extension of Restricted Period\n" +
                                "\n" +
                                "If, during any period within the Non-Competition Period, the Executive is not in compliance with the terms of this section, the Company shall be entitled to, among other remedies, compliance by the Executive with the terms of this section for an additional period equal to the period of such noncompliance. For purposes of this Agreement, the term \"Non-Competition Period\" shall also include this additional period."),
                        Arrays.asList(dummyExtractor)),

   /* Obligations ->Restrictive Covenants->Non-Solicitation*/

                new FragmentTestClassification(
                        new AnalysisFragment("Non-Solicitation. During the term of this Agreement and for [NON-SOLICITATION PERIOD] after any termination of this Agreement, [Party] will not, without the prior written consent of the Company, either directly or indirectly[, on [Party]'s own behalf or in the service or on behalf of others], solicit or attempt to solicit, divert or hire away any person employed by the Company or any customer of the Company."),
                        Arrays.asList(dummyExtractor)),

/* Obligations ->Restrictive Covenants->Non-Disparagement*/


                new FragmentTestClassification(
                        new AnalysisFragment("Mutual Non-Disparagement.\n" +
                                "\n" +
                                "(a) Each Investor agrees that, during the Standstill Period, neither it nor any of its Affiliates or Associates will, and it will cause each of its Affiliates and Associates not to, directly or indirectly, in any capacity or manner, make, express, transmit speak, write, verbalize or otherwise communicate in any way (or cause, further, assist, solicit, encourage, support or participate in any of the foregoing), any remark, comment, message, information, declaration, communication or other statement of any kind, whether verbal, in writing, electronically transferred or otherwise, that might reasonably be construed to be derogatory or critical of, or negative toward, the Company or any of its directors, officers, Affiliates, subsidiaries, employees, agents or representatives (collectively, the \"Company Representatives\"), or that reveals, discloses, incorporates, is based upon, discusses, includes or otherwise involves any confidential or proprietary information of the Company or its subsidiaries or Affiliates, or to malign, harm, disparage, defame or damage the reputation or good name of the Company, its business or any of the Company Representatives.\n" +
                                "\n" +
                                "(b) The Company hereby agrees that, during the Standstill Period, neither it nor any of its Affiliates will, and it will cause each of its Affiliates not to, directly or indirectly, in any capacity or manner, make, express, transmit, speak, write, verbalize or otherwise communicate in any way (or cause, further, assist, solicit, encourage, support or participate in any of the foregoing), any remark, comment, message, information, declaration, communication or other statement of any kind, whether verbal, in writing, electronically transferred or otherwise, that might reasonably be construed to be derogatory or critical of, or negative toward, any Investor or any of its agents or representatives (collectively, the \"Investor Representatives\"), or that reveals, discloses, incorporates, is based upon, discusses, includes or otherwise involves any confidential or proprietary information of any Investor or its subsidiaries or Affiliates, or to malign, harm, disparage, defame or damage the reputation or good name of any Investor or Investor Representatives.\n" +
                                "\n" +
                                "(c)Notwithstanding the foregoing, nothing in this Section 7 or elsewhere in this Agreement shall prohibit any Party from making any statement or disclosure required under the federal securities laws or other applicable laws; provided, that such Party must provide written notice to the other Parties at least two business days prior to making any such statement or disclosure required by under the federal securities laws or other applicable laws that would otherwise be prohibited the provisions of this Section 7, and reasonably consider any comments of such other Parties."),
                        Arrays.asList(dummyExtractor)),


       /* Rights ->Termination->Specific Events of Termination->Change of Control*/

                new FragmentTestClassification(
                        new AnalysisFragment("Termination upon Change of Control. If the Executive's employment is terminated by the Company without Cause or by the Executive for Good Reason in connection with or within [one year/two years] after Change in Control, the Executive shall be entitled to Severance Benefits as stated in the Termination Benefits section.\n" +
                                "\n" +
                                "Change in Control. For purposes of this Agreement, unless the Board determines otherwise, a Change of Control of the Company shall be deemed to have occurred at such time as:\n" +
                                "(a) Change in Ownership: any person (as the term is used in Sections 13(d) and 14(d) of the Securities Exchange Act of 1934, as amended (the Exchange Act)) is or becomes the beneficial owner (as defined in Rule 13d-3 under the Exchange Act), directly or indirectly, of voting securities of the Company representing more than 50% of the Company s outstanding voting securities or rights to acquire such securities except for any voting securities issued or purchased under any employee benefit plan of the Company or its subsidiaries; or\n" +
                                "(b) Sale: any sale, lease, exchange or other transfer (in one transaction or a series of transactions) of all or substantially all of the assets of the Company; or\n" +
                                "(c) Liquidation: a plan of liquidation of the Company or an agreement for the sale or liquidation of the Company is approved and completed; or\n" +
                                "(d) Board Determination: the Board determines in its sole discretion that a Change in Control has occurred, whether or not any event described above has occurred or is contemplated.\n"+
                                "(e) Board Membership: individuals who, as of the date hereof, constitute the entire Board of Directors of the Company (the \"Incumbent Directors\") cease for any reason to constitute at least a majority of the Board of Directors, provided that any individual becoming a director subsequent to the date hereof whose election or nomination for election was approved by a vote of at least a majority of the then Incumbent Directors shall be, for purposes of this provision, considered as though such individual were an Incumbent Director;\n" +
                                "(f) Consolidation: any consolidation or merger of the Company (including, without limitation, a triangular merger) where the shareholders of the Company, immediately prior to the consolidation or merger, would not, immediately after the consolidation or merger, beneficially own, directly or indirectly, shares representing in the aggregate more than fifty percent (50%) of the combined voting power of all the outstanding securities of the corporation issuing cash or securities in the consolidation or merger (or of its ultimate parent corporation, if any);"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Following a Change of Control and Compensation Reduction. In the event that a Change in Control, as hereinafter defined, of the Company shall occur at any time during the Term or Renewal Term, and within 12 months of the occurrence of such Change in Control event the Company terminates the Executive without Cause or the Executive shall terminate the Executive's employment under this Agreement, then, in any such event such termination shall be deemed to be a termination by the Company other than for Cause and the Executive shall be entitled to such compensation and benefits as set forth in Section 6(d) of this Agreement, which shall be paid promptly (but not later than 30 days) following the termination of Executive's employment.\n" +
                                "\n" +
                                "For purposes of this Agreement, a \"Change in Control\" of the Company shall mean any of the following:\n" +
                                "(i) a sale of all or substantially all of the assets of the Company;\n" +
                                "(ii) the date there shall have been a change in a majority of the Board of Directors of the Company during a consecutive twelve-month period, unless the nomination for election by the Company's shareholders of each new director was approved.by the vote of two-thirds of the directors then still in office who were in office at the beginning of the twelve-month period;\n" +
                                "(iii) the date that any person or entity, entities or group of persons (other than the Executive) both (A) is or becomes the Beneficial Owner (as defined in Rule 13d-3 under the Securities Exchange Act of 1934), directly or indirectly, of securities of the Company.representing more than thirty percent (30%) or more of the combined voting power of the Company's then outstanding securities, and (B) has voting control of the Company;\n" +
                                "(iv) consummation of a merger or consolidation of the Company with any corporation or other entity, other than a merger or consolidation which would result in the voting securities of the Company outstanding immediately prior thereto continuing to represent (either by remaining outstanding or by being converted into voting securities of the surviving entity) more than fifty percent (50%) of the combined voting power of the voting securities of the Company or such surviving entity outstanding immediately after such merger or consolidation;\n" +
                                "(v) a change in ownership of the Company through a transaction or series of transactions, such that any person or entity is or becomes the Beneficial Owner (as defined in Rule 13d-3 under the Exchange Act), directly or indirectly, of securities of the Company representing fifty percent (50%) or more of securities of the combined voting power of the Company's then outstanding securities; provided that, for such purposes, any acquisition by the Company, in exchange for the Company's securities, shall be disregarded; or\n" +
                                "(vi) the Board (or the stockholders if stockholder approval is required by applicable law or under the terms of any relevant agreement) shall approve a plan of complete liquidation of the Company.\n" +
                                "provided, however, that a Change of Control shall expressly not include (A) any consolidation or merger effected exclusively to change the domicile of the Company, (B) any transaction or series of transactions principally for bona fide equity financing purposes, or (C) the Transaction."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Termination Following a Change of Control.\n" +
                                "1. In the event that a \"Change in Control,\" as hereinafter defined, shall occur at any time during the Term or Renewal Term hereof, the Executive shall have the right to terminate the Executive's employment under this Agreement upon thirty (30) days written notice given at any time within one (1) year after the occurrence of such event.\n" +
                                "\n" +
                                "2. For purposes of this Agreement, a \"Change in Control\" of the Company shall mean a change in control:\n" +
                                "a)the occurrence of any of the following:\n" +
                                "i) any person, group or organization, other than the Executive, is or becomes the beneficial owner, directly or indirectly, of securities of the Company representing fifty percent (50%) or more of the combined voting power of the Company's outstanding securities then having the right to vote at elections of directors; or\n" +
                                "ii) the individuals who at the Effective Date of this Agreement constitute the Board of Directors cease for any reason to constitute a majority thereof unless the election, or nomination for election, of each new director was approved by the Executive; or\n" +
                                "iii) the business or over fifty percent (50%) of the business revenues of the Company for which the Executive's services are principally performed is/ are sold or otherwise disposed of by the Company (including the stock of a subsidiary of the Company).\n" +
                                "\n" +
                                "Anything herein to the contrary notwithstanding, this Section 6G2 will not apply where the Executive gives the Executive's explicit written waiver stating that for purposes of this Section 6G2 a Change in Control shall not be deemed to have occurred. The Executive's participation in any negotiations or other matters in relation to a Change in Control shall in no way constitute such a waiver which can only be given by an explicit written waiver as provided in the preceding sentence."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Termination->Specific Events of Termination->Termination by Executive*/


/* Rights ->Termination->Termination Benefits*/



/* Rights ->Termination->Termination Procedures*/


                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Conditions on Severance\n" +
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
                                "Any party giving notice of a dispute shall pursue the resolution of such dispute with reasonable diligence. Notwithstanding the dependency of any such dispute, the Company will continue to pay the Executive his full compensation in effect when the notice giving rise to the dispute was given (including, but not limited to, base salary) and continue the Executive as a participant in all compensation, employee benefit and insurance plans, programs and arrangements in which the Executive was participating when the notice giving rise to the dispute was given, until the dispute is finally resolved."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Notice of Termination. Except for termination as specified in Section 3(a), any termination of the Executive�s employment by the Company or any such termination by the Executive shall be communicated by written Notice of Termination to the other party hereto. For purposes of this Agreement, a �Notice of Termination� shall mean a notice which shall indicate the specific termination provision in this Agreement relied upon.\n" +
                                "\n" +
                                "Date of Termination. �Date of Termination� shall mean: (i) if the Executive�s employment is terminated by his death, the date of his death; (ii) if the Executive�s employment is terminated on account of disability under Section 3(b) or by the Company for Cause under Section 3(c) or by the Company without Cause under Section 3(d), the date on which a Notice of Termination is given; (iii) if the Executive�s employment is terminated by the Executive under Section 3(e) without Good Reason, 30 days after the date on which a Notice of Termination is given, and (iv) if the Executive�s employment is terminated by the Executive under Section 3(e) with Good Reason, the date on which a Notice of Termination is given after the end of the Cure Period. Notwithstanding the foregoing, in the event that the Executive gives a Notice of Termination to the Company, the Company may unilaterally accelerate the Date of Termination and such acceleration shall not result in a termination by the Company for purposes of this Agreement.\n" +
                                "\n" +
                                "Resignation on Termination. On the Date of Termination, the Executive shall resign from all positions with the Company and its subsidiaries. In addition, if the Executive is then serving as a member of the Board or the Board of Directors of a subsidiary, the Executive shall tender his resignation from such directorship(s) on the Date of Termination."),
                        Arrays.asList(dummyExtractor)),

/* Rights ->Claims->Employment Indemnification*/


/* Remedies ->Enforcement->Remedies Cumulative */

                new FragmentTestClassification(
                        new AnalysisFragment("Remedies Cumulative. All rights and remedies provided in this Agreement are cumulative and not exclusive of any other rights or remedies that may be available to the parties, whether provided by law, equity, statute, in any other agreement between the parties or otherwise."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Rights and Remedies Cumulative. The enumeration of Lender's rights and remedies set forth in this Loan Agreement is not intended to be exhaustive. The exercise by Lender of any right or remedy under this Loan Agreement does not preclude the exercise of any other rights or remedies, all of which are cumulative and are in addition to any other right or remedy given under this Loan Agreement or under any other agreement between Lender and any Borrower or Guarantor or which may now or subsequently exist in law, in equity, by statute or otherwise."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("No Waiver; Cumulative Remedies. A failure to exercise or a delay in exercising, on the part of the Lender, any right, remedy, power or privilege hereunder or under the other Loan Documents shall not operate as a waiver thereof; nor shall any single or partial exercise of any right, remedy, power or privilege hereunder preclude any other or further exercise thereof or the exercise of any other right, remedy, power or privilege. The rights, remedies, powers and privileges herein provided are cumulative and not exclusive of any rights, remedies, powers and privileges provided by law."),
                        Arrays.asList(dummyExtractor)),

/* Remedies ->Enforcement->Specific Performance */

                new FragmentTestClassification(
                        new AnalysisFragment("Specific Performance. Any breach of this Agreement may result in irreparable damage to [Party2] for which [Party 2] will not have an adequate remedy at law. Accordingly, in addition to any other remedies and damages available, [Party1] acknowledges and agrees that [Party2] may immediately seek enforcement of this Agreement by means of specific performance or injunction, without any requirement to post a bond or other security."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("[Party 1] acknowledges that (i) the restrictions contained in this Agreement are reasonable in scope and are necessary to protect the [Party 2]'s legitimate interests in protecting its business, and (ii) any violation of the restrictions contained in this Agreement will cause significant and irreparable harm to the [Party 2] for which the [Party 2] has no adequate remedy at law"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("[Party 2] shall also be entitled to obtain injunctive relief, including but not limited to a temporary restraining order, a temporary or preliminary injunction or a permanent injunction, to enforce the provisions of this Agreement, as well as an equitable accounting of and constructive trust for all profits or other benefits arising out of or related to any such violation, all of which shall constitute rights and remedies to which the [Party 2] may be entitled.\"\n" +
                                "\n" +
                                "Without Bond or Proof of Damages\n" +
                                "\n" +
                                "Example\n" +
                                "[Party 2] shall be entitled to such relief without the necessity of proving actual damages or posting a bond, in addition to, and not in lieu of, any other rights and remedies available to [Party 2] under law or in equity have the right and remedy to have the provisions of this agreement enforced by injunctive relief in any court of competent jurisdiction, it being agreed that any breach or threatened breach of this agreement would cause irreparable injury to [Party 2] and that damages would not provide an adequate remedy to [Party 2]"),
                        Arrays.asList(dummyExtractor)),


/* Adjudication ->Agreement Scope->Entire Agreement */

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement [(together with the documents [referred to in this Agreement] [listed on Exhibit A])] constitute[s] the entire agreement between the parties with respect to its subject matter and supersedes all prior agreements, representations and understandings of the parties, written or oral."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Transaction Documents, together with the exhibits and schedules thereto, contain the entire understanding of the parties with respect to the subject matter hereof and supersede all prior agreements and understandings, oral or written, with respect to such matters, which the parties acknowledge have been merged into such documents, exhibits and schedules. "),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement, together with the exhibits and schedules hereto, constitutes the entire understanding and agreement of the parties hereto with respect to the subject matter hereof and supersedes all prior and contemporaneous agreements or understandings, inducements or conditions, express or implied, written or oral, between the parties with respect hereto other than the Confidentiality Agreement. The express terms hereof control and supersede any course of performance or usage of the trade inconsistent with any of the terms hereof."),
                        Arrays.asList(dummyExtractor)),

        /* Adjudication ->Agreement Scope->Further Assurances */

                new FragmentTestClassification(
                        new AnalysisFragment("Further Assurances The parties shall execute such further documents and do any and all such further things as may be necessary to implement and carry out the intent of this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Each party shall do and perform, or cause to be done and performed, all such further acts and things, and shall execute and deliver all such other agreements, certificates, instruments and documents, as the other party may reasonably request in order to carry out the intent and accomplish the purposes of this Agreement and the consummation of the transactions contemplated hereby."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The parties shall upon reasonable request of the other, execute such documents as may be necessary or appropriate to carry out the intent of this Agreement.\n" +
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
                                "In case at any time after the Effective Time any further action is reasonably necessary to carry out the purposes of this Agreement or the transactions contemplated by this Agreement, the proper officers of the Company, Parent and the Surviving Corporation shall take any such reasonably necessary action."),
                        Arrays.asList(dummyExtractor)),

        /* Adjudication ->Agreement Scope->Counterparts */


                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement may be executed in counterparts, each of which shall be deemed to be an original, but all of which, taken together, shall constitute one and the same agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Example 1—effective upon signature and delivery\n" +
                                "\n" +
                                "This Agreement may be executed in counterparts (each of which shall be deemed to be an original but all of which taken together shall constitute one and the same agreement) and shall become effective when one or more counterparts have been signed by each of the parties and delivered to the other party.\n" +
                                "\n" +
                                "Example 2—facsimile\n" +
                                "\n" +
                                "This Agreement may be executed in two or more counterparts, and by facsimile, all of which shall be considered one and the same agreement and shall become effective when one or more counterparts have been signed by each of the parties and delivered to the other party, it being understood that all parties need not sign the same counterpart.\n" +
                                "\n" +
                                "Example 3—electronic execution\n" +
                                "\n" +
                                "This Agreement may be executed in multiple counterparts and transmitted by facsimile or by electronic mail in \"portable document format\" (\"PDF\") form, or by any other electronic means intended to preserve the original graphic and pictorial appearance of a party's a signature. Each such counterpart and facsimile or PDF signature shall constitute an original and all of which together shall constitute one and the same original."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Amendments */

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement may be amended only by a written instrument signed by the Parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Bilateral Amendment\n" +
                                "\n" +
                                "No amendment to this agreement will be effective unless it is in writing and signed by both parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Authorization Person\n" +
                                "\n" +
                                "The parties may not amend this agreement, except by written agreement executed by [TITLE OR POSITION OF AUTHORIZED INDIVIDUAL] of each party."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Schedules and Exhibits */

                new FragmentTestClassification(
                        new AnalysisFragment("Schedules and Exhibits The exhibits and schedules referred to in this Agreement will be deemed to be a part of this Agreement."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Recitals, Schedules and Exhibits to this Agreement are incorporated herein and, by this reference, made a part hereof as if fully set forth herein."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The recitals, Annexes, Exhibits and Schedules identified in this Agreement are incorporated herein by reference and made a part hereof. Each disclosure in a Schedule referred to in this Agreement shall be deemed to qualify all representations and warranties of the party making such disclosure, notwithstanding the absence of a specific cross-reference, except to the extent that its applicability to a particular representation, warranty, agreement or condition is not reasonably apparent from the disclosure thereof."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The Schedules to this Agreement are hereby incorporated in and made a part of this Agreement as if set forth in full herein and are an integral part of this Agreement. Matters set forth in the Schedules are not necessarily limited to matters required by this Agreement to be reflected in the schedules. Such additional matters are set forth for informational purposes, and the Schedules do not necessarily include other matters of a similar nature. Nothing in this Agreement or in the Schedules constitutes an admission that any information disclosed, set forth or incorporated by reference in the Schedules or this Agreement is material, constitutes a Material Adverse Effect or is otherwise required by the terms of this Agreement to be so disclosed, set forth or incorporated by reference. All information and disclosures contained in the Schedules are made as of the date of this Agreement and their accuracy is confirmed only as of such date and not at any time thereafter. Matters disclosed pursuant to any section of this Agreement are deemed to be disclosed with respect to all sections of this Agreement and the Schedules to the extent this Agreement requires such disclosure; provided that it is clear by appropriate cross-references that a given disclosure is applicable to such other Schedules, sections and paragraphs. Any capitalized terms used in any Schedule or Exhibit but not otherwise defined therein shall be defined as set forth in this Agreement."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Agreement Scope->Effective Date */

                new FragmentTestClassification(
                        new AnalysisFragment("Effective Date The effective date of this Employment Agreement (the \"Effective Date\") shall be [DATE]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Controlled by Opening Statement\n" +
                                "\n" +
                                "This agreement is effective as of the date shown at the top of the first page, even if any signatures are made after that date.\n" +
                                "\n" +
                                "1.2 Effective Upon Last Signature\n" +
                                "\n" +
                                "This Credit Agreement shall be binding and deemed effective when executed by Borrower, Administrative Agent, and each Lender whose signature is provided for on the signature pages hereof (the \"Effective Date\")."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Notices */

                new FragmentTestClassification(
                        new AnalysisFragment("Notices:\n" +
                                "(a) Form of Notice. All notices, requests, claims, demands and other communications between the parties shall be in writing.\n" +
                                "\n" +
                                "(b) Method of Notice. All notices shall be given (i) by delivery in person (ii) by a nationally recognized next day courier service, (iii) by first class, registered or certified mail, postage prepaid, (iv) by facsimile [or (v) by electronic mail] to the address of the party specified in this Agreement or such other address as either party may specify in writing.\n" +
                                "\n" +
                                "(c) Receipt of Notice. All notices shall be effective upon (i) receipt by the party to which notice is given, or (ii) on the [fifth (5th)] day following mailing, whichever occurs first."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Form—Writing and Language\n" +
                                "\n" +
                                "All notices, requests, claims, demands and other communications shall be in writing [in the English language] [and shall be signed by a person duly authorized to provide such notice].\n" +
                                "\n" +
                                "1.2 How—Method and Address\n" +
                                "\n" +
                                "Notices permitted or required to be given hereunder shall be deemed sufficient if given by (a) registered or certified mail, postage prepaid, return receipt requested, (b) private courier service, or (c) facsimile addressed to the respective addresses of the parties as first above written or at such other addresses as the respective parties may designate by like notice from time to time.\n" +
                                "\n" +
                                "1.3 When—Time of Receipt\n" +
                                "\n" +
                                "Such notice shall be deemed to have been given upon receipt."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("All notices \"shall be deemed to have been duly given (a) when delivered in person, (b) upon confirmation of receipt when transmitted by facsimile transmission or by electronic mail (but, in the case of electronic mail, only if followed by transmittal by national overnight courier or hand for delivery on the next Business Day), (c) upon receipt after dispatch by registered or certified mail, postage prepaid or (d) on the next Business Day if transmitted by national overnight courier (with confirmation of delivery)."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("2.1 Change of Address\n" +
                                "\n" +
                                "Any party to this Agreement may notify any other party of any changes to the address or any of the other details specified in this paragraph; provided, however, that such notification shall only be effective on the date specified in such notice or five Business Days after the notice is given, whichever is later.\n" +
                                "\n" +
                                "Refusal of Delivery\n" +
                                "\n" +
                                "Rejection or other refusal to accept or the inability to deliver because of changed address of which no notice was given shall be deemed to be receipt of the notice as of the date of such rejection, refusal or inability to deliver. Verizon Merger"),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Binding Effect */

                new FragmentTestClassification(
                        new AnalysisFragment("Binding Effect. This Agreement shall be binding upon and inure to the benefit of the parties and their respective heirs, successors and permitted assigns."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("\n" +
                                "No Third Party Beneficiary. The Agreement [does/will] not confer any rights or remedies upon any person other the parties and their respective heirs, executors, administrators, personal representatives, beneficiaries, successors and permitted assigns."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Third Parties */

                new FragmentTestClassification(
                        new AnalysisFragment("Third Party Beneficiaries. This agreement does not and is not intended to confer any rights or remedies upon any person other than the parties."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Example 1\n" +
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
                                "Third Party Beneficiaries. This agreement confers rights and remedies upon [named beneficiaries] as set forth in section [___]. No person, other than the parties and such [named beneficiaries], has any rights or remedies under the agreement. The parties may not amend or terminate this agreement without the prior written consent of the [named beneficiaries]."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Parties and Representatives->Successors and Assigns */

                new FragmentTestClassification(
                        new AnalysisFragment("Successors and Assigns. This Agreement shall be binding upon and shall inure to the benefit of the parties and their [permitted] successors and assigns."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Example 1\n" +
                                "\n" +
                                "Successors and Assigns. This agreement benefits and binds the parties and their [permitted] successors and assigns.\n" +
                                "\n" +
                                "Example 2—Application to Natural Persons\n" +
                                "\n" +
                                "Successors and Assigns. This Agreement shall be binding upon and shall inure to the benefit of the parties and their heirs, executors, administrators, [successors] and [permitted] assigns.\n" +
                                "\n" +
                                "Note: The term \"successor\" can probably be omitted when applied to natural person, because individuals do not have successors."),
                        Arrays.asList(dummyExtractor)),

                /* Adjudication ->Enforcement->Governing Law */

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement shall be governed, construed, and enforced in accordance with the laws of the State of [GOVERNING LAW STATE], without regard to its conflict of laws rules."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement shall be governed by and construed in accordance with the laws of the State of [GOVERNING LAW STATE], without regard to its conflict of laws rules.\n"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This agreement shall be governed by, and construed in accordance with, the [internal OR domestic OR mandatory] laws of the state of:\n" +
                                "(a) regardless of the laws that might otherwise govern under applicable principles of conflicts of laws thereof\n" +
                                "OR\n" +
                                "(b) applicable to agreements made and to be performed solely therein, without giving effect to principles of conflicts of law\n" +
                                "OR\n" +
                                "(c) without giving effect to any choice or conflict of Law provision or rule (whether of the State of [________] of any other jurisdiction) that would cause the application of the Laws of any jurisdiction other than the State of "),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("This Agreement shall in all respects be governed by, and construed and interpreted in accordance with, the Laws of the State of New York without giving effect to any conflicts of law principles of such state that might refer the governance, construction or interpretation of this Agreement to the Laws of another jurisdiction."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Enforcement->Jurisdiction */

                new FragmentTestClassification(
                        new AnalysisFragment("Jurisdiction. The parties submit all their disputes arising out of or in connection with this Agreement to the exclusive jurisdiction of the Courts of [ ]."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("Venue.\n" +
                                "Courts. Any action at law, suit in equity or judicial proceeding arising directly, indirectly, or otherwise in connection with, out of, related to or from this Agreement shall be litigated only in the courts of the State of [ ]. Consent to Jurisdiction. The Parties consent to the jurisdiction of such courts over the subject matter set forth in Section 8. Waiver of Right to Transfer. The Executive waives any right the Executive may have to transfer or change the venue of any litigation brought against the Executive by the Company."),
                        Arrays.asList(dummyExtractor)),

        /* Adjudication ->Interpretation->Severability */

                new FragmentTestClassification(
                        new AnalysisFragment("Severability. The invalidity or unenforceability of any provisions of this Agreement shall not affect the validity or enforceability of any other provision of this Agreement, which shall remain in full force and effect."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.1 Severability—Renegotiation\n" +
                                "\n" +
                                "If any term or other provision of this Agreement is determined to be invalid, illegal or incapable of being enforced by any rule or law, or public policy, all other conditions and provisions of this Agreement shall nevertheless remain in full force and effect so long as the economic or legal substance of the transactions contemplated hereby is not affected in any manner materially adverse to any party. Upon such determination that any term or other provision is invalid, illegal or incapable of being enforced, the parties hereto shall negotiate in good faith to modify this Agreement so as to effect the original intent of the parties as closely as possible in an acceptable manner to the end that transactions contemplated hereby are fulfilled to the extent possible."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("1.2 Reformation (\"Blue Pencil\")\n" +
                                "\n" +
                                "Each term and provision of this agreement shall be valid and enforceable to the fullest extent permitted by law and any invalid, illegal or unenforceable term or provision shall be deemed replaced by a term or provision that is valid and enforceable and that comes closest to expressing the intention of the invalid, illegal or unenforceable term or provision."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("If the final judgment of such court or arbitrator declares that any term or provision hereof is invalid, void or unenforceable, the parties agree to reduce the scope, duration, area or applicability of the term or provision, to delete specific words or phrases, or to replace any invalid, void or unenforceable term or provision with a term or provision that is valid and enforceable and that comes closest to expressing the original intention of the invalid or unenforceable term or provision."),
                        Arrays.asList(dummyExtractor)),

/* Adjudication ->Interpretation->Waiver */

                new FragmentTestClassification(
                        new AnalysisFragment("A party's failure to exercise or delay in exercising any right, power or privilege under this Agreement shall not operate as a waiver; nor shall any single or partial exercise of any right, power or privilege preclude any other or further exercise"),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The waiver by the Employer of a breach of any provision of this Agreement by Employee will not operate or be construed as a waiver of any other subsequent breach by Employee."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("No General Waivers. The failure of any party at any time to require performance of any provision or to resort to any remedy provided under this Agreement shall in no way affect the right of that party to require performance or to resort to a remedy at any time thereafter, nor shall the waiver by any party of a breach be deemed to be a waiver of any subsequent breach. A waiver shall not be effective unless it is in writing and signed by the party against whom the waiver is being enforced."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("No course of dealing, nor any failure to exercise, nor any delay in exercising any right, power or privilege hereunder shall operate as a waiver thereof."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("A party's waiver of any breach, default or right under this Agreement must be in writing and signed by the party against whom the waiver is being enforced. Any such waiver shall not be deemed a waiver of any subsequent breach, default or right, whether of the same nature or otherwise."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The waiver by either party of a breach of any provision of this Agreement must be in writing and shall not operate or be construed as a waiver of any other breach."),
                        Arrays.asList(dummyExtractor)),

                new FragmentTestClassification(
                        new AnalysisFragment("The rights and remedies under this Agreement are cumulative and not exclusive of any rights, remedies, powers and privileges that may otherwise be available to the parties."),
                        Arrays.asList(dummyExtractor)),

        };

        for (FragmentTestClassification classification:employmentAgreement) {

            classification.testIt(all);
        }


    }

}
