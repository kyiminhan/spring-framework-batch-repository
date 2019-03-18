package com.kyiminhan.spring.batch.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Gets the last name.
 *
 * @return the last name
 */
@Getter

/**
 * Sets the last name.
 *
 * @param lastName the new last name
 */
@Setter

/*
 * (non-Javadoc)
 *
 * @see java.lang.Object#toString()
 */
@Builder
public class Employee implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

}