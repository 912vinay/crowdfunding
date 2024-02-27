package com.crowdfunding.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class ProjectFundingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    @NotNull(message = "Email can not be null")
    private Double amount;
    @NotNull(message = "paymentDetails can not be null")
    private String paymentDetails;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date modificationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "projectFundingRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectFundingContributor> contributors;
}
