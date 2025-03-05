package net.syscon.s4.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import net.syscon.s4.common.beans.BaseModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AddressFull",
        "AddressLine1",
        "AddressLine2",
        "NumberFirst",
        "NumberLast",
        "StreetSuffix",
        "State",
        "Street",
        "StreetLine",
        "BuildingName",
        "StreetType",
        "StreetTypeFull",
        "UnitType",
        "UnitTypeFull",
        "UnitNumber",
        "LevelType",
        "LevelTypeFull",
        "LevelNumber",
        "Postcode",
        "Longitude",
        "Latitude",
        "Street_Longitude",
        "Street_Latitude",
        "Suburb_Longitude",
        "Valid"
})
public class AddressDTO extends BaseModel {

    @JsonProperty("AddressFull")
    private String addressFull;
    @JsonProperty("AddressLine1")
    private String addressLine1;
    @JsonProperty("AddressLine2")
    private String addressLine2;
    @JsonProperty("NumberFirst")
    private String numberFirst;
    @JsonProperty("NumberLast")
    private Object numberLast;
    @JsonProperty("StreetSuffix")
    private Object streetSuffix;
    @JsonProperty("State")
    private String state;
    @JsonProperty("Street")
    private String street;
    @JsonProperty("StreetLine")
    private String streetLine;
    @JsonProperty("BuildingName")
    private String buildingName;
    @JsonProperty("StreetType")
    private String streetType;
    @JsonProperty("StreetTypeFull")
    private String streetTypeFull;
    @JsonProperty("UnitType")
    private Object unitType;
    @JsonProperty("UnitTypeFull")
    private Object unitTypeFull;
    @JsonProperty("UnitNumber")
    private Object unitNumber;
    @JsonProperty("LevelType")
    private Object levelType;
    @JsonProperty("LevelTypeFull")
    private Object levelTypeFull;
    @JsonProperty("LevelNumber")
    private Object levelNumber;
    @JsonProperty("Postcode")
    private String postcode;
    @JsonProperty("Longitude")
    private Double longitude;
    @JsonProperty("Latitude")
    private Double latitude;
    @JsonProperty("Street_Longitude")
    private Double streetLongitude;
    @JsonProperty("Street_Latitude")
    private Double streetLatitude;
    @JsonProperty("Suburb_Longitude")
    private Double suburbLongitude;
    @JsonProperty("Valid")
    private Boolean valid;

    @JsonProperty("AddressFull")
    public String getAddressFull() {
        return addressFull;
    }

    @JsonProperty("AddressFull")
    public void setAddressFull(String addressFull) {
        this.addressFull = addressFull;
    }

    @JsonProperty("AddressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("AddressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("AddressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("AddressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty("NumberFirst")
    public String getNumberFirst() {
        return numberFirst;
    }

    @JsonProperty("NumberFirst")
    public void setNumberFirst(String numberFirst) {
        this.numberFirst = numberFirst;
    }

    @JsonProperty("NumberLast")
    public Object getNumberLast() {
        return numberLast;
    }

    @JsonProperty("NumberLast")
    public void setNumberLast(Object numberLast) {
        this.numberLast = numberLast;
    }

    @JsonProperty("StreetSuffix")
    public Object getStreetSuffix() {
        return streetSuffix;
    }

    @JsonProperty("StreetSuffix")
    public void setStreetSuffix(Object streetSuffix) {
        this.streetSuffix = streetSuffix;
    }

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("Street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("Street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("StreetLine")
    public String getStreetLine() {
        return streetLine;
    }

    @JsonProperty("StreetLine")
    public void setStreetLine(String streetLine) {
        this.streetLine = streetLine;
    }

    @JsonProperty("BuildingName")
    public String getBuildingName() {
        return buildingName;
    }

    @JsonProperty("BuildingName")
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @JsonProperty("StreetType")
    public String getStreetType() {
        return streetType;
    }

    @JsonProperty("StreetType")
    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    @JsonProperty("StreetTypeFull")
    public String getStreetTypeFull() {
        return streetTypeFull;
    }

    @JsonProperty("StreetTypeFull")
    public void setStreetTypeFull(String streetTypeFull) {
        this.streetTypeFull = streetTypeFull;
    }

    @JsonProperty("UnitType")
    public Object getUnitType() {
        return unitType;
    }

    @JsonProperty("UnitType")
    public void setUnitType(Object unitType) {
        this.unitType = unitType;
    }

    @JsonProperty("UnitTypeFull")
    public Object getUnitTypeFull() {
        return unitTypeFull;
    }

    @JsonProperty("UnitTypeFull")
    public void setUnitTypeFull(Object unitTypeFull) {
        this.unitTypeFull = unitTypeFull;
    }

    @JsonProperty("UnitNumber")
    public Object getUnitNumber() {
        return unitNumber;
    }

    @JsonProperty("UnitNumber")
    public void setUnitNumber(Object unitNumber) {
        this.unitNumber = unitNumber;
    }

    @JsonProperty("LevelType")
    public Object getLevelType() {
        return levelType;
    }

    @JsonProperty("LevelType")
    public void setLevelType(Object levelType) {
        this.levelType = levelType;
    }

    @JsonProperty("LevelTypeFull")
    public Object getLevelTypeFull() {
        return levelTypeFull;
    }

    @JsonProperty("LevelTypeFull")
    public void setLevelTypeFull(Object levelTypeFull) {
        this.levelTypeFull = levelTypeFull;
    }

    @JsonProperty("LevelNumber")
    public Object getLevelNumber() {
        return levelNumber;
    }

    @JsonProperty("LevelNumber")
    public void setLevelNumber(Object levelNumber) {
        this.levelNumber = levelNumber;
    }

    @JsonProperty("Postcode")
    public String getPostcode() {
        return postcode;
    }

    @JsonProperty("Postcode")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @JsonProperty("Longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("Longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("Latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("Latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("Street_Longitude")
    public Double getStreetLongitude() {
        return streetLongitude;
    }

    @JsonProperty("Street_Longitude")
    public void setStreetLongitude(Double streetLongitude) {
        this.streetLongitude = streetLongitude;
    }

    @JsonProperty("Street_Latitude")
    public Double getStreetLatitude() {
        return streetLatitude;
    }

    @JsonProperty("Street_Latitude")
    public void setStreetLatitude(Double streetLatitude) {
        this.streetLatitude = streetLatitude;
    }

    @JsonProperty("Suburb_Longitude")
    public Double getSuburbLongitude() {
        return suburbLongitude;
    }

    @JsonProperty("Suburb_Longitude")
    public void setSuburbLongitude(Double suburbLongitude) {
        this.suburbLongitude = suburbLongitude;
    }

    @JsonProperty("Valid")
    public Boolean getValid() {
        return valid;
    }

    @JsonProperty("Valid")
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

}