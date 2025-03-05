REM Inserting sequences list that are affected by data migration

--***CONV_OFF_CORE_1
insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'OFFENDER_ID', 'Y', 'OFFENDERS', 'OFFENDER_ID', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'OFFENDER_ID');

insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'OFFENDER_ID_DISPLAY', 'Y', 'OFFENDERS', 'OFFENDER_ID_DISPLAY', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'OFFENDER_ID_DISPLAY');

insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'OFFENDER_ALIAS_ID', 'Y', 'OFFENDERS', 'ALIAS_OFFENDER_ID', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'OFFENDER_ALIAS_ID');

insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'OFFENDER_BOOK_ID', 'Y', 'OFFENDER_BOOKINGS', 'OFFENDER_BOOK_ID', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'OFFENDER_BOOK_ID');

insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'BOOKING_NO', 'Y', 'OFFENDER_BOOKINGS', 'BOOKING_NO', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'BOOKING_NO');

insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME )  
select 'EVENT_ID', 'Y', 'V_OFFENDER_ALL_SCHEDULES', 'EVENT_ID', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'EVENT_ID');

  insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME )  
select 'ESCAPE_ID', 'N', 'OFFENDER_ESCAPES', 'ESCAPE_ID', 'CONV_OFF_CORE_1'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_1' and SEQ_NAME = 'ESCAPE_ID');


--***CONV_OFF_CORE_2
insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'ADDRESS_ID', 'N', 'ADDRESSES', 'ADDRESS_ID', 'CONV_OFF_CORE_2'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_2' and SEQ_NAME = 'ADDRESS_ID');

insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select  'PHONE_ID', 'N', 'PHONES', 'PHONE_ID', 'CONV_OFF_CORE_2' from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_OFF_CORE_2' and SEQ_NAME = 'PHONE_ID');

--***CONV_IMAGES
insert into conv_sequences  (SEQ_NAME, SPECIAL_FLAG, TABLE_NAME, COLUMN_NAME, CONV_PKG_NAME ) 
select 'IMAGE_ID', 'N', 'IMAGES', 'IMAGE_ID', 'CONV_IMAGES'  from dual 
  where not exists (select 1 from conv_sequences  where CONV_PKG_NAME   = 'CONV_IMAGES' and SEQ_NAME = 'IMAGE_ID');

COMMIT;
