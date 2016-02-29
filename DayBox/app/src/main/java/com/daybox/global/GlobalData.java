package com.daybox.global;

import android.app.Application;

import com.daybox.dto.OrderDTO;

import java.util.List;

/**
 * Created by adityaagrawal on 28/02/16.
 */
public class GlobalData extends Application{

    private List<OrderDTO> orderDTOs;


    public List<OrderDTO> getOrderDTOs() {
        return orderDTOs;
    }

    public void setOrderDTOs(List<OrderDTO> orderDTOs) {
        this.orderDTOs = orderDTOs;
    }
}
