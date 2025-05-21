package ra.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ra.project.exception.NotFoundException;
import ra.project.model.dto.request.TopicRequest;
import ra.project.model.entity.Topic;
import ra.project.repository.TopicRepository;
import ra.project.service.ITopicService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements ITopicService {
    private final TopicRepository topicRepository;
    private final ModelMapper mapper;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new NotFoundException("ID ko tòn tại"));
    }

    @Override
    public Topic create(TopicRequest topicRequest) {
        Topic topic = mapper.map(topicRequest, Topic.class);
        return topicRepository.save(topic) ;
    }

    @Override
    public Topic update(Long id, TopicRequest topicRequest) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new NotFoundException("ID ko tòn tại"));
        if (topicRequest.getName() != null) {
            topic.setName(topicRequest.getName());
        }
        if (topicRequest.getDescription() != null) {
            topic.setDescription(topicRequest.getDescription());
        }
        return topicRepository.save(topic);
    }

    @Override
    public void delete(Long id) {
        topicRepository.findById(id).orElseThrow(() -> new NotFoundException("ID ko tòn tại"));
        topicRepository.deleteById(id);
    }
}
