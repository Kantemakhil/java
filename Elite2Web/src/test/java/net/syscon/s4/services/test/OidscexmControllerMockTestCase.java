package net.syscon.s4.services.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.services.config.EliteSpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EliteSpringConfig.class})
@WebAppConfiguration
@WithMockCustomUser
public class OidscexmControllerMockTestCase extends AbstractMockTestCase {
	
	@Test
	public void deactivateOffender() {
		VOffenderAllSchedules searchResult = new VOffenderAllSchedules();
		searchResult.setOffenderBookId(BigDecimal.valueOf(18733));
		searchResult.setEventSubType("ADMN");
		searchResult.setEventType("TRN");
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(searchResult);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidscexm/processExternalMovement", searchResult)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
//			resultActions.andExpect(jsonPath("", is()));
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@Test
	public void updateOffenderInOutStatus() {
		VOffenderAllSchedules searchResult = new VOffenderAllSchedules();
		searchResult.setEventId(BigDecimal.valueOf(100895));
		searchResult.setOffenderBookId(BigDecimal.valueOf(21087));
		searchResult.setEventSubType("MED");
		searchResult.setEventType("APP");
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(searchResult);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidscexm/processExternalMovement", searchResult)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
//			resultActions.andExpect(jsonPath("", is()));
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@Test
	public void insertReturnSchedule() {
		VOffenderAllSchedules searchResult = new VOffenderAllSchedules();
		searchResult.setEventId(BigDecimal.valueOf(100895));
		searchResult.setOffenderBookId(BigDecimal.valueOf(21087));
		searchResult.setEventSubType("MED");
		searchResult.setEventType("TAP");
		searchResult.setDirectionCode("IN");
		searchResult.setEventStatus("SCH");
		searchResult.setfMTime(new Date());
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonInString = mapper.writeValueAsString(searchResult);
			final ResultActions resultActions = this.mockMvc
					.perform(post("/api/oidscexm/processExternalMovement", searchResult)
							.contentType(MediaType.APPLICATION_JSON).content(jsonInString)
							.accept(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print());
//			resultActions.andExpect(jsonPath("", is()));
		} catch (Exception e) {
			logger.error(e);
		}
	}
	/* @Before 
	 public void beforeMethod() { 
	 	 SpringContextLocator.setDataSourceDetails("testDatasource.xml"); 
	 } 

 	 @Test 
 	 public void rgMoveTypeRgroup() throws SQLException {
	 	OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 List<ReferenceCodes> result = obj.rgMoveTypeRgroup();
	 	 // TODO write Assert methods to test
 	}
 	 @Test 
 	 public void rgBuildingRgroup() throws SQLException {
	 	OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 List<ReferenceCodes> result = obj.rgBuildingRgroup();
	 	 // TODO write Assert methods to test
 	}
 	 @Test 
 	 public void rgAgyIdRgroup() throws SQLException {
	 	OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 List<ReferenceCodes> result = obj.rgAgyIdRgroup();
	 	 // TODO write Assert methods to test
 	}
 	 @Test 
 	 public void rgTierRgroup() throws SQLException {
	 	OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 List<ReferenceCodes> result = obj.rgTierRgroup();
	 	 // TODO write Assert methods to test
 	}
 	 @Test 
 	 public void  processExternalMovement() throws SQLException {
	 	BedAssignmentHistories paramBean = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 processExternalMovement(BedAssignmentHistories result = obj. processExternalMovement(paramBean);
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  rgBuildingRecordGroup() throws SQLException {
	 	 = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 rgBuildingRecordGroup() result = obj. rgBuildingRecordGroup();
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  rgTierRecordGroup() throws SQLException {
	 	 = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 rgTierRecordGroup() result = obj. rgTierRecordGroup();
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  offSchExecuteQuery() throws SQLException {
	 	VOffenderAllSchedules objVOffenderAllSchedules = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 offSchExecuteQuery(VOffenderAllSchedules result = obj. offSchExecuteQuery(objVOffenderAllSchedules);
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  chkNotification() throws SQLException {
	 	OffenderPendNotifications paramBean = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 chkNotification(OffenderPendNotifications result = obj. chkNotification(paramBean);
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  rgMoveTypeRecordGroup() throws SQLException {
	 	 = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 rgMoveTypeRecordGroup() result = obj. rgMoveTypeRecordGroup();
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  rgAgyIdRecordGroup() throws SQLException {
	 	 = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 rgAgyIdRecordGroup() result = obj. rgAgyIdRecordGroup();
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  processExternalMovement() throws SQLException {
	 	OffenderBookings paramBean = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 processExternalMovement(OffenderBookings result = obj. processExternalMovement(paramBean);
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  offSchUpdateVOffenderAllSchedules() throws SQLException {
	 	List<VOffenderAllSchedules> lstVOffenderAllSchedules = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 offSchUpdateVOffenderAllSchedules(List<VOffenderAllSchedules> result = obj. offSchUpdateVOffenderAllSchedules(lstVOffenderAllSchedules);
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  chkNotification() throws SQLException {
	 	OffenderNotCompletions paramBean = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 chkNotification(OffenderNotCompletions result = obj. chkNotification(paramBean);
	 	 // TODO write Assert methods to test	 
 }
 	 @Test 
 	 public void  createFormGlobalsCREATE_FORM_GLOBALS() throws SQLException {
	 	OmsModules paramBean = null;
 	 	 OidscexmRepository obj = ConfigDaoManager.getidscexmRepository();
	 	 createFormGlobalsCREATE_FORM_GLOBALS(OmsModules result = obj. createFormGlobalsCREATE_FORM_GLOBALS(paramBean);
	 	 // TODO write Assert methods to test	 
 }*/
}
