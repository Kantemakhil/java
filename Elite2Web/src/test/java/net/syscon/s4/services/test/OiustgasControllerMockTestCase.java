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

import net.syscon.s4.common.beans.AgencyIncidentAssoTostg;
import net.syscon.s4.common.beans.AgencyIncidentAssoTostgCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OiustgasControllerMockTestCase extends AbstractMockTestCase {

	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void agencyIncidentAssoTostgExecuteQueryTestMethod() {
		try {
			AgencyIncidentAssoTostg ageIncAssTostg = new AgencyIncidentAssoTostg();
			ageIncAssTostg.setAgencyIncidentId(3824);
			ageIncAssTostg.setSeqNo(1);
			final String jsonInString = new ObjectMapper().writeValueAsString(ageIncAssTostg);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oiustgas/agencyIncidentAssoTostgExecuteQuery", ageIncAssTostg)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agencyIncidentId", is(3824)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void agencyIncidentAssoTostgCommitTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(post("/api/oiustgas/agencyIncidentAssoTostgCommit").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void agencyIncidentAssoTostgRecordGroupTestMethod() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiustgas/rgStgRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void agencyIncidentAssoTostgRecordGroupTestMethod1() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiustgas/rgStgORecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void agencyIncidentAssoTostgRecordGroupTestMethod2() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiustgas/rgStgLRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/     
	@Test
	public void agencyIncidentAssoTostgRecordGroupTestMethod3() {
	try {
 ResultActions resultActions = this.mockMvc
		.perform(get("/api/oiustgas/stgGrpRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].stgCode", is("BLOODS")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}

@Test
public void agencyIncidentChargesInsertCommit() {
	try {
		AgencyIncidentAssoTostgCommitBean agencyIncidentAssoTostgCommitBean = new AgencyIncidentAssoTostgCommitBean();
		AgencyIncidentAssoTostg agencyIncidentAssoTostg = new AgencyIncidentAssoTostg();
		agencyIncidentAssoTostg.setAgencyIncidentId(4306);  
		agencyIncidentAssoTostg.setSeqNo(1);
		agencyIncidentAssoTostg.setStgId(5000);
		agencyIncidentAssoTostg.setCreateUserId("Arkin Kingmaker");
		agencyIncidentAssoTostg.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
		List<AgencyIncidentAssoTostg> insertList = new ArrayList<AgencyIncidentAssoTostg>();
		insertList.add(agencyIncidentAssoTostg);

		agencyIncidentAssoTostgCommitBean.setInsertList(insertList);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(agencyIncidentAssoTostgCommitBean);
		ResultActions resultActions = this.mockMvc
				.perform(post("/api/oiustgas/agencyIncidentAssoTostgCommit").contentType(MediaType.APPLICATION_JSON)
						.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());
		resultActions.andExpect(jsonPath("$", is(1)));
	} catch (Exception e) {
		logger.error("", e);
	}
}

@Test
public void agencyIncidentChargesUpdateCommit() {
	try {
		AgencyIncidentAssoTostgCommitBean agencyIncidentAssoTostgCommitBean = new AgencyIncidentAssoTostgCommitBean();
		AgencyIncidentAssoTostg agencyIncidentAssoTostg = new AgencyIncidentAssoTostg();
		agencyIncidentAssoTostg.setAgencyIncidentId(4306);  
		agencyIncidentAssoTostg.setSeqNo(1);
		agencyIncidentAssoTostg.setStgId(10000);
		agencyIncidentAssoTostg.setCreateUserId("Arkin Kingmaker");
		agencyIncidentAssoTostg.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
		agencyIncidentAssoTostg.setModifyUserId("Arkin");
		List<AgencyIncidentAssoTostg> updateList = new ArrayList<AgencyIncidentAssoTostg>();
		updateList.add(agencyIncidentAssoTostg);

		agencyIncidentAssoTostgCommitBean.setUpdateList(updateList);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(agencyIncidentAssoTostgCommitBean);
		ResultActions resultActions = this.mockMvc
				.perform(post("/api/oiustgas/agencyIncidentAssoTostgCommit").contentType(MediaType.APPLICATION_JSON)
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
		AgencyIncidentAssoTostgCommitBean agencyIncidentAssoTostgCommitBean = new AgencyIncidentAssoTostgCommitBean();
		AgencyIncidentAssoTostg agencyIncidentAssoTostg = new AgencyIncidentAssoTostg();
		agencyIncidentAssoTostg.setAgencyIncidentId(4306);  
		agencyIncidentAssoTostg.setSeqNo(1);
		agencyIncidentAssoTostg.setStgId(10000);
		agencyIncidentAssoTostg.setCreateUserId("Arkin Kingmaker");
		agencyIncidentAssoTostg.setCreateDatetime(new Date(0, 0, 0, 0, 0, 0));
		agencyIncidentAssoTostg.setModifyUserId("Arkin");
		List<AgencyIncidentAssoTostg> deleteList = new ArrayList<AgencyIncidentAssoTostg>();
		deleteList.add(agencyIncidentAssoTostg);

		agencyIncidentAssoTostgCommitBean.setDeleteList(deleteList);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(agencyIncidentAssoTostgCommitBean);
		ResultActions resultActions = this.mockMvc
				.perform(post("/api/oiustgas/agencyIncidentAssoTostgCommit").contentType(MediaType.APPLICATION_JSON)
						.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print());
		resultActions.andExpect(jsonPath("$", is(1)));
	} catch (Exception e) {
		logger.error("", e);
	}
}
}
