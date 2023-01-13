package org.example.domain.roleclass.admin;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.example.domain.address.AddressStr;
import org.example.domain.phone.PhoneStr;

import java.time.LocalDateTime;

@Data

public class RoleADMINApiDto {
    private Long id;

    private AddressStr addressStr;

    private PhoneStr phoneStr;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    @QueryProjection
    public RoleADMINApiDto( Long id
, AddressStr addressStr,PhoneStr phoneStr,String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate
) {
     this.id = id;
    this.addressStr = addressStr;
    this.phoneStr = phoneStr;
    this.isDel = isDel;
    this.modifiedDate = modifiedDate;
     this.createdDate = createdDate;


    }
/*Builder,    id().

 a().

 createDate().

*/}