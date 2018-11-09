package org.salem.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="tbl_board")
@Data
public class BoardVO {
	
	//db에 자동으로 만들기 위해 이 노테이션들을 써준다.
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_board")
	@SequenceGenerator(name="seq_board", sequenceName="SEQ_BOARD" )
	private Long bno;
	
	@Column(length=300)
	private String title;
	
	@Lob
	private String content;
	private String writer;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;
	
}
