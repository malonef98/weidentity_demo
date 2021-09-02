package org.example.Credentialervice;

import com.webank.weid.protocol.base.CredentialWrapper;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.request.CreateCredentialArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.CredentialService;
import com.webank.weid.service.impl.CredentialServiceImpl;

import java.util.HashMap;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/9/2
 * @time: 16:53
 */
public class demo01 {
    public static void main(String[] args) {

        String weId= "did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209";
        String publicKey="4818518833072282173770949061022161020134153013863998604521376515729480354647087769459660716573699399233936170136013054465239030826249531570139916660827466";
        String privateKey = "57262961193922983192585096379094476780371414196834152140262521340034169261079";


        CredentialService credentialService = new CredentialServiceImpl();

        HashMap<String, Object> claim = new HashMap<String, Object>(3);
        claim.put("name", "zhang san");
        claim.put("gender", "F");
        claim.put("age", 18);

        CreateCredentialArgs createCredentialArgs = new CreateCredentialArgs();
        createCredentialArgs.setClaim(claim);
        createCredentialArgs.setCptId(2);
        createCredentialArgs.setExpirationDate(2051448312461L);
        createCredentialArgs.setIssuer(weId);

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(privateKey);

        createCredentialArgs.setWeIdPrivateKey(weIdPrivateKey);

        ResponseData<CredentialWrapper> response = credentialService.createCredential(createCredentialArgs);
        System.out.printf(response.toString());
    }
}
