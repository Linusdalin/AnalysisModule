package analysis2;

import classifiers.Classification;

/**
 *          Semantic Restriction is an additional restriction put on a
 *          regular expression to accept the classification and extraction.
 *
 */

public class SemanticRestriction {

    private final int group;
    private final RestrictionType type;


    public enum RestrictionType {

        IS_NAME_ENTITY,       // The extracted item is a name entity
        IS_DESCRIBING,        // The extracted item is a description of sort

    }


    SemanticRestriction(int group, RestrictionType type){


        this.group = group;
        this.type = type;
    }


    public int getGroup() {
        return group;
    }

    public RestrictionType getType() {
        return type;
    }


    public boolean isFulfilled(int regexGroupSpan, TextSpan span) {


        switch (type) {

            case IS_NAME_ENTITY:
                break;
            case IS_DESCRIBING:
                break;
        }

        //TODO: Not implemented

        System.out.println("IGNORING semantic restriction " + type.name() + " for span " + span.getText());

        return true;
    }


}

