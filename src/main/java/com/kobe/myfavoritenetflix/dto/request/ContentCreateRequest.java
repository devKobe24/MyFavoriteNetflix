package com.kobe.myfavoritenetflix.dto.request;

import com.kobe.myfavoritenetflix.domain.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ContentCreateRequest {

	private String title;
	private String category;
	private List<String> genres;

	@Builder
	public ContentCreateRequest(String title, String category, List<String> genres) {
		this.title = title;
		this.category = category;
		this.genres = genres;
	}

	public Content toEntity() {
		return Content.builder()
			.title(title)
			.category(category)
			.genres(genres)
			.build();
	}
}
