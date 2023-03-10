package org.example.domain.coperation;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class CoperationApiDto {
    private Long id;

    private String coperationName  ;
    private String catchPrice  ;

    private String isDel;

    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate    ;

    @QueryProjection
    public CoperationApiDto( Long id
, String coperationName  , String catchPrice, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate
) {
     this.id    = id;
     this.coperationName   = coperationName  ;
     this.catchPrice   = catchPrice  ;
     this.isDel = isDel;

        this.modifiedDate = modifiedDate;
     this.createdDate     = createdDate    ;

    }


/*Builder,    id().

 coperationName  ().

 crateDate    ().

*/}