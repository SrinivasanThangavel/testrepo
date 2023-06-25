package com.stackroute.InventoryService.Order.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private String orderId;
    private String userEmailId;
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
    private Double productDiscountedPrice;
    private Double productFinalPrice;

    private OrderStatus orderStatus;
    private String orderDateAndTime;
    private Double orderTotalPrice;




}
