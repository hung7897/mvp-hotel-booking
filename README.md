# MVP Hotel Booking System
The MVP Hotel Booking System is a simple web application built using Spring Boot, Java 11, and PostgreSQL. It allows guests to book hotel rooms and manage reservations.

### Features
* Guest registration and login
* Room availability checking
* Booking creation
* Reservation details retrieval
### Prerequisites
* Java 11
* PostgreSQL database (configured in application.properties)
* Maven (for building and running the project)
### Getting Started
Clone this repository:
* git clone https://github.com/yourusername/mvp-hotel-booking.git

* Configure your PostgreSQL database settings in src/main/resources/application.properties.
* Build and run the application:
cd mvp-hotel-booking
mvn spring-boot:run

* Access the application at http://localhost:8888.
### API Endpoints
* POST /api/register: Register a new guest. (TBU)
* POST /api/login: Authenticate a guest. (TBU)
* POST /api/hotel/bookings: Create a new reservation.
* GET /api/hotel/bookings/guestId={guestId}&roomId={}: Retrieve reservation details.
* GET /api/hotels/search-by-address: Get a list of available hotels.
### Project Structure
```
mvp-hotel-booking/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.mvphotelbooking/
│   │   │   │   ├── controller/
│   │   │   │   ├── entity/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   ├── service/
│   │   │   │   └── MvpHotelBookingApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── static/
│   │   │       └── db.changelog/
│   │   │       └── templates/
│   │   └── ...
│   └── test/
│       └── ...
├── pom.xml
└── README.md
```
### Contributors
Hung Nguyen
### License
TBU