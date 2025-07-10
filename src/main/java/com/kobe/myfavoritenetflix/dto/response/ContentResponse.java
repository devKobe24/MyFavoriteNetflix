package com.kobe.myfavoritenetflix.dto.response;

import com.kobe.myfavoritenetflix.domain.Content;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ContentResponse {

	private final Long id;
	private final String title;
	private final String category;
	private final List<String> genres;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public ContentResponse(Content content) {
		this.id = content.getId();
		this.title = content.getTitle();
		this.category = content.getCategory();
		this.genres = content.getGenres();
		this.createdAt = content.getCreatedAt();
		this.updatedAt = content.getUpdatedAt();
	}
}
