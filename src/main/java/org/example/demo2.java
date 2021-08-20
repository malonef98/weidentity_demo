package org.example;

import com.webank.weid.protocol.base.ClaimPolicy;
import com.webank.weid.protocol.base.CredentialPojo;
import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.request.CreateCredentialPojoArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CredentialPojoService;
import com.webank.weid.service.impl.CredentialPojoServiceImpl;

import java.util.HashMap;
import java.util.Map;

//weId= did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209
//publicKey=  4818518833072282173770949061022161020134153013863998604521376515729480354647087769459660716573699399233936170136013054465239030826249531570139916660827466
//privateKey= 57262961193922983192585096379094476780371414196834152140262521340034169261079

public class demo2 {
    public static void main(String[] args) {
        CredentialPojoService credentialPojoService = new CredentialPojoServiceImpl();
        CreateCredentialPojoArgs<Map<String, Object>> createCredentialPojoArgs =
                new CreateCredentialPojoArgs<Map<String, Object>>();
        createCredentialPojoArgs.setCptId(2000000);
        createCredentialPojoArgs
                .setIssuer("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");
        createCredentialPojoArgs
                .setExpirationDate(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 100);

        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
        weIdAuthentication.setWeId("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(
                "57262961193922983192585096379094476780371414196834152140262521340034169261079");
        weIdAuthentication.setWeIdPrivateKey(weIdPrivateKey);

        weIdAuthentication
                .setWeIdPublicKeyId("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209#key0");
        createCredentialPojoArgs.setWeIdAuthentication(weIdAuthentication);

        Map<String, Object> claim = new HashMap<String, Object>();
        claim.put("name", "lisa");
        claim.put("age", 24);
        createCredentialPojoArgs.setClaim(claim);

        ResponseData<CredentialPojo> response =
                credentialPojoService.createCredential(createCredentialPojoArgs);

        // 选择性披露
        ClaimPolicy claimPolicy = new ClaimPolicy();
        claimPolicy.setFieldsToBeDisclosed("{\"name\":1,\"age\":0}");
        ResponseData<CredentialPojo> selectiveResponse =
                credentialPojoService.createSelectiveCredential(response.getResult(), claimPolicy);

        System.out.printf(selectiveResponse.getResult().toJson());


        ResponseData<CredentialPojo> response2 = credentialPojoService.createCredential(createCredentialPojoArgs);

        ResponseData<Boolean> responseVerify = credentialPojoService.verify("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209", response2.getResult());

    }
}
