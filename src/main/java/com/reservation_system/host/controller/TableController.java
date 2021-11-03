package com.reservation_system.host.controller;

import com.reservation_system.host.infrastructure.ReservationDates;
import com.reservation_system.host.model.entity.TableEntity;
import com.reservation_system.host.model.entity.TableStatusEnum;
import com.reservation_system.host.model.service.ReservationService;
import com.reservation_system.host.model.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TableController {

    private final TableService tableService;
    private final ReservationService reservationService;

    public TableController(TableService tableService, ReservationService reservationService) {
        this.tableService = tableService;
        this.reservationService = reservationService;
    }
    //Доделать
    @GetMapping(value = "/user/tables")
    public ResponseEntity<Map<TableEntity, TableStatusEnum>> getTableMap(@RequestBody ReservationDates reservationDates) {
        try {
            final List<TableEntity> tables = tableService.readAll();
            if (tables == null || tables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Map<TableEntity, TableStatusEnum> tableMap = reservationService.getTablesWithStatus(tables, reservationDates.getBeginDate(), reservationDates.getEndDate());
            return new ResponseEntity<>(tableMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
