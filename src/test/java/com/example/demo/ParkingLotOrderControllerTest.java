package com.example.demo;

import com.example.demo.exception.NotEnoughCapacityException;
import com.example.demo.modle.ParkingLot;
import com.example.demo.modle.ParkingLotOrder;
import com.example.demo.service.ParkingLotOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
        ParkingLotOrder order1  = new ParkingLotOrder(20190720,"opened",12568);

        ParkingLotOrder order2  = new ParkingLotOrder(20190720,"opened",25146);
        order2.getOrderId((long)1);

        ParkingLot parkingLot = new ParkingLot("ParkingLotA",100,"furongqu",50);
        parkingLot.getId((long) 2);

        order2.setParkinglot(parkingLot);
        given(parkingLotOrderService.addOrder((long) 2,order1))
                .willReturn(order2);

        mockmvc.perform(post("/orders/parkingLots/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(order1)))
                .andExpect(content().string(objectMapper.writeValueAsString(order2)));
    }


    @Test
    public void should_return_changed_order_when_order_status_is_changed() throws Exception {
        ParkingLotOrder order  = new ParkingLotOrder(20190720,"C888888",12356);
        order.setOrderId((long) 1);

        ParkingLot parkingLot = new ParkingLot("ParkingLotA",23,"furongqu",15);
        parkingLot.setId((long) 2);


        given(parkingLotOrderService.updateStatus((long) 1))
                .willReturn(order);

        mockmvc.perform(post("/orders/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(objectMapper.writeValueAsString(order)));
    }


    @Test
    public void should_return_exception_message_when_add_error_order() throws Exception {
        ParkingLotOrder order1  = new ParkingLotOrder(20191258,"x125836",125879);

        ParkingLotOrder order2  = new ParkingLotOrder(20190720,"C828254",251486);
        order2.setOrderId((long) 2);

        ParkingLot parkingLot = new ParkingLot("parkinglotB", 100, "ccc",5);
        parkingLot.setId((long) 3);

        order2.setParkinglot(parkingLot);

        given(parkingLotOrderService.addOrder((long) 3,order1))
                .willThrow(new NotEnoughCapacityException("停车场没有空位置"));

        mockmvc.perform(post("/orders/parkingLots/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(order1)))
                .andExpect(content().string("停车场没有空位置"));
    }
}
