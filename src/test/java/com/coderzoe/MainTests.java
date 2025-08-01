package com.coderzoe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author yinhuasheng
 * @date 2025/7/25 16:47
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void helloShouldReturnDefaultMessage() throws Exception {
        // 这个测试会模拟一个HTTP GET请求到/hello
        // 然后验证：
        // 1. HTTP状态码是200 (OK)
        // 2. 返回的内容是字符串 "hello world"
        this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello cicd develop env"));
    }
}
