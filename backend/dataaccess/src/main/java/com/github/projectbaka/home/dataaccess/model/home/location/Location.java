package com.github.projectbaka.home.dataaccess.model.home.location;

import com.github.projectbaka.home.dataaccess.model.home.Home;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "home_location")
public class Location {

    @Id
    @GeneratedValue(generator = "uuidv6")
    @GenericGenerator(name = "uuidv6", strategy = "com.github.projectbaka.home.dataaccess.util.UUID6Generator")
    @Column(length = 16)
    private UUID id;

    @ManyToOne(optional = false)
    private Home home;

    //
    @Size(min = 1)
    @Column(nullable = false, length = 64)
    private String name;

    @Column(length = 1024)
    private String description;
    //

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 64)
    private String createdBy;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastModifiedAt;

    @LastModifiedBy
    @Column(nullable = false, length = 64)
    private String lastModifiedBy;
}
