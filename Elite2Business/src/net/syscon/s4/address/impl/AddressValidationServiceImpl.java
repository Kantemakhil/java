package net.syscon.s4.address.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.address.AddressDTO;
import net.syscon.s4.address.AddressValidationService;
import net.syscon.s4.address.factory.AddressType;
import net.syscon.s4.address.factory.AddressValidation;
import net.syscon.s4.address.factory.AddressValidationFactory;

@Service
public class AddressValidationServiceImpl implements AddressValidationService {

    @Autowired
    AddressValidationFactory addressValidationFactory;


    @Override
    public List<String> suggestionsAutoComplete(String term,String state) {
        AddressValidation addressValidation = addressValidationFactory.createAddressValidation(AddressType.ADDRESSIFY);
        return addressValidation.suggestionsAutoComplete(term,state);
    }

    @Override
    public AddressDTO addressInfo(String term) {
        AddressValidation addressValidation = addressValidationFactory.createAddressValidation(AddressType.ADDRESSIFY);
        return addressValidation.validateAddress(term);
    }

}
