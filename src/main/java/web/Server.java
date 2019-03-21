package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDatabaseDao;
import dao.DatabaseDao;
import dao.SaleJdbcDAO;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author shaki694
 */
public class Server extends Jooby {

	private DatabaseDao dao = new DatabaseDao();
	//private CustomerCollectionsDAO custDao = new CustomerCollectionsDAO();
        private CustomerDatabaseDao custDao = new CustomerDatabaseDao();
        private SaleJdbcDAO saleDao = new SaleJdbcDAO();
        

	public Server() {
		port(8007);
		/*get("/api/products", () -> dao.displayProducts());
		get("/api/products/:id", (req) -> {
			String id = req.param("id").value();
			return dao.searchProduct(id);
		});*/
		use(new Gzon());
		use(new ProductModule(dao));
		use(new CustomerModule(custDao));
		use(new SaleModule(saleDao));
                use(new AssetModule());
	}

	public static void main(String[] args) throws Exception {
		System.out.println("\nStarting Server.");
		Server server = new Server();
		CompletableFuture.runAsync(() -> {
			server.start();

		});
		server.onStarted(() -> {
			System.out.println("\nPress Enter to stop the server.");
		});
// wait for user to hit the Enter key
		System.in.read();
		System.exit(0);
	}
}
