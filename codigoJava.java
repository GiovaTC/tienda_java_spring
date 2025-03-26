package com.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class InventarioApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tienda");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }
}

package com.tienda.model;

public class Producto {
    private int id;
    private String nombre;
    private String tipo;
    private String fechaFabricacion;
    private String fechaVencimiento;
    private String imagen;

    public Producto() {}

    public Producto(int id, String nombre, String tipo, String fechaFabricacion, String fechaVencimiento, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaFabricacion = fechaFabricacion;
        this.fechaVencimiento = fechaVencimiento;
        this.imagen = imagen;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getFechaFabricacion() { return fechaFabricacion; }
    public void setFechaFabricacion(String fechaFabricacion) { this.fechaFabricacion = fechaFabricacion; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}

package com.tienda.repository;

import com.tienda.model.Producto;
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
        String sql = "INSERT INTO productos (nombre, tipo, fecha_fabricacion, fecha_vencimiento, imagen) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, producto.getNombre(), producto.getTipo(), producto.getFechaFabricacion(), producto.getFechaVencimiento(), producto.getImagen());
    }

    public void updateProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, tipo=?, fecha_fabricacion=?, fecha_vencimiento=?, imagen=? WHERE id=?";
        jdbcTemplate.update(sql, producto.getNombre(), producto.getTipo(), producto.getFechaFabricacion(), producto.getFechaVencimiento(), producto.getImagen(), producto.getId());
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

package com.tienda.service;

import com.tienda.model.Producto;
import com.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

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

package com.tienda.controller;

import com.tienda.model.Producto;
import com.tienda.service.ProductoService;
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
