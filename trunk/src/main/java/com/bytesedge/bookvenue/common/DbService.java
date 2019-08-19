package com.bytesedge.bookvenue.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytesedge.bookvenue.service.OrgService;
import com.bytesedge.bookvenue.service.SetupService;
import com.bytesedge.bookvenue.service.UserService;

public class DbService {
	private static DbService _instance = null;

	@Autowired
	private UserService userService = null;

	@Autowired
	private SetupService setupService = null;

	@Autowired
	private MessageHelper msgHelper = null;

	@Autowired
	private OrgService orgService = null;

	private DbService() {
	}

	public static DbService getInstance() {
		if (_instance == null) {
			synchronized (DbService.class) {
				if (_instance == null) {
					_instance = new DbService();
				}
			}
		}
		return _instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SetupService getSetupService() {
		return setupService;
	}

	public void setSetupService(SetupService setupService) {
		this.setupService = setupService;
	}

	public MessageHelper getMsgHelper() {
		return msgHelper;
	}

	public void setMsgHelper(MessageHelper msgHelper) {
		this.msgHelper = msgHelper;
	}

	public OrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

}