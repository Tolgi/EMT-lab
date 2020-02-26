package mk.ukim.finki.emt.sports_shop.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.ukim.finki.emt.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emt.sports_shop.models.Transactions;
import mk.ukim.finki.emt.sports_shop.repository.PersistentTransactionRepository;
import mk.ukim.finki.emt.sports_shop.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;
    private PersistentTransactionRepository repo;

    public PaymentServiceImpl(PersistentTransactionRepository repo){
        this.repo = repo;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripePrivateKey;
    }

    @Override
    public Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);

    }

    @Override
    public Transactions addNewTransaction(String id, String status, String chargeId, String balance_transactionId) {

        Transactions transaction = new Transactions();
        transaction.setId(id);
        transaction.setStatus(status);
        transaction.setChargeId(chargeId);
        transaction.setBalance_transactionId(balance_transactionId);

        repo.save(transaction);
        return transaction;
    }


}
