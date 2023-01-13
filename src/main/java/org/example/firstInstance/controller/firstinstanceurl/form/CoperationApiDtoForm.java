package org.example.firstinstance.controller.firstinstanceurl.form;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class CoperationApiDtoForm {

    private Long id;
    private String coperationName;
    private String catchPrice;
    private String isDel;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
