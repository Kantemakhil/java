package net.syscon.s4.address.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.syscon.s4.address.AddressDTO;


@Service
public class GoogleAddressValidation implements AddressValidation {
    

    @Override
    public AddressDTO validateAddress(String term) {
        return new AddressDTO();
    }

    @Override
    public AddressType getType() {
        return AddressType.GOOGLE;
    }

    @Override
    public List<String> suggestionsAutoComplete(String term,String state) {
        return new ArrayList<>(1);
    }

}
