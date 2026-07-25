package com.hugosantos.url_shortener.model;

import java.time.Instant;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("urls")
public class Url {
  @PrimaryKey
  private String shortcode;
  private String longUrl;
  private Instant createdAt;
}
