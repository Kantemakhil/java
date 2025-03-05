package net.syscon.s4.address;

import java.util.List;

public interface AddressValidationService {

    List<String> suggestionsAutoComplete(String term,String state);

    AddressDTO addressInfo(String term);

}
