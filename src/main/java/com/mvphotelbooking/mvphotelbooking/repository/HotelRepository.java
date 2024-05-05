package com.mvphotelbooking.mvphotelbooking.repository;

import com.mvphotelbooking.mvphotelbooking.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query(value = "SELECT *" +
            "FROM hotel h" +
            "LEFT JOIN address a ON h.address_id = a.address_id" +
            "WHERE" +
            "    (:street IS NULL OR LOWER(a.street) LIKE LOWER(CONCAT('%', :street, '%')))" +
            "    AND" +
            "    (:city IS NULL OR LOWER(a.city) LIKE LOWER(CONCAT('%', :city, '%')));", nativeQuery = true)
    List<Hotel> findByAddress(String street, String city);
}