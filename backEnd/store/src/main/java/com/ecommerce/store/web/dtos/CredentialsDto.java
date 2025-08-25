package com.ecommerce.store.web.dtos;

import lombok.Data;

@Data
public class CredentialsDto {
      private String type;
      private String value;
      private boolean temporary = false;
}
