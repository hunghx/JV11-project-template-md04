package ra.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.project.model.entity.User;
import ra.project.model.entity.UserVocabulary;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVocabularyRepository extends JpaRepository<UserVocabulary, Long> {
    Page<UserVocabulary> findByUserId(Long userId, Pageable pageable);
    List<UserVocabulary> findByUserId(Long userId);
    List<UserVocabulary> findByUser(User user);
    Optional<UserVocabulary> findByUserIdAndVocabId(Long userId, Long vocabId);
    boolean existsByUserIdAndVocabId(Long userId, Long vocabId);
    void deleteByUserIdAndVocabId(Long userId, Long vocabId);
} 