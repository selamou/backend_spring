package com.projetspring.meriem.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projetspring.meriem.dao.ProductRepository;
import com.projetspring.meriem.entities.Product;import ch.qos.logback.core.Context;

@CrossOrigin("*")
@RestController
public class ImagesController {
	private ProductRepository productRepository;

    public ImagesController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
    	
        Product p=productRepository.findById(id).get();
       
        String pathStr = "C:/Users/pc/Desktop/frontend/assets/b/";
        Path path = Paths.get(pathStr);
        System.out.println(System.getProperty("user.home")+"/SpringAngAssets/products/"+p.getImgURL());
        return Files.readAllBytes(Paths.get(path+p.getImgURL()));
    }
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
       Product p=productRepository.findById(id).get();
       p.setImgURL(file.getOriginalFilename());
       
       String pathStr = "C:/Users/pc/Desktop/frontend/assets/b/";
       Path path = Paths.get(pathStr);
       Files.write(Paths.get(path+p.getImgURL()),file.getBytes());
       productRepository.save(p);
    }
}
