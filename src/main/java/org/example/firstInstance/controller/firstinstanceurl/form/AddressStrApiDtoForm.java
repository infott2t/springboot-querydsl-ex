package org.example.firstinstance.controller.firstinstanceurl.form;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class AddressStrApiDtoForm{

    private Long id;
    private String zipCode;
    private String addr1;
    private String addr2;
    private String addrFull;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

}
