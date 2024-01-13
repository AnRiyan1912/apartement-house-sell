package com.enigma.houseapartement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "m_house_price")
public class HousePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "house_id")
    @JsonBackReference
    private House house;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
