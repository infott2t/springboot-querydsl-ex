package org.example.domain.roleclass.company;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.example.domain.address.AddressStr;
import org.example.domain.coperation.Coperation;
import org.example.domain.phone.PhoneStr;

import java.time.LocalDateTime;

@Data

public class RoleCOMPANYApiDto {
    private Long roleCompanyId;
    private Coperation coperation;

    private AddressStr addressStr;

    private PhoneStr phoneStr;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;




    @QueryProjection
    public RoleCOMPANYApiDto( Long roleCompanyId, Coperation coperation, AddressStr addressStr, PhoneStr phoneStr, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.roleCompanyId = roleCompanyId;
        this.coperation = coperation;
        this.addressStr = addressStr;
        this.phoneStr = phoneStr;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }


/*Builder,    roleCompanyId().

 a().

 b().

*/}