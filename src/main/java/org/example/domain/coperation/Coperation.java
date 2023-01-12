package org.example.domain.coperation;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.domain.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="T_COPERATION")
public class Coperation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_COPERATION_ID")
    private Long id;

    private String coperationName;  //협력사명

    private String catchPrice;  //캐치프레이즈 예)  김치를 만들어보세요, 반조리음식. 이 일은 어떤가요.

    private String isDel;

    private LocalDateTime modifiedDate;

    private LocalDateTime createdDate;    //  생성일

    @Builder
    public Coperation(Long id, String coperationName, String catchPrice, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.coperationName = coperationName;
        this.catchPrice = catchPrice;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }

}
