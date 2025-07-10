package com.kobe.myfavoritenetflix.repository;

import com.kobe.myfavoritenetflix.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
	/**
	 * 제목에 특정 문자열이 포함된 컨텐츠 목록을 조회합니다. (대소문자 무시)
	 * @param title 검색할 제목 문자열
	 * @return 컨텐츠 리스트
	 */
	List<Content> findByTitleContainingIgnoreCase(String title);

	/**
	 * 특정 카테고리에 속하는 컨텐츠 목록을 조회합니다.
	 * @param category 검색할 카테고리
	 * @return 컨텐츠 리스트
	 */
	List<Content> findByCategory(String category);

	/**
	 * [장르 검색 - 하나라도 일치]
	 * 특정 장르를 포함하는 컨텐츠 목록을 조회합니다. (예: "액션", 또는 "학원물" 단일 검색)
	 * @param genre 검색할 장르
	 * @return 컨텐츠 리스트
	 */
	List<Content> findByGenresContaining(String genre);

	/**
	 * [장르 검색 - 모두 일치]
	 * 주어진 모든 장르를 포함하는 컨텐츠 목록을 조회합니다. (예: "액션"과 "학원물" 동시 검색)
	 * @param genres 검색할 장르 목록
	 * @param genreCount 검색할 장르의 수 (서비스 로직에서 genres.size()를 Long 타입으로 넘겨주어야 합니다.)
	 * @return 컨텐츠 리스트
	 */
	@Query("SELECT c FROM Content c WHERE (SELECT COUNT(DISTINCT g) FROM c.genres g WHERE g IN :genres) = :genreCount")
    List<Content> findByGenresContainingAll(@Param("genres") List<String> genres, @Param("genreCount") Long genreCount);
}
