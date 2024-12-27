package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Stock;
import com.taskease.college.PayLoad.StockDTO;
import com.taskease.college.Repository.StockRepo;
import com.taskease.college.Service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final ModelMapper modelMapper;
    private final StockRepo stockRepo;

    public StockServiceImpl(ModelMapper modelMapper, StockRepo stockRepo) {
        this.modelMapper = modelMapper;
        this.stockRepo = stockRepo;
    }

    @Override
    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = modelMapper.map(stockDTO, Stock.class);
        Stock save = stockRepo.save(stock);
        return modelMapper.map(save, StockDTO.class);
    }

    @Override
    public List<StockDTO> getStocks() {
        List<StockDTO> stockDTOS  = stockRepo.findAll().stream().map(stock -> modelMapper.map(stock, StockDTO.class)).toList();
        return stockDTOS;
    }

    @Override
    public String updateToggle(int id) {
        Stock stock = stockRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Stock","Id",id));
        stock.setStatus(!stock.getStatus());
        stockRepo.save(stock);
        return stock.getStatus() ? "Available" : "Out of Stock";
    }

    @Override
    public void deleteStock(int id) {
        Stock stock = stockRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Stock","Id",id));
        stockRepo.delete(stock);
    }
}
