package io.mallinicouture.backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mc_client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Please enter your first name")
    private String firstName;

    @NotBlank(message = "Please enter you last name")
    private String lastName;

    @NotBlank(message = "Password field is required")
    @JsonIgnore
    private String password;

    @Transient
    private String confirmPassword;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
            orphanRemoval = true,
            optional = false
    )
    @JoinColumn(name = "mc_basket_id", nullable = false)
    @JsonIgnore
    private Basket basket = new Basket(); // basket is required

    @OneToMany(mappedBy = "client",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    @JsonIgnore
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @JsonIgnore
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    @JsonIgnore // Turn off
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore // Turn off
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore // Turn off
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore // Turn off
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore // Turn off
    public boolean isEnabled() {
        return true;
    }
}
