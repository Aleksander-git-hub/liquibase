package com.shop.liquibase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String secondName;

    private String phone;

    private String email;

    private Boolean deleted;

    private Integer age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CartEntity> carts;
}
