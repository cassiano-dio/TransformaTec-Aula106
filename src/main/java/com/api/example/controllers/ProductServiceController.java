package com.api.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.example.models.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Endpoints da API REST para manipular a classe Product
 * 
 * 1 - POST: criar um novo produto
 * 2 - GET: buscar um produto
 * 3 - GET: listar produtos
 * 4 - PUT: atualizar um produto
 * 5 - DELETE: remover um produto
 */

@RestController
@Api(value = "API para manutenção de produtos")
@CrossOrigin(value = "*")
public class ProductServiceController {

    private static List<Product> products = new ArrayList<Product>();

    static {

        products.add(new Product(0, "Shampoo Canino", 35.00, "Shampoo para cachorro gourmet"));
        products.add(new Product(1, "Shampoo Felino", 38.00, "Shampoo para gato gourmet"));
        products.add(new Product(2, "Bolinha de borracha", 15.00, "Brinquedo para cachorro"));
        products.add(new Product(3, "Remédio Canino", 30.00, "Remédio para cachorro nutella"));
        products.add(new Product(4, "Roupinha de cachorro", 105.00, "Roupinha de cachorro friorento"));

    }

    //Adicionar um novo produto
    @ApiOperation(value = "Adicionar um novo produto")
    @RequestMapping(value = "/new/products", method = RequestMethod.POST )
    public ResponseEntity<Object> createProduct(@RequestBody Product product){

        products.add(product);
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.CREATED);

    }

    //Buscar um produto por id
    @ApiOperation(value = "Buscar um produto")
    @RequestMapping(value = "/get/products/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") int id){
        
        return new ResponseEntity<>(products.get(id), HttpStatus.OK);
    }

    //Listar todos os produtos
    @ApiOperation(value = "Listar todos os produtos")
    @RequestMapping(value = "/all/products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts(){

        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    // Atualizando um produto por Id
    @ApiOperation(value = "Atualizar um produto por Id")
    @RequestMapping(value = "/update/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {

        products.remove(id);
        product.setId(id);
        products.add(product);

        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);

    }

    // Removendo um produto por Id
    @ApiOperation(value = "Remover um produto por Id")
    @RequestMapping(value = "/remove/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {

        products.remove(id);
        return new ResponseEntity<>("Produto removido com sucesso!", HttpStatus.OK);

    }

}
