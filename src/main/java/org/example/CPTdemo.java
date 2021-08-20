package org.example;

import com.webank.weid.constant.JsonSchemaConstant;
import com.webank.weid.protocol.base.CptBaseInfo;
import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.request.CptMapArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CptService;
import com.webank.weid.service.impl.CptServiceImpl;

import java.util.HashMap;

// 注册 驾照CPT模板
public class CPTdemo {
    public static void main(String[] args) {
        CptService cptService = new CptServiceImpl();

        HashMap<String, Object> cptJsonSchema = new HashMap<String, Object>(3);
        cptJsonSchema.put(JsonSchemaConstant.TITLE_KEY, "driver license cpt");
        cptJsonSchema.put(JsonSchemaConstant.DESCRIPTION_KEY, "this is a cpt driver license");

        HashMap<String, Object> propertitesMap1 = new HashMap<String, Object>(2);
        propertitesMap1.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_STRING);
        propertitesMap1.put(JsonSchemaConstant.DESCRIPTION_KEY, "this is driver name");

        String[] genderEnum = { "F", "M" };
        HashMap<String, Object> propertitesMap2 = new HashMap<String, Object>(2);
        propertitesMap2.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_STRING);
        propertitesMap2.put(JsonSchemaConstant.DATA_TYPE_ENUM, genderEnum);

        HashMap<String, Object> propertitesMap3 = new HashMap<String, Object>(2);
        propertitesMap3.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_NUMBER);
        propertitesMap3.put(JsonSchemaConstant.DESCRIPTION_KEY, "this is age");

        HashMap<String, Object> propertitesMap4 = new HashMap<String, Object>(2);
        propertitesMap4.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_STRING);
        propertitesMap4.put(JsonSchemaConstant.DESCRIPTION_KEY, "The registration date");

        HashMap<String, Object> propertitesMap5 = new HashMap<String, Object>(2);
        propertitesMap5.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_STRING);
        propertitesMap5.put(JsonSchemaConstant.DESCRIPTION_KEY, "The carType");

        HashMap<String, Object> propertitesMap6 = new HashMap<String, Object>(2);
        propertitesMap6.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_STRING);
        propertitesMap6.put(JsonSchemaConstant.DESCRIPTION_KEY, "The level");

        HashMap<String, Object> propertitesMap7 = new HashMap<String, Object>(2);
        propertitesMap7.put(JsonSchemaConstant.TYPE_KEY, JsonSchemaConstant.DATA_TYPE_STRING);
        propertitesMap7.put(JsonSchemaConstant.DESCRIPTION_KEY, "The Expiration date");

        HashMap<String, Object> cptJsonSchemaKeys = new HashMap<String, Object>(7);
        cptJsonSchemaKeys.put("name", propertitesMap1);
        cptJsonSchemaKeys.put("gender", propertitesMap2);
        cptJsonSchemaKeys.put("age", propertitesMap3);
        cptJsonSchemaKeys.put("registration_date", propertitesMap4);
        cptJsonSchemaKeys.put("carType", propertitesMap5);
        cptJsonSchemaKeys.put("level", propertitesMap6);
        cptJsonSchemaKeys.put("Expiration_date", propertitesMap7);

        cptJsonSchema.put(JsonSchemaConstant.PROPERTIES_KEY, cptJsonSchemaKeys);

        String[] genderRequired = { "name", "gender","level","registration_date"};
        cptJsonSchema.put(JsonSchemaConstant.REQUIRED_KEY, genderRequired);

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey("57262961193922983192585096379094476780371414196834152140262521340034169261079");

        WeIdAuthentication weIdAuthentication = new WeIdAuthentication();
        weIdAuthentication.setWeId("did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209");
        weIdAuthentication.setWeIdPrivateKey(weIdPrivateKey);

        CptMapArgs cptMapArgs = new CptMapArgs();
        cptMapArgs.setCptJsonSchema(cptJsonSchema);
        cptMapArgs.setWeIdAuthentication(weIdAuthentication);

        ResponseData<CptBaseInfo> response = cptService.registerCpt(cptMapArgs);
        System.out.printf(response.toString());
    }
}
