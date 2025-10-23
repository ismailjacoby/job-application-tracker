package com.ismailjacoby.jobtrackerapi.model.entity;

import com.ismailjacoby.jobtrackerapi.model.enums.ApplicationStatus;
import com.ismailjacoby.jobtrackerapi.model.enums.JobSource;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "jobs")
public class Job extends BaseEntity<Long> {
    @Column(nullable = false)
    @Size(max = 100)
    private String title;

    @Column(nullable = false)
    @Size(max = 50)
    private String companyName;

    @Column(nullable = false)
    @Size(max = 100)
    private String location;

    @Column(nullable = false)
    private String jobUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobSource jobSource;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate dateApplied;

    private String recruiterName;
    private String recruiterEmail;
    private String recruiterPhone;

    private String salary;

    @Column(length = 200)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
