package org.salem.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude="board")
public class FreeReply {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rno;
	private String reply;
	private String replyer;
	
	@CreationTimestamp
	private LocalDateTime replyDate;
	
	@UpdateTimestamp
	private LocalDateTime replyUpdate;
	
	@JsonIgnore //이걸 쓰면 댓글만 나온다.
	@ManyToOne(fetch=FetchType.LAZY)
	private FreeBoard board;
	
}
