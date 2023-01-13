package org.example.domain.coperation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.example.domain.coperation.QCoperation.coperation;
import static org.springframework.util.StringUtils.hasText;



public class CoperationRepositoryImpl implements CoperationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CoperationRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<CoperationApiDto> searchAllV2(CoperationSearchCondition condition, Pageable pageable) {

        List<CoperationApiDto> content = queryFactory.
                select(Projections.constructor(CoperationApiDto.class,
                        coperation.id,
                        coperation.coperationName  ,
                        coperation.catchPrice  ,
                        coperation.isDel,
                        coperation.modifiedDate,
                        coperation.createdDate

                )).from(coperation)
                .where(
                       searchAllV2Predicate(condition)
                ).where(coperation.isDel.eq("N"))
                .orderBy(coperation.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(coperation.count())
                .from(coperation)
                .where(
                        searchAllV2Predicate(condition)
                ).where(coperation.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanBuilder searchAllV2Predicate(CoperationSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)) {
             if(field.equals("id")) {

                builder.or(coperation.id.eq(Long.parseLong(s)));

            } else if(field.equals("coperationName")) {

                builder.or(coperation.coperationName.like("%" + s + "%"));

            }
        }

        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(coperation.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }

    private Predicate condEdate( String edate){
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(edate)) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(edate + "T00:00:00");
                builder.or(coperation.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<CoperationApiDto> searchFindAllDesc() {
        List<CoperationApiDto> content = queryFactory.
                select(Projections.constructor(CoperationApiDto.class,
                        coperation.id,
                        coperation.coperationName  ,
                        coperation.catchPrice  ,
                        coperation.modifiedDate,
                        coperation.createdDate
                )).from(coperation)
                .orderBy(coperation.id.asc())
                .fetch();


        return content;
    }
}