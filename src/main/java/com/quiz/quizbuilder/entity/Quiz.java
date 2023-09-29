package com.quiz.quizbuilder.entity;


import com.quiz.quizbuilder.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update Quiz set deleted = true where id = ?")
@Where(clause = "deleted = false")
public class Quiz extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private int numberOfQuestions;
    private int maxMarks;
    private long timeInSeconds;
    private boolean isPublished = false;
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;


}
