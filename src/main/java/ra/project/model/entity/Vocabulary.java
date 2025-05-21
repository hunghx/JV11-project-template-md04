package ra.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vocabularies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sino;

    private String japan;

    private String english;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "vocabulary")
    @JsonIgnore
    private List<UserVocabulary> userVocabularies;

    @ManyToOne
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    @JsonIgnore
    private Topic topic;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
} 