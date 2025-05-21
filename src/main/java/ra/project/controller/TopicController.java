package ra.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.project.model.dto.request.TopicRequest;
import ra.project.model.entity.Topic;
import ra.project.service.ITopicService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {
    private final ITopicService topicService;
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.findById(id));
    }
    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Object>> createTopic(@RequestBody TopicRequest request) {
        Topic topic = topicService.create(request);
        Map<String, Object> response = Map.of(
                "message", "Thêm chủ đề thành công",
                "topic", topic
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
//        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTopic(@PathVariable Long id, @RequestBody TopicRequest request) {
        Topic topic = topicService.update(id,request);
        Map<String, Object> response = Map.of(
                "message", "Cập nhật chủ đề thành công",
                "topic", topic
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
