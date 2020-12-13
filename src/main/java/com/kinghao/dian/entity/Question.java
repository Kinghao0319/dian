package com.kinghao.dian.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NameStyle(Style.normal)
@Table(name = "answer_question")
@Document(collection="question")
public class Question {
    @Id
    private String Id;
    private String type;
    private String title;
    private Integer sortId=0;
//    private Integer question_id;
    //    private String[] content;
//    private Integer numOfWords;
//    private Integer difficulty;
    //    private String standard_answer;
    private String questionnnaire_id;
    private String questionnnaire_title;
//    private boolean mustAnswered;
//    private boolean isfool;
//    private boolean isDerived;
//    private String content;
//    private String answer;
    private String[] content;
    private String answer;

}
////
//class SingleChoice extends Question {
//    private String content[];
//    private String answer;
//}
//
//class MultipleChoice extends Question {
//    private String content;
//    private String[] answer;
//
//}
//
//class ShortAnswers extends Question {
//    private String content;
//    private String answer;
//}
//
////排序题
//class SortQuestion extends Question{
//    //存储问题和对应的排序比如，choice1-》2
//    private String content;
//    private Map<String, Integer> answer;
//}
//
////
//class MatrixMultipleChoice extends Question{
//    //key 为矩阵多选的横纵坐标，value是是否选择
//    private Map<String[][], Boolean> answer;
//}
//
//
//
