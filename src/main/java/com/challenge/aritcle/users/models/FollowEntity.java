package com.challenge.aritcle.users.models;

import com.challenge.aritcle.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table
@Entity(name = "follows")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn
    @NotNull
    @JsonBackReference
    UserEntity follower;

    @ManyToOne
    @JoinColumn
    @NotNull
    @JsonBackReference
    UserEntity following;
}
