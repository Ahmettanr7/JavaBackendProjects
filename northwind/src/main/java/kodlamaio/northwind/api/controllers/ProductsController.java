package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController
@RequestMapping("/api/products/")
@CrossOrigin
public class ProductsController {
	
	private ProductService productService;
	
	
	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}



	@GetMapping("getall")
	public DataResult<List<Product>> getAll(){
		return this.productService.getAll();
	}
	
	@GetMapping("getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
		return this.productService.getProductWithCategoryDetails();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody Product product) {
		return this.productService.add(product);
	}
	
	@GetMapping("getbyproductname")
	public DataResult<Product> getByProductName(@RequestParam String productName){
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("getbyproductnameandcategory")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam String ProductName,@RequestParam int category){
		return this.productService.getByProductNameAndCategory_CategoryId(ProductName, category);
	}
	
	@GetMapping("getbyproductnameorcategory")
	public DataResult<List<Product>> getByProductNameOrCategory(@RequestParam String productName,@RequestParam int category){
		return this.productService.getByProductNameOrCategory_CategoryId( productName, category);
	}
	
	@GetMapping("getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("getAllByAsc")
		DataResult<List<Product>> getAllAsc(int pageSize){
		return this.productService.getAllAsc(pageSize);
	}
	
	@GetMapping("getAllByDesc")
	DataResult<List<Product>> getAllDesc(int pageSize){
	return this.productService.getAllDesc(pageSize);
}
	
	@GetMapping("getAllDesc")
	public DataResult<List<Product>> getAllSorted(){
		return this.productService.getAllSorted();
	}
	}
