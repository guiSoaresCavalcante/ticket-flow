package br.com.authsrv.infrastructure.adapter.out.database.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccountEntity {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "profile_id", nullable = false)
    private String profileId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "profile_type", nullable = false)
    private String profileType;

    protected UserAccountEntity() {
    }

    public UserAccountEntity(UUID id, String username, String passwordHash, String profileId,
                             String name, String document, String profileType) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.profileId = profileId;
        this.name = name;
        this.document = document;
        this.profileType = profileType;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getProfileType() {
        return profileType;
    }
}
