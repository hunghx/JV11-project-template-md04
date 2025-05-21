package ra.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.project.model.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByName(String name);
} 