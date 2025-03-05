package net.syscon.s4.pkgs.tag_audit.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_audit.TagAuditRepository;
import net.syscon.s4.pkgs.tag_audit.TagAuditService;
import net.syscon.s4.sa.audit.SysTagAuditFormGetUserDetail;
import net.syscon.s4.sa.audit.SysTagAuditFormGetsessiondetail;
import net.syscon.s4.sa.audit.maintenance.AllAuditPolicies;

@Service
public class TagAuditServiceImpl implements TagAuditService {

	@Autowired
	private TagAuditRepository tagAuditRepository;

	private static Logger logger = LogManager.getLogger(TagAuditServiceImpl.class.getName());

	private static Date gvDatefrom = null;
	private static Date gvDateto = null;
	private static Date gvTimefrom = null;
	private static Date gvTimeto = null;

	@Override
	public Integer disableAll(final String pPolicyName) {
		List<AllAuditPolicies> policiesList = null;

		try {
			policiesList = tagAuditRepository.allAuditPoliciesSelectOperation(pPolicyName);
			for (final AllAuditPolicies allAuditPolicies : policiesList) {

				// TODO this is from audit
				/*
				 * DBMS_FGA.disable_policy (object_schema => rec.object_schema, object_name =>
				 * rec.object_name, policy_name => rec.policy_name );
				 */
			}
		} catch (Exception e) {
			logger.error("disableAll", e.getMessage());
			return 0;
		}
		return 1;
	}

	@Override
	public Integer createPolicy(AllAuditPolicies searchBean, final String statType) {
		Integer retVal = 0;
		try {
			// TODO this is from audit
			/*
			 * DBMS_FGA.add_policy (object_schema => c_logic_owner, object_name =>
			 * p_object_name, policy_name => c_policy_all, audit_condition => p_conditions,
			 * audit_column => NULL, handler_schema => NULL, handler_module => NULL, ENABLE
			 * => TRUE, statement_types => p_stat_type, audit_trail => DBMS_FGA.db +
			 * DBMS_FGA.extended, audit_column_opts => DBMS_FGA.any_columns );
			 */
			retVal = 1;
		} catch (Exception e) {
			logger.error("createPolicy", e.getMessage());
		}
		return retVal;
	}

	@Override
	public Integer dropPolicy(final AllAuditPolicies searchBean) {
		Integer retVal = 0;
		try {

			// TODO This is from audit
			/*
			 * DBMS_FGA.drop_policy (object_schema => c_logic_owner, object_name =>
			 * p_object_name, policy_name => p_policy_name );
			 */
			retVal = 1;
		} catch (Exception e) {
			logger.error("dropPolicy", e.getMessage());
		}
		return retVal;
	}

	@Override
	public Integer enableAll(final String pPolicyName) {
		List<AllAuditPolicies> policiesList = new ArrayList<AllAuditPolicies>();
		Integer retVal = 0;
		try {
			policiesList = tagAuditRepository.allAuditPolicies(pPolicyName);
			for (final AllAuditPolicies allAuditPolicies : policiesList) {
				// TODO this is from audit
				/*
				 * DBMS_FGA.enable_policy (object_schema => rec.object_schema, object_name =>
				 * rec.object_name, policy_name => rec.policy_name );
				 */
			}
			retVal = 1;
		} catch (Exception e) {
			logger.error("enableAll", e.getMessage());
		}
		return retVal;
	}

	@Override
	public Integer dropAll(final String pPolicyName) {
		List<AllAuditPolicies> policiesList = new ArrayList<AllAuditPolicies>();
		Integer retVal = 0;

		try {
			policiesList = tagAuditRepository.dropAllSelectOperation(pPolicyName);
			for (final AllAuditPolicies allAuditPolicies : policiesList) {
				// TODO this is from audit
				/*
				 * DBMS_FGA.drop_policy (object_schema => rec.object_schema, object_name =>
				 * rec.object_name, policy_name => rec.policy_name );
				 */
			}
			retVal = 1;
		} catch (Exception e) {
			logger.error("dropAll", e.getMessage());
		}
		return retVal;
	}

	@Override
	public Integer createAll(final String pOwner) {
		Integer retVal = 0;

		try {
			final List<AllAuditPolicies> objNames = tagAuditRepository.getObjNames(pOwner);

			// TODO this is from audit
			/*
			 * FOR rec IN listtab (c_logic_owner) LOOP DBMS_FGA.add_policy (object_schema =>
			 * c_logic_owner, object_name => rec.object_name, policy_name => c_policy_all,
			 * audit_condition => NULL, audit_column => NULL, handler_schema => NULL,
			 * handler_module => NULL, ENABLE => TRUE, statement_types => 'INSERT, UPDATE,
			 * DELETE, SELECT', audit_trail => DBMS_FGA.db + DBMS_FGA.extended,
			 * audit_column_opts => DBMS_FGA.any_columns ); END LOOP;
			 * 
			 */
			retVal = 1;
		} catch (Exception e) {
			logger.error("createAll", e.getMessage());
		}
		return retVal;

	}

	@Override
	public Map<String, List<SysTagAuditFormGetsessiondetail>> formGetsessiondetail(final Long psessionid) {
		final Map<String, List<SysTagAuditFormGetsessiondetail>> returnMap = new HashedMap<String, List<SysTagAuditFormGetsessiondetail>>();
		final List<SysTagAuditFormGetsessiondetail> list = getsessiondetails(psessionid);
		returnMap.put("FORM_CURSOR", list);
		return returnMap;
	}

	@Override
	public List<SysTagAuditFormGetsessiondetail> getsessiondetails(final Long psessionid) {
		// TODO TODO this is from audit
		return null;
	}

	@Override
	public List<SysTagAuditFormGetUserDetail> formGetuserdetail(final SysTagAuditFormGetUserDetail bean) {
		final List<SysTagAuditFormGetUserDetail> retList = null;
		Date timefrom = null;
		Date timeto = null;
		Object outPutFrmGud;
		try {
			if (Optional.ofNullable(bean.getFromDate()).isPresent()
					&& Optional.ofNullable(bean.getToDate()).isPresent()) {
				timefrom = bean.getFromDate();
				timeto = bean.getToDate();
				gvDatefrom = bean.getFromDate();
				gvDateto = bean.getToDate();
				gvTimefrom = bean.getFromTime();
				gvTimeto = bean.getToTime();
			}
			// getuserdetails (p_username) 315
			outPutFrmGud = getUserDetails(bean.getUserId());
			
			//TODO
			//form_cursor need to migrate because of getuserdetails function

		} catch (Exception e) {
			logger.error("formGetuserdetail", e);
		}
		return retList;
	}

	@Override
	public Object getUserDetails(final String userId) {
		//TODO
		// getuserdetails function contains multiple not understandable statements
		return null;
	}

	@Override
	public Boolean checkArray(final Object myArray, final Integer pIndex) {
		// TODO 
		// myArray dataType is bubble_t  
		 return null;
	}


}