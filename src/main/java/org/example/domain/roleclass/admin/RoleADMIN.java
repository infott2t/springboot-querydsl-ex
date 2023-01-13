package org.example.domain.roleclass.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.example.domain.address.AddressStr;
import org.example.domain.phone.PhoneStr;
import org.example.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ROLE_ADMIN")
public class RoleADMIN  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ADMIN_ID")
    private Long id;

    @ManyToOne(targetEntity = AddressStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressStr addressStr;


    @ManyToOne(targetEntity = PhoneStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONE_STR_ID")
    private PhoneStr phoneStr;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;


    @JsonIgnore
    @OneToMany(mappedBy = "roleUser")
    private final List<User> users = new ArrayList<>();



    @Builder
    public RoleADMIN(AddressStr addressStr, PhoneStr phoneStr, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.addressStr = addressStr;
        this.phoneStr = phoneStr;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;

    }
}
