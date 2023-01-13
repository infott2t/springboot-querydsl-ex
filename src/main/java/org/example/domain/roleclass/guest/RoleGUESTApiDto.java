package org.example.domain.roleclass.guest;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class RoleGUESTApiDto {
    private Long id;


    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    @QueryProjection
    public RoleGUESTApiDto( Long id
, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }


}