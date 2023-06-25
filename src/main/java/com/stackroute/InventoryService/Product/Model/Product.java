package com.stackroute.InventoryService.Product.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String productId;
    private String productName;
    private Double productPrice;
    private String productBrand;
    private Double sellingPrice;
    private Double productQuantity;
    private String productCategory;
    private String dateOfPost;
    private String productDescription;
    private String productAvailability;
    private String productCancellation;
    private String productReturnable;
    private String productDiscountedPrice;
    private String productFinalPrice;

}
