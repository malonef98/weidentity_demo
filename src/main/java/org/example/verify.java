package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webank.weid.protocol.base.Credential;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CredentialService;
import com.webank.weid.service.impl.CredentialServiceImpl;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/8/20
 * @time: 11:24
 */
public class verify {
    public static void main(String[] args) throws IOException {
        CredentialService credentialService = new CredentialServiceImpl();
        verify v = new verify();
        String Credential_Json = "{\"context\":\"https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1\"," +
                "\"id\":\"a4f27162-5932-48e2-868b-bdb60b475e6d\"," +
                "\"issuer\":\"did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209\"," +
                "\"issuanceDate\":1629451079," +
                "\"expirationDate\":2351448312," +
                "\"claim\":{\"name\":\"asic\",\"age\":11}," +
                "\"proof\":{\"creator\":\"did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209\"," +
                "\"signature\":\"SGpKMMI3dutgzKlW7feNdfj+8DbnIzCA+Od23h4XCZYQSG7Qj434mrHcOSUpiZ+UJ4pvFldpVHgIMTBoNMD3yQA=\",\"created\":\"1629451079\",\"type\":\"Secp256k1\"}," +
                "\"cptId\":2000000}\n";

        String Json2 = "{\"context\":\"https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1\",\"id\":\"b4cc22f3-c485-4664-beb7-4fca1ca9c5d2\",\"issuer\":\"did:weid:12138:0x61071c950fa976ac370ab5c940577ca07dc936a8\",\"issuanceDate\":1630573881,\"expirationDate\":1830619615,\"claim\":{\"name\":\"zhangsan\",\"age\":22},\"proof\":{\"creator\":\"did:weid:12138:0x61071c950fa976ac370ab5c940577ca07dc936a8#key0\",\"salt\":{\"name\":\"cA9e7\",\"age\":\"i8aVq\"},\"created\":1630573881,\"type\":\"Secp256k1\",\"signatureValue\":\"+7tsRsYVqTyo77Kc4EYwlEc24Jn89mnNbshFZevegwLpefh8v3gHWDsg0gHA6xsMk9h/jUkW0gB0fcDa7/MwMwA=\"},\"cptId\":2000000}\n";
        Credential c = v.getCredential(Credential_Json);
        ResponseData<Boolean> responseVerify = credentialService.verify(c);
        System.out.printf(responseVerify.toString());
    }

    public Credential getCredential(String JSOM_Credential) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(JSOM_Credential);
        Credential credential = objectMapper.readValue(reader, Credential.class);
        return credential;
    }
}



//        HashMap<String,Object> claim = new HashMap<>();
//        claim.put("name","Melo");
//        claim.put("age",23);
//
//        HashMap<String,String> proof = new HashMap<>();
//        proof.put("creator","did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");
//        proof.put("signature","ti2CTkreJ7VxhKZC82LuYuc1XJ5DLtP22z6y0Wzv3jsnP5w698HqRa3iE7HslItvygxxPQ++lw2nOJbG9Ve9gAA=");
//        proof.put("created","1629429595");
//        proof.put("type","Secp256k1");
//
//        Credential credential = new Credential();
//        credential.setClaim(claim);
//        credential.setCptId(Integer.parseInt("2000000"));
//        credential.setExpirationDate(Long.parseLong("2351448312"));
//        credential.setIssuanceDate(Long.parseLong("1629429595"));
//        credential.setIssuer("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");
//        credential.setContext("https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1");
//        credential.setId("66585862-c97c-4328-b689-82c7acb6a86f");
//        credential.setProof(proof);