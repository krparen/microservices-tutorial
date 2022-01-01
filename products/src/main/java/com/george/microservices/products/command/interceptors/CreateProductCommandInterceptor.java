package com.george.microservices.products.command.interceptors;

import com.george.microservices.products.command.CreateProductCommand;
import com.george.microservices.products.core.data.ProductLookupEntity;
import com.george.microservices.products.core.data.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final String ERROR_MESSAGE_TEMPLATE = "Entity with productId = %s or title = %s already exists";

    private final ProductLookupRepository productLookupRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {

        return (index, command) -> {

            log.info("intercepted command: {}", command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {

                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(
                        createProductCommand.getProductId(),
                        createProductCommand.getTitle());

                if (productLookupEntity != null) {
                    String errorMessage = String.format(
                            ERROR_MESSAGE_TEMPLATE,
                            productLookupEntity.getProductId(),
                            productLookupEntity.getTitle()
                    );

                    throw new IllegalStateException(errorMessage);
                }
            }

            return command;
        };
    }
}
