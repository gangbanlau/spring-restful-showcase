package my.mycompany.myapp.web;

import java.util.List;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.service.IProductsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductsService productsService;
	
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	public @ResponseBody List<Product> getAll(){
		return productsService.findAllProducts();
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
	public @ResponseBody Product getOne(@PathVariable long id){
		return productsService.findOneProduct(id);
	}
	
	@RequestMapping(value = "/products",method = RequestMethod.POST)
	public void add(@RequestBody Product prod){
		productsService.insertProduct(prod);
		System.out.println(prod.getId());
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
	public void delete(@PathVariable long id){
		productsService.deleteProduct(id);
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.PUT)
	public void update(@PathVariable long id,@RequestBody Product pro){
		pro.setId(id);
		productsService.updateProduct(pro);
	}
}
