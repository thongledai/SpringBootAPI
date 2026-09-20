package vn.iotstar.Controller.api;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.model.ProductModel;
import vn.iotstar.model.Response;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.IStorageService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductAPIController {

	@Autowired
	IProductService productService;

	@Autowired
	ICategoryService categoryService;

	@Autowired
	IStorageService storageService;

	@GetMapping
	public ResponseEntity<?> getAllProduct() {
		return new ResponseEntity<Response>(new Response(true, "Thành công", productService.findAll()), HttpStatus.OK);
	}

	@PostMapping(path = "/getProduct")
	public ResponseEntity<?> getProduct(@Validated @RequestParam("id") Long id) {
		Optional<Product> product = productService.findById(id);
		if (product.isPresent()) {
			return new ResponseEntity<Response>(new Response(true, "Thành công", product.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response(false, "Không tìm thấy Product", null),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/addProduct")
	public ResponseEntity<?> saveOrUpdate(@Validated @RequestParam("productName") String productName,
			@RequestParam(value = "imageFile", required = false) MultipartFile productImages,
			@Validated @RequestParam("unitPrice") Double productPrice,
			@RequestParam(value = "discount", required = false) Double promotionalPrice,
			@RequestParam(value = "description", required = false) String productDescription,
			@Validated @RequestParam("categoryId") Long categoryId,
			@Validated @RequestParam("quantity") Integer quantity,
			@RequestParam(value = "status", required = false) Short status) {

		Optional<Product> optProduct = productService.findByProductName(productName);

		if (optProduct.isPresent()) {
			return new ResponseEntity<Response>(
					new Response(false, "Sản phẩm này đã tồn tại trong hệ thống", optProduct.get()),
					HttpStatus.BAD_REQUEST);
		} else {
			Product product = new Product();
			Timestamp timestamp = new Timestamp(new Date(System.currentTimeMillis()).getTime());

			try {
				ProductModel proModel = new ProductModel();
				proModel.setProductName(productName);
				proModel.setUnitPrice(productPrice);
				proModel.setDiscount(promotionalPrice != null ? promotionalPrice : 0.0);
				proModel.setDescription(productDescription != null ? productDescription : "");
				proModel.setCategoryId(categoryId);
				proModel.setQuantity(quantity);
				proModel.setStatus(status != null ? status : (short) 1);
				proModel.setImageFile(productImages);

				// copy từ Model sang Entity
				BeanUtils.copyProperties(proModel, product);

				// xử lý category liên quan product
				Optional<Category> optCategory = categoryService.findById(categoryId);
				if (optCategory.isPresent()) {
					product.setCategory(optCategory.get());
				}

				// kiểm tra tồn tại file, lưu file
				if (productImages != null && !productImages.isEmpty()) {
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();
					// lưu file vào trường Images
					product.setImages(storageService.getSorageFilename(productImages, uuString));
					storageService.store(productImages, product.getImages());
				}
				product.setCreateDate(timestamp);

				productService.save(product);

				optProduct = productService.findByCreateDate(timestamp);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ResponseEntity<Response>(
					new Response(true, "Thêm Thành công", optProduct.isPresent() ? optProduct.get() : product),
					HttpStatus.OK);
		}
	}

	@PostMapping(path = "/updateProduct")
	public ResponseEntity<?> updateProduct(@Validated @RequestParam("productId") Long productId,
			@Validated @RequestParam("productName") String productName,
			@RequestParam(value = "imageFile", required = false) MultipartFile productImages,
			@Validated @RequestParam("unitPrice") Double productPrice,
			@RequestParam(value = "discount", required = false) Double promotionalPrice,
			@RequestParam(value = "description", required = false) String productDescription,
			@Validated @RequestParam("categoryId") Long categoryId,
			@Validated @RequestParam("quantity") Integer quantity,
			@RequestParam(value = "status", required = false) Short status) {

		Optional<Product> optProduct = productService.findById(productId);
		if (optProduct.isEmpty()) {
			return new ResponseEntity<Response>(new Response(false, "Không tìm thấy Product", null),
					HttpStatus.BAD_REQUEST);
		}

		Optional<Category> optCategory = categoryService.findById(categoryId);
		if (optCategory.isEmpty()) {
			return new ResponseEntity<Response>(new Response(false, "Category không tồn tại", null),
					HttpStatus.BAD_REQUEST);
		}

		Product product = optProduct.get();
		product.setProductName(productName);
		product.setUnitPrice(productPrice);
		product.setDiscount(promotionalPrice != null ? promotionalPrice : 0.0);
		product.setDescription(productDescription != null ? productDescription : "");
		product.setCategory(optCategory.get());
		product.setQuantity(quantity);
		product.setStatus(status != null ? status : (short) 1);

		// kiểm tra tồn tại file, lưu file
		if (productImages != null && !productImages.isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			product.setImages(storageService.getSorageFilename(productImages, uuString));
			storageService.store(productImages, product.getImages());
		}

		Product saved = productService.save(product);
		return new ResponseEntity<Response>(new Response(true, "Cập nhật Thành công", saved), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteProduct")
	public ResponseEntity<?> deleteProduct(@Validated @RequestParam("productId") Long productId) {
		Optional<Product> optProduct = productService.findById(productId);
		if (optProduct.isEmpty()) {
			return new ResponseEntity<Response>(new Response(false, "Không tìm thấy Product", null),
					HttpStatus.BAD_REQUEST);
		} else if (optProduct.isPresent()) {
			productService.delete(optProduct.get());
			return new ResponseEntity<Response>(new Response(true, "Xóa Thành công", optProduct.get()), HttpStatus.OK);
		}
		return null;
	}

	// Endpoint trả ảnh product cho AJAX <img src="...">
	@GetMapping("/images/{filename:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
	}
}
