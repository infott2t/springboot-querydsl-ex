package org.example.domain.address;

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
import static org.springframework.util.StringUtils.hasText;



public class AddressStrRepositoryImpl implements AddressStrRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AddressStrRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<AddressStrApiDto> searchAllV2(AddressStrSearchCondition condition, Pageable pageable) {

        List<AddressStrApiDto> content = queryFactory.
                select(Projections.constructor(AddressStrApiDto.class,
                        addressStr.id,
                        addressStr.zipCode,
                        addressStr.addr1,
                        addressStr.addr2,
                        addressStr.addrFull,
                        addressStr.isDel,
                        addressStr.modifiedDate,
                        addressStr.createdDate
                )).from(addressStr)
                .where(
                        searchAllV2Predicate(condition)
                ).where(addressStr.isDel.eq("N"))
                .orderBy(addressStr.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(addressStr.count())
                .from(addressStr)
                .where(
                        searchAllV2Predicate(condition)
                ).where(addressStr.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }


    private BooleanBuilder searchAllV2Predicate(AddressStrSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)) {
            if(field.equals("all")){

                builder.or(addressStr.addrFull.like("%" + s + "%"));

                //builder.or(alliance.isrtDate.between(sdate, edate));

            } else if(field.equals("zipcode")) {

                builder.or(addressStr.zipCode.like("%" + s + "%"));

            } else if(field.equals("addr1")) {

                builder.or(addressStr.addr1.like("%" + s + "%"));

            } else if(field.equals("addr2")) {

                builder.or(addressStr.addr2.like("%" + s + "%"));

            } else if(field.equals("id")){
                builder.or(addressStr.id.eq(Long.parseLong(s)));
            }
        }

        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(addressStr.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(addressStr.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<AddressStrApiDto> searchFindAllDesc() {
        List<AddressStrApiDto> content = queryFactory.
                select(Projections.constructor(AddressStrApiDto.class,
                      addressStr.id,
                      addressStr.zipCode,
                      addressStr.addr1,
                      addressStr.addr2

                )).from(addressStr)
                .orderBy(addressStr.id.asc())
                .fetch();


        return content;
    }
}