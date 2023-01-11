package org.example.domain.roleclass.user;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.example.domain.address.AddressStr;
import org.example.domain.phone.PhoneStr;

import java.time.LocalDateTime;

@Data

public class RoleUSERApiDto {
    private Long id;

    private AddressStr addressStr;

    private PhoneStr phoneStr;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public RoleUSERApiDto( Long id,AddressStr addressStr, PhoneStr phoneStr, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.addressStr = addressStr;
        this.phoneStr = phoneStr;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

/*Builder,    id().

 a().

 createDate().

*/}