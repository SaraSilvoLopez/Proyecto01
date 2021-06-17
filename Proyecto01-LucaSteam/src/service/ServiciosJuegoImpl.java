package service;

import dao.CatalogoJuegos;
import dao.CatalogoJuegosImpl;
import exception.JuegoException;
import model.Juego;

/**
 * @ClassName ServiciosJuego Implementaci�n de la Interface ServiciosJuego
 *
 * @author Mar�a Castro, Patricia Garc�a, Usoa Larrarte, Jennifer P�rez y Sara
 *         Silvo
 *
 * @date 15 jun. 2021
 * 
 * @version 1.0
 */
public class ServiciosJuegoImpl implements ServiciosJuego {

	/**
	 * Instancia catalogo
	 */
	private CatalogoJuegos catalogo = new CatalogoJuegosImpl();

	/**
	 * M�todo getter del atributo catalogo
	 *
	 * @return catalogo
	 */
	public CatalogoJuegos getCatalogo() {
		return catalogo;
	}

	/**
	 * M�todo setter del atributo catalogo
	 *
	 * @param catalogo to set catalogo
	 */
	public void setCatalogo(CatalogoJuegos catalogo) {
		this.catalogo = catalogo;
	}

	/**
	 * Implementaci�n del m�todo altaJuego
	 *
	 * @return valor booleano
	 * @throws JuegoException
	 */
	public boolean altaJuego() throws JuegoException {
		Juego juego = new Juego();
		juego.crearJuego();
		return this.altaJuego(juego);
	}

	/**
	 * Sobrescritura del m�todo altaJuego
	 *
	 * @param juego
	 * @return valor booleano
	 * @throws JuegoException
	 */
	public boolean altaJuego(Juego juego) throws JuegoException {
		return catalogo.altaJuego(catalogo.siguienteId(), juego);
	}

	/**
	 * Implementaci�n del m�todo listarJuegos
	 * 
	 * @throws JuegoException
	 *
	 */
	public void listarJuegos() throws JuegoException {
		catalogo.listarJuegos();
	}

	/**
	 * Implementaci�n del m�todo listarJuegosSigloXX
	 * 
	 * @throws JuegoException
	 *
	 */
	public void listarJuegosSigloXX() throws JuegoException {
		catalogo.listarJuegosSigloXX();
	}

	/**
	 * Implementaci�n del m�todo leerDatosFichero
	 * 
	 */
	public void leerDatosFichero() {
		catalogo.leerDatosFichero();
	}

	/**
	 * Implementaci�n del m�todo serializarCatalogoJuegosImpl
	 *
	 */
	public void serializarCatalogoJuegosImpl() {
		catalogo.serializarCatalogoJuegosImpl("CatalogoJuegos.dat");

	}

	/**
	 * Implementaci�n del m�todo deSerializarCatalogoJuegosImpl
	 *
	 */
	public void deSerializarCatalogoJuegosImpl() {
		catalogo.deSerializarCatalogoJuegosImpl("CatalogoJuegos.dat");

	}

	/**
	 * Implementaci�n del m�todo listarJuegosGeneroPlataforma
	 *
	 * @throws JuegoException
	 */
	public void listarJuegosGeneroPlataforma() throws JuegoException {
		catalogo.listarJuegosGeneroPlataforma();
	}

}
