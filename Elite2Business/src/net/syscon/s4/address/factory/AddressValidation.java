package net.syscon.s4.address.factory;

import java.util.List;

import net.syscon.s4.address.AddressDTO;

public interface AddressValidation {
    
    public AddressDTO validateAddress(String term);
    
    public List<String> suggestionsAutoComplete(String term,String state);
    
    public AddressType getType();

    
}
