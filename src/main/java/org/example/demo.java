package org.example;


import com.webank.weid.protocol.base.Credential;
import com.webank.weid.protocol.base.CredentialWrapper;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.request.CreateCredentialArgs;
import com.webank.weid.protocol.request.ServiceArgs;
import com.webank.weid.protocol.response.CreateWeIdDataResult;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CredentialService;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.CredentialServiceImpl;
import com.webank.weid.service.impl.WeIdServiceImpl;

import java.util.HashMap;

public class demo {
    public static void main(String[] args) {
        WeIdService weIdService = new WeIdServiceImpl();
        ResponseData<CreateWeIdDataResult> response = weIdService.createWeId();


        CredentialService credentialService = new CredentialServiceImpl();

        HashMap<String, Object> claim = new HashMap<String, Object>(3);
        claim.put("name", "Lisa");
        claim.put("age", 23);

        CreateCredentialArgs createCredentialArgs = new CreateCredentialArgs();
        createCredentialArgs.setClaim(claim);
        createCredentialArgs.setCptId(2000000);
        createCredentialArgs.setExpirationDate(2351448312461L);
        createCredentialArgs.setIssuer(response.getResult().getWeId());

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(response.getResult().getUserWeIdPrivateKey().getPrivateKey());

        createCredentialArgs.setWeIdPrivateKey(weIdPrivateKey);

        // 创建Credential
        ResponseData<CredentialWrapper> response2 = credentialService.createCredential(createCredentialArgs);
        System.out.printf(response2.toString());

        //验证Credential
        ResponseData<Boolean> responseVerify = credentialService.verify(response2.getResult().getCredential());
        System.out.printf(responseVerify.toString());
    }
}
