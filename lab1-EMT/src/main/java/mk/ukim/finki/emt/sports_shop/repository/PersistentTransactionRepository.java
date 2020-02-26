package mk.ukim.finki.emt.sports_shop.repository;


import mk.ukim.finki.emt.sports_shop.models.Transactions;
import org.springframework.data.repository.Repository;

public interface PersistentTransactionRepository extends Repository<Transactions, String> {

    Transactions save(Transactions transaction);
}
