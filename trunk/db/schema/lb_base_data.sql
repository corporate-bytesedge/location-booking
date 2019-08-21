-- Base data
use lb

LOCK TABLES `lb_context` WRITE;
INSERT INTO `lb_context` VALUES 
(1,'context',0,'192.168.56.104','Bytesedge','be','040 2345678','8019016677','support@lb.be.gov.in','2017-10-13 22:54:01','2017-07-22 21:35:00'),
(2,'context',0,'ts.shilparamam.com','shilparamam(upcoming...)','srm','040 2345678','8019016678','support@lb.srm.gov.in','2017-10-13 22:54:01','2017-07-22 21:37:00'),
(3,'context',0,'ts.shilpakalavedhika.com','shilpakalavedhika(upcoming...)','skv','040 2345679','8019016668','support@lb.skv.gov.in','2017-10-13 22:54:01','2017-07-22 21:37:00');
UNLOCK TABLES;

LOCK TABLES `lb_user_role` WRITE;
INSERT INTO `lb_user_role` VALUES 
(1,'user_role',0,1,'Admin',1,'2017-10-13 17:24:01',1,NULL,NULL),
(2,'user_role',0,1,'Manager',1,'2017-10-13 17:24:01',1,NULL,NULL),
(3,'user_role',0,1,'Audit',1,'2017-10-13 17:24:01',1,NULL,NULL);
UNLOCK TABLES;

insert into lb_organization (id,ctx_id,org_name,support_phone_number,support_mobile_number,support_email_id,created_user_id)  values (1,1,'BYTES EDGE','+91402345678','+918019016668','bytesedge@gmail.com',1);
insert into lb_organization (id,ctx_id,org_name,support_phone_number,support_mobile_number,support_email_id,created_user_id)  values (2,1,'shilparamam','+91402345678','+918019016678','shilparamam@gmail.com',1);
insert into lb_organization (id,ctx_id,org_name,support_phone_number,support_mobile_number,support_email_id,created_user_id)  values (3,1,'shilpakalavedika','+91402345678','+918056016668','shilpakalavedhika@gmail.com',1);

LOCK TABLES `lb_user` WRITE;
INSERT INTO `lb_user` values(1,'user',1,1,1,1,'admin@bytesedge.com','$2a$10$bolscdslqhe0b4k/adfwvov6lebnv3iy1qn2vd7zdbforoqws/hea','ACTIVE','admin','Admin BE','MALE','234523452345','8019016690','2017-08-07 00:00:00','Bytes edge Admin',NULL,NULL,NULL,1,'2017-10-14 12:11:10',NULL,NULL);
UNLOCK TABLES;

--
INSERT INTO `lb_property` VALUES (1,'property',0,1,1,1,1,'Sampradhaya Vedhika ','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL,180);
INSERT INTO `lb_property` VALUES (2,'property',0,1,1,1,1,'Rock Heights ','This is for functions like marriages....','Active','2018-12-21 01:24:01',1,NULL,NULL,180);
INSERT INTO `lb_property` VALUES (3,'property',0,1,1,1,1,'Mountain Heights ','This is for functions like marriages....','Active','2018-12-21 01:24:01',1,NULL,NULL,180);
INSERT INTO `lb_property` VALUES (4,'property',0,1,1,1,1,'Shilpaseema ','This is for functions like marriages....','Active','2018-12-21 01:24:01',1,NULL,NULL,180);

--

--
INSERT INTO `lb_rent_purpose` VALUES (1,'rent_purpose',0,1,1,'Marriage','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL);
INSERT INTO `lb_rent_purpose` VALUES (2,'rent_purpose',0,1,1,'Birthday','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL);
INSERT INTO `lb_rent_purpose` VALUES (3,'rent_purpose',0,1,1,'Engagement','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL);
INSERT INTO `lb_rent_purpose` VALUES (4,'rent_purpose',0,1,1,'Corporate Functions','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL);
INSERT INTO `lb_rent_purpose` VALUES (5,'rent_purpose',0,1,1,'Others','This is for functions like marriages....','Active','2018-12-21 12:24:01',1,NULL,NULL);
--





INSERT INTO `lb_property_rent_price`VALUES(1,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',1,1,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(2,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',1,2,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(3,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',1,3,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(4,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',1,4,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(5,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',1,5,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(6,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,150000.00,'Active',1,1,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(7,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',1,2,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(8,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',1,3,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(9,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,150000.00,'Active',1,4,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(10,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',1,5,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(11,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',2,1,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(12,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',2,2,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(13,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',2,3,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(14,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',2,4,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(15,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',2,5,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(16,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',2,1,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(17,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,150000.00,'Active',2,2,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(18,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',2,3,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(19,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',2,4,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(20,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',2,5,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(21,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',3,1,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(22,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',3,2,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(23,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',3,3,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(24,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',3,4,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(25,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,150000.00,'Active',3,5,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(26,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',3,1,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(27,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',3,2,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(28,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',3,3,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(29,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,150000.00,'Active',3,4,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(30,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,150000.00,'Active',3,5,9.00,9.00,3.00,'2018-12-21 17:13:08',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(31,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',4,1,9.00,9.00,3.00,'2018-12-21 17:13:52',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(32,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',4,2,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(33,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',4,3,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(34,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',4,4,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(35,'property_rent_price',0,1,1,'WEEKDAY',NULL,NULL,100000.00,'Active',4,5,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(36,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',4,1,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(37,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',4,2,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(38,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',4,3,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(39,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',4,4,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
INSERT INTO `lb_property_rent_price`VALUES(40,'property_rent_price',0,1,1,'WEEKEND',NULL,NULL,100000.00,'Active',4,5,9.00,9.00,3.00,'2018-12-21 17:16:07',1,NULL,NULL);
-- end
--
INSERT INTO `lb_payment_gateway_details`(ctx_id,org_id,login,password,req_hash_key,res_hash_key,status,txn_curr,created_user_id,updated_user_id,ttype,prodid,mer_url) VALUES(1,1,'197','Test@123','KEY123657234','KEYRESP123657234','Active','INR',1,1,'NBFundTransfer','NSE','https://192.168.56.104/apu/ext/paymentGatewayResponse');

--
INSERT INTO `lb_marquee`(ctx_id,org_id,text,created_user_id) values(1,1,'Welcome!',1);
INSERT INTO `lb_marquee`(ctx_id,org_id,text,type,created_user_id) values(1,1,'Hi!','DASHBOARD',1);

--end




