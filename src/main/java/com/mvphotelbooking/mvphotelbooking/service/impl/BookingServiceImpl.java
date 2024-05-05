package com.mvphotelbooking.mvphotelbooking.service.impl;

import com.mvphotelbooking.mvphotelbooking.entity.Guest;
import com.mvphotelbooking.mvphotelbooking.entity.Reservation;
import com.mvphotelbooking.mvphotelbooking.entity.Room;
import com.mvphotelbooking.mvphotelbooking.exception.RoomException;
import com.mvphotelbooking.mvphotelbooking.model.AddressDto;
import com.mvphotelbooking.mvphotelbooking.model.HotelDto;
import com.mvphotelbooking.mvphotelbooking.model.RoomDto;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingDetailRequest;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingRequest;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingDetailsResponse;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingResponse;
import com.mvphotelbooking.mvphotelbooking.repository.GuestRepository;
import com.mvphotelbooking.mvphotelbooking.repository.ReservationRepository;
import com.mvphotelbooking.mvphotelbooking.repository.RoomRepository;
import com.mvphotelbooking.mvphotelbooking.service.BookingService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    @Override
    public BookingResponse bookRoom(BookingRequest bookingRequest) throws NotFoundException {
        Guest guest = guestRepository.findById(bookingRequest.getGuestId()).orElseThrow(() -> new NotFoundException("Guest not found."));
        Room room = roomRepository.findById(bookingRequest.getRoomId()).orElseThrow(() -> new NotFoundException("Room not found."));
        // Validate room availability for the given dates
        if (!this.isRoomAvailableForBooking(room, bookingRequest.getCheckin(), bookingRequest.getCheckout())) {
            log.info("checkin/checkout time got overlap.");
            throw new RoomException("room " + room.getNumber() + " is not available for booking");
        }
        // Calculate total cost, handle payment, etc.
        Reservation reservation = Reservation.builder()
                .guest(guest)
                .room(room)
                .checkinDatetime(bookingRequest.getCheckin())
                .checkoutDatetime(bookingRequest.getCheckout())
                .build();
        reservationRepository.save(reservation);
        return bookingResponsePrepare(room, reservation);

    }

    @Override
    public BookingDetailsResponse findBookingDetails(BookingDetailRequest bookingRequest) throws NotFoundException {
        guestRepository.findById(bookingRequest.getGuestId())
                .orElseThrow(() -> new NotFoundException("Guest not found."));

        List<Reservation> reservations = getReservationsForGuestAndRoom(bookingRequest.getGuestId(), bookingRequest.getRoomId());

        List<BookingResponse> bookingResponses = reservations.stream()
                .map(reservation -> bookingResponsePrepare(reservation.getRoom(), reservation))
                .collect(Collectors.toList());

        return new BookingDetailsResponse(bookingResponses);
    }

    /**
     * To Check if room is available for booking
     *
     * @param room     booking room
     * @param checkin  checkin datetime
     * @param checkout checkout datetime
     * @return true is room is free for booking / false if not available
     */
    private boolean isRoomAvailableForBooking(Room room, LocalDateTime checkin, LocalDateTime checkout) {
        if (!room.getStatus().equals(Room.RoomStatus.ACTIVE)) {
            return false;
        }
        List<Reservation> reservation = reservationRepository.findOverlappingReservations(room.getRoomId(), checkin, checkout);
        return reservation.isEmpty();
    }

    /**
     * Booking data for response
     *
     * @param room        room info
     * @param reservation reservation info
     * @return booking info
     */
    private BookingResponse bookingResponsePrepare(Room room, Reservation reservation) {
        RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(room, roomDto);
        HotelDto hotelDto = new HotelDto();
        BeanUtils.copyProperties(room.getHotel(), hotelDto);
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(room.getHotel().getAddress(), addressDto);
        hotelDto.setAddress(addressDto);
        return BookingResponse.builder().room(roomDto).hotel(hotelDto).checkin(reservation.getCheckinDatetime()).checkout(reservation.getCheckoutDatetime()).build();
    }

    /**
     * to find list of reservation of guest
     * @param guestId guest identify
     * @param roomId room identify
     * @return list of reservation
     */
    private List<Reservation> getReservationsForGuestAndRoom(Long guestId, Long roomId) {
        if (roomId == null) {
            return reservationRepository.findByGuestGuestId(guestId);
        } else {
            return reservationRepository.findByGuestGuestIdAndRoomRoomId(guestId, roomId);
        }
    }

}
