package com.mvphotelbooking.mvphotelbooking.repository;

import com.mvphotelbooking.mvphotelbooking.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
