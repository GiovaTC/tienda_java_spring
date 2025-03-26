package service;

import tiendaspring.model.Producto;
import tiendaspring.repository.ProductoRepository;
import tiendaspring.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
 private final ProductoRepository productoRepository;
 
 public ProductoService(ProductoRepository productoRepository) {
	 this.productoRepository = productoRepository;
 }
 
 public List<Producto> obtenerTodos() {
	 return productoRepository.getAllProductos();
 }
 
 public void agregarProducto(Producto producto) {
	 productoRepository.addProducto(producto);
 }
 
 public void actualizarProducto(Producto producto) {
	 productoRepository.updateProducto(producto);
 }
 
 public void eliminarProducto(int id) {
	 productoRepository.deleteProducto(id);
 }
}
