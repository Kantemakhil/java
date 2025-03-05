package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.JsonSpecification;

public interface JoltConverterRepository {

	JsonSpecification getJsonSpecData(String specKey);

    List<String> getAllSpecKey();

    Boolean findJsonExist(String specKey);

    Integer insertJsonData(JsonSpecification jsonSpecification);

    Integer updateJsonData(JsonSpecification jsonSpecification);	
	
}