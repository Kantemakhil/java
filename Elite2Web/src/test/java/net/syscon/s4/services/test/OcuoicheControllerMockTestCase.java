package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResultsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcuoicheControllerMockTestCase extends AbstractMockTestCase  {
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void oicHearingsExecuteQueryTestMethod() {
		try {
			OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingId(1963);
			String jsonInString = new ObjectMapper().writeValueAsString(oicHearings);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearExecuteQuery", oicHearings)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].oicHearingType", is("DISC")));
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
	public void oicHearingsCommitTestMethod() {
		try {
			OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingId(8795);
			oicHearings.setOicHearingType("s");
			oicHearings.setOicIncidentId(2821);
			oicHearings.setScheduleDate(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setScheduleTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingDate(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingStaffId(4466);
			oicHearings.setVisitJusticeText("a");
			oicHearings.setCommentText("h");
			oicHearings.setTapeNumber("h");
			oicHearings.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setCreateUserId("k");
			oicHearings.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setModifyUserId("A");
			oicHearings.setInternalLocationId(5346);
			oicHearings.setRepresentativeText("A");
			oicHearings.setEventId(12);
			oicHearings.setEventStatus("A");
			oicHearings.setSealFlag("A");
			oicHearings.setInserted(true);
			oicHearings.setOicHearingTypeDes("A");
			oicHearings.setInternalLocationIdDes("A");

			List<OicHearings> insertList = new ArrayList<OicHearings>();
			insertList.add(oicHearings);
			OicHearingsCommitBean oicHearingsCommitBean = new OicHearingsCommitBean();
			oicHearingsCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void oicHearingsCommitTestMethod1() {
		try {
			OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingType("O");
			oicHearings.setOicIncidentId(2821);
			oicHearings.setScheduleDate(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setScheduleTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingDate(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingStaffId(45);
			oicHearings.setVisitJusticeText("k");
			oicHearings.setCommentText("L");
			oicHearings.setTapeNumber("T");
			oicHearings.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setCreateUserId("Q");
			oicHearings.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setModifyUserId("AE");
			oicHearings.setInternalLocationId(5346);
			oicHearings.setRepresentativeText("AT");
			oicHearings.setEventId(12);
			oicHearings.setEventStatus("SE");
			oicHearings.setSealFlag("A");
			oicHearings.setInserted(true);
			oicHearings.setOicHearingTypeDes("AG");
			oicHearings.setInternalLocationIdDes("AC");
			oicHearings.setOicHearingId(8795);

			List<OicHearings> updateList = new ArrayList<OicHearings>();
			updateList.add(oicHearings);
			OicHearingsCommitBean oicHearingsCommitBean = new OicHearingsCommitBean();
			oicHearingsCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void oicHearingsCommitTestMethod2() {
		try {
			OicHearings oicHearings = new OicHearings();
			oicHearings.setOicHearingType("O");
			oicHearings.setOicIncidentId(2821);
			oicHearings.setScheduleDate(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setScheduleTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingDate(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setHearingStaffId(45);
			oicHearings.setVisitJusticeText("k");
			oicHearings.setCommentText("L");
			oicHearings.setTapeNumber("T");
			oicHearings.setCreateDateTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setCreateUserId("Q");
			oicHearings.setModifyDateTime(new Date(0, 0, 0, 0, 0, 0));
			oicHearings.setModifyUserId("AE");
			oicHearings.setInternalLocationId(5346);
			oicHearings.setRepresentativeText("AT");
			oicHearings.setEventId(12);
			oicHearings.setEventStatus("SE");
			oicHearings.setSealFlag("A");
			oicHearings.setInserted(true);
			oicHearings.setOicHearingTypeDes("AG");
			oicHearings.setInternalLocationIdDes("AC");
			oicHearings.setOicHearingId(8795);

			List<OicHearings> deleteList = new ArrayList<OicHearings>();
			deleteList.add(oicHearings);
			OicHearingsCommitBean oicHearingsCommitBean = new OicHearingsCommitBean();
			oicHearingsCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearCommit").contentType(MediaType.APPLICATION_JSON)
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
	public void oicHearingsRecordGroupTestMethod1() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgOffenceCodeRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("test")));
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
	public void oicHearingsRecordGroupTestMethod2() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgAgyIncpStaffIdRecordGroup?caseloadId=ARKIN")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].staffId", is(3005)));
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
	public void oicHearingsRecordGroupTestMethod3() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgHearingTypeRecordGroup")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Disciplinary Hearing")));
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
	public void oicHearingsRecordGroupTestMethod4() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgInternalLocationsRecordGroup?caseloadId=ITAG")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("ITAG-VISIT")));
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
	public void oicHearingsRecordGroupTestMethod5() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgIncidentChargesRecordGroup?agencyIncidentId=5060&partySeq=1")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("aaa")));
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
	public void oicHearingsRecordGroupTestMethod6() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgFindingRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Guilty")));
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
	public void oicHearingsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuoiche/rgPleaRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Guilty")));
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
	public void oicHearingResultsExecuteQueryTestMethod() {
		try {
			OicHearingResults oicHearingResults = new OicHearingResults();
			oicHearingResults.setOicHearingId(BigDecimal.valueOf(1963));
			String jsonInString = new ObjectMapper().writeValueAsString(oicHearingResults);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearResExecuteQuery", oicHearingResults)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agencyIncidentId", is(3803)));
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
	public void oicHearingResultsCommitTestMethod() {
		try {

			OicHearingResults oicHearingResults = new OicHearingResults();
			oicHearingResults.setOicHearingId(BigDecimal.valueOf(2144));
			oicHearingResults.setResultSeq(BigDecimal.valueOf(4458));
			oicHearingResults.setAgencyIncidentId(BigDecimal.valueOf(3803));
			oicHearingResults.setChargeSeq(BigDecimal.valueOf(1));
			oicHearingResults.setPleaFindingCode("k");
			oicHearingResults.setFindingCode("L");
			oicHearingResults.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			oicHearingResults.setCreateUserId("Q");
			oicHearingResults.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			oicHearingResults.setModifyUserId("AE");
			oicHearingResults.setOicOffenceId(BigDecimal.valueOf(202));
			oicHearingResults.setSealFlag("A");
			oicHearingResults.setOicResultOffenceId(BigDecimal.valueOf(4466));
			List<OicHearingResults> insertList = new ArrayList<OicHearingResults>();
			insertList.add(oicHearingResults);
			OicHearingResultsCommitBean oicHearingResultsCommitBean = new OicHearingResultsCommitBean();
			oicHearingResultsCommitBean.setInsertList(insertList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingResultsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearResCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void oicHearingResultsCommitTestMethod1() {
		try {

			OicHearingResults oicHearingResults = new OicHearingResults();
			oicHearingResults.setResultSeq(BigDecimal.valueOf(4458));
			oicHearingResults.setAgencyIncidentId(BigDecimal.valueOf(3803));
			oicHearingResults.setChargeSeq(BigDecimal.valueOf(1));
			oicHearingResults.setPleaFindingCode("H");
			oicHearingResults.setFindingCode("L");
			oicHearingResults.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			oicHearingResults.setCreateUserId("Q");
			oicHearingResults.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			oicHearingResults.setModifyUserId("AE");
			oicHearingResults.setOicOffenceId(BigDecimal.valueOf(201));
			oicHearingResults.setSealFlag("b");
			oicHearingResults.setOicResultOffenceId(BigDecimal.valueOf(4466));
			oicHearingResults.setInserted(true);
			oicHearingResults.setOicHearingId(BigDecimal.valueOf(2144));

			List<OicHearingResults> updateList = new ArrayList<OicHearingResults>();
			updateList.add(oicHearingResults);
			OicHearingResultsCommitBean oicHearingResultsCommitBean = new OicHearingResultsCommitBean();
			oicHearingResultsCommitBean.setUpdateList(updateList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingResultsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearResCommit").contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void oicHearingResultsCommitTestMethod2() {
		try {

			OicHearingResults oicHearingResults = new OicHearingResults();
			oicHearingResults.setResultSeq(BigDecimal.valueOf(4458));
			oicHearingResults.setAgencyIncidentId(BigDecimal.valueOf(3803));
			oicHearingResults.setChargeSeq(BigDecimal.valueOf(1));
			oicHearingResults.setPleaFindingCode("k");
			oicHearingResults.setFindingCode("L");
			oicHearingResults.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
			oicHearingResults.setCreateUserId("Q");
			oicHearingResults.setModifyDatetime(new Date(0, 0, 0, 0, 0, 0));
			oicHearingResults.setModifyUserId("AE");
			oicHearingResults.setOicOffenceId(BigDecimal.valueOf(201));
			oicHearingResults.setSealFlag("b");
			oicHearingResults.setOicResultOffenceId(BigDecimal.valueOf(4466));
			oicHearingResults.setInserted(true);
			oicHearingResults.setOicHearingId(BigDecimal.valueOf(2144));

			List<OicHearingResults> deleteList = new ArrayList<OicHearingResults>();
			deleteList.add(oicHearingResults);
			OicHearingResultsCommitBean oicHearingResultsCommitBean = new OicHearingResultsCommitBean();
			oicHearingResultsCommitBean.setDeleteList(deleteList);
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(oicHearingResultsCommitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oicHearResCommit").contentType(MediaType.APPLICATION_JSON)
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
	public void ocuoicheOichearprequery() { 
		try {
			CaseloadAgencyLocations caseloadAgencyLocations = new CaseloadAgencyLocations();
			caseloadAgencyLocations.setCaseloadId("ARKIN");
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(caseloadAgencyLocations);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oichearprequery", caseloadAgencyLocations)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void ocuoicheOichearoncheckdeletemasteroichearrescur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/oichearoncheckdeletemasteroichearrescur?OicHearingId=1963")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * method for query callings
	 */
	@Test
	public void ocuoicheOcuoichekeydelrecoicsanccur() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/ocuoiche/ocuoichekeydelrecoicsanccur?OicHearingId= 2264&ResultSeq=1")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0]", is(1)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}


}