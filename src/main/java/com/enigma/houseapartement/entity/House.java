package com.enigma.houseapartement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "m_house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    private String city;
    private String state;
    private Integer unit;
    private Boolean wifi;
    private Boolean laundry;
    @OneToMany(mappedBy = "house")
    private List<HousePrice> housePrices;
    @OneToMany(mappedBy = "house")
    private List<Image> image;
    @Column(nullable = false, name = "is_active")
    private Boolean isActive;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
