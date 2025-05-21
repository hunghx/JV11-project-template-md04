package ra.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_vocabularies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "vocab_id")
    private Long vocabId;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "added_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "vocab_id", insertable = false, updatable = false)
    private Vocabulary vocabulary;

    @PrePersist
    protected void onCreate() {
        addedAt = new Date();
    }
} 