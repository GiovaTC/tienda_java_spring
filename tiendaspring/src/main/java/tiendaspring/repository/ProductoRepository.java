package tiendaspring.repository;

import tiendaspring.model.Producto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductoRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public ProductoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Producto> getAllProductos() {
		String sql = "SELECT * FROM productos";
		return jdbcTemplate.query(sql, new ProductoRowMapper());
	}
	
	public void addProducto(Producto producto) {
		String sql = "INSERT INTO productos (nombre, tipo, fecha_fabricacion, fecha_vencimiento, imagen) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, producto.getNombre(), producto.getTipo(), producto.getFechaFabricacion(), producto.getFechaVencimiento(), 
		producto.getImagen());
	}
	
	public void updateProducto(Producto producto) {
		String sql = "UPDATE productos SET nombre=?, tipo=?, fecha_fabricacion=?, fecha_vencimiento=?, imagen=? WHERE id=?";
		jdbcTemplate.update(sql, producto.getNombre(), producto.getTipo(), producto.getFechaFabricacion(), producto.getFechaVencimiento(),
		producto.getImagen());
	}
	
	public void deleteProducto(int id) {
		String sql = "DELETE FROM productos WHERE id=?";
		jdbcTemplate.update(sql, id);
	}
	
	private static class ProductoRowMapper implements RowMapper<Producto> {
		@Override
		public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Producto(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("tipo"),
					rs.getString("fecha_fabricacion"),
					rs.getString("fecha_vencimiento"),
					rs.getString("imagen")
			);
		}
	}

}
