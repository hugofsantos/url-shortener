package com.hugosantos.url_shortener.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hugosantos.url_shortener.model.Url;
import com.hugosantos.url_shortener.repository.UrlRepository;

@Service
public class UrlService {
  private final UrlRepository urlRepository;
  private final ShortcodeService shortcodeService;

  public UrlService(UrlRepository urlRepository, ShortcodeService shortcodeService) {
    this.urlRepository = urlRepository;
    this.shortcodeService = shortcodeService;
  }

  public Url shortenUrl(String longUrl) {
    String shortcode = shortcodeService.generateShortcode();
    Url url = new Url(shortcode, longUrl, java.time.Instant.now());
    return urlRepository.save(url);
  }

  public Optional<Url> getOriginalUrl(String shortcode) {
    return urlRepository.findById(shortcode);
  }
}