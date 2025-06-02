import com.seuprojeto.Crypto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CryptoTest {

    @Test
    public void testEncryptDecrypt() {
        String senhaOriginal = "MinhaSenhaSuperSecreta123!";
        String senhaCriptografada = Crypto.encrypt(senhaOriginal);
        String senhaDescriptografada = Crypto.decrypt(senhaCriptografada);

        assertEquals(senhaOriginal, senhaDescriptografada);
    }
}