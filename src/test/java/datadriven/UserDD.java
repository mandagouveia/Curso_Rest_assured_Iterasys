package datadriven;

import org.json.JSONObject;
import org.testng.annotations.*;
import utils.Data;
import utils.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UserDD {
    String uri = "https://petstore.swagger.io/v2/user";
    Data data; //objeto que representa a classe utils.Data
    Log log;
    int contador;
    double soma;

    @DataProvider
    public Iterator<Object[]> provider() throws IOException {
        List<Object[]> testCases = new ArrayList<>();
        String[] testCase;
        String linha;
        BufferedReader bufferReader = new BufferedReader(new FileReader("db/users.csv"));
        while ((linha = bufferReader.readLine()) != null){
            testCase = linha.split(",");
            testCases.add(testCase);

        }

        return testCases.iterator();


    }

    // 3.2 - Métodos e Funções
    @BeforeClass
    public void setup(){
        data = new Data();
        log = new Log();
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Total de registros :"+ contador);
        System.out.println("Soma tota é: "+soma);

    }

    @Test (dataProvider = "provider")
    public void incluirUsuario(
            String id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            String userStatus) throws IOException {

        log.iniciarLog();

        String jsonBody = new JSONObject()

                .put("id", id)
                .put("username", username)
                .put("firstName", firstName)
                .put("lastName", lastName)
                .put("email", email)
                .put("password", password)
                .put("phone", phone)
                .put("userStatus", userStatus)
                .toString();


        String userId =

                given()
                        .contentType("application/json")
                        .log().all()
                        .body(jsonBody)

                .when()
                        .post(uri)
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("code", is(200))
                        .body("type", is("unknown"))
                .extract()
                        .path("message");

        contador += 1;
        System.out.println("o userID é " + userId);
        System.out.println("Esta é a linha de numero "+contador);

        soma = soma + Double.parseDouble(password);


    }


}


