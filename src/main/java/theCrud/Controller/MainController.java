package theCrud.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import theCrud.dao.ProductDao;
import theCrud.model.Product;

@Controller
public class MainController {
	@Autowired
	private ProductDao productDao;
	@RequestMapping("/")
	public String home(Model model) {
		List<Product> products=productDao.getProducts();
		model.addAttribute("products",products);
		return "index";
	}

	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add product");
		return "add_product_form";
		
	}
	
	//handle add product form
	@RequestMapping(value="/handle-product", method=RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request) {
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
		
		
	}
	
	//delete
	@RequestMapping("/delete/{pid}")
	public RedirectView deleteProduct(@PathVariable("pid")int pid, HttpServletRequest request) {
		this.productDao.deleteProduct(pid);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	
	//update
	@RequestMapping("/update/{pid}")
	public String updateForm(@PathVariable("pid") int pid,Model model) {
	    Product product=this.productDao.getProduct(pid);
		model.addAttribute("product",product);
		return "update-form";
	}
}
