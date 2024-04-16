package com.example.demo.model.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Employee;

public class EmployeeService {

	@Autowired
	private EntityManager entityManager;

	Query query2 = entityManager.createNativeQuery("SELECT * FROM employees WHERE salary > :salary", Employee.class);

	@Autowired
	private Session session;
	
	public void createNativeQueryExample() {
		query2.setParameter("salary", 50000);
		List<Employee> results = query2.getResultList();
	}
	
	public void createNamedQueryExample() {
		TypedQuery<Employee> query1 = entityManager.createNamedQuery("Employee.findByName", Employee.class);
		query1.setParameter("name", "John Doe");
		List<Employee> results = query1.getResultList();
	}
	
	public void sessionExample() {
		Query query3 = session.getNamedQuery("Employee.findByDepartment");
		query3.setParameter("deptId", 123);
		List<Employee> results = query3.getResultList();
	}
	
	public void createSQLQueryExample() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		//session.createSQLQuery(
		Query query = session.createSQLQuery("SELECT * FROM employees WHERE salary > :salary").addEntity(Employee.class);
		query.setParameter("salary", 30000);
		List<Employee> results = query.getResultList();
	}
	
	public void insertSQLQueryExample() {
		Query query = session.createSQLQuery("INSERT INTO employees(id,age) values(:id,:age) ").addEntity(Employee.class);
		query.setParameter("id", "123");
		query.setParameter("age", "26");
		List<Employee> results = query.getResultList();
	}
}
