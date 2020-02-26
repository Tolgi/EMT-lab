package mk.ukim.finki.emt.sports_shop.models;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    private String id;
    private String status;
    private String chargeId;
    private String balance_transactionId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getBalance_transactionId() {
        return balance_transactionId;
    }

    public void setBalance_transactionId(String balance_transactionId) {
        this.balance_transactionId = balance_transactionId;
    }
}
