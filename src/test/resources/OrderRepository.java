
package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	String CONDITION = " WHERE e.name = :name";

    // @Query("UPDATE MyEntity e SET age =:age")
    // List<Order> findByName1(@Param("name") String name, @Param("age") int age);

    // @Query(value = "SELECT * FROM my_entity WHERE name =:name", nativeQuery = true)
    // List<Order> findByName(@Param("name") String name);
    
    // @Query("UPDATE MyEntity e SET age = :age" + " WHERE e.name = :name")
    // List<Order> updateByAge(@Param("name") String name, @Param("age") int age);

    @Query("UPDATE Dynamic e SET age = :age" + CONDITION)
    List<Order> findByName(@Param("name") String name, @Param("age") int age);

    // @Query("SELECT e FROM MyEntity e WHERE e.name = ?1 AND e.age > ?2")
    // List<Order> findByNameAndAge(String name, int age);

}