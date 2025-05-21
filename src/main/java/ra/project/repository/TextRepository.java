package ra.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.project.model.entity.Text;

import java.util.List;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findByVocabId(Long vocabId);
    void deleteByVocabId(Long vocabId);
} 