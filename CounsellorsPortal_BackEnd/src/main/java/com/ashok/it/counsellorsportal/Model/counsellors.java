package com.ashok.it.counsellorsportal.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class counsellors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counsellorId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Long phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "counsellor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<enquiries> enquiriesList;
}
