package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFundingContributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Email can not be null")
    private String contributorEmail;
    @CreationTimestamp
    private Date creationTime;
    private Double contributionAmount;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "requestId")
    private ProjectFundingRequest projectFundingRequest;
}

