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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.HoldsCommitBean;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.services.config.EliteSpringConfig;
/***
 * 
 * class OcuholdsControllerMockTestCase
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OcuholdsControllerMockTestCase extends AbstractMockTestCase{


	@Test
	public void populateHoldsDataTestMethod() {
		try {
			final Holds holds = new Holds();
			holds.setEventId(Integer.valueOf(77933));
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(holds);
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuholds/populateHoldsData?eventId=77933")
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("Order Type", is("Immigration Hold")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void orderTypeTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuholds/orderType").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Immigration Hold")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  populateCourtDataTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuholds/populateCourtData").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Syscon District Court")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  populateHoldStatusTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuholds/populateHoldStatus").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Active")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void updateHoldDataTestMethod() {
		try {
			final HoldsCommitBean holdsBeanCommit = new HoldsCommitBean();
			final Holds deleteObj = new Holds();
			deleteObj.setOrderId(Integer.valueOf(9893));
			final List<Holds> deleteList = new ArrayList<>();
			deleteList.add(deleteObj);
			final Holds updateObj = new Holds();
			updateObj.setCommentText("new comment");
			updateObj.setOrderStatus("Active");
			final List<Holds> updateList = new ArrayList<>();
			updateList.add(updateObj);
			final Holds insertobj = new Holds();
			insertobj.setOrderType("Immigration Hold");
			insertobj.setIssuingAgyLocId("Syscon District Court");
			insertobj.setCommentText("test");
			insertobj.setCreateUserId("OMS_OWNER");
			insertobj.setOrderStatus("Active");
			insertobj.setEventId(Integer.valueOf(78029));
			insertobj.setOrderDate(new Date());
			insertobj.setCaseId("10863");
			insertobj.setExpiryDate(new Date());
			final List<Holds> insertList = new ArrayList<>();
			insertList.add(insertobj);
			final String jsonInString = new ObjectMapper().writeValueAsString(holdsBeanCommit);
			ResultActions resultActions = this.mockMvc
			.perform(get("/api/ocuholds/updateHoldData")
					.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print());
			resultActions.andExpect(jsonPath("$", is(0)));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@Test
	public void  calcExpDateTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/ocuholds/calcExpDates?orderType=Immigration Hold").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$", is(" ")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
}
