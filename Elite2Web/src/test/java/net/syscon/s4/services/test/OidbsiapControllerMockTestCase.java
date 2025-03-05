package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidbsiapControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidbsiapControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void offSchExecuteQueryTestMethod() {
		try {
			final String str = " 5/4/2016";
			final Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(str);
			final VOffenderAllSchedules2 beanObj = new VOffenderAllSchedules2();
			beanObj.setAgyLocId("CCC");
			beanObj.setEventSubType("CANT");
			beanObj.setToInternalLocationId(BigDecimal.valueOf(5512));
			beanObj.setEventDate(date1);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(beanObj);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidbsiap/offSchExecuteQuery", beanObj).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].eventId", is(117454)));
		} catch (Exception e) {
			logger.error("In method offSchExecuteQueryTestMethod", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOffenderAllSchedules2CommitTestMethod() {
		try {
			final OffenderIndSchedulesCommitBean commitBean = new OffenderIndSchedulesCommitBean();
			final OffenderIndSchedules list = new OffenderIndSchedules();
			final List<OffenderIndSchedules> insertList = new ArrayList<OffenderIndSchedules>();
			list.setOffenderBookId(20667);
			list.setEventDate(new Date());
			list.setStartTime(new Date());
			list.setEventClass("EXT_MOV");
			list.setEventType("TAP");
			list.setDirectionCode("OUT");
			list.setEventSubType("CANT");
			list.setEventStatus("PEN");
			list.setCommentText("test_24042018_kk");
			list.setApplicationDate(new Date());
			list.setApplicationTime(new Date());
			list.setReturnDate(new Date());
			list.setReturnTime(new Date());
			list.setOutcomeReasonCode("");
			list.setTransportCode("");
			list.setEscortCode("");
			list.setToAddressId(0);
			list.setAgyLocId("ITAG");
			list.setToAgyLocId("");
			list.setToAddressOwnerClass("");
			insertList.add(list);
			commitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidbsiap/offSchCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].name", is("Name")));
		} catch (Exception e) {
			logger.error("In method vOffenderAllSchedules2CommitTestMethod", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgSchInternalScheduleRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidbsiap/rgSchInternalScheduleRecordGroup")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Canteen")));
		} catch (Exception e) {
			logger.error("In method rgSchInternalScheduleRecordGroupTestMethod", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgAgyLocRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidbsiap/rgAgyLocRecordGroup?caseloadId=ITAG")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Red Deer Remand")));
		} catch (Exception e) {
			logger.error("In method rgAgyLocRecordGroupTestMethod", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void rgInternalMoveLocationsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidbsiap/rgInternalMoveLocationsRecordGroup?agyLocId=ITAG")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("ITAG-CHAPEL")));
		} catch (Exception e) {
			logger.error("In method rgInternalMoveLocationsRecordGroupTestMethod", e);
		}
	}
}