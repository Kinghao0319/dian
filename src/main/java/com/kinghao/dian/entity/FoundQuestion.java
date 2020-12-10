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
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NameStyle(Style.normal)
@Table(name = "found_question")
public class FoundQuestion {
    @Id
    private String Id;
    private String title;
    private Integer sort_id;
//    private String[] content;
    private String type;
    private Integer numOfWords;
    private Integer difficulty;
//    private String standard_answer;
    private String found_questionnnaire_id;
    private String found_questionnnaire_title;
    private boolean mustAnswered;
    private boolean isfool;
    private boolean isDerived;


}
class SingleChoice extends FoundQuestion {
    private Integer difficultyOfType = 10;
    private String[] content;
    //比如A,B,C,D...,取决于content.length,但是只能选择一个选项，比如"A"
    private String standard_anwser;
}

class MultipleChoice extends FoundQuestion {
    private Integer difficultyOfType = 20;
    private String[] content;
    //比如[A,B],[B,C,D]
    private String[] standard_anwser;
}

class ShortAnswers extends FoundQuestion {
    private Integer difficultyOfType = 20;
}

//排序题
class SortQuestion extends FoundQuestion{
    private Integer difficultyOfType = 30;
    //存储问题和对应的排序比如，choice1-》2
    private String[] content;
}

//
class MatrixMultipleChoice extends FoundQuestion{
    //问题内容实际上是两个string数组的两两组合
    private String[][] content;

}


