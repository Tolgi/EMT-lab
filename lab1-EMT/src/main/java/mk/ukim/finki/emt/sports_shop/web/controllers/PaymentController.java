package mk.ukim.finki.emt.sports_shop.web.controllers;


import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import mk.ukim.finki.emt.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emt.sports_shop.models.Product;
import mk.ukim.finki.emt.sports_shop.service.PaymentService;
import mk.ukim.finki.emt.sports_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    private PaymentService paymentService;
    private ProductService productService;

    public PaymentController(PaymentService paymentService, ProductService productService){
        this.paymentService = paymentService;
        this.productService = productService;
    }


    @RequestMapping("/checkout/{id}")
    @PreAuthorize("isAuthenticated()")
    public String checkout(@PathVariable("id") Long productId,  Model model) {
        Product product = productService.getById(productId);
        model.addAttribute("name", product.getName());
        model.addAttribute("amount", product.getPrice().intValue()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }


    @PostMapping("/charge")
    public String charge( ChargeRequest chargeRequest, Model model)
            throws StripeException {

        chargeRequest.setDescription("EMT payment");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        paymentService.addNewTransaction(charge.getId(), charge.getStatus(), charge.getId(), charge.getBalanceTransaction());
        return "result";
    }
}
