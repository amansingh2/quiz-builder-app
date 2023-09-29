package com.quiz.quizbuilder.entity;


import com.quiz.quizbuilder.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update Question set deleted = true where id = ?")
@Where(clause = "deleted = false")
public class Question extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String body;

    private boolean isMultiChoice = false;

    @OneToOne
    private Quiz quiz;

    private boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        return body.equalsIgnoreCase(question.body);
    }

    @Override
    public int hashCode() {
        return body.hashCode();
    }
}
