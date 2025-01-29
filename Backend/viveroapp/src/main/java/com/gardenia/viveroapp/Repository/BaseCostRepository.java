package com.gardenia.viveroapp.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gardenia.viveroapp.Model.BaseCost;

@Repository
public interface BaseCostRepository extends JpaRepository<BaseCost, Integer> {

}
