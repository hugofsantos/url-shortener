package com.hugosantos.url_shortener.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record ShortenUrlRequest (
  @NotBlank(message = "The longUrl field is required.")
  @URL(message = "The longUrl field must be a valid URL.")
  String longUrl
) {}
