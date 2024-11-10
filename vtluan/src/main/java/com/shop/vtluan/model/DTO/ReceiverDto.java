package com.shop.vtluan.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReceiverDto {

    @NotBlank(message = "Không được để chống")
    @Size(min = 3, message = "Tối thiểu 3 kí tự")
    String receiverName;
    @NotBlank(message = "Không được để chống")
    @Size(min = 3, message = "Tối thiểu 3 kí tự")
    String receiverPhoneNumber;
    @NotBlank(message = "Không được để chống")
    @Size(min = 3, message = "Tối thiểu 3 kí tự")
    String receiverAddress;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

}
