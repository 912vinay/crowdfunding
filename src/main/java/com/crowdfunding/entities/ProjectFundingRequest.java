package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFundingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;
    private Double amount;
    @NotNull(message = "paymentDetails can not be null")
    private String paymentDetails;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date modificationTime;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    @OneToMany(mappedBy = "projectFundingRequest", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ProjectFundingContributor> projectFundingContributors;
}
