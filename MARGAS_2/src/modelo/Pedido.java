package modelo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	private String dni;
	private Date fecha;
	private Time hora;
	private String estado;
	private ArrayList<LineaDePedido> lineas;
	
	public Pedido(String dni) {
		this.dni = dni;
		this.lineas = new ArrayList<>();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public ArrayList<LineaDePedido> getLineas(){
		return this.lineas;
	}

	public void agregarLinea(LineaDePedido ldp) {
		boolean producto_incluido = false;
		for (LineaDePedido lineaDePedido : lineas) {
			producto_incluido = lineaDePedido.isFor(ldp.getTipoGarrafa());
			if (producto_incluido) {
				lineaDePedido.setCantidad(ldp.getCantidad());
				break;
			}
		}
		if (!producto_incluido) {
			this.lineas.add(ldp);
		}
	}
	
	public double getTotal() {
		double total = 0.0;
		for (LineaDePedido lineaDePedido : lineas) {
			total += lineaDePedido.getImporte();
		}
		return total;
	}

	public void eliminarLinea(int id_producto) {
		for (LineaDePedido lineaDePedido : lineas) {
			if (lineaDePedido.getTipoGarrafa().getId() == id_producto) {
				lineas.remove(lineaDePedido);
				break;
			}
		}
	}
}
