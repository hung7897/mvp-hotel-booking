package com.mvphotelbooking.mvphotelbooking.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "guest")
public class Guest extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long guestId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

}
