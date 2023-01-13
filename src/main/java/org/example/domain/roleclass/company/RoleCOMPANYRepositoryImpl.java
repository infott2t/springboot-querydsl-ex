package org.example.domain.roleclass.company;

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

import static org.example.domain.address.QAddressStr.addressStr;
import static org.example.domain.coperation.QCoperation.coperation;
import static org.example.domain.phone.QPhoneStr.phoneStr;
import static org.example.domain.roleclass.company.QRoleCOMPANY.roleCOMPANY;
import static org.springframework.util.StringUtils.hasText;



public class RoleCOMPANYRepositoryImpl implements RoleCOMPANYRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RoleCOMPANYRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<RoleCOMPANYApiDto> searchAllV2(RoleCOMPANYSearchCondition condition, Pageable pageable) {

        List<RoleCOMPANYApiDto> content = queryFactory.
                select(Projections.constructor(RoleCOMPANYApiDto.class,
                        roleCOMPANY.id,
                        roleCOMPANY.coperation,
                        roleCOMPANY.addressStr,
                        roleCOMPANY.phoneStr,
                        roleCOMPANY.isDel,
                        roleCOMPANY.modifiedDate,
                        roleCOMPANY.createdDate
                )).from(roleCOMPANY)
                .join(roleCOMPANY.coperation, coperation)
                .leftJoin(roleCOMPANY.addressStr, addressStr)
                .leftJoin(roleCOMPANY.phoneStr, phoneStr)

                .where(
                        searchAllV2Predicate(condition)
                ).where(roleCOMPANY.isDel.eq("N"))
                .orderBy(roleCOMPANY.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(roleCOMPANY.count())
                .from(roleCOMPANY)
                .where(
                        searchAllV2Predicate(condition)
                ).where(roleCOMPANY.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanBuilder searchAllV2Predicate(RoleCOMPANYSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)) {
            if(field.equals("id")){

                builder.or(roleCOMPANY.id.eq(Long.parseLong(s)));


            }
        }

        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(roleCOMPANY.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(roleCOMPANY.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<RoleCOMPANYApiDto> searchFindAllDesc() {



        return null;
    }
}