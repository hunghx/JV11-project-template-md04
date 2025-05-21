package ra.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.project.model.entity.Vocabulary;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    Page<Vocabulary> findByTopicId(Long topicId, Pageable pageable);
    List<Vocabulary> findByTopicId(Long topicId);
    Page<Vocabulary> findByEnglishContainingOrJapanContainingOrSinoContaining(
            String english, String japan, String sino, Pageable pageable);
} 