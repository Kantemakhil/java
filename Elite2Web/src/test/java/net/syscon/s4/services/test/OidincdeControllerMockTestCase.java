package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;


import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidincdeControllerMockTestCase
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidincdeControllerMockTestCase extends AbstractMockTestCase {

	@Test
	public void agencyIncidentsSearchAgencyIncidents() {
		try {
			AgencyIncidents agencyIncidents = new AgencyIncidents();
			agencyIncidents.setAgyLocId("ITAG");
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidents);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentsExecuteQuery", agencyIncidents)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].internalLocationId ", is(5345)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentChargesExecuteQuery() {
		try {
			AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(4446);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentCharges);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentChargesExecuteQuery", agencyIncidentCharges)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].chargeSeq ", is(18)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgAgyLocIdsRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgAgyLocIdsRecordGroup?caseloadId=ITAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("SDTC")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgIncidentTypesRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgIncidentTypesRecordGroup?domain=OUTCOMES")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Disturbance/Disruption")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgLevelInternalLocationIdsRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgLevelInternalLocationIdsRecordGroup?agyLocId=ITAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].internalLocationId", is(5345)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgReportedStaffIdsRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgReportedStaffIdsRecordGroup?caseloadId=ITAG")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].staffId", is(3849)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgOicOffenceCodesRecordGroup() {
		try {
			String startDate = "30082012";
			String endDate = "30082014";
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgOicOffenceCodesRecordGroup?startDate=" + startDate + "&endDate="
							+ endDate + "").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("arkin")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgRepairTypesRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgRepairTypesRecordGroup?domain=CITY")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Edmonton")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgOffInvIncidentRolesRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgOffInvIncidentRolesRecordGroup?domain=OUTCOMES")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Acceptable / Excused Absence")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgOffInvActionCodesRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgOffInvActionCodesRecordGroup?domain=RELATIONSHIP")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Aunt")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void rgStaffInvIncidentRolesRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/rgStaffInvIncidentRolesRecordGroup?domain=OUTCOMES")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Acceptable / Excused Absence")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesOffenderExecuteQuery() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentParties);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesOffenderExecuteQuery", agencyIncidentParties)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderBookId", is(18773)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentRepairsExecuteQuery() {
		try {
			AgencyIncidentRepairs agencyIncidentRepairs = new AgencyIncidentRepairs();
			agencyIncidentRepairs.setAgencyIncidentId(4026);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentRepairs);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentRepairsExecuteQuery", agencyIncidentRepairs)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].repairSeq", is(3)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentChargesInsertCommit() {
		try {
			AgencyIncidentChargesCommitBean agencyIncidentChargesCommitBean = new AgencyIncidentChargesCommitBean();
			AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(4347);
			agencyIncidentCharges.setChargeSeq(4089);
			agencyIncidentCharges.setPartySeq(1);
			agencyIncidentCharges.setOicChargeId("5051");
			agencyIncidentCharges.setFindingCode("SSS");
			agencyIncidentCharges.setGuiltyEvidenceText("TTT123");
			agencyIncidentCharges.setReportText(null);
			agencyIncidentCharges.setEvidenceDisposeText("YYY");
			agencyIncidentCharges.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentCharges.setCreateUserId("OMS_OWNER");
			agencyIncidentCharges.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentCharges.setModifyUserId("ADMINQA");
			agencyIncidentCharges.setLidsChargeNumber(1428);
			agencyIncidentCharges.setChargedOicOffenceId(203);
			agencyIncidentCharges.setResultOicOffenceId(null);
			agencyIncidentCharges.setSealFlag(null);
			List<AgencyIncidentCharges> insertList = new ArrayList<AgencyIncidentCharges>();
			insertList.add(agencyIncidentCharges);

			agencyIncidentChargesCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentChargesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentChargesCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentChargesUpdateCommit() {
		try {
			AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(4026);
			agencyIncidentCharges.setChargeSeq(408);
			agencyIncidentCharges.setPartySeq(1);
			agencyIncidentCharges.setOicChargeId("505");
			agencyIncidentCharges.setFindingCode("NNN");
			agencyIncidentCharges.setGuiltyEvidenceText("TTT");
			agencyIncidentCharges.setReportText(null);
			agencyIncidentCharges.setEvidenceDisposeText("ZZZ");
			agencyIncidentCharges.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentCharges.setCreateUserId("OMS_OWNER");
			agencyIncidentCharges.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentCharges.setModifyUserId("ADMINQA");
			agencyIncidentCharges.setLidsChargeNumber(1428);
			agencyIncidentCharges.setChargedOicOffenceId(203);
			agencyIncidentCharges.setResultOicOffenceId(null);
			agencyIncidentCharges.setSealFlag(null);

			List<AgencyIncidentCharges> updateList = new ArrayList<AgencyIncidentCharges>();
			updateList.add(agencyIncidentCharges);
			AgencyIncidentChargesCommitBean agencyIncidentChargesCommitBean = new AgencyIncidentChargesCommitBean();
			agencyIncidentChargesCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentChargesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentChargesCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentChargesDeleteCommit() {
		try {
			AgencyIncidentCharges agencyIncidentCharges = new AgencyIncidentCharges();
			agencyIncidentCharges.setAgencyIncidentId(4026);
			agencyIncidentCharges.setChargeSeq(408);
			agencyIncidentCharges.setPartySeq(1);
			agencyIncidentCharges.setOicChargeId("505");
			agencyIncidentCharges.setFindingCode("NNN");
			agencyIncidentCharges.setGuiltyEvidenceText("TTT");
			agencyIncidentCharges.setReportText(null);
			agencyIncidentCharges.setEvidenceDisposeText("ZZZ");
			agencyIncidentCharges.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentCharges.setCreateUserId("OMS_OWNER");
			agencyIncidentCharges.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentCharges.setModifyUserId("ADMINQA");
			agencyIncidentCharges.setLidsChargeNumber(1428);
			agencyIncidentCharges.setChargedOicOffenceId(203);
			agencyIncidentCharges.setResultOicOffenceId(null);
			agencyIncidentCharges.setSealFlag(null);

			List<AgencyIncidentCharges> deleteList = new ArrayList<AgencyIncidentCharges>();
			deleteList.add(agencyIncidentCharges);
			AgencyIncidentChargesCommitBean agencyIncidentChargesCommitBean = new AgencyIncidentChargesCommitBean();
			agencyIncidentChargesCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentChargesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentChargesCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentRepairsInsertCommit() {
		try {
			AgencyIncidentRepairsCommitBean agencyIncidentRepairsCommitBean = new AgencyIncidentRepairsCommitBean();
			AgencyIncidentRepairs agencyIncidentRepairs = new AgencyIncidentRepairs();
			agencyIncidentRepairs.setAgencyIncidentId(5070);
			agencyIncidentRepairs.setRepairSeq(4);
			agencyIncidentRepairs.setRepairType("ELEC");
			agencyIncidentRepairs.setCommentText("arkin");
			agencyIncidentRepairs.setModifyUserId(null);
			agencyIncidentRepairs.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentRepairs.setRepairCost(null);
			agencyIncidentRepairs.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentRepairs.setCreateUserId("ADMINQA");
			agencyIncidentRepairs.setSealFlag(null);
			List<AgencyIncidentRepairs> insertList = new ArrayList<AgencyIncidentRepairs>();
			insertList.add(agencyIncidentRepairs);

			agencyIncidentRepairsCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentRepairsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentRepairsCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(2)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentRepairsUpdateCommit() {
		try {
			AgencyIncidentRepairs agencyIncidentRepairs = new AgencyIncidentRepairs();
			agencyIncidentRepairs.setAgencyIncidentId(5070);
			agencyIncidentRepairs.setRepairSeq(4);
			agencyIncidentRepairs.setRepairType("ELEC");
			agencyIncidentRepairs.setCommentText("ARKIN");
			agencyIncidentRepairs.setModifyUserId(null);
			agencyIncidentRepairs.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentRepairs.setRepairCost(null);
			agencyIncidentRepairs.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentRepairs.setCreateUserId("ADMINQA");
			agencyIncidentRepairs.setSealFlag(null);

			List<AgencyIncidentRepairs> updateList = new ArrayList<AgencyIncidentRepairs>();
			updateList.add(agencyIncidentRepairs);
			AgencyIncidentRepairsCommitBean agencyIncidentRepairsCommitBean = new AgencyIncidentRepairsCommitBean();
			agencyIncidentRepairsCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentRepairsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentRepairsCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentRepairsDeleteCommit() {
		try {
			AgencyIncidentRepairs agencyIncidentRepairs = new AgencyIncidentRepairs();
			agencyIncidentRepairs.setAgencyIncidentId(5070);
			agencyIncidentRepairs.setRepairSeq(4);
			agencyIncidentRepairs.setRepairType("ELEC");
			agencyIncidentRepairs.setCommentText("ARKIN");
			agencyIncidentRepairs.setModifyUserId(null);
			agencyIncidentRepairs.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentRepairs.setRepairCost(null);
			agencyIncidentRepairs.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentRepairs.setCreateUserId("ADMINQA");
			agencyIncidentRepairs.setSealFlag(null);

			List<AgencyIncidentRepairs> deleteList = new ArrayList<AgencyIncidentRepairs>();
			deleteList.add(agencyIncidentRepairs);
			AgencyIncidentRepairsCommitBean agencyIncidentRepairsCommitBean = new AgencyIncidentRepairsCommitBean();
			agencyIncidentRepairsCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentRepairsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentRepairsCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentPartiesInsertCommit() {
		try {
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3568);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("ADMINQA");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");
			List<AgencyIncidentParties> insertList = new ArrayList<AgencyIncidentParties>();
			insertList.add(agencyIncidentParties);

			agencyIncidentPartiesCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentPartiesCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentPartiesUpdateCommit() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SSSS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("OMS_OWNER");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> updateList = new ArrayList<AgencyIncidentParties>();
			updateList.add(agencyIncidentParties);
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			agencyIncidentPartiesCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentPartiesCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentPartiesDeleteCommit() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SSSS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("OMS_OWNER");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> deleteList = new ArrayList<AgencyIncidentParties>();
			deleteList.add(agencyIncidentParties);
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			agencyIncidentPartiesCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentPartiesCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesStaffInsertCommit() {
		try {
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("ADMINQA");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");
			List<AgencyIncidentParties> insertList = new ArrayList<AgencyIncidentParties>();
			insertList.add(agencyIncidentParties);

			agencyIncidentPartiesCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesStaffCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesStaffUpdateCommit() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SSSS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("OMS_OWNER");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> updateList = new ArrayList<AgencyIncidentParties>();
			updateList.add(agencyIncidentParties);
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			agencyIncidentPartiesCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesStaffCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesStaffDeleteCommit() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SSSS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("OMS_OWNER");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> deleteList = new ArrayList<AgencyIncidentParties>();
			deleteList.add(agencyIncidentParties);
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			agencyIncidentPartiesCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesStaffCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesOffenderInsertCommit() {
		try {
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("ADMINQA");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> insertList = new ArrayList<AgencyIncidentParties>();
			insertList.add(agencyIncidentParties);
			agencyIncidentPartiesCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesOffenderCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesOffenderUpdateCommit() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SSSS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("OMS_OWNER");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> updateList = new ArrayList<AgencyIncidentParties>();
			updateList.add(agencyIncidentParties);
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			agencyIncidentPartiesCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesOffenderCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesOffenderDeleteCommit() {
		try {
			AgencyIncidentParties agencyIncidentParties = new AgencyIncidentParties();
			agencyIncidentParties.setAgencyIncidentId(4006);
			agencyIncidentParties.setPartySeq(26);
			agencyIncidentParties.setIncidentRole("SSSS");
			agencyIncidentParties.setOffenderBookId(18773);
			agencyIncidentParties.setStaffId(3004);
			agencyIncidentParties.setPersonId(5326);
			agencyIncidentParties.setDispositionType(null);
			agencyIncidentParties.setDispositionDate(null);
			agencyIncidentParties.setOicIncidentId(3567);
			agencyIncidentParties.setCommentText(null);
			agencyIncidentParties.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setCreateUserId("OMS_OWNER");
			agencyIncidentParties.setActionCode("RFH");
			agencyIncidentParties.setPartyAddedDate(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			agencyIncidentParties.setModifyUserId("ADMINQA");
			agencyIncidentParties.setSealFlag(null);
			agencyIncidentParties.setRepCompletFlag("N");

			List<AgencyIncidentParties> deleteList = new ArrayList<AgencyIncidentParties>();
			deleteList.add(agencyIncidentParties);
			AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
			agencyIncidentPartiesCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agencyIncidentPartiesCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agyIncPartiesOffenderCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/oidincde/agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur?agencyIncidentId=4446")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/oidincde/agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur?agencyIncidentId=4446")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/oidincde/agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur?agencyIncidentId=4982")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							get("/api/oidincde/agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur?agencyIncidentId=4446&partySeq=1")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agyIncPartiesOffenderPreInsert() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/oidincde/agyIncPartiesOffenderPreInsert?offenderbookid=18733&agencyIncidentId=3804")
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void agencyIncidentChargesPostQuery() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidincde/agencyIncidentChargesPostQuery?systemMode=systemMode")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void setGlobalCaseloadIdworkingCaseloadCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/setGlobalCaseloadIdworkingCaseloadCur?systemMode=systemMode")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].workingCaseloadId", is("ITAG")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oidincdeStateadmUsrCsr() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/oidincdeStateadmUsrCsr?systemMode=systemMode")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void oidincdeState() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/oidincdeState?caseloadId=ITAG").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Syscon Jail")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void setUpdatedIncidentDetailsupdIncDetCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/setUpdatedIncidentDetailsupdIncDetCur?agencyIncidentId=3950")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].incidentDetails", is("test")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void checkUnlockAccessspProfileCur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/checkUnlockAccessspProfileCur?systemMode=systemMode")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].profileValue", is("1821")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Test
	public void checkUnlockAccesscheckRoleAccess() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidincde/checkUnlockAccesscheckRoleAccess?pvalue&pValue2")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
