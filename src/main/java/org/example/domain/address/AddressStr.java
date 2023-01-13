package org.example.domain.address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.BaseTimeEntity;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AddressStr  extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    //우편번호
    private String zipCode;
    //도로명 주소
    private String addr1;
    //상세 주소
    private String addr2;

    //주소 풀 네임
    private String addrFull;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;


    @Builder
    public AddressStr(String zipCode, String addr1, String addr2, String isDel) {

        this.zipCode = zipCode;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addrFull = zipCode + " " + addr1 + " " + addr2;
    }

    public AddressStr() {

    }
}
