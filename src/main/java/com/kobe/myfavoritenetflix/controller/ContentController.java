package com.kobe.myfavoritenetflix.controller;

import com.kobe.myfavoritenetflix.dto.request.ContentCreateRequest;
import com.kobe.myfavoritenetflix.dto.request.ContentUpdateRequest;
import com.kobe.myfavoritenetflix.dto.response.ContentResponse;
import com.kobe.myfavoritenetflix.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentController {

	private final ContentService contentService;

	@PostMapping
	public ResponseEntity<Long> createContent(@RequestBody ContentCreateRequest request) {
		Long contentId = contentService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(contentId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Long> updateContent(@PathVariable Long id, @RequestBody ContentUpdateRequest request) {
		Long updatedId = contentService.update(id, request);
		return ResponseEntity.ok(updatedId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
		contentService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContentResponse> getContentById(@PathVariable Long id) {
		ContentResponse response = contentService.findById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<ContentResponse>> getAllContents() {
		List<ContentResponse> responses = contentService.findAll();
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/search/title")
	public ResponseEntity<List<ContentResponse>> searchContentsByTitle(@RequestParam String title) {
		List<ContentResponse> responses = contentService.findByTitleContaining(title);
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/search/category")
	public ResponseEntity<List<ContentResponse>> searchContentsByCategory(@RequestParam String category) {
		List<ContentResponse> responses = contentService.findByCategory(category);
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/search/genre")
	public ResponseEntity<List<ContentResponse>> searchContentsByGenre(@RequestParam String genre) {
		List<ContentResponse> responses = contentService.findByGenresContaining(genre);
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/search/genres")
	public ResponseEntity<List<ContentResponse>> searchContentsByGenres(@RequestParam List<String> genres) {
		List<ContentResponse> responses = contentService.findByGenresContainingAll(genres);
		return ResponseEntity.ok(responses);
	}
}
