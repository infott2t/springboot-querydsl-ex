package org.example.firstinstance.controller.firstinstanceurl.form;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class PhoneStrApiDtoForm {

    private Long id;
    private String phoneNumber;
    private String isDel;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
