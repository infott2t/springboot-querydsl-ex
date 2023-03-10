package org.example.domain.roleclass.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;
import org.example.domain.BaseTimeEntity;
import org.example.domain.address.AddressStr;
import org.example.domain.phone.PhoneStr;
import org.example.domain.user.User;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ROLE_USER")
public class RoleUSER extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_USER_ID")
    private Long id;

    @ManyToOne(targetEntity = AddressStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressStr addressStr;


    @ManyToOne(targetEntity = PhoneStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONE_STR_ID")
    private PhoneStr phoneStr;




    @JsonIgnore
    @OneToMany(mappedBy = "roleUser")
    private final List<User> users = new ArrayList<>();


    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    private String isDel;


    @Builder
    public RoleUSER(AddressStr addressStr, PhoneStr phoneStr) {
        this.addressStr = addressStr;
        this.phoneStr = phoneStr;
    }
}
