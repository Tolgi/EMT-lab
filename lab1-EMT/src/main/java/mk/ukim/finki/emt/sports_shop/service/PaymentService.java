package mk.ukim.finki.emt.sports_shop.service;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import mk.ukim.finki.emt.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emt.sports_shop.models.Transactions;

public interface PaymentService {
    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException;

    Transactions addNewTransaction(String id, String status, String chargeId, String balance_transactionId);
}
