package com.example.localevents;

public class ListCasesDataProvider
{
    private String countryName;
    private String cases;
    private String activeCases;
    private String recoveredCases;
    private String deaths;

    private String newCases;
    private String newDeaths;
    private String seriousCritical;
    private String casesPerMillion;

    private boolean expanded;

    public ListCasesDataProvider(String countryName, String cases, String activeCases,
                                 String recoveredCase, String deaths, String newCases, String newDeaths, String seriousCritical,
                                 String casesPerMillion)
    {
        this.countryName = countryName;
        this.cases = cases;
        this.activeCases = activeCases;
        this.recoveredCases = recoveredCase;
        this.deaths = deaths;
        this.newCases = newCases;
        this.newDeaths = newDeaths;
        this.seriousCritical = seriousCritical;
        this.casesPerMillion = casesPerMillion;

        this.expanded = false;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(String recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getSeriousCritical() {
        return seriousCritical;
    }

    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    public String getCasesPerMillion() {
        return casesPerMillion;
    }

    public void setCasesPerMillion(String casesPerMillion) {
        this.casesPerMillion = casesPerMillion;
    }

}
