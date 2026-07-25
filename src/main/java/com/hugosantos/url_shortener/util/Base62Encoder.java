package com.hugosantos.url_shortener.util;

import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {
  // 10 números + 26 minúsculas + 26 maiúsculas = 62 caracteres
  private static final String ALLOWED_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final int BASE = ALLOWED_CHARACTERS.length();

  public String encode(long input) {
    if (input == 0) {
      return String.valueOf(ALLOWED_CHARACTERS.charAt(0));
    }

    final StringBuilder encodedString = new StringBuilder();

    while (input > 0) {
      int remainder = (int) (input % BASE);
      encodedString.append(ALLOWED_CHARACTERS.charAt(remainder));
      input = input / BASE;
    }    

    // Como vamos dividindo e pegando o resto, a string fica invertida.
    // Precisamos reverter no final para o hash correto.
    return encodedString.reverse().toString();
  }
}