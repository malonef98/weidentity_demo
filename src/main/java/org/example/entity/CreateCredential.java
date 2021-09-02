package org.example.entity;

import lombok.Data;

import java.util.Map;

/**
 * @email $1602205111@qq.com
 * @author: mayifan
 * @date: 2021/9/2
 * @time: 14:46
 */
@Data
public class CreateCredential {
    private String issuer;

    private String priKey;

    private Long expirationDate;

    private Integer CptId;

    private Map<String,Object> claimData;

    private String type;

}
