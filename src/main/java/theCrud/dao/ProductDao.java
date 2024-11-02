package theCrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import theCrud.model.Product;

@Component
public class ProductDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//Create
	@Transactional
	public void createProduct(Product product) {
		this.hibernateTemplate.saveOrUpdate(product);
	}
	//get All products
	public List<Product> getProducts(){
	List<Product> products=	this.hibernateTemplate.loadAll(Product.class);
	return products;
	
	}
	
	//delete
	@Transactional
	public void deleteProduct(int pid) {
		Product product=this.hibernateTemplate.load(Product.class, pid);
		this.hibernateTemplate.delete(product);
	}
//get product
	
	public Product getProduct(int pid) {
		return this.hibernateTemplate.get(Product.class, pid);
	}
}
