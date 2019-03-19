package com.kyiminhan.spring.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.kyiminhan.spring.batch.model.Employee;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ValidationProcessor.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Mar 18, 2019 </BR>
 *        spring-batch-demo-004 system </BR>
 *        com.kyiminhan.spring.batch.processor </BR>
 *        ValidationProcessor.java </BR>
 */

@Log4j2
public class ValidationProcessor implements ItemProcessor<Employee, Employee> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public Employee process(final Employee employee) throws Exception {
		if (employee.getId() == null) {
			ValidationProcessor.log.info("Missing employee id : " + employee.getId());
			return null;
		}

		try {
			if (Integer.valueOf(employee.getId()) <= 0) {
				ValidationProcessor.log.info("Invalid employee id : " + employee.getId());
				return null;
			}
		} catch (final NumberFormatException e) {
			ValidationProcessor.log.info("Invalid employee id : " + employee.getId());
			return null;
		}
		return employee;
	}

}