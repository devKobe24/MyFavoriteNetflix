package com.kobe.myfavoritenetflix.domain;
// 핵심 엔티티
// BaseTimeEntity를 상속받아 Content 엔티티를 구현.

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 추가, 접근 권한을 protected로 설정
@Entity // 이 클래스는 테이블과 링크될 클래스임을 나타냅니다.
@Table(name = "contents") // 실제 DB 테이블명을 "contents"로 지정합니다.
public class Content extends BaseTimeEntity {

	@Id // 해당 테이블의 PK(Primary Key) 필드임을 나타냅니다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙. IDENTITY는 MySQL의 AUTO_INCREMENT와 같습니다.
	private Long id;

	@Column(nullable = false) // 해당 컬럼이 null을 허용하지 않음을 나타냄.
	private String title;

	@Column(nullable = false)
	private String category;

	@ElementCollection(fetch = FetchType.EAGER) // 값 타입 컬렉션을 매핑할 때 사용합니다. EAGER 전략으로 Content 조회 시 항상 genres도 함께 조회합니다.
	@CollectionTable(
		name = "content_genres", // `genres`를 저장할 별도의 테이블 이름
		joinColumns = @JoinColumn(name = "content_id") // `content_genres` 테이블에서 `Content` 테이블을 참조하는 외래 키
	)
	@Column(name = "genre", nullable = false) // `content_genres` 테이블의 장르 이름 컬럼
	private List<String> genres;

	@Builder // 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
	public Content(String title, String category, List<String> genres) {
		this.title = title;
		this.category = category;
		this.genres = genres;
	}

	// 정보 수정을 위한 메소드
	public void update(String title, String category, List<String> genres) {
		this.title = title;
		this.category = category;
		this.genres = genres;
	}
}