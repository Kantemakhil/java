package net.syscon.s4.inst.casemanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * class OiuiwpgnController 
 */
@EliteController
public class OiuiwpgnController {
@Autowired
private OiuiwpgnService oiuiwpgnService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiuiwpgnController.class.getName());
	/**
	 *getting rgStatus LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oiuiwpgn/rgStatusRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = oiuiwpgnService.rgStatusRecordGroup();
		} catch(Exception e){
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgStatusRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}



}