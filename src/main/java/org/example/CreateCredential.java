package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.webank.weid.protocol.base.Credential;
import com.webank.weid.protocol.base.CredentialWrapper;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.request.CreateCredentialArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CredentialService;
import com.webank.weid.service.impl.CredentialServiceImpl;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//Credential(context=https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1, id=16ebb9e6-8797-408a-9659-7a8b8ccaba34, cptId=2000000, issuer=did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209, issuanceDate=1629427529, expirationDate=2351448312, claim={name=Melo, age=23}, proof={creator=did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209, signature=d4zvNKxLbsJpgZZHA5zk52JI7nr0U3h+KdWM74d6ynlbZxJCOM/qJsh0WCfiBuPwaaHKjKMjT8avaZzcaW3IaQA=, created=1629427529, type=Secp256k1})
// did:weid:12138:0x61071c950fa976ac370ab5c940577ca07dc936a8
//111979719028360571539936581644131352516832130793573303064024905843393027854319
public class CreateCredential {
    public static void main(String[] args) throws JsonProcessingException {
        CredentialService credentialService = new CredentialServiceImpl();

        HashMap<String, Object> claim = new HashMap<String, Object>(2);
        claim.put("name", "asic");
        claim.put("age", 11);

//        claim.put("name","Asia");
//        claim.put("gender","F");
//        claim.put("level","C1");
//        claim.put("registration_date","20201010");

        CreateCredentialArgs createCredentialArgs = new CreateCredentialArgs();
        createCredentialArgs.setClaim(claim);
        createCredentialArgs.setCptId(2000000);
        //createCredentialArgs.setCptId(1001);
        createCredentialArgs.setExpirationDate(2351448312461L);
        createCredentialArgs.setIssuer("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey("57262961193922983192585096379094476780371414196834152140262521340034169261079");

        createCredentialArgs.setWeIdPrivateKey(weIdPrivateKey);

        // 创建Credential
        ResponseData<CredentialWrapper> response = credentialService.createCredential(createCredentialArgs);
        Credential credential = response.getResult().getCredential();
        System.out.printf(response.getResult().getCredential().toString() + "\n");

        CreateCredential d = new CreateCredential();
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
        object.put("context","https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1");
        object.put("id","b4cc22f3-c485-4664-beb7-4fca1ca9c5d2");
        object.put("issuer","did:weid:12138:0x61071c950fa976ac370ab5c940577ca07dc936a8");
        object.put("issuanceDate",1630573881);
        object.put("expirationDate",1830619615);
        object.put("claim","{\"name\":\"zhangsan\",\"age\":22}");
        object.put("proof","{\"creator\":\"did:weid:12138:0x61071c950fa976ac370ab5c940577ca07dc936a8#key0\",\"salt\":{\"name\":\"cA9e7\",\"age\":\"i8aVq\"},\"created\":1630573881,\"type\":\"Secp256k1\",\"signatureValue\":\"+7tsRsYVqTyo77Kc4EYwlEc24Jn89mnNbshFZevegwLpefh8v3gHWDsg0gHA6xsMk9h/jUkW0gB0fcDa7/MwMwA=\"}");
        object.put("cptId",2000000);
        return object.toString();
    }
}






