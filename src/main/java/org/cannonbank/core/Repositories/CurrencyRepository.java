package org.cannonbank.core.Repositories;

import org.cannonbank.core.Entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

}
