package com.hugosantos.url_shortener.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hugosantos.url_shortener.dto.ShortenUrlRequest;
import com.hugosantos.url_shortener.dto.ShortenUrlResponse;
import com.hugosantos.url_shortener.model.Url;
import com.hugosantos.url_shortener.service.UrlService;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UrlController {
  private final UrlService urlService;

  public UrlController(UrlService urlService) {
    this.urlService = urlService;
  }

  @PostMapping("/api/v1/urls")
  public ResponseEntity<ShortenUrlResponse> createShortUrl(@Valid @RequestBody ShortenUrlRequest request) {
    final Url savedUrl = urlService.shortenUrl(request.longUrl());

    // Monta a URL completa baseada no host em que a aplicação está rodando (ex: localhost:8080)
    final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    final String fullUrl = baseUrl + "/" + savedUrl.getShortcode();

    final ShortenUrlResponse response = new ShortenUrlResponse(
      savedUrl.getShortcode(),
      fullUrl,
      savedUrl.getCreatedAt()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{shortcode}")
  public ResponseEntity<Object> redirect(@PathVariable String shortcode) {
    return urlService.getOriginalUrl(shortcode)
      .map(url -> ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.getLongUrl())).build()) // Redirect 302
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
