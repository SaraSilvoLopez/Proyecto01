package test;
import utilities.LeerFichero;
import model.Juego;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import model.Plataforma;
import model.Genero;

	/**
	 * @ClassName TestLeerFichero
	 *
	 * @author Jennifer P�rez
	 *
	 * @date 16 jun. 2021
	 * 
	 * @version 1.0
	 * 
	                                           
	 */

public class TestLeerFichero {
	static Logger logger = LogManager.getLogger(TestLeerFichero.class);
     @Test  
    public void testArrayOk() {
    	logger.info("ejecutando testArrayOk()");

    	Juego altaJuegoValorEsperado = new Juego ("JuegoPrueba", 1996, "Wii", Genero.ROLE_PLAYING, Plataforma.PSP, 2.0);
		String [] prueba = new String [] {"1", "JuegoPrueba","PSP", "1996", "Role-Playing", "Wii", "2.0"};
    	Juego altaJuego = LeerFichero.arrayToJuegos(prueba);
		assertEquals(altaJuego, altaJuegoValorEsperado);          
    }
}  

