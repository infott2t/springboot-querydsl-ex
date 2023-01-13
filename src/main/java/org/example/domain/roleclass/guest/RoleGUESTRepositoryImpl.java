package org.example.domain.roleclass.guest;

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

import static org.example.domain.roleclass.guest.QRoleGUEST.roleGUEST;
import static org.springframework.util.StringUtils.hasText;



public class RoleGUESTRepositoryImpl implements RoleGUESTRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RoleGUESTRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<RoleGUESTApiDto> searchAllV2(RoleGUESTSearchCondition condition, Pageable pageable) {

        List<RoleGUESTApiDto> content = queryFactory.
                select(Projections.constructor(RoleGUESTApiDto.class,
                        roleGUEST.id,
                        roleGUEST.isDel,
                        roleGUEST.modifiedDate,
                        roleGUEST.createdDate
                )).from(roleGUEST)
                .where(
                        searchAllV2Predicate(condition)
                ).where(roleGUEST.isDel.eq("N"))
                .orderBy(roleGUEST.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(roleGUEST.count())
                .from(roleGUEST)
                .where(
                        searchAllV2Predicate(condition)
                ).where(roleGUEST.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanBuilder searchAllV2Predicate(RoleGUESTSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)) {
            if(field.equals("id")){

                builder.or(roleGUEST.id.eq(Long.parseLong(s)));


            }
        }

        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(roleGUEST.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(roleGUEST.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<RoleGUESTApiDto> searchFindAllDesc() {



        return null;
    }
}