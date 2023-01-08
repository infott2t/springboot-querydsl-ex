package org.example.domain.roleclass.guest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.example.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ROLE_GUEST")
public class RoleGUEST {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_GUEST_ID")
    private Long id;


    private LocalDateTime createDate;

    @JsonIgnore
    @OneToMany(mappedBy = "roleUser")
    private final List<User> users = new ArrayList<>();
}
