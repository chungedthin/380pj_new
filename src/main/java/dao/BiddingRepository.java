package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Bidding;

public interface BiddingRepository extends JpaRepository<Bidding, Long> {
}
