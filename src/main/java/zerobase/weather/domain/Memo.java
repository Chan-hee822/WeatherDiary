package zerobase.weather.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "memo")
public class Memo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// mysql 이 만드는 pk 를 그대로 사용
	private int id;
	private String text;
}
