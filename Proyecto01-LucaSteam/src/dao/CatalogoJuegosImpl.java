package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Genero;
import model.Juego;
import utilities.LeerFichero;
import exception.JuegoException;

/**
 * @ClassName CatalogoJuegosImpl Implementaci�n de la Interface CatalogoJuegos
 * 
 * @author Mar�a Castro, Patricia Garc�a, Usoa Larrarte, Jennifer P�rez y Sara
 *         Silvo
 *
 * @date 15 jun. 2021
 * 
 * @version 1.0
 */
public class CatalogoJuegosImpl implements CatalogoJuegos {

	/**
	 * Atributo catalogo
	 * 
	 */
	private Map<Integer, Juego> catalogo;

	/**
	 * Constructor de la clase CatalogoJuegos
	 *
	 */
	public CatalogoJuegosImpl() {
		catalogo = new HashMap<>();
	}

	/**
	 * M�todo getter del atributo catalogo
	 *
	 * @return catalogo
	 */
	public Map<Integer, Juego> getCatalogo() {
		return catalogo;
	}

	/**
	 * M�todo setter del atributo catalogo
	 *
	 * @param catalogo to set catalogo
	 */
	public void setCatalogo(Map<Integer, Juego> catalogo) {
		this.catalogo = catalogo;
	}

	/**
	 * Implementaci�n del m�todo altaJuego
	 * 
	 * Da de alta un juego si su ID no est� repetido en el cat�logo Si el ID existe
	 * en el catalogo, lanza una excepci�n
	 *
	 * @param id
	 * @param juego
	 * @return boolean
	 * @throws JuegoException
	 */
	public boolean altaJuego(Integer id, Juego juego) throws JuegoException {
		if (catalogo.containsKey(id)) {
			throw new JuegoException("No se puede dar de alta el juego. El ID est� repetido. ", 2);
		} else {
			catalogo.put(id, juego);
			return true;
		}
	}

	/**
	 * Implementaci�n del m�todo listarJuegos Lista los juegos del catalogo
	 * 
	 * @throws JuegoException
	 */
	public void listarJuegos() throws JuegoException {
		if (catalogo.size() == 0) {
			throw new JuegoException("El cat�logo no tiene registros. ", 3);
		} else {
			Integer key;
			Iterator<Integer> juegos = catalogo.keySet().iterator();
			System.out.println(" Listado de juegos en Cat�logo ");
			while (juegos.hasNext()) {
				key = juegos.next();
				System.out.println("(" + key + "): " + catalogo.get(key));
			}
		}
	}

	/**
	 * Implementaci�n del m�todo siguienteId
	 * 
	 * Busca el �ltimo ID existente en el cat�logo y devuelve el entero siguiente
	 *
	 * @return maxID+1
	 */
	public Integer siguienteId() {
		Integer maxId;
		if (catalogo.size() == 0) {
			maxId = 0;
		} else {
			maxId = catalogo.entrySet().stream().max((a, b) -> Integer.compare(a.getKey(), b.getKey()))
					.map(e -> e.getKey()).get();
		}
		return maxId + 1;
	}

	/**
	 * Implementaci�n del m�todo leerDatosFichero Lee los datos del fichero CSV y
	 * guarda en catalogo.
	 *
	 */
	public void leerDatosFichero() {
		System.out.println("-- Leyendo Fichero");
		try {
			catalogo = LeerFichero.leerDatosFichero();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Implementaci�n del m�todo serializarCatalogoJuegosImpl
	 *
	 * @param fichero
	 */
	public void serializarCatalogoJuegosImpl(String fichero) {

		System.out.println("-- Serializando");
		try {
			FileOutputStream f = new FileOutputStream(fichero);
			try (ObjectOutputStream s = new ObjectOutputStream(f)) {
				s.writeObject(catalogo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implementaci�n del m�todo deSerializarCatalogoJuegosImpl
	 *
	 * @param fichero
	 */
	public void deSerializarCatalogoJuegosImpl(String fichero) {
		System.out.println("-- Deserializando");
		try {
			File fich = new File(fichero);
			FileInputStream f = new FileInputStream(fich);
			try (ObjectInputStream s = new ObjectInputStream(f)) {
				catalogo = (Map<Integer, Juego>) s.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(CatalogoJuegosImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Implementaci�n del m�todo listarJuegosSigloXX Muestra los juegos del catalogo
	 * del siglo XX (1901-2000)
	 * 
	 */
	public void listarJuegosSigloXX() throws JuegoException {
		if (catalogo.size() == 0) {
			throw new JuegoException("El cat�logo no tiene registros. ", 3);
		} else {
			Integer key;
			int count = 0;
			Iterator<Integer> juegos = catalogo.keySet().iterator();
			while (juegos.hasNext()) {
				key = juegos.next();
				if ((1900 < catalogo.get(key).getFecha()) && (catalogo.get(key).getFecha() <= 2000)) {
					System.out.println(" [" + key + "] - " + catalogo.get(key).getNombre() + " --> A�o: "
							+ catalogo.get(key).getFecha());
					count++;
				}
			}
			if (count == 0) {
				throw new JuegoException("No hay ning�n juego del Siglo XX", 3);
			}
		}
	}

	/**
	 * Implementaci�n del m�todo listarJuegosGeneroPlataforma
	 * 
	 * Filtra e imprime los juegos por genero = PLATFORM del cat�logo de juegos
	 *
	 * @throws JuegoException
	 * 
	 *                        Genera excepciones en caso de que el catalogo est�
	 *                        vac�o o no se haya encontrado con ning�n juego con
	 *                        genero = PLATFORM
	 */
	public void listarJuegosGeneroPlataforma() throws JuegoException {
		if (catalogo.size() == 0) {
			throw new JuegoException("El catalogo no tiene registros", 3);
		} else {
			catalogo.entrySet().stream().filter(a -> a.getValue().getGenero() == Genero.PLATFORM)
					.forEach(j -> System.out.println("(" + j.getKey() + "): " + j.getValue()));
			long count = catalogo.entrySet().stream().filter(a -> a.getValue().getGenero() == Genero.PLATFORM).count();
			if (count == 0) {
				throw new JuegoException("No hay ning�n juego de g�nero PLATFORM", 3);
			}

		}
	}

}
