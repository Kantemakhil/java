package net.syscon.s4.cf.deductions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcufovdtRepository;
import net.syscon.s4.cf.deductions.OcufovdtService;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetailsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.triggers.OffFeeAccountOverridesThtyService;

@Service
public class OcufovdtServiceImpl extends BaseBusiness implements OcufovdtService {

    @Autowired
    private OcufovdtRepository ocufovdtRepository;
    
    @Autowired
    private OffFeeAccountOverridesThtyService offFeeAccountOverridesThtyService;

    @Override
    public List<ReferenceCodes> overrideTypeRecordGroup() {
        return ocufovdtRepository.overrideTypeRecordGroup();
    }

    @Override
    public FeeAccountProfiles feeActExecuteQuery(final FeeAccountProfiles searchBean) {
        final FeeAccountProfiles returnData = new FeeAccountProfiles();
        returnData.setFeeCode(ocufovdtRepository.getDeductionDesc(searchBean.getFeeCode()));
        returnData.setCode(ocufovdtRepository.getFrequency(searchBean.getFrequencyCode()));
        returnData.setLocation(ocufovdtRepository.getLocation(searchBean.getCaseloadId()));
        return returnData;
    }

    @Override
    public List<FeeOverrideDetails> feeOverdDetExecuteQuery(final FeeOverrideDetails searchBean) {
        List<FeeOverrideDetails> returnData =  ocufovdtRepository.feeOverdDetExecuteQuery(searchBean);
        returnData.forEach( obj -> {
            if(Optional.ofNullable(obj).isPresent()){
                obj.setModifyUserId(ocufovdtRepository.getUsername());
                obj.setAddedBy(ocufovdtRepository.getUserID(obj.getAddedByStaffId()));
            }
        });
        return returnData;
    }

    @Transactional
    @Override
    public Integer feeOverdDetCommit(final FeeOverrideDetailsCommitBean commitBean) {
        int liReturn = 0;
        // insertRecords
        if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
            for (FeeOverrideDetails object : commitBean.getInsertList()) {
            	Long adddedStaffId=ocufovdtRepository.getAddedByStaffId(object);
            	object.setAddedByStaffId(adddedStaffId);
            	object.setCreateUserId(commitBean.getCreateUserId());
            	object.setOperation("INS");
                object.setOffFeeOverrideId(ocufovdtRepository.preInsertFeeOverrideId());
            }
            liReturn = ocufovdtRepository.insertFeeOvrdDet(commitBean.getInsertList());
            for (FeeOverrideDetails Object : commitBean.getInsertList()) {
            	offFeeAccountOverridesThtyService.OffFeeAccountOvverRideHistory(Object);
			}
            
        }
        // updateRecords
        if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
        	for (FeeOverrideDetails obj : commitBean.getUpdateList()) {
        		obj.setOperation("UPD");
        		obj.setModifyUserId(commitBean.getCreateUserId());
			}
            liReturn = ocufovdtRepository.updateFeeOvrdDet(commitBean.getUpdateList());
            for (FeeOverrideDetails Object : commitBean.getUpdateList()) {
            	offFeeAccountOverridesThtyService.OffFeeAccountOvverRideHistory(Object);
			}
        }
        // deleteRecords
        if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
        	for (FeeOverrideDetails obj : commitBean.getDeleteList()) {
        		obj.setOperation("DEL");
        		obj.setModifyUserId(commitBean.getCreateUserId());
			}
            liReturn = ocufovdtRepository.deleteFeeOvrdDet(commitBean.getDeleteList());
            for (FeeOverrideDetails Object : commitBean.getDeleteList()) {
            	offFeeAccountOverridesThtyService.OffFeeAccountOvverRideHistory(Object);
			}
        }
        return liReturn;
    }

    @Override
    public Integer sysPflExecuteQuery(String userName) {
        Integer count;
        SystemProfiles returnedObject=new SystemProfiles();
        List<SystemProfiles> returnList=new ArrayList<>();
        returnList=ocufovdtRepository.sysPflExecuteQuery();
        if(!returnList.isEmpty()) {
            returnedObject = returnList.get(0);
            if("Y".equals(returnedObject.getProfileValue())) {
                List<String> roleIdList = ocufovdtRepository.getRoleIdList(userName);
                for (String string : roleIdList) {
                	if(string!=null && returnedObject.getProfileValue2()!=null) {
                	if(string.equals(returnedObject.getProfileValue2())) {
                		  return 1;
                	}
                	}
//                    if(Integer.valueOf(string) == Integer.valueOf(returnedObject.getProfileValue2())) {
//                        return 1;
//                    }

                }
                return 0;
            } else {
                return 1;
            }
        }
        return 1;

    }


    @Override
    public String getAddedByName() {
        return ocufovdtRepository.getAddedByName();
    }

    @Override
    public FeeOverrideDetails feeOverCheckoverLapping(FeeOverrideDetails searchBean) {
        List<FeeOverrideDetails> returnList=new ArrayList<>();
        FeeOverrideDetails returnObject=new FeeOverrideDetails();
        returnList =ocufovdtRepository.feeOverCheckoverLapping(searchBean);
        if(!returnList.isEmpty()) {
            returnObject = returnList.get(0);
        }
        return returnObject;
    }


}
