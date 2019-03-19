package com.kyiminhan.spring.batch.write;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.kyiminhan.spring.batch.model.Employee;

/**
 * The Class CompositeItemWriter.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since 2019/03/19 </BR>
 *        spring-batch-demo-004 system </BR>
 *        com.kyiminhan.spring.batch.write </BR>
 *        CompositeItemWriter.java </BR>
 */
public class ConsoleWriter implements ItemWriter<Employee> {


	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		System.out.println(employees);
	}

}