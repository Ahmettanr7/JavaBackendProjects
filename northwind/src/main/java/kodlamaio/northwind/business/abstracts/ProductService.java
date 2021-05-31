package kodlamaio.northwind.business.abstracts;

import java.util.List;

import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductService {
		DataResult<List<Product>> getAll();	
		DataResult<List<Product>> getAllAsc(int pageSize);
		DataResult<List<Product>> getAllDesc(int pageSize);
		DataResult<List<Product>> getAllSorted();
		
		Result add(Product product);
		
		DataResult<Product> getByProductName(String productName);
		
		DataResult<Product> getByProductNameAndCategory_CategoryId(String ProductName, int category);
		
		DataResult<List<Product>> getByProductNameOrCategory_CategoryId(String productName, int category);

		DataResult<List<Product>> getByCategoryIn(List<Integer> categories);
		
		DataResult<List<Product>> getByProductNameContains(String productName);
		
		DataResult<List<Product>> getByProductNameStartsWith(String productName);
		
		DataResult<List<Product>> getByNameAndCategory(String productName, int category);

		DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();
}
