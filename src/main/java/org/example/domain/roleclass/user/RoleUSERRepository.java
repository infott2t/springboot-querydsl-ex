package org.example.domain.roleclass.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUSERRepository extends JpaRepository<RoleUSER, Long>,
        QuerydslPredicateExecutor<RoleUSER>, RoleUSERRepositoryCustom {


}