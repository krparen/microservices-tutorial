package com.george.microservices.products.core.errorhandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class ProductsServiceEventsErrorHandler implements ListenerInvocationErrorHandler {

    @Override
    public void onError(
            Exception e,
            EventMessage<?> eventMessage,
            EventMessageHandler eventMessageHandler) throws Exception {

        /*
        Бросаем тут исключение, чтобы оно пришло в Rest Controller Advice и там обработалось.
        Дефолтное поведение - залогировать эксепшон и двигаться дальше.
         */
        throw e;
    }
}
