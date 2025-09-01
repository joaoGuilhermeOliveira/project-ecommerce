package com.ecommerce.store.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDto {
      private String type;
      private String value;
      private boolean temporary = false;
}
