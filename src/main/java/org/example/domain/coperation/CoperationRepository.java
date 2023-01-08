package org.example.domain.coperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoperationRepository extends JpaRepository<Coperation, Long>,
        QuerydslPredicateExecutor<Coperation>, CoperationRepositoryCustom {


}