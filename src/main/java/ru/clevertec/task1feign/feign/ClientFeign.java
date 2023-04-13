package ru.clevertec.task1feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.clevertec.task1feign.model.CheckInfo;

@FeignClient(value = "checks", url = "${app.user_url:http://localhost:8002/}",
        configuration = FeignConfiguration.class)
public interface ClientFeign {

    @PostMapping("/check")
    String check(@RequestBody CheckInfo info, @RequestHeader("authorization") String authorization);

}
