package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Order;

import java.util.List;

import javax.persistence.NamedNativeQueries;

public interface OrderRepositoryTwo extends JpaRepository<Order, Long> {

	public static final String FIND_BY_NAME_AND_AGE_GREATER_THAN_QUERY = "SELECT e FROM MyEntity e WHERE e.name = :name AND e.age >:minAge";
	String DELETE_BY_NAME = "DELETE FROM MyEntity e WHERE e.name = :name";
	String DELETE_ALL = "DELETE FROM MyEntity e ";
	String DELETE_WITH_AGE_CONDITION = " WHERE e.age > :age";
	String CONDITION = " WHERE e.name = :name";


	@Query("DELETE FROM MyEntity e" + " WHERE (:name IS NULL OR e.name = :name) AND (:age IS NULL OR e.age = :age)")
	List<Order> findByParameters(@Param("name") String name, @Param("age") Integer age);

	@Query(value = DELETE_ALL + DELETE_WITH_AGE_CONDITION)
	List<Order> findByNameAndAgeGreaterThan(@Param("name") String name, @Param("age") int age);

	@Query(value = DELETE_BY_NAME)
	List<Order> findByAgeAndName(@Param("name") String name);

	@Query("UPDATE MyEntity e SET age = :age" + " WHERE e.name = :name")
	List<Order> updateByAge(@Param("name") String name, @Param("age") int age);

	@Query("UPDATE MyEntity e SET age = :age" + CONDITION)
	List<Order> findByName(@Param("name") String name, @Param("age") int age);
	
	@NamedQueries("UPDATE MyEntity e SET age = :age" + CONDITION)
	List<Order> findByName(@Param("name") String name, @Param("age") int age);
	
	NamedQueries.class
	
	@NamedNativeQueries(value = DELETE_BY_NAME)
	List<Order> findByAgeAndName(@Param("name") String name);
	NamedNativeQueries.class
	
}