import com.seuprojeto.cadastro_senhas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeradorSenhaTest {

    @Test
    public void testGerarSenha() {
        String senha = cadastro_senhas.gerarSenha(16);
        assertNotNull(senha);
        assertEquals(16, senha.length());
    }
}