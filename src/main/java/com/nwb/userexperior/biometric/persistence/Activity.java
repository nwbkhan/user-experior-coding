package com.nwb.userexperior.biometric.persistence;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "staff_activities")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "start_time", nullable = false)
    Date startTime;

    @Column(name = "duration", nullable = false)
    int duration;

    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    Staff staff;

    @Column(name = "name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    ActivityType name;

    @CreatedDate
    Date createDate;
}
