package org.example.CredentialPojoServicedemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webank.weid.protocol.base.CredentialPojo;
import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.request.CreateCredentialPojoArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CredentialPojoService;
import com.webank.weid.service.impl.CredentialPojoServiceImpl;
import org.example.entity.CreateCredential;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/9/2
 * @time: 14:38
 */
public class demo03 {
    public static void main(String[] args) throws JsonProcessingException {


        String weId= "did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209";
        String publicKey="4818518833072282173770949061022161020134153013863998604521376515729480354647087769459660716573699399233936170136013054465239030826249531570139916660827466";
        String privateKey = "57262961193922983192585096379094476780371414196834152140262521340034169261079";

        Map<String, Object> claim = new HashMap<String, Object>();
        claim.put("age", 22);
        claim.put("name","zhang san");

        CreateCredential credential = new CreateCredential();
        credential.setCptId(2000000);
        credential.setClaimData(claim);
        credential.setPriKey(privateKey);
        credential.setIssuer(weId);
        credential.setExpirationDate(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 100);


        CreateCredentialPojoArgs<Map<String, Object>> createCredentialPojoArgs = new CreateCredentialPojoArgs<Map<String, Object>>();
        createCredentialPojoArgs.setCptId(credential.getCptId());
        createCredentialPojoArgs.setExpirationDate(credential.getExpirationDate());
        createCredentialPojoArgs.setIssuer(credential.getIssuer());
        createCredentialPojoArgs.setClaim(credential.getClaimData());

        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
        weIdAuthentication.setWeId(credential.getIssuer());

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(credential.getPriKey());
        weIdAuthentication.setWeIdPrivateKey(weIdPrivateKey);
        weIdAuthentication.setWeIdPublicKeyId(credential.getIssuer() + "#key0");
        createCredentialPojoArgs.setWeIdAuthentication(weIdAuthentication);

        ObjectMapper objectMapper = new ObjectMapper();
        String Json = objectMapper.writeValueAsString(credential);
        System.out.printf(Json);

//        CredentialPojoService credentialPojoService = new CredentialPojoServiceImpl();
//        CreateCredentialPojoArgs<Map<String, Object>> createCredentialPojoArgs = new CreateCredentialPojoArgs<Map<String, Object>>();
//        createCredentialPojoArgs.setCptId(1017);
//        createCredentialPojoArgs.setIssuer(weId);
//        createCredentialPojoArgs.setExpirationDate(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 100);
//
//        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
//        weIdAuthentication.setWeId(weId);
//
//        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
//        weIdPrivateKey.setPrivateKey(privateKey);
//        weIdAuthentication.setWeIdPrivateKey(weIdPrivateKey);
//
//        weIdAuthentication.setWeIdPublicKeyId(weId + "#key0");
//        createCredentialPojoArgs.setWeIdAuthentication(weIdAuthentication);



        CredentialPojoService credentialPojoService = new CredentialPojoServiceImpl();
        ResponseData<CredentialPojo> response = credentialPojoService.createCredential(createCredentialPojoArgs);
        System.out.printf(response.getResult().toString());

        CredentialPojo credentialPojo = new CredentialPojo();
        credentialPojo.setIssuer(response.getResult().getIssuer());
        credentialPojo.setId(response.getResult().getId());
        credentialPojo.setCptId(response.getResult().getCptId());
        credentialPojo.setExpirationDate(response.getResult().getExpirationDate());
        credentialPojo.setClaim(response.getResult().getClaim());
        credentialPojo.setProof(response.getResult().getProof());
        credentialPojo.setType(response.getResult().getType());
        credentialPojo.setContext(response.getResult().getContext());
        credentialPojo.setExpirationDate(response.getResult().getExpirationDate());
        credentialPojo.setIssuanceDate(response.getResult().getIssuanceDate());

        ResponseData<Boolean> responseVerify = credentialPojoService.verify(weId, credentialPojo) ;
        System.out.printf(credentialPojo.toJson() + "\n");
        System.out.printf(responseVerify.toString());
    }
}
