INSERT INTO MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities where menu_item = 'Community Case Management'), 'Community Supervision Tiers',null, 13, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities where menu_item = 'Community Supervision Tiers'), 'Maintenance',null, 2, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);

INSERT INTO MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(NEXTVAL('LABLE_ID_SEQUENCE'), (select menu_id from menu_securities ms where menu_item ='Maintenance' and parent_menu_id = (select menu_id from menu_securities where menu_item ='Community Supervision Tiers')), 'Maintain Community Tier Levels','OCMTIRLV',1, CURRENT_TIMESTAMP, 'OMS_OWNER',CURRENT_TIMESTAMP, 'OMS_OWNER', NULL);