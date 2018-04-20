package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import model.BiddingUser;

public interface BiddingUserRepository extends JpaRepository<BiddingUser, String> {
}
