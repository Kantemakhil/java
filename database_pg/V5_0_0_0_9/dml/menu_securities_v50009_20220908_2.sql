update OMS_OWNER.MENU_SECURITIES set menu_item = 'Court Reports' where module_name = 'OCDPSREP';
update OMS_OWNER.MENU_SECURITIES set parent_menu_id = (select menu_id from menu_securities where menu_item='Legals/Sentence Calculation') ,sort_order  = 2  where module_name = 'OCDPSREP';

--
---
--INST--
INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Legals/Sentence Calculation'), 'Legal Summary', 'OCDLEGLS', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Legals/Sentence Calculation'), 'Legal Orders', 'OCDLEGLO', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Legals/Sentence Calculation'), 'Charge Summary', 'OCDCHGSU', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Legals/Sentence Calculation'), 'Court Events', 'OIDCRTEV', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Legals/Sentence Calculation'), 'Non Custodial Orders', 'OCDNCODE', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);


--COMM--
INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Court Cases & Orders'), 'Legal Summary', 'OCDLEGLS', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Court Cases & Orders'), 'Legal Orders', 'OCDLEGLO', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Court Cases & Orders'), 'Charge Summary', 'OCDCHGSU', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Court Cases & Orders'), 'Court Events', 'OIDCRTEV', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Court Cases & Orders'), 'Non Custodial Orders', 'OCDNCODE', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

---

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'),(select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities ms where menu_item='System Administration') and menu_item='System Automation'), 'Export Quick Action and API', 'OIEXPQAC',8, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
  
--
INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'),(select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities ms where menu_item='System Administration') and menu_item='System Automation'), 'Import Quick Action API', 'OIIMPQAC',9, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
    
--
INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'),(select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities ms where menu_item='System Administration') and menu_item='System Automation'), 'Export process', 'OIEXPPRO',10, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);

--

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'),(select menu_id from menu_securities where parent_menu_id=(select menu_id from menu_securities ms where menu_item='System Administration') and menu_item='System Automation'), 'Import Process', 'OIIMPPRO',11, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
     	
--
INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'), (select d.menu_id from MENU_SECURITIES d where d.menu_item = 'Community Case Management'), 'Weekly Activity Plan', 
    NULL,(select max(m.sort_order)+1 from MENU_SECURITIES m where m.parent_menu_id = (select ms.menu_id from MENU_SECURITIES ms where ms.menu_item = 'Community Case Management')) , 
    current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);  

--

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'), (select max(d.menu_id) from MENU_SECURITIES d where d.menu_item = 'Weekly Activity Plan' and d.MODULE_NAME is NULL), 'Weekly Activity Plan', 
    'OWEACPLN', 1, 
    current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);	
	
---

delete from MENU_SECURITIES where MODULE_NAME = 'ODYNFRM';
delete from MENU_SECURITIES where menu_item = 'menu testing';

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (select menu_id from menu_securities where menu_item='Demographics'), 'Keep Separates', 'OIDONONA', 22, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);

--

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG) 
    VALUES(nextval('MENU_ID'),(select menu_id from menu_securities where menu_item='System Automation'), 'Common Process', 'CMNPROSS',3, CURRENT_TIMESTAMP, 'OMS_OWNER', CURRENT_TIMESTAMP, NULL, NULL);
   
---

update menu_securities set parent_menu_id = (select min(ms1.menu_id) from menu_securities ms1 where ms1.menu_item = 'Programs & Services')
where module_name = 'OCDPROGR' and menu_item = 'Offender Programs';

update menu_securities set parent_menu_id = (select max(ms1.menu_id) from menu_securities ms1 where ms1.menu_item = 'Programs & Services')
where module_name = 'OCDPROGR' and menu_item = 'Offender Programs & Evaluation Measures';

--  

update menu_securities set parent_menu_id = 10000000000 where module_name in (
'OCDBRECI',
'OCDCASHR',
'OCDCBENE',
'OCDCPPAY',
'OCDENFOR',
'OCDOOBLI',
'OCDOTFEE',
'OCDPATTE',
'OCDPAYOB',
'OCDRECEI',
'OCDUATTE',
'OCICBENE',
'OCIDOACC',
'OCIPBENE',
'OCMCOACT',
'OCMDEDUT',
'OCMMPBAL',
'OCMSNOTI',
'OCMTRANS',
'OCMTROPS',
'OCSRECEI',
'OSUREPOR',
'OTDAACCO',
'OTDAGJTR',
'OTDBACRE',
'OTDCLINA',
'OTDCLOSE',
'OTDCNTAC',
'OTDCRVOI',
'OTDDISBU',
'OTDGLIRT',
'OTDHIREM',
'OTDHOLDT',
'OTDMGJTR',
'OTDOALLO',
'OTDOCFEE',
'OTDOFREZ',
'OTDOPCTA',
'OTDRDTFU',
'OTDRECEI',
'OTDRTTFU',
'OTDSDEDU',
'OTDSUBAT',
'OTDTTACC',
'OTIDTACC',
'OTIGLBAL',
'OTINAMES',
'OTIOPINQ',
'OTMACPRD',
'OTMALPRO',
'OTMBACCO',
'OTMCNSER',
'OTMCOPRO',
'OTMCPRIN',
'OTMDPRIO',
'OTMFOPRO',
'OTMFREEZ',
'OTMISAMB',
'OTMMBALA',
'OTMONCOA',
'OTMTFPRO',
'OTSRECEI',
'OTSTASTA',
'AGINTLOC',
'ALLFRM1',
'ATMONTRN',
'CICONNEC',
'OBDCONBI',
'OBDMCHRG',
'OBDOBINQ',
'OBDOBPRO',
'OBMABILP',
'OBMABPRO',
'OCDADJBE',
'OCDAMEND',
'OCDAWORK',
'OCDCASES',
'OCDCCASE',
'OCDCIREQ',
'OCDCREFU',
'OCDCSENT',
'OCDCVWIT',
'OCDDORSN',
'OCDGENSN',
'OCDHEALT',
'OCDMWORK',
'OCDSMEMO',
'OCDSNVER',
'OCIDOCUM',
'OCISENRE',
'OCMTNAVI',
'OCUAOFFE',
'OCUARRES',
'OCUCOMBC',
'OCURNUMB',
'OIDALIAS',
'OIDAPPOR',
'OIDBAILB',
'OIDCOMCY',
'OIDFIXAD',
'OIDGANGA',
'OIDGNONA',
'OIDHALRT',
'OIDHSCTR',
'OIDIDETA',
'OIDIICLO',
'OIDINCRS',
'OIDMEDIC',
'OIDMPCON',
'OIDNCOMP',
'OIDOFFOB',
'OIDOJOIN',
'OIDOMAIL',
'OIDOWREL',
'OIDPWAIT',
'OIDREMEA',
'OIDRNOTI',
'OIDRPITM',
'OIDSENAD',
'OIDSLTRI',
'OIDTPRIT',
'OIDVCCHA',
'OIDVICTM',
'OIDWLOWR',
'OIIAOOFF',
'OIICOURT',
'OIIGNGAF',
'OIIHIRME',
'OIIHLOCA',
'OIIIMVHS',
'OIIIRSEN',
'OIIISINQ',
'OIINOTIF',
'OIIOFFOB',
'OIIORDER',
'OIIORSEN',
'OIIOTARO',
'OIIPINQU',
'OIITASCH',
'OIITROUT',
'OIMAVCLS',
'OIMCIPST',
'OIMCLASS',
'OIMCLBUN',
'OIMGNGCH',
'OIMHCRCO',
'OIMIRSMA',
'OIMMQSTN',
'OIMMQUES',
'OIMOFFOB',
'OIMROUTE',
'OIMRSCED',
'OIMSGLEN',
'OIMSTRIP',
'OIMWORKR',
'OMSALJNT',
'OMSRELST',
'OMSYSJNT',
'OMUPSINQ',
'OODAGJTR',
'OODBACLR',
'OODBAREC',
'OODCASHR',
'OODCFORD',
'OODCLVNP',
'OODCONPC',
'OODCOUNT',
'OODCRVOI',
'OODGLIRT',
'OODIADJU',
'OODIBRPO',
'OODMGJTR',
'OODMPORS',
'OODNRETU',
'OODNSALE',
'OODORETU',
'OODOSALE',
'OODOSILI',
'OODPINVO',
'OODPROPL',
'OODPRTVI',
'OODRSTVE',
'OODSALER',
'OODSTOCK',
'OODTRANS',
'OODVENDO',
'OOIGLBAL',
'OOIOSALE',
'OOMBARID',
'OOMBCACC',
'OOMCNSER',
'OOMCOACC',
'OOMCPRIN',
'OOMCSLIM',
'OOMINCSC',
'OOMNOFFS',
'OOMOCRES',
'OOMONCOA',
'OOMOPOSF',
'OOMPLSTA',
'OOMQULIM',
'OOMREPMS',
'OOMSCATE',
'OOMSCRST',
'OOMSTAFF',
'OOMSTTYP',
'OOMTAXRA',
'OOMTRANS',
'OOMTROPS',
'OOMWORKL',
'OORCHECK',
'OOSCANIN',
'OSIADDRE',
'OSIAFISS',
'OSICASID',
'OTDAUREC',
'OTDPAYRO',
'OTIPBATC',
'OTMARMAP',
'OTMCSLIM',
'OTMDEMOG',
'OTMIDTRN',
'OTMOFLIM',
'OTMOTXPR',
'OTMPDLIM',
'OTMSCPAY',
'OTMTAXRE',
'OTMTXRAT',
'OTRCHECK',
'OTSDJLOG',
'OTSINDIS',
'OTSTAXRE',
'OTSTXGEN',
'OUIADACT',
'OUIAFLAT',
'OUIAUACT',
'OUMAGENC',
'OUMAPASS',
'OUMASMNT',
'OUMAUDIT',
'OUMCFPRI',
'OUMCLFRM',
'OUMCLPAR',
'OUMCPASS',
'OUMDTMAC',
'OUMEEMOV',
'OUMMEROF',
'OUMMERPR',
'OUMMEVNT',
'OUMMSSG',
'OUMOFFQU',
'OUMOISPA',
'OUMPDEFI',
'OUMPTACA',
'OUMSEQNO',
'OUMSORTO',
'OYDCGPAY',
'OYDEOFFU',
'OYDOASSI',
'OYMACYCL',
'OYMCOMPE',
'OYMEASSI',
'OYMWGROU',
'OCDBIREV',
'OCDPROGR',
'OCIPPHIS',
'OCUADJCR',
'OCUAUTHR',
'OCUCORPT',
'OCUDPDIS',
'OCUGLTRD',
'OCUOBADD',
'OCUOBHIS',
'OCUOSCPV',
'OCUOTRAH',
'OCUOVROB',
'OCUPATOF',
'OCUPAYPL',
'OCUSCHPR',
'OCUSCUPS',
'OCUSMODU',
'OCUSREPS',
'OCUSSESS',
'OCUTRAHI',
'OCUWARNI',
'OIUFSOFF',
'OTMREMIT',
'OTUACODE',
'OTUCOBWH',
'OTUCPAYE',
'OTUHOLDR',
'OTUINVAC',
'OTUSUBAC',
'OTUSUBAD',
'OUMAGENC',
'OIDPAATT',
'OCDOFACC',
'OCDSUPST',
'OCIINTRR',
'OCMFAPRO',
'OCMPFACC',
'OCMGOBLI',
'OTMCFEES');


update menu_securities set parent_menu_id = (select min(ms1.menu_id) from menu_securities ms1 where ms1.menu_item = 'Programs & Services')
where module_name = 'OCDPROGR' and menu_item = 'Offender Programs';

update menu_securities set parent_menu_id = (select max(ms1.menu_id) from menu_securities ms1 where ms1.menu_item = 'Programs & Services')
where module_name = 'OCDPROGR' and menu_item = 'Offender Programs & Evaluation Measures';
 
--
--pt.3--
DO
$body$
DECLARE var_array text[]:='{Contact Log,Scheduler,Case Plan,Substance Abuse,Substance Testing,Virtual Team,Inquiry,Weekly Activity Plans}';
	i int;
    menu_name text;
begin
	for i in array_lower(var_array, 1) .. array_upper(var_array, 1)
	loop
update
	menu_securities
set
	sort_order = i
where
	menu_item = var_array[i]
	and parent_menu_id =(
	select
		menu_id
	from
		menu_securities ms2
	where
		parent_menu_id =(
		select
			menu_id
		from
			menu_securities ms
		where
			menu_item = 'Community Case Management')
		and menu_item = 'Case Management');	
--raise notice 'Value: %',var_array[i];
end loop;
END;
$body$ 