package net.syscon.s4.sa.audit;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagAuditFormGettabledetail;

/**
 * OuiadactController 
 */
@EliteController
public class OuiadactController {
@Autowired
private OuiadactService ouiadactService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OuiadactController.class.getName());
	/**
	 *getting rgTableName LOV values 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ouiadact/rgTableNameRecordGroup",method=RequestMethod.GET)
	public List<ReferenceCodes> rgTableNameRecordGroup() {
		List<ReferenceCodes> recordList =new ArrayList<ReferenceCodes>();
		try {
			recordList = ouiadactService.rgTableNameRecordGroup();
		} catch(Exception e){
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ouiadact:",e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ouiadact/getTableDetailExecuteQuery", method=RequestMethod.POST)
	public List<TagAuditFormGettabledetail> getTableDetailExecuteQuery(@RequestBody final TagAuditFormGettabledetail searchBean) {
		List<TagAuditFormGettabledetail> searchResult = new ArrayList<>();
		try {
			searchResult = ouiadactService.getTableDetailExecuteQuery(searchBean);
		} catch (Exception e) {
			final TagAuditFormGettabledetail bean = new TagAuditFormGettabledetail();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ouiadact/getStaffName", method=RequestMethod.POST)
	public TagAuditFormGettabledetail getStaffName(@RequestBody final TagAuditFormGettabledetail searchBean) {
		TagAuditFormGettabledetail searchResult = new TagAuditFormGettabledetail();
		try {
			searchResult = ouiadactService.getStaffName(searchBean);
		} catch (Exception e) {
			final TagAuditFormGettabledetail bean = new TagAuditFormGettabledetail();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			
		}
		return searchResult;
	}

}