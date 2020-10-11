package com.kishor.paypalbookstore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kishor.paypalbookstore.entity.Customer;
import com.kishor.paypalbookstore.entity.Users;

//@Repository
//public interface UsersRepository extends JpaRepository<Users, Integer> {

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, Integer> {
	
	@Query("Select u from Users u where u.username=:username")
	public Users getUserByUsername(@Param("username") String username);
//	@Query(value="SELECT * from users where username=?1",nativeQuery=true)
//	public Users getUserByUsername(@Param("username") String username);
	

	@Query(value="SELECT users.user_id,users.username,users.password,users.password_hint,users.enabled,roles_users.role_id from users,roles_users where users.user_id=roles_users.user_id AND roles_users.role_id=2",nativeQuery=true)
	public  Page<Users> getOnlyStaff(Pageable number);
	
	public Users findByUsername(String username);

}
