package org.example.domain.phone;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PhoneStrApiDto {
    private Long id;

    private String phoneNumber;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    @QueryProjection
    public PhoneStrApiDto( Long id
, String phoneNumber, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }





    }
