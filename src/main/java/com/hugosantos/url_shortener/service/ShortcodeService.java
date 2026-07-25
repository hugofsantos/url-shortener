package com.hugosantos.url_shortener.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.hugosantos.url_shortener.util.Base62Encoder;

@Service
public class ShortcodeService{
  private final StringRedisTemplate redisTemplate;
  private final Base62Encoder base62Encoder;

  private static final String COUNTER_KEY = "url_counter";
  // iniciar de um número alto (ex: 14 milhões)
  // para garantir que os hashes já nasçam com no mínimo 4 caracteres.
  private static final String INITIAL_OFFSET = "14000000"; 
  
  public ShortcodeService(StringRedisTemplate redisTemplate, Base62Encoder base62Encoder) {
    this.redisTemplate = redisTemplate;
    this.base62Encoder = base62Encoder;
  }

  public String generateShortcode() {
    // Se a chave não existir no Redis, ele cria com o valor de 14 milhões.
    // O setIfAbsent é thread-safe.
    redisTemplate.opsForValue().setIfAbsent(COUNTER_KEY, INITIAL_OFFSET);

    // Operação atômica equivalente ao INCR do Redis
    Long id = redisTemplate.opsForValue().increment(COUNTER_KEY);

    // Passa o ID único gerado para o nosso conversor
    return base62Encoder.encode(id);
  }
}