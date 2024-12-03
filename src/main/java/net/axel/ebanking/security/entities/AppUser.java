package net.axel.ebanking.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Table(name = "users")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppRole role;

    @PrePersist
    public void assignDefaultRole() {
        if (this.role == null) {
            this.role = new AppRole("ROLE_USER");
        }
    }
}
