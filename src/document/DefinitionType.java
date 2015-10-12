package document;

/************************************************************************''
 *
 *          Definitions can have different types (with different
 *          semantic meanings) that are used in the rules
 */
public enum DefinitionType {

    REGULAR,            // A regular definitions with no other semantic meaning
    OBJECT,             // The definition refers to something that is an object (e.g. a deliverable)
    ACTOR,               // The definition defines a specific party or entity
    RECIPROCAL_ACTOR,    // The definition defines someone that is an actor (e.g part of a contract)

    ;

    public static DefinitionType getTypeByName(String name){

        if(name.equals(OBJECT.name()))
            return OBJECT;
        if(name.equals(ACTOR.name()))
            return ACTOR;
        if(name.equals(RECIPROCAL_ACTOR.name()))
            return RECIPROCAL_ACTOR;

        return REGULAR;
    }

}