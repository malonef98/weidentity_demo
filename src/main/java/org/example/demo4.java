package org.example;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/8/20
 * @time: 14:51
 */
public class demo4 {
    public static void main(String[] args) throws NoSuchFieldException, JsonProcessingException {
        String str = "{\n" +
                "  \"claimData\": {\n" +
                "    \"age\": 32,\n" +
                "    \"name\": \"zhang san\",\n" +
                "    \"gender\": \"F\"\n" +
                "  },\n" +
                "  \"cptId\": 1001,\n" +
                "  \"issuer\": \"did:weid:1:0x19607cf2bc4538b49847b43688acf3befc487a41\"\n" +
                "}";

        String c = "{\"context\":\"https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1\"," +
                "\"id\":\"66585862-c97c-4328-b689-82c7acb6a86f\"," +
                "\"cptId\":2000000," +
                "\"issuer\":\"did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209\"," +
                "\"issuanceDate\":1629429595,\"expirationDate\":2351448312," +
                "\"claim\":{\"name\":\"Melo\",\"age\":23}," +
                "\"proof\":{\"creator\":\"did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209\"," +
                "\"signature\":\"ti2CTkreJ7VxhKZC82LuYuc1XJ5DLtP22z6y0Wzv3jsnP5w698HqRa3iE7HslItvygxxPQ++lw2nOJbG9Ve9gAA=\"," +
                "\"created\":\"1629429595\",\"type\":\"Secp256k1\"}," +
                "\"proofType\":\"Secp256k1\",\"hash\":\"0x67877fe37fc364efe53b1e8b4daa8280471e222bb3057009d3fca26d5b68810d\"," +
                "\"signatureThumbprint\":\"{\\\"claim\\\":\\\"age0x10c7300213002bff7f4b88b32a678c690bb211bddf8a5a17d9493a29f7df6883name0x0086d7a5e8dea02270fa0a8db0cbef0ce955aa6f4a3bba4ef9eb50d995cf738a\\\",\\\"context\\\":\\\"https://github.com/WeBankFinTech/WeIdentity/blob/master/context/v1\\\",\\\"cptId\\\":2000000,\\\"expirationDate\\\":2351448312,\\\"id\\\":\\\"66585862-c97c-4328-b689-82c7acb6a86f\\\",\\\"issuanceDate\\\":1629429595,\\\"issuer\\\":\\\"did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209\\\",\\\"proof\\\":{\\\"created\\\":\\\"1629429595\\\",\\\"creator\\\":\\\"did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209\\\",\\\"signature\\\":\\\"ti2CTkreJ7VxhKZC82LuYuc1XJ5DLtP22z6y0Wzv3jsnP5w698HqRa3iE7HslItvygxxPQ++lw2nOJbG9Ve9gAA=\\\",\\\"type\\\":\\\"Secp256k1\\\"}}\",\"signature\":\"ti2CTkreJ7VxhKZC82LuYuc1XJ5DLtP22z6y0Wzv3jsnP5w698HqRa3iE7HslItvygxxPQ++lw2nOJbG9Ve9gAA=\"}\n" +
                "       ";
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> resultMap = mapper.readValue(str, new TypeReference<HashMap<String,Object>>(){});

        HashMap<String,Object>claim = (HashMap<String, Object>) resultMap.get("claimData");
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        for (Map.Entry<String, Object> entry : claim.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }
}
