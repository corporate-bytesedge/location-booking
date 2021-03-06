use lb;

DROP TABLE IF EXISTS `lb_server_prop`;
CREATE TABLE `lb_server_prop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'server_prop',
  `version` bigint(20) DEFAULT '0',
  `name` varchar(64) NOT NULL,
  `value` varchar(255) NOT NULL, 
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__lb_server_prop__name` (`name`),
  KEY `idx__lb_server_prop` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_context`;
CREATE TABLE `lb_context` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'context',
  `version` bigint(20) DEFAULT '0',
  `url` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `code` varchar(4) NOT NULL,  
  `support_phone_number` varchar(255) DEFAULT NULL,
  `support_mobile_number` varchar(255) NOT NULL,
  `support_email_id` varchar(255) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__lb_org_home_data__url` (`url`),
  KEY `idx__lb_context__url` (`url`),
  KEY `idx__lb_context__name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_user_role`;
CREATE TABLE `lb_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'user_role',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `admin` tinyint(1) DEFAULT '0',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__lb_user_role__name__ctx_id` (`name`,`ctx_id`),
  KEY `idx__lb_user_role__ctx_id` (`ctx_id`),
  KEY `idx__lb_user_role__name` (`name`),
  KEY `idx__lb_user_role__created_user_id` (`created_user_id`),
  KEY `idx__lb_user_role__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_user_role__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_organization`;
CREATE TABLE `lb_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'organization',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_name` varchar(64) NOT NULL,
  `support_phone_number` varchar(255) DEFAULT NULL,
  `support_mobile_number` varchar(255) NOT NULL,
  `support_email_id` varchar(255) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_organization__ctx_id` (`ctx_id`),
  KEY `idx__lb_organization__org_name` (`org_name`),
  KEY `idx__lb_organization__created_user_id` (`created_user_id`),
  KEY `idx__lb_organization__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_organization__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_user`;
CREATE TABLE `lb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'user',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `login_name` varchar(128) NOT NULL,
  `login_password` varchar(128) NOT NULL,
  `account_state` varchar(8) DEFAULT 'ACTIVE',
  `email` varchar(64) NOT NULL,
  `display_name` varchar(64) NOT NULL,
  `gender` varchar(16) DEFAULT 'MALE',
  `aadhar_id` varchar(12) DEFAULT NULL,
  `mobile_number` varchar(14) NOT NULL,
  `dob` datetime NOT NULL,
  `designation` varchar(64) NOT NULL,
  `auth_failed` bigint(20) DEFAULT '0',
  `auth_failed_time` datetime DEFAULT NULL,
  `locked_time` datetime DEFAULT NULL,
  `created_user_id` bigint(20) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` bigint(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_login_un` (`login_name`),
  KEY `idx__lb_user__ctx_id` (`ctx_id`),
  KEY `idx__lb_user__org_id` (`org_id`),
  KEY `idx__lb_user__role_id` (`role_id`),
  KEY `idx__lb_user__login_name` (`login_name`),
  KEY `idx__lb_user__display_name` (`display_name`),
  KEY `idx__lb_user__account_state` (`account_state`),
  KEY `idx__lb_user__aadhar_id` (`aadhar_id`),
  KEY `idx__lb_user__mobile_number` (`mobile_number`),
  KEY `idx__lb_user__designation` (`designation`),
  CONSTRAINT `fk__lb_user__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_user__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_user__role_id` FOREIGN KEY (`role_id`) REFERENCES `lb_user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_login_history`;
CREATE TABLE `lb_login_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'login_history',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `login_name` varchar(255) NOT NULL,
  `login_time` datetime NOT NULL,
  `logout_time` datetime DEFAULT NULL,
  `logout_type` bigint(4) DEFAULT NULL,
  `login_type` bigint(4) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_login_history__ctx_id` (`ctx_id`),
  KEY `idx__lb_login_history__org_id` (`org_id`),
  KEY `idx__lb_login_history__user_id` (`user_id`),
  CONSTRAINT `fk__lb_login_history__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_lb_login_history__user_id` FOREIGN KEY (`user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_audit_data`;
CREATE TABLE `lb_audit_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'audit_data',
  `ctx_id` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT '0',
  `org_id` bigint(20) NOT NULL,
  `operation` varchar(16) NOT NULL,
  `name_of_application` varchar(64) NOT NULL,
  `src` text,
  `dst` text NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_audit_data__ctx_id` (`ctx_id`),
  KEY `idx__lb_audit_data__org_id` (`org_id`),
  KEY `idx__lb_audit_data__created_user_id` (`created_user_id`),
  KEY `idx__lb_audit_data__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_audit_data__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_audit_data__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_audit_data__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_audit_data__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_property`;
CREATE TABLE `lb_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'property',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `property_id` bigint(20) NOT NULL,
  `unique_id` varchar(16) NOT NULL,
  `name` varchar(64) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `state` varchar(8) DEFAULT 'ACTIVE',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  `venue_days_limit` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_property__ctx_id` (`ctx_id`),
   KEY `idx__lb_property__org_id` (`org_id`),
  KEY `idx__lb_property__property_id` (`property_id`),
  KEY `idx__lb_property__name` (`name`),
  KEY `idx__lb_property__state` (`state`),
  KEY `idx__lb_property__unique_id` (`unique_id`),
  KEY `idx__lb_property__created_user_id` (`created_user_id`),
  KEY `idx__lb_property__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_property__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_property__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_property__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_property__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_rent_purpose`;
CREATE TABLE `lb_rent_purpose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'rent_purpose',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `state` varchar(8) DEFAULT 'ACTIVE',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_rent_purpose__ctx_id` (`ctx_id`),
  KEY `idx__lb_rent_purpose__org_id` (`org_id`),
  KEY `idx__lb_rent_purpose__created_user_id` (`created_user_id`),
  KEY `idx__lb_rent_purpose__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_rent_purpose__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_rent_purpose__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_rent_purpose__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_rent_purpose__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_property_rent_price`;
CREATE TABLE `lb_property_rent_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'property_rent_price',
  `version` bigint(20) DEFAULT 0,
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `slot_type` varchar(8) DEFAULT 'NONE',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `price` float(9,2) DEFAULT 0.00,
  `state` varchar(8) DEFAULT 'Active',
  `property_id` bigint(20) NOT NULL,
  `purpose_id` bigint(20) NOT NULL,
  `cgst` float(9,2) DEFAULT 9.00,
  `sgst` float(9,2) DEFAULT 9.00,
  `service_tax` float(9,2) DEFAULT 3.45,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk__lb_property_rent_price__uk`(`property_id`, `purpose_id`, `slot_type`, `state`),
  KEY `idx__lb_property_rent_price__ctx_id` (`ctx_id`),
  KEY `idx__lb_property_rent_price__org_id` (`org_id`),
  KEY `idx__lb_property_rent_price__property_id` (`property_id`),
  KEY `idx__lb_property_rent_price__purpose_id` (`purpose_id`),
  KEY `idx__lb_property_rent_price__state` (`state`),
  KEY `idx__lb_property_rent_price__start_time` (`start_time`),
  KEY `idx__lb_property_rent_price__end_time` (`end_time`),
  KEY `idx__lb_property_rent_price__created_user_id` (`created_user_id`),
  KEY `idx__lb_property_rent_price__updated_user_id` (`updated_user_id`),
  KEY `idx__lb_property_rent_price__slot_type` (`slot_type`),
  CONSTRAINT `fk__lb_property_rent_price__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_property_rent_price__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_property_rent_price__property_id` FOREIGN KEY (`property_id`) REFERENCES `lb_property` (`id`),
  CONSTRAINT `fk__lb_property_rent_price__purpose_id` FOREIGN KEY (`purpose_id`) REFERENCES `lb_rent_purpose` (`id`),
  CONSTRAINT `fk__lb_property_rent_price__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_property_rent_price__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `lb_payment_gateway_details`;
CREATE TABLE `lb_payment_gateway_details`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'property',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `login` varchar(16) NOT NULL,
  `password` varchar(128) NOT NULL,
  `req_hash_key` varchar(128) NOT NULL,
  `res_hash_key` varchar(128) NOT NULL,
  `status` varchar(8) DEFAULT 'PENDING',
  `txn_curr` varchar(16) NOT NULL,
  `ttype` varchar(64) NOT NULL,
  `prodid` varchar(32) NOT NULL,
  `mer_url` varchar(64) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_payment_gateway_details__ctx_id` (`ctx_id`),
  KEY `idx__lb_payment_gateway_details__org_id` (`org_id`),
  KEY `idx__lb_payment_gateway_details__status` (`status`),
  KEY `idx__lb_payment_gateway_details__created_user_id` (`created_user_id`),
  KEY `idx__lb_payment_gateway_details__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_payment_gateway_details__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_payment_gateway_details__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_payment_gateway_details__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_payment_gateway_details__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
  -- end

DROP TABLE IF EXISTS `lb_total_status`;
CREATE TABLE `lb_total_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'total_status',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  `purpose_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `mobile_number` varchar(16) NOT NULL,
  `referred_by` varchar(64) DEFAULT NULL,
  `venue_date` datetime  NOT NULL,
  `addr` varchar(255) NOT NULL,
  `created_user_id` bigint(20) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` bigint(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_total_status__ctx_id` (`ctx_id`),
  KEY `idx__lb_total_status__name` (`name`),
  KEY `idx__lb_total_status__property_id` (`property_id`),
  KEY `idx__lb_total_status__purpose_id` (`purpose_id`),
  KEY `idx__lb_total_status__created_user_id` (`created_user_id`),
  KEY `idx__lb_total_status__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_total_status__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_total_status__property_id` FOREIGN KEY (`property_id`) REFERENCES `lb_property` (`id`),
  CONSTRAINT `fk__lb_total_status__purpose_id` FOREIGN KEY (`purpose_id`) REFERENCES `lb_rent_purpose` (`id`),
  CONSTRAINT `fk__lb_total_status__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_total_status__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_sms_gateway_details`;
CREATE TABLE `lb_sms_gateway_details`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'sms_gateway_details',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `sender_id` bigint(20) NOT NULL,
  `route` bigint(20) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user_id` bigint(20) NOT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_sms_gateway_details__ctx_id` (`ctx_id`),
  KEY `idx__lb_sms_gateway_details__org_id` (`org_id`),
  KEY `idx__lb_sms_gateway_details__created_user_id` (`created_user_id`),
  KEY `idx__lb_sms_gateway_details__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_sms_gateway_details__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_sms_gateway_details__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_sms_gateway_details__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_sms_gateway_details__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_marquee`;
CREATE TABLE `lb_marquee`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_code` varchar(64) DEFAULT 'marquee',
  `version` bigint(20) DEFAULT '0',
  `ctx_id` bigint(20) NOT NULL,
  `org_id` bigint(20) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `type` varchar(20) DEFAULT 'REGISTERPAGE',
  `created_user_id` bigint(20) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` bigint(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx__lb_marquee__ctx_id` (`ctx_id`),
  KEY `idx__lb_marquee__org_id` (`org_id`),
  KEY `idx__lb_marquee__created_user_id` (`created_user_id`),
  KEY `idx__lb_marquee__updated_user_id` (`updated_user_id`),
  CONSTRAINT `fk__lb_marquee__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
  CONSTRAINT `fk__lb_marquee__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
  CONSTRAINT `fk__lb_marquee__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
  CONSTRAINT `fk__lb_marquee__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lb_end_user`;
CREATE TABLE `lb_end_user` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `class_code` varchar(64) DEFAULT 'end_user',
 `version` bigint(20) DEFAULT '0',
 `ctx_id` bigint(20) NOT NULL,
 `org_id` bigint(20) NOT NULL,
 `application_id` varchar(32) NOT NULL,
 `name` varchar(64) NOT NULL,
 `otp` varchar(6) DEFAULT NULL,
 `email` varchar(64) NOT NULL,
 `id_proof_type` varchar(20) DEFAULT 'AADHAAR',
 `id_proof` varchar(24) NOT NULL,
 `user_name` varchar(64) NOT NULL,
 `mobile_number` varchar(14) NOT NULL,
 `booking_date` datetime NOT NULL,
 `property_id` bigint(20) NOT NULL,
 `purpose_id` bigint(20) NOT NULL,
 `price` float(9,2) DEFAULT 0.00,
 `service_tax` float(9,2) DEFAULT 0.00,
 `total` float(9,2) DEFAULT 0.00,
 `cgst` float(9,2) DEFAULT 0.00,
 `sgst` float(9,2) DEFAULT 0.00,
 `addr` varchar(255) NOT NULL,
 `deposit_price` float(9,2) DEFAULT 0.00,
 `caterers_addr` varchar(255) DEFAULT NULL,
 `referred_by` varchar(64) DEFAULT NULL,
 `booking_type` varchar(20) DEFAULT 'REGISTER',
 `payment_status` varchar(8) DEFAULT 'PENDING',
 `payment_mode` varchar(8) DEFAULT 'CHEQUE',
 `property_rent_price_id` bigint(20) DEFAULT NULL,
 `expiry_date` datetime DEFAULT NULL,
 `name_pg_res` varchar(64) DEFAULT NULL,
 `txn_date_pg_res` varchar(64) DEFAULT NULL,
 `sur_charge_pg_res` float(9,2) DEFAULT 0.00,
 `discr_pg_res` varchar(32) DEFAULT NULL,
 `card_number_pg_res` varchar(32) DEFAULT NULL,
 `mer_txn_pg_res` varchar(24) DEFAULT NULL,
 `mmp_txn_pg_res` varchar(24) DEFAULT NULL,
 `prod_id_pg_res` varchar(8) DEFAULT NULL,
 `bank_txn_id_pg_res` varchar(16) DEFAULT NULL,
 `auth_code_pg_res` varchar(8) DEFAULT NULL,
 `f_code_pg_res` varchar(8) DEFAULT NULL,
 `client_code_pg_res` varchar(32) DEFAULT NULL,
 `bank_name_pg_res` varchar(24) DEFAULT NULL,
 `ipg_txn_id_pg_res` varchar(16) DEFAULT NULL,
 `mer_id_pg_res` varchar(8) DEFAULT NULL,
 `desc_pg_res` varchar(32) DEFAULT NULL,
 `udf9_pg_res` varchar(32) DEFAULT NULL,
 `udf5_pg_res` varchar(32) DEFAULT NULL,
 `udf6_pg_res` varchar(32) DEFAULT NULL,
 `res_sign_pg_res` varchar(255) DEFAULT NULL,
 `amt_pg_res` varchar(64) DEFAULT NULL,
 `payment_url` varchar(1024) DEFAULT NULL,
 `created_user_id` bigint(20) NOT NULL,
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_user_id` bigint(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `user_state` varchar(8) DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  KEY `idx__lb_end_user__ctx_id` (`ctx_id`),
  KEY `idx__lb_end_user__org_id` (`org_id`),
  KEY `idx__lb_end_user__id` (`id`),
  KEY `idx__lb_end_user__property_rent_price_id` (`property_rent_price_id`),
  KEY `idx__lb_end_user__property_id` (`property_id`),
  KEY `idx__lb_end_user__purpose_id` (`purpose_id`),
  KEY `idx__lb_end_user__mobile_number` (`mobile_number`),
  KEY `idx__lb_end_user__created_user_id` (`created_user_id`),
  KEY `idx__lb_end_user__updated_user_id` (`updated_user_id`),
 CONSTRAINT `fk__lb_end_user__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
 CONSTRAINT `fk__lb_end_user__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
 CONSTRAINT `fk__lb_end_user__property_rent_price_id` FOREIGN KEY (`property_rent_price_id`) REFERENCES `lb_property_rent_price` (`id`),
 CONSTRAINT `fk__lb_end_user__property_id` FOREIGN KEY (`property_id`) REFERENCES `lb_property` (`id`),
 CONSTRAINT `fk__lb_end_user__purpose_id` FOREIGN KEY (`purpose_id`) REFERENCES `lb_rent_purpose` (`id`),
 CONSTRAINT `fk__lb_end_user__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
 CONSTRAINT `fk__lb_end_user__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;  

-- end 
DROP TABLE IF EXISTS `lb_forgot_passwd`;
CREATE TABLE `lb_forgot_passwd` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`class_code` varchar(64) DEFAULT 'forgot_passwd',
`version` bigint(20) DEFAULT '0',
`ctx_id` bigint(20) NOT NULL,
`org_id` bigint(20) NOT NULL,
`email` varchar(64) DEFAULT NULL,
`password` varchar(64) DEFAULT NULL,
`confirm_password` varchar(64) DEFAULT NULL,
`mobile_number` varchar(14) DEFAULT NULL,
`otp` varchar(6) DEFAULT NULL,
`username` varchar(64) DEFAULT NULL,
`expiry_date` datetime DEFAULT NULL,
`created_user_id` bigint(20) NOT NULL,
`created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
`updated_user_id` bigint(20) DEFAULT NULL,
`updated_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `idx__lb_forgot_passwd__created_user_id` (`created_user_id`),
KEY `idx__lb_forgot_passwd__updated_user_id` (`updated_user_id`),
KEY `idx__lb_forgot_passwd__ctx_id` (`ctx_id`),
KEY `idx__lb_forgot_passwd__org_id` (`org_id`),
CONSTRAINT `fk__forgot_passwd__ctx_id` FOREIGN KEY (`ctx_id`) REFERENCES `lb_context` (`id`),
CONSTRAINT `fk__lb_forgot_passwd__org_id` FOREIGN KEY (`org_id`) REFERENCES `lb_organization` (`id`),
CONSTRAINT `fk__lb_forgot_passwd__created_user_id` FOREIGN KEY (`created_user_id`) REFERENCES `lb_user` (`id`),
CONSTRAINT `fk__lb_forgot_passwd__updated_user_id` FOREIGN KEY (`updated_user_id`) REFERENCES `lb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 
 
 