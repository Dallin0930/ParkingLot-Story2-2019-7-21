package com.example.demo;

import com.example.demo.modle.ParkingLot;
import com.example.demo.modle.ParkingLotOrder;
import com.example.demo.service.ParkingLotOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotOrderControllerTest {
    @Autowired
     MockMvc mockmvc;

    @MockBean
    ParkingLotOrderService parkingLotOrderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_order_when_park_car_to_parkingLot() throws Exception {
        ParkingLotOrder order1  = new ParkingLotOrder(20190720,"Copened",12568);
        ParkingLotOrder order2  = new ParkingLotOrder(20190720,"C888888",25146);
        order2.getOrderId((long)1);
        ParkingLot parkingLot = new ParkingLot("ParkingLotA",100,"furongqu",50);
        parkingLot.getId((long) 1);

        order2.setParkinglot(parkingLot);
        given(parkingLotOrderService.addOrder((long) 1,order1))
                .willReturn(order2);

        mockmvc.perform(post("/orders/parkingLots/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(order1)))
                .andExpect(content().string(objectMapper.writeValueAsString(order2)));
    }
}
