package com.example.ecommerce.dto.RequestDto;

import com.example.ecommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto{
    int sellerId;

    String productName;

    int price;

    int quantity;

    ProductCategory productCategory;
}


