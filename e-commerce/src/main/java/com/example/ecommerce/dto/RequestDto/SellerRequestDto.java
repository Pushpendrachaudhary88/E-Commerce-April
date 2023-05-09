package com.example.ecommerce.dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SellerRequestDto {
    String name;
    String emailId;
    int  age;
    String mobNo;

}
