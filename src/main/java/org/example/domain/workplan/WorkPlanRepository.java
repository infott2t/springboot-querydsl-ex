package org.example.domain.workplan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPlanRepository extends JpaRepository<WorkPlan, Long>,
        QuerydslPredicateExecutor<WorkPlan>, WorkPlanRepositoryCustom {


}