package casoschecker;

import com.github.LuizCzaikowski.APICovid19.CuritibaCovid19;
import com.github.LuizCzaikowski.APICovid19.PessoasRep;
import com.github.LuizCzaikowski.APICovid19.PessoasRepDB;
import java.io.IOException;

/**
 *
 * @author Saulo Jr
 */
public class CasosChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        PessoasRepDB banco = new PessoasRepDB();
        CuritibaCovid19 obj = new CuritibaCovid19();
        PessoasRep pessoa = new PessoasRep();

        pessoa = obj.run();
        banco.Insert(pessoa);

        banco.DezDesc();
        banco.Media();

    }
}
