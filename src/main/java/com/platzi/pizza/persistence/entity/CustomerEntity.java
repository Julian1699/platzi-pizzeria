package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @Column(name = "id_customer",nullable = false,length = 15)
    private String idCustomer;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "address", length = 60)
    private String address;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}
