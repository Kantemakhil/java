package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.im.beans.AgyIncInvStatements;
import net.syscon.s4.im.beans.AgyIncInvStatementsCommitBean;
import net.syscon.s4.im.beans.AgyIncInvestigations;
import net.syscon.s4.im.beans.AgyIncInvestigationsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OcuoicinControllerMockTestCase extends AbstractMockTestCase {
	

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agyIncInvestigationsExecuteQueryTestMethod() {
		try {

			final AgyIncInvestigations agyIncInvestigations = new AgyIncInvestigations();
			agyIncInvestigations.setInvestigatorId(3007);
			final String jsonInString = new ObjectMapper().writeValueAsString(agyIncInvestigations);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestExecuteQuery", agyIncInvestigations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agencyIncidentId", is(3804)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void agyIncInvestigationsInsertCommitTestMethod() {
		try {

			final AgyIncInvestigations agyIncInvestigations = new AgyIncInvestigations();
			agyIncInvestigations.setAgyIncInvestigationId(4478);
			agyIncInvestigations.setAgencyIncidentId(3804);
			agyIncInvestigations.setInvestigatorId(3004);
			agyIncInvestigations.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setAssignedDate(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setCommentText(null);
			agyIncInvestigations.setModifyUserId("ARKIN");
			agyIncInvestigations.setCreateUserId(null);
			agyIncInvestigations.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setPartySeq(2);
			agyIncInvestigations.setSealFlag(null);

			final List<AgyIncInvestigations> insertList = new ArrayList<AgyIncInvestigations>();
			insertList.add(agyIncInvestigations);
			final AgyIncInvestigationsCommitBean agyIncInvestigationsCommitBean = new AgyIncInvestigationsCommitBean();
			agyIncInvestigationsCommitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agyIncInvestigationsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void agyIncInvestigationsUpdateCommitTestMethod() {
		try {
			AgyIncInvestigations agyIncInvestigations = new AgyIncInvestigations();
			agyIncInvestigations.setAgyIncInvestigationId(4478);
			agyIncInvestigations.setAgencyIncidentId(3804);
			agyIncInvestigations.setInvestigatorId(3004);
			agyIncInvestigations.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setAssignedDate(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setCommentText(null);
			agyIncInvestigations.setModifyUserId("SAI");
			agyIncInvestigations.setCreateUserId("aaa");
			agyIncInvestigations.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setPartySeq(2);
			agyIncInvestigations.setSealFlag(null);

			List<AgyIncInvestigations> updateList = new ArrayList<AgyIncInvestigations>();
			updateList.add(agyIncInvestigations);
			AgyIncInvestigationsCommitBean agyIncInvestigationsCommitBean = new AgyIncInvestigationsCommitBean();
			agyIncInvestigationsCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agyIncInvestigationsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void agyIncInvestigationsDeleteCommitTestMethod() {
		try {
			AgyIncInvestigations agyIncInvestigations = new AgyIncInvestigations();
			agyIncInvestigations.setAgyIncInvestigationId(4478);
			agyIncInvestigations.setAgencyIncidentId(3804);
			agyIncInvestigations.setInvestigatorId(3004);
			agyIncInvestigations.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setAssignedDate(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setCommentText(null);
			agyIncInvestigations.setModifyUserId("SAI");
			agyIncInvestigations.setCreateUserId("aaa");
			agyIncInvestigations.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvestigations.setPartySeq(2);
			agyIncInvestigations.setSealFlag(null);

			List<AgyIncInvestigations> deleteList = new ArrayList<AgyIncInvestigations>();
			deleteList.add(agyIncInvestigations);
			AgyIncInvestigationsCommitBean agyIncInvestigationsCommitBean = new AgyIncInvestigationsCommitBean();
			agyIncInvestigationsCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agyIncInvestigationsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agyIncInvestigationsRgStatementRecordGroupTestMethod() {
		try {

			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicin/rgStatementTypeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Behavior Report")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agyIncInvestigationsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoicin/rgAgyIncpStaffIdRecordGroup?caseloadId=ARKIN")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].code", is("3005")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agyIncInvStatementsExecuteQueryTestMethod() {
		try {
			final AgyIncInvStatements agyIncInvStatements = new AgyIncInvStatements();
			agyIncInvStatements.setAgyIncInvestigationId(1262);
			final String jsonInString = new ObjectMapper().writeValueAsString(agyIncInvStatements);
			ResultActions resultActions = this.mockMvc.perform(
					post("/api/ocuoicin/oicInvestStaExecuteQuery", agyIncInvStatements/* tagSearchGetOffenderRecords */)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].statementType", is("BEHAV")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void agyIncInvStatementsInsertCommitTestMethod() {
		try {

			final AgyIncInvStatements agyIncInvStatements = new AgyIncInvStatements();
			agyIncInvStatements.setAgyIiStatementId(4466);
			agyIncInvStatements.setAgyIncInvestigationId(1262);
			agyIncInvStatements.setStatementType("sathi");
			agyIncInvStatements.setNameOfStatementTaker(null);
			agyIncInvStatements.setDateOfStatementTaken(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setStatementDetail("kiran");
			agyIncInvStatements.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setCreateUserId(null);
			agyIncInvStatements.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setModifyUserId("sai");
			agyIncInvStatements.setSealFlag(null);

			final List<AgyIncInvStatements> insertList = new ArrayList<AgyIncInvStatements>();
			insertList.add(agyIncInvStatements);
			final AgyIncInvStatementsCommitBean agyIncInvStatementsCommitBean = new AgyIncInvStatementsCommitBean();
			agyIncInvStatementsCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agyIncInvStatementsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestStaCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void agyIncInvStatementsUpdateCommitTestMethod() {
		try {

			final AgyIncInvStatements agyIncInvStatements = new AgyIncInvStatements();
			agyIncInvStatements.setAgyIiStatementId(4466);
			agyIncInvStatements.setAgyIncInvestigationId(1262);
			agyIncInvStatements.setStatementType("adi");
			agyIncInvStatements.setNameOfStatementTaker(null);
			agyIncInvStatements.setDateOfStatementTaken(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setStatementDetail("kiran");
			agyIncInvStatements.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setCreateUserId("san");
			agyIncInvStatements.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setModifyUserId("sai");
			agyIncInvStatements.setSealFlag(null);

			final List<AgyIncInvStatements> updateList = new ArrayList<AgyIncInvStatements>();
			updateList.add(agyIncInvStatements);
			final AgyIncInvStatementsCommitBean agyIncInvStatementsCommitBean = new AgyIncInvStatementsCommitBean();
			agyIncInvStatementsCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(agyIncInvStatementsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestStaCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void agyIncInvStatementsDeleteCommitTestMethod() {
		try {

			final AgyIncInvStatements agyIncInvStatements = new AgyIncInvStatements();
			agyIncInvStatements.setAgyIiStatementId(4466);
			agyIncInvStatements.setAgyIncInvestigationId(1262);
			agyIncInvStatements.setStatementType("adi");
			agyIncInvStatements.setNameOfStatementTaker(null);
			agyIncInvStatements.setDateOfStatementTaken(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setStatementDetail("kiran");
			agyIncInvStatements.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setCreateUserId("san");
			agyIncInvStatements.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			agyIncInvStatements.setModifyUserId("sai");
			agyIncInvStatements.setSealFlag(null);

			List<AgyIncInvStatements> deleteList = new ArrayList<AgyIncInvStatements>();
			deleteList.add(agyIncInvStatements);
			AgyIncInvStatementsCommitBean agyIncInvStatementsCommitBean = new AgyIncInvStatementsCommitBean();
			agyIncInvStatementsCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(agyIncInvStatementsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoicin/oicInvestStaCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void ocuoicinOicinvestoncheckdeletemasteroicinveststacur() {
		try {
			final AgyIncInvStatements agyIncInvStatements = new AgyIncInvStatements();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(agyIncInvStatements);
			ResultActions resultActions = this.mockMvc
					.perform(
							post("/api/ocuoicin/oicInvestOnCheckDeleteMasteroicInvestStaCur?AgyIncInvestigationId=1262")
									.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
									.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	
}
