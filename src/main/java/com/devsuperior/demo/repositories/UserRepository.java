package com.devsuperior.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.demo.entities.User;
import com.devsuperior.demo.projection.UserDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /*
   * Consulta SQL para buscar um usuário por email com as roles
   * para evitar configurar a entidade 'User' com Eager Loading
   * no many to many, ou evitar usar o JOIN FETCH no JPQL.
   */
  @Query(nativeQuery = true, value = """
      SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority
      FROM tb_user
      INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
      INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
      WHERE tb_user.email = :email
      """)
  List<UserDetailsProjection> searchUserAndRolesByByEmail(String email);

  User findByEmail(String email);

}
