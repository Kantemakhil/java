package net.syscon.s4.services.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.common.beans.VOffExmCommitBean;
import net.syscon.s4.common.beans.VOffExm;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EliteSpringConfig.class })
@WebAppConfiguration
@WithMockCustomUser
public class OiditranControllerMockTestCase extends AbstractMockTestCase  {
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OiditranControllerMockTestCase.class);


	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void vOffExmExecuteQueryTestMethod() {
	try {
		VOffExm voffExm= new VOffExm();
		voffExm.setOffenderBookId(20444);
		final ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(voffExm);
		ResultActions resultActions = this.mockMvc
				.perform(post("/api/oiditran/offEmExecuteQueryVoffExm").contentType(MediaType.APPLICATION_JSON)
						.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print());
		resultActions.andExpect(jsonPath("$[0].movementSeq", is(1)));
	} catch (Exception e) {
		logger.error("offEmExecuteQueryVoffExm", e);
	}
}
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@Test
	public void vOffExmCommitTestMethod() {
	try {
		
			VOffExm vOffExm = new VOffExm();
			vOffExm.setOffenderBookId(19965);
			vOffExm.setMovementSeq(1);
			vOffExm.setMovementDate(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			vOffExm.setMovementTime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
//			vOffExm.setInternalScheduleType("");
//			vOffExm.setInternalScheduleReasonCode("");
//			vOffExm.setMovementType("");
//			vOffExm.setMovementReasonCode("");
//            vOffExm.setDirectionCode("");
		
//			vOffExm.setArrestAgencyLocId("");
//			vOffExm.setToProvStatCode("");
//			vOffExm.setEscortCode("");
//			vOffExm.setFromAgyLocId("");
//			vOffExm.setToAgyLocId("");
			vOffExm.setActiveFlag("Y");
//			vOffExm.setEscortText("");
			vOffExm.setCommentText("Hello");
//			vOffExm.setReportingDate(new Timestamp(0, 0, 0, 0, 0, 0, 0));
			vOffExm.setToCity("ARKIN");
			vOffExm.setFromCity("");
			vOffExm.setReportingTime(new Timestamp(0, 0, 0, 0, 0, 0, 0));
//	//		vOffExm.setInserted();
//			vOffExm.setErrorMessage("");
//			vOffExm.setDspFirstName("");
//	//    	vOffExm.setDspLastNamee("");
//			vOffExm.setOffenderDisplayId("");
			List<VOffExm> updateList = new ArrayList<VOffExm>();
			updateList.add(vOffExm);
			VOffExmCommitBean VOffExmCommitBean = new VOffExmCommitBean(); 
			VOffExmCommitBean.setUpdateList(updateList);
			String jsonInString = new ObjectMapper().writeValueAsString(VOffExmCommitBean);
				
         ResultActions resultActions = this.mockMvc
		.perform(post("/api/oiditran/offEmCommit").contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString).accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print());
resultActions.andExpect(jsonPath("$", is(1)));
} catch (Exception e) {
logger.error("OffEmCommit", e);
}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOffExmmoveRsnLovRecordGroup() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiditran/moveRsnLovRecordGroup").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].description", is("Administrative")));
		} catch (Exception e) {
			logger.error("MoveRsnLovRecordGroup", e);
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@Test
	public void vOffExmRecordGroupTestMethod() {
		try {
			ResultActions resultActions = this.mockMvc
					.perform(get("/api/oiditran/cgfkOffEmToAgyLocIdRecordGroup?caseloadId=ITAG").accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
			resultActions.andExpect(jsonPath("$[0].agyLocId", is("CPAR")));
		} catch (Exception e) {
			logger.error("cgfkOffEmToAgyLocIdRecordGroup", e);
		}
	}

}