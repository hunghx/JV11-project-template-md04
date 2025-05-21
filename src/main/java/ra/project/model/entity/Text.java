package ra.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "texts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vocab_id")
    private Long vocabId;

    private String component;

    private String meaning;

    @ManyToOne
    @JoinColumn(name = "vocab_id", insertable = false, updatable = false)
    private Vocabulary vocabulary;
} 