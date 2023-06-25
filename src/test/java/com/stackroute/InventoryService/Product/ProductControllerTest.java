package com.stackroute.InventoryService.Product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.InventoryService.Product.Controller.ProductController;
import com.stackroute.InventoryService.Product.Exception.ProductNotFoundException;
import com.stackroute.InventoryService.Product.Model.Product;
import com.stackroute.InventoryService.Product.Service.ProductService;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateStock() throws Exception {
        // Create a new Product object
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Item 1");

        // Mock the productService's addProduct method
        Mockito.when(productService.addProduct(Mockito.any(Product.class)))
                .thenReturn(product);

        // Convert the Product object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(product);

        // Perform the HTTP POST request
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("Item 1"));
    }



    @Test
    public void testCreateStockWithInvalidData() throws Exception {
        Product product = new Product();
        product.setProductName("Item 1");

        when(productService.addProduct(Mockito.any(Product.class)))
                .thenThrow(new IllegalArgumentException("Invalid data"));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Invalid data"));
    }

    @Test
    public void testGetAllStocks() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Item 1");
        products.add(product);

        when(productService.getAllProducts())
                .thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].stockId", Matchers.is("1")))
                .andExpect(jsonPath("$[0].stockName", Matchers.is("Item 1")));
    }

    @Test
    public void testGetStocksById() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Item 1");

        when(productService.getProductById(Mockito.eq("1")))
                .thenReturn(product);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expectedJson = "{\"stockId\":\"1\",\"stockName\":\"Item 1\"}";
        String responseJson = result.getResponse().getContentAsString();

        try {
            JSONAssert.assertEquals(expectedJson, responseJson, false);
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail("Error comparing JSON response: " + e.getMessage());
        }
    }

    @Test
    public void testGetStocksByInvalidId() throws Exception {
        when(productService.getProductById(Mockito.anyString()))
                .thenThrow(new ProductNotFoundException("Inventory stock not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory/invalidId"))
                .andExpect(MockMvcResultMatchers.status().isConflict()) // Update to expect 409 status code
                .andExpect(MockMvcResultMatchers.content().string("Inventory stock not found"));
    }

    @Test
    public void testDeleteStocksById() throws Exception {
        // Create a new Product object
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Item 1");

        // Mock the productService's deleteProductById method
        Mockito.when(productService.deleteProductById(Mockito.eq("1")))
                .thenReturn(product);

        // Perform the HTTP DELETE request
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/inventory/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Matchers.is("Item 1")));
    }

    @Test
    public void testDeleteStocksByInvalidId() throws Exception {
        when(productService.deleteProductById(Mockito.anyString()))
                .thenThrow(new ProductNotFoundException("Inventory stock not found"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/inventory/invalidId"))
                .andExpect(MockMvcResultMatchers.status().isConflict()) // Update to expect 409 status code
                .andExpect(MockMvcResultMatchers.content().string("Inventory stock not found"));
    }

    @Test
    public void testUpdateStocksById() throws Exception {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Updated Item");

        when(productService.updateProductById(Mockito.eq("1"), Mockito.any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/inventory/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.stockId", Matchers.is("1")))
                .andExpect(jsonPath("$.stockName", Matchers.is("Updated Item")));
    }

    @Test
    public void testUpdateStocksByInvalidId() throws Exception {
        Product product = new Product();
        product.setProductName("Updated Item");

        when(productService.updateProductById(Mockito.anyString(), Mockito.any(Product.class)))
                .thenThrow(new ProductNotFoundException("Inventory stock not found"));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/inventory/invalidId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isConflict()) // Update to expect 409 status code
                .andExpect(MockMvcResultMatchers.content().string("Inventory stock not found"));
    }

    // Helper method to convert an object to JSON string
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}