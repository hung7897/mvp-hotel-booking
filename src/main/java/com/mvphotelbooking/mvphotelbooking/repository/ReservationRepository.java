package com.mvphotelbooking.mvphotelbooking.repository;

import com.mvphotelbooking.mvphotelbooking.entity.Reservation;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND ((r.checkinDatetime <= :checkout AND r.checkoutDatetime >= :checkin) " +
            "OR (r.checkoutDatetime >= :checkin AND r.checkinDatetime <= :checkout))")
    List<Reservation> findOverlappingReservations(Long roomId, LocalDateTime checkin, LocalDateTime checkout);

    List<Reservation> findByGuestGuestId(Long guestId);

    List<Reservation> findByGuestGuestIdAndRoomRoomId(Long guestId, Long roomId);
}