package com.example.demo.repositories;


import com.example.demo.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitle(String title, Pageable pageable);

    List<ProductEntity> findByCreatedAtAfterOrderByTitle(LocalDateTime after);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title,  Pageable pageable);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    //Custom queries - use for complex queries
    //Native SQL Query
    //@Query(value = "SELECT * FROM users WHERE age > ?1", nativeQuery = true)
    //List<User> findUsersNative(int age);

    // SQL @Query("select e.title, e.price from ProductEntity e where e.title=?1 and e.price=?2")
    //JPQL ProductEntity - java Name
    @Query("select e.title, e.price from ProductEntity e where e.title=:title and e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);


}
