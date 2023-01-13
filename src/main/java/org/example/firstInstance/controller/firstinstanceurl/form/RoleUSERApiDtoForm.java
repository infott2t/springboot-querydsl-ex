package org.example.firstinstance.controller.firstinstanceurl.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleUSERApiDtoForm {

    private Long id;
    private Long addressStrId;
    private Long phoneStrId;

    private LocalDateTime modifiedDate;

    private LocalDateTime createdDate;
}
