package com.brihaspathee.zeus.domain.security;

import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import jakarta.persistence.*;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 23, May 2022
 * Time: 6:07 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.domain.entity.security
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(Types.LONGVARCHAR)
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "authority_id", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID authorityId;

    @Column(name = "permission", nullable = false, length = 100)
    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", permission='" + permission + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return authorityId.equals(authority.authorityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId);
    }
}
