package net.syscon.s4.sa.audit.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.TagAuditFormGettabledetail;
import net.syscon.s4.sa.audit.OuiadactRepository;
import net.syscon.s4.sa.audit.OuiadactService;

/**
 * Class OuiadactServiceImpl
 */
@Service
public class OuiadactServiceImpl extends BaseBusiness implements OuiadactService {

	@Autowired
	private OuiadactRepository ouiadactRepository;

	public List<TagAuditFormGettabledetail> getTableDetailExecuteQuery(final TagAuditFormGettabledetail searchRecord) {
		return ouiadactRepository.getTableDetailExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgTableNameRecordGroup() {
		return ouiadactRepository.rgTableNameRecordGroup();

	}
	
	public TagAuditFormGettabledetail getStaffName(final TagAuditFormGettabledetail object) {	
		object.setStaffName(ouiadactRepository.getStaffName(object));
		return object;
	}

}