// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package lifespace.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqVO, String> {
    // 找目前最大的 FAQ ID
    @Query("SELECT f.faqId FROM FaqVO f WHERE f.faqId LIKE 'FAQ%' ORDER BY f.faqId DESC LIMIT 1")
    String findTopByOrderByFaqIdDesc();
}