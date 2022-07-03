package com.example.travelermileageservice.domain.point.entity;

import com.example.travelermileageservice.domain.base.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public final class PointHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID sourceId;

    @Column(updatable = false, nullable = false)
    private Integer addPoint;

    @Column(updatable = false, nullable = false)
    private Integer addBonusPoint;

    private PointHistory(final Type type, final UUID sourceId, final UUID userId, final Integer addPoint, final Integer addBonusPoint) {
        this.type = type;
        this.sourceId = sourceId;
        this.addPoint = addPoint;
        this.addBonusPoint = addBonusPoint;

        this.createdBy = userId;
        this.updatedBy = userId;
    }

    public static PointHistory of(final Type type, final UUID sourceId, final UUID userId, final Integer addBonusPoint) {
        return new PointHistory(type, sourceId, userId, type.addPoint, addBonusPoint);
    }

    @RequiredArgsConstructor
    public enum Type {
        REVIEW_ADD(1),
        REVIEW_DELETE(-1);

        private final Integer addPoint;
    }
}
