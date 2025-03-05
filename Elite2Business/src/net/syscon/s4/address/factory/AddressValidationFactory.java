package net.syscon.s4.address.factory;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressValidationFactory {

    private EnumMap<AddressType, AddressValidation> mapValidationServices;

    @Autowired
    private AddressValidationFactory(List<AddressValidation> listAddressValidators) {
        this.mapValidationServices = new EnumMap<>(AddressType.class);
        listAddressValidators.stream().forEach((add) -> {
            mapValidationServices.put(add.getType(), add);
        });
    }

    public  AddressValidation createAddressValidation(AddressType addressType) {
        return Optional.ofNullable(mapValidationServices.get(addressType)).orElseThrow(IllegalArgumentException::new);
    }

}
