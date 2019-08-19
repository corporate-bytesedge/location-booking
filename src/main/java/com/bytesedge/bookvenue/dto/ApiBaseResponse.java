package com.bytesedge.bookvenue.dto;

import java.util.ArrayList;
import java.util.List;

public class ApiBaseResponse {
	private List<ApiValidationError> errorList = null;
	private String role;
	private Long panchayatId;
	private String panchayatName;
	private Long mandalId;
	private String mandalName;	
	private Long divId;
	private String divName;
	private Long distId;
	private String distName;
	private Long stateId;
	private String stateName;
	
	public boolean hasErrors() {
		if(errorList == null) {
			return false;
		}
		if(errorList.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public ApiBaseResponse(List<ApiValidationError> errors) {
		errorList = errors;
	}

	public ApiBaseResponse() {
	}

	public void add(ApiValidationError error) {
		if(errorList == null) {
			errorList = new ArrayList<ApiValidationError>();
		}
		errorList.add(error);
	}

	public void add(List<ApiValidationError> errors) {
		if(errorList == null) {
			errorList = new ArrayList<ApiValidationError>();
		}
		errorList.addAll(errors);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ApiValidationError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ApiValidationError> errorList) {
		this.errorList = errorList;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public Long getDivId() {
		return divId;
	}

	public void setDivId(Long divId) {
		this.divId = divId;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}