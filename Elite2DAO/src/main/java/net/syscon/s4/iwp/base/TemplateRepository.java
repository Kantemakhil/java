package net.syscon.s4.iwp.base;

import java.util.List;

import net.syscon.s4.im.beans.IwpTemplates;

public interface TemplateRepository {


	List<IwpTemplates> getAllIWPTemplateList();


	//Method to get all templateIds associated with given Module
	List<IwpTemplates> getIWPTemplateList(String moduleName);


	//Method to get all templateIds associated with given Module
	IwpTemplates getIWPTemplate(String templateId);


	List<IwpTemplates> getIWPTemplatesByModule(String moduleName);


	IwpTemplates getIWPTemplateByName(String templateName);


}
