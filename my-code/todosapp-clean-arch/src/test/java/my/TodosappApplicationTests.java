package my;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import my.entities.Transaction;

@SpringBootTest
class TodosappApplicationTests {

    // test("Should create a transaction", async () => {
    // const code = uuidv4()
    // const requestBody = { code, value: 1000, numberInstallments: 12,
    // paymentMethod: "credit_card" }
    // const createResponse = await axios.post(`${BASE_URL}/transactions`,
    // requestBody)
    //
    // expect(createResponse.status).toBe(201)
    //
    // const getResponse = await axios.get(`${BASE_URL}/transactions/${code}`)
    //
    // expect(getResponse.status).toBe(200)
    //
    // const transaction = getResponse.data
    //
    // expect(transaction.value).toBe(1000)
    // expect(transaction.numberInstallments).toBe(12)
    // expect(transaction.paymentMethod).toBe("credit_card")
    //
    // expect(transaction.installments).toHaveLength(12)
    // const first = transaction.installments.find((x: any) => x.number == 1)
    // expect(first?.value).toBe(83.33)
    // const last = transaction.installments.find((x: any) => x.number == 12)
    // expect(last?.value).toBe(83.37)
    // })

    // given, when, then
    // arrange, act, assert
    @Test
    void ShouldCreateATransaction() {
        final var transactionCode = UUID.randomUUID().toString();

        final var requestBody = new HashMap<String, Object>();
        requestBody.put("code", transactionCode);
        requestBody.put("value", 1000);
        requestBody.put("numberOfIntallments", 12);
        requestBody.put("paymentMethod", "credit_card");

        final var reqHeaders = new HttpHeaders();
        reqHeaders.add("Content-Type", "application/json");

        final var request = new HttpEntity<>(requestBody, reqHeaders);

        final var response = new RestTemplate().postForEntity("http://localhost:5000/api/transactions", request,
                String.class);
        assertEquals(201, response.getStatusCode().value());

        final var reqHeaders2 = new HttpHeaders();
        reqHeaders2.add("Content-Type", "application/json");
        final var response2 = new RestTemplate()
                .getForEntity("http://localhost:5000/api/transactions/" + transactionCode, Transaction.class);
        assertEquals(200, response2.getStatusCode().value());

        final var transaction = response2.getBody();
        assertEquals(transactionCode, transaction.getCode());
        assertEquals(1000, transaction.getValue());
        assertEquals(12, transaction.getNumberOfInstallments());
        assertEquals("credit_card", transaction.getPaymentMethod());

        // TODO: make installments part first to be 83.33 and last to be 83.37
    }
}
