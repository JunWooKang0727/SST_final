package org.sst.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class QuestionReplyPageVO {

	private int replyCnt;
	private List<QuestionReplyVO> list;
}
