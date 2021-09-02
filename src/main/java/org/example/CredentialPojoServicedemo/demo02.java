package org.example.CredentialPojoServicedemo;

import com.webank.weid.protocol.base.CredentialPojo;
import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.cpt.Cpt101;
import com.webank.weid.protocol.request.CptStringArgs;
import com.webank.weid.protocol.request.CreateWeIdArgs;
import com.webank.weid.protocol.request.ServiceArgs;
import com.webank.weid.protocol.request.SetServiceArgs;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.WeIdServiceImpl;
import com.webank.weid.util.DataToolUtils;
import com.webank.weid.util.WeIdUtils;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;


import java.math.BigInteger;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/8/30
 * @time: 10:12
 */
public class demo02 {

    public static void main(String[] args) {
        String weId= "did:weid:12138:0x79982f28806aa9aa4544a7c1a8cd270665d3b209";
        String publicKey="4818518833072282173770949061022161020134153013863998604521376515729480354647087769459660716573699399233936170136013054465239030826249531570139916660827466";
        String privateKey = "57262961193922983192585096379094476780371414196834152140262521340034169261079";

        WeIdService weIdService = new WeIdServiceImpl();

        WeIdPublicKey weIdPublicKey = new WeIdPublicKey();
        weIdPublicKey.setPublicKey(publicKey);
        String delegateWeId = weId;
        String delegatePrivateKey = privateKey;
        WeIdAuthentication weIdAuthentication = new WeIdAuthentication(delegateWeId, delegatePrivateKey);
        weIdAuthentication.setWeIdPublicKeyId(weId + "#key0");

        ResponseData<String> response = weIdService.delegateCreateWeId(weIdPublicKey, weIdAuthentication);
        System.out.printf(response.toString());
    }

}
