import com.seuprojeto.crypto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoTest {

    @Test
    public void testEncryptDecrypt() {
        String senhaOriginal = "MinhaSenhaSuperSecreta123!";
        String senhaCriptografada = crypto.encrypt(senhaOriginal);
        String senhaDescriptografada = crypto.decrypt(senhaCriptografada);

        assertEquals(senhaOriginal, senhaDescriptografada);
    }
}