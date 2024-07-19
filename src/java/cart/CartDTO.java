/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.util.List;
import product.ProductDTO;

/**
 *
 * @author patho
 */
public class CartDTO {
    private List<ProductDTO> products;

    public CartDTO() {
    }

    public CartDTO(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
    
}
