package org.example.domain.phone;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
public class PhoneStr extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_STR_ID")
    private Long id;

    //전화번호
    private String phoneNumber;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;


    @Builder
    public PhoneStr(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public PhoneStr() {

    }
}
