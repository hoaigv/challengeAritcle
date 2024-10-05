package com.challenge.aritcle.users.repository;

import com.challenge.aritcle.users.models.FollowEntity;
import com.challenge.aritcle.users.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity,String> {
    Page<FollowEntity> findByFollowing(UserEntity following, Pageable pageable);
    Page<FollowEntity> findByFollower(UserEntity follower, Pageable pageable);
    Optional<FollowEntity> findByFollowerAndFollowing(UserEntity follower, UserEntity following);
    boolean existsByFollowerAndFollowing(UserEntity follower, UserEntity following);
}
