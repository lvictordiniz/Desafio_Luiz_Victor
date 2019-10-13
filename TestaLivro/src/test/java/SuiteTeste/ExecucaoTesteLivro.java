/**
 * Suite de teste para o pacote web Livro
 * @author Larissa Silva <larissa.natali00@gmail.com>
 */
package SuiteTeste;
import org.junit.Test;
import com.submarino.livro.TestaLivro;


public class ExecucaoTesteLivro extends TestaLivro {
	
	TestaLivro orquestrador;
	
	@Test
	public void teste1() throws Throwable
	{
		SiteSubmarino();
		SiteAmericanas();
		SiteAmazon();
	}	
	
}
