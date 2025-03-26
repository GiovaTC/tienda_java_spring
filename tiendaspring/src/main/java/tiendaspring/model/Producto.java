package model;

public class Producto {
	private int id;
	private String nombre;
	private String tipo;
	private String fechaFabricacion;
	private String fechaVencimiento;
	private String imagen;
	
	public Producto () {}
	
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
