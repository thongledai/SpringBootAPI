package vn.iotstar.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.iotstar.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	// Tìm kiếm theo nội dung tên
	List<Product> findByProductNameContaining(String name);

	// Tìm kiếm và phân trang
	Page<Product> findByProductNameContaining(String name, Pageable pageable);

	// Tìm theo tên chính xác (dùng để check trùng khi addProduct)
	Optional<Product> findByProductName(String name);

	// Tìm theo ngày tạo (dùng để lấy lại product vừa insert trong bản gốc,
	// hiện ProductAPIController mình viết không cần dùng cái này nữa
	// vì đã trả về trực tiếp entity vừa save())
	Optional<Product> findByCreateDate(Date createAt);
}