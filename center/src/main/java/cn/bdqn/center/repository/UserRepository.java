package cn.bdqn.center.repository;

import cn.bdqn.center.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.status=1")
    Page<User> showUser(Pageable pageable);

    @Modifying
    @Query("update User u set u.status=0 where u.id=?1")
    int deleteUser(Long userId);

    @Query("select u from User u where u.status=1")
    List<User> selectAll();
}
