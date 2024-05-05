package com.mvphotelbooking.mvphotelbooking;

import com.mvphotelbooking.mvphotelbooking.entity.Address;
import com.mvphotelbooking.mvphotelbooking.entity.Guest;
import com.mvphotelbooking.mvphotelbooking.entity.Hotel;
import com.mvphotelbooking.mvphotelbooking.entity.Room;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingDetailRequest;
import com.mvphotelbooking.mvphotelbooking.model.request.BookingRequest;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingDetailsResponse;
import com.mvphotelbooking.mvphotelbooking.model.response.BookingResponse;
import com.mvphotelbooking.mvphotelbooking.repository.GuestRepository;
import com.mvphotelbooking.mvphotelbooking.repository.ReservationRepository;
import com.mvphotelbooking.mvphotelbooking.repository.RoomRepository;
import com.mvphotelbooking.mvphotelbooking.service.impl.BookingServiceImpl;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    public void testBookRoom_Success() throws NotFoundException {
        // Arrange
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setGuestId(1L);
        bookingRequest.setRoomId(2L);
        // Set other fields in the request

        Guest guest = new Guest();
        Room room = new Room();
        Hotel hotel = new Hotel();
        Address address = new Address();
        hotel.setAddress(address);
        room.setStatus(Room.RoomStatus.ACTIVE);
        room.setHotel(hotel);
        // Initialize guest and room as needed

        when(guestRepository.findById(bookingRequest.getGuestId())).thenReturn(Optional.of(guest));
        when(roomRepository.findById(bookingRequest.getRoomId())).thenReturn(Optional.of(room));
        // Mock other dependencies as necessary

        // Act
        BookingResponse response = bookingService.bookRoom(bookingRequest);

        // Assert
        assertNotNull(response);
    }

    @Test
    public void testFindBookingDetails_Success() throws NotFoundException {
        // Arrange
        BookingDetailRequest detailRequest = BookingDetailRequest.builder().guestId(1L).roomId(2L).build();
        // Set other fields in the request

        Guest guest = new Guest();
        // Initialize guest as needed

        when(guestRepository.findById(detailRequest.getGuestId())).thenReturn(Optional.of(guest));
        // Mock other dependencies as necessary

        // Act
        BookingDetailsResponse response = bookingService.findBookingDetails(detailRequest);

        // Assert
        assertNotNull(response);
    }


}
