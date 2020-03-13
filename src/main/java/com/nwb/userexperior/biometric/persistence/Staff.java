package com.nwb.userexperior.biometric.persistence;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "staffs")
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "activities")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "staff_id")
    Long staffId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staff", fetch = FetchType.LAZY)
    List<Activity> activities;

    @CreatedDate
    Date createDate;
}
