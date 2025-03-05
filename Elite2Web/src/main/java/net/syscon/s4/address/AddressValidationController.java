package net.syscon.s4.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;

@EliteController
public class AddressValidationController {
   
    @Autowired
    private AddressValidationService addressService;
    
    @GetMapping("/address/autocomplete")
    public List<String> suggestAutocomplete(@RequestParam(name = "term") String term,@RequestParam(name = "state") String state){
        return addressService.suggestionsAutoComplete(term,state);
    }
    @GetMapping("/address/info")
    public AddressDTO addressInfo(@RequestParam(name = "term") String term){
        return addressService.addressInfo(term);
    }
    
}

