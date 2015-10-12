package featureTypes;

import language.English;
import language.LanguageInterface;
import log.AnalysisLogger;

import java.util.ArrayList;
import java.util.List;

/**
 *              Functionality for a feature type.
 *              A feature type can be part of other feature types (the hierarchical structure of classifications)
 *
 */
public class FeatureType implements FeatureTypeInterface{

    private String name;
    protected String description = "";
    protected String definition = name;
    private String akaTag;   // Also known as - find THIS classification also under the aka name. E.g. A percentage is also a number
    private FeatureTypeInterface parent = null;

    /************************************************************************************
     *
     *      The part-of list defines which classifications may be a part of this (i.e parent classifications)
     *
     *          e.g. A number is part of a percentage
     *
     *      This is used to block classifications so that only the parent classification is triggered
     *
     */

    protected List<FeatureTypeInterface> partOfList = new ArrayList<>();


    FeatureType(String name, String searchTag){

        this.name = name;
        this.akaTag = searchTag;
        this.description = "";

    }


    @Override
    public List<FeatureTypeInterface> getIsPartOfList() {

        return partOfList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAkaTag() {
        return akaTag;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDefinition() {
        return definition;
    }

    public boolean isPartOf(FeatureTypeInterface higherAbstractionFeature) {

        boolean isPart = false;
        for(FeatureTypeInterface featureType : getIsPartOfList()){

            isPart |= (featureType != null && higherAbstractionFeature.getName().equals(featureType.getName()));
        }
        AnalysisLogger.log(AnalysisLogger.Level.DEBUG, "  - Checking partOf: " + higherAbstractionFeature.getName() + " in " + getIsPartOfList().toString() + "..." + isPart);
        return isPart;
    }


    public void setIsPartOf(FeatureTypeInterface higherAbstractionFeature) {

        partOfList.add(higherAbstractionFeature);
    }

    public void setParent(FeatureTypeInterface higherAbstractionFeature) {

         parent = higherAbstractionFeature;
     }

    public FeatureTypeInterface getParent() {
        return parent;
    }

    //TODO: Add language as parameter

    @Override
    public String getHierarchy() {

        if(parent == null)
            return "";

        LanguageInterface defaultLanguage = new English();
        String localizedTag = defaultLanguage.getLocalizedClassification(parent.getName());

        return parent.getName() + " #" +localizedTag +  " " + parent.getHierarchy();      //TODO Optimize string handling here
    }

    @Override
    public String createKeywordString(String keywords) {

        return  "#CLASSIFICATION "+name + " " +
                getHierarchy() + " " +
                getAkaTag() + " " +
                keywords;
    }

}
