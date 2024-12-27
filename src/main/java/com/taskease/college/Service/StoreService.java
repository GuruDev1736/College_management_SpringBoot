package com.taskease.college.Service;

import com.taskease.college.PayLoad.StoreDTO;

import java.util.List;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO);
    String updateStatus(String category);
    void deleteStore(String category);
    List<StoreDTO> getStoreList();
}
