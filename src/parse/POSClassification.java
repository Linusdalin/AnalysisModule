package parse;


/**
 *
 *
 1. 	CC 	Coordinating conjunction
 2. 	CD 	Cardinal number
 3. 	DT 	Determiner
 4. 	EX 	Existential there
 5. 	FW 	Foreign word
 6. 	IN 	Preposition or subordinating conjunction
 7. 	JJ 	Adjective
 8. 	JJR 	Adjective, comparative
 9. 	JJS 	Adjective, superlative
 10. 	LS 	List item marker
 11. 	MD 	Modal
 12. 	NN 	Noun, singular or mass
 13. 	NNS 	Noun, plural
 14. 	NNP 	Proper noun, singular
 15. 	NNPS 	Proper noun, plural
 16. 	PDT 	Predeterminer
 17. 	POS 	Possessive ending
 18. 	PRP 	Personal pronoun
 19. 	PRP$ 	Possessive pronoun
 20. 	RB 	Adverb
 21. 	RBR 	Adverb, comparative
 22. 	RBS 	Adverb, superlative
 23. 	RP 	Particle
 24. 	SYM 	Symbol
 25. 	TO 	to
 26. 	UH 	Interjection
 27. 	VB 	Verb, base form
 28. 	VBD 	Verb, past tense
 29. 	VBG 	Verb, gerund or present participle
 30. 	VBN 	Verb, past participle
 31. 	VBP 	Verb, non-3rd person singular present
 32. 	VBZ 	Verb, 3rd person singular present
 33. 	WDT 	Wh-determiner
 34. 	WP 	Wh-pronoun
 35. 	WP$ 	Possessive wh-pronoun
 36. 	WRB 	Wh-adverb

 *
 */
public enum POSClassification {


    CC  ("Coordinating conjunction"),
    CD  ("Cardinal number"),
    DT 	("Determiner"),
    EX 	("Existential there"),
    FW 	("Foreign word"),
    IN 	("Preposition or subordinating conjunction"),
    AJ 	("Adjective"),
    JJR ("Adjective, comparative"),
    JJS ("Adjective, superlative"),
    LS 	("List item marker"),
    MD 	("Modal"),
    NN 	("Noun, singular or mass"),
    NNS ("Noun, plural"),
    NNP ("Proper noun, singular"),
    NNPS("Proper noun, plural"),
    PDT ("Predeterminer"),
    POS ("Possessive ending"),
    PRP ("Personal pronoun"),
    PRP$("Possessive pronoun"),
    RB 	("Adverb"),
    RBR ("Adverb, comparative"),
    RBS ("Adverb, superlative"),
    RP 	("Particle"),
    SYM ("Symbol"),
    TO 	("to"),
    UH 	("Interjection"),
    VV 	("Verb"),
    VB 	("Verb, base form"),
    VBD ("Verb, past tense"),
    VBG ("Verb, gerund or present participle"),
    VBN ("Verb, past participle"),
    VBP ("Verb, non-3rd person singular present"),
    VBZ ("Verb, 3rd person singular present"),
    WDT ("Wh-determiner"),
    WP 	("Wh-pronoun"),
    WP$ ("Possessive wh-pronoun"),
    WRB ("Wh-adverb"),


    // Compound types, used for matching

    ANY("Any"),
    ANY_NN("Any Noun"),
    ANY_JJ("Any Adjective"),
    ANY_VB("Any Verb"),

    // Linus added this to handle . , ; : etc.

    IP ("Inter punctuation"),
    UNK("Unknown"),
    QT ("Quote"),
    LRB("Left Bracket"),
    RRB("Right Bracket");

    String description;

    private POSClassification(String name){

        description = name;
    }

    public String getDescription(){

        return description;
    }

    public boolean isAdjective(){

        return this == AJ || this == JJR || this == JJS;
    }

    public boolean isPreposition(){


        return this == IN;
    }


    public boolean isVerb(){

        return this == VV
                || this == VB
                || this == VBD
                || this == VBG
                || this == VBN
                || this == VBP
                || this == VBZ;
    }

    public boolean isNoun(){

        return this == NN
                || this == NNS
                || this == NNP
                || this == NNPS;
    }


    public boolean isWord() {
        return this != IP && this != QT && this != LRB && this != RRB;
    }

    public boolean is(SimpleClassification wordClass) {

        switch(wordClass){

            case ANY:

                return true;

            case VERB:

                return  this == VV
                    || this == VB
                    || this == VBD
                    || this == VBG
                    || this == VBN
                    || this == VBP
                    || this == VBZ;

            case NOUN:

                return this == NN
                        || this == NNS
                        || this == NNP
                        || this == NNPS;
        }

        return true;
    }


    public boolean is(String pattern) {

        if(pattern.equals(ANY.name()))
            return true;
        if(pattern.equals(ANY_NN.name()))
            return is(SimpleClassification.NOUN);
        if(pattern.equals(ANY_VB.name()))
            return is(SimpleClassification.VERB);
        if(pattern.equals(ANY_JJ.name()))
            return is(SimpleClassification.ADJECTIVE);

        return pattern.equals(this.name());

    }
}
