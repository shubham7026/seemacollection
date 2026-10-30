package com.seemacollection.seemacollection.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@ConditionalOnProperty(prefix = "credit", value = "enabled", havingValue = "true", matchIfMissing = false)
@Component
public class RewardPointService {

    @Value("${credit.points}")
    private int points;
}
