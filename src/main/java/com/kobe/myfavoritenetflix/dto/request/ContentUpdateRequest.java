package com.kobe.myfavoritenetflix.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ContentUpdateRequest {

	private String title;
	private String category;
	private List<String> genres;

	@Builder
	public ContentUpdateRequest(String title,String category, List<String> genres) {
		this.title = title;
		this.category = category;
		this.genres = genres;
	}
}
