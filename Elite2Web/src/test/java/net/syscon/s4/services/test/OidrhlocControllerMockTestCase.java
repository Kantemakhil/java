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

import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;
import net.syscon.s4.services.config.EliteSpringConfig;

/**
 * Class OidrhlocControllerMockTestCase
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OidrhlocControllerMockTestCase extends AbstractMockTestCase {
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void reserveBedLocationsExecuteQueryTestMethod() {
		try {
			final ReserveBedLocations bean = new ReserveBedLocations();
			bean.setAgyLocId("ITAG");
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(bean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrhloc/resBlExecuteQuery", bean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].offenderId", is(1021832)));
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
	public void reserveBedLocationsCommitTestMethod() {
		try {
			final ReserveBedLocationscommitBean commitBean = new ReserveBedLocationscommitBean();
			final ReserveBedLocations bean = new ReserveBedLocations();
			final List<ReserveBedLocations> insertList = new ArrayList<>();
			bean.setAgyLocId("ITAG");
			bean.setCommentText("Test Offender");
			bean.setLivingUnitId(BigDecimal.valueOf(5357));
			bean.setOffenderId(BigDecimal.valueOf(1019572));
			bean.setReserveUntilDate(new Date());
			insertList.add(bean);
			commitBean.setInsertList(insertList);
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(commitBean);
			ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidrhloc/resBlCommit", commitBean).contentType(MediaType.APPLICATION_JSON)
							.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
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
	public void reserveBedLocationsRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oidrhloc/cgfkResBlAgyLocIdRecordGroup?caseloadId=ITAG")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("CLARK COUNTY")));
		} catch (Exception e) {
			logger.error("In method reserveBedLocationsRecordGroupTestMethod", e);
		}
	}
}