package com.lijingyao.stateMachine;

import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

/**
 * Created by lijingyao on 2017/11/24 10:40.
 */
@Configuration
public class OrderPersistHandlerConfig {

    @Resource
    private StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine;


    @Bean
    public OrderStateService persist() {
        PersistStateMachineHandler handler = persistStateMachineHandler();
        handler.addPersistStateChangeListener(persistStateChangeListener());
        return new OrderStateService(handler);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(stateMachine);
    }

    @Bean
    public OrderPersistStateChangeListener persistStateChangeListener(){
        return new OrderPersistStateChangeListener();
    }


}
