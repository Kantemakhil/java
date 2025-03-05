package net.syscon.s4.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Suburb",
        "Number",
        "StreetSuffixFull",
        "Suburb_Latitude",
        "Meshblock",
        "Meshblock2016",
        "Meshblock2021",
        "Gnaf_ID"
})
public class AddressifyAddressDTO extends AddressDTO {

    @JsonProperty("Suburb")
    private String suburb;
    @JsonProperty("Number")
    private String number;
    @JsonProperty("StreetSuffixFull")
    private Object streetSuffixFull;
    @JsonProperty("Suburb_Latitude")
    private Double suburbLatitude;
    @JsonProperty("Meshblock")
    private String meshblock;
    @JsonProperty("Meshblock2016")
    private String meshblock2016;
    @JsonProperty("Meshblock2021")
    private String meshblock2021;
    @JsonProperty("Gnaf_ID")
    private String gnafID;

    @JsonProperty("Suburb")
    public String getSuburb() {
        return suburb;
    }

    @JsonProperty("Suburb")
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    @JsonProperty("Number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("Number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("StreetSuffixFull")
    public Object getStreetSuffixFull() {
        return streetSuffixFull;
    }

    @JsonProperty("StreetSuffixFull")
    public void setStreetSuffixFull(Object streetSuffixFull) {
        this.streetSuffixFull = streetSuffixFull;
    }

    @JsonProperty("Suburb_Latitude")
    public Double getSuburbLatitude() {
        return suburbLatitude;
    }

    @JsonProperty("Suburb_Latitude")
    public void setSuburbLatitude(Double suburbLatitude) {
        this.suburbLatitude = suburbLatitude;
    }

    @JsonProperty("Meshblock")
    public String getMeshblock() {
        return meshblock;
    }

    @JsonProperty("Meshblock")
    public void setMeshblock(String meshblock) {
        this.meshblock = meshblock;
    }

    @JsonProperty("Meshblock2016")
    public String getMeshblock2016() {
        return meshblock2016;
    }

    @JsonProperty("Meshblock2016")
    public void setMeshblock2016(String meshblock2016) {
        this.meshblock2016 = meshblock2016;
    }

    @JsonProperty("Meshblock2021")
    public String getMeshblock2021() {
        return meshblock2021;
    }

    @JsonProperty("Meshblock2021")
    public void setMeshblock2021(String meshblock2021) {
        this.meshblock2021 = meshblock2021;
    }

    @JsonProperty("Gnaf_ID")
    public String getGnafID() {
        return gnafID;
    }

    @JsonProperty("Gnaf_ID")
    public void setGnafID(String gnafID) {
        this.gnafID = gnafID;
    }

}