package net.syscon.s4.pkgs.tag_sealer.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.tag_sealer.TagSealerRepository;
import net.syscon.s4.pkgs.tag_sealer.TagSealerService;
import net.syscon.s4.sa.admin.impl.OumpurgeServiceImpl;
@Service
public class TagSealerServiceImpl extends RepositoryBase implements TagSealerService {
	//@Autowired
	//private SavepointManager savePoint;
	@Autowired
	private TagSealerRepository tagSealerRepository;

	private static final String VPDSSOWNER="OMS_OWNER";

	private static final String VPDMARK="SEAL_FLAG"; 

	private static final String VPDKEYCOL02="OFFENDER_BOOK_ID";

	private static final String VPDKEYCOL03="ROOT_OFFENDER_ID";

	private static final String VPDKEYCOL01="OFFENDER_ID";
    
	private static Logger logger = LogManager.getLogger(OumpurgeServiceImpl.class.getName());


	//  seal particular offender booking information
	@Override
	public void sealOffender(BigDecimal pOffenderId,String userName) throws Exception {
		BigDecimal prid=null;
		getSavePoint("start_seal");
		//attempt to lock all records
		lockResources(pOffenderId);
		String sealFlagValueWhenSeal="Y";

		gRid(pOffenderId).forEach(i->{
			gTabs(VPDKEYCOL03).forEach(j->{
				buildSql(j.getTableName(), VPDKEYCOL03, null,i.getRootOffenderId(),sealFlagValueWhenSeal,userName);
			});//j
			//OFFENDER_CONTACT_PERSONS
			buildSql("OFFENDER_CONTACT_PERSONS","CONTACT_ROOT_OFFENDER_ID",null,i.getRootOffenderId(),sealFlagValueWhenSeal,userName);
		});//i
		//OFFENDER_ID
		for(Offenders i: gOid(pOffenderId)) {
			gTabs(VPDKEYCOL01).forEach(j->{
				buildSql(j.getTableName(), VPDKEYCOL01, null,BigDecimal.valueOf(i.getOffenderId()),sealFlagValueWhenSeal,userName); 
			});//j
			//OFFENDER_NON_ASSOCIATIONS
			buildSql("OFFENDER_NON_ASSOCIATIONS", "NS_OFFENDER_ID", null,BigDecimal.valueOf(i.getOffenderId()),sealFlagValueWhenSeal,userName);
			//OFFENDER_NA_DETAILS
			buildSql("OFFENDER_NA_DETAILS", "NS_OFFENDER_ID", null,BigDecimal.valueOf(i.getOffenderId()),sealFlagValueWhenSeal,userName);
			//all BOOKINGS for given OFFENDER_ID
			for(OffenderBookings k: gBid(i.getOffenderId()!=null?new BigDecimal(i.getOffenderId()):null)) {
				gTabs(VPDKEYCOL02).forEach(l->{
					buildSql(l.getTableName(),VPDKEYCOL02,null,BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);  
				});//l
				//hpqc#15282
				prid= getIndRid(k.getOffenderBookId()!=null?new BigDecimal(k.getOffenderBookId()):null);
				//ADDRESSES
				buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				//PHONES
				buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				//INTERNET_ADDRESSES
				buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				//IMAGES
				buildSql("IMAGES", "IMAGE_OBJECT_ID", "IMAGE_OBJECT_TYPE IN ( 'OFF_IDM','OFF_BKG' )",BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				//OFFENDER_CASE_ASSOCIATIONS
				buildSql("OFFENDER_CASE_ASSOCIATIONS", "ASSOCIATED_OFF_BOOK_ID",null,BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal,userName);
				//QM_PROCESSES_INS
				//buildSql("QM_PROCESSES_INS", "DETAIL", null,BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal);
				//QM_OBJECT_INS
				//buildSql("QM_OBJECT_INS", "VALUE", null,BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal);
				//QM_PROCESSES_INS_HIST
				//buildSql("QM_PROCESSES_INS_HIST", "DETAIL", null,BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal);
				//QM_OBJECT_INS_HIST
				//buildSql("QM_OBJECT_INS_HIST", "VALUE", null,BigDecimal.valueOf(k.getOffenderBookId()),sealFlagValueWhenSeal);
			};//k
		};//i
	}

	@Override
	public void lockBookingResources(BigDecimal pOffBookId) {
		// all BOOKINGS for given OFFENDER_BOOK_ID
		gTabs(pOffBookId+"").forEach(val->buildLockSql(val.getTableName(), VPDKEYCOL02, null,pOffBookId));;
		//ADDRESSES
		buildLockSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF','OFF_EDU','OFF_EMP' )",pOffBookId);
		//PHONES
		buildLockSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",pOffBookId);
		//INTERNET_ADDRESSES
		buildLockSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",pOffBookId);
		//IMAGES
		buildLockSql("IMAGES", "IMAGE_OBJECT_ID", "IMAGE_OBJECT_TYPE IN ( 'OFF_IDM','OFF_BKG' )",pOffBookId);
		//OFFENDER_CASE_ASSOCIATIONS
		buildLockSql("OFFENDER_CASE_ASSOCIATIONS", "ASSOCIATED_OFF_BOOK_ID",null,pOffBookId);
		//QM_PROCESSES_INS
		buildLockSql("QM_PROCESSES_INS", "DETAIL",null,pOffBookId);
		//QM_OBJECT_INS
		buildLockSql("QM_OBJECT_INS", "VALUE",null,pOffBookId);
		//QM_PROCESSES_INS_HIST
		buildLockSql("QM_PROCESSES_INS_HIST", "DETAIL",null,pOffBookId);
		//QM_OBJECT_INS_HIST
		buildLockSql("QM_OBJECT_INS_HIST", "VALUE",null,pOffBookId);
	}

	@Override
	public List<Offenders> gTabs(String keyCol) {
		return tagSealerRepository.gTabs(keyCol, VPDSSOWNER);
	}

	@Override
	public List<OffenderBookings> buildLockSql(String pTabName, String pSecondName, String pExtraW, BigDecimal offenderValue) {
		String ret="";
		ret="select " + VPDMARK + " from " + VPDSSOWNER + '.' +pTabName + " where " + pSecondName + "="+offenderValue;
		if(pExtraW!=null) 
			ret = ret + " AND " + pExtraW;
//		ret = ret + " for update wait " +1;//+lock_wait_time;
		//ret = ret + "for update";
		return tagSealerRepository.buildLockSql(ret);
	}

	@Override
	public List<Offenders> gRid(BigDecimal pOffId) {
		return tagSealerRepository.gRid(pOffId);
	}

	@Override
	public void lockResources(BigDecimal pOffenderId) {
		//seal all relevant ROOT_OFFENDER_ID
		gRid(pOffenderId).forEach(i->{
			gTabs(VPDKEYCOL03).forEach(j->{
				buildLockSql(j.getTableName(), VPDKEYCOL03, null,i.getRootOffenderId());
			});
			//OFFENDER_CONTACT_PERSONS
			buildLockSql("OFFENDER_CONTACT_PERSONS","CONTACT_ROOT_OFFENDER_ID",null,i.getRootOffenderId());
		});

		//OFFENDER_ID
		gOid(pOffenderId).forEach(i->{
			gTabs(VPDKEYCOL01).forEach(j->{
				buildLockSql(j.getTableName(),VPDKEYCOL01,null,BigDecimal.valueOf(i.getOffenderId()));
			});//j
			//OFFENDER_NON_ASSOCIATIONS
			buildLockSql("OFFENDER_NON_ASSOCIATIONS","NS_OFFENDER_ID",null,BigDecimal.valueOf(i.getOffenderId()));
			//OFFENDER_NA_DETAILS
			buildLockSql("OFFENDER_NA_DETAILS","NS_OFFENDER_ID",null,BigDecimal.valueOf(i.getOffenderId()));
			gBid(i.getOffenderId()!=null?new BigDecimal(i.getOffenderId()):null).forEach(k->{
				gTabs(VPDKEYCOL02).forEach(l->{
					buildLockSql(l.getTableName(),VPDKEYCOL02,null,BigDecimal.valueOf(k.getOffenderBookId()));
				});//l
				//ADDRESSES
				buildLockSql("ADDRESSES","OWNER_ID","OWNER_CLASS IN ( 'OFF','OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()));
				//PHONES
				buildLockSql("PHONES","OWNER_ID","OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()));
				//INTERNET_ADDRESSES
				buildLockSql("INTERNET_ADDRESSES","OWNER_ID","OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()));
				//IMAGES
				buildLockSql("IMAGES","IMAGE_OBJECT_ID","IMAGE_OBJECT_TYPE IN ( 'OFF_IDM','OFF_BKG' )",BigDecimal.valueOf(k.getOffenderBookId()));
				//OFFENDER_CASE_ASSOCIATIONS
				buildLockSql("OFFENDER_CASE_ASSOCIATIONS","ASSOCIATED_OFF_BOOK_ID",null,BigDecimal.valueOf(k.getOffenderBookId()));
				//QM_PROCESSES_INS
				//buildLockSql("QM_PROCESSES_INS","DETAIL",null,BigDecimal.valueOf(k.getOffenderBookId()));
				//QM_OBJECT_INS
				//buildLockSql("QM_OBJECT_INS","VALUE",null,BigDecimal.valueOf(k.getOffenderBookId()));
				//QM_PROCESSES_INS_HIST
				//buildLockSql("QM_PROCESSES_INS_HIST","DETAIL",null,BigDecimal.valueOf(k.getOffenderBookId()));
				//QM_OBJECT_INS_HIST
				//buildLockSql("QM_OBJECT_INS_HIST","VALUE",null,BigDecimal.valueOf(k.getOffenderBookId()));
			});//k
		});//i
	}//method

	@Override
	public List<Offenders> gOid(BigDecimal pOffId) {
		return tagSealerRepository.gOid(pOffId);
	}

	@Override
	@Transactional
	public Integer buildSql(String pTabName, String pSecondName, String pExtraW, BigDecimal offenderValue, String sealFlagValueWhenSeal,String userName) {
		pSecondName=pSecondName.toLowerCase();
		pTabName=pTabName.toLowerCase();
		String table="";String column="";
		Integer buildUpd =null;
		try {
		String dataType=tagSealerRepository.getDataType(pTabName, pSecondName);
		String array[]=dataType.split(",");
		table=array[0];
		column=array[1];
		String ret="";
		if(table!=null && column.equalsIgnoreCase("character varying")) {
		ret= "update " + VPDSSOWNER + '.' + pTabName + " set " + VPDMARK + "=" + "'"+sealFlagValueWhenSeal+"'"+ ","+"modify_datetime"+ "="+"current_timestamp"+ "," +"modify_user_id"+ "="+"'"+ userName+"'"+ " where " + pSecondName +"=" +offenderValue;
		}else {
		ret= "update " + VPDSSOWNER + '.' + pTabName + " set " + VPDMARK +  "=" + "'"+sealFlagValueWhenSeal+"'" + ","+"modify_datetime"+ "="+"current_timestamp"+ "," +"modify_user_id"+ "="+"'"+ userName+"'" + " where " + pSecondName +"=" +offenderValue;
		}
		if(pExtraW!=null)
			ret= ret +" AND " + pExtraW;						
			if(table!=null && !table.equals("")) {		
				buildUpd= tagSealerRepository.buildSql(ret);
			} 	
		}catch (Exception e) {
			logger.error("Error in buildSql when updating the seal"+e);
		}
		return buildUpd;
	}

	@Override
	public List<OffenderBookings> gBid(BigDecimal pOffId) {
		return tagSealerRepository.gBid(pOffId);
	}

	@Override
	public BigDecimal getIndRid(BigDecimal pOb) {
		return tagSealerRepository.getIndRid(pOb);
	}

	@Override
	public void unsealOffender(BigDecimal pOffenderId,String userName) throws Exception{
		BigDecimal pRid=null;
		String unsealFlagValue="N";
		//SAVEPOINT start_unseal;
//		getSavePoint("start_unseal");
		lockResources(pOffenderId);
		gRid(pOffenderId).forEach(i->{
			gTabs(VPDKEYCOL03).forEach(j->buildSql(j.getTableName(), VPDKEYCOL03, null,i.getRootOffenderId(),unsealFlagValue,userName));
			//OFFENDER_CONTACT_PERSONS
			buildSql("OFFENDER_CONTACT_PERSONS", "CONTACT_ROOT_OFFENDER_ID", null,i.getRootOffenderId(),unsealFlagValue,userName);
		});//i
		for(Offenders j: gOid(pOffenderId)){
			gTabs(VPDKEYCOL01).forEach(i->buildSql(i.getTableName(),VPDKEYCOL01, null,BigDecimal.valueOf(j.getOffenderId()),unsealFlagValue,userName));
			//OFFENDER_NON_ASSOCIATIONS
			buildSql("OFFENDER_NON_ASSOCIATIONS", "NS_OFFENDER_ID",null,BigDecimal.valueOf(j.getOffenderId()),unsealFlagValue,userName);
			//OFFENDER_NA_DETAILS
			buildSql("OFFENDER_NA_DETAILS", "NS_OFFENDER_ID",null,BigDecimal.valueOf(j.getOffenderId()),unsealFlagValue,userName);
			//OFFENDER_BOOK_ID
			for(OffenderBookings k: gBid(j.getOffenderId()!=null?new BigDecimal(j.getOffenderId()):null)){
				gTabs(VPDKEYCOL02).forEach(l->buildSql(l.getTableName(), VPDKEYCOL02, null,BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName));
				pRid= getIndRid(k.getOffenderBookId()!=null?new BigDecimal(k.getOffenderBookId()):null);
				//ADDRESSES
				buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//PHONES
				buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//INTERNET_ADDRESSES
				buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//IMAGES
				buildSql("IMAGES", "IMAGE_OBJECT_ID", "IMAGE_OBJECT_TYPE IN ( 'OFF_IDM','OFF_BKG' )",BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//OFFENDER_CASE_ASSOCIATIONS
				buildSql("OFFENDER_CASE_ASSOCIATIONS", "ASSOCIATED_OFF_BOOK_ID", null,BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//QM_PROCESSES_INS
				//buildSql("QM_PROCESSES_INS", "DETAIL", null,BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//QM_OBJECT_INS
				//buildSql("QM_OBJECT_INS", "VALUE", null,BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//QM_PROCESSES_INS_HIST
				//buildSql("QM_PROCESSES_INS_HIST", "DETAIL", null,BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
				//QM_OBJECT_INS_HIST
				//buildSql("QM_OBJECT_INS_HIST", "VALUE", null,BigDecimal.valueOf(k.getOffenderBookId()),unsealFlagValue,userName);
			}//k
		};//j
	}//method

	@Override
	public void sealOffenderBooking(BigDecimal pOffenderBookId,String userName) throws Exception {
		BigDecimal pRid=null;
		// SAVEPOINT start_seal;
//		getSavePoint("start_seal");
		String sealFlagValuewhenSeal="Y";
		lockBookingResources(pOffenderBookId);
		gTabs(VPDKEYCOL02).forEach(l->buildSql(l.getTableName(), VPDKEYCOL02, null,pOffenderBookId,sealFlagValuewhenSeal,userName));
		pRid=getIndRid(pOffenderBookId); 
		//ADDRESSES
		buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",pOffenderBookId,sealFlagValuewhenSeal,userName);
		buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",pOffenderBookId,sealFlagValuewhenSeal,userName);
		//PHONES
		buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",pOffenderBookId,sealFlagValuewhenSeal,userName);
		buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF' )",pOffenderBookId,sealFlagValuewhenSeal,userName);
		//INTERNET_ADDRESSES
		buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF_EDU','OFF_EMP' )",pOffenderBookId,sealFlagValuewhenSeal,userName);
		buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( 'OFF')",pOffenderBookId,sealFlagValuewhenSeal,userName);
		//IMAGES
		buildSql("IMAGES", "IMAGE_OBJECT_ID", "IMAGE_OBJECT_TYPE IN ( 'OFF_IDM','OFF_BKG' )",pOffenderBookId,sealFlagValuewhenSeal,userName);
		//OFFENDER_CASE_ASSOCIATIONS
		buildSql("OFFENDER_CASE_ASSOCIATIONS", "ASSOCIATED_OFF_BOOK_ID", null,pOffenderBookId,sealFlagValuewhenSeal,userName);
		//QM_PROCESSES_INS
		//buildSql("QM_PROCESSES_INS", "DETAIL", null,pOffenderBookId,sealFlagValuewhenSeal,userName);
		//QM_OBJECT_INS
		//buildSql("QM_OBJECT_INS", "VALUE", null,pOffenderBookId,sealFlagValuewhenSeal,userName);
		//QM_PROCESSES_INS_HIST
		//buildSql("QM_PROCESSES_INS_HIST", "DETAIL", null,pOffenderBookId,sealFlagValuewhenSeal,userName);
		//QM_OBJECT_INS_HIST
		//buildSql("QM_OBJECT_INS_HIST", "VALUE", null,pOffenderBookId,sealFlagValuewhenSeal,userName);
	}

	@Override
	public void unsealOffenderBooking(BigDecimal pOffenderBookId,String userName) throws Exception{
		BigDecimal pRid=null;
		//SAVEPOINT start_unseal;
		getSavePoint("start_unseal");
		String sealgFlagValueWhenUnseal="N";
		//attempt to lock all records
		//lockBookingResources(pOffenderBookId);
		//OFFENDER_BOOK_ID
		gTabs(VPDKEYCOL02).forEach(l->buildSql(l.getTableName(), VPDKEYCOL02, null,pOffenderBookId,sealgFlagValueWhenUnseal,userName));
		//hpqc#15282
		pRid=getIndRid(pOffenderBookId);
		//ADDRESSES
		buildSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( ''OFF_EDU'',''OFF_EMP'' )",pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		buildLockSql("ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( ''OFF'' )",pOffenderBookId);
		//PHONES
		buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( ''OFF_EDU'',''OFF_EMP'' )",pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		buildSql("PHONES", "OWNER_ID", "OWNER_CLASS IN ( ''OFF'' )",pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//INTERNET_ADDRESSES
		buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( ''OFF_EDU'',''OFF_EMP'' )",pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		buildSql("INTERNET_ADDRESSES", "OWNER_ID", "OWNER_CLASS IN ( ''OFF'' )",pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//IMAGES
		buildSql("IMAGES", "IMAGE_OBJECT_ID", "IMAGE_OBJECT_TYPE IN ( ''OFF_IDM'',''OFF_BKG'' )",pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//OFFENDER_CASE_ASSOCIATIONS
		buildSql("OFFENDER_CASE_ASSOCIATIONS", "ASSOCIATED_OFF_BOOK_ID", null,pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//QM_PROCESSES_INS
		//buildSql("QM_PROCESSES_INS", "DETAIL", null,pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//QM_OBJECT_INS
		//buildSql("QM_OBJECT_INS", "VALUE", null,pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//QM_PROCESSES_INS_HIST
		//buildSql("QM_PROCESSES_INS_HIST", "DETAIL", null,pOffenderBookId,sealgFlagValueWhenUnseal,userName);
		//QM_OBJECT_INS_HIST
		//buildSql("QM_OBJECT_INS_HIST", "VALUE", null,pOffenderBookId,sealgFlagValueWhenUnseal,userName);
	}//method

	public Savepoint getSavePoint(String savePoint) throws Exception {
		Savepoint save=null;
		try(Connection conn = dataSource.getConnection()) {
			
			save=conn.setSavepoint(savePoint);
		}catch(Exception e) {
			logger.error("getSavePoint"+e);
		}
		return save;
	}
}
