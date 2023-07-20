package vn.com.msb.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.msb.aop.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
