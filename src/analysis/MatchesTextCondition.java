package analysis;

import textPatterns.TextPattern;

/**
 * Created by Linus
 */

public class MatchesTextCondition implements ConditionType {

    public TextPattern textPattern;

    public MatchesTextCondition(TextPattern text) {
        this.textPattern = text;

    }
}
