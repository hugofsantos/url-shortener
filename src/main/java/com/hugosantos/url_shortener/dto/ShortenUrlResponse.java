package com.hugosantos.url_shortener.dto;

import java.time.Instant;

public record ShortenUrlResponse (
  String shortcode,
  String shortUrl,
  Instant createdAt
) {}