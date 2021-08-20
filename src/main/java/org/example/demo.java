package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webank.weid.exception.DataTypeCastException;
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
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//Credential(context=https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1, id=16ebb9e6-8797-408a-9659-7a8b8ccaba34, cptId=2000000, issuer=did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209, issuanceDate=1629427529, expirationDate=2351448312, claim={name=Melo, age=23}, proof={creator=did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209, signature=d4zvNKxLbsJpgZZHA5zk52JI7nr0U3h+KdWM74d6ynlbZxJCOM/qJsh0WCfiBuPwaaHKjKMjT8avaZzcaW3IaQA=, created=1629427529, type=Secp256k1})

public class demo {
    public static void main(String[] args) throws JsonProcessingException {
        CredentialService credentialService = new CredentialServiceImpl();

        HashMap<String, Object> claim = new HashMap<String, Object>(2);
        claim.put("name", "asic");
        claim.put("age", 11);

        CreateCredentialArgs createCredentialArgs = new CreateCredentialArgs();
        createCredentialArgs.setClaim(claim);
        createCredentialArgs.setCptId(2000000);
        createCredentialArgs.setExpirationDate(2351448312461L);
        createCredentialArgs.setIssuer("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey("57262961193922983192585096379094476780371414196834152140262521340034169261079");

        createCredentialArgs.setWeIdPrivateKey(weIdPrivateKey);

        // 创建Credential
        ResponseData<CredentialWrapper> response = credentialService.createCredential(createCredentialArgs);
        Credential credential = response.getResult().getCredential();
        System.out.printf(response.getResult().getCredential().toString() + "\n");

        demo d = new demo();
        System.out.println("***\n"+d.getCredential_JSON(response));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(credential);
//        System.out.println("####\n"+json+"\n###");

        //获取证书内容

    }

    public String getCredential_JSON(ResponseData<CredentialWrapper> response){
        String context = response.getResult().getCredential().getContext();
        String id = response.getResult().getCredential().getId();
        Integer cptid = response.getResult().getCredential().getCptId();
        String issuer = response.getResult().getCredential().getIssuer();
        Long issuanceDate = response.getResult().getCredential().getIssuanceDate();
        Long expirationDate = response.getResult().getCredential().getExpirationDate();
        Map<String, Object> claims = response.getResult().getCredential().getClaim();
        Map<String, String>  proof = response.getResult().getCredential().getProof();

        JSONObject object = new JSONObject();
        object.put("context",context);
        object.put("id",id);
        object.put("issuer",issuer);
        object.put("issuanceDate",issuanceDate);
        object.put("expirationDate",expirationDate);
        object.put("claim",claims);
        object.put("proof",proof);
        object.put("cptId",cptid);
        return object.toString();
    }
}






