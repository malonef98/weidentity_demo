package org.example;

import com.webank.weid.protocol.response.CreateWeIdDataResult;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.WeIdServiceImpl;

public class WeIdent_demo {
    public static void main(String[] args) {
        WeIdService weIdService = new WeIdServiceImpl();
        ResponseData<CreateWeIdDataResult> response = weIdService.createWeId();
        System.out.printf(response.toString());
    }
}
