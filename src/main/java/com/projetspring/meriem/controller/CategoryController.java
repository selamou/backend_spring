package com.projetspring.meriem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetspring.meriem.dao.CategoryRepository;
import com.projetspring.meriem.dao.ClientRepository;
import com.projetspring.meriem.dao.OrderItemRepository;
import com.projetspring.meriem.dao.OrderRepository;
import com.projetspring.meriem.dao.ProductRepository;
import com.projetspring.meriem.entities.Category;
import com.projetspring.meriem.entities.Client;
import com.projetspring.meriem.entities.Order;
import com.projetspring.meriem.entities.OrderForm;
import com.projetspring.meriem.entities.OrderItem;
import com.projetspring.meriem.entities.OrderProduct;
import com.projetspring.meriem.entities.Product;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository CategoryRepository;
    @PostMapping("/catagory")
    public Category saveCategory (@RequestBody Category category){
        
        Category cg=new Category(); 
        cg.setName(category.getName());
        cg.setDescription(category.getDescription());
        return CategoryRepository.save(cg);
   
    }
    @GetMapping("/catagory")
    public ResponseEntity<List<Category>> getAllCommands() {
      try {
        List<Category> category = new ArrayList<Category>();
        CategoryRepository.findAll().forEach(category::add);
  
        if (category.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
  
        return new ResponseEntity<>(category, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @DeleteMapping("/catagory/{id}")
    public ResponseEntity<HttpStatus> deleteCommand(@PathVariable("id") long id) {
      try {
        CategoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
