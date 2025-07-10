package com.kobe.myfavoritenetflix.service;

import com.kobe.myfavoritenetflix.domain.Content;
import com.kobe.myfavoritenetflix.dto.request.ContentCreateRequest;
import com.kobe.myfavoritenetflix.dto.request.ContentUpdateRequest;
import com.kobe.myfavoritenetflix.dto.response.ContentResponse;
import com.kobe.myfavoritenetflix.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

	private final ContentRepository contentRepository;

	@Transactional
	public Long save(final ContentCreateRequest request) {
		Content content = request.toEntity();
		return contentRepository.save(content).getId();
	}

	@Transactional
	public Long update(final Long id, final ContentUpdateRequest request) {
		Content content = contentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다."));
		content.update(request.getTitle(), request.getCategory(), request.getGenres());
		return id;
	}

	@Transactional
	public void delete(final Long id) {
		Content content = contentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다."));
		contentRepository.delete(content);
	}

	@Transactional(readOnly = true)
	public ContentResponse findById(final Long id) {
		Content content = contentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컨텐츠입니다."));
		return new ContentResponse(content);
	}

	@Transactional(readOnly = true)
	public List<ContentResponse> findAll() {
		return contentRepository.findAll().stream()
			.map(ContentResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ContentResponse> findByTitleContaining(final String title) {
		return contentRepository.findByTitleContainingIgnoreCase(title).stream()
			.map(ContentResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ContentResponse> findByCategory(final String category) {
		return contentRepository.findByCategory(category).stream()
			.map(ContentResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ContentResponse> findByGenresContaining(final String genre) {
		return contentRepository.findByGenresContaining(genre).stream()
			.map(ContentResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ContentResponse> findByGenresContainingAll(final List<String> genres) {
		return contentRepository.findByGenresContainingAll(genres, (long) genres.size()).stream()
			.map(ContentResponse::new)
			.collect(Collectors.toList());
	}
}
