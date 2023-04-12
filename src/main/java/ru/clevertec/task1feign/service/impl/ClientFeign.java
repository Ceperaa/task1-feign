package ru.clevertec.task1feign.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import ru.clevertec.task1feign.model.ClientInfo;

@FeignClient(value = "checks",url = "${app.user_url:http://localhost:8002/}")
@Component
public interface ClientFeign {

    @PostMapping("/check")
    ClientInfo check();

}
