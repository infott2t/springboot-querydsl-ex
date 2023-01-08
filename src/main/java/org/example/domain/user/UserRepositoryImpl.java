package org.example.domain.user;
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

//import static goodshop.board.alliance.QAlliance.alliance;

import static org.example.domain.user.QUser.user;
import static org.example.domain.roleclass.user.QRoleUSER.roleUSER;
import static org.example.domain.roleclass.company.QRoleCOMPANY.roleCOMPANY;
import static org.example.domain.roleclass.admin.QRoleADMIN.roleADMIN;
import static org.example.domain.roleclass.guest.QRoleGUEST.roleGUEST;


import static org.springframework.util.StringUtils.hasText;




public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<UserApiDto> searchAllV2(UserSearchCondition condition, Pageable pageable) {

        List<UserApiDto> content = queryFactory.
                select(Projections.constructor(UserApiDto.class,
                        user.id,
                        user.name,
                        user.email,
                        user.picture,
                        user.roleGuest,
                        user.roleUser,
                        user.roleCompany,
                        user.roleAdmin,
                        user.role,
                        user.isDel,
                        user.createDate,
                        user.modifiedDate
                )).from(user)
                .leftJoin(user.roleGuest, roleGUEST)
                .leftJoin(user.roleUser, roleUSER)
                .leftJoin(user.roleCompany, roleCOMPANY)
                .leftJoin(user.roleAdmin, roleADMIN)
                .where(
                     //   searchAllV2Predicate(condition)
                )
                .orderBy(user.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(user.count())
                .from(user)
                .where(
                    //    searchAllV2Predicate(condition)
                )
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }

/*
    private BooleanBuilder searchAllV2Predicate(ProductCategorySearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)) {
            if(field.equals("all")){

                builder.or(alliance.userTitle.like("%" + s + "%"));
                builder.or(alliance.userContent.like("%" + s + "%"));
                //builder.or(alliance.isrtDate.between(sdate, edate));

            } else if(field.equals("title")) {

                builder.or(alliance.userTitle.like("%" + s + "%"));

            } else if(field.equals("content")) {

                builder.or(alliance.userContent.like("%" + s + "%"));

            }
        }

        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(alliance.isrtDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(alliance.isrtDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }
*/


    @Override
    public List<UserApiDto> searchFindAllDesc() {
        List<UserApiDto> content = queryFactory.
                select(Projections.constructor(UserApiDto.class,
                      user.id,
                      user.name,
                      user.email,
                      user.picture,
                      user.roleGuest,
                      user.roleUser,
                        user.roleCompany,
                        user.roleAdmin,
                      user.role,
                      user.isDel,
                      user.createDate,
                      user.modifiedDate
                )).from(user)
                .leftJoin(user.roleGuest, roleGUEST)
                .leftJoin(user.roleUser, roleUSER)
                .leftJoin(user.roleCompany, roleCOMPANY)
                .leftJoin(user.roleAdmin, roleADMIN)
                .orderBy(user.id.asc())
                .fetch();


        return content;
    }
}