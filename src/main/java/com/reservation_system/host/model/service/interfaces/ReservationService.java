package com.reservation_system.host.model.service.interfaces;

import com.reservation_system.host.model.entity.ReservationEntity;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    void create(ReservationEntity reservation);
    List<ReservationEntity> readAll();
    ReservationEntity read(UUID id);
    boolean update(ReservationEntity reservation, UUID id);
    boolean delete(UUID id);
}