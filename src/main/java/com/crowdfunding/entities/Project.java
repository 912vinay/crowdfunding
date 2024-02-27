package com.crowdfunding.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name can not be null")
    @Size(min =  2, max =  255, message = "Name must be between  2 and  255 characters")
    private String name;
    private String description;
    @NotNull(message = "Email can not be null")
    private String innovatorEmail;
    @CreationTimestamp
    private Date creationTime;
    @UpdateTimestamp
    private Date modificationTime;
    @Enumerated(EnumType.STRING)
    private Status status=Status.CREATED;

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProjectFundingRequest> projectFundingRequests;
}
