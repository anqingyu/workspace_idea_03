package com.xf.maven_ssm.service.webapi;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 用户请求认证服务GetToken，将token保存在服务器端缓存中，并返回对应的Token到客户端（该请求不需要进行签名认证）,
 *      使用GET调用方式
 * @Author: xiefu
 * @Date: 2019/4/25 11:08
 */
public class ApiToken {

    /**
     * 用户请求认证服务GetToken，将token保存在服务器端缓存中，并返回对应的Token到客户端（该请求不需要进行签名认证）,使用GET调用方式
     * @param signKey
     * @return
     */
    /*public IHttpActionResult GetToken(String signKey)
    {
        if (StringUtils.isNotEmpty(signKey)){
            return Json<ResultMsg>(new ResultMsg((int)ExceptionStatus.ParameterError, EnumExtension.GetEnumText(ExceptionStatus.ParameterError), null));
        }

        //根据签名ID获取缓存token
        string strKey = string.Format("{0}{1}", WebConfig.signKey, signKey);
        Token cacheData = HttpRuntime.Cache.Get(strKey) as Token;
        if (cacheData == null)
        {
            cacheData = new Token();
            cacheData.signId = signKey;
            cacheData.timespan = DateTime.Now.AddDays(1);
            cacheData.signToken = Guid.NewGuid().ToString("N");
            //插入缓存，缓存时间为1天
            HttpRuntime.Cache.Insert(strKey, cacheData, null, cacheData.timespan, TimeSpan.Zero);
        }
        //返回token信息
        return Json<ResultMsg>(new ResultMsg((int)ExceptionStatus.OK, EnumExtension.GetEnumText(ExceptionStatus.OK), cacheData));
    }
*/
}
