package org.example.domain.address;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class AddressStrApiDto {
    private Long id;

    private String zipCode;
    private String addr1;
    private String addr2;

    private String addrFull;

    private String isDel;

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;




    @QueryProjection
    public AddressStrApiDto( Long id, String zipCode, String addr1, String addr2, String addrFull, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.zipCode = zipCode;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addrFull = addrFull;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }

/*Builder,    id().

 zipCode().
 addr1().
 addr2().

 a().

*/}