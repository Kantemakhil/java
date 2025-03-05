drop public synonym CONV_LOGS;
drop public synonym CONV_LOG_SEQ;
drop public synonym CONV_ENA_DIS_CONSTRAINTS;
drop public synonym CONV_ENA_DIS_TRIGGERS;
drop public synonym CONV_SEQUENCES;
drop public synonym CONV_UTILITIES;

drop table oms_conv.CONV_LOGS;
drop sequence oms_conv.CONV_LOG_SEQ;

drop table oms_conv.CONV_ENA_DIS_CONSTRAINTS;
drop table oms_conv.CONV_ENA_DIS_TRIGGERS;
drop table oms_conv.CONV_SEQUENCES;

drop package oms_conv.CONV_UTILITIES;

--

DROP PUBLIC SYNONYM CONV_MAP_BOOKINGS;
DROP TABLE oms_conv.CONV_MAP_BOOKINGS;
DROP PUBLIC SYNONYM CONV_MAP_OFFENDERS;
DROP TABLE oms_conv.CONV_MAP_OFFENDERS;
DROP PUBLIC SYNONYM CONV_RANK_MOVEMENTS;
DROP TABLE oms_conv.CONV_RANK_MOVEMENTS;

drop package oms_conv.CONV_OFF_CORE_1;
drop package oms_conv.CONV_OFF_CORE_2;

--
DROP USER oms_conv CASCADE;
DELETE FROM staff_members WHERE user_id = 'OMS_CONV';

