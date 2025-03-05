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

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;
/**
 * class OidsiappControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidsiappControllerMockTestCase extends AbstractMockTestCase  {
	
	
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void checkScheduleConflict() {
	try {
		final VOffenderAllSchedules obj = new VOffenderAllSchedules();
		obj.setEventDate(new Date());
		obj.setOffenderBookId(new BigDecimal(20446));
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(obj);
     ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidsiapp/checkScheduleConflict", obj).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void vOffenderAllSchedulesExecuteQueryTestMethod() {
		
		try {
			final VOffenderAllSchedules bean = new VOffenderAllSchedules();
			bean.setOffenderBookId(BigDecimal.valueOf(20446));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
	 ResultActions resultActions = this.mockMvc
			.perform(post("/api/oidsiapp/offSchExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
			.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void vOffenderAllSchedulesCommitTestMethod() {
	try {
		final VOffenderAllSchedulesCommitBean commitBean = new VOffenderAllSchedulesCommitBean();
		final VOffenderAllSchedules list = new VOffenderAllSchedules();
		final List<VOffenderAllSchedules> insertList = new ArrayList<VOffenderAllSchedules>();
		list.setOffenderBookId(new BigDecimal(20446));
		list.setEventDate(new Date());
		list.setStartTime(new Date());
		list.setEventClass("EXT_MOV");
		list.setEventType("APP");
		list.setEventSubType("CANT");
		list.setEventStatus("SCH");
		list.setCommentText("test_20446_junit");
		list.setApplicationDate(new Date());
		list.setApplicationTime(new Date());
		list.setReturnDate(new Date());
		list.setReturnTime(new Date());
		list.setOutcomeReasonCode("");
		list.setTransportCode("");
		list.setEscortCode("");
		list.setToAddressId(new BigDecimal(0));
		list.setAgyLocId("CCC");
		list.setToAgyLocId("");
		list.setToAddressOwnerClass("");
		list.setToInternalLocationId(new BigDecimal(5512));
		insertList.add(list);
		commitBean.setInsertList(insertList);
		final ObjectMapper mapper = new ObjectMapper();
		final String jsonInString = mapper.writeValueAsString(commitBean);
     ResultActions resultActions = this.mockMvc
		.perform(post("/api/oidsiapp/offSchCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
		.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void vOffenderAllSchedulesMovLocRecordGroupTestMethod() {
	try {
		
        ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidsiapp/rgInternalMoveLocationsRecordGroup?agyLocId=CCC").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("CCC-TEST")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void vOffenderAllSchedulesRecordGroupTestMethod() {
	try {
         ResultActions resultActions = this.mockMvc
		.perform(get("/api/oidsiapp/rgSchInternalScheduleRecordGroup").contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Canteen")));
		} catch (Exception e) {
			logger.error("", e);
		}
		}
}