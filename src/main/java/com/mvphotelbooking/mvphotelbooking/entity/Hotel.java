package com.mvphotelbooking.mvphotelbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotel")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Address address;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Room> rooms;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HotelStatus status;

    // Enums for hotel status
    public enum HotelStatus {
        ACTIVE,
        INACTIVE,
        DELETED
    }
}