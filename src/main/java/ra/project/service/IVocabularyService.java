package ra.project.service;

import ra.project.model.entity.Vocabulary;

import java.util.List;

public interface IVocabularyService extends IGenericService<Vocabulary, Long , Vocabulary, Vocabulary>{
    List<Vocabulary> getVocabulariesByTopicId(Long topicId);
}
