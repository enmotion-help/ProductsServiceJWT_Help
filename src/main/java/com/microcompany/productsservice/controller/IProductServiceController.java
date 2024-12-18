package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequestMapping(value = "/default", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Validated
//@CrossOrigin(origins = {"http://localhost:8080/"})
@Tag(name = "Endpoints de productos", description = "Usa estos endpoints para crear y consumir productos")
public interface IProductServiceController {

    @GetMapping("")
//    @PreAuthorize("authentication.principal.username == 'user@email.com")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @Secured("ADMIN")
//    @PostAuthorize("returnObject.size() > 0")
    public ResponseEntity<List<Product>> getAll(@RequestParam(required = false) String text);


    @Operation(summary = "Obtener un producto", description = "Endpoint para obtener un producto concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo bien"),
            @ApiResponse(responseCode = "404", description = "El producto no existe")
    })
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public ResponseEntity<Product> getOne(
            @Parameter(name = "pid", description = "El identificador del producto", required = true, example = "234")
            @Min(1) @PathVariable("pid") Long id
    );

    @Operation(summary = "Crear un producto", description = "Endpoint para crear un producto concreto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Todo bien"),
            @ApiResponse(responseCode = "412", description = "El producto enviado no es correcto")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Product> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "El producto a crear sin Id.")
            @Valid @RequestBody
            Product product
    );

    @RequestMapping(value = "/{pid}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable("pid") Long id, @RequestBody Product aProduct);

    @RequestMapping(value = "/{pid}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("pid") Long id);


}