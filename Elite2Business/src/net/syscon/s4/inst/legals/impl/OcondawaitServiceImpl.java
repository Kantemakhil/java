package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.OcondawaitRepository;
import net.syscon.s4.inst.legals.OcondawaitService;
import net.syscon.s4.inst.legals.beans.OffenderAllocationsSentences;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.inst.legals.beans.OffenderCondTransferCommitBean;

@Service
public class OcondawaitServiceImpl implements OcondawaitService {

	private static Logger logger = LogManager.getLogger(OcondawaitServiceImpl.class.getName());

	@Autowired
	private OcondawaitRepository ocondawaitRepository;

	@Override
	public List<ReferenceCodes> rgLocationRecGroup(String caseLoadId) {
		return ocondawaitRepository.rgLocationRecGroup(caseLoadId);
	}

	@Override
	public List<Teams> rgTeamRecGroup(String caseLoadId, String userName) {
		List<Teams> teamsList = ocondawaitRepository.rgTeamRecGroup(caseLoadId, userName);
		if(teamsList != null && teamsList.size() > 0) {
			teamsList.forEach(e -> {
				if(!("Y".equals(e.getActiveFlag()) && caseLoadId.equals(e.getAgyLocId()))) {
					e.setCanDisplay(false);
				}
			});
		}
		return teamsList;
	}
	
	/**
	 * Updating INST initial conditons of an offender to Community Intake Location
	 */
	private void updateInstCondtionsToCOMM() {
		try {
			List<OffenderCondTransfer> instConditionsList = ocondawaitRepository.getInstConditionsForOffenders();
			if(!instConditionsList.isEmpty()) {
				ocondawaitRepository.updateInstConditonsToCOMM(instConditionsList);
			}
		} catch (Exception e) {
			logger.error("Exception in OcondawaitServiceImpl class updateInstCondtionsToCOMM method", e.getMessage());
		}
		
	}

	@Override
	public List<OffenderAllocationsSentences> getSentenceData(OffenderAllocationsSentences searchBean) {
		List<OffenderCondTransfer> SetencetList = new ArrayList<OffenderCondTransfer>();
		List<OffenderAllocationsSentences> returnList = new ArrayList<OffenderAllocationsSentences>();
		try {
			updateInstCondtionsToCOMM();
			SetencetList = ocondawaitRepository.getSentenceData(searchBean);
			for (OffenderCondTransfer obj : SetencetList) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
				if (map != null && map.get("myJsonRowData") != null) {
					List<Map<String, Object>> Sentencelist = new ArrayList();
					Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
					for (Map<String, Object> sentenceObj : Sentencelist) {
						Integer sentenceSeq = null;
						try {
							sentenceSeq = (Integer) sentenceObj.get("orderNo");
						} catch (Exception e) {
							logger.error("Exception in OcondawaitServiceImpl class getSentenceData method while converting OrderNo to Integer", e.getMessage());
						}
						if (sentenceSeq != null && sentenceSeq == obj.getSentenceSeq()) {
							List<OffenderAllocationsSentences> filteredList = returnList.stream()
									.filter(e -> e.getOffenderBookId().equals(obj.getOffenderBookId())
											&& e.getSentenceSeq() == (Integer) sentenceObj.get("orderNo") && 
											e.getOrderType().equalsIgnoreCase((String)sentenceObj.get("orderType")))
									.collect(Collectors.toList());
							if (filteredList != null && filteredList.size() == 0 && (String)sentenceObj.get("orderType") != null) {
								OffenderAllocationsSentences offenderAllocationsSentences = new OffenderAllocationsSentences();
								offenderAllocationsSentences.setOffenderBookId(obj.getOffenderBookId());
								offenderAllocationsSentences.setNo((Integer) sentenceObj.get("orderNo"));
								offenderAllocationsSentences.setSentenceSeq((Integer) sentenceObj.get("orderNo"));
								offenderAllocationsSentences.setOffenderIdDisplay(obj.getOffenderIdDisplay());
								offenderAllocationsSentences.setLastName(obj.getLastName());
								offenderAllocationsSentences.setFirstName(obj.getFirstName());
								offenderAllocationsSentences.setCommenceType((String) sentenceObj.get("sentenceCalcType"));
								offenderAllocationsSentences.setMatter((String) sentenceObj.get("matter"));
								offenderAllocationsSentences.setOrderType((String) sentenceObj.get("orderType"));
								
								returnList.add(offenderAllocationsSentences);
							}

						}
					}

				}

			}
		} catch (Exception e) {
			logger.error("Exception in OcondawaitServiceImpl class getSentenceData method", e.getMessage());
		}

		return returnList;
	}

	@Override
	public List<OffenderSentConditions> getAwaitingConditions(OffenderAllocationsSentences searchBean) {
		List<OffenderSentConditions> returnList = new ArrayList<OffenderSentConditions>();
		List<OffenderSentConditions> finalList = new ArrayList<OffenderSentConditions>();
		try {
			returnList = ocondawaitRepository.checkCondExitsIntkAgyLocId(searchBean);
			if(searchBean.getTeamId() != null) {
				for(OffenderSentConditions obj : returnList) {
					if(obj.getParentCondTransferId() == null) {
						finalList.add(obj);
					}else if(obj.getTeamId() != null || obj.getToTeamId() != null) {
						finalList.add(obj);
					}
				}
			}else {
				finalList.addAll(returnList);
			}
		} catch (Exception e) {
			logger.error("Exception in OcondawaitServiceImpl class getAwaitingConditions method", e.getMessage());
		}

		return finalList;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<OffenderCondTransfer> offenderCondTransferCommit(OffenderCondTransferCommitBean commitBean) {
		final List<OffenderCondTransfer> liReturnData = new ArrayList<>();
		final OffenderCondTransfer sentenceCalcTypes = new OffenderCondTransfer();
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(e -> e.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = ocondawaitRepository.offenderCondTransferInsert(commitBean.getInsertList());
			if( liReturn > 0) {
				logger.info(this.getClass().getName()+" offenderCondTransferInsertCasePlan method call");
				commitBean.getInsertList().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
				ocondawaitRepository.offenderCondTransferInsertCasePlan(commitBean.getInsertList());
			}

		}
		sentenceCalcTypes.setListSeq(BigDecimal.valueOf(liReturn));
		liReturnData.add(sentenceCalcTypes);
		return liReturnData;
	}

	@Override
	public List<ReferenceCodes> getStaffDetails(String caseLoadId) {
		return ocondawaitRepository.getStaffDetails(caseLoadId);
	}

	@Override
	public List<ReferenceCodes> getTeamMemberDetails(Integer teamId) {
		return ocondawaitRepository.getTeamMemberDetails(teamId);
	}

	@Override
	public List<OffenderCondTransfer> getAssignedConditions(OffenderAllocationsSentences searchBean) {
		return ocondawaitRepository.getAssignedConditions(searchBean);
	}

	@Override
	public List<OffenderAllocationsSentences> getAssignedCondOffenders(OffenderAllocationsSentences searchBean) {
		List<OffenderAllocationsSentences> returnList = new ArrayList<OffenderAllocationsSentences>();
		try {
			List<OffenderAllocationsSentences> assignedOffenders = ocondawaitRepository
					.getAssignedCondOffenders(searchBean);
			for (OffenderAllocationsSentences obj : assignedOffenders) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
				ObjectMapper mapper = new ObjectMapper();
				if (returnList != null && returnList.size() > 0) {
					int count = 0;
					for (OffenderAllocationsSentences obj1 : returnList) {
						String orderType = null;
						if(obj1.getOrderType().equals("NCUS")) {
							orderType = "NONCUST";
						}else if(obj1.getOrderType().equals("PAR") || obj1.getOrderType().equals("BAIL")) {
							orderType = obj1.getOrderType();
						}else {
							orderType = "CUST";
						}
						if (obj.getOffenderBookId().equals(obj1.getOffenderBookId())
								&& obj.getSentenceSeq() == obj1.getSentenceSeq() && obj.getOrderType().equals(orderType) ) {
							count += 1;
						}
					}
					if (count > 0) {
						continue;
					}
				}

				Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
				if (map != null && map.get("myJsonRowData") != null) {
					List<Map<String, Object>> Sentencelist = new ArrayList();
					Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
					for (Map<String, Object> sentenceObj : Sentencelist) {
						if ((Integer) sentenceObj.get("orderNo") != obj.getSentenceSeq()) {
							continue;
						}
						OffenderAllocationsSentences offenderAllocationsSentences = new OffenderAllocationsSentences();
						offenderAllocationsSentences.setOffenderBookId(obj.getOffenderBookId());
						offenderAllocationsSentences.setNo((Integer) sentenceObj.get("orderNo"));
						offenderAllocationsSentences.setSentenceSeq((Integer) sentenceObj.get("orderNo"));
						offenderAllocationsSentences.setIntakeAgyLocId(obj.getIntakeAgyLocId());
						offenderAllocationsSentences.setOffenderIdDisplay(obj.getOffenderIdDisplay());
						offenderAllocationsSentences.setLastName(obj.getLastName());
						offenderAllocationsSentences.setFirstName(obj.getFirstName());
						offenderAllocationsSentences.setCommenceType((String) sentenceObj.get("sentenceCalcType"));
						offenderAllocationsSentences.setMatter((String) sentenceObj.get("matter"));
						offenderAllocationsSentences.setConTransferId(obj.getConTransferId());
						offenderAllocationsSentences.setOrderType((String) sentenceObj.get("orderType"));
						returnList.add(offenderAllocationsSentences);
					}

				}
			}
		} catch (Exception e) {
			logger.error("Exception in OcondawaitServiceImpl class getSentenceData method", e.getMessage());
		}
		return returnList;
	}

	@Override
	public List<OffenderAllocationsSentences> getTransferredCondOffenders(OffenderAllocationsSentences searchBean) {
		List<OffenderAllocationsSentences> returnList = new ArrayList<OffenderAllocationsSentences>();
		try {
			List<OffenderAllocationsSentences> assignedOffenders = ocondawaitRepository
					.getTransferredCondOffenders(searchBean);
			for (OffenderAllocationsSentences obj : assignedOffenders) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
				ObjectMapper mapper = new ObjectMapper();

				Map<String, Object> map = mapper.readValue(obj.getFormInfoJson(), Map.class);
				if (map != null && map.get("myJsonRowData") != null) {
					List<Map<String, Object>> Sentencelist = new ArrayList();
					Sentencelist = (List<Map<String, Object>>) map.get("myJsonRowData");
					for (Map<String, Object> sentenceObj : Sentencelist) {
						OffenderAllocationsSentences offenderAllocationsSentences = new OffenderAllocationsSentences();
						offenderAllocationsSentences.setOffenderBookId(obj.getOffenderBookId());
						offenderAllocationsSentences.setNo((Integer) sentenceObj.get("orderNo"));
						if (obj.getSentenceSeq() == offenderAllocationsSentences.getNo()) {
							int count = 0;
							if(returnList != null && returnList.size() > 0) {
								
								final String orderType = obj.getOrderType().equals("NONCUST") ? "NCUS" : (obj.getOrderType().equals("PAR") || obj.getOrderType().equals("BAIL")) ? obj.getOrderType(): "CUST";
							 count = 	returnList.stream().filter(e -> e.getOrderType().equals(orderType) && e.getOffenderBookId().equals(obj.getOffenderBookId()) && obj.getSentenceSeq() == e.getNo()).collect(Collectors.toList()).size();
							}
							if(count == 0) {
							offenderAllocationsSentences.setIntakeAgyLocId(obj.getIntakeAgyLocId());
							offenderAllocationsSentences.setOffenderIdDisplay(obj.getOffenderIdDisplay());
							offenderAllocationsSentences.setLastName(obj.getLastName());
							offenderAllocationsSentences.setFirstName(obj.getFirstName());
							offenderAllocationsSentences.setCommenceType((String) sentenceObj.get("sentenceCalcType"));
							offenderAllocationsSentences.setMatter((String) sentenceObj.get("matter"));
							offenderAllocationsSentences.setOrderType((String) sentenceObj.get("orderType"));
							returnList.add(offenderAllocationsSentences);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in OcondawaitServiceImpl class getTransferredCondOffenders method", e.getMessage());
		}
		// }
		return returnList;
	}

	@Override
	public List<OffenderAllocationsSentences> getTransferredConditons(OffenderAllocationsSentences searchBean) {
		return ocondawaitRepository.getTransferredConditons(searchBean);
	}
	
	@Override
	public List<Teams> rgTransferTeamRecGroup(String caseLoadId) {
		List<Teams> lovList = ocondawaitRepository.rgTransferTeamRecGroup(caseLoadId);
		lovList.forEach(e -> {
			if("N".equalsIgnoreCase(e.getActiveFlag())) {
				e.setCanDisplay(false);
			}
		});
		return lovList;
	}

}
