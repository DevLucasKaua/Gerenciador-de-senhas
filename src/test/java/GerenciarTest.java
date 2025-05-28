import com.seuprojeto.Credenciais;
import com.seuprojeto.Gerenciar;
import com.seuprojeto.crypto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GerenciarTest {

    @Test
    public void testSalvarEListarCredenciais() {
        Gerenciar gerenciar = new Gerenciar();
        Credenciais cred = new Credenciais("email@example.com", crypto.encrypt("senha123"));

        gerenciar.salvarCredenciais(cred);

        assertFalse(gerenciar.getCredenciais().isEmpty());
        assertEquals("email@example.com", gerenciar.getCredenciais().get(0).getEmail());
    }
}