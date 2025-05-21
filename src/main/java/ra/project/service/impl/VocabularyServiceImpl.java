package ra.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.project.model.entity.Vocabulary;
import ra.project.repository.VocabularyRepository;
import ra.project.service.IVocabularyService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements IVocabularyService {
    private final VocabularyRepository vocabularyRepository;

    @Override
    public List<Vocabulary> getVocabulariesByTopicId(Long topicId) {
        return vocabularyRepository.findByTopicId(topicId);
    }

    @Override
    public List<Vocabulary> findAll() {
        return List.of();
    }

    @Override
    public Vocabulary findById(Long id) {
        return null;
    }

    @Override
    public Vocabulary create(Vocabulary vocabulary) {
        return null;
    }

    @Override
    public Vocabulary update(Long id, Vocabulary vocabulary) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
