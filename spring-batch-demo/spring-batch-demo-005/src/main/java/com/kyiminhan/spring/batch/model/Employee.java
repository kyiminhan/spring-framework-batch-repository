package com.kyiminhan.spring.batch.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Employee.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 19, 2019 </BR>
 *        spring-batch-demo-004 system </BR>
 *        com.kyiminhan.spring.batch.model </BR>
 *        Employee.java </BR>
 */
@Getter

/**
 * Sets the last name.
 *
 * @param lastName the new last name
 */
@Setter
@ToString
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