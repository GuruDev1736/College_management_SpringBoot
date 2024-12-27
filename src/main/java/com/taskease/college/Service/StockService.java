package com.taskease.college.Service;

import com.taskease.college.PayLoad.StockDTO;

import java.util.List;

public interface StockService {

    StockDTO createStock(StockDTO stockDTO);
    List<StockDTO> getStocks();
    String updateToggle(int id);
    void deleteStock(int id);
}
