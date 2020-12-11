package com.kinghao.dian.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class AnswerQuestion {
    @Id
    private String Id;
    private String title;
    private String answer_questionnnaire_id;
    private String answer_questionnnaire_title;
    private Integer answer_user_id;
    private String type;

}
class AqSingleChoice extends AnswerQuestion {
    private String answer;
}

class AqMultipleChoice extends AnswerQuestion {
    private String[] answer;

}

class AqShortAnswers extends AnswerQuestion {
    private String answer;
}

//排序题
class AqSortQuestion extends AnswerQuestion{
    //存储问题和对应的排序比如，choice1-》2
     Map<String, Integer> answer;
}

//
class AqMatrixMultipleChoice extends AnswerQuestion{
    //key 为矩阵多选的横纵坐标，value是是否选择
    private Map<String[][], Boolean> answer;
}



