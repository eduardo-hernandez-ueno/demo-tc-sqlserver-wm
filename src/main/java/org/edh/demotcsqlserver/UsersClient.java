package org.edh.demotcsqlserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usersClient", url = "${test.url}")
public interface UsersClient {
    @GetMapping("/{id}")
    User getById(@PathVariable String id);
}
