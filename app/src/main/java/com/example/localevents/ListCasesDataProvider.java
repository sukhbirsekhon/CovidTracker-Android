package com.example.localevents;

public class ListCasesDataProvider
{
    private String countryName;
    private String cases;

    public ListCasesDataProvider(String countryName, String cases)
    {
        this.countryName = countryName;
        this.cases = cases;
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
}
