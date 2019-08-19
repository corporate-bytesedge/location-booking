-- 18-June
-- start

update sbv_user set created_user_id=1 where id=1;
-- end


July-08
--start
alter table sbv_end_user change payment_mode `payment_mode` varchar(32) DEFAULT NULL;
alter table sbv_end_user change id_proof `id_proof` varchar(24) DEFAULT NULL;

--end

July 08
-- start
alter table sbv_property add column `venue_days_limit` bigint(20) NOT NULL;
update sbv_property set venue_days_limit=180;
-- end

July 17
--start
update sbv_property_rent_price set service_tax=3.00;
--end

July 18
--Start
alter table sbv_end_user change otp `otp` varchar(6) DEFAULT NULL;
--end

July 19
--Start
INSERT INTO `sbv_rent_purpose` VALUES (4,'rent_purpose',0,1,1,'Corporate Functions','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL);
alter table sbv_end_user add column `user_state` varchar(8) DEFAULT 'ACTIVE';
--end
