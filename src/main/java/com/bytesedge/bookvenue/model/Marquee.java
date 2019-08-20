package com.bytesedge.bookvenue.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Divya
 */
/*
 * This class is for create the scroller or marquee text
 */
@Entity
@Table(name = "lb_marquee")
@DiscriminatorColumn(name = "class_code", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "marquee")
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Marquee extends OrgDo implements Auditable, Indexable {

	/*
	 * text is nothing but content which data want in a scroller
	 */
	private String text;
	/*
	 * type indicates the data scrolls in which page want to be selected
	 */
	private MarqueeType type = MarqueeType.REGISTERPAGE;

	@Transient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	public MarqueeType getType() {
		return type;
	}

	public void setType(MarqueeType type) {
		this.type = type;
	}
}