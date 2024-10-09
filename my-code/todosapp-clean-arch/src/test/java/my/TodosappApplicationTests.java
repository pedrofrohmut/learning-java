package my;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
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

    // given, when, then
    // arrange, act, assert
    @Test
    void ShouldCreateATransaction() {
        final var transactionCode = UUID.randomUUID().toString();

        final var requestBody = new HashMap<String, Object>();
        requestBody.put("code", transactionCode);
        requestBody.put("value", 1000);
        requestBody.put("numberOfInstallments", 12);
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

        // Check Transaction
        final var transaction = response2.getBody();
        assertEquals(transactionCode, transaction.getCode());
        assertEquals(new BigDecimal("1000"), transaction.getValue());
        assertEquals("credit_card", transaction.getPaymentMethod());
        assertEquals(12, transaction.getNumberOfInstallments());

        // Check Transaction Installments
        final var installments = transaction.getInstallments();
        assertEquals(12, installments.size());
        final var first = installments.stream().filter(x -> x.getNumber() == 1).findFirst();
        assertTrue(first.isPresent());
        assertEquals(new BigDecimal("83.33"), first.get().getValue());
        final var last = installments.stream().filter(x -> x.getNumber() == 12).findFirst();
        assertTrue(last.isPresent());
        assertEquals(new BigDecimal("83.37"), last.get().getValue());
    }
}
