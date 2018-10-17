package controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.PedidoDAO;
import modelo.TipoGarrafa;
import modelo.TipoGarrafaDAO;

public class ControladorPedido {
	private Pedido pedido;
	
	public Pedido getPedido(){
		return this.pedido;
	}
	
	private ArrayList<TipoGarrafa> getTiposGarrafas() throws ClassNotFoundException, SQLException {
		ArrayList<TipoGarrafa> lista_tipos_garrafa = TipoGarrafaDAO.GetAll();
		
		return lista_tipos_garrafa;
	}
	
	public ArrayList<TipoGarrafa> iniciarPedido(String dni) throws ClassNotFoundException, SQLException {
		this.pedido = new Pedido(dni);
		
		return this.getTiposGarrafas();
	}
	
	public void agregarProducto(int id_producto, int cantidad) throws ClassNotFoundException, SQLException {
		TipoGarrafa tipoGarrafa = TipoGarrafaDAO.GetOne(id_producto);
		if (tipoGarrafa != null) {
			LineaDePedido ldp = new LineaDePedido(tipoGarrafa, cantidad);
			this.pedido.agregarLinea(ldp);
		}
	}

	public void eliminarLinea(int id_producto) {
		this.pedido.eliminarLinea(id_producto);
	}

	public boolean enviar() throws ClassNotFoundException, SQLException {
		
		ArrayList<LineaDePedido> lineas = pedido.getLineas(); 
		
		if (!lineas.isEmpty()) {
			PedidoDAO.Save(this.pedido);
			
			TipoGarrafa tipoGarrafa = null;
			int cantidad = 0;
			for (LineaDePedido lineaDePedido : lineas) {
				tipoGarrafa = lineaDePedido.getTipoGarrafa();
				cantidad = lineaDePedido.getCantidad();
				tipoGarrafa.actualizarStock(-cantidad);
				
				TipoGarrafaDAO.UpdateStock(tipoGarrafa);
			}
		}
		
		return !lineas.isEmpty();
	}
}
