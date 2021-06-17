package test;

import static org.junit.Assert.assertThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import control.LucaSteam;
import utilities.Teclado;

/**
 * @ClassName TestTeclado
 *
 * @author Sara Silvo
 *
 * @date 17 jun. 2021
 * 
 * @version 1.0
 */
class TestTeclado {

	static Logger logger = LogManager.getLogger(TestTeclado.class);
	static LucaSteam lucaSteam;
	static Integer opcion = 0;

	@BeforeAll
	static void inicioTest() {
		logger.info("Inicio Test Unitario Excepciones");
	}

	@AfterAll
	static void finTest() {
		logger.info("Fin Test Unitarios Excepciones");
	}

	@BeforeEach
	void crearCatalogoServicios() {
		logger.info("Crear nuevo LucaSteam");
		lucaSteam = new LucaSteam();
	}

	@Test
	void testOpcionIntroducirPorTecladoInt() {
		logger.info("Ejecutando tecladoInt(). Introduce un numero entero. Si no es correcto, lanzar� una excepci�n");
		assertThrows(java.lang.Exception.class, () -> {
			Teclado.tecladoInt();
		});
	}

}
