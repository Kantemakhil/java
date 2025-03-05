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

import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogsCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * class OidshlogControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidshlogControllerMockTestCase extends AbstractMockTestCase {

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agencyShiftLogsExecuteQueryTestMethod() {
		try {
			final AgencyShiftLogs bean = new AgencyShiftLogs();
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidshlog/agyShilExecuteQuery", bean)
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
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agencyShiftLogsCommitTestMethod() {
		try {
			final AgencyShiftLogsCommitBean commitBean = new AgencyShiftLogsCommitBean();
			final AgencyShiftLogs list = new AgencyShiftLogs();
			list.setGlobalCaseLoadId("ITAG");
			list.setLogDate(new Date());
			list.setLogTime(new Date());
			list.setDspAgyLocId4("ITAG");
			list.setDspAgyLocId3("CHAPEL");
			list.setStaffId(new BigDecimal(1));
			list.setAgyActivityCode("CELL");
			list.setLockFlag("Y");
			final List<AgencyShiftLogs> insertList = new ArrayList<AgencyShiftLogs>();
			insertList.add(list);
			commitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidshlog/agyShilCommit", commitBean)
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
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void agencyShiftLogsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidshlog/cgfkAgyShilDspAgyLocId4RecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("")));
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
	public void agencyShiftLogsStaffIdRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidshlog/cgfkAgyShilStaffIdRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("")));
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
	public void agencyShiftLogsAgyLoc3RecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidshlog/cgfkAgyShilDspAgyLocId3RecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("")));
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
	public void agencyShiftLogsActivityCodeRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidshlog/cgfkAgyShilAgyActivityCodRecordGroup").contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}