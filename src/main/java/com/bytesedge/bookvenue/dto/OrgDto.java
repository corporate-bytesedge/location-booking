package com.bytesedge.bookvenue.dto;

public class OrgDto implements Comparable<OrgDto> {
	private String name;
	private long id;
	private String selected = "";

	public OrgDto() {
	}
	
	public OrgDto(String name, long id, boolean selected) {
		this.name = name;
		this.id = id;
		if(selected) {
			this.selected = "selected";
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	@Override
	public int compareTo(OrgDto o) {
		if(o.getName() != null) {
			if(o.getName().startsWith("All ")) {
				return 1;
			}
		}
		
		if(name != null && o.getName() != null) {
			return name.compareTo(o.getName());
		} else {
			return 1;
		}
	}

}