package tiendaspring.controller;

import tiendaspring.model.Producto;
import tiendaspring.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	private final ProductoService productoService;
	
	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}
	
	@GetMapping
	public List<Producto> obtenerTodos() {
		return productoService.obtenerTodos();
	}
	
	@PostMapping
	public void agregarProducto(@RequestBody Producto producto) {
		productoService.agregarProducto(producto);
	}
	
	@PutMapping
	public void actualizarProducto(@RequestBody Producto producto) {
		productoService.actualizarProducto(producto);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarProducto(@PathVariable int id) {
		productoService.eliminarProducto(id);
	}
}
