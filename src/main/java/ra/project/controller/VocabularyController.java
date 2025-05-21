package ra.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.project.model.entity.Vocabulary;
import ra.project.service.ITopicService;
import ra.project.service.IVocabularyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vocabularies")
@RequiredArgsConstructor
public class VocabularyController   {
    private  final IVocabularyService vocabularyService;
    @GetMapping("/topics/{topicId}")
    public ResponseEntity<List<Vocabulary>> getVocabulariesByTopicId(@PathVariable Long topicId) {
        // Implement logic to fetch vocabularies by topic ID
        return ResponseEntity.ok(vocabularyService.getVocabulariesByTopicId(topicId));
    }
}
