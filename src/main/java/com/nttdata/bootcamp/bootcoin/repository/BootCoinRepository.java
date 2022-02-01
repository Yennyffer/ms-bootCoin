package com.nttdata.bootcamp.bootcoin.repository;

import com.nttdata.bootcamp.bootcoin.model.BootCoin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootCoinRepository
        extends ReactiveMongoRepository<BootCoin,String> {
}

