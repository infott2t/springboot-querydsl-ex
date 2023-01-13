package org.example.domain.roleclass.admin;

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
import static org.example.domain.phone.QPhoneStr.phoneStr;
import static org.example.domain.roleclass.admin.QRoleADMIN.roleADMIN;
import static org.springframework.util.StringUtils.hasText;



public class RoleADMINRepositoryImpl implements RoleADMINRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RoleADMINRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<RoleADMINApiDto> searchAllV2(RoleADMINSearchCondition condition, Pageable pageable) {

        List<RoleADMINApiDto> content = queryFactory.
                select(Projections.constructor(RoleADMINApiDto.class,
                        roleADMIN.id,
                        roleADMIN.addressStr,
                        roleADMIN.phoneStr,
                        roleADMIN.isDel,
                        roleADMIN.modifiedDate,
                        roleADMIN.createdDate
                )).from(roleADMIN)
                .leftJoin(roleADMIN.addressStr, addressStr)
                .leftJoin(roleADMIN.phoneStr, phoneStr)
                .where(
                        searchAllV2Predicate(condition)
                ).where(roleADMIN.isDel.eq("N"))
                .orderBy(roleADMIN.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(roleADMIN.count())
                .from(roleADMIN)
                .where(
                        searchAllV2Predicate(condition)
                ).where(roleADMIN.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<RoleADMINApiDto> searchFindAllDesc() {
        return null;
    }


    private BooleanBuilder searchAllV2Predicate(RoleADMINSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)) {
            if(field.equals("id")){

                builder.or(roleADMIN.id.eq(Long.parseLong(s)));


            }
        }

        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(roleADMIN.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }

    private Predicate condEdate(String edate){
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(edate)) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(edate + "T00:00:00");
                builder.or((roleADMIN.modifiedDate.loe(localDateTime))); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }




}