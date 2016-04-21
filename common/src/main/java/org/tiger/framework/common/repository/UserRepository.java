package org.tiger.framework.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tiger.framework.common.entity.User;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
