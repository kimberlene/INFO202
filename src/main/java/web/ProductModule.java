package web;

import dao.ProductManager;
import org.jooby.Jooby;

/**
 *
 * @author shaki694
 */
public class ProductModule extends Jooby {
	
	public ProductModule(ProductManager dao) {
		get("/api/products", () -> dao.displayProducts());
		get("/api/products/:id", (req) -> {
			String id = req.param("id").value();
			return dao.searchProduct(id);
		});
		get("/api/categories", () -> dao.getCategories());
		get("/api/categories/:category", (req) -> {
			String cat = req.param("category").value();
			return dao.filterCategory(cat);
		});
	}
	
}
