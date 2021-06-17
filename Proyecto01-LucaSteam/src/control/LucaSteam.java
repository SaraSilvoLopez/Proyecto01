package control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.Menu;
import gui.MenuCargaCatalogo;
import service.ServiciosJuego;
import service.ServiciosJuegoImpl;
import utilities.Teclado;

/**
 * @ClassName LucaSteam
 *
 * @author Mar�a Castro
 *
 * @date 15 jun. 2021
 * 
 * @version 1.0
 */
public class LucaSteam {

	static Logger logger = LogManager.getLogger(LucaSteam.class);

	/**
	 * Instancia servicios
	 */
	private ServiciosJuego servicios = new ServiciosJuegoImpl();

	/**
	 * M�todo para inicializar el arranque de los men�s Primero el de inicializar
	 * cat�logo, despu�s men� principal
	 */
	public void startLucaSteam() {
		// Men� inicializar Cat�logo
		Integer opcion = 0;
		boolean continuaMenu1 = true;
		do {
			MenuCargaCatalogo.mostrarMenuCargaCatalogo();
			boolean opcionBool = true;
			do {
				try {
					opcion = Teclado.tecladoInt();
					if ((opcion == 1) || (opcion == 2) || (opcion == 3)) {
						opcionBool = false;
					} else {
						throw new Exception();
					}
				} catch (Exception e) {
					logger.error(e.toString());
				}
			} while (opcionBool);
			continuaMenu1 = this.opcionesMenuCarga(opcion);
		} while (continuaMenu1);

		// Men� principal
		boolean continuaMenu2 = true;
		do {
			Menu.mostrarMenu();
			continuaMenu2 = this.opcionesMenuPrincipal();
		} while (continuaMenu2);
		logger.info(" **FIN DE LA SESION** ");
	}

	/**
	 * M�todo que devuelve un booleano en funci�n del valor que el usuario introduce
	 * por teclado para Men� de Inicializar Cat�logo
	 * 
	 * @return boolean sigueMenu1
	 */
	public boolean opcionesMenuCarga(Integer opcion) {
		boolean sigueMenu1 = true;
		switch (opcion) {
		case 1:
			// CARGA DESDE BBDD
			servicios.deSerializarCatalogoJuegosImpl();
			logger.info("El cat�logo ha sido inicializado desde base de datos");
			sigueMenu1 = false;
			break;
		case 2:
			// CARGA DESDE FICHERO
			servicios.leerDatosFichero();
			logger.info("El cat�logo ha sido inicializado desde fichero");
			sigueMenu1 = false;
			break;
		case 3:
			// CAT�LOGO VAC�O
			logger.info("El cat�logo ha sido inicializado vac�o");
			sigueMenu1 = false;
			break;
		}
		return sigueMenu1;
	}

	/**
	 * M�todo que devuelve un booleano en funci�n del valor que el usuario introduce
	 * por teclado para Men� principal
	 * 
	 * @return sigueMenu
	 */
	public boolean opcionesMenuPrincipal() {
		boolean sigueMenu2 = true;
		try {
			switch (Teclado.tecladoInt()) {
			case 1:
				// ALTA DE UN JUEGO
				servicios.altaJuego();
				break;
			case 2:
				// LISTAR JUEGOS
				servicios.listarJuegos();
				break;
			case 3:
				// LISTAR JUEGOS G�NERO PLATAFORMA
				servicios.listarJuegosGeneroPlataforma();
				break;
			case 4:
				// LISTAR JUEGOS SIGLO XX
				servicios.listarJuegosSigloXX();
				break;
			case 5:
				//LISTAR JUEGOS CONSOLA NINTENDO
				servicios.listarJuegosConsolasNintendo();
				break;
			case 6:
				//LISTAR JUEGOS A�OS PARES
				servicios.listarJuegosAnnosPares();
				break;
			case 7:
				//LISTAR EDITORES
				servicios.listarEditores();
				break;
			case 0:
				sigueMenu2 = salir();
				break;
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return sigueMenu2;
	}

	/**
	 * M�todo que devuelve un booleano en funci�n del String introducido por teclado
	 * para salir del men� Permite la opci�n de guardar
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean salir() throws Exception {
		String salir = Teclado.tecladoString(" �Est� seguro?(S/N)");
		if (salir.toUpperCase().charAt(0) == 'S') {
			String guardar = Teclado.tecladoString(" �Desea guardar antes de salir?(S/N)");
			if (guardar.toUpperCase().charAt(0) == 'S') {
				try {
					servicios.serializarCatalogoJuegosImpl();
					logger.info("Los cambios han sido guardados");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
		return (salir.toUpperCase().charAt(0) != 'S');
	}

}
