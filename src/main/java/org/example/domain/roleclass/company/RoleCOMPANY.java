package org.example.domain.roleclass.company;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.example.domain.address.AddressStr;
import org.example.domain.coperation.Coperation;
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
@Table(name="ROLE_COMPANY")
public class RoleCOMPANY {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_COMPANY_ID")
    private Long id;

    @ManyToOne(targetEntity = AddressStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressStr addressStr;


    @ManyToOne(targetEntity = PhoneStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONE_STR_ID")
    private PhoneStr phoneStr;

    @ManyToOne(targetEntity = Coperation.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "T_COPERATION_ID")
    private Coperation coperation;

    private LocalDateTime createDate;

    @JsonIgnore
    @OneToMany(mappedBy = "roleUser")
    private final List<User> users = new ArrayList<>();

    public void setAddressStr(AddressStr addressStr0) {
        this.addressStr = addressStr0;
    }

    public void setPhoneStr(PhoneStr phoneStr0) {
        this.phoneStr = phoneStr0;
    }

    @Builder
    public RoleCOMPANY(AddressStr addressStr, PhoneStr phoneStr, Coperation coperation, LocalDateTime createDate) {
        this.addressStr = addressStr;
        this.phoneStr = phoneStr;
        this.coperation = coperation;
        this.createDate = createDate;
    }
}
