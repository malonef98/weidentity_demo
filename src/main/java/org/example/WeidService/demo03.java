package org.example.WeidService;

import com.webank.weid.protocol.base.WeIdAuthentication;
import com.webank.weid.protocol.base.WeIdPrivateKey;
import com.webank.weid.protocol.base.WeIdPublicKey;
import com.webank.weid.protocol.request.CreateWeIdArgs;
import com.webank.weid.protocol.response.CreateWeIdDataResult;
import com.webank.weid.protocol.response.ResponseData;
import com.webank.weid.rpc.WeIdService;
import com.webank.weid.service.impl.WeIdServiceImpl;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Locale;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/8/30
 * @time: 15:42
 */
public class demo03 {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
       String weid = "did:weid:12138:0x87656e5468bf4a6baccec1d8a0fb7471370faf11";
       String pri = "113704271206653805471748042275847324604531081243138221766175077869462786070042";
       String pub = "5275813269581763565161769137119595925055999058850641340108741788838972554623063169004679340254737668463233874947478044102895815117345166885870330202084282";

       // Ecc
       ECKeyPair keyPair = GenCredential.createGuomiKeyPair();

       WeIdService weIdService = new WeIdServiceImpl();
       WeIdPublicKey weIdPublicKey = new WeIdPublicKey();
       weIdPublicKey.setPublicKey(keyPair.getPublicKey().toString());


        CreateWeIdArgs createWeIdArgs = new CreateWeIdArgs();
        createWeIdArgs.setPublicKey(keyPair.getPublicKey().toString());

        WeIdPrivateKey weIdPrivateKey = new WeIdPrivateKey();
        weIdPrivateKey.setPrivateKey(keyPair.getPrivateKey().toString());

        createWeIdArgs.setWeIdPrivateKey(weIdPrivateKey);

        ResponseData<String> response = weIdService.createWeId(createWeIdArgs);

        System.out.printf(response.getResult().toString());


        String delegateWeId = weid;
        
        String delegatePrivateKey = pri;

        WeIdAuthentication weIdAuthentication = new WeIdAuthentication(delegateWeId, delegatePrivateKey);

        weIdAuthentication.setWeIdPublicKeyId(weid +  "#key0");

       // ResponseData<String> response = weIdService.delegateCreateWeId(weIdPublicKey, weIdAuthentication);
        //System.out.printf(response.toString());
    }
}
