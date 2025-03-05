package net.syscon.s4.inst.incidentsoic.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReferenceCodesCommitBean;
import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.globalconfiguration.OumrcodeRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OimsrlucRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OimsrlucService;

@Service
public class OimsrlucServiceImpl implements OimsrlucService {

	
	private static Logger logger = LogManager.getLogger(OimsrlucServiceImpl.class.getName());
	
	@Autowired
	private OimsrlucRepository oimsrlucRepository;

	@Autowired
	private OumrcodeRepository oumrcodeRepository;

	@Override
	public List<ReferenceDomains> refDmnExcuteQuery() {
			List<ReferenceDomains> recordList = new ArrayList<ReferenceDomains>();
			recordList = oimsrlucRepository.refDmnExcuteQuery();
			return recordList;
		}
	
	@Override
	public List<ReferenceCodes> refCodeExecuteQuery(ReferenceCodes searchRecord) {
		List<ReferenceCodes> filteredList = null;
		List<ReferenceCodes> list = oumrcodeRepository.refCodeExecuteQuery(searchRecord);
		logger.info(this.getClass().getName() + " refCodeExecuteQuery");
		if (list.size() > 0 && !list.isEmpty()) {
			
			 filteredList = list.stream()
					.filter(e ->e.getActiveFlag()!= null && e.getActiveFlag().equalsIgnoreCase("Y")
							)
					.collect(Collectors.toList());
			
			filteredList.forEach(bo -> {

				if(bo!=null && bo.getCode()!=null) {
					bo.setReportType(bo.getCode());
				}
			
				ReferenceCodes bean = refCodeCondExecuteQuery(bo.getCode());
				if (bean != null && bean.getAutomaticFlag() != null) {
					bo.setAutomaticFlag(bean.getAutomaticFlag());
				}
				if (bean != null && bean.getLength() != null) {
					bo.setLength(bean.getLength());
				}
				
				if (bean != null && bean.getLengthUnit() != null) {
					bo.setLengthUnit(bean.getLengthUnit());
				}
				
			});

		}
		
		return filteredList;

		
	}
	
	@Override
	public ReferenceCodes refCodeCondExecuteQuery(final String reportType) {
		return oimsrlucRepository.refCodeCondExecuteQuery(reportType);
	}
	
	@Transactional
	public Integer refCodeCondCommit(ReferenceCodesCommitBean commitBean) {
		int liReturn = 0;
		if(commitBean.getUpdateList()!=null && commitBean.getUpdateList().size()>0) {
			commitBean.getUpdateList().forEach(bo->{
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (ReferenceCodes obj : commitBean.getUpdateList()) {
				ReferenceCodes bean = refCodeCondExecuteQuery(obj.getCode());
				logger.info(this.getClass().getName() + " refCodeCondExecuteQuery");
				if (bean != null && bean.getReportType() != null) {
					liReturn = oimsrlucRepository.refCodeCondUpdateReference(commitBean.getUpdateList());
					logger.info(this.getClass().getName() + " refCodeCondUpdateReference");
				} else {
					liReturn = oimsrlucRepository.refCodeCondInsertReference(commitBean.getUpdateList());
					logger.info(this.getClass().getName() + " refCodeCondInsertReference");
				}
			}
		}
		
		return liReturn;
	}

	@Override
	public List<ReferenceCodes> unitLovExecuteQuery() {
		List<ReferenceCodes> list = new ArrayList<ReferenceCodes>();
		List<ReferenceCodes> listTemp = new ArrayList<ReferenceCodes>();
		list = oimsrlucRepository.unitLovExecuteQuery();
		if (list != null && list.size() > 0) {
			for (ReferenceCodes bean : list) {
				if ((bean.getCode().equals("DAYS"))) {
					listTemp.add(bean);
				}
				if ((bean.getCode().equals("HOURS"))) {
					listTemp.add(bean);
				}
				if ((bean.getCode().equals("MONTHS"))) {
					listTemp.add(bean);
				}
			}
		}
		return listTemp;
	}
}

