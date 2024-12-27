package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Store;
import com.taskease.college.PayLoad.StoreDTO;
import com.taskease.college.Repository.StoreRepo;
import com.taskease.college.Service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepo;
    private final ModelMapper modelMapper;


    public StoreServiceImpl(StoreRepo storeRepo, ModelMapper modelMapper) {
        this.storeRepo = storeRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public StoreDTO createStore(StoreDTO storeDTO) {
        Store store = modelMapper.map(storeDTO, Store.class);
        Store save = storeRepo.save(store);
        return modelMapper.map(save, StoreDTO.class);
    }

    @Override
    public String updateStatus(String category) {
        Store store = storeRepo.findByCategory(category).orElseThrow(()-> new ResourceNotFoundException("Store","Category",0));
        store.setStatus(!store.getStatus());
        storeRepo.save(store);
        return store.getStatus() ? "Open" : "Close";
    }

    @Override
    public void deleteStore(String category) {
        Store store = storeRepo.findByCategory(category).orElseThrow(()-> new ResourceNotFoundException("Store","Category",0));
        storeRepo.delete(store);
    }

    @Override
    public List<StoreDTO> getStoreList() {
        List<StoreDTO> storeDTOS = storeRepo.findAll().stream().map(x -> modelMapper.map(x, StoreDTO.class)).toList();
        return storeDTOS;
    }
}
